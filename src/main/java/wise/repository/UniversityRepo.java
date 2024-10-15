package wise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import wise.models.*;

@Repository
public interface UniversityRepo extends JpaRepository<UniversityEntity, Integer> {
    boolean existsByUniversityName(String UNIVERSITY_NAME);
    // Exists by department name and other than the given department code
    boolean existsByUniversityNameAndUniversityCodeNot(String UNIVERSITY_NAME, Integer UNIVERSITY_CODE);

    // Find max id
    @Query("SELECT (COALESCE(MAX(d.universityCode), 100) + 1) FROM UniversityEntity d")
    Integer findMaxId();
}
