package steps;

import factory.BrowserFactory;
import io.cucumber.java.en.Given;
import org.openqa.selenium.WebDriver;
import pages.HomePage;

public class HomeStep {

    private WebDriver driver;
    private HomePage home;

    public HomeStep(){
        this.driver = BrowserFactory.getDriver();
        home = new HomePage(driver);
    }

    @Given("I search for {string}")
    public void i_search(String searchBy) {
        home.goToSearchPage(searchBy);
    }
}
