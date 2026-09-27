package com.arsio.game.internal.application.usecase;

import com.arsio.game.api.exception.TagNotFoundException;
import com.arsio.game.internal.domain.model.Tag;
import com.arsio.game.internal.domain.repository.TagRepository;
import com.arsio.game.internal.domain.valueobject.TagId;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
public class DeleteTagUseCase {

    private final TagRepository tags;

    public DeleteTagUseCase(TagRepository tags) {
        this.tags = tags;
    }

    public void execute(UUID value) {

        TagId tagId = new TagId(value);

        Tag tag = tags.findById(tagId)
                .orElseThrow(TagNotFoundException::new);

        tag.deactivate();

        tags.save(tag);
    }
}
