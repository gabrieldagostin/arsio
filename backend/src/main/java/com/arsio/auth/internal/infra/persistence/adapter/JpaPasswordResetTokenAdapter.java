package com.arsio.auth.internal.infra.persistence.adapter;

import com.arsio.auth.internal.domain.repository.PasswordResetTokenRepository;
import com.arsio.auth.internal.domain.model.PasswordResetToken;
import com.arsio.auth.internal.domain.valueobject.PasswordToken;
import com.arsio.auth.internal.infra.persistence.entity.PasswordResetTokenEntity;
import com.arsio.auth.internal.infra.persistence.entity.UserAuthEntity;
import com.arsio.auth.internal.infra.persistence.mapper.PasswordResetTokenEntityMapper;
import com.arsio.auth.internal.infra.persistence.repository.SpringDataPasswordResetTokenRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JpaPasswordResetTokenAdapter implements PasswordResetTokenRepository {

    private final PasswordResetTokenEntityMapper mapper;
    private final SpringDataPasswordResetTokenRepository passwordResetTokens;
    private final EntityManager entityManager;

    @Override
    public void save(PasswordResetToken passwordResetToken) {

        UserAuthEntity userAuthEntity = entityManager.getReference(
                UserAuthEntity.class,
                passwordResetToken.getUserId()
        );

        PasswordResetTokenEntity entity = mapper.toEntity(passwordResetToken,  userAuthEntity);

        passwordResetTokens.save(entity);
    }

    @Override
    public Optional<PasswordResetToken> findByToken(PasswordToken token) {
        return passwordResetTokens.findByPasswordToken(token.value())
                .map(mapper::toDomain);
    }
}
