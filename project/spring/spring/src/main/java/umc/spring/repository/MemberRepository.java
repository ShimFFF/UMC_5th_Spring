package umc.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import umc.spring.domain.User;

// JAP를 사용하기 위해 Repository를 상속받는다.
@Repository
public interface MemberRepository extends JpaRepository<User, Long> {
}
