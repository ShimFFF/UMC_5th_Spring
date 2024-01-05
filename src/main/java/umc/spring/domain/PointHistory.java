package umc.spring.domain;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import umc.spring.domain.common.BaseTimeEntitiy;
import umc.spring.domain.enums.PointStatus;

import javax.persistence.*;

@Entity
@Getter
@Builder
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class PointHistory extends BaseTimeEntitiy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id") //FK
    private Users user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id") //FK
    private Store store;

    @Column(nullable = false)
    private Integer point;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar(10) default 'EARN'")
    private PointStatus status; // (EARN, USE, EXPIRE)
}
