package es.ulpgc.eite.da.advmasterdetail.data;

import java.util.List;

public interface RepositoryContract {

  //Interfaz comprobar usuario

  interface VerifyUserCallback {
    void onVerificationResult(boolean success);
  }
  void verifyUser(String email, String password, VerifyUserCallback callback);


  //Interfaz agregar usuario

  interface AddUserCallback {
    void onVerificationResultAdd(boolean success);
  }
  void addUser(String name, String apellido, String email, String password, AddUserCallback callback);


  //Interfaz verificar si el favorito ya existe
  interface VerifyFavoriteCallback {
    void onVerificationResultFavorite(boolean success);
  }
  void verifyFavorite(String emailUser, String nameTool, VerifyFavoriteCallback callback);


  //Interfaz agregar un favorito
  interface AddFavoritesCallback {
    void onVerificationResultAdd(boolean success);
  }
  void addFavorite(String emailUser, String nameTool, AddFavoritesCallback callback);


  //Interfaz borrar un favorito
  interface RemoveFavoritesCallback {
    void onVerificationResultRemove(boolean success);
  }
  void removeFavorite(String emailUser, String nameTool, RemoveFavoritesCallback callback);




  //Obtener los favoritos de un usuario
  interface GetFavoritesCallback {
    void setFavorites(List<ProductItem> favorites);
  }
  void getFavoritesListData (String emailUser,RepositoryContract.GetFavoritesCallback callback);


  interface FetchCatalogDataCallback {
    void onCatalogDataFetched(boolean error);
  }

  interface GetProductListCallback {
    void setProductList(List<ProductItem> products);
  }

  interface GetProductCallback {
    void setProduct(ProductItem product);
  }

  interface GetCategoryListCallback {
    void setCategoryList(List<CategoryItem> categories);
  }

  interface GetCategoryCallback {
    void setCategory(CategoryItem category);
  }

  interface DeleteCategoryCallback {
    void onCategoryDeleted();
  }

  interface UpdateCategoryCallback {
    void onCategoryUpdated();
  }

  interface DeleteProductCallback {
    void onProductDeleted();
  }

  interface UpdateProductCallback {
    void onProductUpdated();
  }


  void loadCatalog(
      boolean clearFirst, CatalogRepository.FetchCatalogDataCallback callback);

  void getProductList(
      CategoryItem category, CatalogRepository.GetProductListCallback callback);

  void getProductList(
      int categoryId, CatalogRepository.GetProductListCallback callback);

  void getProduct(int id, CatalogRepository.GetProductCallback callback);
  void getCategory(int id, CatalogRepository.GetCategoryCallback callback);
  void getCategoryList(CatalogRepository.GetCategoryListCallback callback);

  void deleteProduct(
      ProductItem product, CatalogRepository.DeleteProductCallback callback);

  void updateProduct(
      ProductItem product, CatalogRepository.UpdateProductCallback callback);

  void deleteCategory(
      CategoryItem category, CatalogRepository.DeleteCategoryCallback callback);

  void updateCategory(
      CategoryItem category, CatalogRepository.UpdateCategoryCallback callback);
}
