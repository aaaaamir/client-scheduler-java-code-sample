package physio_clinic;

public enum PractitionerErrorMsg {

	NOT_WITHIN_WORKING_HOURS("Appointment is not within the hours being worked by the practitioner"),
	TIME_NOT_AVAILABLE("Appointment time is already booked up"),
	APPOINTMENT_TOO_CLOSE("Appointment start time is not at least 2 hours in the future"),
	NOT_ENDING_IN_HALF_HOUR_OR_HOUR("Appointment does not start on the hour or half-hour");
	
	public String getErrorMsg() {
		return errorMsg;
	}

	private PractitionerErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	private String errorMsg;
	
}
