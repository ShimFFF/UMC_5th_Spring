package umc.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import umc.spring.domain.mapping.MemberMission;

@Repository
public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {
}
