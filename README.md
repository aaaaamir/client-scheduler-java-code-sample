# client-scheduler-java-code-sample

This project serves to act as a code sample for some backend business logic in Java. Below I got some example constraints to be followed for the implementation of a healthcare clinic. Implemented is the code along with unit tests, but not any Spring boot or other library interface to make CLI calls or boot up this code as a web server or anything -- It is rather just meant to show a sample of code for the innards of a generic backend system 😊

### Contraints
- The business is open from 9am-5pm. Appointments can only be made that start and end within those hours.
- The business currently has 3 workers.
- The business offers three types of services: A 90 minutes consultation, a 60 minute service appointment, and 30 minute checkin appointment.
- Clients must be able to book with a specific worker.
- Workers can only see a single client at any time.
- Service appointments start on the hour or half-hour. Bookings cannot be made within 2 hours of the appointment start time.

### **Application Functionality:** Required Methods

- **List method 1:** Provide a client with a list of available booking times. Inputs are the booking type and a date greater than or equal to "today".
- **Book method:** Allow the patient to book an appointment.
- **List method 2:** Provide the practitioner with a list of scheduled appointments for the current day.
