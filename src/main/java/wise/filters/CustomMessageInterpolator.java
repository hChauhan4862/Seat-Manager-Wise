package wise.filters;

import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class CustomMessageInterpolator extends ResourceBundleMessageInterpolator {

    private final MessageSource messageSource;
    private static final Pattern GROUP_PATTERN = Pattern.compile("#([^#]+)#");

    public CustomMessageInterpolator(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    public String interpolate(String messageTemplate, Context context, Locale locale) {

        // if messageTemplate starts with { and ends with }, then remove them
        if (messageTemplate.startsWith("{") && messageTemplate.endsWith("}")) {
            // Assume the message is a key in the message source
            messageTemplate = messageTemplate.substring(1, messageTemplate.length() - 1);
            messageTemplate = messageSource.getMessage(messageTemplate, null, messageTemplate, locale);

            // if any #group# is found, replace it with {group}
            messageTemplate = replaceGroupPlaceholders(messageTemplate);

        }

        messageTemplate = super.interpolate(messageTemplate, context, locale);
        

        // Now, resolve any placeholders like {departments.name} recursively using
        return messageTemplate;
    }


    private String replaceGroupPlaceholders(String messageTemplate) {

        Matcher matcher = GROUP_PATTERN.matcher(messageTemplate);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            String groupName = matcher.group(1); // Extract group name

            // translate the group name to the message
            String m = messageSource.getMessage(groupName, null, groupName, Locale.getDefault());
            if (m == null || m.equals(groupName)) {
                matcher.appendReplacement(sb, "{" + groupName + "}"); // Replace #group# with {group} if no message found
            } else {
                // Boolean A = GROUP_PATTERN.matcher(m).find();
                if (GROUP_PATTERN.matcher(m).find()) {
                    if (GROUP_PATTERN.matcher(m).group(0).equals(m)){
                        m = m.substring(1, m.length() - 1);
                    }
                    
                    m = replaceGroupPlaceholders(m);
                }
                matcher.appendReplacement(sb, m); // Replace #group# with the message
            }

        }
        matcher.appendTail(sb);
        return sb.toString();
    }
}
