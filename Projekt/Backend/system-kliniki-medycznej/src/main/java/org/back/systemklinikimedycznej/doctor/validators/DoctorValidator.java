package org.back.systemklinikimedycznej.doctor.validators;

import lombok.experimental.UtilityClass;
import org.back.systemklinikimedycznej.doctor.exceptions.PwzNumberException;
import org.springframework.http.HttpStatus;

@UtilityClass
public class DoctorValidator {

    public static void checkPwzNumberNotExist(boolean existPwzNumber){
        if(existPwzNumber){
            throw new PwzNumberException("Podany numer pwz już zajęty!", HttpStatus.CONFLICT);
        }
    }
}
