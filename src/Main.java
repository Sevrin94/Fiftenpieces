import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;


public class Main extends JFrame implements ActionListener {

    public JFrame pussel = new JFrame();
    public JPanel p1 = new JPanel(new GridLayout(4, 4));
    public JPanel p2 = new JPanel();
    public JButton blankTile = new JButton();
    public JButton reset = new JButton("Reset");

    public JButton order = new JButton("Order");
    int[] nummer = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};

    public Main(int n) {
        pussel.setBounds(0, 0, 500, 600);
        p1.setBounds(0, 0, 500, 500);
        p1.setLayout(new GridLayout(4, 4));
        knappar2(n);
        pussel.setVisible(true);
        pussel.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }


    public Main() {
        pussel.setBounds(0, 0, 500, 600);
        p1.setBounds(0, 0, 500, 500);
        p1.setLayout(new GridLayout(4, 4));
        knappar();
        pussel.setVisible(true);
        pussel.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void knappar2(int n) {
        for (int i = 1; i < n; i++) {
            pussel.add(p1);
            String buttonLabel = String.valueOf(i);
            JButton tile = new JButton(buttonLabel);
            p1.add(tile);
            tile.addActionListener(this);
        }
        p1.add(blankTile);
        JButton tile = new JButton("15");
        p1.add(tile);
        pussel.add(p2, BorderLayout.SOUTH);
        p2.add(reset);
        p2.add(order);
        blankTile.setVisible(false);
        tile.addActionListener(this);
        reset.addActionListener(this);
        order.addActionListener(this);
        blankTile.addActionListener(this);
    }

    public void knappar() {
        pussel.add(p1);
        for (int i = 1; i < 16; i++) {
            String buttonLabel = numret();
            JButton tile = new JButton(buttonLabel);
            p1.add(tile);
            tile.addActionListener(this);
        }
        p1.add(blankTile);
        pussel.add(p2, BorderLayout.SOUTH);
        p2.add(reset);
        p2.add(order);
        blankTile.setVisible(false);
        reset.addActionListener(this);
        order.addActionListener(this);
        blankTile.addActionListener(this);
    }

    public String numret() {
        Random random = new Random();
        int n = random.nextInt(15);
        String s = " ";

        while (s.equals(" "))
            if (nummer[n] != 0) {
                s = String.valueOf(nummer[n]);
                nummer[n] = 0;
            } else {
                n = random.nextInt(15);
            }
        return s;
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == reset) {
            pussel.dispose();
            new Main();
        }
        if (e.getSource() == order) {
            pussel.dispose();
            new Main(15);
        } else {
            JButton tile = (JButton) e.getSource();
            int tileIndex = getIndexOfButton(tile);

            if (
                    isValidMove(tileIndex)
            ) {
                blankTile.setText(tile.getText());
                blankTile.setVisible(true);
                tile.setVisible(false);
                blankTile = tile;
                if(didIWin()) {
                    JOptionPane.showMessageDialog(this,"Congratulations you won");
                }
            }
        }

    }

    public int getIndexOfButton(JButton tile) {
        for (int i = 0; i < p1.getComponents().length; i++) {
            if (tile.hashCode() == p1.getComponent(i).hashCode()) {
                return i;
            }

        }
        return -1;
    }

    public boolean isValidMove(int index) {
        int RightIndex = index + 1;
        int LeftIndex = index - 1;
        int DownIndex = index + 4;
        int UpIndex = index - 4;
        if (UpIndex > -1 && p1.getComponent(UpIndex) == blankTile) {
            return true;
        }
        if (DownIndex < 16 && p1.getComponent(DownIndex) == blankTile) {
            return true;
        }
        if (LeftIndex != -1 && LeftIndex != 3 && LeftIndex != 7 && LeftIndex != 11 && p1.getComponent(LeftIndex) == blankTile) {
            return true;
        }
        if (RightIndex != 4 && RightIndex != 8 && RightIndex != 12 && RightIndex != 16 && p1.getComponent(RightIndex) == blankTile) {
            return true;
        }

        return false;
    }

    public boolean didIWin() {
        for (int i = 0; i < p1.getComponents().length-1; i++) {
            JButton tile = (JButton) p1.getComponent(i);
            if (tile.isVisible() == false) {
                return false;
            }
            String tileText = tile.getText();
            String expectedText = Integer.toString(i + 1);
            if (!tileText.equals(expectedText)) {
                return false;
            }

        }return true;

    }

    public static void main (String[]args){
        new Main();
    }
}