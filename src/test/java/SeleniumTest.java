import com.beust.ah.A;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static org.testng.AssertJUnit.assertTrue;

public class SeleniumTest {

    public static WebDriver driver = new ChromeDriver();

    @BeforeTest
    public static void Setup(){
        String testEnv = "https://anupdamoda.github.io/AceOnlineShoePortal/index.html";
        driver.get(testEnv);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    void testSteps() throws InterruptedException {
        WebElement hamburgerMenu = driver.findElement(By.xpath("//*[@id=\"menuToggle\"]/input"));
        hamburgerMenu.click();

        WebElement signInPortal = driver.findElement(By.linkText("Sign In Portal"));
        signInPortal.click();

        WebElement username = driver.findElement(By.id("usr"));
        WebElement password = driver.findElement(By.id("pwd"));
        WebElement loginButton = driver.findElement(By.xpath("/html/body/center[1]/div/form/input"));
        username.sendKeys("sa");
        password.sendKeys("sa");
        loginButton.click();

        List<WebElement> shoeElements = driver.findElements(By.xpath("//*[@id=\"ShoeType\"]"));
        ArrayList<String> shoesList = new ArrayList<>();

        for(WebElement shoe : shoeElements){
            shoesList.add(shoe.getText());
        }

        for(String shoe : shoesList){
            System.out.println(shoe);
        }

    }
}
