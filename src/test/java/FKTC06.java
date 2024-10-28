import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class FKTC06 {
    public static WebDriver driver = new ChromeDriver();

    // @BeforeTest: Method to set up the test environment, runs once before any test methods.
    @BeforeTest
    public static void Setup(){
        String testEnv = ConfigurationReader.getProperty("fieldKitSignUp"); // Retrieve register page link from configuration file via ConfigurationReader.
        driver.get(testEnv); // Open the login page in the browser.
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // Set an implicit wait of 10 seconds.
    }

    // @Test: Test method to validate error handling when required fields are left empty.
    @Test
    public void errorHandling (){

        // Locate the Terms of Use checkbox element by XPath and assign it to termsOfUseCheckbox.
        WebElement termsOfUseCheckbox = driver.findElement(By.xpath("/html/body/div/div/div/form/div[5]/div/label/span[2]"));

        // Locate the Create Account button element by XPath and assign it to createAccountButton.
        WebElement createAccountButton = driver.findElement(By.xpath("/html/body/div/div/div/form/button"));

        // Click on the Terms of Use checkbox to simulate agreement.
        termsOfUseCheckbox.click();

        // Click the Create Account button to attempt form submission without filling required fields.
        createAccountButton.click();

        // Locate error messages for each required field using XPath.
        WebElement nameErrorMessage = driver.findElement(By.xpath("/html/body/div/div/div/form/div[1]/div/div"));
        WebElement emailErrorMessage = driver.findElement(By.xpath("/html/body/div/div/div/form/div[2]/div/div"));
        WebElement passwordErrorMessage = driver.findElement(By.xpath("/html/body/div/div/div/form/div[3]/div/div"));
        WebElement confirmPasswordErrorMessage = driver.findElement(By.xpath("/html/body/div/div/div/form/div[4]/div/div"));

        // Uncheck the Terms of Use checkbox to trigger error message for the checkbox.
        termsOfUseCheckbox.click();

        // Locate the Terms of Use error message element by XPath.
        WebElement termsOfUseErrorMessage = driver.findElement(By.xpath("/html/body/div/div/div/form/div[6]/div"));

        // Assert that the error message is displayed for the Name field.
        Assert.assertTrue(nameErrorMessage.isDisplayed(), "Error message is not displayed for name field");
        System.out.println("\nError message is displayed successfully for name field");

        // Assert that the error message is displayed for the Email field.
        Assert.assertTrue(emailErrorMessage.isDisplayed(), "Error message is not displayed for email field");
        System.out.println("Error message is displayed successfully for email field");

        // Assert that the error message is displayed for the Password field.
        Assert.assertTrue(passwordErrorMessage.isDisplayed(), "Error message is not displayed for password field");
        System.out.println("Error message is displayed successfully for password field");

        // Assert that the error message is displayed for the Confirm Password field.
        Assert.assertTrue(confirmPasswordErrorMessage.isDisplayed(), "Error message is not displayed for confirm password field");
        System.out.println("Error message is displayed successfully for confirm password field");

        // Assert that the error message is displayed for the Terms of Use checkbox.
        Assert.assertTrue(termsOfUseErrorMessage.isDisplayed(), "Error message is not displayed for terms of use checkbox");
        System.out.println("Error message is displayed successfully for terms of use checkbox");
    }

    // @AfterTest: Method to close the browser after the test has run.
    @AfterTest
    public static void closeBrowser() throws InterruptedException {
        Thread.sleep(3000); // Waits for 3 seconds before closing for visibility.
        driver.close(); // Closes the browser.
    }
}
