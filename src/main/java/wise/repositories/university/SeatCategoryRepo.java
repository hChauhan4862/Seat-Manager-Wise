package wise.repositories.university;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wise.entities.SeatCategoryEntity;
import wise.models.enums.SeatCategory;

import org.springframework.data.jpa.repository.Query;

@Repository
public interface SeatCategoryRepo extends JpaRepository<SeatCategoryEntity, Integer> {

    boolean existsBySeatCatCode(int seatCatCode);

    boolean existsBySeatCatName(SeatCategory seatCatName);

    @Query(value = "SELECT (COALESCE(MAX(s.SEATCAT_CODE), 900) + 1) FROM WN_SEAT_CATEGORY s", nativeQuery = true)
    Integer getMaxId();
}
