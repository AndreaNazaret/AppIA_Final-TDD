package es.ulpgc.eite.da.advmasterdetail.favorites;

import android.util.Log;

import es.ulpgc.eite.da.advmasterdetail.data.RepositoryContract;

public class FavoritesModel implements FavoritesContract.Model {

    public static String TAG = "Adv Master-Detail.FavoritesModel";

    private RepositoryContract repository;

    public FavoritesModel(RepositoryContract repository){
        this.repository = repository;
    }

    @Override
    public void fetchFavoritesData(String emailUser, RepositoryContract.GetFavoritesCallback callback) {

        Log.e(TAG, "fetchFavoritesData() llamado en el modelo, se le ha pasado el usuario" + emailUser);
        repository.getFavoritesListData(emailUser, callback);
    }
}
