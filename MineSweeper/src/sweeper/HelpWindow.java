package sweeper;

import javax.swing.*;
import java.awt.*;

public class HelpWindow
{
    Font font = new Font("Tahoma", Font.BOLD, 20);
    public HelpWindow()
    {
        EventQueue.invokeLater(() ->
        {
            try
            {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            }
            catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ignored) {}

            String sb =
                    "<html>  * ІНСТРУКЦІЯ ПО ГРІ САПЕР *<br>" +
                            "* Щоб розпочати гру Сапер, натисніть на будь-яку комірку на полі.<br>" +
                            "* Якщо поруч з відкритою коміркою є порожня клітинка, то вона відкриється автоматично.<br>" +
                            "* Число в комірці показує, скільки мін приховано навколо даної комірки. Це число допоможе зрозуміти вам, де знаходяться безпечні комірки, а де знаходяться бомби.<br>" +
                            "* Що б позначити клітинку, в якій знаходиться бомба, натисніть на неї правою кнопкою миші.<br>" +
                            "* Якщо поруч з коміркою позначена кількість бомб відповідає номеру в комірці, і ви впевнені в правильності вибору. Натисніть ще раз на комірку, щоб відкрити не зайняті комірки навколо даної.<br>"+
                            "* Якщо ви відкрили комірку з міною,то гра програна.<br>"+
                            "* Гра продовжується до тих пір, поки ви не відкриєте всі не заміновані комірки.<br>" +
                            "* Щоб перезапустити гру натисніть середню кнопку миші.</html>";

            JLabel label = new JLabel(sb);
            label.setFont(font);
            JDialog frame = new JDialog();
            frame.setTitle("HOW TO PLAY");
            frame.setModal(true);
            frame.setDefaultCloseOperation(frame.DISPOSE_ON_CLOSE);
            frame.setLayout(new BorderLayout());
            frame.add(label);
            frame.setSize(600, 550);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}