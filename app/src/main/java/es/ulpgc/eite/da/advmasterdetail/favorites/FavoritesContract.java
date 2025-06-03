package es.ulpgc.eite.da.advmasterdetail.favorites;

import java.lang.ref.WeakReference;

import es.ulpgc.eite.da.advmasterdetail.data.ProductItem;
import es.ulpgc.eite.da.advmasterdetail.data.RepositoryContract;

public interface FavoritesContract {

    interface View {
      void injectPresenter(Presenter presenter);

      void displayFavoriteData(FavoritesViewModel viewModel);

      void navigateToProductDetailScreen();
    }

    interface Presenter {
        void injectView(WeakReference<View> view);

        void injectModel(Model model);

        void fetchFavoritesData();

        void selectedFavoriteData(ProductItem item);

        void onCreateCalled();

        void onRecreateCalled();

        void onPauseCalled();

    }

    interface Model {
      void fetchFavoritesData(String emailUser, RepositoryContract.GetFavoritesCallback callback);
    }

}
