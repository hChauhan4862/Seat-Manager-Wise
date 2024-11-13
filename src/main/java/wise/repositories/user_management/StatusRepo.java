package wise.repositories.user_management;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import wise.entities.*;

@Repository
public interface StatusRepo extends JpaRepository<StatusEntity, String> {
    // Exists by status code
    boolean existsByStatusCode(String status_code);

    // Exists by status name
    boolean existsByStatusName(String status_name);

    // Exists by status name and other than the given status code (for update)
    boolean existsByStatusNameAndStatusCodeNot(String status_name, String status_code);
}
