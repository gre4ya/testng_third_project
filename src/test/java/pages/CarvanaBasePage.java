package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class CarvanaBasePage {
    public CarvanaBasePage(){PageFactory.initElements(Driver.getDriver(), this);}

    @FindBy(css = "div[class*='fSZhVx'] svg")
    public WebElement logo;
    @FindBy(css = "a[class*='gXAmVW']")
    public WebElement howItWorksDropdown;
    @FindBy(css = "a[class*='bQSPiS']")
    public WebElement aboutCarvanaDropdown;
    @FindBy(css = "a[class*='iXAdzc']")
    public WebElement supportAndContactDropdown;


    //div[class*='MenuTitle']
}
