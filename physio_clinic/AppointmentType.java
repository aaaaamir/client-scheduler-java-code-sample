package physio_clinic;

public enum AppointmentType {

	  CHECK_IN(30, "Brief check-in"), STANDARD(60, "Standard assessment"), INITIAL_CONSULTATION(5, "Initial assessment");     
	
	  private long minutesDuration;
	  private String description;
	
	  private AppointmentType(long minutesDuration, String description) {
	    this.minutesDuration = minutesDuration;
	    this.description = description;
	  }
	  
	  public long getMinutesDuration() {
		  return minutesDuration;
	  }

}
