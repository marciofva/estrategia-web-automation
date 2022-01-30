package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public abstract class BasePage {

    protected WebDriver driver;
    private static final int MAX_SECONDS_TIMEOUT = 40;
    private WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(MAX_SECONDS_TIMEOUT));
    }

    protected void clickOn(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", element);
    }

    protected List<WebElement> getAllElements(List<WebElement> elements) {
        return wait.until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    protected WebElement getElement(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected List<SearchContext> expandRootElement(By shadowDomHostElement) {
        wait.until(ExpectedConditions.presenceOfElementLocated(shadowDomHostElement));
        return (List<SearchContext>) ((JavascriptExecutor) driver).executeScript("return arguments[0].shadowRoot.children", driver.findElement(shadowDomHostElement));
    }

    protected void scrollByElement(WebElement element) {
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }
}
