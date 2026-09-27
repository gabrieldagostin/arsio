package com.arsio.game.internal.application.usecase;

import com.arsio.game.api.exception.TagNotFoundException;
import com.arsio.game.internal.application.command.UpdateTagCommand;
import com.arsio.game.internal.domain.model.Tag;
import com.arsio.game.internal.domain.repository.TagRepository;
import com.arsio.game.internal.infra.Controller.dto.response.GetTagResponse;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UpdateTagUseCase {

    private final TagRepository tags;

    public UpdateTagUseCase(TagRepository tags) {
        this.tags = tags;
    }

    public GetTagResponse execute(UpdateTagCommand command) {

        Tag tag = tags.findById(command.tagId())
                .orElseThrow(TagNotFoundException::new);

        tag.update(command.name());

        tags.save(tag);

        return new GetTagResponse(
                tag.getId().value(),
                tag.getName().value()
        );
    }
}
