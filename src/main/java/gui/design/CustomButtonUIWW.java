package gui.design;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CustomButtonUIWW extends BasicButtonUI {

    @Override
    public void installUI(JComponent c) {
        super.installUI(c);
        AbstractButton button = (AbstractButton) c;
        button.setBackground(new Color(192, 192, 192)); // Встановіть фон кнопки тут
        button.setForeground(new Color(0, 0, 0)); // Встановіть колір тексту кнопки тут
        button.setBorderPainted(false); // Збережіть розмір кнопки без рамки

        // Додайте обробку подій наведення миші
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                button.setBackground(new Color(153, 153, 153)); // Новий фон при наведенні
                button.setForeground(new Color(0, 89, 255)); // Новий колір тексту при наведенні
            }

            @Override
            public void mouseExited(MouseEvent evt) {
                button.setBackground(new Color(192, 192, 192)); // Повертаємо фон при знятті наведення
                button.setForeground(new Color(0, 0, 0)); // Повертаємо колір тексту при знятті наведення
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


