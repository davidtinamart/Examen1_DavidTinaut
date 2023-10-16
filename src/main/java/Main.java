import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int bufferCapacity = 100;
        SharedBuffer buffer = new SharedBuffer(bufferCapacity);

        Workstation workstation1 = new Workstation(buffer);
        Workstation workstation2 = new Workstation(buffer);

        ProductionLine productionLine = new ProductionLine(buffer);

        JFrame frame = new JFrame("Simulación Campana de Gauss");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        GaussPanel gaussPanel = new GaussPanel();
        frame.add(gaussPanel);

        workstation1.start();
        workstation2.start();
        productionLine.start();

        Timer timer = new Timer(100, e -> {
            gaussPanel.setBuffer(buffer.getBuffer());
            gaussPanel.repaint();
        });

        timer.start();

        frame.setVisible(true);
    }
}

class GaussPanel extends JPanel {
    private Queue<Component> buffer;

    public GaussPanel() {
        buffer = new LinkedList<>();
    }

    public void setBuffer(Queue<Component> buffer) {
        this.buffer = buffer;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x = 10;
        int y = 100;

        for (Component component : buffer) {
            g.drawRect(x, y, 30, 30);
            g.drawString(Integer.toString(component.getComponentId()), x + 10, y + 20);
            x += 40;
        }
    }
}
