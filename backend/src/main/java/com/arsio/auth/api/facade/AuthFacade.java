package com.arsio.auth.api.facade;

public interface AuthFacade {

    String extractSubject(String token);
}
