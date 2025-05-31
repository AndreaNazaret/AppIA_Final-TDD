package es.ulpgc.eite.da.advmasterdetail.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import es.ulpgc.eite.da.advmasterdetail.data.FavoriteItem;

//El Dao además de una tabla, indica las operaciones que se van a hacer contra la tabla
//En este caso se observa que se puede actualizar, borrar o insertar
@Dao
public interface FavoritesDao {

//Insertar los favoritos
// "OnConflictStrategy" -> Señala que si ya existe se borre y añada la nueva
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  void insertFavorites(List<FavoriteItem> favorites);



  //Insertar un solo favorito
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  void insertFavorite(FavoriteItem favorite);



  //Actualizar un favorito
  @Update
  void updateFavorites(FavoriteItem favorites);

  //Borrar un favorito
  @Delete
  void deleteFavorite(FavoriteItem favorite);

  //Buscar todos los usuarios
  @Query("SELECT * FROM favorites")
  List<FavoriteItem> loadFavorites();

  //Buscar un favorito por su ID
  @Query("SELECT nameTool FROM favorites WHERE emailUser = :emailUser LIMIT 1")
  List<String> loadFavorites(String emailUser);

  //Encontrar usuario por email
  @Query("SELECT * FROM favorites WHERE emailUser = :emailUser")
  List<FavoriteItem> loadFavoritesByUser(String emailUser);

}
