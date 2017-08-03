import javax.swing.*;
import java.awt.*;

/**
 * Created by William Madgwick on 8/3/2017.
 */
class TopPanel extends JPanel{

    private ImageIcon snakeImage;
    private JLabel lblImage;
    private JPanel lftPanel;
    private JLabel lblName;
    private JLabel lblScore;
    private JLabel lblHScore;

    TopPanel(){

        //Set the panel up
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(GameDrawingPanel.BOARD_WIDTH, 110));
        setBackground(Board.THEME_COLOUR);

        snakeImage = new ImageIcon(Player.FileHandler.ICON_PATH);

        lblImage = new JLabel(snakeImage);
        add(lblImage, BorderLayout.EAST);

        //Left panel where all of the labels will be
        lftPanel = new JPanel();
        lftPanel.setLayout(new GridLayout(3, 0));
        lftPanel.setBackground(Board.THEME_COLOUR);

        lblName = new JLabel(Board.getPlayer().getName());
        lblName.setForeground(Color.WHITE);
        lblName.setFont(new Font("Calibri", Font.BOLD, 24));

        lblScore = new JLabel("0");
        lblScore.setForeground(Color.WHITE);
        lblScore.setFont(new Font("Calibri", Font.BOLD, 20));

        lblHScore = new JLabel("High Score: " + Board.getPlayer().getHighScore());
        lblHScore.setForeground(Color.WHITE);
        lblHScore.setFont(new Font("Calibri", Font.BOLD, 24));

        lftPanel.add(lblName);
        lftPanel.add(lblHScore);
        lftPanel.add(lblScore);

        add(lftPanel, BorderLayout.WEST);
    }

    public void setLabelScore(){
        lblScore.setText(Board.getPlayer().getCurrentScore() + "");
        lblHScore.setText("High Score: " + Board.getPlayer().getHighScore());
    }

}
