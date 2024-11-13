package wise.repositories.university;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wise.entities.ScheduleTypeEntity;

import org.springframework.data.jpa.repository.Query;

@Repository
public interface ScheduleTypeRepo extends JpaRepository<ScheduleTypeEntity, Integer> {

    boolean existsByScheduleTypeCode(int scheduleTypeCode);

    boolean existsByScheduleTypeName(String scheduleTypeName);
    
    Optional<ScheduleTypeEntity> findByScheduleTypeName(String scheduleTypeName);     

    @Query(value = "SELECT (COALESCE(MAX(s.SCHEDULE_TYPE_CODE), 700) + 1) FROM WN_SCHEDULE_TYPE s", nativeQuery = true)
    Integer getMaxId();
}
