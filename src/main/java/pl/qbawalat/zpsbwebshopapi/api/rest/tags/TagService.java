package pl.qbawalat.zpsbwebshopapi.api.rest.tags;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagService {
    private final TagRepository tagRepository;

    public List<Tag> getAll() {
        return tagRepository.findAll();
    }

    public void save(Tag tag) {
        tagRepository.save(tag);
    }
}
