package com.arsio.auth.api.facade;

import java.util.UUID;

public interface AuthFacade {

    UUID extractSubject(String token);
}
