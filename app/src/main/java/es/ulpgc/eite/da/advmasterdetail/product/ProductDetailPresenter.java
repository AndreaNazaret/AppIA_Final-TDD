package es.ulpgc.eite.da.advmasterdetail.product;

import android.util.Log;

import java.lang.ref.WeakReference;

import es.ulpgc.eite.da.advmasterdetail.app.CatalogMediator;
import es.ulpgc.eite.da.advmasterdetail.categories.CategoryListState;
import es.ulpgc.eite.da.advmasterdetail.data.CategoryItem;
import es.ulpgc.eite.da.advmasterdetail.data.ProductItem;


public class ProductDetailPresenter implements ProductDetailContract.Presenter {

  public static String TAG = "AdvMasterDetail.ProductDetailPresenter";

  private WeakReference<ProductDetailContract.View> view;
  private ProductDetailState state;
  private ProductDetailContract.Model model;
  private CatalogMediator mediator;

  public ProductDetailPresenter(CatalogMediator mediator) {
    this.mediator = mediator;
  }

  @Override
  public void onCreateCalled() {
    // Log.e(TAG, "onCreateCalled");

    state = new ProductDetailState();
  }

  @Override
  public void onRecreateCalled() {
    // Log.e(TAG, "onRecreateCalled");

    state = mediator.getProductDetailState();
  }

  @Override
  public void onPauseCalled() {
    Log.e(TAG, "onPauseCalled()");

    mediator.setProductDetailState(state);
  }


  private ProductItem getDataFromPreviousScreen() {

    // set passed state
    CategoryItem category = mediator.getCategory();
    String emailUser = new String();

    if(mediator.getProductListState() != null){
       emailUser =mediator.getProductListState().emailUser;
    }else{
      emailUser = mediator.getFavoritesState().emailUser;
    }

    state.emailUser=emailUser;


    if (category != null) {
      state.category = category;
    }

    ProductItem product = mediator.getProduct();

    mediator.setProductDetailState(state);

    return product;
  }


  @Override
  public void fetchProductDetailData() {
    // Log.e(TAG, "fetchProductDetailData()");
    // set passed state
    ProductItem product = getDataFromPreviousScreen();
    if(product != null) {
        state.product = product;
    }

    String emailUser = state.emailUser;
    String nameTool = product.name;

    model.verifyFavorite(emailUser, nameTool, success -> {
      state.isFavorite = success;
      view.get().displayProductDetailData(state);
    });

    view.get().displayProductDetailData(state);
  }


  @Override
  public void onFavoriteButtonClicked() {
    Log.e(TAG, "onFavoriteButtonClicked()");

    // TODO: include code if necessary
    state.isFavorite=!state.isFavorite;
    model.onUpdatedDataFromRecreatedScreen(state.product, state.isFavorite);

    String nameTool = state.product.name;
    String emailUser =new String();

    if(mediator.getProductListState() != null){
      emailUser = mediator.getProductListState().emailUser;
    }else{
      emailUser = mediator.getFavoritesState().emailUser;
    }


    if(state.isFavorite){
      Log.d(TAG, "Se quiere añadir la herramienta " + nameTool + " para el usuario " + emailUser);

      String finalEmailUser = emailUser;
      model.verifyFavorite(emailUser, nameTool, success -> {
        if (success){
          new android.os.Handler(android.os.Looper.getMainLooper()).post(() ->
                  view.get().showFavoriteAddError()

          );
          Log.d(TAG, "No se puede eliminar porque ya esta en la BD");
        }else{
          model.addFavorite(finalEmailUser, nameTool, success2 -> {
            if(success2){
              new android.os.Handler(android.os.Looper.getMainLooper()).post(() ->
                      view.get().showFavoriteAddCorrect()
              );
            }else{
              new android.os.Handler(android.os.Looper.getMainLooper()).post(() ->
                      view.get().showFavoriteAddError()
              );
              Log.d(TAG, "No se puede añadir a la BD pero tampoco esta en esta");
            }
          });
        }
      });
    }else{

      Log.d(TAG, "Se quiere quitar la herramienta " + nameTool + " para el usuario " + emailUser);

      String finalEmailUser1 = emailUser;
      model.verifyFavorite(emailUser, nameTool, success -> {
        if(success){
          model.removeFavorite(finalEmailUser1, nameTool, success2 -> {
            if(success2){
              new android.os.Handler(android.os.Looper.getMainLooper()).post(() ->
                      view.get().showFavoriteRemoveCorrect()
              );

            }else{
              new android.os.Handler(android.os.Looper.getMainLooper()).post(() ->
                      view.get().showFavoriteRemoveError()
              );
              Log.d(TAG, "No se puede eliminar pero esta en la BD");
            }
          });
        }else{
          new android.os.Handler(android.os.Looper.getMainLooper()).post(() ->
                  view.get().showFavoriteRemoveError()

          );
          Log.d(TAG, "No se puede eliminar porque no esta en la BD");
        }
      });
    }
    mediator.setProductDetailState(state);
    view.get().displayProductDetailData(state);
  }

  @Override
  public void favNotEnableClicked(){
    new android.os.Handler(android.os.Looper.getMainLooper()).post(() ->
            view.get().showFavErrorFavGuest()
    );
  }

  @Override
  public void injectView(WeakReference<ProductDetailContract.View> view) {
    this.view = view;
  }

  @Override
  public void injectModel(ProductDetailContract.Model model) {
    this.model = model;
  }

}
