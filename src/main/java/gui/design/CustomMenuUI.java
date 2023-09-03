package gui.design;

import javax.swing.*;
import javax.swing.plaf.basic.BasicMenuUI;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CustomMenuUI extends BasicMenuUI {
    @Override
    public void paintBackground(Graphics g, JMenuItem menuItem, Color bgColor) {
        g.setColor(new Color(209, 232, 255));
        g.fillRect(0, 0, menuItem.getWidth(), menuItem.getHeight());

        menuItem.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                menuItem.setBackground(new Color(209, 232, 255));
                menuItem.setForeground(new Color(0, 89, 255));
            }

            @Override
            public void mouseExited(MouseEvent evt) {
                menuItem.setBackground(new Color(209, 232, 255));
            }
        });
    }
}