package es.ulpgc.eite.da.advmasterdetail.register;

import java.lang.ref.WeakReference;

import android.util.Log;

import es.ulpgc.eite.da.advmasterdetail.app.CatalogMediator;

public class RegisterPresenter implements RegisterContract.Presenter {

    public static String TAG = "Adv Master-Detail.RegisterPresenter";

    private WeakReference<RegisterContract.View> view;
    private CatalogMediator mediator;
    private RegisterContract.Model model;
    private RegisterState state;

    public RegisterPresenter(CatalogMediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public void onCreateCalled() {
        Log.e(TAG, "onCreateCalled()");

        // call the mediator initialize the state
        state = new RegisterState();


    }

    @Override
    public void onRecreateCalled() {
        Log.e(TAG, "onRecreateCalled()");

        // call the mediator to initialize the state
        state = mediator.getRegisterState();


    }

    @Override
    public void onResumeCalled() {
        Log.e(TAG, "onResumeCalled()");


        view.get().onRefreshViewWithUpdatedData(state);

    }

    @Override
    public void onBackButtonPressed() {
        Log.e(TAG, "onBackButtonPressed()");



    }

    @Override
    public void onPauseCalled() {
        Log.e(TAG, "onPauseCalled()");

        // save the state
        mediator.setRegisterState(state);
    }

    @Override
    public void onDestroyCalled() {
        Log.e(TAG, "onDestroyCalled()");

        // reset the state if is necessary
        //resetScreenState();
    }

    /*BOTONES*/

    @Override
    public void onRegisterButtonClicked() {
        String name = view.get().getNameInput();
        String apellido = view.get().getLastNameInput();
        String email = view.get().getEmailInput();
        String password = view.get().getPasswordInput();
        Log.d(TAG, "Datos introducidos en Register(Presenter): nombre=" + name + ", apellidos=" + apellido + ", email= " + email + ", pass=" + password);

        model.verifyUser(email,password, success -> {
            if (success){
                new android.os.Handler(android.os.Looper.getMainLooper()).post(() ->
                        view.get().showRegisterErrorExist()
                );
            }else{
                model.addUser(name, apellido, email, password, success2 -> {
                    if(success2){
                        new android.os.Handler(android.os.Looper.getMainLooper()).post(() ->
                                view.get().showRegisterAddCorrect()
                        );
                    }else{
                        new android.os.Handler(android.os.Looper.getMainLooper()).post(() ->
                                view.get().showRegisterAddError()
                        );
                    }
                });
            }
        });

    }


  /*
  private void resetScreenState() {
    mediator.resetRegisterScreenState();
  }
  */
/*
    private SavedNextRegisterState getStateFromNextScreen() {
        return mediator.getNextRegisterScreenState();
    }

    private void passStateToNextScreen(NewNextRegisterState state) {
        mediator.setNextRegisterScreenState(state);
    }

    private void passStateToPreviousScreen(NewPreviousRegisterState state) {
        mediator.setPreviousRegisterScreenState(state);
    }

    private SavedPreviousRegisterState getStateFromPreviousScreen() {
        return mediator.getPreviousRegisterScreenState();
    }

 */

    @Override
    public void injectView(WeakReference<RegisterContract.View> view) {
        this.view = view;
    }

    @Override
    public void injectModel(RegisterContract.Model model) {
        this.model = model;
    }



}