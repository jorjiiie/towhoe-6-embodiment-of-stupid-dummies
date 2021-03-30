/*
the thing you run, assembles guis and stuff
author: all of us
date: today
*/
import java.awt.Container;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;

public class Towhoe extends JFrame {
    // IMPORTANT FINALS
    public static Towhoe window = new Towhoe(); // TODO this is retarded
    private JavaArcade game; // THIS IS HERE SO I CAN MAKE A METHOD TO RETURN THIS MF

    public Towhoe() {
        super("Towhoe 6: Embodiment of Stupid Dummies");

        game = new UserPanel(0, 0); // values of this dont matter

        GameStats display = new GameStats(game);

        ControlPanel controls = new ControlPanel(game, display);

        game.setDisplay(display); // provides game ability to update display

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(new EmptyBorder(0, 5, 0, 5));
        panel.add(display, BorderLayout.NORTH);
        panel.add((JPanel) game, BorderLayout.CENTER);
        panel.add(controls, BorderLayout.SOUTH);

        Container c = getContentPane();
        c.add(panel, BorderLayout.CENTER);
    }

        public static void main(String[] args) {
            window.setBounds(100, 100, 600, 600);
            window.setDefaultCloseOperation(EXIT_ON_CLOSE);
            //window.setResizable(false); // THIS ONE IS IMPORTANT
            window.setVisible(true);
        }

        // TODO this is retarded
        public JavaArcade getGame() {
        return game;
    }

    // Absolute hack
    // causes game to freeze when resized too small (41x161) but nobody will play like that anyways
    // values are border size x 2 + character diameter
    public int getBorderWidth() {
        return super.getWidth() - 41;
    }

    public int getBorderHeight() {
        return super.getHeight() - 161;
    }
}