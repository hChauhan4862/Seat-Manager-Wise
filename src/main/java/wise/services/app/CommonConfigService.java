package wise.services.app;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import wise.common.CustomException;
import wise.entities.CommonConfig;
import wise.entities.FloorEntity;
import wise.repositories.app.CommonConfigRepo;


@Service
@RequiredArgsConstructor
public class CommonConfigService {

    private final CommonConfigRepo commonConfigRepo;

    @Transactional(readOnly = true)
    public String getConfig(String key) {
        return commonConfigRepo.getConfig(key).orElse("");
    }

    @Transactional(readOnly = true)
    public String getConfig(String key, String Default) {
        return commonConfigRepo.getConfig(key).orElse(Default);
    }

    @Transactional(readOnly = true)
    public String getConfigStrict(String key) {
        return commonConfigRepo.getConfig(key)
        .orElseThrow(() -> new CustomException("commonconfig.notFound", HttpStatus.NOT_FOUND));
    }
}
