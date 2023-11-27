package umc.spring.domain.mapping;

import lombok.*;
import umc.spring.domain.FoodCateg;
import umc.spring.domain.Member;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class FoodPerfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long foodPerferId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id") //FK
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "foodCateg_id") //FK
    private FoodCateg foodCateg;
}
