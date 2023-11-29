package umc.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import umc.spring.domain.FoodCateg;

@Repository
public interface FoodCategRepository extends JpaRepository<FoodCateg, Long> {
}
