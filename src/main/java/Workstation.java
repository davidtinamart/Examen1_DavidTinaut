import java.util.Random;

public class Workstation extends Thread {
    private SharedBuffer buffer;

    public Workstation(SharedBuffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        Random random = new Random();

        while (true) {
            Component component = new Component();
            buffer.produce(component);

            try {
                Thread.sleep(random.nextInt(500));  // Simula tiempo de producción variable
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}