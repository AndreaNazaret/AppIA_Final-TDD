package es.ulpgc.eite.da.advmasterdetail.product;

import androidx.fragment.app.FragmentActivity;

import java.lang.ref.WeakReference;

import es.ulpgc.eite.da.advmasterdetail.app.CatalogMediator;
import es.ulpgc.eite.da.advmasterdetail.data.CatalogRepository;
import es.ulpgc.eite.da.advmasterdetail.data.RepositoryContract;


public class ProductDetailScreen {

  public static void configure(ProductDetailContract.View view) {

    WeakReference<FragmentActivity> context =
        new WeakReference<>((FragmentActivity) view);

    CatalogMediator mediator = CatalogMediator.getInstance();
    ProductDetailContract.Presenter presenter=new ProductDetailPresenter(mediator);

    RepositoryContract repository = CatalogRepository.getInstance(context.get());
    ProductDetailModel model = new ProductDetailModel(repository);

    presenter.injectModel(model);
    presenter.injectView(new WeakReference<>(view));
    view.injectPresenter(presenter);

  }

}
