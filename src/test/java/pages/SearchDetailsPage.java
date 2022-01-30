package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchDetailsPage extends BasePage {

    @FindBy(css = ".cur-details-shopping-price div")
    private WebElement totalPriceLabel;

    @FindBy(css = ".cur-details-shopping-installments")
    private WebElement instalmentPriceLabel;

    public SearchDetailsPage(WebDriver driver) {
        super(driver);
    }

    public String getInstallmentNumber() {
        String[] formatPriceArray = getElement(instalmentPriceLabel).getText()
                .replace("ou ", "")
                .split("x");
        return formatPriceArray[0].trim();
    }

    public String getInstallmentPrice() {
        String[] formatPriceArray = getElement(instalmentPriceLabel).getText().split(" ");
        return formatPriceArray[formatPriceArray.length-1].trim();
    }
}
