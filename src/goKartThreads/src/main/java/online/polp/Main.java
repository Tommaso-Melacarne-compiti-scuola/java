package online.polp;

import online.polp.places.ChangingRoom;
import online.polp.places.Track;

import java.util.ArrayList;
import java.util.List;

import static online.polp.Constants.*;

public class Main {
    public static void main(String[] args) {
        ChangingRoom changingRoom = new ChangingRoom(CHANGING_ROOM_CAPACITY);
        Track track = new Track(TRACK_CAPACITY);

        List<RunnableDriver> runnableDrivers = new ArrayList<>(DRIVERS);

        for (int i = 1; i <= DRIVERS; i++) {
            runnableDrivers.add(new RunnableDriver(i, changingRoom, track));
        }

        List<Thread> threads = runnableDrivers.stream().map(Thread::new).toList();

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                //noinspection CallToPrintStackTrace
                e.printStackTrace();
            }
        }
    }
}
