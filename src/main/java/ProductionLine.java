public class ProductionLine extends Thread {
    private SharedBuffer buffer;

    public ProductionLine(SharedBuffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while (true) {
            Component component = buffer.consume();
            // Simula el ensamblaje
            System.out.println("Ensamblaje de componente " + component.getComponentId());
        }
    }
}