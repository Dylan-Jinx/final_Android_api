package com.finalproj.devapp.api.utils;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.io.ClassPathResource;

import java.util.Properties;

public class YamlUtils {

    public static String getStringByYaml(String key){
        YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
        yaml.setResources(new ClassPathResource("application.yml"));
        Properties properties = yaml.getObject();
        assert properties != null;
        return (String) properties.get(key);
    }
}