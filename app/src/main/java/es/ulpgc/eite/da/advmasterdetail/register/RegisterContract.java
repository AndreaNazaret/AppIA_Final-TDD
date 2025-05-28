package es.ulpgc.eite.da.advmasterdetail.register;

import java.lang.ref.WeakReference;

import es.ulpgc.eite.da.advmasterdetail.data.RepositoryContract;

public interface RegisterContract {

    interface View {
        void injectPresenter(Presenter presenter);

        void onRefreshViewWithUpdatedData(RegisterViewModel viewModel);

        void navigateToNextScreen();

        void navigateToPreviousScreen();

        String getNameInput();

        String getLastNameInput();

        String getEmailInput();

        String getPasswordInput();

        void showRegisterErrorExist();

        void showRegisterAddError();

        void showRegisterAddCorrect();
    }

    interface Presenter {
        void injectView(WeakReference<View> view);

        void injectModel(Model model);

        void onResumeCalled();

        void onCreateCalled();

        void onRecreateCalled();

        void onBackButtonPressed();

        void onPauseCalled();

        void onDestroyCalled();

        void onRegisterButtonClicked();
    }

    interface Model {

        public void verifyUser(String email, String password, RepositoryContract.VerifyUserCallback callback);

        public void addUser(String name, String apellido, String email, String password, RepositoryContract.AddUserCallback callback);


        /*
        String getStoredData();


        String getSavedData();

        String getCurrentData();

        void setCurrentData(String data);

        void onUpdatedDataFromRecreatedScreen(String data);

        void onUpdatedDataFromNextScreen(String data);

        void onUpdatedDataFromPreviousScreen(String data);


        */
    }

}
