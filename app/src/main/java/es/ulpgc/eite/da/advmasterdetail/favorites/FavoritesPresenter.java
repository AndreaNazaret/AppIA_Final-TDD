package es.ulpgc.eite.da.advmasterdetail.favorites;

import java.lang.ref.WeakReference;
import java.util.List;

import android.util.Log;

import es.ulpgc.eite.da.advmasterdetail.app.CatalogMediator;
import es.ulpgc.eite.da.advmasterdetail.data.CategoryItem;
import es.ulpgc.eite.da.advmasterdetail.data.FavoriteItem;
import es.ulpgc.eite.da.advmasterdetail.data.ProductItem;
import es.ulpgc.eite.da.advmasterdetail.data.RepositoryContract;
import es.ulpgc.eite.da.advmasterdetail.data.UsersItem;

public class FavoritesPresenter implements FavoritesContract.Presenter {

    public static String TAG = "Adv Master-Detail.FavoritesPresenter";

    private WeakReference<FavoritesContract.View> view;
    private CatalogMediator mediator;
    private FavoritesContract.Model model;
    private FavoritesState state;

    public FavoritesPresenter(CatalogMediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public void onCreateCalled() {
        Log.e(TAG, "onCreateCalled()");

        // call the mediator initialize the state
        state = new FavoritesState();


    }

    @Override
    public void onRecreateCalled() {
        Log.e(TAG, "onRecreateCalled()");

        // call the mediator to initialize the state
        state = mediator.getFavoritesState();


    }


    @Override
    public void onPauseCalled() {
        Log.e(TAG, "onPauseCalled()");

        // save the state
        mediator.setFavoritesState(state);
    }

    @Override
    public void fetchFavoritesData(String emailUser) {
        Log.e(TAG, "fetchFavoritesData en el PRESENTER INICIADO para el usuario " +emailUser);

        model.fetchFavoritesData(emailUser, new RepositoryContract.GetFavoritesCallback() {
            @Override
            public void setFavorites(List<ProductItem> favorites) {
                Log.e(TAG, "setFavorites() en el callback del presenter, tamaño recibido: " + favorites.size());
                for (ProductItem p : favorites) {
                    Log.e(TAG, "→ Producto: " + p.name);
                }
                state.productDetailsFavorites = favorites;
                view.get().displayFavoriteData(state);
            }
        });
    }


    @Override
    public void selectedFavoriteData(ProductItem item){


       // view.get().navigateToProductDetailScreen();
    }



    @Override
    public void injectView(WeakReference<FavoritesContract.View> view) {
        this.view = view;
    }

    @Override
    public void injectModel(FavoritesContract.Model model) {
        this.model = model;
    }

}



/*
    @Override
    public void onResumeCalled() {
        Log.e(TAG, "onResumeCalled()");


        // use passed state if is necessary
        SavedNextFavoritesState savedState = getStateFromNextScreen();
        if (savedState != null) {

            // update the model if is necessary
            model.onUpdatedDataFromNextScreen(savedState.data);

        }

        // call the model and initialize the state
        state.data = model.getCurrentData();

        // update the view
        view.get().onRefreshViewWithUpdatedData(state);

    }

     @Override
    public void onBackButtonPressed() {
        Log.e(TAG, "onBackButtonPressed()");

    }

    @Override
    public void onDestroyCalled() {
        Log.e(TAG, "onDestroyCalled()");

        // reset the state if is necessary
        //resetScreenState();
    }
    */