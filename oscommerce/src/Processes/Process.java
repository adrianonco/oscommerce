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

}
