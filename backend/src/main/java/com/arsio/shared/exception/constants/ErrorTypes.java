package com.arsio.shared.exception.constants;

public final class ErrorTypes {

    private static final String BASE =
            "https://api.arsio.com/errors/";

    private ErrorTypes() {}

    public static final String GLOBAL_VALIDATION_ERROR = BASE + "global-validation-error";
    public static final String INTERNAL_SERVER_ERROR = BASE + "internal-server-error";
    public static final String VALIDATION_ERROR = BASE + "validation-error";
    public static final String BAD_REQUEST = BASE + "bad-request";
    public static final String METHOD_NOT_ALLOWED = BASE + "method-not-allowed";
    public static final String UNSUPPORTED_MEDIA_TYPE = BASE + "unsupported-media-type";
    public static final String FORBIDDEN = BASE + "forbidden";
    public static final String INVALID_MONEY = BASE + "invalid-money";

    public static final String AUTH_INVALID_CREDENTIALS = BASE + "invalid-credentials";
    public static final String AUTH_INVALID_TOKEN = BASE + "invalid-token";
    public static final String AUTH_INVALID_SESSION = BASE + "invalid-session";
    public static final String AUTH_PASSWORD_RESET_TOKEN_NOT_FOUND = BASE + "password-reset-token-not-found";
    public static final String AUTH_SESSION_NOT_FOUND = BASE + "session-not-found";
    public static final String AUTH_SESSION_NOT_ACTIVE = BASE + "session-not-active";
    public static final String AUTH_JWT_GENERATION_ERROR = BASE + "jwt-generation-error";
    public static final String AUTH_JWT_PARSING_ERROR = BASE + "jwt-parsing-error";

    public static final String USER_INVALID_USER_ROLE = BASE + "invalid-user-role";
    public static final String USER_INVALID_PASSWORD = BASE + "invalid-password";
    public static final String USER_INVALID_USERNAME = BASE + "invalid-username";
    public static final String USER_INVALID_BIO = BASE + "invalid-bio";
    public static final String USER_INVALID_PROFILE_IMAGE_KEY = BASE + "invalid-profile-image-key";
    public static final String USER_NOT_FOUND = BASE + "user-not-found";
    public static final String USER_PROFILE_NOT_FOUND = BASE + "user-profile-not-found";
    public static final String USER_EMAIL_MALFORMED = BASE + "email-malformed";
    public static final String USER_USERNAME_UNAVAILABLE = BASE + "username-unavailable";
    public static final String USER_EMAIL_ALREADY_EXISTS = BASE + "email-already-exists";
    public static final String USER_FRIENDSHIP_NOT_FOUND = BASE + "friendship-not-found";
    public static final String USER_INVALID_PROFILE_COUNTRY_CODE = BASE + "invalid-profile-country-code";
    public static final String USER_INVALID_FRIENDSHIP_STATUS = BASE + "invalid-friendship-status";
    public static final String USER_INVALID_FRIENDSHIP =  BASE + "invalid-friendship";

    public static final String GAME_NOT_FOUND = BASE + "game-not-found";
    public static final String GAME_INVALID_TITLE = BASE + "game-invalid-title";
    public static final String GAME_INVALID_DESCRIPTION = BASE + "game-invalid-description";
    public static final String GAME_INVALID_STATUS = BASE + "game-invalid-game-status";
    public static final String GAME_INVALID_REQUIREMENT = BASE + "game-invalid-requirement";
    public static final String GAME_INVALID_MEDIA_TYPE = BASE + "game-invalid-media-type";
    public static final String GAME_INVALID_GENRE_NAME = BASE + "game-invalid-genre-name";
    public static final String GAME_INVALID_TAG_NAME = BASE + "game-invalid-tag-name";

    public static final String NOTIFICATION_INVALID_MESSAGE = BASE + "invalid-message";
    public static final String NOTIFICATION_INVALID_TITLE = BASE + "invalid-title";

}
