package umc.spring.domain.mapping;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import umc.spring.domain.Users;
import umc.spring.domain.Mission;
import umc.spring.domain.common.BaseTimeEntitiy;
import umc.spring.domain.enums.MissionStatus;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MemberMission extends BaseTimeEntitiy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long MemberMissionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id") //FK
    private Mission mission;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")  //FK
    private Users user;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar(10) default 'GOING'")
    private MissionStatus status; // (GOING, SUCCESS, FAIL)

    private LocalDateTime dueDate; //마감일

    @Column(nullable = true)
    private LocalDateTime completeDate; //달성일


    @PrePersist // 데이터베이스에 저장되기 전에 dueDate를 계산하고 설정
    public void prePersist() {
        if (dueDate == null) {
            // create_at에서 30일을 더한 후 마감일로 설정
            dueDate = getCreatedAt().plusDays(30);
        }
    }

    public void completeMission() {
        this.status = MissionStatus.SUCCESS;
        this.completeDate = LocalDateTime.now();
    }
}
