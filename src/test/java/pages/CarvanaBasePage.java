package pages;

import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class CarvanaBasePage {
    public CarvanaBasePage(){PageFactory.initElements(Driver.getDriver(), this);}


}
