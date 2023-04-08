import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import ui.SpiralMain;

public class App {

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    protected static void createAndShowGUI() {
        JFrame frame = new JFrame("Spiral Fibonacci");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1920, 1080));
        int x = 1620;
        int y = 1005;

        JPanel panel = new SpiralMain(x, y);
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
    }
}
