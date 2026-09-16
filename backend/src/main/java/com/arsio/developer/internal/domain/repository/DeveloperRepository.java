package com.arsio.developer.internal.domain.repository;

import com.arsio.developer.internal.domain.valueobject.DeveloperId;

public interface DeveloperRepository {

    boolean checkExists(DeveloperId developerId);
}
