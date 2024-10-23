package wise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.data.web.config.EnableSpringDataWebSupport.PageSerializationMode;


@SpringBootApplication
@EnableSpringDataWebSupport(pageSerializationMode = PageSerializationMode.VIA_DTO)
public class EGovBootApplication {
    public static void main(String[] args) {

        System.out.println("##### EgovBootApplication Start #####");
        SpringApplication.run(EGovBootApplication.class, args);
    }
}
