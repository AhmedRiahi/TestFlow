package com.talan.testflow.helper.locator.meta;

import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Data
@XmlRootElement
public class PageLocatorMeta {

    private String name;
    private List<DomElement> domElements;

    private PageLocatorMeta(){}


    public DomElement dom(String name){
        return this.domElements.stream().filter(domElement -> domElement.getName().equalsIgnoreCase(name)).findFirst().orElseThrow(() -> new RuntimeException("unknown Dom Element :"+name));
    }
}
