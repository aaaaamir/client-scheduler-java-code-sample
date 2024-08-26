package physio_clinic_tests;

import java.time.LocalTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import physio_clinic.Appointment;
import physio_clinic.AppointmentType;

class AppointmentTests {

	@Test
	void testIsConflictingHelperMethod() {
		AppointmentType defaultAptType = AppointmentType.STANDARD;
		LocalTime arbitraryStartDate = LocalTime.now();
		Appointment apt1 = new Appointment(defaultAptType, arbitraryStartDate);
		Appointment conflictingApt = new Appointment(defaultAptType, arbitraryStartDate.plusMinutes(30));
		Appointment nonConflictingApt = new Appointment(defaultAptType, arbitraryStartDate.plusMinutes(90));
		Assertions.assertTrue(apt1.isConflicting(conflictingApt) && !apt1.isConflicting(nonConflictingApt));
	}

}
