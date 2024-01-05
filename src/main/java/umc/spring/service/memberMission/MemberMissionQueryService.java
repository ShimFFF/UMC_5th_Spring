package umc.spring.service.memberMission;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.ApiPayload.code.status.ErrorStatus;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.exception.handler.MemberMissionHandler;
import umc.spring.repository.MemberMissionRepository;

@Service
@RequiredArgsConstructor
public class MemberMissionQueryService {

    private final MemberMissionRepository memberMissionRepository;


    @Transactional
    public void completeMission(Long userId,Long memberMissionId) {
        MemberMission memberMission = memberMissionRepository.findById(memberMissionId)
                .orElseThrow(() -> new MemberMissionHandler(ErrorStatus.MEMBER_MISSION_NOT_FOUND));
        // 해당 미션을 찾지 못했다면

        if(!memberMission.getUser().getUserId().equals(userId)) { // 해당 미션을 수행하는 유저가 아니라면
            throw new MemberMissionHandler(ErrorStatus.MEMBER_MISSION_NOT_MATCH);
        }

        memberMission.completeMission();
    }
}
