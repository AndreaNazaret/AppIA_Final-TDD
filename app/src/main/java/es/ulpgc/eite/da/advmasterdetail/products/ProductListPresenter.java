package es.ulpgc.eite.da.advmasterdetail.products;

import android.util.Log;

import java.lang.ref.WeakReference;
import java.util.List;

import es.ulpgc.eite.da.advmasterdetail.app.CatalogMediator;
import es.ulpgc.eite.da.advmasterdetail.data.CategoryItem;
import es.ulpgc.eite.da.advmasterdetail.data.ProductItem;
import es.ulpgc.eite.da.advmasterdetail.data.RepositoryContract;


public class ProductListPresenter implements ProductListContract.Presenter {

  public static String TAG = "AdvMasterDetail.ProductListPresenter";

  private WeakReference<ProductListContract.View> view;
  private ProductListState state;
  private ProductListContract.Model model;
  private CatalogMediator mediator;

  public ProductListPresenter(CatalogMediator mediator) {
    this.mediator = mediator;
  }


  @Override
  public void onCreateCalled() {
    // Log.e(TAG, "onCreateCalled");

    state = new ProductListState();
  }

  @Override
  public void onRecreateCalled() {
    // Log.e(TAG, "onRecreateCalled");

    state = mediator.getProductListState();
    state.emailUser = mediator.getProductListState().emailUser;
    Log.e(TAG, "EmailUser que ha llegado a ProductList: " + state.emailUser);

  }

  @Override
  public void onPauseCalled() {
    Log.e(TAG, "onPauseCalled()");

    mediator.setProductListState(state);
  }


  @Override
  public void fetchProductListData() {
    // Log.e(TAG, "fetchProductListData()");

    // set passed state
    CategoryItem category = mediator.getCategory();
    state.emailUser = mediator.getCategoryListState().emailUser;
    state.isGuest = mediator.getCategoryListState().isGuest;

    //Log.e(TAG, "EmailUser que se ha guadado en el state de CategoryList: " + mediator.getCategoryListState().emailUser);

    Log.e(TAG, "Datos que han llegado a ProductList: " + state.emailUser + "isGuest: " + state.isGuest);


    if (category != null) {
      state.category = category;
    }

    // call the model
    model.fetchProductListData(state.category, products -> {
      state.products = products;

      view.get().displayProductListData(state);
    });

  }

  @Override
  public void selectedProductData(ProductItem item) {
    mediator.setProduct(item);
    view.get().navigateToProductDetailScreen();
  }

  @Override
  public void injectView(WeakReference<ProductListContract.View> view) {
    this.view = view;
  }

  @Override
  public void injectModel(ProductListContract.Model model) {
    this.model = model;
  }


}
