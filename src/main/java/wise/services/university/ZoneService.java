package wise.services.university;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wise.common.CustomException;
import wise.entities.ZoneEntity;
import wise.repositories.university.ZoneRepo;
import org.springframework.http.HttpStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class ZoneService {

    private final ZoneRepo zoneRepo;

    public ZoneService(ZoneRepo zoneRepo) {
        this.zoneRepo = zoneRepo;
    }

    // ------------------------------------- List all zones -------------------------------------
    @Transactional(readOnly = true)
    public Page<ZoneEntity> getAllZones(Pageable pageable) {
        return zoneRepo.findAll(pageable);
    }

    // ------------------------------------- Get zone by code -------------------------------------
    @Transactional(readOnly = true)
    public ZoneEntity getZoneByCode(int zoneCode) {
        return zoneRepo.findById(zoneCode)
                .orElseThrow(() -> new CustomException("zone.notFound", HttpStatus.NOT_FOUND));
    }

    // ------------------------------------- Create a new zone -------------------------------------
    @Transactional
    public ZoneEntity createZone(ZoneEntity zoneEntity) {
        if (zoneRepo.existsByZoneCode(zoneEntity.getZoneCode())) {
            throw new CustomException("zone.code.alreadyExists", HttpStatus.CONFLICT);
        }

        if (zoneRepo.existsByZoneName(zoneEntity.getZoneName())) {
            throw new CustomException("zone.name.alreadyExists", HttpStatus.CONFLICT);
        }

        Integer maxId = zoneRepo.getMaxId();
        zoneEntity.setZoneCode(maxId);

        return zoneRepo.save(zoneEntity);
    }

    // ------------------------------------- Update zone -------------------------------------
    @Transactional
    public ZoneEntity updateZone(ZoneEntity zoneEntity) {

        return zoneRepo.save(zoneEntity);
    }

    // ------------------------------------- Delete zone by code -------------------------------------
    @Transactional
    public boolean deleteZone(int zoneCode) {
        if (!zoneRepo.existsByZoneCode(zoneCode)) {
            throw new CustomException("zone.notFound", HttpStatus.NOT_FOUND);
        }
        zoneRepo.deleteById(zoneCode);
        return true;
    }
}
