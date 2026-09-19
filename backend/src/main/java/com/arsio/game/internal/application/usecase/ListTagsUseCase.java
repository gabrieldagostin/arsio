package com.arsio.game.internal.application.usecase;

import com.arsio.game.internal.domain.repository.TagRepository;
import com.arsio.game.internal.infra.Controller.dto.response.TagResponse;
import com.arsio.shared.pagination.PageResult;
import com.arsio.shared.pagination.Pagination;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ListTagsUseCase {

    private final TagRepository tags;

    public ListTagsUseCase(TagRepository tags) {
        this.tags = tags;
    }

    public PageResult<TagResponse> execute(String search, Pagination pagination) {

        return tags.findAll(search, pagination)
                .map(tag ->  {
                    return new TagResponse(
                            tag.getId().value(),
                            tag.getName().value()
                    );
                });
    }
}
