package wise.repositories.university;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wise.entities.ScheduleEntity;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface ScheduleRepo extends JpaRepository<ScheduleEntity, Integer> {

    // Check if a schedule with the given start date and end date already exists
    boolean existsByStartDateAndEndDate(String startDate, String endDate);

    // Check if there is any schedule with the same start date that overlaps with the given start and end dates
    @Query("SELECT COUNT(s) > 0 FROM ScheduleEntity s WHERE " +
            "(s.startDate BETWEEN :startDate AND :endDate) OR " +
            "(s.endDate BETWEEN :startDate AND :endDate)")
    boolean existsConflictingSchedule(String startDate, String endDate);

    boolean existsByScheduleTypeCode(Integer scheduleTypeCode);


    @Query( value = "SELECT (COALESCE(MAX(s.SCHEDULE_CODE), 2000) + 1) FROM WN_SCHEDULES s", nativeQuery = true)
    Integer getMaxId();
}
