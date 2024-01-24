package Screens;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.openqa.selenium.JavascriptExecutor;

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
	    By addToCartSelector = By.cssSelector(".btn-2.add-to-cart");
	    
	    // Wait for the element to be present in the DOM as it sometimes throws NoSuchElementException
	    wait.until(ExpectedConditions.presenceOfElementLocated(addToCartSelector));

	    WebElement addToCartButton = driver.findElement(addToCartSelector);
	    wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
	    addToCartButton.click();
	}
	
	public void waitForPopupAndAddItem(WebDriverWait wait) throws InterruptedException {
	    // Wait for 4 seconds for the popup to appear
	    Thread.sleep(4000);  // Using Thread.sleep is generally discouraged, but used here for simplicity

	    WebElement popup = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("popup-box-wrap")));
	    WebElement addItemButton = popup.findElement(By.className("bigger"));
	    addItemButton.click();
	}
	
	public int getCartQuantity() {
	    WebElement quantityInput = driver.findElement(By.cssSelector(".qty-inp-s"));
	    return Integer.parseInt(quantityInput.getAttribute("value"));
	} 
	
	// Step 6
	
	public void checkout(WebDriverWait wait) {
	    // Assuming the 'btn-2' button is uniquely identifiable within the popup
	    WebElement checkoutButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".popup-box-wrap .btn-2")));
	    wait.until(ExpectedConditions.elementToBeClickable(checkoutButton));
	    checkoutButton.click();
	}
	
	/*
	// Step 7
	
	public void navigateToAccountPage(WebDriverWait wait) {
	    WebElement myAccountButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".my-acc-link")));
	    myAccountButton.click();
	}

	public void login(String email, String password, WebDriverWait wait) {
	    WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-email_address")));
	    WebElement passwordInput = driver.findElement(By.id("login-password"));
	    WebElement loginButton = driver.findElement(By.cssSelector(".btn-2"));

	    emailInput.sendKeys(email);
	    passwordInput.sendKeys(password);
	    loginButton.click();
	}
	*/

}
