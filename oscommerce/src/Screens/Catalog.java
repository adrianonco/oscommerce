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
	    WebElement addToCartButton = driver.findElement(By.cssSelector("button[type='submit'].btn-2.add-to-cart"));
	    wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
	    addToCartButton.click();
	}
	
	public void updateQuantity(WebDriverWait wait, String quantity) {
	    WebElement quantityUpdateButton = driver.findElement(By.cssSelector(".bigger"));
	    wait.until(ExpectedConditions.elementToBeClickable(quantityUpdateButton));
	}
	
	public void assertQuantity(WebDriverWait wait, String expectedQuantity) throws Exception {
	    WebElement quantityField = driver.findElement(By.cssSelector("your-quantity-field-selector")); // Replace with the actual selector
	    wait.until(ExpectedConditions.visibilityOf(quantityField));
	    String actualQuantity = quantityField.getAttribute("value"); // Replace "value" with the appropriate attribute if different

	    if (!actualQuantity.equals(expectedQuantity)) {
	        throw new Exception("Quantity mismatch: expected " + expectedQuantity + " but found " + actualQuantity);
	    }
	}

}
