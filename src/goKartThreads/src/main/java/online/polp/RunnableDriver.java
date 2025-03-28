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
    private final Color color;

    @Override
    public void run() {
        try {
            color.printWithColor("Driver " + id + " arrives");

            changingRoom.use(id, color);
            track.use(id, LAPS, color);
            changingRoom.use(id, color);

            color.printWithColor("Driver " + id + " has finished");
        } catch (InterruptedException e) {
            //noinspection CallToPrintStackTrace
            e.printStackTrace();
        }
    }
}
