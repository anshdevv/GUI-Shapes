package Drawing_shape;

import java.awt.*;
import java.awt.*;

public class Rectangle extends Shape {
    /**
     * extends shape and has its abstract methods
     */
    public int width;
    public int height;
    public Color color;
    public Point centre;

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
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

    public Rectangle(int width, int height, Color color, Point centre) {
        this.width = width;
        this.height = height;
        this.color = color;
        this.centre = centre;
    }

    @Override
    public void draw(Graphics g) {
        int Size=(getHeight()*getWidth());
        g.setColor(getColor());
       g.drawRect(getCentre().x  ,getCentre().y, getWidth(), getHeight());
        g.fillRect(getCentre().x , getCentre().y, getWidth(), getHeight());
    }





    @Override
    public String toString() {

        return "Rectangle" + "\n" + "width :" + width + "\n" + "height :" + height + "\n"+"point centre: " + centre.x+","+centre.y + "\n" + "Color :" + color.getRGB();
    }

    @Override
    public Shape fromString() {
        return null;
    }

}
