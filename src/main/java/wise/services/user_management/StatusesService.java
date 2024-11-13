package wise.services.user_management;

import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wise.common.CustomException;
import wise.entities.StatusEntity;
import wise.repositories.user_management.StatusRepo;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;

@Service
public class StatusesService {

    private StatusRepo statusRepo;

    public StatusesService(StatusRepo statusRepo) {
        this.statusRepo = statusRepo;
    }

    // ------------------------------------- List all statuses -------------------------------------
    @Transactional(readOnly = true)
    public List<StatusEntity> getAll() {
        return statusRepo.findAll();
    }

    // ------------------------------------- List all statuses with pagination -------------------------------------
    @Transactional(readOnly = true)
    public Page<StatusEntity> getAllStatuses(Pageable pagable) {
        return statusRepo.findAll(pagable);
    }

    
    // ------------------------------------- Get status by ID -------------------------------------
    @Transactional(readOnly = true)
    public StatusEntity getByCode(String code) {
        return statusRepo.findById(code)
            .map(status -> {
                return status;
            })
            .orElseThrow(() -> new CustomException("statuses.notFound", HttpStatus.NOT_FOUND));
    }

    // ------------------------------------- Create a new status -------------------------------------
    @Transactional
    public StatusEntity create(StatusEntity statusEntity) {
        // Find Max Code 1,8 characters and increment by 1
        if (statusEntity.getStatusCode() != null && statusRepo.existsByStatusCode(statusEntity.getStatusCode())) {
            throw new CustomException("statuses.code.alreadyExists", HttpStatus.CONFLICT);
        } else if (statusEntity.getStatusCode() == null) {
            statusEntity.setStatusCode(RandomStringUtils.randomAlphanumeric(10).toUpperCase());
        }

        // Check if status already exists in the database
        if(statusRepo.existsByStatusName(statusEntity.getStatusName())) {
            throw new CustomException("statuses.name.alreadyExists", HttpStatus.CONFLICT);
        }
        return statusRepo.save(statusEntity);
    }

    // ------------------------------------- Update a status by ID -------------------------------------
    public StatusEntity updateStatus(StatusEntity statusEntity) {

        if(statusRepo.existsByStatusNameAndStatusCodeNot(statusEntity.getStatusName(), statusEntity.getStatusCode())) {
            throw new CustomException("statuses.name.alreadyExistsForAnotherCode", HttpStatus.CONFLICT);
        }

        return statusRepo.save(statusEntity);
    }

    // ------------------------------------- Delete a status by ID -------------------------------------
    @Transactional
    public boolean deleteStatus(String code) {
        if(!statusRepo.existsById(code)) {
            throw new CustomException("statuses.notFound", HttpStatus.NOT_FOUND);
        }

        statusRepo.deleteById(code);
        return true;
    }
}
