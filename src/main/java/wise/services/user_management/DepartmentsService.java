package wise.services.user_management;

import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wise.common.CustomException;
import wise.entities.DepartmentEntity;
import wise.repositories.user_management.DepartmentRepo;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;



@Service
public class DepartmentsService {

    private DepartmentRepo deptRepo;

    public DepartmentsService(DepartmentRepo deptRepo) {
        this.deptRepo = deptRepo;
    }

    // ------------------------------------- List all departments -------------------------------------
    @Transactional(readOnly = true)
    public List<DepartmentEntity> getAll() {
        return deptRepo.findAll();
    }

    // ------------------------------------- List all departments with pagination -------------------------------------
    @Transactional(readOnly = true)
    public Page<DepartmentEntity> getAllDepartments(Pageable pagable) {
        return deptRepo.findAll(pagable);
    }

    
    // ------------------------------------- Get department by ID -------------------------------------
    @Transactional(readOnly = true)
    public DepartmentEntity getByCode(String code) {
        return deptRepo.findById(code)
            .map(department -> {
                return department;
            })
            .orElseThrow(() -> new CustomException("departments.notFound", HttpStatus.NOT_FOUND));
    }

    // ------------------------------------- Create a new department -------------------------------------
    @Transactional
    public DepartmentEntity create(DepartmentEntity deptEntity) {
        // Find Max Code 1,8 characters and increment by 1

        if (deptEntity.getDeptCode() != null && deptRepo.existsByDeptCode(deptEntity.getDeptCode())) {
            throw new CustomException("departments.code.alreadyExists", HttpStatus.CONFLICT);
        } else if (deptEntity.getDeptCode() == null) {
            deptEntity.setDeptCode(RandomStringUtils.randomAlphanumeric(10).toUpperCase());
        }
        
        // Check if department already exists in the database
        if(deptRepo.existsByDeptName(deptEntity.getDeptName())) {
            throw new CustomException("departments.name.alreadyExists", HttpStatus.CONFLICT);
        }
        return deptRepo.save(deptEntity);
    }

    // ------------------------------------- Update a department by ID -------------------------------------
    public DepartmentEntity updateDepartment(DepartmentEntity deptEntity) {

        if(deptRepo.existsByDeptNameAndDeptCodeNot(deptEntity.getDeptName(), deptEntity.getDeptCode())) {
            throw new CustomException("departments.name.alreadyExistsForAnotherCode", HttpStatus.CONFLICT);
        }

        return deptRepo.save(deptEntity);
    }

    // ------------------------------------- Delete a department by ID -------------------------------------
    @Transactional
    public boolean deleteDepartment(String code) {
        if(!deptRepo.existsById(code)) {
            throw new CustomException("departments.notFound", HttpStatus.NOT_FOUND);
        }

        deptRepo.deleteById(code);
        return true;
    }
}
