package umc.spring.domain;

import lombok.*;
import umc.spring.domain.common.BaseTimeEntitiy;
import umc.spring.domain.enums.Gender;
import umc.spring.domain.enums.Role;
import umc.spring.domain.enums.SocialType;
import umc.spring.domain.enums.Status;
import umc.spring.domain.mapping.FoodPerfer;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.domain.mapping.TermAgree;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity // JPA Entity 라는 것을 알려주는 어노테이션
@Getter // Getter 메소드를 자동 생성

//아래 3개 어노테이션은 Builder 패턴을 사용할 수 있게 해주는 어노테이션
@Builder // Builder 패턴을 사용할 수 있게 해주는 어노테이션
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 기본 생성자를 자동으로 추가하는 어노테이션
@AllArgsConstructor // 모든 필드 값을 파라미터로 받는 생성자를 자동으로 추가하는 어노테이션
public class Member extends BaseTimeEntitiy {

    @Id // Primary Key 라는 것을 알려주는 어노테이션
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동으로 값이 생성되는 것을 알려주는 어노테이션
    private Long memberId;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false, unique = true, length = 40)
    private String email;

    @Column(columnDefinition = "INT default 0")
    private Integer pointTotal; //실제 DB 적용시 자동으로 point_total로 적용됨

    @Column(nullable = false, length = 10)
    private String birth;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(2) default 'N'", nullable = false)
    private Gender gender; //성별 (M, F, N)

    @Column(length = 20)
    private String phone;

    @Column(length = 100)
    private String address;

    @Enumerated(EnumType.STRING) //Enum 어노테이션을 사용하면 DB에는 해당 Enum의 이름이 저장됨
    @Column(columnDefinition = "VARVHAR(10) default 'ACTIVE'", nullable = false)
    private Status status; //상태 (ACTIVE, INACTIVE)

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(10) default 'USER'", nullable = false)
    private Role role; // 권한 (USER, ADMIN, OWNER)

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(10) ", nullable = false)
    private SocialType socialType; //소셜 로그인 타입 (KAKAO, NAVER, GOOGLE, APPLE)

    private LocalDate inactivatedAt; //탈퇴일

    @Column(length = 100)
    private String accessToken;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<TermAgree> termAgreeList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MemberMission> memberMissionList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<FoodPerfer> foodPerferList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<PointHistory> pointHistoryList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Store> storeList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<StoreReview> storeReviewList = new ArrayList<>();

}
