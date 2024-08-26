package physio_clinic;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Practitioner {

	List<WorkingDay> workingDays;
	private LocalTime practitionerStartTime;
	private LocalTime practitionerEndTime;
	
	public Practitioner(List<WorkingDay> workingDays, LocalTime practitionerStartTime, LocalTime practitionerEndTime) {
		this.workingDays = workingDays;
		this.practitionerStartTime = practitionerStartTime;
		this.practitionerEndTime = practitionerEndTime;
	}

	public List<WorkingDay> getWorkingDays() {
		return workingDays;
	}

	public void setWorkingDays(List<WorkingDay> workingDays) {
		this.workingDays = workingDays;
	}

	public void bookAppoint(Appointment apt, LocalDate inputDay) throws Exception {
		addNewWorkingDayIfOmitted(inputDay);
		WorkingDay relevantWorkingDay = getRelevantWorkingDay(inputDay);
		if (!isAppointWithinWorkingHours(apt))
			throw new Exception(PractitionerErrorMsg.NOT_WITHIN_WORKING_HOURS.getErrorMsg());
		else if (!isAppointTimeFree(apt, relevantWorkingDay))
			throw new Exception(PractitionerErrorMsg.TIME_NOT_AVAILABLE.getErrorMsg());
		else if (isAppointTooEarlyInTime(apt, inputDay))
			throw new Exception(PractitionerErrorMsg.APPOINTMENT_TOO_CLOSE.getErrorMsg());
		else if (!isAppointTimeDivisibleByHalfHour(apt))
			throw new Exception(PractitionerErrorMsg.NOT_ENDING_IN_HALF_HOUR_OR_HOUR.getErrorMsg());
		else 
			relevantWorkingDay.appoints.add(apt);
	}
	
	public boolean isAppointWithinWorkingHours(Appointment apt) {
		return apt.getStartTime().isAfter(practitionerStartTime) &&
				apt.getEndTime().isBefore(practitionerEndTime);
	}
	
	public boolean isAppointTimeFree(Appointment apt, WorkingDay relevantWorkingDay) {
		return relevantWorkingDay.appoints.stream().noneMatch(t -> t.isConflicting(apt));
	}
	
	public boolean isAppointTooEarlyInTime(Appointment apt, LocalDate inputDay) {
		return LocalTime.now().until(apt.getStartTime(), ChronoUnit.HOURS) < 2 && inputDay.equals(LocalDate.now());
	}
	
	public boolean isAppointTimeDivisibleByHalfHour(Appointment apt) {
		return apt.getStartTime().getMinute() == 30 || apt.getStartTime().getMinute() == 0;
	}
	
	public String listTodaysApts() {
		WorkingDay today = getRelevantWorkingDay(LocalDate.now());
		StringBuilder sb = new StringBuilder();
		for (Appointment apt : today.appoints)
			sb.append(apt);
		return sb.toString();
	}
	
	private void addNewWorkingDayIfOmitted(LocalDate inputDay) {
		if (workingDays.stream().noneMatch(t -> t.getCalendarDate().equals(inputDay)))
			workingDays.add(new WorkingDay(inputDay, new ArrayList<>()));
	}
	
	private WorkingDay getRelevantWorkingDay(LocalDate inputDay) {
		Optional<WorkingDay> relevantWorkingDay = workingDays.stream()
				.filter(t -> t.getCalendarDate().equals(inputDay)).findFirst();
		return relevantWorkingDay.get();
	}
	
}
