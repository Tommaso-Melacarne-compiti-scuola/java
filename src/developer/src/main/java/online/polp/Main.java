package online.polp;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // All’interno del main:
        //- Creare 3 sviluppatori dei vari tipi (e di conseguenza anche 3 curriculum).
        //- Stampare tutti gli sviluppatori
        //- Testare tutti i metodi creati

        List<Developer> developers = List.of(
                new MobileDeveloper(
                        "123456789",
                        "Mario",
                        "Rossi",
                        40000,
                        new Curriculum(
                                1,
                                "Politecnico di Milano"
                        ),
                        "iOS",
                        true
                ),
                new CloudDeveloper(
                        "987654321",
                        "Luigi",
                        "Verdi",
                        29000,
                        new Curriculum(
                                2,
                                "Politecnico di Torino"
                        ),
                        "AWS",
                        5
                ),
                new MobileDeveloper(
                        "123456789",
                        "Mario",
                        "Verdi",
                        35000,
                        new Curriculum(
                                3,
                                "Politecnico di Milano"
                        ),
                        "Android",
                        false
                )
        );

        developers.forEach(System.out::println);

        Curriculum curriculum = new Curriculum(100, "Politecnico di Milano");

        System.out.println(developers.getFirst().getCurriculum().calculateValue());

        System.out.println(curriculum.addLanguage("Java"));




    }
}