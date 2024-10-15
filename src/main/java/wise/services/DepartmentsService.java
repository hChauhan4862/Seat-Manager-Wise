package wise.services;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wise.repository.DepartmentRepo;
import wise.models.DepartmentEntity;
import wise.common.CustomException;
import org.springframework.data.domain.Pageable;



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
    public Optional<DepartmentEntity> getByCode(String code) {
        return deptRepo.findById(code);
    }

    // ------------------------------------- Create a new department -------------------------------------
    @Transactional
    public DepartmentEntity create(DepartmentEntity deptEntity) {
        // Find Max Code 1,8 characters and increment by 1
        String newId = RandomStringUtils.randomAlphanumeric(10).toUpperCase();
        deptEntity.setDeptCode(newId);

        // Check if department already exists in the database
        if(deptRepo.existsByDeptName(deptEntity.getDeptName())) {
            throw new CustomException("departments.name.alreadyExists");
        }
        return deptRepo.save(deptEntity);
    }

    // ------------------------------------- Update a department by ID -------------------------------------
    public DepartmentEntity updateDepartment(String code, DepartmentEntity deptEntity) {
        return deptRepo.findById(code)
            .map(department -> {
                department.setDeptName(deptEntity.getDeptName());
                return deptRepo.save(department);
            })
            .orElseThrow(() -> new CustomException("departments.notFound"));
    }    

    // ------------------------------------- Delete a department by ID -------------------------------------
    @Transactional
    public boolean deleteDepartment(String code) {
        if(!deptRepo.existsById(code)) {
            return false;
        }

        deptRepo.deleteById(code);
        return true;
    }
}
