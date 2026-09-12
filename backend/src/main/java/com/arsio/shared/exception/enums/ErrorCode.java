package com.arsio.shared.exception.enums;

public enum ErrorCode {

    GLOBAL_VALIDATION_ERROR("Global validation error"),
    INTERNAL_SERVER_ERROR("Internal server error"),
    VALIDATION_ERROR("Validation error"),
    BAD_REQUEST("Bad request"),
    METHOD_NOT_ALLOWED("Method not allowed"),
    UNSUPPORTED_MEDIA_TYPE("Unsupported Media Type"),
    FORBIDDEN("Forbidden"),
    INVALID_MONEY("Invalid Money"),

    AUTH_INVALID_CREDENTIALS("Invalid credentials"),
    AUTH_INVALID_TOKEN("Invalid token"),
    AUTH_INVALID_SESSION("Invalid session"),
    AUTH_PASSWORD_RESET_TOKEN_NOT_FOUND("Password reset token not found"),
    AUTH_SESSION_NOT_FOUND("Session not found"),
    AUTH_SESSION_NOT_ACTIVE("Session not active"),
    AUTH_JWT_GENERATION_ERROR("Jwt generation error"),
    AUTH_JWT_PARSING_ERROR("Jwt parsing error"),

    USER_INVALID_USER_ROLE("Invalid user role"),
    USER_INVALID_PASSWORD("Invalid password"),
    USER_INVALID_USERNAME("Invalid Username"),
    USER_INVALID_BIO("Invalid bio"),
    USER_INVALID_PROFILE_IMAGE_KEY("Invalid profile image key"),
    USER_NOT_FOUND("User not found"),
    USER_PROFILE_NOT_FOUND("User profile not found"),
    USER_EMAIL_MALFORMED("Email malformed"),
    USER_USERNAME_UNAVAILABLE("Username unavailable"),
    USER_EMAIL_ALREADY_EXISTS("Email already exists"),
    USER_FRIENDSHIP_NOT_FOUND("Friendship not found"),
    USER_INVALID_PROFILE_COUNTRY_CODE("Invalid profile country code"),
    USER_INVALID_FRIENDSHIP("Invalid friendship"),
    USER_INVALID_FRIENDSHIP_STATUS("Invalid friendship status"),

    GAME_NOT_FOUND("Game not found"),
    GAME_INVALID_TITLE("Invalid title"),
    GAME_INVALID_DESCRIPTION("Invalid description"),
    GAME_INVALID_STATUS("Invalid status"),
    GAME_INVALID_REQUIREMENT("Invalid requirements"),
    GAME_INVALID_MEDIA_TYPE("Invalid media type"),
    GAME_INVALID_GENRE_NAME("Invalid genre name"),
    GAME_INVALID_TAG_NAME("Invalid tag name"),

    NOTIFICATION_INVALID_MESSAGE("Invalid message"),
    NOTIFICATION_INVALID_TITLE("Invalid title");

    private final String defaultMessage;

    ErrorCode(String defaultMessage) {
        this.defaultMessage = defaultMessage;
    }

    public String getDefaultMessage() {
        return defaultMessage;
    }
}
