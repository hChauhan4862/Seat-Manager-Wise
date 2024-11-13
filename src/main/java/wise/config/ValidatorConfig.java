package wise.config;

import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import wise.filters.CustomMessageInterpolator;

@Configuration
public class ValidatorConfig {

    private final CustomMessageInterpolator customMessageInterpolator;

    public ValidatorConfig(CustomMessageInterpolator customMessageInterpolator) {
        this.customMessageInterpolator = customMessageInterpolator;
    }

    @Bean
    public LocalValidatorFactoryBean validator() {
        LocalValidatorFactoryBean validatorFactoryBean = new LocalValidatorFactoryBean();
        validatorFactoryBean.setProviderClass(HibernateValidator.class);
        validatorFactoryBean.setMessageInterpolator(customMessageInterpolator);  // Set custom interpolator
        return validatorFactoryBean;
    }
}
