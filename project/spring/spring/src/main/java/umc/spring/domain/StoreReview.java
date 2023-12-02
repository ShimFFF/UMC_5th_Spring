package umc.spring.domain;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import umc.spring.domain.common.BaseTimeEntitiy;
import umc.spring.domain.enums.Status;

import javax.persistence.*;

@Entity
@Getter
@Builder
@DynamicInsert
@DynamicUpdate
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
    @JoinColumn(name = "user_id") //FK
    private Users user;

    @Column(nullable = false, length = 100)
    private String content;

    @Column(columnDefinition = "varchar(10) default 'ACTIVE'")
    private Status status;

    @Column(nullable = false)
    private Float starPoint; //별점
}
