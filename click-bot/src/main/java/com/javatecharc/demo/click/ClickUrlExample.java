package com.javatecharc.demo.click;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ClickUrlExample {
    public static void main(String[] args) {
        // Set the path to your ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\rajan\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");

        // Initialize the ChromeDriver
        WebDriver driver = new ChromeDriver();

        // Open the URL
        driver.get("https://javatecharc.com");

        // Find the element to click (e.g., a button)
        WebElement element = driver.findElement(By.linkText("Click Me"));

        // Click the element
        element.click();

        // Close the browser
        driver.quit();
    }
}
