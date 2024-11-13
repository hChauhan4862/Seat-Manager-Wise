package wise.services.university;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import wise.common.CustomException;
import wise.entities.ScheduleEntity;
import wise.repositories.university.ScheduleRepo;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepo scheduleRepo;

    // ------------------------------------- List all schedules -------------------------------------
    @Transactional(readOnly = true)
    public Page<ScheduleEntity> getAllSchedules(Pageable pageable) {
        return scheduleRepo.findAll(pageable);
    }

    // ------------------------------------- Get schedule by ID -------------------------------------
    @Transactional(readOnly = true)
    public ScheduleEntity getScheduleById(Integer scheduleCode) {
        return scheduleRepo.findById(scheduleCode)
                .orElseThrow(() -> new CustomException("schedule.notFound", HttpStatus.NOT_FOUND));
    }

    // ------------------------------------- Create a new schedule -------------------------------------
    @Transactional
    public ScheduleEntity createSchedule(ScheduleEntity scheduleEntity) {
        // Check if the schedule dates conflict with any existing schedules
        boolean isConflicting = scheduleRepo.existsConflictingSchedule(scheduleEntity.getStartDate(), scheduleEntity.getEndDate());
        if (isConflicting) {
            throw new CustomException("schedule.dates.conflict", HttpStatus.CONFLICT);
        }

        // Set the schedule code (assuming a method to get the next available schedule code)
        Integer maxId = scheduleRepo.getMaxId();
        scheduleEntity.setScheduleCode(maxId);

        // Save and return the newly created schedule
        return scheduleRepo.save(scheduleEntity);
    }

    // ------------------------------------- Update an existing schedule -------------------------------------
    @Transactional
    public ScheduleEntity updateSchedule(ScheduleEntity scheduleEntity) {
        // Check if the schedule dates conflict with any existing schedules
        boolean isConflicting = scheduleRepo.existsConflictingSchedule(scheduleEntity.getStartDate(), scheduleEntity.getEndDate());
        if (isConflicting) {
            throw new CustomException("schedule.dates.conflict", HttpStatus.CONFLICT);
        }

        // Save and return the updated schedule
        return scheduleRepo.save(scheduleEntity);
    }

    // ------------------------------------- Delete a schedule by ID -------------------------------------
    @Transactional
    public boolean deleteSchedule(Integer scheduleCode) {
        if (!scheduleRepo.existsById(scheduleCode)) {
            throw new CustomException("schedule.notFound", HttpStatus.NOT_FOUND);
        }
        scheduleRepo.deleteById(scheduleCode);
        return true;
    }
}
