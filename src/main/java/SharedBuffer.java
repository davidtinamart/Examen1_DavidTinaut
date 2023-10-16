import java.util.LinkedList;
import java.util.Queue;

public class SharedBuffer {
    private Queue<Component> buffer = new LinkedList<>();
    private int capacity;

    public SharedBuffer(int capacity) {
        this.capacity = capacity;
    }

    public synchronized void produce(Component component) {
        while (buffer.size() >= capacity) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        buffer.add(component);
        System.out.println("Produjo componente " + component.getComponentId());
        notifyAll();
    }

    public synchronized Component consume() {
        while (buffer.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Component component = buffer.poll();
        System.out.println("Ensambló componente " + component.getComponentId());
        notifyAll();
        return component;
    }

    public Queue<Component> getBuffer() {
        return buffer;
    }

    public void setBuffer(Queue<Component> buffer) {
        this.buffer = buffer;
    }
}