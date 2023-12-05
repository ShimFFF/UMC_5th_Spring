package umc.spring.repository;

import com.sun.xml.bind.v2.runtime.reflect.Lister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import umc.spring.domain.Store;
import umc.spring.domain.StoreReview;
import umc.spring.domain.Users;

@Repository
public interface ReviewRepository extends JpaRepository<StoreReview, Long> {

    Page<StoreReview> findAllByStore(Store store, PageRequest pageRequest);

    Page<StoreReview> findAllByUser(Users users, PageRequest pageRequest);

}
