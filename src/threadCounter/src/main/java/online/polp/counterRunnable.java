package online.polp;


import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class counterRunnable implements Runnable {
    private final String name;
    private final int start;
    private final int end;
    private final int sleepTime;

    @Override
    public void run() {
        if (start < end) {
            for (int i = start; i <= end; i++) {
                printNumber(i);
            }
        } else {
            for (int i = start; i >= end; i--) {
                printNumber(i);
            }
        }
    }

    private void printNumber(int i) {
        System.out.println(name + ": " + i);
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
