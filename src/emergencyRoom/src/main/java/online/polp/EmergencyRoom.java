package online.polp;

import java.time.Duration;
import java.time.LocalDateTime;

public class EmergencyRoom extends PriorityDeque<Patient> {
    private Duration timeSoFar = Duration.ZERO;
    private int patientsSoFar = 0;

    public EmergencyRoom() {
        super();
    }

    @Override
    public void add(Priority priority, Patient patient) {
        LocalDateTime now = LocalDateTime.now();
        TimedPatient timedPatient = new TimedPatient(patient, now);

        super.add(priority, timedPatient);
    }

    @Override
    public Patient pop() {
        TimedPatient timedPatient = (TimedPatient) super.pop();
        if (timedPatient == null) {
            return null;
        }

        LocalDateTime now = LocalDateTime.now();
        Duration timeInQueue = Duration.between(timedPatient.getEnqueueTime(), now);
        timeSoFar = timeSoFar.plus(timeInQueue);
        patientsSoFar++;

        return timedPatient;
    }

    public Duration getAverageTimeInQueue() {
        if (patientsSoFar == 0) {
            return Duration.ZERO;
        }

        return timeSoFar.dividedBy(patientsSoFar);
    }
}
