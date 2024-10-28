import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class FKTC05 {
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
    public void Logout() throws InterruptedException {

        // Retrieve email, password, and dashboard, login URL from the configuration file via ConfigurationReader.
        String email = ConfigurationReader.getProperty("validEmail");
        String password = ConfigurationReader.getProperty(("validPassword"));
        String expectedURLDashboard = ConfigurationReader.getProperty("fieldKitDashboard");
        String expectedURLLogin = ConfigurationReader.getProperty("fieldKitLogin");

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
        Assert.assertEquals(driver.getCurrentUrl(), expectedURLDashboard, "URL mismatch: The user was not redirected to the expected page upon Login.");
        System.out.println("\nTest is successful: User was redirected to the expected URL after login");

        // Locate the profile element using XPath and assign it to the WebElement 'profile'.
        WebElement profile = driver.findElement(By.xpath("/html/body/div/div/div[1]/div[2]/div[1]/div[2]/a"));

        // Locate the logOut element using XPath and assign it to the WebElement 'logOut'.
        WebElement logOut = driver.findElement(By.xpath("/html/body/div/div/div[1]/div[2]/div[1]/div[2]/div[2]/header/div/a[2]"));

        // Click on the profile element to open the profile menu.
        profile.click();

        // Click on the logOut element to initiate the logout process.
        logOut.click();

        // Pause execution for 1 second to allow the page to fully load after logout.
        Thread.sleep(1000);

        // Assert that the current URL matches the expected login URL, verifying the user is redirected to the login page after logout.
        Assert.assertEquals(driver.getCurrentUrl(), expectedURLLogin, "URL mismatch: The user was not redirected to the expected page upon Logout.");
        System.out.println("Test is successful: User was redirected to the expected URL after logout");
    }

    // @AfterTest: Method to close the browser after the test has run.
    @AfterTest
    public static void closeBrowser() throws InterruptedException {
        Thread.sleep(3000); // Waits for 3 seconds before closing for visibility.
        driver.close(); // Closes the browser.
    }
}
