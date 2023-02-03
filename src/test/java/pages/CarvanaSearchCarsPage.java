package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CarvanaSearchCarsPage extends CarvanaBasePage{
    public CarvanaSearchCarsPage(){super();}

    @FindBy(css = "div[class*='qjQqb'] button")
    public List<WebElement> filterOptionsHeaders;



}
