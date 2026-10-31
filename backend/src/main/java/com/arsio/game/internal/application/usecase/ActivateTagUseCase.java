package com.arsio.game.internal.application.usecase;

import com.arsio.game.api.exception.TagNotFoundException;
import com.arsio.game.internal.domain.model.Tag;
import com.arsio.game.internal.domain.repository.TagRepository;
import com.arsio.game.internal.domain.valueobject.TagId;
import com.arsio.game.internal.infra.Controller.dto.response.GetTagResponse;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
public class ActivateTagUseCase {

    private final TagRepository tags;

    public ActivateTagUseCase(TagRepository tags) {
        this.tags = tags;
    }

    public GetTagResponse execute(UUID value) {

        TagId tagId = new TagId(value);

        Tag tag = tags.findById(tagId)
                .orElseThrow(TagNotFoundException::new);

        tag.activate();

        tags.save(tag);

        return new GetTagResponse(
                tag.getId().value(),
                tag.getName().value()
        );
    }
}
