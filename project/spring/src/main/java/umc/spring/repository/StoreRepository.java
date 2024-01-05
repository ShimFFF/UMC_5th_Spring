package umc.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import umc.spring.domain.Store;
import umc.spring.domain.StoreReview;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {


        Boolean existsByStoreId(Long storeId);
}