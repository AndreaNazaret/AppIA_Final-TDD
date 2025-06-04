package es.ulpgc.eite.da.advmasterdetail.product;

import java.lang.ref.WeakReference;

import es.ulpgc.eite.da.advmasterdetail.data.ProductItem;
import es.ulpgc.eite.da.advmasterdetail.data.RepositoryContract;

interface ProductDetailContract {

  interface View {
    void injectPresenter(Presenter presenter);

    void displayProductDetailData(ProductDetailViewModel viewModel);

    void showFavoriteAddError();

    void showFavoriteAddCorrect();

    void showFavErrorFavGuest();

    void showFavoriteRemoveError();

    void showFavoriteRemoveCorrect();


  }

  interface Presenter {
    void injectView(WeakReference<View> view);
    void injectModel(Model model);

    void fetchProductDetailData();

    void onCreateCalled();

    void onRecreateCalled();

      void onPauseCalled();

    void onFavoriteButtonClicked();

    void favNotEnableClicked();

  }

  interface Model {

    void onUpdatedDataFromRecreatedScreen(
            ProductItem product, boolean isFavorite);

    void verifyFavorite(String emailUser, String nameTool, RepositoryContract.VerifyFavoriteCallback callback);

    void addFavorite(String emailUser, String nameTool, RepositoryContract.AddFavoritesCallback callback);

    void removeFavorite(String emailUser, String nameTool, RepositoryContract.RemoveFavoritesCallback callback);
  }


}