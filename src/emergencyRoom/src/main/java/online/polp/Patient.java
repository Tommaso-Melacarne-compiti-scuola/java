package online.polp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Patient {
    private String name;
    private String lastName;
    private LocalDate birthDate;
    private String ssn;
}
