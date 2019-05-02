package com.talan.testflow.core.page.locator;


import com.talan.testflow.core.helper.PropertiesManager;
import com.talan.testflow.core.page.locator.meta.PageLocatorMeta;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PageLocatorsParser {

    private final static PageLocatorsParser instance = new PageLocatorsParser();
    private Unmarshaller unmarshaller;
    private Map<String, PageLocatorMeta> pageLocatorMap;


    public static PageLocatorsParser getInstance(){
        return PageLocatorsParser.instance;
    }

    private PageLocatorsParser(){
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(PageLocatorMeta.class);
            this.unmarshaller = jaxbContext.createUnmarshaller();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public void parsePageLocators(){
        List<File> locatorsFiles = this.loadLocatorsFiles();
        List<PageLocatorMeta> pageLocatorMetas = locatorsFiles.stream().map(file -> {
            try {
               return (PageLocatorMeta)this.unmarshaller.unmarshal(file);
            } catch (JAXBException e) {
                e.printStackTrace();
            }
            return null;
        }).collect(Collectors.toList());

        this. pageLocatorMap = pageLocatorMetas.stream().collect(Collectors.toMap(PageLocatorMeta::getName, Function.identity()));
    }

    private List<File> loadLocatorsFiles(){
        try {
            List<Path> locatorsPaths = Files.list(Paths.get(PropertiesManager.getInstance().getPropertyValue("locators.path"))).filter(file -> file.getFileName().toString().endsWith(".xml")).collect(Collectors.toList());
            return locatorsPaths.stream().map(Path::toFile).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public PageLocatorMeta getPageLocatorByName(String name){
        if(!this.pageLocatorMap.containsKey(name)) throw new RuntimeException("Page locator not found : "+name);
        return this.pageLocatorMap.get(name);
    }
}
