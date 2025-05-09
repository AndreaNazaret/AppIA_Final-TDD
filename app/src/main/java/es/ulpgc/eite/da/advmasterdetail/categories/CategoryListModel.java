package es.ulpgc.eite.da.advmasterdetail.categories;

import android.util.Log;

import es.ulpgc.eite.da.advmasterdetail.data.RepositoryContract;


public class CategoryListModel implements CategoryListContract.Model {

  public static String TAG = "AdvMasterDetail.CategoryListModel";

  private RepositoryContract repository;

  public CategoryListModel(RepositoryContract repository) {
    this.repository = repository;

  }

  @Override
  public void fetchCategoryListData(
      final RepositoryContract.GetCategoryListCallback callback) {

    Log.e(TAG, "fetchCategoryListData()");

    //Si es true cada vez que se ejecuta la aplicación se accede al JSON para crear nuevamente la base de datos


        repository.getCategoryList(callback); //Se le piden los datos al repositorio
  }

    /*
    repository.loadCatalog(
        true, new RepositoryContract.FetchCatalogDataCallback() {

      @Override
      public void onCatalogDataFetched(boolean error) {
        if(!error) {
          repository.getCategoryList(callback);
        }
      }
    });
    */

  }


