package es.ulpgc.eite.da.advmasterdetail.favorites;

import androidx.fragment.app.FragmentActivity;

import java.lang.ref.WeakReference;

import es.ulpgc.eite.da.advmasterdetail.app.CatalogMediator;
import es.ulpgc.eite.da.advmasterdetail.data.CatalogRepository;
import es.ulpgc.eite.da.advmasterdetail.data.RepositoryContract;
import es.ulpgc.eite.da.advmasterdetail.products.ProductListModel;

public class FavoritesScreen {

    public static void configure(FavoritesContract.View view) {

        WeakReference<FragmentActivity> context =
                new WeakReference<>((FragmentActivity) view);


        CatalogMediator mediator = CatalogMediator.getInstance();
        FavoritesContract.Presenter presenter = new FavoritesPresenter(mediator);

       RepositoryContract repository = CatalogRepository.getInstance(context.get());
       FavoritesModel model = new FavoritesModel(repository);

        presenter.injectModel(model);
        presenter.injectView(new WeakReference<>(view));
        view.injectPresenter(presenter);

    }
}