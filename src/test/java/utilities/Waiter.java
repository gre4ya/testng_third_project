package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.rmi.server.ExportException;

public class Waiter {

    public static void pause(int seconds){
        try {
            Thread.sleep(1000 * seconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void waitURLToContainFraction(int seconds, String fraction){
        new WebDriverWait(Driver.getDriver(), seconds).until(ExpectedConditions.urlContains(fraction));
    }
}
