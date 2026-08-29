package com.arsio.user.internal.infra.persistance.mapper;

import com.arsio.user.internal.domain.model.Profile;
import com.arsio.user.internal.domain.valueobject.*;
import com.arsio.user.internal.infra.persistance.entity.ProfileEntity;
import com.arsio.user.internal.infra.persistance.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.UUID;

@Mapper(componentModel = "spring",
typeConversionPolicy = ReportingPolicy.ERROR)
public interface ProfileEntityMapper {

    @Mapping(target = "id", source = "profileEntity.id")
    @Mapping(target = "userId", source = "profileEntity.user.id")
    Profile toDomain(ProfileEntity profileEntity);

    @Mapping(target = "id", source = "profile.id")
    @Mapping(target = "user", source = "userEntity")
    @Mapping(target = "createdAt", ignore = true)
    ProfileEntity toEntity(Profile profile, UserEntity userEntity);

    default UUID profileIdToUuid(ProfileId profileId) {
        return profileId.value();
    }

    default ProfileId uuidToProfileId(UUID value) {
        return new ProfileId(value);
    }

    default UUID userIdToUuid(UserId userId) {
        return userId.value();
    }

    default UserId uuidToUserId(UUID value) {
        return new UserId(value);
    }

    default String usernameToString(Username username) {
        return username.value();
    }

    default Username stringToUsername(String value) {
        return new Username(value);
    }

    default String bioToString(Bio bio) {
        if (bio == null) return null;
        return bio.value();
    }

    default Bio stringToBio(String value) {
        return new Bio(value);
    }

    default String profileImageKeyToString(ObjectKey objectKey) {
        if (objectKey == null) return null;
        return objectKey.value();
    }

    default ObjectKey stringToProfileImageKey(String value) {
        return new ObjectKey(value);
    }

    default String countryToString(Country country) {
        if (country == null) return null;
        return country.getCode();
    }

    default Country stringToCountry(String value) {
        return Country.valueOf(value);
    }
}
