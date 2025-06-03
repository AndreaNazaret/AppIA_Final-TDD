package es.ulpgc.eite.da.advmasterdetail.favorites;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;

import es.ulpgc.eite.da.advmasterdetail.R;
import es.ulpgc.eite.da.advmasterdetail.app.CatalogMediator;
import es.ulpgc.eite.da.advmasterdetail.data.FavoriteItem;
import es.ulpgc.eite.da.advmasterdetail.data.ProductItem;
import es.ulpgc.eite.da.advmasterdetail.product.ProductDetailActivity;
import es.ulpgc.eite.da.advmasterdetail.products.ProductListAdapter;
import es.ulpgc.eite.da.advmasterdetail.products.ProductListViewModel;

public class FavoritesActivity
        extends AppCompatActivity implements FavoritesContract.View {

    public static String TAG = "Adv Master-Detail.FavoritesActivity";

    private FavoritesContract.Presenter presenter;

    private FavoritesAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_list);
        // Cambiar título de la barra
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        Log.e(TAG, "onCreate() Favorites INICIADO");


        // do the setup
        FavoritesScreen.configure(this);

        initFavoritesListContainer();

        // init or update the state
        if (savedInstanceState == null) {
            presenter.onCreateCalled();

        } else {
            presenter.onRecreateCalled();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Log.e(TAG, "onResume()");

        // load the data

        presenter.fetchFavoritesData();
    }



    @Override
    protected void onPause() {
        super.onPause();

        // Log.e(TAG, "onPause()");

        presenter.onPauseCalled();
    }


    private void initFavoritesListContainer() {

        listAdapter = new FavoritesAdapter(view -> {

            ProductItem item = (ProductItem) view.getTag();
            presenter.selectedFavoriteData(item);
        });

        RecyclerView recyclerView = findViewById(R.id.product_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(listAdapter);

        presenter.fetchFavoritesData();
    }

    @Override
    public void navigateToProductDetailScreen() {
        Log.e(TAG, "Navegando a la pantalla ProductDetail desde Favorites");
        Intent intent = new Intent(this, ProductDetailActivity.class);
        //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


    public void displayFavoriteData(final FavoritesViewModel viewModel) {
        Log.e(TAG, "displayFavoriteData() llamado. Productos recibidos: " +
                (viewModel.productDetailsFavorites != null ? viewModel.productDetailsFavorites.size() : 0));

        runOnUiThread(() -> {
            // deal with the data
            listAdapter.setItems(viewModel.productDetailsFavorites);
        });

    }



    @Override
    public void injectPresenter(FavoritesContract.Presenter presenter) {
        this.presenter = presenter;
    }
}


