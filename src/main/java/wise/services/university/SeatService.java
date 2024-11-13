package wise.services.university;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wise.common.CustomException;
import wise.entities.SeatEntity;
import wise.repositories.university.SeatRepo;
import org.springframework.http.HttpStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class SeatService {

    private final SeatRepo seatRepo;

    public SeatService(SeatRepo seatRepo) {
        this.seatRepo = seatRepo;
    }

    // ------------------------------------- List all seats -------------------------------------
    @Transactional(readOnly = true)
    public Page<SeatEntity> getAllSeats(Pageable pageable) {
        return seatRepo.findAll(pageable);
    }

    // ------------------------------------- Get seat by code -------------------------------------
    @Transactional(readOnly = true)
    public SeatEntity getSeatByCode(int seatCode) {
        return seatRepo.findById(seatCode)
                .orElseThrow(() -> new CustomException("seat.notFound", HttpStatus.NOT_FOUND));
    }

    // ------------------------------------- Create a new seat -------------------------------------
    @Transactional
    public SeatEntity createSeat(SeatEntity seatEntity) {
        if (seatRepo.existsBySeatCode(seatEntity.getSeatCode())) {
            throw new CustomException("seat.code.alreadyExists", HttpStatus.CONFLICT);
        }

        if (seatRepo.existsBySeatName(seatEntity.getSeatName())) {
            throw new CustomException("seat.name.alreadyExists", HttpStatus.CONFLICT);
        }

        Integer maxId = seatRepo.getMaxId();
        seatEntity.setSeatCode(maxId);

        return seatRepo.save(seatEntity);
    }

    // ------------------------------------- Update seat -------------------------------------
    @Transactional
    public SeatEntity updateSeat(SeatEntity seatEntity) {

        return seatRepo.save(seatEntity);
    }

    // ------------------------------------- Delete seat by code -------------------------------------
    @Transactional
    public boolean deleteSeat(int seatCode) {
        if (!seatRepo.existsBySeatCode(seatCode)) {
            throw new CustomException("seat.notFound", HttpStatus.NOT_FOUND);
        }
        seatRepo.deleteById(seatCode);
        return true;
    }
}
