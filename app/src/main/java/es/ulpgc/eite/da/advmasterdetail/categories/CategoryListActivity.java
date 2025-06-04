package es.ulpgc.eite.da.advmasterdetail.categories;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import es.ulpgc.eite.da.advmasterdetail.R;
import es.ulpgc.eite.da.advmasterdetail.app.CatalogMediator;
import es.ulpgc.eite.da.advmasterdetail.data.CategoryItem;
import es.ulpgc.eite.da.advmasterdetail.favorites.FavoritesActivity;
import es.ulpgc.eite.da.advmasterdetail.login.LoginState;
import es.ulpgc.eite.da.advmasterdetail.products.ProductListActivity;


public class CategoryListActivity
        extends AppCompatActivity implements CategoryListContract.View {

  public static String TAG = "AdvMasterDetail.CategoryListActivity";

  CategoryListContract.Presenter presenter;

  private CatalogMediator mediator;

  private CategoryListAdapter listAdapter;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_category_list);
    // Cambiar título de la barra
    if (getSupportActionBar() != null) {
      getSupportActionBar().hide();
    }

    //Cambiamos los iconos de la barra de notificaciones ya que pusimos la barra blanca en themes
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
      getWindow().setStatusBarColor(Color.WHITE);
      getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }

    Log.e(TAG, "CategoryListActivity onCreate INICIADO");

    // do the setup
    CategoryListScreen.configure(this);

    // do some work
    initCategoryListContainer();



    if (presenter != null) {
      LoginState state = CatalogMediator.getInstance().getLoginState();
      if (state != null && state.isGuest) {
        findViewById(R.id.fab_favorites).setAlpha(0.5f);
        findViewById(R.id.fab_favorites).setOnClickListener(view -> presenter.favNotEnableClicked());
      }else{
        findViewById(R.id.fab_favorites).setOnClickListener(view -> presenter.onFavButtonClicked());
      }
    }


    if(savedInstanceState == null) {
      presenter.onCreateCalled();

    }else{
      presenter.onRecreateCalled();
    }
  }

  @Override
  protected void onResume() {
    super.onResume();

    // do some work
    presenter.fetchCategoryListData();
  }

  private void initCategoryListContainer() {

    listAdapter = new CategoryListAdapter(view -> {
      CategoryItem item = (CategoryItem) view.getTag(); //Se le pasan los datos
      presenter.selectedCategoryData(item);
    });

    RecyclerView recyclerView = findViewById(R.id.category_recycler);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    recyclerView.setAdapter(listAdapter);
  }

  @Override
  protected void onPause() {
    super.onPause();

    presenter.onPauseCalled();
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
  }

  @Override
  public void displayCategoryListData(final CategoryListViewModel viewModel) {
    Log.e(TAG, "displayCategoryListData()");

    //Para actualizar el recyclerView hay q pasarle los datos al Adapter
    runOnUiThread(() -> {

      // deal with the data
      listAdapter.setItems(viewModel.categories);
    });

  }

  @Override
  public void navigateToProductListScreen() {
    Intent intent = new Intent(this, ProductListActivity.class);
    //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    startActivity(intent);
  }

  @Override
  public void navigateToFavoriteScreen() {
    try {
      Intent intent = new Intent(this, FavoritesActivity.class);
      startActivity(intent);
      Log.e(TAG, "Intent lanzado correctamente hacia favorite");
    }catch (Exception e) {
      Log.e(TAG, "ERROR al lanzar FavoritesActivity: " + e.getMessage(), e);
    }
  }

  @Override
  public void showLoginErrorFavGuest(){
    String message = getString(R.string.fav_error_message);
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
  }

  @Override
  public void injectPresenter(CategoryListContract.Presenter presenter) {
    this.presenter = presenter;
  }


}
