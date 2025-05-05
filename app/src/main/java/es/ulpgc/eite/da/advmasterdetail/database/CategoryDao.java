package es.ulpgc.eite.da.advmasterdetail.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import es.ulpgc.eite.da.advmasterdetail.data.CategoryItem;

//El Dao además de una tabla, indica las operaciones que se van a hacer contra la tabla
//En eset caso se observa que se puede actualizar, borrar o insertar
@Dao
public interface CategoryDao {

//Insertar una categoria
// "OnConflictStrategy" -> Señala que si ya existe se borre y añada la nueva
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  void insertCategory(CategoryItem category);

  //Actualizar una categoria
  @Update
  void updateCategory(CategoryItem category);

  //Borrar una categoria
  @Delete
  void deleteCategory(CategoryItem category);

  //Buscar todas las categorias
  @Query("SELECT * FROM categories")
  List<CategoryItem> loadCategories();

  //Buscar una categoria por su ID
  @Query("SELECT * FROM categories WHERE id = :id LIMIT 1")
  CategoryItem loadCategory(int id);
}
