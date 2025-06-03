package es.ulpgc.eite.da.advmasterdetail.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.widget.Toast;

import es.ulpgc.eite.da.advmasterdetail.R;
import es.ulpgc.eite.da.advmasterdetail.categories.CategoryListActivity;
import es.ulpgc.eite.da.advmasterdetail.register.RegisterActivity;


public class LoginActivity
    extends AppCompatActivity implements LoginContract.View {

  public static String TAG = "Adv Master-Detail.LoginActivity";

  private LoginContract.Presenter presenter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Log.e(TAG, "UsersActivity onCreate INICIADO");

    setContentView(R.layout.activity_login);
    // Cambiar título de la barra
    if (getSupportActionBar() != null) {
      getSupportActionBar().hide();
    }


    LoginScreen.configure(this);

    findViewById(R.id.loginButton).setOnClickListener(view -> {
      String email = getEmailInput();
      String password = getPasswordInput();
      Log.d(TAG, "Datos introducidos: Email=" + email + ", Password=" + password);
      presenter.onLoginButtonClicked();
    });

    findViewById(R.id.guestButton).setOnClickListener(view ->presenter.onGuestButtonClicked());
    findViewById(R.id.registerButton).setOnClickListener(view ->presenter.onRegisterButtonClicked());

    // init or update the state
    if (savedInstanceState == null) {
      presenter.onCreateCalled();

    } else {
      presenter.onRecreateCalled();
    }
  }


  //Obtener campos de texto
  public String getEmailInput() {
    TextView emailText = findViewById(R.id.emailInput);
    return emailText.getText().toString().trim();
  }

  public String getPasswordInput() {
    TextView passwordText = findViewById(R.id.passwordInput);
    return passwordText.getText().toString();
  }

  @Override
  public void showLoginError() {
    String message = getString(R.string.login_error_message);
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
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
  public void navigateToCategoryScreen(String emailUser) {
    // Log.e(TAG, "navigateToNextScreen()");

    try {
      Intent intent = new Intent(this, CategoryListActivity.class);
      intent.putExtra("emailUser", emailUser);
      startActivity(intent);
      Log.e(TAG, "Intent lanzado correctamente hacia category");
    } catch (Exception e) {
      Log.e(TAG, "ERROR al lanzar CategoryListActivity: " + e.getMessage(), e);
    }
  }

  @Override
  public void navigateToRegisterScreen() {
    // Log.e(TAG, "navigateToNextScreen()");

    try {
      Intent intent = new Intent(this, RegisterActivity.class);
      startActivity(intent);
      Log.e(TAG, "Intent lanzado correctamente hacia register");
    } catch (Exception e) {
      Log.e(TAG, "ERROR al lanzar RegisterActivity: " + e.getMessage(), e);
    }
  }

  @Override
  public void navigateToPreviousScreen() {
    // Log.e(TAG, "navigateToPreviousScreen()");

    finish();
  }




  @Override
  public Context getContext() {
    return this;
  }



  @Override
  public void injectPresenter(LoginContract.Presenter presenter) {
    this.presenter = presenter;
  }
}


