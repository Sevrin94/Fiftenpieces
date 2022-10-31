import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Main extends JFrame implements ActionListener {


    JFrame pussel = new JFrame();
    JPanel p1 = new JPanel();
    JPanel p2 = new JPanel();
    JButton blankTile = new JButton();

    int [] nummer = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12,13,14,15};



    public Main() {
        pussel.setBounds(0, 0, 500, 500);
        pussel.setLayout(new GridLayout(4, 4));
        knappar();
        pussel.setVisible(true);
        pussel.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
public String numret(){
    Random random = new Random();
        int n = random.nextInt(15);
        String s= " ";

        while(s.equals(" "))
        if(nummer[n] != 0){
            s = String.valueOf(nummer[n]);
            nummer[n]=0;
        }else{
            n = random.nextInt(15);
        }
        return s;
}
    public void knappar() {
        pussel.add(p1, BorderLayout.NORTH);
        for (int i = 1; i < 16; i++) {
            String buttonLabel = numret();
            JButton tile = new JButton(buttonLabel);
            p1.add(tile);
            tile.addActionListener(this);
        }
        JButton reset = new JButton("Reset");
        p1.add(blankTile);
        pussel.add(p2, BorderLayout.SOUTH);
        p2.add(reset);
        blankTile.setVisible(false);
        reset.addActionListener(this);
        blankTile.addActionListener(this);
    }


    public void actionPerformed(ActionEvent e) {
        if (e.getSource()== )
            knappar();
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
