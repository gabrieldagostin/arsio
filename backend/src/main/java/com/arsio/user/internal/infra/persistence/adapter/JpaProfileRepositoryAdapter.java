package com.arsio.user.internal.infra.persistence.adapter;

import com.arsio.user.internal.domain.model.Profile;
import com.arsio.user.internal.domain.repository.ProfileRepository;
import com.arsio.user.internal.domain.valueobject.UserId;
import com.arsio.user.internal.infra.persistence.entity.ProfileEntity;
import com.arsio.user.internal.infra.persistence.entity.UserEntity;
import com.arsio.user.internal.infra.persistence.mapper.ProfileEntityMapper;
import com.arsio.user.internal.infra.persistence.repository.SpringDataProfileRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JpaProfileRepositoryAdapter implements ProfileRepository {

    private final SpringDataProfileRepository profiles;
    private final ProfileEntityMapper mapper;
    private final EntityManager entityManager;

    @Override
    public Optional<Profile> findByUserId(UserId userId) {
        return profiles.findByUser_Id(userId.value())
                .map(mapper::toDomain);
    }

    @Override
    public void save(Profile profile) {

        UserEntity userEntity = entityManager.getReference(
                UserEntity.class,
                profile.getUserId().value()
        );

        ProfileEntity profileEntity = mapper.toEntity(profile, userEntity);

        profiles.save(profileEntity);
    }
}
