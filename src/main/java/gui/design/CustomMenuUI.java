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
        g.setColor(new Color(112, 111, 111));
        g.fillRect(0, 0, menuItem.getWidth(), menuItem.getHeight());

        menuItem.setForeground(new Color(255, 244, 241));
        menuItem.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                menuItem.setBackground(new Color(153, 153, 153));
                menuItem.setForeground(new Color(0, 89, 255));
            }

            @Override
            public void mouseExited(MouseEvent evt) {
                menuItem.setBackground(new Color(112, 111, 111));
                menuItem.setForeground(new Color(255, 244, 241));
            }
        });
    }
}