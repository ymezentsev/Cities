package gui.design;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class GradientPanel extends JPanel {
    private Color backgroundColor;
    public GradientPanel() {
        this.backgroundColor = new Color(255, 255, 255);
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Color startColor = new Color(215, 215, 215);
        Color endColor = new Color(121, 121, 121);

        // Створюємо об'єкт градієнта
        GradientPaint gradient = new GradientPaint(
                new Point2D.Double(0, 0), startColor,
                new Point2D.Double(0, getHeight()), endColor
        );

        // Встановлюємо градієнт як фон
        Graphics2D g2d = (Graphics2D) g;
        g2d.setPaint(gradient);
        g2d.fill(new Rectangle2D.Double(0, 0, getWidth(), getHeight()));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Gradient Panel Example");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 200);

            GradientPanel panel = new GradientPanel();
            frame.add(panel);

            frame.setVisible(true);
        });
    }
    public void setBackgroundColor(Color color) {
        this.backgroundColor = color;
        repaint();
    }
}
