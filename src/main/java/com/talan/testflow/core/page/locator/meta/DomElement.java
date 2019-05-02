package com.talan.testflow.core.page.locator.meta;

import lombok.Data;
import org.openqa.selenium.By;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


@Data
@XmlRootElement
public class DomElement {

    private String name;
    private List<DomElementLocator> locators;

    public DomElementLocator getPrimaryLocator(){
        Collections.sort(this.locators, Comparator.comparing(DomElementLocator::getPriority));
        return this.locators.get(0);
    }

    public By primaryBy(){
        return this.getPrimaryLocator().toSeleniumLocator();
    }

    public By secondaryBy(){
        return this.locators.get(1).toSeleniumLocator();
    }

    public boolean hasSecondaryLocator(){
        return this.locators.size() > 1;
    }
}
