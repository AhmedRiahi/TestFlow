package com.talan.testflow.helper.locator.meta;

import lombok.Data;
import org.openqa.selenium.By;

import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlRootElement
public class DomElementLocator {

    private Locators type;
    private String value;
    private Integer priority;


    public By toSeleniumLocator(){
        switch (this.type){
            case ID: return By.id(this.value);
            case NAME: return By.name(this.value);
            case LINK_TEXT: return By.linkText(this.value);
            case CSS: return By.cssSelector(this.value);
            case XPATH: return By.xpath(this.value);
            default: throw new RuntimeException("Unknow selenium locator type :"+this.type);
        }
    }
}
