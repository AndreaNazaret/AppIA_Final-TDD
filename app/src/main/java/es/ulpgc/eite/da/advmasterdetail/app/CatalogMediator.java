package es.ulpgc.eite.da.advmasterdetail.app;

import static android.content.ContentValues.TAG;

import android.util.Log;

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

  //  private LoginState loginState = new LoginState();
//  private RegisterState registerState = new RegisterState();
//  private CategoryListState categoryListState = new CategoryListState();
//  private ProductListState productListState = new ProductListState();
//  private ProductDetailState productDetailState = new ProductDetailState();
//  private FavoritesState favoritesState = new FavoritesState();


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


  public LoginState getLoginState() {
    Log.d(TAG, "Se han recuperado del mediador el State de Login, email: " + loginState.emailUser);
    return loginState;
  }

  public RegisterState getRegisterState() {
    return registerState;
  }

  public CategoryListState getCategoryListState() {
    Log.d(TAG, "Se han recuperado del mediador el State de CategoryList, email: " + categoryListState.emailUser);
    return categoryListState;
  }

  public ProductListState getProductListState() {
    if (productListState != null && productListState.emailUser != null) {
      Log.d(TAG, "Se han recuperado del mediador el State de ProductList, email: " + productListState.emailUser);
    } else {
      Log.d(TAG, "productListState o emailUser es null");
    }
    return productListState;
  }


  public ProductDetailState getProductDetailState() {
    Log.d(TAG, "Se han recuperado del mediador el State de ProductDetail, email: " + productDetailState.emailUser);
    return productDetailState;
  }

  public FavoritesState getFavoritesState() {
    Log.d(TAG, "Se han recuperado del mediador el State de Favorites, email: " + favoritesState.emailUser);
    return favoritesState;
  }



  public ProductItem getProduct() {
    ProductItem item = product;
    //product = null;
    return item;
  }

  public void setProduct(ProductItem item) {product = item;}



  public UsersItem getUser() {
    UsersItem item = user;
    //user= null;
    return item;
  }
  public void setUser(UsersItem item) {user = item;}



  public FavoriteItem getFavorite() {
    FavoriteItem item = favorite;
    //favorite = null;
    return item;
  }
  public void setFavorite(FavoriteItem item) {favorite = item;}



  public CategoryItem getCategory() {
    CategoryItem item = category;
    //category = null;
    return item;
  }

  public void setCategory(CategoryItem item) {
    category = item;
  }




  public void setLoginState(LoginState state) {
    loginState = state;
    Log.d(TAG, "Se ha guardado en el mediador el State de Login, email: " + loginState.emailUser);
  }

  public void setRegisterState(RegisterState state) {
    registerState = state;
  }

  public void setCategoryListState(CategoryListState state) {
    categoryListState = state;
    Log.d(TAG, "Se ha guardado en el mediador el State de CategoryList, email: " + categoryListState.emailUser);
  }

  public void setProductListState(ProductListState state) {
    productListState = state;
    Log.d(TAG, "Se ha guardado en el mediador el State de ProductList, email: " + productListState.emailUser);
  }

  public void setProductDetailState(ProductDetailState state) {
    productDetailState = state;
    Log.d(TAG, "Se ha guardado en el mediador el State de ProductDetail, email: " + productDetailState.emailUser);
  }

  public void setFavoritesState(FavoritesState state) {
    favoritesState = state;
    Log.d(TAG, "Se ha guardado en el mediador el State de Favorite, email: " + favoritesState.emailUser);
  }
}