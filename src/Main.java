import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Main extends JFrame implements ActionListener {


    JFrame pussel = new JFrame();
    JButton blankTile = new JButton();

    public Main() {
        pussel.setBounds(0, 0, 500, 500);
        pussel.setLayout(new GridLayout(4, 4));
        knappar();
        pussel.setVisible(true);
        pussel.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void knappar() {
        for (int i = 1; i < 16; i++) {
            String buttonLabel = String.valueOf(i);
            JButton tile = new JButton(buttonLabel);
            pussel.add(tile);
            tile.addActionListener(this);
        }
        pussel.add(blankTile);
        blankTile.setVisible(false);
        blankTile.addActionListener(this);
    }


    public void actionPerformed(ActionEvent e) {
        JButton tile = (JButton) e.getSource();
        int tileIndex = getIndexOfButton(tile);
        if (
              isValidMove(tileIndex)
        ) {
        blankTile.setText(tile.getText());
        blankTile.setVisible(true);
        tile.setVisible(false);
        blankTile = tile; }
    }

    public int getIndexOfButton(JButton tile) {
        for (int i = 0; i < pussel.getContentPane().getComponents().length; i++) {
            if (tile.hashCode() == pussel.getContentPane().getComponent(i).hashCode()) {
                return i; }

            }
        return -1;
        }
        public boolean isValidMove(int index) {
            int RightIndex = index +1;
            int LeftIndex = index -1;
            int DownIndex = index +4;
            int UpIndex = index -4;
            if (UpIndex > -1 && pussel.getContentPane().getComponent(UpIndex) == blankTile) {
                return true;
            }
            if (DownIndex < 16 && pussel.getContentPane().getComponent(DownIndex) == blankTile) {
                return true;
            }
            if (LeftIndex !=-1 && LeftIndex !=3 && LeftIndex !=7 && LeftIndex !=11 && pussel.getContentPane().getComponent(LeftIndex) == blankTile) {
                return true;
            }
            if (RightIndex !=4 && RightIndex !=8 && RightIndex !=12 && RightIndex !=16 && pussel.getContentPane().getComponent(RightIndex) == blankTile) {
        return true;
    }

        return false;
    }

        public static void main (String[]args){
            new Main();
        }
    }

