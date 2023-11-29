package umc.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.FoodCateg;

public interface FoodCategRepository extends JpaRepository<FoodCateg, Long> {
}
