package es.ulpgc.eite.da.advmasterdetail.categories;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import es.ulpgc.eite.da.advmasterdetail.R;
import es.ulpgc.eite.da.advmasterdetail.data.CategoryItem;
import es.ulpgc.eite.da.advmasterdetail.products.ProductListActivity;


public class CategoryListActivity
    extends AppCompatActivity implements CategoryListContract.View {

  public static String TAG = "AdvMasterDetail.CategoryListActivity";

  CategoryListContract.Presenter presenter;

  private CategoryListAdapter listAdapter;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_category_list);
    setTitle(R.string.title_category_list);

    Log.e(TAG, "CategoryListActivity onCreate INICIADO");

    // do the setup
    CategoryListScreen.configure(this);

    if (presenter == null) {
      Log.e(TAG, "ERROR: presenter no fue inyectado correctamente");
      finish(); // Detiene la actividad para evitar crash
      return;
    }


    // do some work
    initCategoryListContainer();

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
    presenter.fetchCategoryListData();
  }

  private void initCategoryListContainer() {

    listAdapter = new CategoryListAdapter(view -> {
      CategoryItem item = (CategoryItem) view.getTag(); //Se le pasan los datos
      presenter.selectedCategoryData(item);
    });

    RecyclerView recyclerView = findViewById(R.id.category_recycler);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    recyclerView.setAdapter(listAdapter);
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
  public void displayCategoryListData(final CategoryListViewModel viewModel) {
    Log.e(TAG, "displayCategoryListData()");

    //Para actualizar el recyclerView hay q pasarle los datos al Adapter
    runOnUiThread(() -> {

      // deal with the data
      listAdapter.setItems(viewModel.categories);
    });

  }

  @Override
  public void navigateToProductListScreen() {
    Intent intent = new Intent(this, ProductListActivity.class);
    //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    startActivity(intent);
  }

  @Override
  public void injectPresenter(CategoryListContract.Presenter presenter) {
    this.presenter = presenter;
  }

}
