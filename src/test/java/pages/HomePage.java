package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import java.util.List;

public class HomePage extends BasePage {

    @FindBy(css = ".nav-header-links > a")
    private List<WebElement> searchLinkBar;

    private static final By SHADOW_DOM_HOST_ELEMENT = By.cssSelector("getsitecontrol-widget[id^='getsitecontrol-1']");
    private static final By SHADOW_CONTENT_CLOSE_POPUP_ICON = By.cssSelector(".close");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public SearchPage goToSearchPage(String text) {
        close_popup();

        for(WebElement element : searchLinkBar) {
            if (element.getText().equalsIgnoreCase(text)){
                clickOn(element);
                break;
            }
        }
        return new SearchPage(driver);
    }

    public void close_popup() {
        expandRootElement(SHADOW_DOM_HOST_ELEMENT).get(3).findElement(SHADOW_CONTENT_CLOSE_POPUP_ICON).click();
    }
}
