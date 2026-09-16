package com.arsio.developer.internal.application.facade;

import com.arsio.developer.api.exception.DeveloperNotFoundException;
import com.arsio.developer.api.facade.DeveloperFacade;
import com.arsio.developer.internal.domain.repository.DeveloperRepository;
import com.arsio.developer.internal.domain.valueobject.DeveloperId;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeveloperFacadeImpl implements DeveloperFacade {

    private final DeveloperRepository developers;

    public DeveloperFacadeImpl(DeveloperRepository developers) {
        this.developers = developers;
    }

    @Override
    public void checkExists(UUID id) {

        DeveloperId developerId = new DeveloperId(id);

        if (!developers.checkExists(developerId))
            throw new DeveloperNotFoundException();
    }
}
