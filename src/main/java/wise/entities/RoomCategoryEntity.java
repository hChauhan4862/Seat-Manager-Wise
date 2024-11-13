package wise.entities;

import org.hibernate.annotations.SoftDelete;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import wise.models.enums.RoomCategoryType;

@Data
@Entity
@Table(name = "WN_ROOM_CATEGORY", schema = "dbo")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@SoftDelete
public class RoomCategoryEntity extends BaseEntity {

    @Id
    @Column(name = "ROOMCAT_CODE", nullable = false)
    private Integer roomCatCode;

    @Enumerated(EnumType.STRING)
    @Column(name = "ROOMCAT_NAME", columnDefinition = "nvarchar(50)")
    private RoomCategoryType roomCatName;
}
