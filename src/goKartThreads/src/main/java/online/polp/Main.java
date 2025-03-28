package online.polp;

import online.polp.places.ChangingRoom;
import online.polp.places.Track;
import online.polp.colorize.Color;

import java.util.ArrayList;
import java.util.List;

import static online.polp.Constants.*;

public class Main {
    public static void main(String[] args) {
        ChangingRoom changingRoom = new ChangingRoom(CHANGING_ROOM_CAPACITY);
        Track track = new Track(TRACK_CAPACITY);

        List<RunnableDriver> runnableDrivers = new ArrayList<>(DRIVERS);
        
        // Array of available colors
        Color[] colors = {
            Color.RED, Color.GREEN, Color.YELLOW, Color.BLUE, 
            Color.PURPLE, Color.CYAN, Color.WHITE, Color.BLACK
        };

        for (int i = 1; i <= DRIVERS; i++) {
            // Assign a color to each driver, cycling through available colors
            Color driverColor = colors[(i - 1) % colors.length];
            runnableDrivers.add(new RunnableDriver(i, changingRoom, track, driverColor));
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
