package com.ics.project.controllers.errors;

import java.util.List;

/**
 * Any response with errors that occur in the system will take the format of this class except validation errors
 *
 * @author Dr H
 */
public class ApiError {
    private List<String> errors;

    public ApiError(List<String> errors) {
        this.errors = errors;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
