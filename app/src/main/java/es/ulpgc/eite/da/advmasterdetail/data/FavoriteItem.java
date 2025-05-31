package es.ulpgc.eite.da.advmasterdetail.data;


import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.util.List;

//Una fila es una Entity -> Aquí se especifican las columnas de una tabla
@Entity(tableName = "favorites")
public class FavoriteItem {

  @PrimaryKey(autoGenerate = true)
  public int id; //Identificador de cada fila

  public String emailUser; //Nombre Usuario

  public String nameTool;

  @Override
  public String toString() {
    return emailUser;
  }
}