package umc.spring.domain;

import lombok.*;
import umc.spring.domain.common.BaseTimeEntitiy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Store extends BaseTimeEntitiy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long storeId;

    @Column(nullable = false, length = 20)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categ_id") //FK
    private FoodCateg foodCateg;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id") //FK
    private Users user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "regoin_id") //FK
    private Region region;

    @Column(nullable = false, length = 50)
    private String address;

    @Column(length = 20)
    private String phone;

    @Column(length = 20)
    private String openTime;

    @Column(length = 20)
    private String closeTime;

    @Column(length = 20)
    private String holiday;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<PointHistory> pointHistory= new ArrayList<>();

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<Mission> missionList= new ArrayList<>();

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<StoreReview> storeReviewList= new ArrayList<>();
}
