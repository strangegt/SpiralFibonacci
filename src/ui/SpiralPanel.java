package ui;

import java.awt.Dimension;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSlider;

public class SpiralPanel extends JScrollPane {
    private int drawHeight;
    private int drawWidth;
    private int drawMargin;
    private SpiralDraw panelGraph;
    private JPanel container;

    public SpiralPanel(int width, int height, int margin, JRadioButton anticlockwiseRadio, JCheckBox[] checkBoxs,
            JCheckBox showSquares, JSlider angulo) {
        this.drawWidth = width;
        this.drawHeight = height;
        this.drawMargin = margin;
        panelGraph = new SpiralDraw(anticlockwiseRadio, checkBoxs, showSquares,angulo);
        container = new JPanel(null);
        container.add(panelGraph);
        panelGraph.setBounds(drawMargin, drawMargin, drawWidth, drawHeight);
        container.setPreferredSize(new Dimension(drawWidth + drawMargin * 2, drawHeight + drawMargin * 2));
        setViewportView(container);
    }
}
