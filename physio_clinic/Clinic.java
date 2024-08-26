package physio_clinic;

import java.time.LocalTime;
import java.util.List;

public class Clinic {
	
	List<Practitioner> practitioners;
	private LocalTime clinicStartTime;
	private LocalTime clinicEndTime;
	
	public Clinic(List<Practitioner> practitioners, LocalTime clinicStartTime, LocalTime clinicEndTime) {
		this.practitioners = practitioners;
		this.clinicStartTime = clinicStartTime;
		this.clinicEndTime = clinicEndTime;
	}
	
	public List<Practitioner> getPractitioners() {
		return practitioners;
	}
	public void setPractitioners(List<Practitioner> practitioners) {
		this.practitioners = practitioners;
	}
	public LocalTime getClinicStartTime() {
		return clinicStartTime;
	}
	public void setClinicStartTime(LocalTime clinicStartTime) {
		this.clinicStartTime = clinicStartTime;
	}
	public LocalTime getClinicEndTime() {
		return clinicEndTime;
	}
	public void setClinicEndTime(LocalTime clinicEndTime) {
		this.clinicEndTime = clinicEndTime;
	}
	
}
