package wise.services.university;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wise.common.CustomException;
import wise.entities.UniversityEntity;
import wise.repositories.university.UniversityRepo;

import org.springframework.data.domain.Pageable;

@Service
public class UniversityService {

    private final UniversityRepo universityRepo;

    public UniversityService(UniversityRepo universityRepo) {
        this.universityRepo = universityRepo;
    }

    @Transactional(readOnly = true)
    public List<UniversityEntity> getAll() {
        return universityRepo.findAll();
    }

    @Transactional(readOnly = true)
    public Page<UniversityEntity> getAllUniversities(Pageable pagable) {
        return universityRepo.findAll(pagable);
    }

    @Transactional(readOnly = true)
    public Optional<UniversityEntity> getByCode(Integer code) {
        return universityRepo.findById(code);
    }


    @Transactional
    public UniversityEntity create(UniversityEntity universityEntity) {
        // Generate a new unique university code, if necessary
        // You can replace this with your custom logic if university code generation is handled differently
        String newId = RandomStringUtils.randomNumeric(6);
        universityEntity.setUnivCode(Integer.parseInt(newId));

        // Check if the university already exists in the database by name
        if (universityRepo.existsByUnivName(universityEntity.getUnivName())) {
            throw new CustomException("universities.name.alreadyExists");
        }
        return universityRepo.save(universityEntity);
    }

    // ------------------------------------- Update a university by ID -------------------------------------
    public UniversityEntity updateUniversity(Integer code, UniversityEntity universityEntity) {
        return universityRepo.findById(code)
            .map(university -> {
                university.setUnivName(universityEntity.getUnivName());
                university.setUnivAddress(universityEntity.getUnivAddress());
                university.setUnivCity(universityEntity.getUnivCity());
                university.setUnivState(universityEntity.getUnivState());
                university.setUnivZip(universityEntity.getUnivZip());
                university.setUnivPhone(universityEntity.getUnivPhone());
                university.setUnivPhoneAlt(universityEntity.getUnivPhoneAlt());
                university.setUnivWebsite(universityEntity.getUnivWebsite());
                university.setUnivLatitude(universityEntity.getUnivLatitude());
                university.setUnivLongitude(universityEntity.getUnivLongitude());
                return universityRepo.save(university);
            })
            .orElseThrow(() -> new CustomException("universities.notFound"));
    }

    // ------------------------------------- Delete a university by ID -------------------------------------
    @Transactional
    public boolean deleteUniversity(Integer code) {
        if (!universityRepo.existsById(code)) {
            return false;
        }
        universityRepo.deleteById(code);
        return true;
    }
}
