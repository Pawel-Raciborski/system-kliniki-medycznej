package org.back.systemklinikimedycznej.receptionist.services;

import lombok.RequiredArgsConstructor;
import org.back.systemklinikimedycznej.account.repositories.entities.Account;
import org.back.systemklinikimedycznej.account.services.AccountService;
import org.back.systemklinikimedycznej.personal_details.repositories.entities.PersonalDetails;
import org.back.systemklinikimedycznej.personal_details.services.PersonalDetailsService;
import org.back.systemklinikimedycznej.receptionist.controller.dto.RegisterReceptionistForm;
import org.back.systemklinikimedycznej.receptionist.exceptions.ReceptionistNotFoundException;
import org.back.systemklinikimedycznej.receptionist.repositories.ReceptionistRepository;
import org.back.systemklinikimedycznej.receptionist.repositories.entities.Receptionist;
import org.back.systemklinikimedycznej.receptionist.util.ReceptionistManagementUtil;
import org.back.systemklinikimedycznej.role.enums.BasicAppRoles;
import org.back.systemklinikimedycznej.role.services.AccountRoleService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReceptionistService {
    private final AccountService accountService;
    private final PersonalDetailsService personalDetailsService;
    private final ReceptionistRepository receptionistRepository;
    private final AccountRoleService accountRoleService;
    @Transactional
    public Receptionist register(RegisterReceptionistForm registerReceptionistForm) {
        Account createdReceptionistAccount = accountService.create(registerReceptionistForm.registerAccountData());
        PersonalDetails personalDetails = personalDetailsService.create(registerReceptionistForm.personalDetails());

        Receptionist receptionistToAdd = ReceptionistManagementUtil.buildReceptionist(createdReceptionistAccount, personalDetails,registerReceptionistForm.dateOfEmployment());

        accountRoleService.processAccountRoleCreation(createdReceptionistAccount, BasicAppRoles.RECEPTIONIST.name());
        return receptionistRepository.save(receptionistToAdd);
    }

    public void delete(String email) {
        Receptionist receptionistToRemove = findByEmail(email);
        PersonalDetails personalDetailsToRemove = receptionistToRemove.getPersonalDetails();

        receptionistRepository.delete(receptionistToRemove);
        personalDetailsService.deletePersonalDetails(personalDetailsToRemove);
    }

    private Receptionist findByEmail(String email) {
        return receptionistRepository.findByAccount_Email(email)
                .orElseThrow(() -> new ReceptionistNotFoundException("Nie znaleziono recepcjonisty z podanym emailem!", HttpStatus.NOT_FOUND));
    }
}
