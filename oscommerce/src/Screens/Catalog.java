package Screens;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class Catalog {
	
	public WebDriver driver;
	
	public Catalog (WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	// Step 1
	
	// Method to navigate to a URL
    public void enterSite(String url, WebDriverWait wait) {
    	
    	// WebDriver navigates to the specified URL
        driver.get(url); 
    }
    
    // Step 2
    
    // Method to click on a specific product with a specific name
    public void clickProduct(WebDriverWait wait, String productName) {

        // Define dynamically the element for the product link and wait for the product link to be clickable
        WebElement productElement = wait.until(ExpectedConditions.elementToBeClickable(By.linkText(productName)));

        // Click it
        productElement.click();
    }
    
    // Step 3
    
    // Method to wait for and click the "Add to Cart" button
    public void clickAddToCartButton(WebDriverWait wait) {

        // Locate the button and wait for it to be clickable
    	WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".btn-2.add-to-cart")));

        // Click it
        addToCartButton.click();
    }
    
    // Step 4
    
    // Method to handle popup and click the element to add specified quantity of the product
    public void addProductInPopup(WebDriverWait wait, int quantity) throws InterruptedException {
    	
        // Wait for a few seconds before checking for the popup as it has delay
        Thread.sleep(4000);

        // Locate the popup and wait for it to be visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cart-form")));

        
        // Click the "+" button as many times as needed assuming the initial action (add to cart) adds the first item
        for (int i = 1; i < quantity; i++) {
        	
        	// Locate the element to increment the product quantity
            WebElement addProductElement = driver.findElement(By.cssSelector("div.qty span.bigger"));
            
            // Click it
            addProductElement.click();
            
            // Wait to ensure each click is registered
            Thread.sleep(1000); 
        }
    }
    
    // Step 5
    
    // Method to assert the quantity from the input form
    public int getQuantityFromInput(WebDriverWait wait) {
    	
        // Locate the input form and wait for it to be visible
        WebElement quantityInputElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input.qty-inp-s")));

        // Retrieve the value attribute from the input form element as a string as retrieving an integer is not possible
        String quantityValue = quantityInputElement.getAttribute("value");
        
        // Convert the string value to an integer in order to be asserted in Test class
        return Integer.parseInt(quantityValue);
    }
    
    // Step 6
    
    // Method to click the checkout button
    public void clickCheckoutButton(WebDriverWait wait) throws InterruptedException {
    	
    	// Wait for 2 seconds as the process has some delay
        Thread.sleep(2000);
        
        // Locate the checkout button and wait for it to be clickable
        WebElement checkoutButton = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("GO TO CART")));

        // Click it
        checkoutButton.click();
    }
    
    // Step 7
    
    // Method to click the "My Account" link
    public void clickMyAccountLink(WebDriverWait wait) {
    	
    	// Locate the link and wait for it to be clickable
        WebElement myAccountLink = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.my-acc-link")));
        
        // Click it
        myAccountLink.click();
    }

    // Method to enter the login credentials and click the login button
    public void enterLoginCredentialsAndLogin(String email, String password, WebDriverWait wait) {

        // Locate the email input and wait for it to be visible
        WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-email_address")));
        
        // Enter email
        emailInput.sendKeys(email);

        // Locate the password input
        WebElement passwordInput = driver.findElement(By.id("login-password"));
        
        // Enter password
        passwordInput.sendKeys(password);

        // Locate the login button
        WebElement loginButton = driver.findElement(By.cssSelector(".btn-2"));
        
        // Click it
        loginButton.click();  
    }
    
    // Method to hover over "Shopping Cart" and click the "Checkout" button after login
    public void hoverAndClickCart(WebDriverWait wait) {
        
    	// Create an instance of Actions class to perform mouse actions
        Actions actions = new Actions(driver);

        // Locate the cart box and wait for it to be visible
        WebElement cartBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cart-box")));
        
        // Hover over it
        actions.moveToElement(cartBox).build().perform();

        // Locate the "Checkout" button and wait for it to be clickable
        WebElement checkoutButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#cart-box .right-buttons a.btn")));
        
        // Click it
        checkoutButton.click();  
    }
    
    // Step 8
    
    // Method to click the radio button for payment method
    public void selectPaymentMethod(WebDriverWait wait) {

        // Locate the radio button for "Cash on Delivery" and wait for the radio button to be clickable
        WebElement radioButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[type='radio'][name='payment'][value='cod']")));

        // Click it
        radioButton.click();
    }
    
    // Step 9
    
    // Method to fulfill the payment form, confirm and pay
    public void fulfillPaymentForm(WebDriverWait wait) throws InterruptedException {
        
    	// Wait for 2 seconds as there is a delay in the process
        Thread.sleep(2000);
        
        // Other steps to fill the form can be included here if they were not fulfilled in the sign-up

        // Locate the "Confirm and pay" button and wait for it to be clickable
        WebElement confirmAndPayButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("box-209382")));

        // Click it
        confirmAndPayButton.click();
    }
    
    
    // Step 10
    
    // Method to verify the order confirmation message
    public boolean isOrderConfirmationDisplayed() {
    	
    	// Retrieve the HTML source of the page using the driver already initialized
        String pageSource = driver.getPageSource();
        
        // Check if the page source contains the specific text and return boolean
        return pageSource.contains("We've received your order");
    }

}
