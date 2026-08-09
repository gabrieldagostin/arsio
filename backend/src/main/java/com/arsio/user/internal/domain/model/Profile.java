package com.arsio.user.internal.domain.model;

import com.arsio.user.internal.domain.valueobject.*;

public class Profile {

    private final ProfileId id;
    private final UserId userId;
    private Username displayName;
    private Bio bio;
    private ProfileImageKey profileImageKey;
    private ProfileImageKey profileBannerKey;
    private Country country;

    public Profile(ProfileId id, UserId userId, Username displayName, Bio bio, ProfileImageKey profileImageKey, ProfileImageKey profileBannerKey, Country country) {
        this.id = id;
        this.userId = userId;
        this.displayName = displayName;
        this.bio = bio;
        this.profileImageKey = profileImageKey;
        this.profileBannerKey = profileBannerKey;
        this.country = country;
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

    public ProfileImageKey getProfileImageKey() {
        return profileImageKey;
    }

    public ProfileImageKey getProfileBannerKey() {
        return profileBannerKey;
    }

    public Country getCountry() {
        return country;
    }
}
