package com.arsio.user.internal.infra.persistance.adapter;

import com.arsio.user.internal.domain.model.Profile;
import com.arsio.user.internal.domain.repository.ProfileRepository;
import com.arsio.user.internal.domain.valueobject.UserId;
import com.arsio.user.internal.infra.persistance.entity.ProfileEntity;
import com.arsio.user.internal.infra.persistance.entity.UserEntity;
import com.arsio.user.internal.infra.persistance.mapper.ProfileEntityMapper;
import com.arsio.user.internal.infra.persistance.repository.SpringDataProfileRepository;
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
