package wise.repositories.user_management;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import wise.entities.*;

@Repository
public interface PositionRepo extends JpaRepository<PositionEntity, String> {
    // Exists by position code
    boolean existsByPosCode(String pos_code);

    // Exists by position name
    boolean existsByPosName(String pos_name);

    // Exists by position name and other than the given position code (for update)
    boolean existsByPosNameAndPosCodeNot(String pos_name, String pos_code);
}
