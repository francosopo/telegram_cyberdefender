package org.config;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigService {

    private static ConfigService configService = null;
    private static Properties prop;

    private ConfigService() {
        try {
            String configPath = "src/config.properties";
            FileInputStream propsInput = new FileInputStream(configPath);
            prop = new Properties();
            prop.load(propsInput);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

    }

    public static ConfigService getConfigService() {
        if (ConfigService.configService == null) {
            ConfigService.configService = new ConfigService();
            return ConfigService.configService;
        }
        return ConfigService.configService;
    }

    public String getParameter(String key)
    {
        String resp = ConfigService.prop.getProperty(key);
        if (resp != null)
        {
            return  resp;
        }
        return "";
    }
}
