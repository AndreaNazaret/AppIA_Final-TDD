package es.ulpgc.eite.da.advmasterdetail.data;


import static androidx.room.ForeignKey.CASCADE;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

//Definición de la clave foránea que relaciona las tablas
//Parent (Category) tiene que buscar el ID y meterlo en la tabla hijo (Products) en la columna category_ID
@Entity(
    tableName = "products",
    foreignKeys = @ForeignKey(
        entity = CategoryItem.class,
        parentColumns = "id",
        childColumns = "category_id",
        onDelete = CASCADE //Si se borra una categoria se borran todos los productos de esta
    )
)

//La tabla productos tiene el ID, el titulo, la descripcion y la imagen
public class ProductItem {

  @PrimaryKey(autoGenerate = true)
  public int id;

  public String name;
  public String developer;
  public String details ;
  public String imageName;
  public int imageResId;


  //La herramienta tiene que tener una categoria que identificaremos con su ID
  @ColumnInfo(name = "category_id")
  public int category_id;


  @Override
  public String toString() {
    return name;
  }

}