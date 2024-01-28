package TestCases;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import Processes.Process;
import io.github.bonigarcia.wdm.WebDriverManager;

import static org.junit.jupiter.api.Assertions.assertEquals;


class TestCase1{
	
	private static WebDriver driverChrome;
	private static WebDriver driverFirefox;
	private static ChromeOptions chromeOptions;
	private static FirefoxOptions firefoxOptions;
	private String baseUrl = "https://demo.oscommerce.com/watch/";
	private Process process; 
	
	public static void createAndStartChromeService() throws IOException {
		WebDriverManager.chromedriver().setup();
  		ArrayList<String> optionsList = new ArrayList<String>();
		chromeOptions = new ChromeOptions();
		optionsList.add("--start-maximized");
		optionsList.add("--incognito");
		optionsList.add("--ignore-certificate-errors");
		optionsList.add("--disable-notifications");
		optionsList.add("--allow-running-insecure-content");
		//optionsList.add("headless");
		
		chromeOptions.addArguments(optionsList);
		chromeOptions.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS,true);


	}
	
	public static void createAndStartFirefoxService() throws IOException {
		WebDriverManager.firefoxdriver().setup();
  		ArrayList<String> optionsList = new ArrayList<String>();
		firefoxOptions = new FirefoxOptions();
		optionsList.add("-private");
		
		firefoxOptions.addArguments(optionsList);

	}
	
	public static void createChromeDriver() {
		driverChrome = new ChromeDriver(chromeOptions);

	}
	
	public static void createFirefoxDriver() {
		driverFirefox = new FirefoxDriver(firefoxOptions);

	}
	
	public static void quitDrivers() {
		  if (driverChrome!=null) driverChrome.quit();
		  if (driverFirefox!=null)driverFirefox.quit();
		  driverChrome=null;
		  driverFirefox=null;
	}
	

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		createAndStartChromeService();
		createAndStartFirefoxService();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {}

	@BeforeEach
	void setUp() throws Exception {
		
		
	}

	@AfterEach
	void tearDown() throws Exception {
		quitDrivers();
	}

	@Test
	//A first test of the purchase process by adding and paying the product "Royal London 41003-03" with quantity "2" (executed in Chrome)
	void test1() throws InterruptedException {
		createChromeDriver();
		Process process = new Process(driverChrome);
		process.purchaseProcess("https://demo.oscommerce.com/watch/", "Royal London 41003-03", "2");
		int quantity = process.fetchQuantityFromCart();
	    assertEquals(2, quantity, "The quantity in the cart is not as expected");
	    
	}
		/*
		int quantity = process.getCartQuantity();
	    assertEquals(2, quantity);
	    */
	
	@Test
	//A second test of the purchase process by adding and paying the product "Citizen Eco-Drive Silver Tone Men" with quantity "3"
	void test2() throws InterruptedException {
		createFirefoxDriver();
		Process process = new Process(driverFirefox);
	}

}



