package wise.repositories.app;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import wise.entities.*;

@Repository
public interface CommonConfigRepo extends JpaRepository<CommonConfig, String> {
    // Exists by Config Key
    boolean existsByConfigKey(String configKey);

    // Find by config key
    CommonConfig findByConfigKey(String configKey);

    // Find by config key and active
    Optional<CommonConfig> findByConfigKeyAndActiveTrue(String configKey);

    @Query("SELECT c.configValue FROM CommonConfig c WHERE c.configKey = :configKey AND c.active = true")
    Optional<String> getConfig(@Param("configKey") String configKey);

    
}
