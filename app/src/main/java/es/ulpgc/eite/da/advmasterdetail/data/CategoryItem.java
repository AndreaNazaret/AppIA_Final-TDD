package es.ulpgc.eite.da.advmasterdetail.data;


import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.util.List;

//Una fila es una Entity -> Aquí se especifican las columnas de una tabla, en este caso; ID, content y details
@Entity(tableName = "categories")
public class CategoryItem {

  @PrimaryKey
  public int id;

  public String title;
  public String description;
  public String details;
  public String imageName;
  public int imageResId;

  @Ignore //No se puede meter un array en un elemento de la tabla de la BD
  @SerializedName("products")
  public List<ProductItem> items;  //Llama a al otra tabla de productas asociada a esa categoria

  @Override
  public String toString() {
    return title;
  }
}