package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Main {

    protected WebDriver driver;

    protected static WebDriverWait waitDriver;

    // List of products and their quantities
    protected static String[] products = {"Cucumber","Carrot","Tomato", "Mushroom"};
    protected static String promoCode="test";

    // WebElements
    protected WebElement addToCartButton,cartIcon,checkOutBtn,promoCodeTbox,promoBtn,promoInfo,placeOrderBtn;

    public Main() {
        // Initialize Edge browser
        driver = new EdgeDriver();
        waitDriver = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://rahulshettyacademy.com/seleniumPractise/#");
    }

    //Loops over a list of products to add each to the cart.
    public void addMultipleProductsToCart(String[] products) {
        for (int i = 0; i < products.length; i++) {
            try {
                // Locate the Add to Cart button using product name
                WebElement addToCartButton = driver.findElement(By.xpath("//h4[contains(text(), '" + products[i] + "')]/parent::div/div[@class='product-action']/button"));
                addToCartButton.click(); // Click the Add to Cart button
            } catch (NoSuchElementException e) {
                System.out.println("Product: " + products[i] + " is not found");
                // Optionally continue or log more details
            }
        }
     }

    public void checkOut(String promoCode){
        cartIcon = driver.findElement(By.xpath("//img[@alt='Cart']"));
        cartIcon.click();

        checkOutBtn = driver.findElement(By.xpath("//button[text()='PROCEED TO CHECKOUT']"));
        checkOutBtn.click();

        waitDriver.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".promoCode")));

        promoCodeTbox = driver.findElement(By.cssSelector(".promoCode"));
        promoCodeTbox.sendKeys(promoCode);

        promoBtn = driver.findElement(By.cssSelector(".promoBtn"));
        promoBtn.click();

        waitDriver.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".promoInfo")));

        promoInfo = driver.findElement(By.cssSelector(".promoInfo"));
        System.out.println("Code information message: " + promoInfo.getText());

        placeOrderBtn = driver.findElement(By.xpath("//button[text()='Place Order']"));
        placeOrderBtn.click();

    }

    public static void main(String[] args) {
        Main m = new Main();
        m.addMultipleProductsToCart(products);
        m.checkOut(promoCode);
    }
}
