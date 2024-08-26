package physio_clinic;
import java.time.LocalTime;

public class Appointment {
	
	private AppointmentType aptType;
	private LocalTime startTime;
	private LocalTime endTime;
	
	public Appointment(AppointmentType aptType, LocalTime startTime) {
		this.aptType = aptType;
		this.startTime = startTime;
		this.endTime = startTime.plusMinutes(aptType.getMinutesDuration());
	}
	
	public boolean isConflicting(Appointment apt) {
		LocalTime maxStartTime = startTime;
		if (apt.getStartTime().isAfter(startTime))
			maxStartTime = apt.getStartTime();
		LocalTime minEndTime = endTime;
		if (apt.getEndTime().isBefore(endTime))
			minEndTime = apt.getEndTime();
		return maxStartTime.isBefore(minEndTime);
	}
	
	public AppointmentType getAptType() {
		return aptType;
	}
	public void setAptType(AppointmentType aptType) {
		this.aptType = aptType;
	}
	public LocalTime getStartTime() {
		return startTime;
	}
	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}
	public LocalTime getEndTime() {
		return endTime;
	}
	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}
	
}
