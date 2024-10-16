package wise.filters;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;
import wise.config.ApiKeyConfig;

@Component
public class ApiKeyFilter implements Filter {

    @Autowired
    private ApiKeyConfig apiKeyConfig;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization code if needed
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String ClientApiKey = httpRequest.getHeader("x-api-key");
        String requestURI = httpRequest.getRequestURI();

        Map<String, String> apiKeys = apiKeyConfig.getApiKeys();

        // Determine the correct API key based on the route group
        String expectedApiKey = null;
        Boolean needAPIKEY = false;
        if (requestURI.startsWith("/web/")) {
            expectedApiKey = apiKeys.get("web");
            needAPIKEY = true;
        } else if (requestURI.startsWith("/mobile/")) {
            expectedApiKey = apiKeys.get("mobile");
            needAPIKEY = true;
        } else if (requestURI.startsWith("/kiosk/")) {
            expectedApiKey = apiKeys.get("kiosk");
            needAPIKEY = true;
        }


        String masterApiKey = apiKeys.get("master");
        masterApiKey = (masterApiKey == null || masterApiKey == "") ? expectedApiKey : masterApiKey; // If master API key is not set, use the expected API key

        // Check if the API Key is Set in Config and matched with the request
        if ( 
            needAPIKEY 
            &&   
            !(
                // If the expected or master API key is not set or not required, allow all requests
                (expectedApiKey == null || expectedApiKey == "") && (masterApiKey == null || masterApiKey == "")
            ) 
            && (
                // If the client API key is not set or does not match the expected or master API key, return 404
                ClientApiKey == null 
                        || 
                (!ClientApiKey.equals(expectedApiKey) && !ClientApiKey.equals(masterApiKey))
                )
            ) {
            // httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            // httpResponse.getWriter().write("Invalid API Key");

            
            httpResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // Proceed with the request if API key matches
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // Cleanup code if needed
    }
}
