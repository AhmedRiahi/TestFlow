package com.talan.testflow.page.locator;


import com.talan.testflow.page.locator.meta.PageLocatorMeta;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@AllArgsConstructor
@Slf4j
public class PageLocator {

    private PageLocatorMeta pageLocatorMeta;
    protected WebDriver webDriver;

    public WebElement locate(String elementName){
        WebElement webElement = null;
        try{
            webElement = this.webDriver.findElement(this.pageLocatorMeta.dom(elementName).primaryBy());
        }catch(NoSuchElementException e){
            if(this.pageLocatorMeta.dom(elementName).hasSecondaryLocator()){
                try {
                    webElement = this.webDriver.findElement(this.pageLocatorMeta.dom(elementName).secondaryBy());
                }catch(NoSuchElementException e1){
                    log.error("Element "+elementName+" not found");
                    log.info("Waiting for Element presence");
                    webElement = (new WebDriverWait(this.webDriver, 20)).until(ExpectedConditions.presenceOfElementLocated(this.pageLocatorMeta.dom(elementName).primaryBy()));
                }
            }else{
                log.error("Element "+elementName+" not found");
                log.info("Waiting for Element presence");
                webElement = (new WebDriverWait(this.webDriver, 20)).until(ExpectedConditions.presenceOfElementLocated(this.pageLocatorMeta.dom(elementName).primaryBy()));
            }

        }
        return webElement;
    }
}
