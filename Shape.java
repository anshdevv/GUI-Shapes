package Drawing_shape;

import java.awt.*;

public abstract class Shape {
    /**
     * parent class
     * @param g
     */
    public abstract void draw(Graphics g);

    public abstract String toString();

    public abstract Shape fromString();
}

