package Drawing_shape;

import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;

public class Circle extends Shape {
    /**
     * extends shape and has its abstract methods
     */
    public int size;
    public Color color;
    public Point centre;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Point getCentre() {
        return centre;
    }

    public void setCentre(Point centre) {
        this.centre = centre;
    }

    public Circle(int size, Color color, Point centre) {
        this.size = size;
        this.color = color;
        this.centre = centre;
    }

    /**
     * abstract draw function
     *
     * @param g
     */
    @Override
    public void draw(Graphics g) {
        g.setColor(getColor());
        g.drawOval(getCentre().x - getSize() / 2, getCentre().y - getSize() / 2, getSize(), getSize());
        g.fillOval(getCentre().x - getSize() / 2, getCentre().y - getSize() / 2, getSize(), getSize());
    }
    public void preview(Graphics g){
        g.setColor(Color.black);
        g.drawOval(getCentre().x - getSize() / 2, getCentre().y - getSize() / 2, getSize(), getSize());
    }

    /**
     * converts shape details to string that can be stored in the shape.txt
     * @return
     */
    @Override
    public String toString() {

        return "Circle"+"\n"+"size :"+size+"\n"+"Point :"+centre.x+","+centre.y+"\n"+"Color :"+ color.getRGB();
    }

    @Override
    public Shape fromString() {
        return null;
    }
}
