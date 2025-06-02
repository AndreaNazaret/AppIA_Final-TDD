package es.ulpgc.eite.da.advmasterdetail.product;

import java.lang.ref.WeakReference;

import es.ulpgc.eite.da.advmasterdetail.data.ProductItem;

interface ProductDetailContract {

  interface View {
    void injectPresenter(Presenter presenter);

    void displayProductDetailData(ProductDetailViewModel viewModel);
  }

  interface Presenter {
    void injectView(WeakReference<View> view);
    void injectModel(Model model);

    void fetchProductDetailData();

    void onCreateCalled();

    void onRecreateCalled();

      void onPauseCalled();

    void onFavoriteButtonClicked();

  }

  interface Model {

    void onUpdatedDataFromRecreatedScreen(
            ProductItem product, boolean isFavorite);

  }

}