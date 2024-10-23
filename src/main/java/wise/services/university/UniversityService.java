package wise.services.university;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wise.common.CustomException;
import wise.entities.UniversityEntity;
import wise.repositories.university.UniversityRepo;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;

@Service
public class UniversityService {

    private final UniversityRepo universityRepo;

    public UniversityService(UniversityRepo universityRepo) {
        this.universityRepo = universityRepo;
    }

    @Transactional(readOnly = true)
    public Page<UniversityEntity> getAllUniversities(Pageable pageable) {
        return universityRepo.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public UniversityEntity getUniversityById(int id) {
        return universityRepo.findById(id)
                .orElseThrow(() -> new CustomException("university.notFound", HttpStatus.NOT_FOUND));
    }

    @Transactional
    public UniversityEntity createUniversity(UniversityEntity universityEntity) {
        Integer maxId = universityRepo.getMaxId();
        universityEntity.setUnivCode(maxId);
        // Check if university name already exists
        if (universityRepo.existsByUnivName(universityEntity.getUnivName())) {
            throw new CustomException("university.name.alreadyExists", HttpStatus.CONFLICT);
        }
        return universityRepo.save(universityEntity);
    }

    @Transactional
    public UniversityEntity updateUniversity(UniversityEntity universityEntity) {
        // Check if university name already exists for another code
        if (universityRepo.existsByUnivNameAndUnivCodeNot(universityEntity.getUnivName(), universityEntity.getUnivCode())) {
            throw new CustomException("university.name.alreadyExistsForAnotherCode", HttpStatus.CONFLICT);
        }
        return universityRepo.save(universityEntity);
    }

    @Transactional
    public boolean deleteUniversity(int id) {
        if (!universityRepo.existsById(id)) {
            throw new CustomException("university.notFound", HttpStatus.NOT_FOUND);
        }
        universityRepo.deleteById(id);
        return true;
    }
}
