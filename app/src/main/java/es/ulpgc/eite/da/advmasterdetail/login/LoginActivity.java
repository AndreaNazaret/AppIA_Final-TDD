package es.ulpgc.eite.da.advmasterdetail.login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;

import es.ulpgc.eite.da.advmasterdetail.R;
import es.ulpgc.eite.da.advmasterdetail.categories.CategoryListActivity;

public class LoginActivity
    extends AppCompatActivity implements LoginContract.View {

  public static String TAG = "Adv Master-Detail.LoginActivity";

  private LoginContract.Presenter presenter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    // Cambiar título
    if (getSupportActionBar() != null) {
      getSupportActionBar().setTitle("Login");
    }

    setContentView(R.layout.activity_login);
    LoginScreen.configure(this);

    findViewById(R.id.guestButton).setOnClickListener(view ->
            presenter.onGuestButtonClicked()
    );

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
    presenter.onResumeCalled();
  }

  @SuppressWarnings("deprecation")
  @Override
  public void onBackPressed() {
    super.onBackPressed();

    // Log.e(TAG, "onBackPressed()");

    presenter.onBackButtonPressed();
  }

  @Override
  protected void onPause() {
    super.onPause();

    // Log.e(TAG, "onPause()");

    presenter.onPauseCalled();
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();

    // Log.e(TAG, "onDestroy()");

    presenter.onDestroyCalled();
  }

  @Override
  public void onRefreshViewWithUpdatedData(LoginViewModel viewModel) {
    //Log.e(TAG, "onRefreshViewWithUpdatedData()");

    // deal with the data

  }


  @Override
  public void navigateToCategoryScreen() {
    // Log.e(TAG, "navigateToNextScreen()");

    try {
      Intent intent = new Intent(this, CategoryListActivity.class);
      startActivity(intent);
      Log.e(TAG, "Intent lanzado correctamente");
    } catch (Exception e) {
      Log.e(TAG, "ERROR al lanzar CategoryListActivity: " + e.getMessage(), e);
    }
  }

  @Override
  public void navigateToPreviousScreen() {
    // Log.e(TAG, "navigateToPreviousScreen()");

    finish();
  }

  @Override
  public void injectPresenter(LoginContract.Presenter presenter) {
    this.presenter = presenter;
  }
}


