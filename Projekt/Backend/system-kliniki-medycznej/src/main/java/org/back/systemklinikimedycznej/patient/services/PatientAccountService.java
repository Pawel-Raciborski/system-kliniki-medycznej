package org.back.systemklinikimedycznej.patient.services;

import org.back.systemklinikimedycznej.account.exceptions.AccountException;
import org.back.systemklinikimedycznej.account.repositories.entities.Account;
import org.back.systemklinikimedycznej.patient.repositories.entities.Patient;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PatientAccountService {

    public Account findAccount(Patient patient){
        return patient.getAccount();
    }
}
