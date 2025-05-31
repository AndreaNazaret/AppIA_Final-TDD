package es.ulpgc.eite.da.advmasterdetail.app;

import es.ulpgc.eite.da.advmasterdetail.categories.CategoryListState;
import es.ulpgc.eite.da.advmasterdetail.data.CategoryItem;
import es.ulpgc.eite.da.advmasterdetail.data.FavoriteItem;
import es.ulpgc.eite.da.advmasterdetail.data.ProductItem;
import es.ulpgc.eite.da.advmasterdetail.data.UsersItem;
import es.ulpgc.eite.da.advmasterdetail.favorites.FavoritesState;
import es.ulpgc.eite.da.advmasterdetail.login.LoginState;
import es.ulpgc.eite.da.advmasterdetail.product.ProductDetailState;
import es.ulpgc.eite.da.advmasterdetail.products.ProductListState;
import es.ulpgc.eite.da.advmasterdetail.register.RegisterState;

public class CatalogMediator {

//  private CategoryListState categoryListState = new CategoryListState();
//  private ProductListState productListState = new ProductListState();
//  private ProductDetailState productDetailState = new ProductDetailState();
//  private LoginState loginState = new LoginState();
//  private RegisterState registerState = new RegisterState();

  private LoginState loginState;
  private RegisterState registerState;
  private CategoryListState categoryListState;
  private ProductListState productListState;
  private ProductDetailState productDetailState;
  private FavoritesState favoritesState;

  private CategoryItem category;
  private ProductItem product;
  private UsersItem user;
  private FavoriteItem favorite;


  private static CatalogMediator INSTANCE;

  private CatalogMediator() {

  }

  public static void resetInstance() {
    INSTANCE = null;
  }


  public static CatalogMediator getInstance() {
    if (INSTANCE == null) {
      INSTANCE = new CatalogMediator();
    }

    return INSTANCE;
  }


  public CategoryListState getCategoryListState() {
    return categoryListState;
  }

  public ProductDetailState getProductDetailState() {
    return productDetailState;
  }

  public LoginState getLoginState() {
    return loginState;
  }

  public RegisterState getRegisterState() {
    return registerState;
  }

  public ProductListState getProductListState() {
    return productListState;
  }

  public FavoritesState getFavoritesState() {
    return favoritesState;
  }

  public ProductItem getProduct() {
    ProductItem item = product;
    //product = null;
    return item;
  }

  public UsersItem getUser() {
    UsersItem item = user;
    //user= null;
    return item;
  }


  public void setProduct(ProductItem item) {
    product = item;
  }

  public void setCategory(CategoryItem item) {
    category = item;
  }


  public CategoryItem getCategory() {
    CategoryItem item = category;
    //category = null;
    return item;
  }

  public void setCategoryListState(CategoryListState state) {
    categoryListState = state;
  }

  public void setLoginState(LoginState state) {
    loginState = state;
  }

  public void setRegisterState(RegisterState state) {
    registerState = state;
  }


  public void setProductListState(ProductListState state) {
    productListState = state;
  }

  public void setProductDetailState(ProductDetailState state) {
    productDetailState = state;
  }

  public void setFavoritesState(FavoritesState state) {
    favoritesState = state;
  }
}