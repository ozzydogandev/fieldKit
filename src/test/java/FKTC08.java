import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class FKTC08 {
    public static WebDriver driver = new ChromeDriver();

    // @BeforeTest: Method to set up the test environment, runs once before any test methods.
    @BeforeTest
    public static void Setup(){
        String testEnv = ConfigurationReader.getProperty("fieldKitAddProject"); // Retrieve add project page link from configuration file via ConfigurationReader.
        driver.get(testEnv); // Open the login page in the browser.
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // Set an implicit wait of 10 seconds.
    }

    // @Test: Test method to validate redirection for a logged-out user attempting to access the add project page.
    @Test
    public void addProjectRedirect() throws InterruptedException {

        // Retrieve the expected login URL from the configuration file using ConfigurationReader.
        String expectedURLLogin = ConfigurationReader.getProperty("fieldKitLogin");

        // Pause execution for 2 seconds to allow the page to load fully before checking the URL.
        Thread.sleep(2000);

        // Assert that the current URL matches the expected login URL, indicating that the user was redirected correctly.
        Assert.assertEquals(driver.getCurrentUrl(), expectedURLLogin, "Logged out user is not redirected to login page.");
        System.out.println("Logged-out user is successfully redirected to the login page after attempting to access the add project page.");
    }


    // @AfterTest: Method to close the browser after the test has run.
    @AfterTest
    public static void closeBrowser() throws InterruptedException {
        Thread.sleep(3000); // Waits for 3 seconds before closing for visibility.
        driver.close(); // Closes the browser.
    }
}
