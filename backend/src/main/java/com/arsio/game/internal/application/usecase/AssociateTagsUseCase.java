package com.arsio.game.internal.application.usecase;

import com.arsio.game.api.exception.GameNotFoundException;
import com.arsio.game.api.exception.TagNotFoundException;
import com.arsio.game.internal.application.command.AssociateTagsCommand;
import com.arsio.game.internal.domain.model.Game;
import com.arsio.game.internal.domain.model.Tag;
import com.arsio.game.internal.domain.repository.GameRepository;
import com.arsio.game.internal.domain.repository.TagRepository;
import com.arsio.game.internal.domain.valueobject.GameId;
import com.arsio.game.internal.infra.Controller.dto.response.TagResponse;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class AssociateTagsUseCase {

    private final TagRepository tags;
    private final GameRepository games;

    public AssociateTagsUseCase(TagRepository tags, GameRepository games) {
        this.tags = tags;
        this.games = games;
    }

    public Set<TagResponse> execute(UUID id, AssociateTagsCommand command) {

        GameId gameId = new GameId(id);

        Game game = games.findById(gameId)
                .orElseThrow(GameNotFoundException::new);

        Set<Tag> tagSet = tags.findAllByIds(command.tagIds());

        if (tagSet.size() != command.tagIds().size())
            throw new TagNotFoundException();

        game.associateTags(command.tagIds());

        games.save(game);

        return tagSet.stream()
                .map(tag -> {
                    return new TagResponse(
                            tag.getId().value(),
                            tag.getName().value()
                    );
                })
                .collect(Collectors.toSet());
    }
}
