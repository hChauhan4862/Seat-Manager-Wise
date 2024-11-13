package wise.repositories.university;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import wise.entities.UniversityEntity;

import org.springframework.data.jpa.repository.Query;

@Repository
public interface UniversityRepo extends JpaRepository<UniversityEntity, Integer> {
    boolean existsByUnivName(String univ_name);
    boolean existsByUnivNameAndUnivCodeNot(String univ_name, Integer univ_code);


    @Query(value = "SELECT (COALESCE(MAX(s.UNIV_CODE), 1000) + 1) FROM WN_UNIVERSITY s", nativeQuery = true)
    Integer getMaxId();
}
