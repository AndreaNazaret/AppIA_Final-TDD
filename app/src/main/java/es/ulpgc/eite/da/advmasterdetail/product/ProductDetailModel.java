package es.ulpgc.eite.da.advmasterdetail.product;

import es.ulpgc.eite.da.advmasterdetail.data.ProductItem;

public class ProductDetailModel implements ProductDetailContract.Model {

  public static String TAG = "AdvMasterDetail.ProductDetailModel";

  private ProductItem product;
  private boolean isFavorite;

  public ProductDetailModel() {

  }

  @Override
  public void onUpdatedDataFromRecreatedScreen(
          ProductItem product, boolean isFavorite) {

    // Log.e(TAG, "onUpdatedDataFromPreviousScreen()");

    this.product = product;
    this.isFavorite=isFavorite;
  }


}
