package umc.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.repository.FoodCategRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FoodCateService {

    private final FoodCategRepository foodCategRepository;

    @Transactional(readOnly = true)
    public boolean isfoodCategoryValid(List<Long> values) {
        return values.stream()
                .allMatch(value -> foodCategRepository.existsById(value));
    }
}
