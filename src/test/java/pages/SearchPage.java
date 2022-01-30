package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.HashMap;
import java.util.List;

public class SearchPage extends BasePage {

    @FindBy(css = ".card-prod-title > a")
    private List<WebElement> firstCardList;

    @FindBy(css = ".card-prod")
    private List<WebElement> secondCardList;

    private static final By PRODUCT_INSTALLMENT_ROW = By.cssSelector(".card-prod-price");

    private String instalmentNumber = "";
    private String instalmentPrice = "";
    private int index = 0;

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    public SearchPage selectItemByName(String item) {
        for(WebElement element : getAllElements(firstCardList)) {
            if (element.getText().contains(item)) {
                scrollByElement(element);
                clickOn(element);
                break;
            }
        }
        return this;
    }


    public HashMap<String, String> selectRandomItem() {
        int index = generateRandomIndexToSeeDetails();
        formatPrice(secondCardList.get(index).findElement(PRODUCT_INSTALLMENT_ROW).getText());
        HashMap<String, String> map = new HashMap<>();
        map.put("instalmentNumber", getInstallmentNumber());
        map.put("instalmentPrice", getInstallmentPrice());
        return map;
    }

    public void formatPrice(String literalText) {
        String[] formatPriceArray = literalText.replace("cursos em até ", "")
                .replace(" de R$ ", "")
                .split("x");

        if (formatPriceArray.length > 1) {
            //Payment in installments
            instalmentNumber = formatPriceArray[0].trim();
            instalmentPrice = formatPriceArray[formatPriceArray.length-1].trim();
        }else{
            //Payment upfront
            instalmentNumber = "";
            instalmentPrice = formatPriceArray[0].trim();
        }
    }

    public int generateRandomIndexToSeeDetails(){
        index = (int) Math.floor(Math.random() * getAllElements(secondCardList).size());
        return index;
    }

    public SearchDetailsPage goToDetails(int index) {
        scrollByElement(getAllElements(secondCardList).get(index));
        clickOn(getElement(secondCardList.get(index)).findElement(By.cssSelector("h1 > a")));
        return new SearchDetailsPage(driver);
    }

    public int getIndex() {
        return index;
    }

    public String getInstallmentNumber() {
        return instalmentNumber;
    }

    public String getInstallmentPrice() {
        return instalmentPrice;
    }
}
