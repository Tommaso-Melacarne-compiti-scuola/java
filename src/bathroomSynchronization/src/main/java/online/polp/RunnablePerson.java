package online.polp;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RunnablePerson implements Runnable {
    final Gender gender;
    final Bathroom bathroom;

    @Override
    public void run() {
        bathroom.use();
    }
}
