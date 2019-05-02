package com.talan.testflow.page;


import com.talan.testflow.helper.locator.PageLocator;
import com.talan.testflow.helper.locator.meta.PageLocatorMeta;
import lombok.Data;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@Data
public abstract class AbstractPageController {

    protected WebDriver webDriver;
    private PageLocator pageLocator;
    private PageLocatorMeta pageLocatorMeta;

    public AbstractPageController(WebDriver webDriver,PageLocatorMeta pageLocatorMeta){
        this.pageLocatorMeta = pageLocatorMeta;
        this.webDriver = webDriver;
        this.pageLocator = new PageLocator(this.pageLocatorMeta,this.webDriver);
    }

    public AbstractPageController(PageLocatorMeta pageLocatorMeta){
        this.pageLocatorMeta = pageLocatorMeta;
    }


    public WebElement locate(String elementName){
       return this.pageLocator.locate(elementName);
    }


    public void setWebDriver(WebDriver webDriver){
        this.webDriver = webDriver;
        this.pageLocator = new PageLocator(this.pageLocatorMeta,this.webDriver);
    }
}
