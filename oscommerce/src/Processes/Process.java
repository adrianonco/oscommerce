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
	
	public Process(WebDriver driver) {
		this.driver=driver;
		catalog = new Catalog(driver);
		wait = new WebDriverWait(driver, ESPERA);
	}
	
	public void enterSite(String url) {
		driver.get(url);
	}
	
	public void purchaseProcess(String baseUrl, String product, String quantity) throws InterruptedException {
		this.enterSite(baseUrl); // Step 1
		catalog.clickProduct(wait); // Step 2
		catalog.clickAddToCartButton(wait); // Step 3
		catalog.addProductInPopup(wait); // Step 4
		catalog.clickCheckoutButton(wait); // Step 6
	}
	
	// Step 5: Method to get the quantity from the Catalog class and return it
	public int fetchQuantityFromCart() {
	    return catalog.getQuantityFromInput(wait); // Get the quantity from the input form
	}

	
	/*
	
	// Step 1: Navigate to a specific URL
	public void enterSite (String url) {
		driver.get(url);
	}
	
	public void purchaseProcess(String baseUrl, String product, String quantity) throws InterruptedException {
		this.enterSite(baseUrl);
		catalog.ClickProduct(product, wait);
		catalog.addToCart(wait);
		catalog.waitForPopupAndAddItem(wait);
		catalog.navigateToAccountPage(wait);
	    catalog.login("onco85@hotmail.com", "pass1234", wait);
	}
	
	public int getCartQuantity() {
	    return catalog.getCartQuantity();
	}
	
	*/

}
