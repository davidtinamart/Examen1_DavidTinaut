public class Component {
    private static int nextId = 1;
    private int componentId;

    public Component() {
        this.componentId = nextId++;
    }

    public int getComponentId() {
        return componentId;
    }
}