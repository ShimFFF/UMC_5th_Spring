package umc.spring.service.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.ApiPayload.code.status.ErrorStatus;
import umc.spring.converter.FoodPreferConverter;
import umc.spring.converter.MemberConverter;
import umc.spring.domain.FoodCateg;
import umc.spring.domain.Users;
import umc.spring.domain.mapping.FoodPerfer;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.exception.handler.FoodCategHandler;
import umc.spring.exception.handler.MemberMissionHandler;
import umc.spring.repository.FoodCategRepository;
import umc.spring.repository.MemberMissionRepository;
import umc.spring.repository.MemberRepository;
import umc.spring.web.dto.member.MemberRequestDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberCommandServiceImpl implements MemberCommendService {

    private final MemberRepository memberRepository;
    private final MemberMissionRepository memberMissionRepository;
    private final FoodCategRepository foodCategRepository;


    @Override
    @Transactional
    public Users signUp(MemberRequestDTO.SignUpDTO request)  {

        Users newUsers = MemberConverter.toMember(request);
        List<FoodCateg> foodCategoryList = request.getFoodPerferIdList().stream()
                .map(category -> {
                    return foodCategRepository.findById(category).orElseThrow(() -> new FoodCategHandler(ErrorStatus.FOOD_CATEGORY_NOT_FOUND));
                }).collect(Collectors.toList());

        List<FoodPerfer> foodPreferList = FoodPreferConverter.toMemberPreferList(foodCategoryList);

        foodPreferList.forEach(foodPrefer -> {foodPrefer.setUser(newUsers);});

        return memberRepository.save(newUsers);
    }

}
