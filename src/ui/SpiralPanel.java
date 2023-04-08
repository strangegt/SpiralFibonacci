package ui;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class SpiralPanel extends JScrollPane{
    private int drawHeight;
    private int drawWidth;
    private int drawMargin;
    private SpiralDraw panelGraph;
    private JPanel container;

    public SpiralPanel(int width, int height, int margin) {
        this.drawWidth = width;
        this.drawHeight = height;
        this.drawMargin = margin;
        panelGraph = new SpiralDraw();
        container = new JPanel(null);
        container.add(panelGraph);
        panelGraph.setBounds(drawMargin, drawMargin, drawWidth, drawHeight);
        container.setPreferredSize(new Dimension(drawWidth + drawMargin * 2, drawHeight + drawMargin * 2));
        setViewportView(container);
    } 
}
