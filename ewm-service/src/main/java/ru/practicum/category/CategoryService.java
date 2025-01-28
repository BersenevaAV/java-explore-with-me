package ru.practicum.category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import ru.practicum.dto.CategoryDto;
import ru.practicum.dto.NewCategoryDto;
import java.util.List;

@Service
@Getter
@Setter
@AllArgsConstructor
public class CategoryService {
    private final CategoryRepository catRepository;

    @Transactional
    public CategoryDto saveCat(NewCategoryDto newCategoryDto) {
        Category newCat = CategoryMapper.toModelFromDto(newCategoryDto);
        return CategoryMapper.fromModelToDto(catRepository.save(newCat));
    }

    @Transactional
    public void deleteCat(Long catId) {
        catRepository.deleteById(catId);
    }

    @Transactional
    public CategoryDto updateCat(Long id, NewCategoryDto newCategoryDto) {
        Category newCat = CategoryMapper.toModelFromDto(id,newCategoryDto);
        return CategoryMapper.fromModelToDto(catRepository.save(newCat));
    }

    @Transactional(readOnly = true)
    public List<CategoryDto> findCats(Integer from, Integer size) {
        List<Category> cats = catRepository.getCats(from,size);
        return cats.stream()
                .map(CategoryMapper::fromModelToDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public CategoryDto findCatById(Long id) {
        return CategoryMapper.fromModelToDto(catRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Категория не найдена")));
    }
}