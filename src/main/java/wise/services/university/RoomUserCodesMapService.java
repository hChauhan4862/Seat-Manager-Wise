package wise.services.university;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wise.common.CustomException;
import wise.entities.RoomUserCodesMapEntity;
import wise.repositories.university.RoomUserCodesMapRepo;
import org.springframework.http.HttpStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class RoomUserCodesMapService {

    private final RoomUserCodesMapRepo roomUserCodesMapRepo;

    public RoomUserCodesMapService(RoomUserCodesMapRepo roomUserCodesMapRepo) {
        this.roomUserCodesMapRepo = roomUserCodesMapRepo;
    }

    // ------------------------------------- List all Room User Codes Maps -------------------------------------
    @Transactional(readOnly = true)
    public Page<RoomUserCodesMapEntity> getAllRoomUserCodesMaps(Pageable pageable) {
        return roomUserCodesMapRepo.findAll(pageable);
    }

    // ------------------------------------- Get Room User Code Map by seq -------------------------------------
    @Transactional(readOnly = true)
    public RoomUserCodesMapEntity getRoomUserCodesMapBySeq(int seq) {
        return roomUserCodesMapRepo.findById(seq)
                .orElseThrow(() -> new CustomException("roomUserCodeMap.notFound", HttpStatus.NOT_FOUND));
    }

    // ------------------------------------- Create a new Room User Code Map -------------------------------------
    @Transactional
    public RoomUserCodesMapEntity createRoomUserCodesMap(RoomUserCodesMapEntity roomUserCodesMapEntity) {
        if (roomUserCodesMapRepo.existsBySeq(roomUserCodesMapEntity.getSeq())) {
            throw new CustomException("roomUserCodeMap.seq.alreadyExists", HttpStatus.CONFLICT);
        }

        Integer maxId = roomUserCodesMapRepo.getMaxId();
        roomUserCodesMapEntity.setSeq(maxId);

        return roomUserCodesMapRepo.save(roomUserCodesMapEntity);
    }

    // ------------------------------------- Update Room User Code Map -------------------------------------
    @Transactional
    public RoomUserCodesMapEntity updateRoomUserCodesMap(RoomUserCodesMapEntity roomUserCodesMapEntity) {

        return roomUserCodesMapRepo.save(roomUserCodesMapEntity);
    }

    // ------------------------------------- Delete Room User Code Map by seq -------------------------------------
    @Transactional
    public boolean deleteRoomUserCodesMap(int seq) {
        if (!roomUserCodesMapRepo.existsBySeq(seq)) {
            throw new CustomException("roomUserCodeMap.notFound", HttpStatus.NOT_FOUND);
        }
        roomUserCodesMapRepo.deleteById(seq);
        return true;
    }
}
