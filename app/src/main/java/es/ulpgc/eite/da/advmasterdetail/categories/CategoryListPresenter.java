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
    LoginState loginState = mediator.getLoginState();
    state.isGuest = loginState.isGuest;

    mediator.setCategoryListState(state);

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

    //De modo asincrono se solicita al modelo los datos
    // call the model
    model.fetchCategoryListData(categories -> {

      state.categories = categories;

      view.get().displayCategoryListData(state);
    });

    /*model.fetchCategoryListData(new RepositoryContract.GetCategoryListCallback() {

      @Override
      public void setCategoryList(List<CategoryItem> categories) {
        state.categories = categories;

        view.get().displayCategoryListData(state);
      }
    });*/

  }


  @Override
  public void onFavButtonClicked (String emailUser){
    state.emailUser= emailUser;
    Log.d(TAG, "Usuario que se le pasara a FavActivity" + state.emailUser);
    view.get().navigateToFavoriteScreen(emailUser);
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
