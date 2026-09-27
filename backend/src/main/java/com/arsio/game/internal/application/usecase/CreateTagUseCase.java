package com.arsio.game.internal.application.usecase;

import com.arsio.game.internal.application.command.CreateTagCommand;
import com.arsio.game.internal.domain.model.Tag;
import com.arsio.game.internal.domain.repository.TagRepository;
import com.arsio.game.internal.infra.Controller.dto.response.GetTagResponse;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CreateTagUseCase {

    private final TagRepository tags;

    public CreateTagUseCase(TagRepository tags) {
        this.tags = tags;
    }

    public GetTagResponse execute(CreateTagCommand command) {

        Tag tag = Tag.create(command.name());

        tags.save(tag);

        return new GetTagResponse(
                tag.getId().value(),
                tag.getName().value()
        );
    }
}
