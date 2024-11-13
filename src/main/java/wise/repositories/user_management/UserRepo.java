package wise.repositories.user_management;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import wise.entities.UserEntity;

@Repository
public interface UserRepo extends JpaRepository<UserEntity, String> {
    // Exists by user ID
    boolean existsByUserId(String userId);

    // Find by User ID
    Optional<UserEntity> findByUserId(String userId);

    // Get by user name
    Optional<UserEntity> findByUserName(String usernameString);

    // Exists by user name
    boolean existsByUserName(String usernameString);

    // Exists by school number and other than the given user ID (for update)
    boolean existsByUserSchoolNoAndUserIdNot(String userSchoolNo, String userId);

    @Query("SELECT (COALESCE(MAX(d.userSeq), 10000) + 1) FROM UserEntity d")
    Integer getMaxId();
}
