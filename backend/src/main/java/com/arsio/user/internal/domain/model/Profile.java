package com.arsio.user.internal.domain.model;

import com.arsio.user.internal.domain.valueobject.*;

import java.util.UUID;

public class Profile {

    private final ProfileId id;
    private final UserId userId;
    private Username displayName;
    private Bio bio;
    private ObjectKey avatarObjectKey;
    private ObjectKey bannerObjectKey;
    private Country country;

    public Profile(ProfileId id, UserId userId, Username displayName, Bio bio, ObjectKey avatarObjectKey, ObjectKey bannerObjectKey, Country country) {
        this.id = id;
        this.userId = userId;
        this.displayName = displayName;
        this.bio = bio;
        this.avatarObjectKey = avatarObjectKey;
        this.bannerObjectKey = bannerObjectKey;
        this.country = country;
    }

    public static Profile create(UUID userId, String displayName) {
        return new Profile(
                ProfileId.generate(),
                new UserId(userId),
                new Username(displayName),
                null,
                null,
                null,
                null
        );
    }

    public ProfileId getId() {
        return id;
    }

    public UserId getUserId() {
        return userId;
    }

    public Username getDisplayName() {
        return displayName;
    }

    public Bio getBio() {
        return bio;
    }

    public ObjectKey getAvatarObjectKey() {
        return avatarObjectKey;
    }

    public ObjectKey getBannerObjectKey() {
        return bannerObjectKey;
    }

    public Country getCountry() {
        return country;
    }

    public void updateAvatarObjectKey(ObjectKey objectKey) {
        this.avatarObjectKey = objectKey;
    }

    public void updateBannerObjectKey(ObjectKey objectKey) {
        this.bannerObjectKey = objectKey;
    }

    public void updateBio(Bio bio) {
        this.bio = bio;
    }

    public void updateCountry(Country country) {
        this.country = country;
    }
}
