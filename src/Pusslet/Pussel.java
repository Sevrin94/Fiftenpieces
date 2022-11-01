package Pusslet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static java.util.Collections.swap;

public class Pussel extends JFrame implements ActionListener{
    JFrame pussel = new JFrame();
    JPanel p1 = new JPanel(new GridLayout(4,4));
    JPanel p2 = new JPanel();
    JButton blankTile = new JButton();
    JButton reset = new JButton("Reset");

    JButton order = new JButton("Order");
    int x = 0;
    JLabel count = new JLabel("Antal drag " + x);

    List<String> list = new ArrayList<>();
    List<String> facit = new ArrayList<>(Arrays.asList
            ("1", "2","3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "blank"));
    int[] nummer = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};

    public Pussel(){
        skapaPanel();
        knappar();
    }
    public Pussel(int n){
        skapaPanel();
        knapparLösning(n);
    }

    public void skapaPanel(){
        pussel.setBounds(0, 0, 600, 600);
        p1.setBounds(0, 0, 500,500);
        p1.setLayout(new GridLayout(4, 4));
        pussel.add(p1);
        pussel.add(p2, BorderLayout.SOUTH);
        p2.add(reset);
        p2.add(order);
        p2.add(count);
        pussel.setVisible(true);
        pussel.setDefaultCloseOperation(EXIT_ON_CLOSE);
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
    public void knappar() {
        for (int i = 1; i < 16; i++) {
            String buttonLabel = numret();
            JButton tile = new JButton(buttonLabel);
            p1.add(tile);
            list.add(buttonLabel);
            tile.addActionListener(this);
        }
        p1.add(blankTile);
        list.add("blank");
        blankTile.setVisible(false);
        reset.addActionListener(this);
        order.addActionListener(this);
        blankTile.addActionListener(this);
    }
    public void knapparLösning(int n) {
        for(int i=1; i<n; i++){
            String buttonLabel = String.valueOf(i);
            JButton tile = new JButton(buttonLabel);
            p1.add(tile);
            list.add(buttonLabel);
            tile.addActionListener(this);
        }
        p1.add(blankTile);
        list.add("blank");
        JButton tile = new JButton("15");
        list.add("15");
        p1.add(tile);
        blankTile.setVisible(false);
        tile.addActionListener(this);
        reset.addActionListener(this);
        order.addActionListener(this);
        blankTile.addActionListener(this);
    }
    public void speletVunnet(){
        JOptionPane.showMessageDialog(null,  "Grattis! Det tog " + x + " drag",
                "Grattis!", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("src\\Bild\\giphy.gif"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==reset)

        {
            pussel.dispose();
            new Pussel();
        }
        else if(e.getSource()==order)

        {
            pussel.dispose();
            new Pussel(15);
        }
        else{JButton tile = (JButton) e.getSource();
            int tileIndex = getIndexOfButton(tile);

            if (
                    isValidMove(tileIndex)
            ) {
                swap(list, list.indexOf(tile.getText()), list.lastIndexOf("blank"));

                blankTile.setText(tile.getText());
                blankTile.setVisible(true);
                tile.setVisible(false);
                blankTile = tile;
                x++;
                count.setText("Antal drag " + x);
                if (list.equals(facit))
                    speletVunnet();
            }
        }

    }
    public int getIndexOfButton(JButton tile) {
        for (int i = 0; i < p1.getComponents().length; i++) {
            if (tile.hashCode() == p1.getComponent(i).hashCode()) {
                return i; }

        }
        return -1;
    }
    public boolean isValidMove(int index) {
        int RightIndex = index +1;
        int LeftIndex = index -1;
        int DownIndex = index +4;
        int UpIndex = index -4;
        if (UpIndex > -1 && p1.getComponent(UpIndex) == blankTile) {
            return true;
        }
        if (DownIndex < 16 && p1.getComponent(DownIndex) == blankTile) {
            return true;
        }
        if (LeftIndex !=-1 && LeftIndex !=3 && LeftIndex !=7 && LeftIndex !=11 && p1.getComponent(LeftIndex) == blankTile) {
            return true;
        }
        if (RightIndex !=4 && RightIndex !=8 && RightIndex !=12 && RightIndex !=16 && p1.getComponent(RightIndex) == blankTile) {
            return true;
        }

        return false;
    }

    }

