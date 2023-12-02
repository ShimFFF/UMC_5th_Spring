package umc.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.spring.repository.FoodCategRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class FoodCateService {

    private final FoodCategRepository foodCategRepository;

    public boolean isfoodCategoryValid(List<Long> values) {
        return values.stream()
                .allMatch(value -> foodCategRepository.existsById(value));
    }
}
