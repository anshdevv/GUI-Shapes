package Drawing_shape;

import java.awt.*;

public class Triangle extends Shape {
    /**
     * extends shape and has its abstract methods
     */
    public Color color;

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Point getP1() {
        return p1;
    }

    public void setP1(Point p1) {
        this.p1 = p1;
    }

    public Point getP2() {
        return p2;
    }

    public void setP2(Point p2) {
        this.p2 = p2;
    }

    public Point getP3() {
        return p3;
    }

    public void setP3(Point p3) {
        this.p3 = p3;
    }

    public Triangle(Point p1, Point p2, Point p3,Color color) {
        this.color = color;
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
    }

    Point p1;
    Point p2;
    Point p3;


    public void preview(Graphics g) {

    }

    @Override
    public void draw(Graphics g) {
        g.setColor(getColor());
        int[]x={p1.x,p2.x,p3.x};
        int[] y={p1.y,p2.y,p3.y};
        g.drawPolygon(x, y, 3);
        g.fillPolygon(x, y, 3);
    }

    @Override
    public String toString() {
        return "Triangle" + "\n" + "point1 :" + p1.x+","+p1.y + "\n" + "Point 2:" + p2.x+","+p2.y +"\n" + "point3:" + p3.x+","+p3.y + "\n" + "Color :" + color.getRGB();
    }
    @Override
    public Shape fromString() {
        return null;
    }
}
