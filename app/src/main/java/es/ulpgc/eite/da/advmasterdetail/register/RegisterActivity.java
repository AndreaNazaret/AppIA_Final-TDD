package es.ulpgc.eite.da.advmasterdetail.register;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.widget.Toast;

import es.ulpgc.eite.da.advmasterdetail.R;

public class RegisterActivity
        extends AppCompatActivity implements RegisterContract.View {

    public static String TAG = "Adv Master-Detail.RegisterActivity";

    private RegisterContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Cambiar título de la barra
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        Log.e(TAG, "Register onCreate INICIADO");

        // do the setup
        RegisterScreen.configure(this);

        findViewById(R.id.buttonRegister).setOnClickListener(view -> {
            String name = getNameInput();
            String apellido = getLastNameInput();
            String email = getEmailInput();
            String password = getPasswordInput();
            Log.d(TAG, "Datos introducidos en Register: nombre=" + name + ", apellidos=" + apellido + ", email= " + email + ", pass=" + password);
            presenter.onRegisterButtonClicked();
        });
        // init or update the state
        if (savedInstanceState == null) {
            presenter.onCreateCalled();

        } else {
            presenter.onRecreateCalled();
        }
    }


    //Obtener campos de texto
    public String getNameInput() {
        TextView emailText = findViewById(R.id.editTextName);
        return emailText.getText().toString().trim();
    }
    public String getLastNameInput() {
        TextView emailText = findViewById(R.id.editTextLastName);
        return emailText.getText().toString().trim();
    }
    public String getEmailInput() {
        TextView emailText = findViewById(R.id.editTextEmail);
        return emailText.getText().toString().trim();
    }

    public String getPasswordInput() {
        TextView passwordText = findViewById(R.id.editTextPassword);
        return passwordText.getText().toString();
    }

    //Mensaje de error: Usuario ya existente

    @Override
    public void showRegisterErrorExist() {
        String message = getString(R.string.register_error_exist_message);
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void showRegisterAddError() {
        String message = getString(R.string.register_error_add_message);
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void showRegisterAddCorrect() {
        String message = getString(R.string.register_correct_add_message);
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
    public void onRefreshViewWithUpdatedData(RegisterViewModel viewModel) {
        //Log.e(TAG, "onRefreshViewWithUpdatedData()");

        // deal with the data

    }


    @Override
    public void navigateToNextScreen() {
        // Log.e(TAG, "navigateToNextScreen()");

        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    @Override
    public void navigateToPreviousScreen() {
        // Log.e(TAG, "navigateToPreviousScreen()");

        finish();
    }

    @Override
    public void injectPresenter(RegisterContract.Presenter presenter) {
        this.presenter = presenter;
    }
}


