package org.example;

import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Main {

    protected WebDriver driver;

    protected static WebDriverWait waitDriver;

    // List of products and their quantities
    protected static String[] products = {"mixture","Cucumber","  ","Carrot","hh","Tomato","Mushro","ghgh"};
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
        List <WebElement> productsList = driver.findElements(By.cssSelector(".product-name"));
        boolean productIsFound;
        for (int j=0 ; j<products.length ; j++){
            productIsFound=false;
            if((products[j].length()<3)||(products[j].isBlank())){
                System.out.printf("Product: (%s) is invalid\n",products[j]);
                continue;
            }
            for(int i=0 ; i<productsList.size() ; i++){
                String productName = productsList.get(i).getText().toLowerCase();
                if(productName.contains(products[j].toLowerCase())){
                    ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");
                    addToCartButton = driver.findElement(By.xpath(String.format("(//div[@class='product-action'])[%d]//button",i+1)));
                    waitDriver.until(ExpectedConditions.elementToBeClickable(addToCartButton)).click();
                    productIsFound=true;
                    break;
                }
            }
            if (!productIsFound){
                System.out.printf("Product: (%s) is not found\n",products[j]);
            }
        }
    }

//    public void checkOut(String promoCode){
//        cartIcon = driver.findElement(By.xpath("//img[@alt='Cart']"));
//        cartIcon.click();
//
//        checkOutBtn = driver.findElement(By.xpath("//button[text()='PROCEED TO CHECKOUT']"));
//        checkOutBtn.click();
//
//        waitDriver.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".promoCode")));
//
//        promoCodeTbox = driver.findElement(By.cssSelector(".promoCode"));
//        promoCodeTbox.sendKeys(promoCode);
//
//        promoBtn = driver.findElement(By.cssSelector(".promoBtn"));
//        promoBtn.click();
//
//        waitDriver.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".promoInfo")));
//
//        promoInfo = driver.findElement(By.cssSelector(".promoInfo"));
//        System.out.println("Code information message: " + promoInfo.getText());
//
//        placeOrderBtn = driver.findElement(By.xpath("//button[text()='Place Order']"));
//        placeOrderBtn.click();
//
//    }

    public static void main(String[] args) {
        Main m = new Main();
        m.addMultipleProductsToCart(products);
       // m.checkOut(promoCode);
    }
}
