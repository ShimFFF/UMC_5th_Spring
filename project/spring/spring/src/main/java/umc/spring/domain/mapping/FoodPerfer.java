package umc.spring.domain.mapping;

import lombok.*;
import umc.spring.domain.FoodCateg;
import umc.spring.domain.Users;

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
    @JoinColumn(name = "user_id") //FK
    private Users user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "foodCateg_id") //FK
    private FoodCateg foodCateg;

    //연관 관계 편의 메서드
    public void setUser(Users user){
        if(this.user != null)
            user.getFoodPerferList().remove(this);
        this.user = user;
        user.getFoodPerferList().add(this);
    }

    public void setFoodCateg(FoodCateg foodCategory){
        this.foodCateg = foodCategory;
    }
}
