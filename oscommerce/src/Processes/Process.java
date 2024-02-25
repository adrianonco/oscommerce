package Processes;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import Screens.Catalog;

public class Process {
	private static final Duration ESPERA = Duration.ofSeconds(50);
	private  WebDriverWait wait;
	private WebDriver driver;
	public Catalog catalog;
	
	// Method for the Process class: initializes the WebDriver, Catalog, and WebDriverWait instances.
	public Process(WebDriver driver) {
		
		// Assign the WebDriver instance passed to the constructor to the class member
		this.driver=driver;
		
		// Initialize the Catalog object with the WebDriver instance
		catalog = new Catalog(driver);
		
		// Initialize WebDriverWait with the driver and a predefined wait duration (ESPERA)
		wait = new WebDriverWait(driver, ESPERA);
	}
	
	// Method to navigate to a specific URL
	public void enterSite(String url) {
		
		// WebDriver navigates to the specified URL
		driver.get(url);
	}
	
	// Method to perform the purchase process for a given product on a specified website
	public void purchaseProcess(String baseUrl, String product, int quantity) throws InterruptedException {
		
		// Step 1: Navigate to the base URL
		this.enterSite(baseUrl); 
		
		// Step 2: Click on the product specified dynamically
		catalog.clickProduct(wait, product); 
		
		// Step 3: Click the "Add to Cart" button
		catalog.clickAddToCartButton(wait); 
		
		// Step 4: Add quantity of product dynamically
		catalog.addProductInPopup(wait, quantity); 
	
		
	}
	
	// Method to fetch the quantity from the input form
    public int fetchQuantityFromInput() {
        
    	// Step 5:
    	// Delegate the call to the catalog's method to retrieve the visible quantity
        return catalog.getVisibleQuantity(wait);
        
    }
		
        /*
		// Step 5:
		int actualQuantity = fetchQuantityFromInput(); // Adjusted to call fetchQuantityFromInput here
	    // You would then use actualQuantity for assertion in the test method, not here directly
		*/
    
        // Method to encapsulate the checkout process
        public void proceedToCheckout() throws InterruptedException {
		
        	// Step 6: Checkout process
        
        
		
		// Click the checkout button to proceed with the purchase
		catalog.closePopUp(wait);
		
		// Perform hover and click checkout button
		catalog.hoverAndClickCart(wait); 
	    
	    // Step 7: Click on the desired radio button
	    catalog.selectPaymentMethod(wait);
	}
	
	/*
	// Step 5: Assert specified quantities
	public int fetchQuantityFromInput() {
	    // Delegate the call to the catalog's method and return its result
	    return catalog.getVisibleQuantity(new WebDriverWait(driver, Duration.ofSeconds(10)));
	}
	*/
	
	/*
	// Method to get the quantity from the Catalog class and return it
	public int fetchQuantityFromCart() {
		
		// Get the quantity from the input form
	    return catalog.getQuantityFromInput(wait); 
	}
	*/
	
	// Step 8: 
	public void fillPaymentFormProcess(WebDriverWait wait, String firstName, String lastName, String streetAddress, String postcode, String city, String email) throws InterruptedException {
	    catalog.fillPaymentForm(wait, firstName, lastName, streetAddress, postcode, city, email);
	}
    
    // Step 9: Check success message
    
    // Method to check the order confirmation message
	public boolean checkOrderConfirmation() {
		
	    // Call the method from 'Catalog' to check it and return if it is true or false
	    return catalog.isOrderConfirmationDisplayed();
	}
}