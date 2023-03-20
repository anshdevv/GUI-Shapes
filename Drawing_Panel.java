package Drawing_shape;

import Drawing_shape.Triangle;

import java.awt.*;
import java.awt.event.*;
import java.awt.event.KeyListener;
import java.io.*;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

import javax.crypto.spec.PSource;
import javax.management.StringValueExp;
import javax.swing.*;

public class Drawing_Panel extends JPanel implements MouseListener, MouseMotionListener, KeyListener {
    /**
     * instance variable
     * calling of objects of circle,triangle,rectangle,stack,queue
     */
    Stack stack = new Stack();
    Queue queue = new Queue();
    int[] x = new int[3];

    int[] y = new int[3];
    private final int Default_Width = 600;
    Point pointend;
    private final int Default_Height = 600;
    private int x1, y1, x2, y2, x3, y3;
    private Graphics g;
    private Circle circle;
    private Random random = new Random();
    int current_shape = 0;
    private Rectangle rectangel;
    private Triangle triangle;
    int collected_points = 0;
    Point p2;
    Point p1;
    Point p3;

    Color color = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));

    /**
     * constructor implementing mouse listener options
     *
     */
    public Drawing_Panel() {
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(Default_Width, Default_Height));
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.addKeyListener((KeyListener) this);
        setFocusable(true);

    }

    public void paintcomp(Graphics g) {
        super.paintComponent(g);
    }

    /**
     * setupdrawinggraphics renamed as paint
     *
     */
    public void paint() {
        g = getGraphics();
        if (current_shape == 1) {

            if (circle != null) {
                circle.draw(g);
            }
        }
        if (current_shape == 2) {
            if (rectangel != null) {
                rectangel.draw(g);
            }
        }
        if (current_shape == 3) {
            if (triangle != null) {
                triangle.draw(g);
            }

        }
    }

    /**
     *paint function implemented when right click is done
     *
     */
    public void paintredo() {
        g = getGraphics();
        g.clearRect(0, 0, 4000, 4000);
        try {
            if (stack.head != null) {
                Node current = stack.head;

                while (current != null) {
                    current.shape.draw(g);
                    current = current.next;


                }
            }

        }catch (NullPointerException e){
            System.out.println("queue empty");
        }
    }



    public void previewpaint() {

        g = getGraphics();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    /**
     * generates triangle successfull and makes circle and rectangle of 0 size
     *
     * @param e the event to be processed
     */
    @Override
    public void mousePressed(MouseEvent e) {
        g = getGraphics();


        if (SwingUtilities.isLeftMouseButton(e)) {
            if (current_shape == 1) {
                x1 = e.getX();
                y1 = e.getY();
                Point centre = new Point(x1, y1);
                circle = new Circle(0, color, centre);

            }
            if (current_shape == 2) {
                x1 = e.getX();
                y1 = e.getY();
                Point centre = new Point(x1, y1);
                rectangel = new Rectangle(0, 0, color, centre);

            }
            if (current_shape == 3) {
                x1 = e.getX();
                y1 = e.getY();
                collected_points++;

                if (collected_points == 1) {

                    p1 = new Point(x1, y1);
                } else if (collected_points == 2) {

                    p2 = new Point(x1, y1);
                    g.drawLine(p1.x, p1.y, p2.x, p2.y);
                } else if (collected_points == 3) {

                    p3 = new Point(x1, y1);
                    g.drawLine(p2.x, p2.y, p3.x, p3.y);
                    System.out.println(Arrays.toString(x));
                    Point centre = new Point(x[0], y[0]);
                    triangle = new Triangle(p1, p2, p3, color);
                    stack.push(triangle);
                    try {
                        tofile();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    triangle.draw(g);
                    paint();
                    collected_points = 0;
                }

            }
        }
    }

    /**
     * mouse released makes the circle and triangle and after it is made it is pushed into the stack
     * @param e the event to be processed
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        g = getGraphics();
        if (SwingUtilities.isLeftMouseButton(e)) {
            if (current_shape == 1) {
                x2 = e.getX();
                y2 = e.getY();
                Point centre = new Point(x1, y1);
                int radius = (x2 - x1);
                int circumference = (int) (2 * Math.PI * radius);
                circle = new Circle(circumference / 2, color, centre);
                stack.push(circle);
                try {
                    tofile();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                circle.draw(g);


            }
            if (current_shape == 2) {
                x2 = e.getX();
                y2 = e.getY();
                Point centre = new Point(x1, y1);
                int width = x2 - x1;
                int height = y2 - y1;
                rectangel = new Rectangle(width, height, color, centre);
                stack.push(rectangel);
                try {
                    tofile();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                rectangel.draw(g);


            }
        } else if (SwingUtilities.isRightMouseButton(e)) {
            if (stack.head != null) {
                Shape current = stack.pop();
                queue.enqueue(current);
            }
            System.out.println("right pressed");
        } else if (SwingUtilities.isMiddleMouseButton(e)) {
            if (queue.tail != null) {
                Shape current = queue.dequeue();
                stack.push(current);
            } else if (queue.tail==null) {
                return;

            }
            System.out.println("middle pressed");
        }
        paintredo();
    }


    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    /**
     * mouse dragged keeps the track of how big the circle is getting
     * the circle cannot be shortened
     * @param e the event to be processed
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        g = getGraphics();
        if (SwingUtilities.isLeftMouseButton(e)) {
            if (current_shape == 1) {
                x2 = e.getX();
                y2 = e.getY();

                Point centre = new Point(x1, y1);
                int radius = (x2 - x1);
                int circumference = (int) (2 * Math.PI * radius);
                circle = new Circle(circumference / 2, color, centre);

                circle.draw(g);

            }
            if (current_shape == 2) {
                x2 = e.getX();
                y2 = e.getY();

                Point centre = new Point(x1, y1);
                int width = x2 - x1;
                int height = y2 - y1;
                rectangel = new Rectangle(width, height, color, centre);

                rectangel.draw(g);


            }
        }

    }


    @Override
    public void mouseMoved(MouseEvent e) {


    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * prints which key is pressed
     */
    public void Status() {

        g.drawString(current_shape + "pressed", Default_Width / 3, Default_Height/20);
    }

    /**
     * dtects which key is pressed
     * @param e the event to be processed
     */
    @Override
    public void keyPressed(KeyEvent e) {
        g = getGraphics();
        if (current_shape == 0) {

        }
        if (e.getKeyChar() == '1') {
            current_shape = 1;
            Status();
        }
        if (e.getKeyChar() == '2') {
            current_shape = 2;
            Status();


        }
        if (e.getKeyChar() == '3') {
            current_shape = 3;
            Status();

        }
        if (e.getKeyChar() == 't') {
            filereading();


        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    /**
     * writes the detail of the shapes drawn to the shape.txt file
     * it reads from the stack and stores it in the file
     * @throws IOException
     */
    public void tofile() throws IOException {
        FileWriter writer = new FileWriter("./src/Drawing_shape/shape.txt");
        PrintWriter print = new PrintWriter(writer);
        Node current = stack.head;
        while (current != null) {
            print.println(current.shape.toString());
            current = current.next;
        }
        writer.close();
    }

    /**
     * this function is called when "t" is pressed
     * it reads the shape detail from the file
     * then redraw them
     */
    public void filereading() {
        g = getGraphics();
        File file = new File("./src/Drawing_shape/shape.txt");
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String name = sc.nextLine();
                if (name.equals("Circle")) {
                    String size = sc.nextLine();
                    String[] sizesplit = size.split(":");
                    int Size = Integer.parseInt(sizesplit[1].trim());

                    String point = sc.nextLine();
                    String[] pointsplit = point.split(":");
                    String[] pointsplit2 = pointsplit[1].split(",");

                    int centre_x = Integer.parseInt(pointsplit2[0].trim());
                    int centre_y = Integer.parseInt(pointsplit2[1].trim());
                    Point centre = new Point(centre_x, centre_y);

                    String color = sc.nextLine();
                    String[] colorsplit = color.split(":");
                    int c1 = Integer.parseInt(colorsplit[1].trim());
                    Color c = new Color(c1);
                    Color coloor = new Color(c.getRed(), c.getGreen(), c.getBlue());


                    Circle circle = new Circle(Size, coloor, centre);
                    circle.draw(g);
                }
                if (name.equals("Triangle")) {
                    String point1 = sc.nextLine();
                    String[] spliter1 = point1.split(":");
                    String[] split2 = spliter1[1].split(",");
                    Point Point1 = new Point(Integer.parseInt(split2[0]), Integer.parseInt(split2[1]));

                    String point2 = sc.nextLine();
                    String[] spliter2 = point2.split(":");
                    String[] split3 = spliter2[1].split(",");
                    Point Point2 = new Point(Integer.parseInt(split3[0]), Integer.parseInt(split3[1]));

                    String point3 = sc.nextLine();
                    String[] spliter3 = point3.split(":");
                    String[] split4 = spliter3[1].split(",");
                    Point Point3 = new Point(Integer.parseInt(split4[0]), Integer.parseInt(split4[1]));

                    String color = sc.nextLine();
                    String[] colorsplit = color.split(":");
                    int c1 = Integer.parseInt(colorsplit[1].trim());
                    Color c = new Color(c1);
                    Color coloor = new Color(c.getRed(), c.getGreen(), c.getBlue());
                    triangle = new Triangle(Point1, Point2, Point3, coloor);
                    triangle.draw(g);

                }
                if (name.equals("Rectangle")) {
                    String width = sc.nextLine();
                    String[] split = width.split(":");
                    int Width = Integer.parseInt(split[1]);

                    String height = sc.nextLine();
                    String[] splitheight = width.split(":");
                    int Height = Integer.parseInt(splitheight[1]);

                    String point = sc.nextLine();
                    String[] pointsplit = point.split(":");
                    String[] coordinatesplit = pointsplit[1].split(",");
                    int centre_x = Integer.parseInt(coordinatesplit[0].trim());
                    int centre_y = Integer.parseInt(coordinatesplit[1].trim());
                    Point centre = new Point(centre_x, centre_y);

                    String color = sc.nextLine();
                    String[] colorsplit = color.split(":");
                    int c1 = Integer.parseInt(colorsplit[1].trim());
                    Color c = new Color(c1);
                    Color coloor = new Color(c.getRed(), c.getGreen(), c.getBlue());

                    rectangel = new Rectangle(Width, Height, coloor, centre);
                    rectangel.draw(g);

                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}