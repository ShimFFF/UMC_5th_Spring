package umc.spring.repository;

import jdk.jshell.Snippet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import umc.spring.domain.Mission;
import umc.spring.domain.Users;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.enums.Status;
import umc.spring.domain.mapping.MemberMission;

import java.util.Optional;

@Repository
public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {

    @Query("SELECT CASE WHEN COUNT(mm) > 0 THEN true ELSE false END FROM MemberMission mm WHERE mm.user.userId = :memberId AND mm.mission.missionId = :missionId AND mm.status = 'GOING'")
    boolean existsByMemberIdAndMissionIdAndStatus(@Param("memberId") Long memberId, @Param("missionId") Long missionId);

    Page<MemberMission> findAllByUserAndStatus(Users user, MissionStatus missionStatus, PageRequest pageRequest);

}
