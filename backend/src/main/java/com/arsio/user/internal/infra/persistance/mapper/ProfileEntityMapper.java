package com.arsio.user.internal.infra.persistance.mapper;

import com.arsio.user.internal.domain.model.Profile;
import com.arsio.user.internal.domain.valueobject.*;
import com.arsio.user.internal.infra.persistance.entity.ProfileEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.UUID;

@Mapper(componentModel = "spring",
typeConversionPolicy = ReportingPolicy.ERROR)
public interface ProfileEntityMapper {

    Profile toDomain(ProfileEntity profileEntity);

    @Mapping(target = "createdAt", ignore = true)
    ProfileEntity toEntity(Profile profile);

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
        return bio.value();
    }

    default Bio stringToBio(String value) {
        return new Bio(value);
    }

    default String profileImageKeyToString(ProfileImageKey profileImageKey) {
        return profileImageKey.value();
    }

    default ProfileImageKey stringToProfileImageKey(String value) {
        return new ProfileImageKey(value);
    }

    default String countryToString(Country country) {
        return country.getCode();
    }

    default Country stringToCountry(String value) {
        return Country.valueOf(value);
    }
}
