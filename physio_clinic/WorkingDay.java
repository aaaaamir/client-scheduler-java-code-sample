package physio_clinic;

import java.time.LocalDate;
import java.util.List;

public class WorkingDay {

	private LocalDate calendarDate;
	List<Appointment> appoints;
	
	public WorkingDay(LocalDate calendarDate, List<Appointment> appoints) {
		this.calendarDate = calendarDate;
		this.appoints = appoints;
	}
	
	public LocalDate getCalendarDate() {
		return calendarDate;
	}
	public void setCalendarDate(LocalDate calendarDate) {
		this.calendarDate = calendarDate;
	}
	public List<Appointment> getAppoints() {
		return appoints;
	}
	public void setAppoints(List<Appointment> appoints) {
		this.appoints = appoints;
	}
	
}
