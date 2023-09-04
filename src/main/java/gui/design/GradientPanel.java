package gui.design;


import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

//creating a gradient background for windows
public class GradientPanel extends JPanel {
    //main colors for app design
    public static final int MAIN_COLOR_VALUE_OF_RED = 209;
    public static final int MAIN_COLOR_VALUE_OF_GREEN = 232;
    public static final int MAIN_COLOR_VALUE_OF_BLUE = 255;
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Color startColor = new Color(255, 255, 255);
        Color endColor = new Color(MAIN_COLOR_VALUE_OF_RED, MAIN_COLOR_VALUE_OF_GREEN, MAIN_COLOR_VALUE_OF_BLUE);

        //create a gradient object
        GradientPaint gradient = new GradientPaint(
                new Point2D.Double(0, 0), startColor,
                new Point2D.Double(0, getHeight()), endColor
        );

        //set the gradient as the background
        Graphics2D g2d = (Graphics2D) g;
        g2d.setPaint(gradient);
        g2d.fill(new Rectangle2D.Double(0, 0, getWidth(), getHeight()));
    }

    public void setBackgroundColor() {
        repaint();
    }
}