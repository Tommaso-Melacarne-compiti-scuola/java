package online.polp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@Data()
public class TimedPatient extends Patient {
    private LocalDateTime enqueueTime;

    public TimedPatient(Patient patient, LocalDateTime enqueueTime) {
        super(patient.getName(), patient.getLastName(), patient.getBirthDate(), patient.getSsn());
        this.enqueueTime = enqueueTime;
    }
}
