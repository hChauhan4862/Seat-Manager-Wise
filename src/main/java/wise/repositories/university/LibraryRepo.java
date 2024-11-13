package wise.repositories.university;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wise.entities.LibraryEntity;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface LibraryRepo extends JpaRepository<LibraryEntity, Integer> {

    boolean existsByLibName(String libName);
    
    boolean existsByLibCode(Integer libCode);
    
    boolean existsByLibNameAndLibCodeNot(String libName, Integer libCode);

    @Query(value = "SELECT (COALESCE(MAX(l.LIB_CODE), 200) + 1) FROM WN_LIBRARY l", nativeQuery = true)
    Integer getMaxId();
}
