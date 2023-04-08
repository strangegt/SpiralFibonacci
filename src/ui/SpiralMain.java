package ui;

import java.awt.BorderLayout;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JToolBar;

public class SpiralMain extends JPanel {

    public SpiralMain(int x, int y) {
        setLayout(new BorderLayout());
        JToolBar toolBar = new JToolBar(JToolBar.HORIZONTAL);
        String[] checkTitles = new String[] { "RED", "BLUE", "GREEN", "MAGENTA" };
        JCheckBox[] checkBoxs = new JCheckBox[4];

        JRadioButton anticlockwiseRadio = new JRadioButton("Anti Clockwise");
        anticlockwiseRadio.setSelected(true);
        JRadioButton clockwiseRadio = new JRadioButton("Clockwise");
        clockwiseRadio.setSelected(false);
        ButtonGroup group = new ButtonGroup();
        group.add(anticlockwiseRadio);
        group.add(clockwiseRadio);
        for (int i = 0; i < checkTitles.length; i++) {
            JCheckBox checkbox = new JCheckBox(checkTitles[i]);
            checkbox.setSelected(true);
            checkBoxs[i] = checkbox;
            toolBar.add(checkbox);
        }
        toolBar.addSeparator();
        toolBar.add(anticlockwiseRadio);
        toolBar.add(clockwiseRadio);
        toolBar.addSeparator();
        JCheckBox showSquares = new JCheckBox("Show Squares");
        toolBar.add(showSquares);

        SpiralPanel spiral = new SpiralPanel(x, y, 5, anticlockwiseRadio, checkBoxs, showSquares);
        add(spiral);
        add(toolBar, BorderLayout.NORTH);

    }

}