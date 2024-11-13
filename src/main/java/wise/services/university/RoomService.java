package wise.services.university;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import wise.common.CustomException;
import wise.entities.RoomConfigEntity;
import wise.entities.RoomConfigId;
import wise.entities.RoomEntity;
import wise.entities.RoomSchedulesEntity;
import wise.entities.RoomSchedulesId;
import wise.entities.ScheduleTypeEntity;
import wise.models.enums.WeekdayType;
import wise.repositories.university.FloorRepo;
import wise.repositories.university.RoomCategoryRepo;
import wise.repositories.university.RoomRepo;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoomService {

    private final RoomRepo roomRepo;
    private final FloorRepo floorRepo;
    private final RoomCategoryRepo roomCategoryRepo;
    private final RoomConfigService roomConfigService;
    private final ScheduleTypeService scheduleTypeService;
    private final RoomScheduleService roomScheduleService;

    // List all rooms with pagination
    @Transactional(readOnly = true)
    public Page<RoomEntity> getAllRooms(Pageable pageable) {
        return roomRepo.findAll(pageable);
    }

    // Get a room by its code
    @Transactional(readOnly = true)
    public RoomEntity getRoomByCode(Integer roomCode) {
        return roomRepo.findByRoomCode(roomCode)
                .orElseThrow(() -> new CustomException("room.notFound", HttpStatus.NOT_FOUND));
    }

    // Get a room by its name
    @Transactional(readOnly = true)
    public RoomEntity getRoomByName(String roomName) {
        return roomRepo.findByRoomName(roomName)
                .orElseThrow(() -> new CustomException("room.notFound", HttpStatus.NOT_FOUND));
    }

    /**
     * @apiNote Create New Room with Room Config according to Scheudle type.
     * @param roomEntity
     * @return
     */
    @Transactional
    public RoomEntity createRoom(RoomEntity roomEntity) {
        Integer nextRoomCode = roomRepo.getNextRoomCode();
        roomEntity.setRoomCode(nextRoomCode);

        if (roomRepo.existsByRoomNumberAndFloorCode(roomEntity.getRoomNumber(), roomEntity.getFloorCode())) {
            throw new CustomException("room.number.alreadyExists", HttpStatus.CONFLICT);
        }

        if (!floorRepo.existsByFloorCode(roomEntity.getFloorCode())) {
            throw new CustomException("floor.code.notExists", HttpStatus.BAD_REQUEST);
        }

        if (!roomCategoryRepo.existsByRoomCatCode(roomEntity.getRoomCatCode())) {
            throw new CustomException("roomCategory.code.notExist", HttpStatus.BAD_REQUEST);
        }
        if (roomRepo.existsByRoomNumberAndFloorCode(roomEntity.getRoomNumber(), roomEntity.getFloorCode())) {
            throw new CustomException("room.number.floor.code.alreadyExist", HttpStatus.BAD_REQUEST);
        }
        List<ScheduleTypeEntity> scheduleTypeData = scheduleTypeService.getAllScheduleTypes();
        for (ScheduleTypeEntity scheduleType : scheduleTypeData) {
            // Creating data for Room config
            Integer scheduleCode = scheduleType.getScheduleTypeCode();
            RoomConfigEntity roomConfigEntity = roomConfigDataBuilder(roomEntity, scheduleCode);
            roomConfigService.createRoomConfig(roomConfigEntity);
            // Creating schedule data for all weekday according to schedule type code.
            for (WeekdayType weekdayType : WeekdayType.values()) {
                RoomSchedulesEntity roomSchedulesEntity = roomShceduleDataBuilder(roomEntity, scheduleCode,
                        weekdayType.ordinal());
                roomScheduleService.createRoomSchedule(roomSchedulesEntity);
            }
        }

        log.info("Room Config Created");
        log.info("Room Schedule Created");
        return roomRepo.save(roomEntity);
    }

    /**
     * @apiNote Construction Room config data for the given schedule types (COMMON,
     *          VACATION, ....)
     * @param roomEntity
     * @param scheduleType
     * @return
     */
    @Transactional
    public RoomConfigEntity roomConfigDataBuilder(RoomEntity roomEntity, int scheduleTypeCode) {
        // Fetching Schedule Code from Schedule type name (e.g. COMMON, VACATION)
        RoomConfigEntity roomConfigEntity = new RoomConfigEntity();
        roomConfigEntity.setRoomConfigId(new RoomConfigId(roomEntity.getRoomCode(), scheduleTypeCode));
        return roomConfigEntity;
    }

    /**
     * @apiNote Constructon of Schedule Data for given week day(0: MONDAY, 1:
     *          TUESDAY, ....) and schedule type (e.g 401:COMMON, 402:VACATION, ...)
     * @param roomEntity
     * @param scheduleTypeCode
     * @param weekdayValue
     * @return
     */
    @Transactional
    public RoomSchedulesEntity roomShceduleDataBuilder(RoomEntity roomEntity, int scheduleTypeCode,
            int weekdayValue) {
        RoomSchedulesId roomSchedulesId = new RoomSchedulesId(roomEntity.getRoomCode(), scheduleTypeCode, weekdayValue);
        RoomSchedulesEntity roomSchedulesEntity = new RoomSchedulesEntity();
        roomSchedulesEntity.setRoomSchedulesId(roomSchedulesId);
        return roomSchedulesEntity;
    }

    // Update an existing room by its code
    @Transactional
    public RoomEntity updateRoom(RoomEntity roomEntity) {
        if (roomRepo.existsByRoomNameAndFloorCode(roomEntity.getRoomName(), roomEntity.getFloorCode())) {
            throw new CustomException("room.name.alreadyExists", HttpStatus.CONFLICT);
        }
        return roomRepo.save(roomEntity);
    }

    // Delete a room by its code
    @Transactional
    public boolean deleteRoom(Integer roomCode) {
        if (!roomRepo.existsByRoomCode(roomCode)) {
            throw new CustomException("room.notFound", HttpStatus.NOT_FOUND);
        }
        roomRepo.deleteById(roomCode);
        return true;
    }
}
