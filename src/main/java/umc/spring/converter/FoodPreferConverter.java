package umc.spring.converter;

import umc.spring.domain.FoodCateg;
import umc.spring.domain.mapping.FoodPerfer;

import java.util.List;
import java.util.stream.Collectors;

public class FoodPreferConverter {

    public static List<FoodPerfer> toMemberPreferList(List<FoodCateg> foodCategoryList){

        return foodCategoryList.stream()
                .map(foodCategory ->
                        FoodPerfer.builder() // 양방항 연관 관계 설정은 Service에서 처리
                                .foodCateg(foodCategory) //따라서, Member를 넣지 않음
                                .build()
                ).collect(Collectors.toList());
    }
}
