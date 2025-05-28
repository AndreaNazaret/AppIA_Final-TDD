package es.ulpgc.eite.da.advmasterdetail.register;

import android.util.Log;

import es.ulpgc.eite.da.advmasterdetail.data.RepositoryContract;

public class RegisterModel implements RegisterContract.Model {

    public static String TAG = "Adv Master-Detail.RegisterModel";

    private RepositoryContract repository;


    public RegisterModel(RepositoryContract repository) {
        this.repository = repository;

    }

    @Override
    public void verifyUser(String email, String password, RepositoryContract.VerifyUserCallback callback) {
        repository.verifyUser(email, password, callback);
    }

    @Override
    public void addUser(String name, String apellido, String email, String password, RepositoryContract.AddUserCallback callback){
        repository.addUser(name,apellido, email, password, callback);
    }

/*
    @Override
    public String getStoredData() {
        // Log.e(TAG, "getStoredData()");

        return data;
    }


    @Override
    public String getSavedData() {
        // Log.e(TAG, "getSavedData()");

        return data;
    }


    @Override
    public String getCurrentData() {
        // Log.e(TAG, "getCurrentData()");

        return data;
    }

    @Override
    public void setCurrentData(String data) {
        // Log.e(TAG, "setCurrentData()");

        this.data = data;
    }

    @Override
    public void onUpdatedDataFromRecreatedScreen(String data) {
        // Log.e(TAG, "onUpdatedDataFromRecreatedScreen()");


    }

    @Override
    public void onUpdatedDataFromNextScreen(String data) {
        // Log.e(TAG, "onUpdatedDataFromNextScreen()");


    }

    @Override
    public void onUpdatedDataFromPreviousScreen(String data) {
        // Log.e(TAG, "onUpdatedDataFromPreviousScreen()");


    }

 */
}
