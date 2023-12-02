package umc.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import umc.spring.domain.Mission;
import umc.spring.domain.Users;
import umc.spring.domain.mapping.MemberMission;

import java.util.Optional;

@Repository
public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {

    Optional<MemberMission> findByMemberIdAndMissionId(Long memberId, Long missionId);


    @Query("SELECT COUNT(mm) > 0 FROM MemberMission mm " +
            "WHERE mm.user = :memberId " +
            "AND mm.mission = :missionId " +
            "AND mm.status = 'CHALLENGING'")
    boolean existsByMemberIdAndMissionIdAndStatus(@Param("memberId") Long memberId, @Param("missionId") Long missionId);
}
