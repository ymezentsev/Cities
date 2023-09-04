package gui.design;


import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

//creating a gradient background for windows
public class GradientPanel extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Color startColor = new Color(255, 255, 255);
        Color endColor = new Color(209, 232, 255);

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