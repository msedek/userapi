package com.globallogic.userapi.entities;

public class Constants {

    public static final String NO_BODY = "Bad request no body sent";
    public static final String MISSING_INFO = "Bad request missing information";

    public static final String MAIL_REGEX = "[a-z]+@[a-z]+\\.[cl]{2}";
    public static final String INVALID_MAIL = "Bad request invalid email format";

    public static final String PASSWORD_REGEX = "^(?!(?:[^0-9]*[0-9]){3})(?=^(?:\D*\d\D*){2}$)(?=.*[a-z])[a-z0-9]*[A-Z][a-z0-9]*$";
    public static final String INVALID_PASSWORD = "Bad request invalid password, make sure is 1 Uppercase letter, 2 digits and N lowercase letters";

    public static final String EMAIL_EXCEPTION_KEYWORD = "EMAIL";
    public static final String REGISTERED_EMAIL = "El correo ya registrado";
}
