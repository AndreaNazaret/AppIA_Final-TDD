package es.ulpgc.eite.da.advmasterdetail.categories;

import java.lang.ref.WeakReference;

import es.ulpgc.eite.da.advmasterdetail.data.CategoryItem;
import es.ulpgc.eite.da.advmasterdetail.data.RepositoryContract;


interface CategoryListContract {

  interface View {
    void injectPresenter(Presenter presenter);

    void displayCategoryListData(CategoryListViewModel viewModel);

    void navigateToProductListScreen();
    void navigateToFavoriteScreen(String emailUser);
  }

  interface Presenter {
    void injectView(WeakReference<View> view);
    void injectModel(Model model);

    void fetchCategoryListData();
    void selectedCategoryData(CategoryItem item);

    void onCreateCalled();

    void onRecreateCalled();

      void onPauseCalled();

    void onFavButtonClicked (String emailUser);
  }

  interface Model {
    void fetchCategoryListData(
        RepositoryContract.GetCategoryListCallback callback);
  }

}