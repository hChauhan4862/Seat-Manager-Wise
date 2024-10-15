package wise.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import wise.common.CustomException;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

@Service
public class ConfigurationService {

    @Value("${spring.profiles.active}")
    private String activeProfile;

    public Properties loadProperties() {
        String filePath = "C:/SpringConfig/application-" + activeProfile + ".properties";
        Properties properties = new Properties();
        try {
            properties.load(Files.newInputStream(Paths.get(filePath)));
        } catch (IOException e) {
            throw new CustomException("sysproperties.fileNotFound");
        }
        
        return properties;
    }

    public void updateProperty(String key, String value) {
        String filePath = "C:/SpringConfig/application-" + activeProfile + ".properties";
        Properties properties = loadProperties();
        properties.setProperty(key, value);

        try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
            properties.store(outputStream, null); // Save updated properties
        } 
        catch (IOException e) {
            throw new CustomException("sysproperties.updateFailed");
        }
    }
}
