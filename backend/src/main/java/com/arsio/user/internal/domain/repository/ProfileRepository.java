package com.arsio.user.internal.domain.repository;

import com.arsio.user.internal.domain.model.Profile;
import com.arsio.user.internal.domain.valueobject.UserId;

import java.util.Optional;

public interface ProfileRepository {

    Optional<Profile> findByUserId(UserId userId);

    void save(Profile profile);
}
