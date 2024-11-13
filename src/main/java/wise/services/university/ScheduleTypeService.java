package wise.services.university;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wise.common.CustomException;
import wise.entities.ScheduleTypeEntity;
import wise.repositories.university.ScheduleTypeRepo;
import org.springframework.http.HttpStatus;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class ScheduleTypeService {

    private final ScheduleTypeRepo scheduleTypeRepo;

    public ScheduleTypeService(ScheduleTypeRepo scheduleTypeRepo) {
        this.scheduleTypeRepo = scheduleTypeRepo;
    }

    // ------------------------------------- List all schedule types
    // -------------------------------------
    @Transactional(readOnly = true)
    public Page<ScheduleTypeEntity> getAllScheduleTypes(Pageable pageable) {
        return scheduleTypeRepo.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public List<ScheduleTypeEntity> getAllScheduleTypes() {
        return scheduleTypeRepo.findAll();
    }

    // ------------------------------------- Get schedule type by code
    // -------------------------------------
    @Transactional(readOnly = true)
    public ScheduleTypeEntity getScheduleTypeByCode(int scheduleTypeCode) {
        return scheduleTypeRepo.findById(scheduleTypeCode)
                .orElseThrow(() -> new CustomException("scheduleType.notFound", HttpStatus.NOT_FOUND));
    }

    @Transactional(readOnly = true)
    public ScheduleTypeEntity getByScheduleTypeName(String scheduleType) {
        return scheduleTypeRepo.findByScheduleTypeName(scheduleType)
                .orElseThrow(() -> new CustomException("scheduleTypeName.notFound", HttpStatus.NOT_FOUND));
    }

    // ------------------------------------- Create a new schedule type
    // -------------------------------------
    @Transactional
    public ScheduleTypeEntity createScheduleType(ScheduleTypeEntity scheduleTypeEntity) {
        if (scheduleTypeRepo.existsByScheduleTypeCode(scheduleTypeEntity.getScheduleTypeCode())) {
            throw new CustomException("scheduleType.code.alreadyExists", HttpStatus.CONFLICT);
        }

        Integer maxId = scheduleTypeRepo.getMaxId();
        scheduleTypeEntity.setScheduleTypeCode(maxId);

        return scheduleTypeRepo.save(scheduleTypeEntity);
    }

    // ------------------------------------- Update schedule type
    // -------------------------------------
    @Transactional
    public ScheduleTypeEntity updateScheduleType(ScheduleTypeEntity scheduleTypeEntity) {
        return scheduleTypeRepo.save(scheduleTypeEntity);
    }

    // ------------------------------------- Delete schedule type by code
    // -------------------------------------
    @Transactional
    public boolean deleteScheduleType(int scheduleTypeCode) {
        if (!scheduleTypeRepo.existsByScheduleTypeCode(scheduleTypeCode)) {
            throw new CustomException("scheduleType.notFound", HttpStatus.NOT_FOUND);
        }
        scheduleTypeRepo.deleteById(scheduleTypeCode);
        return true;
    }
}
