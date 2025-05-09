package es.ulpgc.eite.da.advmasterdetail.login;

import java.lang.ref.WeakReference;

import es.ulpgc.eite.da.advmasterdetail.data.RepositoryContract;

public interface LoginContract {

    interface View {
        void injectPresenter(Presenter presenter);

        void onRefreshViewWithUpdatedData(LoginViewModel viewModel);

        void navigateToCategoryScreen();

        void navigateToPreviousScreen();

        String getEmailInput();

        String getPasswordInput();

        void showLoginError();
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


    }

    interface Model {

        public void verifyUser(String email, String password, RepositoryContract.VerifyUserCallback callback);
        void loadCatalogData(RepositoryContract.FetchCatalogDataCallback callback);


    }
}
