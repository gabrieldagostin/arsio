package com.arsio.developer.internal.infra.persistance.adapter;

import com.arsio.developer.internal.domain.repository.DeveloperRepository;
import com.arsio.developer.internal.domain.valueobject.DeveloperId;
import com.arsio.developer.internal.infra.persistance.repository.SpringDataDeveloperRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class JpaDeveloperRepositoryAdapter implements DeveloperRepository {

    private final SpringDataDeveloperRepository developers;

    @Override
    public boolean checkExists(DeveloperId developerId) {
        return developers.existsById(developerId.value());
    }
}
