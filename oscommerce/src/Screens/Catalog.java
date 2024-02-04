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
    
    // Method to click on a specific product
    public void clickProduct(WebDriverWait wait) {
    	
        // Define the CSS selector for the product link
        By productSelector = By.linkText("Royal London 41003-03"); 

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
    public void addProductInPopup(WebDriverWait wait) throws InterruptedException {
    	
        // Wait for a few seconds before checking for the popup as it has delay
        Thread.sleep(4000);

        // Define the CSS selector of the popup and wait for it to be visible
        By popupSelector = By.id("cart-form");
        wait.until(ExpectedConditions.visibilityOfElementLocated(popupSelector));

        // Define the CSS selector of the element, locate and click it
        By addProductSelector = By.cssSelector("div.qty span.bigger");
        WebElement addProductElement = driver.findElement(addProductSelector);
        addProductElement.click();
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
    
 // Method to hover "Shopping Cart" and click the "Checkout" button after login
    public void hoverAndClickCart(WebDriverWait wait) {
        
    	// Create an instance of Actions class to perform complex mouse actions
        Actions actions = new Actions(driver);

        // Hover over the "Shopping Cart" button
        WebElement cartBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cart-box")));
        actions.moveToElement(cartBox).build().perform();

        // Wait for the "Checkout" button to be clickable after hover action
        WebElement checkoutButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#cart-box .right-buttons a.btn")));
        
        // Click the button
        checkoutButton.click();
        
    }
    
    
    // Step 8
    
    // Method to click the radio button for payment method
    
    public void selectPaymentMethod(WebDriverWait wait) {
        
    	// Use a more specific selector to locate the radio button for "Cash on Delivery"
        By radioButtonSelector = By.cssSelector("input[type='radio'][name='payment'][value='cod']");

        // Wait for the radio button to be clickable
        WebElement radioButton = wait.until(ExpectedConditions.elementToBeClickable(radioButtonSelector));

        // Click the radio button
        radioButton.click();
    }
    
    
    // Step 9
    
    // Method to fulfill the payment form, confirm and pay
    
    public void fulfillPaymentForm(WebDriverWait wait) throws InterruptedException {
        
    	// Wait for 2 seconds before checking the quantity has been updated
        Thread.sleep(2000);
        
        // Other steps to fill the form can be included here

        // Use WebDriverWait to wait for the "Confirm and pay" button to be clickable
        WebElement confirmAndPayButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("box-209382")));

        // Click the “Confirm and pay” button after ensuring it's clickable
        confirmAndPayButton.click();
    }
    
    
    // Step 10
    
    // Method to verify the order confirmation message
    public boolean isOrderConfirmationDisplayed() {
        // Directly use 'driver', which is already a member of 'Catalog'
        String pageSource = driver.getPageSource();
        return pageSource.contains("We've received your order");
    }


    
	
	/*
	
	// Step 1: Method to navigate to a URL
    public void enterSite(String url, WebDriverWait wait) {
        driver.get(url); // WebDriver navigates to the specified URL
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
	    WebElement checkoutButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".popup-box-wrap .btn-2")));
	    checkoutButton.click();

	    // Wait for a specific element on the new page to appear
	    By uniqueElementOnNextPage = By.id("uniqueElementId"); // Replace with an actual unique identifier
	    wait.until(ExpectedConditions.visibilityOfElementLocated(uniqueElementOnNextPage));
	}
	
	/*
	public void checkout(WebDriverWait wait) {
	    // Assuming the 'btn-2' button is uniquely identifiable within the popup
	    WebElement checkoutButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".popup-box-wrap .btn-2")));
	    wait.until(ExpectedConditions.elementToBeClickable(checkoutButton));
	    checkoutButton.click();
	}
	*/
	
	// Step 7
	
	/* This works
	
	public void navigateToAccountPage(WebDriverWait wait) {
	    // Wait for the "My account" button to be clickable and click it
	    WebElement myAccountButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("box-210238")));
	    myAccountButton.click();

	    // Wait for a specific element on the login page to ensure it's loaded
	    // Example: By.id("login-email_address")
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-email_address")));
	}

	public void login(String email, String password, WebDriverWait wait) {
	    WebElement emailInput = driver.findElement(By.id("login-email_address"));
	    WebElement passwordInput = driver.findElement(By.id("login-password"));
	    WebElement loginButton = driver.findElement(By.cssSelector(".btn-2"));

	    emailInput.sendKeys(email);
	    passwordInput.sendKeys(password);
	    loginButton.click();
	}
	
	/* 
	// Step 7
	
	public void navigateToAccountPage(WebDriverWait wait) {
	    // Wait for the page to be fully loaded
	    wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));

	    // Retry mechanism for clicking the button
	    final By myAccountSelector = By.id("box-210238");
	    final int MAX_ATTEMPTS = 3;
	    for (int attempts = 0; attempts < MAX_ATTEMPTS; attempts++) {
	        try {
	            WebElement myAccountButton = wait.until(ExpectedConditions.elementToBeClickable(myAccountSelector));
	            myAccountButton.click();
	            break; // Break the loop if click is successful
	        } catch (ElementClickInterceptedException e) {
	            if (attempts == MAX_ATTEMPTS - 1) {
	                throw e; // Rethrow the exception if max attempts are reached
	            }
	            // Optionally, add a small delay here if needed
	        }
	    }
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
