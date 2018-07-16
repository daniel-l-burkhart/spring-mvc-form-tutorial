package com.daniel.form.validator;

import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class to validate email address
 *
 * @author daniel
 * @version 1.0
 */
@Component("emailValidator")
public class EmailValidator {

    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private Pattern pattern;
    private Matcher matcher;

    /**
     * Constructor that creates pattern variable from our string regex pattern.
     */
    public EmailValidator() {
        this.pattern = Pattern.compile(EMAIL_PATTERN);
    }

    /**
     * Validates email based on our pattern
     *
     * @param email The passed in email string
     * @return True if valid, false otherwise
     */
    public boolean valid(final String email) {

        this.matcher = this.pattern.matcher(email);
        return matcher.matches();

    }
}