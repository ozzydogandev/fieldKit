// Import statements for Selenium WebDriver, locating elements, assertions, and TestNG annotations.
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

// Define the main class FKTC03, named after the test case or feature being tested.
public class FKTC03 {

    // Static WebDriver instance to control the browser, initialized with ChromeDriver.
    public static WebDriver driver = new ChromeDriver();

    // @BeforeTest: Method to set up the test environment, runs once before any test methods.
    @BeforeTest
    public static void Setup(){
        String testEnv = ConfigurationReader.getProperty("fieldKitLogin"); // Retrieve login page link from configuration file via ConfigurationReader.
        driver.get(testEnv); // Open the login page in the browser.
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // Set an implicit wait of 10 seconds.
    }

    // @Test: Test method for the login functionality.
    @Test
    public void Login() throws InterruptedException {

        // Retrieve email, password, and dashboard URL from the configuration file via ConfigurationReader.
        String email = ConfigurationReader.getProperty("validEmail");
        String password = ConfigurationReader.getProperty(("validPassword"));
        String expectedURLDashboard = ConfigurationReader.getProperty("fieldKitDashboard");

        // Locate email input field by XPath and assign it to emailBox.
        WebElement emailBox = driver.findElement(By.xpath("/html/body/div/div/form/div[1]/label"));

        // Locate password input field by XPath and assign it to passwordBox.
        WebElement passwordBox = driver.findElement(By.xpath("/html/body/div/div/form/div[2]/label/input"));

        // Locate login button by XPath and assign it to loginButton.
        WebElement loginButton = driver.findElement(By.xpath("/html/body/div/div/form/button"));

        // Input email into the emailBox field.
        emailBox.sendKeys(email);

        // Input password into the passwordBox field.
        passwordBox.sendKeys(password);

        // Click the login button to attempt to log in.
        loginButton.click();

        // Wait for 2 seconds to allow for page load (not ideal for long-term use, better replaced by explicit waits).
        Thread.sleep(2000);

        // Assert that the current URL matches the expected URL, indicating a successful login.
        Assert.assertEquals(driver.getCurrentUrl(), expectedURLDashboard, "URL mismatch: The user was not redirected to the expected page.");
        System.out.println("\nTest is successful: User was redirected to the expected URL");
    }

    // @AfterTest: Method to close the browser after the test has run.
    @AfterTest
    public static void closeBrowser() throws InterruptedException {
        Thread.sleep(3000); // Waits for 3 seconds before closing for visibility.
        driver.close(); // Closes the browser.
    }
}
