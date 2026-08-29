package com.arsio.user.internal.application.service;

import com.arsio.user.internal.application.command.UpdateProfileCountryCommand;
import com.arsio.user.internal.domain.exception.UserNotFoundException;
import com.arsio.user.internal.domain.model.Profile;
import com.arsio.user.internal.domain.repository.ProfileRepository;
import com.arsio.user.internal.domain.valueobject.Country;
import com.arsio.user.internal.domain.valueobject.UserId;
import com.arsio.user.internal.infra.controller.dto.response.UpdateProfileCountryResponse;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
public class UpdateProfileCountryService {

    private final ProfileRepository profiles;

    public UpdateProfileCountryService(ProfileRepository profiles) {
        this.profiles = profiles;
    }

    public UpdateProfileCountryResponse execute(UUID id, UpdateProfileCountryCommand command) {

        UserId userId = new UserId(id);

        Profile profile = profiles.findByUserId(userId)
                .orElseThrow(UserNotFoundException::new);

        profile.updateCountry(command.newCountry());

        profiles.save(profile);

        return new UpdateProfileCountryResponse(
                profile.getCountry().getFlag() + profile.getCountry().getCode()
        );
    }
}
