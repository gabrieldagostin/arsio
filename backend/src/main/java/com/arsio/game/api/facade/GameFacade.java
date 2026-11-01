package com.arsio.game.api.facade;

import com.arsio.game.api.dto.GetDeveloperGamesView;

import java.util.List;
import java.util.UUID;

public interface GameFacade {

    List<GetDeveloperGamesView> findByDeveloperId(UUID developerId);
}
