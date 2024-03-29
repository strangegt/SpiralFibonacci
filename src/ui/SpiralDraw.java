package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SpiralDraw extends JPanel implements MouseListener, MouseMotionListener {
    /**
     * The radius to grag then spiral
     */
    private static final int RADIUS = 10;

    /**
     * The x cordinate of the spiral center
     */
    private int x = 1170;
    /**
     * The y cordinate of the spiral center
     */
    private int y = 720;
    private JCheckBox[] checkBoxs;
    private JRadioButton anticlockwiseRadio;
    private JCheckBox showSquares;

    private JSlider angulo;

    /**
     * Constructor
     * 
     * @param checkBoxs
     * @param anticlockwiseRadio
     * @param showSquares
     * @param angulo
     */
    public SpiralDraw(JRadioButton anticlockwiseRadio, JCheckBox[] checkBoxs, JCheckBox showSquares, JSlider angulo) {
        this.anticlockwiseRadio = anticlockwiseRadio;
        this.checkBoxs = checkBoxs;
        this.angulo = angulo;
        ChangeListener changeListener = new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                repaint();
            }
        };
        angulo.addChangeListener(changeListener);
        anticlockwiseRadio.addChangeListener(changeListener);
        for (int index = 0; index < checkBoxs.length; index++) {
            this.checkBoxs[index].addChangeListener(changeListener);
        }
        this.showSquares = showSquares;
        showSquares.addChangeListener(changeListener);
        // Track mouse clicks
        addMouseListener(this);
        // Track mouse moves
        addMouseMotionListener(this);
        // Set a border
        setBorder(BorderFactory.createLineBorder(Color.black));
        // Set background
        setBackground(Color.WHITE);
    }

    /**
     * The colors of the spirals
     */
    private Color[] colors = new Color[] { Color.RED, Color.BLUE, Color.GREEN, Color.MAGENTA };

    /**
     * Dragging the spiral
     */
    private boolean dragging;

    /**
     * Paint the spiral
     * 
     * @param g
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        double angulo = (this.angulo.getValue()* Math.PI / 180);
        Graphics2D g2d = (Graphics2D) g;
        g2d.rotate(angulo, x, y);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        for (int index = 0; index < colors.length; index++) {
            if (checkBoxs[index].isSelected()) {
                g2d.setColor(colors[index]);
                if (anticlockwiseRadio.isSelected()) {
                    paintSpiralLeft(g2d, x, y, index, 1, 0, 16 + index);
                } else {
                    paintSpiralRight(g2d, x, y, index, 1, 0, 16 + index);
                }
            }
        }
        g2d.rotate(-angulo, x, y);
    }

    /**
     * Paint a section of the spiral, does recursive until max
     * 
     * @param g2d       The graphics
     * @param x         The x cordinate of the left position of square
     * @param y         The y coordinate of the top of square
     * @param pos       Position of the next square
     * @param value     Value in the fibonace serie
     * @param prevValue The previous value in the fibonace serie
     * @param max       Max number of interactions
     */
    private void paintSpiralLeft(Graphics2D g2d, int x, int y, int pos, int value, int prevValue, int max) {
        switch (pos % 4) {
            case 0:
                // lo dibujamos x,y sup izq
                if (showSquares.isSelected())
                    g2d.drawRect(x, y, value, value);
                g2d.drawArc(x - value, y, value * 2, value * 2, 0, 90);
                if (pos < max) {
                    int nextValue = value + prevValue;
                    paintSpiralLeft(g2d, x - nextValue, y, ++pos, nextValue, value, max);
                }
                break;
            case 1:
                // lo dibujamos x,y inf d
                if (showSquares.isSelected())
                    g2d.drawRect(x, y, value, value);
                g2d.drawArc(x, y, value * 2, value * 2, 90, 90);
                if (pos < max) {
                    int nextValue = value + prevValue;
                    paintSpiralLeft(g2d, x, y + value, ++pos, nextValue, value, max);
                }
                break;
            case 2:
                if (showSquares.isSelected())
                    g2d.drawRect(x, y, value, value);
                g2d.drawArc(x, y - value, value * 2, value * 2, 180, 90);
                if (pos < max) {
                    int nextValue = value + prevValue;
                    paintSpiralLeft(g2d, x + value, y - prevValue, ++pos, nextValue, value, max);
                }
                break;
            case 3:
                if (showSquares.isSelected())
                    g2d.drawRect(x, y, value, value);
                g2d.drawArc(x - value, y - value, value * 2, value * 2, 270, 90);
                if (pos < max) {
                    int nextValue = value + prevValue;
                    paintSpiralLeft(g2d, x - prevValue, y - nextValue, ++pos, nextValue, value, max);
                }
                break;
            case 4:
                if (showSquares.isSelected())
                    g2d.drawRect(x, y, value, value);
                if (pos < max) {
                    int nextValue = value + prevValue;
                    paintSpiralLeft(g2d, x + value - nextValue, y - nextValue, ++pos, nextValue, value, max);
                }
                break;
        }
    }

    /**
     * Paint a section of the spiral, does recursive until max
     * 
     * @param g2d       The graphics
     * @param x         The x cordinate of the left position of square
     * @param y         The y coordinate of the top of square
     * @param pos       Position of the next square
     * @param value     Value in the fibonace serie
     * @param prevValue The previous value in the fibonace serie
     * @param max       Max number of interactions
     */
    private void paintSpiralRight(Graphics2D g2d, int x, int y, int pos, int value, int prevValue, int max) {
        switch (pos % 4) {
            case 0:
                // lo dibujamos x,y sup izq
                if (showSquares.isSelected())
                    g2d.drawRect(x, y, value, value);
                g2d.drawArc(x, y, value * 2, value * 2, 90, 90);
                if (pos < max) {
                    int nextValue = value + prevValue;
                    paintSpiralRight(g2d, x + value, y, ++pos, nextValue, value, max);
                }
                break;
            case 1:
                // lo dibujamos x,y inf d
                if (showSquares.isSelected())
                    g2d.drawRect(x, y, value, value);
                g2d.drawArc(x - value, y, value * 2, value * 2, 0, 90);
                if (pos < max) {
                    int nextValue = value + prevValue;
                    paintSpiralRight(g2d, x - prevValue, y + value, ++pos, nextValue, value, max);
                }
                break;
            case 2:
                if (showSquares.isSelected())
                    g2d.drawRect(x, y, value, value);
                g2d.drawArc(x - value, y - value, value * 2, value * 2, 270, 90);
                if (pos < max) {
                    int nextValue = value + prevValue;
                    paintSpiralRight(g2d, x - nextValue, y - prevValue, ++pos, nextValue, value, max);
                }
                break;
            case 3:
                if (showSquares.isSelected())
                    g2d.drawRect(x, y, value, value);
                g2d.drawArc(x, y - value, value * 2, value * 2, 180, 90);
                if (pos < max) {
                    int nextValue = value + prevValue;
                    paintSpiralRight(g2d, x, y - nextValue, ++pos, nextValue, value, max);
                }
                break;
            case 4:
                if (showSquares.isSelected())
                    g2d.drawRect(x, y, value, value);
                if (pos < max) {
                    int nextValue = value + prevValue;
                    paintSpiralRight(g2d, x + value - nextValue, y - nextValue, ++pos, nextValue, value, max);
                }
                break;
        }
    }

    /**
     * Move the spiral on mouse drag
     */
    @Override
    public void mouseDragged(java.awt.event.MouseEvent e) {
        if (dragging) {
            int eX = e.getX();
            int eY = e.getY();
            x = eX;
            y = eY;
            if (eX < RADIUS) {
                x = RADIUS;
            }
            if (eY < RADIUS) {
                y = RADIUS;
            }
            if (eX > getWidth() - RADIUS) {
                x = getWidth() - RADIUS;
            }
            if (eY > getHeight() - RADIUS) {
                y = getHeight() - RADIUS;
            }
            this.scrollRectToVisible(new Rectangle(x - RADIUS / 2, y - RADIUS / 2, RADIUS, RADIUS));
            repaint();
        }
    }

    @Override
    public void mouseMoved(java.awt.event.MouseEvent e) {
    }

    @Override
    public void mouseClicked(java.awt.event.MouseEvent e) {
    }

    /**
     * Start dragging when pressed Right button near the center of the spiral
     */
    @Override
    public void mousePressed(java.awt.event.MouseEvent e) {
        int button = e.getButton();
        if (button == MouseEvent.BUTTON1) {
            int eX = e.getX();
            int eY = e.getY();
            if (InsideCircle(eX, eY, x, y, RADIUS)) {
                dragging = true;
                repaint();
            }
        }
    }

    /**
     * Stop dragging when release left button
     */

    @Override
    public void mouseReleased(java.awt.event.MouseEvent e) {
        int button = e.getButton();
        if (button == MouseEvent.BUTTON1) {
            dragging = false;
            repaint();
        }
    }

    @Override
    public void mouseEntered(java.awt.event.MouseEvent e) {
    }

    @Override
    public void mouseExited(java.awt.event.MouseEvent e) {
    }

    /**
     * Detect si the point is inside the circle
     * 
     * @param x1     The x of the point
     * @param y1     THe y of the point
     * @param x2     THe x of the center of then circle
     * @param y2     THe y of the center of then circle
     * @param radius The radius of the circle
     * @return
     */
    public boolean InsideCircle(float x1, float y1, float x2, float y2, float radius) {
        return Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2) <= Math.pow(radius, 2);
    }

}
