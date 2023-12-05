package umc.spring.converter;

import org.springframework.data.domain.Page;
import umc.spring.domain.Mission;
import umc.spring.domain.StoreReview;
import umc.spring.domain.Users;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.web.dto.memberMission.MemberMissionRequest;
import umc.spring.web.dto.memberMission.MemberMissionResponse;
import umc.spring.web.dto.review.ReviewResponse;
import umc.spring.web.dto.store.StoreResponse;

import java.util.List;
import java.util.stream.Collectors;

public class MissionConverter {

    public static MemberMissionResponse.challengeDTO toResponseAddDTO (MemberMission memberMission){
        return MemberMissionResponse.challengeDTO.builder()
                .memberMissionId(memberMission.getMemberMissionId())
                .build();
    }

    public static MemberMission toMemberMission(MemberMissionRequest.challengeDTO request, Mission mission, Users users){

        return MemberMission.builder()
                .mission(mission)
                .user(users)
                .build();
    }

    public static StoreResponse.StoreMissionPreViewDTO toStoreMissionPreView(Mission mission){
        return StoreResponse.StoreMissionPreViewDTO.builder()
                .achievePrice(mission.getAchivePrice())
                .missionId(mission.getMissionId())
                .earnPoint(mission.getPoint())
                .build();
    }

    public static StoreResponse.StoreMissionPreViewListDTO toStoreMissionListPreView(Page<Mission> missionList){
        // stream으로 바꿔서 map으로 하나씩 바꿔줌 -> collect로 다시 list로 바꿔줌 -> 그걸 reviewPreViewDTOList에 넣음
        List<StoreResponse.StoreMissionPreViewDTO> missionPreViewDTOList = missionList.stream()
                .map(MissionConverter::toStoreMissionPreView).collect(Collectors.toList());

        return StoreResponse.StoreMissionPreViewListDTO.builder()
                .isLast(missionList.isLast())
                .isFirst(missionList.isFirst())
                .totalPage(missionList.getTotalPages())
                .totalElements(missionList.getTotalElements())
                .listSize(missionPreViewDTOList.size())
                .reviewList(missionPreViewDTOList)
                .build();
    }

}
