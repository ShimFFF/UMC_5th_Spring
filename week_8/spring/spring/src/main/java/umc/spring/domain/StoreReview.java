package umc.spring.domain;

import lombok.*;
import umc.spring.domain.common.BaseTimeEntitiy;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class StoreReview extends BaseTimeEntitiy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id") //FK
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id") //FK
    private Member member;

    @Column(nullable = false, length = 100)
    private String content;

    @Column(nullable = false)
    private Float starPoint; //별점
}
