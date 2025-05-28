package es.ulpgc.eite.da.advmasterdetail.login;

import java.lang.ref.WeakReference;

import android.util.Log;

import es.ulpgc.eite.da.advmasterdetail.app.CatalogMediator;
import es.ulpgc.eite.da.advmasterdetail.categories.CategoryListState;

public class LoginPresenter implements LoginContract.Presenter {

    public static String TAG = "Adv Master-Detail.LoginPresenter";

    private WeakReference<LoginContract.View> view;
    private CatalogMediator mediator;
    private LoginContract.Model model;
    private LoginState state;

    public LoginPresenter(CatalogMediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public void onCreateCalled() {
        Log.e(TAG, "onCreateCalled()");

        // call the mediator initialize the state
        state = new LoginState();

    }

    @Override
    public void onRecreateCalled() {
        Log.e(TAG, "onRecreateCalled()");

        // call the mediator to initialize the state
        state = mediator.getLoginState();
    }

    @Override
    public void onResumeCalled() {
        Log.e(TAG, "onResumeCalled()");

        view.get().onRefreshViewWithUpdatedData(state);

    }

    @Override
    public void loadInitialData() {
        Log.d(TAG, "Loading initial data from JSON...");
        model.loadCatalogData( error -> {
            if (!error) {
                Log.d(TAG, "Initial data loaded successfully.");
            } else {
                Log.e(TAG, "Error loading initial data.");
            }
        });
    }


    @Override
    public void onBackButtonPressed() {
        Log.e(TAG, "onBackButtonPressed()");

    }

    @Override
    public void onPauseCalled() {
        Log.e(TAG, "onPauseCalled()");

        // save the state
        mediator.setLoginState(state);
    }

    @Override
    public void onDestroyCalled() {
        Log.e(TAG, "onDestroyCalled()");

        // reset the state if is necessary
        //resetScreenState();
    }


    /*BOTONES*/

    @Override
    public void onGuestButtonClicked() {
        view.get().navigateToCategoryScreen();
    }

    @Override
    public void onLoginButtonClicked() {
        String email = view.get().getEmailInput();
        String password = view.get().getPasswordInput();

        Log.d(TAG, "Parámetros introducidos (Presenter): Email=" + email + ", Password=" + password);

        model.verifyUser(email, password, success -> {
            if (success) {
                view.get().navigateToCategoryScreen();
            } else {
                new android.os.Handler(android.os.Looper.getMainLooper()).post(() ->
                        view.get().showLoginError()
                );
            }
        });
    }

    @Override
    public void onRegisterButtonClicked() {
        view.get().navigateToRegisterScreen();
    }




    /*
  private void resetScreenState() {
    mediator.resetLoginScreenState();
  }
  */
/*
    private SavedNextLoginState getStateFromNextScreen() {
        return mediator.getNextLoginScreenState();
    }

    private void passStateToNextScreen(NewNextLoginState state) {
        mediator.setNextLoginScreenState(state);
    }

    private void passStateToPreviousScreen(NewPreviousLoginState state) {
        mediator.setPreviousLoginScreenState(state);
    }

    private SavedPreviousLoginState getStateFromPreviousScreen() {
        return mediator.getPreviousLoginScreenState();
    }
*/
    @Override
    public void injectView(WeakReference<LoginContract.View> view) {
        this.view = view;
    }

    @Override
    public void injectModel(LoginContract.Model model) {
        this.model = model;
    }

}