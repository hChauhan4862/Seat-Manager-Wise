package wise.repositories.university;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wise.entities.RoomUserCodesMapEntity;
import wise.models.enums.UserCodeType;

import org.springframework.data.jpa.repository.Query;

@Repository
public interface RoomUserCodesMapRepo extends JpaRepository<RoomUserCodesMapEntity, Integer> {

    boolean existsBySeq(int seq);

    boolean existsByRoomCode(int roomCode);

    boolean existsByUserCode(int userCode);

    boolean existsByUserCodeType(UserCodeType userCodeType);

    boolean existsByScheduleTypeCode(int scheduleTypeCode);

    @Query(value = "SELECT (COALESCE(MAX(m.SEQ), 1100) + 1) FROM WN_ROOM_USERCODES_MAP m", nativeQuery = true)
    Integer getMaxId();
}
