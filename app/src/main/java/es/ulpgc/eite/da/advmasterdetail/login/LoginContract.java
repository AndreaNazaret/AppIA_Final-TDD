package es.ulpgc.eite.da.advmasterdetail.login;

import android.content.Context;

import java.lang.ref.WeakReference;

import es.ulpgc.eite.da.advmasterdetail.data.RepositoryContract;

public interface LoginContract {

    interface View {
        void injectPresenter(Presenter presenter);

        void onRefreshViewWithUpdatedData(LoginViewModel viewModel);

        void navigateToCategoryScreen(String emailUser);

        void navigateToRegisterScreen();

        String getEmailInput();

        String getPasswordInput();

        void showLoginError();

        void navigateToPreviousScreen();

        Context getContext();

    }

    interface Presenter {
        void injectView(WeakReference<View> view);

        void injectModel(Model model);

        void onResumeCalled();

        void loadInitialData();

        void onCreateCalled();

        void onRecreateCalled();

        void onBackButtonPressed();

        void onPauseCalled();

        void onDestroyCalled();

        void onGuestButtonClicked();

        void onLoginButtonClicked();

        void onRegisterButtonClicked();






    }

    interface Model {

        public void verifyUser(String email, String password, RepositoryContract.VerifyUserCallback callback);
        void loadCatalogData(RepositoryContract.FetchCatalogDataCallback callback);


    }
}
