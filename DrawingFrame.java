package Drawing_shape;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DrawingFrame extends JFrame implements ActionListener {
    public static void main(String[] args) {
        JFrame jframe=new JFrame("drawing ");
        jframe.setDefaultCloseOperation(3);
        Drawing_Panel panel=new Drawing_Panel();
        jframe.add(panel);
        jframe.pack();
        jframe.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
