package online.polp;

import lombok.RequiredArgsConstructor;
import online.polp.places.ChangingRoom;
import online.polp.places.Track;
import online.polp.colorize.Color;

import static online.polp.Constants.LAPS;

@RequiredArgsConstructor
public class RunnableDriver implements Runnable {
    private final int id;
    private final ChangingRoom changingRoom;
    private final Track track;

    @Override
    public void run() {
        try {
            Color.BLUE.printWithColor("Driver " + id + " arrives");

            changingRoom.use(id);
            track.use(id, LAPS);
            changingRoom.use(id);

            Color.BLUE.printWithColor("Driver " + id + " has finished");
        } catch (InterruptedException e) {
            //noinspection CallToPrintStackTrace
            e.printStackTrace();
        }
    }
}
