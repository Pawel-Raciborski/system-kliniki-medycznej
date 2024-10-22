package org.back.systemklinikimedycznej.appointment.mappers;

import org.back.systemklinikimedycznej.appointment.controllers.dto.AppointmentInfo;
import org.back.systemklinikimedycznej.appointment.repositories.entities.Appointment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AppointmentMapper {

    AppointmentMapper APPOINTMENT_MAPPER = Mappers.getMapper(AppointmentMapper.class);

    @Mappings({
            @Mapping(target = "status",source = "status.appointmentStatusName")
    })
    AppointmentInfo mapToAppointmentInfo(Appointment appointment);
}
