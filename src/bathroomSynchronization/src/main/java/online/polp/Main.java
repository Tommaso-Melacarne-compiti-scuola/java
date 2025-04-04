package online.polp;

import online.polp.singletons.RandomSingleton;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static final int THREADS_COUNT = 10;

    public static void main(String[] args) {
        Random random = RandomSingleton.getInstance();

        Bathroom manBathroom = new Bathroom();
        List<Thread> threads = createThreads(manBathroom);

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static List<Thread> createThreads(Bathroom manBathroom) {
        Bathroom womanBathroom = new Bathroom();

        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i <= THREADS_COUNT; i++) {
            Gender gender = Gender.getRandom();

            Bathroom bathroom = switch (gender) {
                case MALE -> manBathroom;
                case FEMALE -> womanBathroom;
            };

            RunnablePerson person = new RunnablePerson(gender, bathroom);

            Thread thread = new Thread(person);

            threads.add(thread);
        }

        return threads;
    }
}