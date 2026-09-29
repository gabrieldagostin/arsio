package com.arsio.game.internal.application.usecase;

import com.arsio.game.internal.domain.repository.TagRepository;
import com.arsio.game.internal.domain.valueobject.GameId;
import com.arsio.game.internal.infra.Controller.dto.response.GetTagResponse;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class GetGameTagsUseCase {

    private final TagRepository tags;

    public GetGameTagsUseCase(TagRepository tags) {
        this.tags = tags;
    }

    public Set<GetTagResponse> execute(UUID value) {

        GameId gameId = new GameId(value);

        return tags.findAllByGameId(gameId)
                .stream()
                .map(tag -> {
                    return new GetTagResponse(
                            tag.getId().value(),
                            tag.getName().value()
                    );
                })
                .collect(Collectors.toSet());
    }
}
