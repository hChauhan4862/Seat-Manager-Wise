package wise.services.university;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wise.common.CustomException;
import wise.entities.SeatCategoryEntity;
import wise.repositories.university.SeatCategoryRepo;
import org.springframework.http.HttpStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class SeatCategoryService {

    private final SeatCategoryRepo seatCategoryRepo;

    public SeatCategoryService(SeatCategoryRepo seatCategoryRepo) {
        this.seatCategoryRepo = seatCategoryRepo;
    }

    // ------------------------------------- List all seat categories -------------------------------------
    @Transactional(readOnly = true)
    public Page<SeatCategoryEntity> getAllSeatCategories(Pageable pageable) {
        return seatCategoryRepo.findAll(pageable);
    }

    // ------------------------------------- Get seat category by code -------------------------------------
    @Transactional(readOnly = true)
    public SeatCategoryEntity getSeatCategoryByCode(int seatCatCode) {
        return seatCategoryRepo.findById(seatCatCode)
                .orElseThrow(() -> new CustomException("seatCategory.notFound", HttpStatus.NOT_FOUND));
    }

    // ------------------------------------- Create a new seat category -------------------------------------
    @Transactional
    public SeatCategoryEntity createSeatCategory(SeatCategoryEntity seatCategoryEntity) {
        if (seatCategoryRepo.existsBySeatCatCode(seatCategoryEntity.getSeatCatCode())) {
            throw new CustomException("seatCategory.code.alreadyExists", HttpStatus.CONFLICT);
        }

        if (seatCategoryRepo.existsBySeatCatName(seatCategoryEntity.getSeatCatName())) {
            throw new CustomException("seatCategory.name.alreadyExists", HttpStatus.CONFLICT);
        }

        Integer maxId = seatCategoryRepo.getMaxId();
        seatCategoryEntity.setSeatCatCode(maxId);

        return seatCategoryRepo.save(seatCategoryEntity);
    }

    // ------------------------------------- Update seat category -------------------------------------
    @Transactional
    public SeatCategoryEntity updateSeatCategory(SeatCategoryEntity seatCategoryEntity) {

        return seatCategoryRepo.save(seatCategoryEntity);
    }

    // ------------------------------------- Delete seat category by code -------------------------------------
    @Transactional
    public boolean deleteSeatCategory(int seatCatCode) {
        if (!seatCategoryRepo.existsBySeatCatCode(seatCatCode)) {
            throw new CustomException("seatCategory.notFound", HttpStatus.NOT_FOUND);
        }
        seatCategoryRepo.deleteById(seatCatCode);
        return true;
    }
}
