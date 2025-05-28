package es.ulpgc.eite.da.advmasterdetail.register;

import androidx.fragment.app.FragmentActivity;

import java.lang.ref.WeakReference;

import es.ulpgc.eite.da.advmasterdetail.app.CatalogMediator;
import es.ulpgc.eite.da.advmasterdetail.data.CatalogRepository;
import es.ulpgc.eite.da.advmasterdetail.data.RepositoryContract;

public class RegisterScreen {

    public static void configure(RegisterContract.View view) {

        WeakReference<FragmentActivity> context =
                new WeakReference<>((FragmentActivity) view);


        CatalogMediator mediator = CatalogMediator.getInstance();
        RegisterContract.Presenter presenter = new RegisterPresenter(mediator);

        RepositoryContract repositry = CatalogRepository.getInstance(context.get());
        RegisterContract.Model model = new RegisterModel(repositry);

        presenter.injectModel(model);
        presenter.injectView(new WeakReference<>(view));
        view.injectPresenter(presenter);

    }
}