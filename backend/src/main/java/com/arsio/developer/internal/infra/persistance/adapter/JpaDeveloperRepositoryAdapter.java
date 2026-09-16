package com.arsio.developer.internal.infra.persistance.adapter;

import com.arsio.developer.internal.domain.repository.DeveloperRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class JpaDeveloperRepositoryAdapter implements DeveloperRepository {
}
