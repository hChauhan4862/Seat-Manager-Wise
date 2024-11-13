package wise.repositories.university;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wise.entities.ZoneEntity;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface ZoneRepo extends JpaRepository<ZoneEntity, Integer> {

    boolean existsByZoneName(String zoneName);

    boolean existsByZoneCode(int zoneCode);

    @Query(value = "SELECT (COALESCE(MAX(z.ZONE_CODE), 800) + 1) FROM WN_ZONES z", nativeQuery = true)
    Integer getMaxId();
}
