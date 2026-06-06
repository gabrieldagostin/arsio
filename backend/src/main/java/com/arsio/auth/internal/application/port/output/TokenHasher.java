package com.arsio.auth.internal.application.port.output;

public interface TokenHasher {

    String hash(String token);
}
