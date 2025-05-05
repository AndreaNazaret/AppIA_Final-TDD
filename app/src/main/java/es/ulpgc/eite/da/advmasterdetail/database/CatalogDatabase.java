package es.ulpgc.eite.da.advmasterdetail.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import es.ulpgc.eite.da.advmasterdetail.data.CategoryItem;
import es.ulpgc.eite.da.advmasterdetail.data.ProductItem;

// Accesos a tablas en este caso observamos que solo hay dos (Cada Dao es una tabla)
//CategoryItem y ProductItem son las columnas de cada tabla
@Database(entities = {CategoryItem.class, ProductItem.class}, version = 1)
public abstract class CatalogDatabase extends RoomDatabase {

  public abstract CategoryDao categoryDao();
  public abstract ProductDao productDao();
}
