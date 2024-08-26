package physio_clinic;

import java.time.LocalTime;
import java.util.ArrayList;

public class ClinicRunner {

	private static final int COMMON_START_HOUR = 9;
	private static final int COMMON_END_HOUR = 17;
	private static final int NUM_PRACTITIONERS = 3;
	private static final LocalTime COMMON_START_TIME = LocalTime.of(COMMON_START_HOUR, 0);
	private static final LocalTime COMMON_END_TIME = LocalTime.of(COMMON_END_HOUR, 0);
	
	// EXAMPLE MAIN FLOW -- Instantiating a physio clinic with required 3 practitioners
	public static void main(String[] args) {
		Clinic physioClinic = new Clinic(new ArrayList<>(), COMMON_START_TIME, COMMON_END_TIME);
		for (int practitionerCount = 0; practitionerCount < NUM_PRACTITIONERS; practitionerCount++)
			physioClinic.getPractitioners().add(new Practitioner(new ArrayList<>(), COMMON_START_TIME, COMMON_END_TIME));
	}
	
}
