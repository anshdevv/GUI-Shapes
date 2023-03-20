package Drawing_shape;

import Drawing_shape.Shape;

public class Node {
    public Node next;
    Shape shape;

    public Node(Shape data) {

        this.shape = data;
    }
}
