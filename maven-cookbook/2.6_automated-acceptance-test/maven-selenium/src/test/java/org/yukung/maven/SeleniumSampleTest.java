import static org.junit.Assert.*;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumSampleTest {

    @Test
    public void testHelloWorld() {
        WebDriver driver = new HtmlUnitDriver();
        try {
            driver.get("http://localhost:8080/maven-selenium/");
            WebElement element = driver.findElement(By.tagName("h2"));
            assertEquals("Hello World!", element.getText());
        } catch (Exception e) {
            fail(e.getMessage());
        } finally {
            driver.quit();
        }
    }
}
