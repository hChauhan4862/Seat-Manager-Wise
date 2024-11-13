package wise.services.user_management;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import wise.common.CustomException;
import wise.entities.*;
import wise.repositories.app.CommonConfigRepo;
import wise.repositories.user_management.*;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;

@Service
@Slf4j
public class UsersService {

    private final UserRepo userRepo;
    private final DepartmentRepo departmentRepo;
    private final PositionRepo positionRepo;
    private final StatusRepo statusRepo;
    private final CommonConfigRepo commonConfigRepo;

    public UsersService(UserRepo userRepo, DepartmentRepo departmentRepo, PositionRepo positionRepo,
            StatusRepo statusRepo, CommonConfigRepo commonConfigRepo) {
        this.userRepo = userRepo;
        this.departmentRepo = departmentRepo;
        this.positionRepo = positionRepo;
        this.statusRepo = statusRepo;
        this.commonConfigRepo = commonConfigRepo;
    }

    // ------------------------------------- List all users -------------------------------------
    @Transactional(readOnly = true)
    public Page<UserEntity> getAllUsers(Pageable pageable) {
        return userRepo.findAll(pageable);
    }

    // ------------------------------------- Get user by ID -------------------------------------
    @Transactional(readOnly = true)
    public UserEntity getUserById(String id) {
        log.debug("get user by id");
        return userRepo.findByUserId(id)
                .orElseThrow(() -> new CustomException("users.notFound", HttpStatus.NOT_FOUND));
    }


    // ------------------------------------- Get user by USERNAME -------------------------------------
    @Transactional(readOnly = true)
    public UserEntity getUserByUsername(String username) {
        return userRepo.findByUserName(username)
                .orElseThrow(() -> new CustomException("users.notFound", HttpStatus.NOT_FOUND));
    }
    

    // ------------------------------------- Create a new user -------------------------------------
    @Transactional
    public UserEntity createUser(UserEntity userEntity) {

        Integer maxId = userRepo.getMaxId();
        userEntity.setUserSeq(maxId);

        if (userRepo.existsByUserId(userEntity.getUserId())) {
            throw new CustomException("users.id.alreadyExists", HttpStatus.CONFLICT);
        }

        if (!departmentRepo.existsByDeptCode(userEntity.getUserDeptCode())) {
            if (commonConfigRepo.findByConfigKeyAndActiveTrue("AUTO_CREATE_DEPARTMENT") == null) {
                throw new CustomException("departments.notFound", HttpStatus.NOT_FOUND);
            } else {
                DepartmentEntity departmentEntity = new DepartmentEntity();
                departmentEntity.setDeptCode(userEntity.getUserDeptCode());
                departmentEntity.setDeptName(userEntity.getUserDeptName());
                DepartmentsService departmentsService = new DepartmentsService(departmentRepo);
                departmentEntity = departmentsService.create(departmentEntity);
            }
        }

        if (!positionRepo.existsByPosCode(userEntity.getUserPosCode())) {
            if (commonConfigRepo.findByConfigKeyAndActiveTrue("AUTO_CREATE_POSITION") == null) {
                throw new CustomException("positions.notFound", HttpStatus.NOT_FOUND);
            } else {
                PositionEntity positionEntity = new PositionEntity();
                positionEntity.setPosCode(userEntity.getUserPosCode());
                positionEntity.setPosName(userEntity.getUserPosName());
                PositionsService positionsService = new PositionsService(positionRepo);
                positionEntity = positionsService.create(positionEntity);
            }
        }

        if (!statusRepo.existsByStatusCode(userEntity.getUserStatusCode())) {
            if (commonConfigRepo.findByConfigKeyAndActiveTrue("AUTO_CREATE_STATUS") == null) {
                throw new CustomException("statuses.notFound", HttpStatus.NOT_FOUND);
            } else {
                StatusEntity statusEntity = new StatusEntity();
                statusEntity.setStatusCode(userEntity.getUserStatusCode());
                statusEntity.setStatusName(userEntity.getUserStatusCode());
                StatusesService statusesService = new StatusesService(statusRepo);
                statusEntity = statusesService.create(statusEntity);
            }
        }
        return userRepo.save(userEntity);
    }

    // ------------------------------------- Update a user by ID -------------------------------------
    @Transactional
    public UserEntity updateUser(UserEntity userEntity) {
        if (userRepo.existsByUserSchoolNoAndUserIdNot(userEntity.getUserSchoolNo(), userEntity.getUserId())) {
            throw new CustomException("users.schoolNo.alreadyExistsForAnotherId", HttpStatus.CONFLICT);
        }

        if (!departmentRepo.existsByDeptCode(userEntity.getUserDeptCode())) {
            if (commonConfigRepo.findByConfigKeyAndActiveTrue("AUTO_CREATE_DEPARTMENT") == null) {
                throw new CustomException("departments.notFound", HttpStatus.NOT_FOUND);
            } else {
                DepartmentEntity departmentEntity = new DepartmentEntity();
                departmentEntity.setDeptCode(userEntity.getUserDeptCode());
                departmentEntity.setDeptName(userEntity.getUserDeptName());
                DepartmentsService departmentsService = new DepartmentsService(departmentRepo);
                departmentEntity = departmentsService.create(departmentEntity);
            }
        }

        if (!positionRepo.existsByPosCode(userEntity.getUserPosCode())) {
            if (commonConfigRepo.findByConfigKeyAndActiveTrue("AUTO_CREATE_POSITION") == null) {
                throw new CustomException("positions.notFound", HttpStatus.NOT_FOUND);
            } else {
                PositionEntity positionEntity = new PositionEntity();
                positionEntity.setPosCode(userEntity.getUserPosCode());
                positionEntity.setPosName(userEntity.getUserPosName());
                PositionsService positionsService = new PositionsService(positionRepo);
                positionEntity = positionsService.create(positionEntity);
            }
        }

        if (!statusRepo.existsByStatusCode(userEntity.getUserStatusCode())) {
            if (commonConfigRepo.findByConfigKeyAndActiveTrue("AUTO_CREATE_STATUS") == null) {
                throw new CustomException("statuses.notFound", HttpStatus.NOT_FOUND);
            } else {
                StatusEntity statusEntity = new StatusEntity();
                statusEntity.setStatusCode(userEntity.getUserStatusCode());
                statusEntity.setStatusName(userEntity.getUserStatusCode());
                StatusesService statusesService = new StatusesService(statusRepo);
                statusEntity = statusesService.create(statusEntity);
            }
        }

        //  response = new UserResponse();
        // UserResponse response = 
        return userRepo.save(userEntity);
    }

    // ------------------------------------- Delete a user by ID -------------------------------------
    @Transactional
    public boolean deleteUser(String id) {
        if (!userRepo.existsById(id)) {
            throw new CustomException("users.notFound", HttpStatus.NOT_FOUND);
        }
        userRepo.deleteById(id);
        return true;
    }
}
