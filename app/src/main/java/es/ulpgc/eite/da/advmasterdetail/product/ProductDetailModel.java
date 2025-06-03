package es.ulpgc.eite.da.advmasterdetail.product;

import es.ulpgc.eite.da.advmasterdetail.data.ProductItem;
import es.ulpgc.eite.da.advmasterdetail.data.RepositoryContract;

public class ProductDetailModel implements ProductDetailContract.Model {

  public static String TAG = "AdvMasterDetail.ProductDetailModel";

  private ProductItem product;
  private boolean isFavorite;
  private RepositoryContract repository;

  public ProductDetailModel(RepositoryContract repository) {
    this.repository = repository;
  }

  @Override
  public void onUpdatedDataFromRecreatedScreen(
          ProductItem product, boolean isFavorite) {

    // Log.e(TAG, "onUpdatedDataFromPreviousScreen()");

    this.product = product;
    this.isFavorite=isFavorite;
  }

  @Override
  public void verifyFavorite(String emailUser, String nameTool, RepositoryContract.VerifyFavoriteCallback callback) {
    repository.verifyFavorite(emailUser, nameTool, callback);
  }

  @Override
  public void addFavorite(String emailUser, String nameTool, RepositoryContract.AddFavoritesCallback callback){
    repository.addFavorite(emailUser,nameTool, callback);
  }

  @Override
  public void removeFavorite(String emailUser, String nameTool, RepositoryContract.RemoveFavoritesCallback callback){
    repository.removeFavorite(emailUser, nameTool, callback);
  }


}
