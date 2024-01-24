package Screens;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Catalog {
	
	public WebDriver driver;
	
	public Catalog (WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	public void ClickProduct(String product, WebDriverWait wait) {
		WebElement productLink = driver.findElement(By.cssSelector("[data-id='29']"));
	    wait.until(ExpectedConditions.elementToBeClickable(productLink));
	    productLink.click();
	}
	
	public void addToCart(WebDriverWait wait) {
	    WebElement addToCartButton = driver.findElement(By.cssSelector(".btn-2.add-to-cart"));
	    wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
	    addToCartButton.click();
	}
	
	public void waitForPopupAndAddItem(WebDriverWait wait) throws InterruptedException {
	    // Wait for 6 seconds for the popup to appear
	    Thread.sleep(6000);  // Using Thread.sleep is generally discouraged, but used here for simplicity

	    WebElement popup = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("popup-box-wrap")));
	    WebElement addItemButton = popup.findElement(By.className("bigger"));
	    addItemButton.click();
	}
	
	public int getCartQuantity() {
	    WebElement quantityInput = driver.findElement(By.cssSelector(".qty-inp-s"));
	    return Integer.parseInt(quantityInput.getAttribute("value"));
	}
	
	/*
	public void updateQuantity(WebDriverWait wait, String quantity) {
	    WebElement quantityUpdateButton = driver.findElement(By.cssSelector(".popup-box-wrap .bigger"));
	    wait.until(ExpectedConditions.elementToBeClickable(quantityUpdateButton));
	    quantityUpdateButton.click();
	}
	*/
	
	/*
	public String getQuantity() {
	    // Assuming there's a way to fetch the current quantity from the UI
	    WebElement quantityElement = driver.findElement(By.cssSelector(".qty-inp-s"));
	    return quantityElement.getAttribute("value");
	}
	*/
	
	/*
	public boolean verifyQuantity(WebDriverWait wait, String expectedQuantity) {
	    WebElement quantityElement = driver.findElement(By.className("qty-inp-s"));
	    wait.until(ExpectedConditions.visibilityOf(quantityElement));
	    String actualQuantity = quantityElement.getAttribute("value");
	    return actualQuantity.equals(expectedQuantity);
	}
	*/
	  

}
