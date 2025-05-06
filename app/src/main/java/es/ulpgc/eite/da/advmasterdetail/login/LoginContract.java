package es.ulpgc.eite.da.advmasterdetail.login;

import java.lang.ref.WeakReference;

public interface LoginContract {

    interface View {
        void injectPresenter(Presenter presenter);

        void onRefreshViewWithUpdatedData(LoginViewModel viewModel);

        void navigateToNextScreen();

        void navigateToPreviousScreen();
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
    }

    interface Model {

    }

}
