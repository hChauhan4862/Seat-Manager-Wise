package wise.services.university;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wise.common.CustomException;
import wise.entities.RoomSchedulesEntity;
import wise.entities.RoomSchedulesId;
import wise.repositories.university.RoomSchedulesRepo;
import org.springframework.http.HttpStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Service
public class RoomScheduleService {

    private final RoomSchedulesRepo roomSchedulesRepo;

    public RoomScheduleService(RoomSchedulesRepo roomSchedulesRepo) {
        this.roomSchedulesRepo = roomSchedulesRepo;
    }

    // ------------------------------------- List all room schedules
    // -------------------------------------
    @Transactional(readOnly = true)
    public Page<RoomSchedulesEntity> getAllRoomSchedules(Pageable pageable) {
        return roomSchedulesRepo.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public List<RoomSchedulesEntity> getAllRoomSchedules() {
        return roomSchedulesRepo.findAll();
    }

    // ------------------------------------- Get room schedule by ID
    // -------------------------------------
    @Transactional(readOnly = true)
    public RoomSchedulesEntity getRoomScheduleById(RoomSchedulesId id) {
        return roomSchedulesRepo.findById(id)
                .orElseThrow(() -> new CustomException("roomSchedule.notFound", HttpStatus.NOT_FOUND));
    }

    // ------------------------------------- Create a new room schedule
    // -------------------------------------
    @Transactional
    public RoomSchedulesEntity createRoomSchedule(RoomSchedulesEntity roomSchedulesEntity) {
        if (roomSchedulesRepo.existsById(roomSchedulesEntity.getRoomSchedulesId())) {
            throw new CustomException("roomSchedule.id.alreadyExists", HttpStatus.CONFLICT);
        }
        return roomSchedulesRepo.save(roomSchedulesEntity);
    }

    // ------------------------------------- Update room schedule
    // -------------------------------------
    @Transactional
    public RoomSchedulesEntity updateRoomSchedule(RoomSchedulesEntity roomSchedulesEntity) {
        return roomSchedulesRepo.save(roomSchedulesEntity);
    }

    // ------------------------------------- Delete room schedule by ID
    // -------------------------------------
    @Transactional
    public boolean deleteRoomSchedule(RoomSchedulesId id) {
        if (!roomSchedulesRepo.existsById(id)) {
            throw new CustomException("roomSchedule.notFound", HttpStatus.NOT_FOUND);
        }
        roomSchedulesRepo.deleteById(id);
        return true;
    }
}
