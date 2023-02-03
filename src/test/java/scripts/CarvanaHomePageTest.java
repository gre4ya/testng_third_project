package scripts;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CarvanaBasePage;
import test_data.TestData;

public class CarvanaHomePageTest extends CarvanaBase{

    @BeforeMethod
    public void setPage(){
        carvanaBasePage = new CarvanaBasePage();
    }

    @Test(priority = 1, description = "Validate Carvana home page title and url")
    public void validateTitleAndURL(){
        Assert.assertEquals(driver.getTitle(), TestData.getHomePageTitle());
        Assert.assertEquals(driver.getCurrentUrl(), TestData.getHomePageURL());
    }

    @Test(priority = 2, description = "Validate the Carvana logo")
    public void validateLogo(){
        Assert.assertTrue(carvanaBasePage.logo.isDisplayed());
    }
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



}
