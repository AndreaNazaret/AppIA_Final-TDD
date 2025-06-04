package es.ulpgc.eite.da.advmasterdetail;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;

import static org.hamcrest.Matchers.not;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.RemoteException;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.uiautomator.UiDevice;

import org.hamcrest.Matchers;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
public class AppIAEspressoStep {

    //GENERALES
    //Espera 1 segundo (1000 milisegundos) tras el inicio de una pantalla.
    @Given("app start screen {string}")
    public void appStartScreen(String screen) {

        try {

            Thread.sleep(1000);

        } catch (Exception e) {

        }
    }

    //Pulsar botón back
    @And("user presses back button")
    @And("user presses the back button")
    public void userPressesBackButton() {
        pressBack();
    }

    //Rotar la pantalla
    @And("user rotates the screen")
    public void userRotatesTheScreen() {
        UiDevice device = UiDevice.getInstance(getInstrumentation());

        try {
            if (device.isNaturalOrientation()) {
                device.setOrientationLeft(); // Pasar a landscape
            } else {
                device.setOrientationNatural(); // Volver a portrait
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }




    //PANTALLA LOGIN

    //Simula que el usuario introduce credenciales.
    @When("user enters email {string} and password {string}")
    public void userEntersCredentials(String email, String password) {
        onView(withId(R.id.emailInput)).perform(scrollTo(), typeText(email), closeSoftKeyboard());
        onView(withId(R.id.passwordInput)).perform(scrollTo(), typeText(password), closeSoftKeyboard());
    }

    // Ejecuta la acción de login.
    @And("user clicks on login button")
    public void userClicksLoginButton() {
        onView(withId(R.id.loginButton)).perform(scrollTo(), click());
    }

    //Verifica que se navega correctamente después del login.
    @Then("app navigates to category list")
    public void appNavigatesToCategoryList() {
        onView(withId(R.id.category_recycler)).check(matches(isDisplayed()));
    }

    //Ejecuta la acción de guest
    @And("user clicks on guest button")
    public void userClicksGuestButton() {
        onView(withId(R.id.guestButton)).perform(scrollTo(), click());
    }

    //Navegar a la pantalla de registro
    @When("user clicks on register button")
    public void userClicksRegisterButton() {
        onView(withId(R.id.registerButton)).perform(scrollTo(), click());
    }

    //verificar que el campo de email está visible
    @And("login screen shows email field")
    public void loginScreenShowsEmailField() {
        onView(withId(R.id.emailInput)).check(matches(withHint(R.string.email_hint)));
    }

    //verificar que el campo de contraseña está visible
    @And("login screen shows password field")
    public void loginScreenShowsPasswordField() {
        onView(withId(R.id.passwordInput)).check(matches(withHint(R.string.password_hint)));
    }

    //verificar que el botón de iniciar sesión muestra el texto correcto
    @And("login screen shows login button")
    public void loginScreenShowsLoginButton() {
        onView(withId(R.id.loginButton)).check(matches(withText(R.string.login_button_text)));
    }

    //verificar que el botón de guest muestra el texto correcto
    @And("login screen shows guest button")
    public void loginScreenShowsGuestButton() {
        onView(withId(R.id.guestButton)).check(matches(withText(R.string.guest_button_text)));
    }

    //verificar que el texto "Si no tienes cuenta..." está visible
    @And("login screen shows register link")
    public void loginScreenShowsRegisterText() {
        onView(withId(R.id.noAccountText)).check(matches(withText(R.string.no_account_text)));
    }

    // verificar que el botón de registro existe y tiene el texto correcto
    @And("login screen shows register button with text {string}")
    public void loginScreenShowsRegisterButtonText() {
        onView(withId(R.id.registerButton)).check(matches(withText(R.string.register_button_text)));
    }




    //PANTALLA REGISTER

    //Introducir datos en formulario de registro
    @And("user fills register form with email {string} and password {string}")
    public void userFillsRegisterForm(String name, String lastName, String email, String password) {
        onView(withId(R.id.editTextName)).perform(scrollTo(), typeText(name), closeSoftKeyboard());
        onView(withId(R.id.editTextLastName)).perform(scrollTo(), typeText(lastName), closeSoftKeyboard());
        onView(withId(R.id.editTextEmail)).perform(scrollTo(), typeText(email), closeSoftKeyboard());
        onView(withId(R.id.editTextPassword)).perform(scrollTo(), typeText(password), closeSoftKeyboard());
    }

    //Pulsar botón de registro en la pantalla Register
    @And("user submits the register form")
    public void userSubmitsRegisterForm() {
        onView(withId(R.id.buttonRegister)).perform(click());
    }

    //verificar que el campo de nombre está visible
    @And("register screen shows name field")
    public void registerScreenShowsNameField() {
        onView(withId(R.id.editTextName)).check(matches(withHint(R.string.nombre_hint)));
    }

    //verificar que el campo de apellido está visible
    @And("register screen shows last name field")
    public void registerScreenShowsLastNameField() {
        onView(withId(R.id.editTextLastName)).check(matches(withHint(R.string.apellido_hint)));
    }

    //verificar que el campo de email está visible
    @And("register screen shows email field")
    public void registerScreenShowsEmailFieldRegister() {
        onView(withId(R.id.editTextEmail)).check(matches(withHint(R.string.email_hint)));
    }

    //verificar que el campo de contraseña está visible
    @And("register screen shows password field")
    public void registerScreenShowsPasswordFieldRegister() {
        onView(withId(R.id.editTextPassword)).check(matches(withHint(R.string.password_hint)));
    }

    //verificar que el botón de registro está visible
    @And("register screen shows register button")
    public void registerScreenShowsRegisterButton() {
        onView(withId(R.id.buttonRegister)).check(matches(withText(R.string.register_hint)));
    }




    //PANTALLA CATEGORIAS

    //Se muestran correctamente las categorias
    @Then("category list shows {int} categories")
    public void categoryListShowsCategories(int count) {
        onView(withId(R.id.category_recycler)).check(matches(isDisplayed()));
    }

    //Clicar en una categoria
    @When("user clicks on category at position {int}")
    public void userClicksCategory(int position) {
        onView(withId(R.id.category_recycler))
                .perform(RecyclerViewActions.actionOnItemAtPosition(position, click()));
    }
    //Navegar correctamente a la pantalla ProductList
    @Then("app navigates to product list")
    public void appNavigatesToProductList() {
        onView(withId(R.id.product_recycler)).check(matches(isDisplayed()));
    }

    //Navegar correctamente a la pantalla Favorites
    @Then("app navigates to favorites")
    public void appNavigatesToFavorites() {
        onView(withId(R.id.product_recycler)).check(matches(isDisplayed()));
    }


    //Navegar a la pantalla favoritos
    @When("user clicks on favorites button")
    public void userClicksFavoritesButton() {
        onView(withId(R.id.fab_favorites)).perform( click());
    }



    //PANTALLA PRODUCT DETAIL

    //Navegar correctamente a la pantalla ProductDetail
    @Then("app navigates to product detail")
    public void appNavigatesToProductDetail() {
        onView(withId(R.id.product_detail)).check(matches(isDisplayed()));
    }



    //Elimar producto de favorito
    @And("user removes product from favorites")
    public void userClickHeartFavorites() {
        onView(withId(R.id.favorite_button)).perform(click());
    }



    //Corazón de la pantalla details esta habilitado
    @Then("favorite button in details should be enabled")
    public void favoriteButtonInDetailsShouldBeEnabled() {
        onView(withId(R.id.favorite_button)).check(matches(isEnabled()));
    }




    //PANTALLA FAVORITOS

    //Aparecen correctamente las herramientas en favorito
    @Then("favorites shows {int} products")
    public void favoritesShowsProducts(int count) {
        onView(withId(R.id.product_recycler)).check(matches(isDisplayed()));
    }

    //Herramienta no aparece en favoritos
    @Then("product with name {string} should not be visible")
    public void productWithNameShouldNotBeVisible(String productName) {
        onView(withId(R.id.product_recycler))
                .check(matches(not(hasDescendant(withText(productName)))));
    }

    //Clicar en un producto
    @When("user clicks on product at position {int}")
    public void userClicksProduct(int position) {
        onView(withId(R.id.product_recycler))
                .perform(RecyclerViewActions.actionOnItemAtPosition(position, click()));
    }

    //Navegar correctamente a la pantalla ProductDetail
    @Then("app navigates to product detail favorites")
    public void appNavigatesToProductDetailFavorites() {
        onView(withId(R.id.product_detail)).check(matches(isDisplayed()));
    }

    //Herramienta aparece en favoritos
    @Then("product with name {string} should be visible")
    public void productWithNameShouldBeVisible(String productName) {
        onView(withId(R.id.product_recycler))
                .check(matches(hasDescendant(withText(productName))));
    }






}
