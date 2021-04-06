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
    public static Towhoe window = new Towhoe(); // TODO this is really dumb
    private JavaArcade game; // THIS IS HERE SO I CAN MAKE A METHOD TO RETURN THIS MF I KNOW ITS STUPID BUT AT THIS POINT I DO NOT CARE

    public Towhoe() {
        super("Towhoe 6: Embodiment of Stupid Dummies");

        game = new UserPanel(293,300); // inputs to this are where player spawns. Yes you can resize windows and stuff as you wish HOWEVER on the initial spawn the window will always be the same size so we can make this a number and not care

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

    // TODO this is a really dumb way to do it but who cares
    public JavaArcade getGame() {
        return game;
    }

    public void restartGame() {
    	window = new Towhoe();
	window.setBounds(100,100,600,600);
	window.setDefaultCloseOperation(EXIT_ON_CLOSE);
	window.setVisible(true);
    }
    // Absolute hack
    // causes game to freeze when resized too small (41x194) but nobody will play like that anyways
    // values are border size x 2 + character diameter but for some reason the borderheight changed so its 194 now.
    public int getBorderWidth() {
        return super.getWidth() - 41;
    }

    public int getBorderHeight() {
        return super.getHeight() - 194; // i have no idea why this changed but it did so /shrug
    }

    public static void main(String[] args) {
        window.setBounds(100, 100, 600, 600);
        window.setDefaultCloseOperation(EXIT_ON_CLOSE);
        //window.setResizable(false); // THIS ONE IS (no longer) IMPORTANT
        window.setVisible(true);
    }
}
