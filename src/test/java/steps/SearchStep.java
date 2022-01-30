package steps;

import factory.BrowserFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.SearchDetailsPage;
import pages.SearchPage;
import java.util.HashMap;

public class SearchStep {

    private WebDriver driver;
    private SearchPage search;
    private SearchDetailsPage search_details;
    private String installmentNumber = "";
    private String installmentPrice = "";

    public SearchStep(){
        this.driver = BrowserFactory.getDriver();
        search = new SearchPage(driver);
        search_details = new SearchDetailsPage(driver);
    }

    @Given("I choose {string} in the list")
    public void i_choose_in_the_list(String item) {
        search.selectItemByName(item);
    }

    @When("I select an available course")
    public void i_select_an_available_course() {
        HashMap<String, String> map = search.selectRandomItem();
        installmentNumber = map.get("instalmentNumber");
        installmentPrice = map.get("instalmentPrice");
        search.goToDetails(search.getIndex());
    }

    @Then("I should see a right installment payment conditions")
    public void i_should_see_a_right_installment_payment_conditions() {
        Assert.assertEquals(installmentNumber, search_details.getInstallmentNumber());
        Assert.assertEquals(installmentPrice, search_details.getInstallmentPrice());
    }
}
