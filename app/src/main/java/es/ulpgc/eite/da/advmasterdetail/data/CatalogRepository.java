package es.ulpgc.eite.da.advmasterdetail.data;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.room.Room;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

import es.ulpgc.eite.da.advmasterdetail.database.CatalogDatabase;
import es.ulpgc.eite.da.advmasterdetail.database.CategoryDao;
import es.ulpgc.eite.da.advmasterdetail.database.ProductDao;
import es.ulpgc.eite.da.advmasterdetail.database.UsersDao;


public class CatalogRepository implements RepositoryContract {

  public static String TAG = CatalogRepository.class.getSimpleName();


  public static final String DB_FILE = "catalog.db";
  public static final String JSON_FILE = "catalog.json";
  public static final String JSON_ROOT = "categories";

  private static CatalogRepository INSTANCE;

  private CatalogDatabase database;
  private Context context;


  public static RepositoryContract getInstance(Context context) {
    if(INSTANCE == null){
      INSTANCE = new CatalogRepository(context);
    }

    return INSTANCE;
  }

  // Metodo para crear la base de datos
  private CatalogRepository(Context context) {
    this.context = context;

    database = Room.databaseBuilder(context, CatalogDatabase.class, DB_FILE)
            .fallbackToDestructiveMigration() //Elimina ("destruye") la base de datos existente y la vuelve a crear desde cero con la nueva estructura.
            .build();

  }

  //El repositorio inicia una tarea


  private boolean isCatalogLoaded = false;

  @Override
  public void loadCatalog(
      final boolean clearFirst, final FetchCatalogDataCallback callback) {

    //1º se asegura se si clear first esta activo para saber si borra todo o no
    Log.d(TAG, "loadCatalog called. clearFirst=" + clearFirst);

    if (isCatalogLoaded && !clearFirst) {
      Log.d(TAG, "Catalog already loaded, skipping reload.");
      if (callback != null) callback.onCatalogDataFetched(false);
      return;
    }

    AsyncTask.execute(() -> {
      if(clearFirst) {
        Log.d(TAG, "Clearing database and resetting ID sequence...");
        database.clearAllTables();
        database.getOpenHelper().getWritableDatabase().execSQL("DELETE FROM sqlite_sequence WHERE name='users';"); //Nos permite reiniciar los ID automáticos cuando se regenera la tabla
        isCatalogLoaded = false;
      }

      //Carga del JSON la BD
      boolean error = false;
      if (database.categoryDao().loadCategories().isEmpty()) {
        error = !loadCatalogFromJSON(loadJSONFromAsset());
        isCatalogLoaded = !error;
      } else {
        isCatalogLoaded = true;
      }

      if (callback != null) callback.onCatalogDataFetched(error);
    });

  }




  @Override
  public void getProductList(
      final CategoryItem category, final GetProductListCallback callback) {

    getProductList(category.id, callback);
  }


  @Override
  public void getProductList(
      final int categoryId, final GetProductListCallback callback) {

    AsyncTask.execute(() -> {
      if(callback != null) {
        callback.setProductList(getProductDao().loadProducts(categoryId));
      }
    });

  }


  @Override
  public void getProduct(final int id, final GetProductCallback callback) {

    AsyncTask.execute(() -> {
      if(callback != null) {
        callback.setProduct(getProductDao().loadProduct(id));
      }
    });
  }

  @Override
  public void getCategory(final int id, final GetCategoryCallback callback) {

    AsyncTask.execute(() -> {
      if(callback != null) {
        callback.setCategory(getCategoryDao().loadCategory(id));
      }
    });

  }

  @Override
  public void getCategoryList(final GetCategoryListCallback callback) {
    AsyncTask.execute(() -> {
      if(callback != null) {
        callback.setCategoryList(getCategoryDao().loadCategories());
      }
    });

  }

  @Override
  public void deleteProduct(
      final ProductItem product, final DeleteProductCallback callback) {

    AsyncTask.execute(() -> {
      if(callback != null) {
        getProductDao().deleteProduct(product);
        callback.onProductDeleted();
      }
    });
  }

  @Override
  public void updateProduct(
      final ProductItem product, final UpdateProductCallback callback) {

    AsyncTask.execute(() -> {
      if(callback != null) {
        getProductDao().updateProduct(product);
        callback.onProductUpdated();
      }
    });
  }


  @Override
  public void deleteCategory(
      final CategoryItem category, final DeleteCategoryCallback callback) {

    AsyncTask.execute(() -> {
      if(callback != null) {
        getCategoryDao().deleteCategory(category);
        callback.onCategoryDeleted();
      }
    });
  }

  @Override
  public void updateCategory(
      final CategoryItem category, final UpdateCategoryCallback callback) {

    AsyncTask.execute(() -> {
      if(callback != null) {
        getCategoryDao().updateCategory(category);
        callback.onCategoryUpdated();
      }
    });
  }


  private CategoryDao getCategoryDao() {
    return database.categoryDao();
  }

  private ProductDao getProductDao() {
    return database.productDao();
  }

  private UsersDao getUsersDao() {
    return database.usersDao();
  }


  private boolean loadCatalogFromJSON(String json) {
    Log.e(TAG, "loadCatalogFromJSON()");

    GsonBuilder gsonBuilder = new GsonBuilder();
    Gson gson = gsonBuilder.create();

    try {
      JSONObject jsonObject = new JSONObject(json);

      // Cargar Categorías
      if (jsonObject.has(JSON_ROOT)) {
        JSONArray jsonArray = jsonObject.getJSONArray(JSON_ROOT);

        if (jsonArray.length() > 0) {
          final List<CategoryItem> categories = Arrays.asList(
                  gson.fromJson(jsonArray.toString(), CategoryItem[].class)
          );

          for (CategoryItem category : categories) {
            getCategoryDao().insertCategory(category);
          }

          for (CategoryItem category : categories) {
            for (ProductItem product : category.items) {
              product.categoryId = category.id;
              getProductDao().insertProduct(product);
            }
          }
        }
      }

      // Cargar Usuarios
      if (jsonObject.has("Users")) {
        JSONArray usersArray = jsonObject.getJSONArray("Users");

        if (usersArray.length() > 0) {
          final List<UsersItem> users = Arrays.asList(
                  gson.fromJson(usersArray.toString(), UsersItem[].class)
          );


            try {
              getUsersDao().insertUsers(users);
              Log.d(TAG, "User Inserted: " + users.size());
              // Verificar qué usuarios hay en la base de datos después de la inserción
              List<UsersItem> allUsers = getUsersDao().loadUsers();
              for (UsersItem u : allUsers) {
                Log.d(TAG, "User in DB -> ID: " + u.id + ", Email: " + u.email + ", Password: " + u.password);
              }
            } catch (Exception e) {
              Log.e(TAG, "Error inserting user.");
            }

        }
      }

      return true;

    } catch (JSONException error) {
      Log.e(TAG, "JSON Parsing Error: " + error.getMessage(), error);
    } catch (Exception ex) {
      Log.e(TAG, "Unexpected Error: " + ex.getMessage(), ex);
    }

    return false;
  }



  //Se carga el JSON en memoria como un String de texto plano

  private String loadJSONFromAsset( )  {

    //Log.e(TAG, "loadJSONFromAsset()");

    String json = null;

    try {

      InputStream inputStream = context.getAssets().open(JSON_FILE);
      BufferedReader reader =
              new BufferedReader(new InputStreamReader(inputStream));
      StringBuilder stringBuilder = new StringBuilder();
      String line;

      while ((line = reader.readLine()) != null) {
        stringBuilder.append(line);
      }

      reader.close();
      json = stringBuilder.toString();

      //Log.e(TAG, "JSON: " + json);

    } catch (IOException error) {
      Log.e(TAG, "error: " + error);
    }

    return json;
  }




  @Override
  public void verifyUser(String email, String password, VerifyUserCallback callback) {
    AsyncTask.execute(() -> {
      try {
        Log.d(TAG, "Checking user in DB: Email=" + email);
        UsersItem user = database.usersDao().findUserByEmail(email);

        boolean isValid = (user != null && user.password != null && user.password.equals(password));

        if (user != null) {
          Log.d(TAG, "User Found -> Email: " + user.email + ", Password: " + user.password);
          boolean passwordMatches = user.password.equals(password);
          Log.d(TAG, "Password Entered: " + password + ", Password Match: " + passwordMatches);
        } else {
          Log.d(TAG, "User Found: false");
        }

        callback.onVerificationResult(isValid);

      } catch (Exception e) {
        Log.e(TAG, "Error en verifyUser()", e);
        callback.onVerificationResult(false);
      }
    });
  }

  @Override
  public void addUser(String name, String apellido, String email, String password, AddUserCallback callback){
    AsyncTask.execute(() -> {
      try{
        UsersItem user = new UsersItem();
        user.firstName= name;
        user.lastName = apellido;
        user.email = email;
        user.password = password;

        database.usersDao().insertUser(user);
        callback.onVerificationResultAdd(true);
      } catch (Exception e) {
        Log.e(TAG, "Error en el try de addUser del repository", e);
        callback.onVerificationResultAdd(false);
      }
    });
  }


}
