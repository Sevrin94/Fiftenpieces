import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Main extends JFrame implements ActionListener {


    JFrame pussel = new JFrame();

    public Main() {
        pussel.setBounds(0, 0, 500, 500);
        pussel.setLayout(new GridLayout(4, 4));
        knappar();
        pussel.setVisible(true);
        pussel.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void knappar() {
        for (int i = 1; i < 16; i++) {
            String j = String.valueOf(i);
            pussel.add(new JButton(j));
            (new JButton(j)).addActionListener(this);

        }
    }


    public void actionPerformed(ActionEvent e) {

    }


        public static void main(String[] args) {
        new Main();
        }
    }
