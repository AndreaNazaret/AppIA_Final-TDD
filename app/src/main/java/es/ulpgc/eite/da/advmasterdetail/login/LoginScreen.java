package es.ulpgc.eite.da.advmasterdetail.login;

import androidx.fragment.app.FragmentActivity;

import java.lang.ref.WeakReference;

import es.ulpgc.eite.da.advmasterdetail.app.CatalogMediator;

public class LoginScreen {

    public static void configure(LoginContract.View view) {

        WeakReference<FragmentActivity> context =
                new WeakReference<>((FragmentActivity) view);


        CatalogMediator mediator = CatalogMediator.getInstance();
        LoginContract.Presenter presenter = new LoginPresenter(mediator);


        LoginContract.Model model = new LoginModel();

        presenter.injectModel(model);
        presenter.injectView(new WeakReference<>(view));
        view.injectPresenter(presenter);

    }
}