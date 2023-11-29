package umc.spring.domain;

import lombok.*;
import umc.spring.domain.common.BaseTimeEntitiy;
import umc.spring.domain.mapping.FoodPerfer;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class FoodCateg extends BaseTimeEntitiy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categId;

    @Column(nullable = false, length = 20)
    private String name;

    @OneToMany(mappedBy = "foodCateg", cascade = CascadeType.ALL)
    private List<FoodPerfer> foodPerferList= new ArrayList<>();

    @OneToMany(mappedBy = "foodCateg", cascade = CascadeType.ALL)
    private List<Store> storeList= new ArrayList<>();
}
