/*
statistics guis and tracking
author: all of us
date: today
*/

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class GameStats extends JPanel {
    private JTextField gameNameText, currentHighScorer, currentHighScore, current_Score;
    private int yourScore;
    private JLabel yourScoreText, hScore,lives,points;
    private JavaArcade game;

    // Constructor
    public GameStats(JavaArcade t) {
        super(new GridLayout(3, 4, 10, 0));
        setBorder(new EmptyBorder(0, 0, 5, 0));
        Font gameNameFont = new Font("Monospaced", Font.BOLD, 24);

        JLabel gName = new JLabel(" " + t.getGameName());

        gName.setForeground(Color.red);
        gName.setFont(gameNameFont);
        add(gName);
        hScore = new JLabel(" High Score: " + t.getHighScore());

        add(hScore); // new JLabel(" Current High Score: " + t.getHighScore()));

        add(new JLabel(" "));
        yourScoreText = new JLabel(" Your Final Score: " + 0);

        add(yourScoreText);
        

        points = new JLabel(" Points: 0");

        add(points);

        lives = new JLabel((" Lives: " + ((UserPanel)t).getPlayerLives()));
        add(lives);
        Font displayFont = new Font("Monospaced", Font.BOLD, 16);
        game = t;

    }

    public void updateHighScore() {
        hScore.setText(" High Score: " + ((UserPanel)game).getHighScore());
    }
    public void update(int points) {

        yourScoreText.setText(" Your Score: " + points);

    }

    public void updateLives(int l) {
        lives.setText(" Lives: " + l);
    }
    public void updatePoints() {
        points.setText(" Points: " + ((UserPanel)game).getPoints());
    }

    public void gameOver(int points) {

        if (points > Integer.parseInt(game.getHighScore())) {
            yourScoreText.setForeground(Color.BLUE);
            String s = (String) JOptionPane.showInputDialog(this,
                    "You are the new high scorer. Congratulations!\n Enter your name: ", "High Score",
                    JOptionPane.PLAIN_MESSAGE, null, null, "name");
            hScore.setText(" High Score: " + points);

        }

    }
}
