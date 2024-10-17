package org.back.systemklinikimedycznej.doctor.util;

import lombok.experimental.UtilityClass;
import org.back.systemklinikimedycznej.account.repositories.entities.Account;
import org.back.systemklinikimedycznej.doctor.controller.dto.RegisterDoctorDetails;
import org.back.systemklinikimedycznej.doctor.controller.dto.DoctorInfo;
import org.back.systemklinikimedycznej.doctor.controller.dto.DoctorsInfo;
import org.back.systemklinikimedycznej.doctor.mapper.DoctorMapper;
import org.back.systemklinikimedycznej.doctor.repositories.entities.Doctor;
import org.back.systemklinikimedycznej.personal_details.repositories.entities.PersonalDetails;

import java.util.List;

@UtilityClass
public class DoctorManagerUtil {

    public static Doctor buildDoctor(RegisterDoctorDetails registerDoctorDetails, Account createdDoctorAccount, PersonalDetails doctorPersonalDetails) {
        return Doctor.builder()
                .account(createdDoctorAccount)
                .personalDetails(doctorPersonalDetails)
                .description(registerDoctorDetails.description())
                .pwzNumber(registerDoctorDetails.pwzNumber())
                .dateOfEmployment(registerDoctorDetails.dateOfEmployment())
                .build();
    }

    public static DoctorsInfo wrapIntoDoctorsInfo(List<Doctor> pagedDoctors) {
        List<DoctorInfo> doctorInfos = pagedDoctors.stream().map(DoctorMapper.INSTANCE::mapToDoctorInfo).toList();

        return DoctorsInfo.builder()
                .doctors(doctorInfos)
                .build();
    }
}
