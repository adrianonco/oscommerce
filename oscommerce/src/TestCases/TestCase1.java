package TestCases;

import java.io.IOException;
import java.time.Duration;
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
import org.openqa.selenium.support.ui.WebDriverWait;
import Processes.Process;
import io.github.bonigarcia.wdm.WebDriverManager;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class TestCase1{
	
	private static WebDriver driverChrome;
	private static WebDriver driverFirefox;
	private static ChromeOptions chromeOptions;
	private static FirefoxOptions firefoxOptions;
	private String baseUrl = "https://sqademosatp.net/watch/";
	private Process process; 
	
	public static void createAndStartChromeService() throws IOException {
	
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
		
		// Initialize a Chrome WebDriver instance for the test
		createChromeDriver();
		
		// Create a new 'Process' object, passing the Chrome WebDriver to handle the test's process flow
		Process process = new Process(driverChrome);
		
		// Step 1, 2 & 4: Execute the purchase process for the specific product on the indicated website and add 2 items to the cart 
		
		/*
		String productName = "Royal London 41003-03";
	    int expectedQuantity = 2; // Define expected quantity for the assertion

	    // Execute the purchase process
	    process.purchaseProcess("https://sqademosatp.net/watch/", productName, expectedQuantity);
		*/
	    
		process.purchaseProcess("https://sqademosatp.net/watch/", "Royal London 41003-03", 2);
		
	    
		// Step 5: 
	    
	    int actualQuantity = process.fetchQuantityFromInput();
	    assertEquals(2, actualQuantity, "The expected quantity does not match the actual quantity in the cart.");
	    
	    /*
		// Fetch the actual quantity from the input field using the new Process method
	    int actualQuantity = process.fetchQuantityFromInput();
	    
	    // Assert that actual quantity matches expected quantity
	    assertEquals(expectedQuantity, actualQuantity, "The expected quantity does not match the actual quantity in the cart.");
		*/
		/*
		// Call the method in Process and fetch the quantity of the item from the cart
		int quantity = process.fetchQuantityFromCart(); 
		// Perform the assert and assert if it matches the expected quantity (2)
	    assertEquals(2, quantity, "The expected quantity does not match the actual quantity in the cart."); 
	    */
		
		// Step 8:
	    // Directly pass user details to the method without using a User object.
	    WebDriverWait wait = new WebDriverWait(driverChrome, Duration.ofSeconds(10)); // Assuming driverChrome is your WebDriver instance
	    process.fillPaymentFormProcess(wait, "Pepe", "Pérez", "Avda. Diagonal", "08013", "Barcelona", "example@example.com");

	    // Further actions or assertions to validate the filling process.
	 
	    // Step 10:
	    // Check if the order confirmation message "We've received your order" is displayed on the page
	    boolean confirmationDisplayed = process.checkOrderConfirmation(); 
	    // Assert that the message is displayed and throw a fail message if it is not
	    assertTrue(confirmationDisplayed, "Order confirmation message was not displayed.");
	
	}
	
	
	@Test
	//A second test of the purchase process by adding and paying the product "Citizen Eco-Drive Silver Tone Men" with quantity "3"
	void test2() throws InterruptedException {
	    
		// Ensure Firefox driver is initialized instead of Chrome
		createFirefoxDriver(); 
		
		// Use the Firefox driver for the process
	    process = new Process(driverFirefox); 
	    
	    String productName = "Citizen Eco-Drive Silver Tone Men";
	    int expectedQuantity = 3; // Define expected quantity for the assertion

	    // Execute the purchase process
	    process.purchaseProcess("https://sqademosatp.net/watch/", productName, expectedQuantity);
	    
	    /*
	    // Execute the purchase process for "Citizen Eco-Drive Silver Tone Men" with quantity (3)
	    process.purchaseProcess("https://sqademosatp.net/watch/", "Citizen Eco-Drive Silver Tone Men", 3);
	    */
	    
	    // Step 5: 
	    
	    // Fetch the actual quantity from the input field using the new Process method
	    int actualQuantity = process.fetchQuantityFromInput();
	    
	    // Assert that actual quantity matches expected quantity
	    assertEquals(expectedQuantity, actualQuantity, "The expected quantity does not match the actual quantity in the cart.");
	    
	    /*
	 	// Call the method in Process and fetch the quantity of the item from the cart
	    int quantity = process.fetchQuantityFromCart();
	    // Perform the assert and assert if it matches the expected quantity (3)
	    assertEquals(3, quantity, "The expected quantity does not match the actual quantity in the cart.");
	    */
	    
	    // Step 7: Perform the log-in with given credentials
	    

	    // Step 10:
	    // Check if the order confirmation message "We've received your order" is displayed on the page
	    boolean confirmationDisplayed = process.checkOrderConfirmation();
	    // Assert that the message is displayed and throw a fail message if it is not
	    assertTrue(confirmationDisplayed, "Order confirmation message was not displayed.");
	
	}
	
}






