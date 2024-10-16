package wise.repositories.user_management;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import wise.entities.*;

@Repository
public interface PositionRepo extends JpaRepository<PositionEntity, String> {
    boolean existsByPosName(String pos_name);
    // Exists by position name and other than the given position code
    boolean existsByPosNameAndPosCodeNot(String pos_name, String pos_code);
}
