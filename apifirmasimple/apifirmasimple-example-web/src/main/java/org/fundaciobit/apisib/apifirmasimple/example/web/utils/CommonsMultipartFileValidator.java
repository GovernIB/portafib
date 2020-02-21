package org.fundaciobit.apisib.apifirmasimple.example.web.utils;

import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * 
 * TODO MOURE A UNA LLIBRERIA
 * 
 * Implementation for the @ValidMultipartFile annotation; for validating a property of type
 * CommonsMultipartFile.
 * 
 * 
 */
public class CommonsMultipartFileValidator implements ConstraintValidator<ValidMultipartFile, CommonsMultipartFile> {

    private ValidMultipartFile constraintAnnotation;

    @Override
    public void initialize(ValidMultipartFile constraintAnnotation) {
        this.constraintAnnotation = constraintAnnotation;
    }
    
    
    public class TimeHolder {
        
        @Override
        public String toString() {
            return String.valueOf(new Date().getTime());
        }
        
    }
    
    

    @Override
    public boolean isValid(CommonsMultipartFile value, ConstraintValidatorContext context) {

        boolean isValid = true;

        if (value == null) {
            isValid = !constraintAnnotation.required();
        } else if (constraintAnnotation.required() && value.getSize() == 0) {
            isValid = false;
        } else if (constraintAnnotation.maxSize() >= 0 && value.getSize() > constraintAnnotation.maxSize()) {
            isValid = false;
        }

        return isValid;
    }
}