package wise.repositories.user_management;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import wise.entities.*;

@Repository
public interface StatusRepo extends JpaRepository<StatusEntity, String> {
    boolean existsByStatusName(String status_name);
    // Exists by status name and other than the given status code
    boolean existsByStatusNameAndStatusCodeNot(String status_name, String status_code);
}
