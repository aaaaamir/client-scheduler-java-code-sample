package physio_clinic_tests;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import physio_clinic.WorkingDay;
import physio_clinic.Appointment;
import physio_clinic.AppointmentType;
import physio_clinic.Practitioner;

class PractitionerTests {
	
	private static Practitioner practitioner;
	private static final int PRACTITIONER_START_HOUR = 9;
	private static final int PRACTITIONER_END_HOUR = 17;
	
	@BeforeEach
    void setupPractitioner() {
		List<WorkingDay> workingDays = new ArrayList<>();
		LocalTime practitionerStartTime = LocalTime.of(PRACTITIONER_START_HOUR, 0);
		LocalTime practitionerEndTime = LocalTime.of(PRACTITIONER_END_HOUR, 0);
		practitioner = new Practitioner(workingDays, practitionerStartTime, practitionerEndTime);
    }
	
	@Test
	void testIsAppointWithinWorkingHours() {
		AppointmentType defaultAptType = AppointmentType.STANDARD;
		Appointment validApt = new Appointment(defaultAptType, LocalTime.of(PRACTITIONER_START_HOUR + 1, 0));
		Appointment tooEarlyApt = new Appointment(defaultAptType, LocalTime.of(PRACTITIONER_START_HOUR - 1, 30));
		Appointment tooLateApt = new Appointment(defaultAptType, LocalTime.of(PRACTITIONER_END_HOUR - 1, 30));
		Assertions.assertTrue(practitioner.isAppointWithinWorkingHours(validApt) &&
				!practitioner.isAppointWithinWorkingHours(tooEarlyApt) &&
				!practitioner.isAppointWithinWorkingHours(tooLateApt));
	}
	
	@Test
	void testIsAppointNotOnHalfHour() {
		AppointmentType defaultAptType = AppointmentType.STANDARD;
		Appointment invalidApt = new Appointment(defaultAptType, LocalTime.of(PRACTITIONER_START_HOUR, 15));
		Assertions.assertFalse(practitioner.isAppointTimeDivisibleByHalfHour(invalidApt));
	}
	
	@Test
	void testBookingSuccess() {
		AppointmentType defaultAptType = AppointmentType.STANDARD;
		Appointment validApt = new Appointment(defaultAptType, LocalTime.of(PRACTITIONER_START_HOUR + 1, 0));
		Assertions.assertDoesNotThrow(() -> practitioner.bookAppoint(validApt, LocalDate.now().plusDays(1)));
	}
	
	@Test
	void testBookingTooEarlyInTimeFailure() {
		AppointmentType defaultAptType = AppointmentType.STANDARD;
		Appointment tooEarlyInTimeApt = new Appointment(defaultAptType, LocalTime.now());
		Assertions.assertThrows(Exception.class, () -> practitioner.bookAppoint(tooEarlyInTimeApt, LocalDate.now()));
	}
	
	@Test
	void testBookingConflictionFailure() {
		AppointmentType defaultAptType = AppointmentType.STANDARD;
		Appointment validApt = new Appointment(defaultAptType, LocalTime.of(PRACTITIONER_START_HOUR + 1, 0));
		Appointment conflictingApt = new Appointment(defaultAptType, LocalTime.of(PRACTITIONER_START_HOUR + 1, 30));
		LocalDate bookingDay = LocalDate.now().plusDays(1);
		try { practitioner.bookAppoint(validApt, bookingDay); }
		catch (Exception e) { Assertions.fail("Test was not able to run. Ensure testBookingSuccess() passes and rerun. Error msg: " + e.getMessage()); }
		Assertions.assertThrows(Exception.class, () -> practitioner.bookAppoint(conflictingApt, bookingDay));
	}

}
