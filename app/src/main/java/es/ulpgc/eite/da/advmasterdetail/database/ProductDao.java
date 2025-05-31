package es.ulpgc.eite.da.advmasterdetail.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import es.ulpgc.eite.da.advmasterdetail.data.ProductItem;

//El Dao de la tabla Producto refleja las operaciones que vamos ha hacer con la tabla
@Dao
public interface ProductDao {


  @Insert(onConflict = OnConflictStrategy.REPLACE)
  void insertProduct(ProductItem product);

  @Update
  void updateProduct(ProductItem product);

  @Delete
  void deleteProduct(ProductItem product);

  @Query("SELECT * FROM products")
  List<ProductItem> loadProducts();

  @Query("SELECT * FROM products WHERE id = :id LIMIT 1")
  ProductItem loadProduct(int id);

  //Dame los productos de una categoria en concreto segun el ID de esta
  @Query("SELECT * FROM products WHERE category_id=:categoryId")
  List<ProductItem> loadProducts(final int categoryId);

  @Query("SELECT * FROM products")
  List<ProductItem> loadProductList();

}
