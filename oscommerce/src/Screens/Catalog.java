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
    	
        // Define the CSS selector for the product link
        By productSelector = By.linkText(productName); 

        // Wait for the product link to be clickable
        WebElement productElement = wait.until(ExpectedConditions.elementToBeClickable(productSelector));

        // Click it
        productElement.click();
    }
    
    // Step 3
    
    // Method to wait for and click the "Add to Cart" button
    public void clickAddToCartButton(WebDriverWait wait) {
    	
        // Define the CSS selector for the button
    	By addToCartSelector = By.cssSelector(".btn-2.add-to-cart");

        // Wait for the button to be clickable
    	WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(addToCartSelector));

        // Click it
        addToCartButton.click();
    }
    
    // Step 4
    
    // Method to handle popup and click the element to add another product
    public void addProductInPopup(WebDriverWait wait, int quantity) throws InterruptedException {
    	
        // Wait for a few seconds before checking for the popup as it has delay
        Thread.sleep(4000);

        // Define the CSS selector of the popup and wait for it to be visible
        By popupSelector = By.id("cart-form");
        wait.until(ExpectedConditions.visibilityOfElementLocated(popupSelector));

        // Define the CSS selector of the element, locate and click it
        By addProductSelector = By.cssSelector("div.qty span.bigger");
        
        // Click the "add more" button as many times as needed to match the desired quantity
        // Assuming the initial action (add to cart) adds the first item,
        // so we start the loop from 1 rather than 0.
        for (int i = 1; i < quantity; i++) {
            WebElement addProductElement = driver.findElement(addProductSelector);
            addProductElement.click();
            // Optionally, add a short wait here if needed to ensure the action is registered
            Thread.sleep(1000); // Example: wait 1 second between clicks
        }
    }
    
    // Step 5
    
    // Method to assert the quantity from the input form
    public int getQuantityFromInput(WebDriverWait wait) {
    	
        // Define the CSS selector of the input form and wait for it to be visible
        By quantityInputSelector = By.cssSelector("input.qty-inp-s");
        WebElement quantityInputElement = wait.until(ExpectedConditions.visibilityOfElementLocated(quantityInputSelector));

        // Retrieve the value attribute and parse it to an integer
        String quantityValue = quantityInputElement.getAttribute("value");
        return Integer.parseInt(quantityValue);
    }
    
    // Step 6
    
    // Method to click the checkout button
    public void clickCheckoutButton(WebDriverWait wait) throws InterruptedException {
    	
    	// Wait for 2 seconds before checking the quantity has been updated
        Thread.sleep(2000);
        
        // Define the CSS selector of the checkout button and wait for it to be clickable
        By checkoutButtonSelector = By.linkText("GO TO CART");
        WebElement checkoutButton = wait.until(ExpectedConditions.elementToBeClickable(checkoutButtonSelector));

        // Click it
        checkoutButton.click();
    }
    
    // Step 7
    
    // Method to click the "My Account" link
    public void clickMyAccountLink(WebDriverWait wait) {
    	
    	// Define the CSS selector of the link and wait for it to be clickable
        WebElement myAccountLink = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.my-acc-link")));
        
        // Click it
        myAccountLink.click();
    }

    // Method to enter the login credentials and click the login button
    public void enterLoginCredentialsAndLogin(String email, String password, WebDriverWait wait) {

        // Locate the email input, wait for it to be visible and enter email
        WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-email_address")));
        emailInput.sendKeys(email);

        // Locate the password input and enter password
        WebElement passwordInput = driver.findElement(By.id("login-password"));
        passwordInput.sendKeys(password);

        // Locate and click the login button
        WebElement loginButton = driver.findElement(By.cssSelector(".btn-2"));
        loginButton.click();  
    }
    
    // Method to hover over "Shopping Cart" and click the "Checkout" button after login
    public void hoverAndClickCart(WebDriverWait wait) {
        
    	// Create an instance of Actions class to perform mouse actions
        Actions actions = new Actions(driver);

        // Locate, wait for and hover over the "Shopping Cart" button
        WebElement cartBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cart-box")));
        actions.moveToElement(cartBox).build().perform();

        // Wait for the "Checkout" button to be clickable
        WebElement checkoutButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#cart-box .right-buttons a.btn")));
        
        // Click it
        checkoutButton.click();  
    }
    
    // Step 8
    
    // Method to click the radio button for payment method
    public void selectPaymentMethod(WebDriverWait wait) {
        
    	// Use a more specific selector to locate the radio button for "Cash on Delivery"
        By radioButtonSelector = By.cssSelector("input[type='radio'][name='payment'][value='cod']");

        // Wait for the radio button to be clickable
        WebElement radioButton = wait.until(ExpectedConditions.elementToBeClickable(radioButtonSelector));

        // Click it
        radioButton.click();
    }
    
    
    // Step 9
    
    // Method to fulfill the payment form, confirm and pay
    public void fulfillPaymentForm(WebDriverWait wait) throws InterruptedException {
        
    	// Wait for 2 seconds as there is a delay in the process
        Thread.sleep(2000);
        
        // Other steps to fill the form can be included here if they were not fulfilled in the sign-up

        // Locate and wait for the "Confirm and pay" button to be clickable
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
