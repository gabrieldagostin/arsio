package com.arsio.user.internal.infra.persistance.adapter;

import com.arsio.user.internal.domain.model.Profile;
import com.arsio.user.internal.domain.repository.ProfileRepository;
import com.arsio.user.internal.domain.valueobject.UserId;
import com.arsio.user.internal.infra.persistance.mapper.ProfileEntityMapper;
import com.arsio.user.internal.infra.persistance.repository.SpringDataProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JpaProfileRepositoryAdapter implements ProfileRepository {

    private final SpringDataProfileRepository profiles;
    private final ProfileEntityMapper mapper;

    @Override
    public Optional<Profile> findByUserId(UserId userId) {
        return profiles.findByUserId(userId)
                .map(mapper::toDomain);
    }
}
