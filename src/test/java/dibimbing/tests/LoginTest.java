package dibimbing.tests;

import dibimbing.core.BaseTest;
import dibimbing.core.DriverManager;
import dibimbing.pages.GlobalPage;
import dibimbing.pages.LoginPage;
import dibimbing.pages.ProductPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {
  @Test
  public void successLogin() {
    GlobalPage globalPage = new GlobalPage(DriverManager.getDriver());
    LoginPage loginPage = new LoginPage(DriverManager.getDriver());
    ProductPage productPage = new ProductPage(DriverManager.getDriver());

    // Go to Login page
    globalPage.clickHamburgerMenu();
    globalPage.clickLoginMenu();

    // Input Credentials and login
    loginPage.login("bod@example.com", "10203040");

    // Check Login success
    Assert.assertTrue(productPage.isProductTitlePage());
  }
}
