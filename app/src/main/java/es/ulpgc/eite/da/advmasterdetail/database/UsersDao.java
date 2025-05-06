package es.ulpgc.eite.da.advmasterdetail.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import es.ulpgc.eite.da.advmasterdetail.data.UsersItem;

//El Dao además de una tabla, indica las operaciones que se van a hacer contra la tabla
//En este caso se observa que se puede actualizar, borrar o insertar
@Dao
public interface UsersDao {

//Insertar un usuario
// "OnConflictStrategy" -> Señala que si ya existe se borre y añada la nueva
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  void insertUsers(UsersItem users);


  //Actualizar un usuario
  @Update
  void updateUsers(UsersItem users);

  //Borrar un usuario
  @Delete
  void deleteUsers(UsersItem users);

  //Buscar todos los usuarios
  @Query("SELECT * FROM users")
  List<UsersItem> loadUsers();

  //Buscar un usuario por su ID
  @Query("SELECT * FROM users WHERE id = :id LIMIT 1")
  UsersItem loadUsers(int id);
}
