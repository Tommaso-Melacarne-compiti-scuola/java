package online.polp;

public class Main {
    public static void main(String[] args) {
        StudentOperations studentManager = new StudentManager();

        //1. Interfacce: Definisci un'interfaccia StudentOperations con metodi per aggiungere studenti, calcolare la media e ordinare per nome.
        //
        //
        //2. Liste e Mappe: Usa una lista per archiviare gli studenti e una mappa per gestire i voti.
        //
        //
        //3. Eccezioni: Gestisci un'eccezione personalizzata quando si tenta di calcolare la media per uno studente senza voti.
        //
        //
        //4. Ordinamento: Ordina la lista degli studenti per cognome oppure per media

        studentManager.addStudent(new Student("Crancesco"));
        studentManager.addStudent(new Student("Albanella"));
        studentManager.addStudent(new Student("Giccolo"));

        studentManager.addGradeToStudent("Crancesco", "Italiano", 5.0);
        studentManager.addGradeToStudent("Albanella", "Matematica", 2.0);
        studentManager.addGradeToStudent("Giccolo", "Italiano", 3.0);
        studentManager.addGradeToStudent("Crancesco", "Matematica", 4.0);

        System.out.println("Media degli studenti: ");
        System.out.println(studentManager.getAverage());

        System.out.println("Studenti ordinati per nome: ");
        System.out.println(studentManager.getSortedByName());
    }
}