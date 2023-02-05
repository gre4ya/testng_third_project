package scripts;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.CarvanaBasePage;
import pages.CarvanaSearchCarsPage;
import test_data.TestData;
import utilities.Driver;
import utilities.Waiter;
import utilities.WindowHandler;

import java.util.Arrays;
import java.util.stream.IntStream;

public class CarvanaHomePageTest extends CarvanaBase{

    @BeforeMethod
    public void setPage(){
        carvanaBasePage = new CarvanaBasePage();
        carvanaSearchCarsPage = new CarvanaSearchCarsPage();
    }
    /**
     Test Case 1: Test name = Validate Carvana home page title and url
     Test priority = 1
     Given user is on "https://www.carvana.com/"
     Then validate title equals  "Carvana | Buy & Finance Used Cars Online | At Home Delivery"
     And validate url equals  "https://www.carvana.com/"
     */
    @Test(priority = 1, description = "Validate Carvana home page title and url")
    public void validateTitleAndURL(){
        Assert.assertEquals(driver.getTitle(), TestData.getHomePageTitle());
        Assert.assertEquals(driver.getCurrentUrl(), TestData.getHomePageURL());
    }

    /**
     Test Case 2: Test name = Validate the Carvana logo
     Test priority = 2
     Given user is on "https://www.carvana.com/"
     Then validate the logo of the “Carvana” is displayed
     */
    @Test(priority = 2, description = "Validate the Carvana logo")
    public void validateLogo(){
        Assert.assertTrue(carvanaBasePage.logo.isDisplayed());
    }

    /**
     Test Case 3: Test name = Validate the main navigation section items
     Test priority = 3
     Given user is on "https://www.carvana.com/"
     Then validate the navigation section items below are displayed
     |HOW IT WORKS      |
     |ABOUT CARVANA     |
     |SUPPORT & CONTACT |
     */
    @Test(priority = 3, description = "Validate the main navigation section items")
    public void validateMainSectionNavigationItems(){
        Assert.assertTrue(carvanaBasePage.howItWorksDropdown.isDisplayed());
        Assert.assertEquals(carvanaBasePage.howItWorksDropdown.getText(),
                "HOW IT WORKS");
        Assert.assertTrue(carvanaBasePage.aboutCarvanaDropdown.isDisplayed());
        Assert.assertEquals(carvanaBasePage.aboutCarvanaDropdown.getText(),
                "ABOUT CARVANA");
        Assert.assertTrue(carvanaBasePage.supportAndContactDropdown.isDisplayed());
        Assert.assertEquals(carvanaBasePage.supportAndContactDropdown.getText(),
                "SUPPORT & CONTACT");
    }

    /**
     Test Case 4: Test name = Validate the sign in error message
     Test priority = 4
     Given user is on "https://www.carvana.com/"
     When user clicks on “SIGN IN” button
     Then user should be navigated to “Sign in” modal
     When user enters email as johndoe@gmail.com
     And user clicks on "CONTINUE" button
     And user enters password as "abcd1234"
     And user clicks on "SIGN IN" button
     Then user should see error message as "Email address and/or password combination is incorrect.
     */
    @Test(priority = 4, description = "Validate the sign in error message")
    public void ValidateSignInErrorMessage(){
        carvanaBasePage.signInButton.click();
        carvanaBasePage.emailInput.sendKeys("johndoe@gmail.com");
        carvanaBasePage.continueAndSigninButton.click();
        carvanaBasePage.passwordInput.sendKeys("abcd1234");
        carvanaBasePage.continueAndSigninButton.click();
        Assert.assertTrue(carvanaBasePage.errorMessage.isDisplayed());
        Assert.assertEquals(carvanaBasePage.errorMessage.getText(),
                "Email address and/or password combination is incorrect.");
    }
    /**
     Test Case 5: Test name = Validate the search filter options and search button
     Test priority = 5
     Given user is on "https://www.carvana.com/"
     When user clicks on "SEARCH CARS" link
     Then user should be routed to "https://www.carvana.com/cars"
     And user should see filter options
     |PAYMENT & PRICE|
     |MAKE & MODEL   |
     |BODY TYPE      |
     |YEAR & MILEAGE |
     |FEATURES       |
     |MORE FILTERS   |
     */
    @Test(priority = 5, description = "Validate the search filter options and search button")
    public void validateFilterOptions(){
        carvanaBasePage.searchCarsButton.click();
        WindowHandler.switchToChildWindow();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.carvana.com/cars");
        String[] filterOptionsExpected = {
                "PAYMENT & PRICE",
                "MAKE & MODEL",
                "BODY TYPE",
                "YEAR & MILEAGE",
                "FEATURES",
                "MORE FILTERS"};
        IntStream.range(0, carvanaSearchCarsPage.filterOptionsHeaders.size()).forEach(i -> {

                Assert.assertTrue(carvanaSearchCarsPage.filterOptionsHeaders.get(i).isDisplayed());
                Assert.assertEquals(carvanaSearchCarsPage.filterOptionsHeaders.get(i).getText(),
                                filterOptionsExpected[i]);
        });
    }
    /**
     Test Case 6: Test name = Validate the search result tiles
     Test priority = 6
     Given user is on "https://www.carvana.com/"
     When user clicks on "SEARCH CARS" link
     Then user should be routed to "https://www.carvana.com/cars"
     When user enters "mercedes-benz" to the search input box
     And user clicks on "GO" button in the search input box
     Then user should see "mercedes-benz" in the url
     And validate each result tile
     VALIDATION OF EACH TILE INCLUDES BELOW
     Make sure each result tile is displayed with below information
     1. an image
     2. add to favorite button
     3. tile body
     ALSO VALIDATE EACH TILE BODY:
     Make sure each tile body has below information
     1. Inventory type - text should be displayed and should not be null or empty
     2. Year-Make-Model information - text should be displayed and should not be null or empty
     3. Trim-Mileage information - text should be displayed and should not be null or empty
     4. Price - Make sure that each price is more than zero
     5. Monthly Payment information - text should be displayed and should not be null or empty
     6. Down Payment information - text should be displayed and should not be null or empty
     7. Delivery chip must be displayed, and text is not null or empty
     */

    @Test(priority = 6, description = "Validate the search result tiles")
    public void validateSearchResultTiles() {
        Waiter.pause(1);
        carvanaBasePage.searchCarsButton.click();
        WindowHandler.switchToChildWindow();
        carvanaSearchCarsPage.searchInput.sendKeys("mercedes-benz");
        carvanaSearchCarsPage.goButton.click();
        Waiter.waitURLToContainFraction(5, "mercedes-benz");
        Assert.assertTrue(driver.getCurrentUrl().contains("mercedes-benz"));
        Actions action = new Actions(Driver.getDriver());
        SoftAssert softAssert = new SoftAssert();

        int j = 1;
        while (carvanaSearchCarsPage.nextButton.isEnabled()) {

            int pageNumber = j;

            IntStream.range(0, carvanaSearchCarsPage.resultTiles.size()).forEach(i -> {

                softAssert.assertTrue(carvanaSearchCarsPage.imageTile.get(i).isDisplayed(),
                        "Tile " + (i + 1) + " \'image\' on page " + pageNumber + " is not displayed");

                softAssert.assertTrue(carvanaSearchCarsPage.favButtonTile.get(i).isDisplayed(),
                        "Tile " + (i + 1) + " \'favorite button\' on page " + pageNumber + " is not displayed");
                softAssert.assertTrue(carvanaSearchCarsPage.favButtonTile.get(i).isEnabled(),
                        "Tile " + (i + 1) + " \'favorite button\' on page " + pageNumber + " is not enabled");

                softAssert.assertTrue(carvanaSearchCarsPage.inventoryTypeTile.get(i).isDisplayed(),
                        "Tile " + (i + 1) + " \'inventory type\' field on page " + pageNumber + " is not displayed");
                softAssert.assertFalse(carvanaSearchCarsPage.inventoryTypeTile.get(i).getText().isEmpty(),
                        "Tile " + (i + 1) + " \'inventory type\' field on page " + pageNumber + " is empty");

                softAssert.assertTrue(carvanaSearchCarsPage.yearMakeModelTile.get(i).isDisplayed(),
                        "Tile " + (i + 1) + " \'year, make, model\' field on page " + pageNumber + " is not displayed");
                softAssert.assertFalse(carvanaSearchCarsPage.yearMakeModelTile.get(i).getText().isEmpty(),
                        "Tile " + (i + 1) + " \'year, make, model\' field on page " + pageNumber + " is empty");

                softAssert.assertTrue(carvanaSearchCarsPage.trimMileageTile.get(i).isDisplayed(),
                        "Tile " + (i + 1) + " \'trim, mileage\' field on page " + pageNumber + " is not displayed");
                softAssert.assertFalse(carvanaSearchCarsPage.trimMileageTile.get(i).getText().isEmpty(),
                        "Tile " + (i + 1) + " \'trim, mileage\' field on page " + pageNumber + " is empty");

                softAssert.assertTrue(carvanaSearchCarsPage.priceTile.get(i).isDisplayed(),
                        "Tile " + (i + 1) + " \'price\' field on page " + pageNumber + " is not displayed");
                softAssert.assertTrue(Integer.parseInt(carvanaSearchCarsPage.priceTile.get(i).getText().replaceAll("[^0-9]", "")) > 0,
                        "Tile " + (i + 1) + " \'price\' field on page " + pageNumber + " is less than 1");

                softAssert.assertTrue(carvanaSearchCarsPage.monthlyPaymentTile.get(i).isDisplayed(),
                        "Tile " + (i + 1) + " \'monthly payment\' field on page " + pageNumber + " is not displayed");
                softAssert.assertFalse(carvanaSearchCarsPage.monthlyPaymentTile.get(i).getText().isEmpty(),
                        "Tile " + (i + 1) + " \'monthly payment\' field on page " + pageNumber + " is empty");

                softAssert.assertTrue(carvanaSearchCarsPage.downPaymentTile.get(i).isDisplayed(),
                        "Tile " + (i + 1) + " \'down payment\' field on page " + pageNumber + " is not displayed");
                softAssert.assertFalse(carvanaSearchCarsPage.downPaymentTile.get(i).getText().isEmpty(),
                        "Tile " + (i + 1) + " \'down payment\' field on page " + pageNumber + " is empty");

                softAssert.assertTrue(carvanaSearchCarsPage.deliveryChipTile.get(i).isDisplayed(),
                        "Tile " + (i + 1) + " \'delivery chip\' field on page " + pageNumber + " is not displayed");
                softAssert.assertFalse(carvanaSearchCarsPage.deliveryChipTile.get(i).getText().isEmpty(),
                        "Tile " + (i + 1) + "\'delivery chip\' field on page " + pageNumber + " is empty");
            });


            action.moveToElement(carvanaSearchCarsPage.nextButton).click().perform();
            j++;
            try {
                carvanaSearchCarsPage.closeButton.click();
            } catch (Exception e) {
                System.out.println(Arrays.toString(e.getStackTrace()));
            }
        }
        softAssert.assertAll();
    }
}
