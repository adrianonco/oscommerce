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
		
		// Step 2: Click on the specified product
		catalog.clickProduct(wait, product); 
		
		// Step 3: Click the "Add to Cart" button
		catalog.clickAddToCartButton(wait); 
		
		// Step 4: Handle additional actions in the popup
		catalog.addProductInPopup(wait, quantity); 
		
		// Step 6: Click the checkout button to proceed with the purchase
		catalog.clickCheckoutButton(wait);
	}
	
	// Step 5
	
	// Method to get the quantity from the Catalog class and return it
	public int fetchQuantityFromCart() {
		
		// Get the quantity from the input form
	    return catalog.getQuantityFromInput(wait); 
	}
	
	// Step 7
	
	// Method to perform the login process and come back to checkout
	public void login(String email, String password) throws InterruptedException {
		
		// Click on the "My Account" link
	    catalog.clickMyAccountLink(wait); 
	    
	    // Enter login credentials and submit
	    catalog.enterLoginCredentialsAndLogin(email, password, wait); 
	    
	    // Perform hover and click checkout button
	    catalog.hoverAndClickCart(wait); 
	    
	    // Click on the desired radio button
	    catalog.selectPaymentMethod(wait);
	    
	    // Complete the form and confirm payment
        catalog.fulfillPaymentForm(wait); 
    }
    
    // Step 10
    
    // Method to check the order confirmation message
	public boolean checkOrderConfirmation() {
		
	    // Call the method from 'Catalog' to check it and return if it is true or false
	    return catalog.isOrderConfirmationDisplayed();
	}

}
