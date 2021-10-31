package com.test.planda.services;

import androidx.annotation.Nullable;

public class InputValidationService {

    private static InputValidationService inputValidationService;

    public InputValidationService() {}

    public static synchronized InputValidationService getInstance() {
        if(inputValidationService == null) {
            inputValidationService = new InputValidationService();
        } return inputValidationService;
    }

    public boolean isInputValid(CharSequence email, String name, String password) {
        return isNameValid(name) && isEmailValid(email) && isPasswordValid(password);
    }

    //#hardcoded values, mijn ogen branden als fakkels in de nacht
    public boolean isNameValid(String name) {
        if (name == null || name.trim().length() < 3) {
            return false;
        } else {
            return true;
        }
    }

    public boolean isPasswordValid(String password) {
        if (password == null || password.trim().length() < 7) {
            return false;
        } else {
            return true;
        }
    }

    public boolean isEmailValid(CharSequence email) {
        if (email == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
        }
    }

    public String notEmpty(@Nullable String text) {
        if(text == null || text.isEmpty()) {
            return " ";
        }
        return text;
    }


}
