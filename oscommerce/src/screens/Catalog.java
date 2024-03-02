package screens;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.interactions.Actions;

public class Catalog {
	
	public WebDriver driver;
	
	public Catalog (WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	// Step 1: Go to website
	
	// Method to navigate to a URL
    public void enterSite(String url, WebDriverWait wait) {
    	
    	// WebDriver navigates to the specified URL
        driver.get(url); 
    }
    
    // Step 2: Select the product indicated
    
    // Method to click on a specific product with a specific name
    public void clickProduct(WebDriverWait wait, String productName) {

        // Define dynamically the element for the product link and wait for the product link to be clickable
        WebElement productElement = wait.until(ExpectedConditions.elementToBeClickable(By.linkText(productName)));

        // Click it
        productElement.click();
    }
    
    // Step 3: Add to Cart
    
    // Method to wait for and click the "Add to Cart" button
    public void clickAddToCartButton(WebDriverWait wait) {

        // Locate the button and wait for it to be clickable
    	WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".btn-2.add-to-cart")));

        // Click it
        addToCartButton.click();
    }
    
    // Step 4: Update quantity
    
    // Method to handle pop-up and click the element to add specified quantity of the product
    public void addProductInPopup(WebDriverWait wait, int quantity) throws InterruptedException {
    	
        // Wait for a few seconds before checking for the pop-up as it has delay
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
    
    // Step 5: Assert that quantity
    
    // Method to assert the quantity from the input form
    public int getVisibleQuantity(WebDriverWait wait) {
    	
        // Locate the input form and wait for it to be visible
        WebElement quantityInputElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".qty-inp-s")));

        // Retrieve the value attribute from the input form element as a string as retrieving an integer is not possible
        String quantityValue = quantityInputElement.getAttribute("value");
        
        // Convert the string value to an integer in order to be asserted in Test class
        return Integer.parseInt(quantityValue);
    }
    
    // Step 6: Checkout
    
    // Method to close the pop-up form
    public void closePopUp(WebDriverWait wait) throws InterruptedException {
    	
    	// Wait for 2 seconds as the process has some delay
        Thread.sleep(2000);
        
        // Close the pop-up form by clicking the "Continue shopping" button
        WebElement continueShoppingButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#cart-form > .buttons > .left-buttons > .btn")));
        
        // Click it
        continueShoppingButton.click();
        
    }
    
    // Method to hover over "Shopping Cart", click the "Checkout" button and continue as "Guest"
    public void hoverAndClickCart(WebDriverWait wait) throws InterruptedException {
        
    	// Wait for 2 seconds as the process has some delay
        Thread.sleep(2000);
        
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
        
        // Click the 'Continue as guest' button
        WebElement continueAsGuestButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#box-1770 > .btn-2")));
        
        // Click it
        continueAsGuestButton.click();
    }
    
    // Step 7: Select “Cash on delivery” payment method
    
    // Method to click the radio button for payment method
    public void selectPaymentMethod(WebDriverWait wait) {

        // Locate the radio button for "Cash on Delivery" and wait for the radio button to be clickable
        WebElement radioButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[type='radio'][name='payment'][value='cod']")));

        // Click it
        radioButton.click();
    }
    
    // Step 8: Complete the payment
    
    // Method to fulfill the payment form, confirm and pay
    public void fillPaymentForm(WebDriverWait wait, String firstName, String lastName, String streetAddress, String postcode, String city, String email) throws InterruptedException {
        
    	// Wait for 2 seconds as there is a delay in the process
        Thread.sleep(2000);
        
        // Wait for the input fields to be visible and fill them with the provided data
        WebElement firstNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("shipping_address-firstname")));
        firstNameField.sendKeys(firstName);
        
        WebElement lastNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("shipping_address-lastname")));
        lastNameField.sendKeys(lastName);
       
        WebElement streetField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("shipping_address-street_address")));
        streetField.sendKeys(streetAddress);
        
        WebElement postField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("shipping_address-postcode")));
        postField.sendKeys(postcode);
        
        WebElement cityField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("shipping_address-city")));
        cityField.sendKeys(city);
        
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("checkout-email_address")));
        emailField.sendKeys(email);
        
        // Locate the radio button for "Cash on Delivery" as it is being modified, wait for the radio button to be clickable and click it
        WebElement radioButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[type='radio'][name='payment'][value='cod']")));
        radioButton.click();
        
        // Locate the "Terms" checkbox, wait for it to be clickable and click it
        WebElement termsCheckbox = wait.until(ExpectedConditions.elementToBeClickable(By.id("checkout-terms")));
        termsCheckbox.click();
        
        // Locate the "Confirm and pay" button, wait for it to be clickable and click it
        WebElement confirmAndPayButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".btn-2.btn-next")));
        confirmAndPayButton.click();
    }
    
    // Step 9: Check the success message
    
    // Method to verify the order confirmation message
    public boolean isOrderConfirmationDisplayed() throws InterruptedException {
    	
    	// Wait for 10 seconds as there is a delay in the process
        Thread.sleep(10000);
    	
    	// Retrieve the HTML source of the page using the driver already initialized
        String pageSource = driver.getPageSource();
        
        // Check if the page source contains the specific text and return boolean
        return pageSource.contains("We've received your order");
    }

}
