package umc.spring.converter;

import org.springframework.data.domain.Page;
import umc.spring.domain.Mission;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.web.dto.member.MemberResponseDTO;
import umc.spring.web.dto.store.StoreResponse;

import java.util.List;
import java.util.stream.Collectors;

public class MemberMissionConverter {

    public static MemberResponseDTO.goingMissionPreViewDTO toGoingMissionPreView(MemberMission memberMission){
        return MemberResponseDTO.goingMissionPreViewDTO.builder()
                .missionId(memberMission.getMission().getMissionId())
                .achievePrice(memberMission.getMission().getAchivePrice())
                .earnPoint(memberMission.getMission().getPoint())
                .build();
    }

    public static MemberResponseDTO.goinMissionPreViewListDTO toGoingMissionPreViewList(Page<MemberMission> missionList){
        // stream으로 바꿔서 map으로 하나씩 바꿔줌 -> collect로 다시 list로 바꿔줌 -> 그걸 reviewPreViewDTOList에 넣음
        List<MemberResponseDTO.goingMissionPreViewDTO> missionPreViewDTOList = missionList.stream()
                .map(MemberMissionConverter::toGoingMissionPreView).collect(Collectors.toList());

        return MemberResponseDTO.goinMissionPreViewListDTO.builder()
                .isLast(missionList.isLast())
                .isFirst(missionList.isFirst())
                .totalPage(missionList.getTotalPages())
                .totalElements(missionList.getTotalElements())
                .listSize(missionPreViewDTOList.size())
                .missionList(missionPreViewDTOList)
                .build();
    }

}
