package com.arsio.game.internal.infra.persistence.mapper;

import com.arsio.config.mapper.CentralMapperConfig;
import com.arsio.game.internal.domain.model.Tag;
import com.arsio.game.internal.domain.valueobject.TagId;
import com.arsio.game.internal.domain.valueobject.TagName;
import com.arsio.game.internal.infra.persistence.entity.TagEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(config = CentralMapperConfig.class)
public interface TagEntityMapper {

    @Mapping(target = "active", ignore = true)
    Tag toDomain(TagEntity entity);

    @Mapping(target = "active", ignore = true)
    TagEntity toEntity(Tag domain);

    default UUID tagIdToUuid(TagId tagId) {
        return tagId.value();
    }

    default TagId uuidToTagId(UUID value) {
        return new TagId(value);
    }

    default String tagNameToString(TagName tagName) {
        return tagName.value();
    }

    default TagName stringToTagName(String value) {
        return new TagName(value);
    }
}
