package es.ulpgc.eite.da.advmasterdetail.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import es.ulpgc.eite.da.advmasterdetail.data.CategoryItem;
import es.ulpgc.eite.da.advmasterdetail.data.FavoriteItem;
import es.ulpgc.eite.da.advmasterdetail.data.ProductItem;
import es.ulpgc.eite.da.advmasterdetail.data.UsersItem;

// Accesos a tablas en este caso observamos que solo hay tres (Cada Dao es una tabla)
//CategoryItem y ProductItem son las columnas de cada tabla
@Database(entities = {CategoryItem.class, ProductItem.class, UsersItem.class, FavoriteItem.class}, version = 9)
public abstract class CatalogDatabase extends RoomDatabase {

  public abstract CategoryDao categoryDao();
  public abstract ProductDao productDao();
  public abstract UsersDao usersDao();

  public abstract FavoritesDao favoritesDao();
}
