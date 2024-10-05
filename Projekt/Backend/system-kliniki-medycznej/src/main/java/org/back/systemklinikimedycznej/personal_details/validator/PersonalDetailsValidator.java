package org.back.systemklinikimedycznej.personal_details.validator;

import org.back.systemklinikimedycznej.exceptions.GlobalAppException;
import org.back.systemklinikimedycznej.personal_details.exceptions.PeselExistsException;
import org.back.systemklinikimedycznej.personal_details.exceptions.PhoneExistsException;
import org.springframework.http.HttpStatus;

public class PersonalDetailsValidator {

    public static void checkPeselNotExist(boolean existPersonalDetailsForPesel){
        if (existPersonalDetailsForPesel) {
            throw new PeselExistsException("Błędny pesel", HttpStatus.CONFLICT);
        }
    }

    public static void checkPhoneNumberNotExist(boolean existPersonalDetailsWithPhone) {
        if(existPersonalDetailsWithPhone){
            throw new PhoneExistsException("Numer telefonu jest zajęty!", HttpStatus.CONFLICT);
        }
    }


    public static void checkPeselExist(boolean peselNotExist) {
        if (peselNotExist) {
            throw new GlobalAppException("Nie znaleziono danych z podanym PESELEM", HttpStatus.CONFLICT);
        }

    }
}
