package gui.design;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static gui.design.GradientPanel.MAIN_COLOR_VALUE_OF_RED;
import static gui.design.GradientPanel.MAIN_COLOR_VALUE_OF_GREEN;
import static gui.design.GradientPanel.MAIN_COLOR_VALUE_OF_BLUE;

//class that defines the appearance of the buttons
public class CustomButton extends BasicButtonUI {
    @Override
    public void installUI(JComponent c) {
        super.installUI(c);
        AbstractButton button = (AbstractButton) c;
        button.setBackground(new Color(MAIN_COLOR_VALUE_OF_RED, MAIN_COLOR_VALUE_OF_GREEN, MAIN_COLOR_VALUE_OF_BLUE));
        button.setForeground(new Color(0, 0, 0));
        button.setBorderPainted(true);

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                button.setBackground(new Color(238, 242, 252));
                button.setForeground(new Color(0, 89, 255));
            }

            @Override
            public void mouseExited(MouseEvent evt) {
                button.setBackground(new Color(MAIN_COLOR_VALUE_OF_RED, MAIN_COLOR_VALUE_OF_GREEN, MAIN_COLOR_VALUE_OF_BLUE));
                button.setForeground(new Color(0, 0, 0));
            }
        });
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        AbstractButton button = (AbstractButton) c;
        paintBackground(g, button, button.getModel().isPressed() ? 2 : 0);
        super.paint(g, c);
    }

    private void paintBackground(Graphics g, AbstractButton b, int yOffset) {
        Dimension size = b.getSize();
        g.setColor(b.getBackground());
        g.fillRect(0, yOffset, size.width, size.height - yOffset);
    }
}