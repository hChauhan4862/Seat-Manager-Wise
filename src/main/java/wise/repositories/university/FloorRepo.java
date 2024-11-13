package wise.repositories.university;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wise.entities.FloorEntity;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface FloorRepo extends JpaRepository<FloorEntity, Integer> {

    boolean existsByFloorName(String floorName);

    boolean existsByFloorCode(Integer floorCode);
    
    boolean existsByFloorNameAndFloorCodeNot(String floorName, Integer floorCode);
    
    boolean existsByLibCode(Integer libCode);

    @Query( value = "SELECT (COALESCE(MAX(f.FLOOR_CODE), 300) + 1) FROM WN_FLOORS f", nativeQuery = true)
    Integer getMaxId();
}
