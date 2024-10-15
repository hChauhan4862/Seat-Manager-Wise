package wise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wise.models.*;

@Repository
public interface DepartmentRepo extends JpaRepository<DepartmentEntity, String> {
    boolean existsByDeptName(String dept_name);
    // Exists by department name and other than the given department code
    boolean existsByDeptNameAndDeptCodeNot(String dept_name, String dept_code);
}
