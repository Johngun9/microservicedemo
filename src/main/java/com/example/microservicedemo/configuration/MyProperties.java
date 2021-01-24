package com.example.microservicedemo.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.*;

@Configuration
@ConfigurationProperties("my")
public class MyProperties {
    private String sample;
    private List<String> listValues;
    private Map<String, String> mapValues;

    public String getSample() {
        return sample;
    }

    public void setSample(String sample) {
        this.sample = sample;
    }

    public List<String> getListValues() {
        return listValues;
    }

    public void setListValues(List<String> listValues) {
        this.listValues = listValues;
    }

    public Map<String, String> getMapValues() {
        return mapValues;
    }

    public void setMapValues(Map<String, String> mapValues) {
        this.mapValues = mapValues;
    }
}
