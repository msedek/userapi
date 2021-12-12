package com.globallogic.userapi.entities;

public class Constants {

    private Constants() { throw new IllegalStateException("Utility class"); }

    public static final String NO_BODY = "Bad request no body sent";
    public static final boolean ACTIVATE_USER = true;
    public static final String VALID_JWT_PATTERN = "^[A-Za-z0-9\\-_=]+\\.[A-Za-z0-9\\-_=]+(\\.[A-Za-z0-9\\-_.+/=]+)?$";

    public static final String MAIL_REGEX = "^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$";
    public static final String INVALID_MAIL = "Bad request invalid email format";
    public static final String INVALID_MAIL_NULL = "Bad request invalid email, email can't be null";

    public static final String PASSWORD_REGEX = "^(?!(?:[^0-9]*[0-9]){3})(?=^(?:\\D*\\d\\D*){2}$)(?=.*[a-z])[a-z0-9]*[A-Z][a-z0-9]*$";
    public static final String INVALID_PASSWORD = "Bad request invalid password, make sure is 1 Uppercase letter, 2 digits and N lowercase letters";
    public static final String INVALID_PASSWORD_NULL = "Bad request invalid password, password can't be null";

    public static final String EMAIL_EXCEPTION_KEYWORD = "EMAIL";
    public static final String REGISTERED_EMAIL = "El correo ya registrado";
}
