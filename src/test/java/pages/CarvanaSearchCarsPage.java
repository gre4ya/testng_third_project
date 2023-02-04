package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CarvanaSearchCarsPage extends CarvanaBasePage{
    public CarvanaSearchCarsPage(){super();}

    @FindBy(css = "div[class*='qjQqb'] button")
    public List<WebElement> filterOptionsHeaders;
    @FindBy(css = "input[class*='input']")
    public WebElement searchInput;
    @FindBy(css = "button[class*='iPPBRN']")
    public WebElement goButton;
    @FindBy(css = "div[data-test='ResultTile']")
    public List<WebElement> resultTiles;
    @FindBy(css = "div[data-test='ResultTile'] picture")
    public List<WebElement> imageTile;
    @FindBy(css = "div[class='favorite-vehicle']")
    public List<WebElement> favButtonTile;
    @FindBy(css = "div[class*='inventory-type-variant']")
    public List<WebElement> inventoryTypeTile;
    @FindBy(css = "div[class='year-make']")
    public List<WebElement> yearMakeModelTile;
    @FindBy(css = "div[class='trim-mileage']")
    public List<WebElement> trimMileageTile;
    @FindBy(css = "div[data-testid='price']")
    public List<WebElement> priceTile;
    @FindBy(css = ".monthly-payment")
    public List<WebElement> monthlyPaymentTile;
    @FindBy(css = ".down-payment")
    public List<WebElement> downPaymentTile;
    @FindBy(css = "div[class='tk-pane full-width']:nth-child(2)")
    public List<WebElement> deliveryChipTile;
    //@FindBy(xpath = "//button[text()='Next']")
    @FindBy(css = "#pagination li:nth-child(3) button:nth-child(1)")
    public WebElement nextButton;

    @FindBy(css = "svg[class*='llqIcd']")
    public WebElement closeButton;






    /**
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











}
