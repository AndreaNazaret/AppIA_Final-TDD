package es.ulpgc.eite.da.advmasterdetail.product;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import es.ulpgc.eite.da.advmasterdetail.R;
import es.ulpgc.eite.da.advmasterdetail.app.CatalogMediator;
import es.ulpgc.eite.da.advmasterdetail.data.ProductItem;
import es.ulpgc.eite.da.advmasterdetail.products.ProductListActivity;
import es.ulpgc.eite.da.advmasterdetail.products.ProductListState;


public class ProductDetailActivity
    extends AppCompatActivity implements ProductDetailContract.View {

  public static String TAG = "AdvMasterDetail.ProductDetailActivity";

  ProductDetailContract.Presenter presenter;

  private TextView productNameTextView, productDescrTextView;

  private ImageButton favoriteButton;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_product_detail);

    if (getSupportActionBar() != null) {
      getSupportActionBar().hide();
    }
//Cambiamos los iconos de la barra de notificaciones ya que pusimos la barra blanca en themes
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
      getWindow().setStatusBarColor(Color.WHITE);
      getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }

    Log.e(TAG, "Favorites onCreate INICIADO");

    ProductDetailScreen.configure(this);

    favoriteButton  =findViewById(R.id.favorite_button);
    productNameTextView=findViewById(R.id.product_name);
    productDescrTextView=findViewById(R.id.product_detail);


    if (presenter != null) {
      ProductListState state = CatalogMediator.getInstance().getProductListState();
      if(state != null && state.isGuest){
        favoriteButton.setAlpha(0.5f);
        favoriteButton.setOnClickListener(view -> presenter.favNotEnableClicked());
      }else{
        favoriteButton.setOnClickListener(view -> presenter.onFavoriteButtonClicked());
      }
    }


    // do some work
    if(savedInstanceState == null) {
      presenter.onCreateCalled();

    }else{
      presenter.onRecreateCalled();
    }
  }

  @Override
  protected void onResume() {
    super.onResume();

    // do some work
    presenter.fetchProductDetailData();
  }

  @Override
  protected void onPause() {
    super.onPause();

    presenter.onPauseCalled();
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
  }

  @Override
  public void displayProductDetailData(ProductDetailViewModel viewModel) {
    Log.e(TAG, "displayProductDetailData");

    // deal with the data
    ProductItem product = viewModel.product;
    productNameTextView.setText(product.getName());
    productDescrTextView.setText(product.getDescription());

    if (product != null) {


      ((TextView) findViewById(R.id.product_name)).setText(product.name);
      ((TextView) findViewById(R.id.product_developer)).setText(product.developer);
      ((TextView) findViewById(R.id.product_detail)).setText(product.details);

      ImageView imageView = findViewById(R.id.product_image);
      int resId = getResources().getIdentifier(product.imageName, "drawable", getPackageName());
      imageView.setImageResource(resId != 0 ? resId : R.drawable.default_category);

    }
    if (viewModel.isFavorite) {
      favoriteButton.setImageResource(R.drawable.ic_red_heart);
    } else {
      favoriteButton.setImageResource(R.drawable.ic_black_heart);
    }

  }

  private void loadImageFromURL(ImageView imageView, String imageUrl){
    RequestManager reqManager = Glide.with(imageView.getContext());
    RequestBuilder reqBuilder = reqManager.load(imageUrl);
    RequestOptions reqOptions = new RequestOptions();
    reqOptions.diskCacheStrategy(DiskCacheStrategy.ALL);
    reqBuilder.apply(reqOptions);
    reqBuilder.into(imageView);
  }

  public void showFavoriteAddError() {
    String message = getString(R.string.favorite_error_add_message);
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
  }

  public void showFavoriteAddCorrect() {
    String message = getString(R.string.favorite_correct_add_message);
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
  }

  public void showFavoriteRemoveError(){
    String message = getString(R.string.favorite_error_remove_message);
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
  }

  public void showFavoriteRemoveCorrect(){
    String message = getString(R.string.favorite_correct_remove_message);
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
  }

  @Override
  public void showFavErrorFavGuest(){
    String message = getString(R.string.fav_error_message);
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
  }

  @Override
  public void injectPresenter(ProductDetailContract.Presenter presenter) {
    this.presenter = presenter;
  }
}
