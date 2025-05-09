package es.ulpgc.eite.da.advmasterdetail.login;

import androidx.fragment.app.FragmentActivity;

import java.lang.ref.WeakReference;

import es.ulpgc.eite.da.advmasterdetail.app.CatalogMediator;
import es.ulpgc.eite.da.advmasterdetail.data.CatalogRepository;
import es.ulpgc.eite.da.advmasterdetail.data.RepositoryContract;

public class LoginScreen {

    public static void configure(LoginContract.View view) {

        WeakReference<FragmentActivity> context =
                new WeakReference<>((FragmentActivity) view);


        CatalogMediator mediator = CatalogMediator.getInstance();
        LoginContract.Presenter presenter = new LoginPresenter(mediator);

        RepositoryContract repository = CatalogRepository.getInstance(context.get());
        LoginContract.Model model = new LoginModel(repository);

        presenter.injectModel(model);
        presenter.injectView(new WeakReference<>(view));
        view.injectPresenter(presenter);

    }
}