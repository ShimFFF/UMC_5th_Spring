package umc.spring.domain.mapping;

import lombok.*;
import umc.spring.domain.Member;
import umc.spring.domain.Mission;
import umc.spring.domain.common.BaseTimeEntitiy;
import umc.spring.domain.enums.MissionStatus;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
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
    @JoinColumn(name = "member_id")  //FK
    private Member member;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar(10) default 'GOING'")
    private MissionStatus status; // (GOING, SUCCESS, FAIL)

    private LocalDate dueDate; //마감일

    private LocalDateTime completeDate; //달성일



}
