// Import statements for Selenium WebDriver, locating elements, assertions, and TestNG annotations.
import org.openqa.selenium.By;               // Used to locate elements by various selectors (e.g., ID, XPath).
import org.openqa.selenium.WebDriver;        // WebDriver interface controls the browser.
import org.openqa.selenium.WebElement;       // WebElement represents HTML elements in Selenium.
import org.openqa.selenium.chrome.ChromeDriver; // ChromeDriver is the class for controlling the Chrome browser.
import org.testng.Assert;                    // TestNG assertion library for validating test conditions.
import org.testng.annotations.BeforeTest;    // TestNG annotation for a setup method that runs before tests.
import org.testng.annotations.Test;          // TestNG annotation for test methods.

import java.util.concurrent.TimeUnit;        // Utility for setting time units (e.g., seconds for implicit waits).

// Define the main class FKTC03, named after the test case or feature being tested.
public class FKTC03 {

    // Static WebDriver instance to control the browser, initialized with ChromeDriver.
    public static WebDriver driver = new ChromeDriver();

    // @BeforeTest: Method to set up the test environment, runs once before any test methods.
    @BeforeTest
    public static void Setup(){
        String testEnv = "https://portal.fieldkit.org/login";  // Define the test environment URL.
        driver.get(testEnv);                                   // Open the login page in the browser.
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); // Set an implicit wait of 10 seconds.
    }

    // @Test: Test method for the login functionality.
    @Test
    public void Login() throws InterruptedException {
        // Locate email input field by XPath and assign it to emailBox.
        WebElement emailBox = driver.findElement(By.xpath("/html/body/div/div/form/div[1]/label"));

        // Locate password input field by XPath and assign it to passwordBox.
        WebElement passwordBox = driver.findElement(By.xpath("/html/body/div/div/form/div[2]/label/input"));

        // Locate login button by XPath and assign it to loginButton.
        WebElement loginButton = driver.findElement(By.xpath("/html/body/div/div/form/button"));

        // Input email into the emailBox field.
        emailBox.sendKeys("ozzyfieldkit+3@gmail.com");

        // Input password into the passwordBox field.
        passwordBox.sendKeys("ozzyfieldkit");

        // Click the login button to attempt to log in.
        loginButton.click();

        // Wait for 2 seconds to allow for page load (not ideal for long-term use, better replaced by explicit waits).
        Thread.sleep(2000);

        // Define the expected URL after successful login.
        String expectedURL = "https://portal.fieldkit.org/dashboard";

        // Assert that the current URL matches the expected URL, indicating a successful login.
        Assert.assertEquals(driver.getCurrentUrl(), expectedURL);
    }
}
