package es.ulpgc.eite.da.advmasterdetail.data;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.util.List;

//Una fila es una Entity -> Aquí se especifican las columnas de una tabla, en este caso; ID, firstName, lastName, email, password
@Entity(tableName = "users")
public class UsersItem {

  @PrimaryKey(autoGenerate = true)
  public int id; //Identificador de cada fila

  public String firstName; //Nombre
  public String lastName; //Apellido
  public String email; //Email
  public String password; //Contraseña


  @Override
  public String toString() {
    return firstName;
  }
}