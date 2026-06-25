package com.arsio.auth.internal.infra.persistance.adapter;

import com.arsio.auth.internal.domain.repository.PasswordResetTokenRepository;
import com.arsio.auth.internal.domain.model.PasswordResetToken;
import com.arsio.auth.internal.infra.persistance.entity.PasswordResetTokenEntity;
import com.arsio.auth.internal.infra.persistance.mapper.PasswordResetTokenEntityMapper;
import com.arsio.auth.internal.infra.persistance.repository.SpringDataPasswordResetTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class JpaPasswordResetTokenAdapter implements PasswordResetTokenRepository {

    private final PasswordResetTokenEntityMapper mapper;
    private final SpringDataPasswordResetTokenRepository passwordResetTokens;

    @Override
    public void save(PasswordResetToken passwordResetToken) {
        PasswordResetTokenEntity entity = mapper.toEntity(passwordResetToken);
        passwordResetTokens.save(entity);
    }

    @Override
    public PasswordResetToken findByToken(String token) {
        PasswordResetTokenEntity entity = passwordResetTokens.findByPasswordToken(token);
        return mapper.toDomain(entity);
    }
}
