package wise.services.university;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wise.common.CustomException;
import wise.entities.RoomConfigEntity;
import wise.entities.RoomConfigId;
import wise.repositories.university.RoomConfigRepo;
import org.springframework.http.HttpStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class RoomConfigService {

    private final RoomConfigRepo roomConfigRepo;

    public RoomConfigService(RoomConfigRepo roomConfigRepo) {
        this.roomConfigRepo = roomConfigRepo;
    }

    // ------------------------------------- List all room configurations -------------------------------------
    @Transactional(readOnly = true)
    public Page<RoomConfigEntity> getAllRoomConfigs(Pageable pageable) {
        return roomConfigRepo.findAll(pageable);
    }

    // ------------------------------------- Get room configuration by room code -------------------------------------
    @Transactional(readOnly = true)
    public RoomConfigEntity getRoomConfigByRoomCode(int roomCode) {
        return roomConfigRepo.findByRoomConfigId_RoomCode(roomCode)
                .orElseThrow(() -> new CustomException("roomConfig.notFound", HttpStatus.NOT_FOUND));
    }

    // ------------------------------------- Create a new room configuration -------------------------------------
    @Transactional
    public RoomConfigEntity createRoomConfig(RoomConfigEntity roomConfigEntity) {
        if (roomConfigRepo.existsById(roomConfigEntity.getRoomConfigId())) {
            throw new CustomException("roomConfig.code.alreadyExists", HttpStatus.CONFLICT);
        }

        return roomConfigRepo.save(roomConfigEntity);
    }

    // ------------------------------------- Update room configuration -------------------------------------
    @Transactional
    public RoomConfigEntity updateRoomConfig(RoomConfigEntity roomConfigEntity) {
        return roomConfigRepo.save(roomConfigEntity);
    }

    // ------------------------------------- Delete room configuration by room code -------------------------------------
    @Transactional
    public boolean deleteRoomConfig(int roomCode) {
        if (!roomConfigRepo.existsByRoomConfigId_RoomCode
        (roomCode)) {
            throw new CustomException("roomConfig.notFound", HttpStatus.NOT_FOUND);
        }
        roomConfigRepo.deleteByRoomConfigId_RoomCode(roomCode);
        return true;
    }
}
