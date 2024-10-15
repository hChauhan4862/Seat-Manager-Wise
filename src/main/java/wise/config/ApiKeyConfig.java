package wise.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@RefreshScope  // Enables dynamic refresh of API key values
public class ApiKeyConfig {

    @Value("${wn.config.api.key.master:}")
    private String masterApiKey;

    @Value("${wn.config.api.key.web:}")
    private String webApiKey;

    @Value("${wn.config.api.key.mobile:}")
    private String mobileApiKey;

    @Value("${wn.config.api.key.kiosk:}")
    private String kioskApiKey;

    public Map<String, String> getApiKeys() {
        return Map.of(
                "master", masterApiKey,
                "web", webApiKey,
                "mobile", mobileApiKey,
                "kiosk", kioskApiKey
        );
    }
}
