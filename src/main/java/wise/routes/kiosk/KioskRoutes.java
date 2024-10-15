package wise.routes.kiosk;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Kiosk", description = "Mobile-specific APIs")
@RequestMapping("/kiosk")
public class KioskRoutes {
    
}
