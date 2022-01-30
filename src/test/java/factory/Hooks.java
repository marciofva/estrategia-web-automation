package factory;

import helper.LoadProperties;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.Properties;
import java.util.logging.Logger;

public class Hooks {

	public static LoadProperties load_prop = new LoadProperties();

	@Before
	public static void setup() throws MalformedURLException, IllegalAccessException {
		String mavenProfileEnv = System.getProperty("env");
		String browserEnv = System.getProperty("browser");

		Logger.getGlobal().info(">>>> Environment: " + mavenProfileEnv.toUpperCase());
		Logger.getGlobal().info(">>>> Start browser: " + browserEnv.toUpperCase());

		Properties prop = load_prop.loadPropertiesFile("config.properties");

		BrowserFactory.setDriver(BrowserFactory.createBrowserInstance(browserEnv));
		BrowserFactory.getDriver().get(prop.getProperty("url"));
	}

	@Before
	public void before(final Scenario scenario) {
		String[] scenarioTags = scenario.getSourceTagNames().toArray(new String[]{});
		Logger.getGlobal().info(">>>> Tags: " + Arrays.toString(scenarioTags));
	}

	@After
	public static void tearDown() {
		BrowserFactory.getDriver().close();
	}
}
