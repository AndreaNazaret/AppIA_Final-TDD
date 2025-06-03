package es.ulpgc.eite.da.advmasterdetail.categories;

import android.util.Log;

import java.lang.ref.WeakReference;
import java.util.List;

import es.ulpgc.eite.da.advmasterdetail.app.CatalogMediator;
import es.ulpgc.eite.da.advmasterdetail.data.CategoryItem;
import es.ulpgc.eite.da.advmasterdetail.data.RepositoryContract;
import es.ulpgc.eite.da.advmasterdetail.login.LoginState;


public class CategoryListPresenter implements CategoryListContract.Presenter {

  public static String TAG = "AdvMasterDetail.CategoryListPresenter";

  private WeakReference<CategoryListContract.View> view;
  private CategoryListState state;
  private CategoryListContract.Model model;
  private CatalogMediator mediator;


  public CategoryListPresenter(CatalogMediator mediator) {
    this.mediator = mediator;
  }

  @Override
  public void onCreateCalled() {
    // Log.e(TAG, "onCreateCalled");

    state = new CategoryListState(); //Crea el estado

    // Copiar estado del login


  }

  @Override
  public void onRecreateCalled() {
    // Log.e(TAG, "onRecreateCalled");

    state = mediator.getCategoryListState();
  }

  @Override
  public void onPauseCalled() {
    Log.e(TAG, "onPauseCalled()");

    mediator.setCategoryListState(state);
  }

  //Equivalente al onResume();
  @Override
  public void fetchCategoryListData() {
    // Log.e(TAG, "fetchCategoryListData");
    state.emailUser= mediator.getLoginState().emailUser;
    state.isGuest= mediator.getLoginState().isGuest;

    //De modo asincrono se solicita al modelo los datos
    model.fetchCategoryListData(categories -> {

      state.categories = categories;

      view.get().displayCategoryListData(state);
    });

    mediator.setCategoryListState(state);

  }


  @Override
  public void onFavButtonClicked (){
    state.emailUser= mediator.getLoginState().emailUser;
    mediator.setCategoryListState(state);
    Log.d(TAG, "Usuario que se le pasará a FavActivity" + state.emailUser);
    view.get().navigateToFavoriteScreen();
  }



  @Override
  public void favNotEnableClicked(){
    new android.os.Handler(android.os.Looper.getMainLooper()).post(() ->
            view.get().showLoginErrorFavGuest()
    );
  }

  @Override
  public void selectedCategoryData(CategoryItem item) {
    mediator.setCategory(item);
    Log.d(TAG, "Usuario que se le pasará a ProductList " + state.emailUser);
    view.get().navigateToProductListScreen();
  }


  @Override
  public void injectView(WeakReference<CategoryListContract.View> view) {
    this.view = view;
  }

  @Override
  public void injectModel(CategoryListContract.Model model) {
    this.model = model;
  }


}
