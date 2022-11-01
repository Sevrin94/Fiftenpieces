package Pusslet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static java.util.Collections.swap;


public class Main extends JFrame implements ActionListener {

    JFrame pussel = new JFrame();
    JPanel p1 = new JPanel(new GridLayout(4,4));
    JPanel p2 = new JPanel();
    JButton blankTile = new JButton();
    JButton reset = new JButton("Reset");
    boolean vunnit = false;

    JButton order = new JButton("Order");
    int x = 0;
    JLabel count = new JLabel("Antal drag " + x);

    List<String> list2 = new ArrayList<>();
    List<String> facit = new ArrayList<>(Arrays.asList
            ("1", "2","3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "blank"));
    int[] nummer = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
    String[] list = new String[16];

    String [][] place = new String[4][4];

    public Main (int n){
        pussel.setBounds(0, 0, 500, 600);
        p1.setBounds(0, 0, 500,500);
        p1.setLayout(new GridLayout(4, 4));
        knappar2(n);
        pussel.setVisible(true);
        pussel.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }



    public Main() {
        pussel.setBounds(0, 0, 500, 600);
        p1.setBounds(0, 0, 500,500);
        p1.setLayout(new GridLayout(4, 4));
        knappar();
        sortering();
        pussel.setVisible(true);
        pussel.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void knappar2(int n) {
        for(int i=1; i<n; i++){
            String buttonLabel = String.valueOf(i);
            JButton tile = new JButton(buttonLabel);
            p1.add(tile);
            list2.add(buttonLabel);
            tile.addActionListener(this);
        }
        pussel.add(p1);
        p1.add(blankTile);
        list2.add("blank");
        JButton tile = new JButton("15");
        list2.add("15");
        p1.add(tile);
        pussel.add(p2, BorderLayout.SOUTH);
        p2.add(reset);
        p2.add(order);
        p2.add(count);
        blankTile.setVisible(false);
        tile.addActionListener(this);
        reset.addActionListener(this);
        order.addActionListener(this);
        blankTile.addActionListener(this);
    }

    public void knappar() {
        for (int i = 1; i < 16; i++) {
            String buttonLabel = numret();
            JButton tile = new JButton(buttonLabel);
            p1.add(tile);
            list[i] = buttonLabel;
            list2.add(buttonLabel);
            tile.addActionListener(this);

        }

        pussel.add(p1);
        p1.add(blankTile);
        list[15] = "blank";
        list2.add("blank");
        pussel.add(p2);
        p2.add(reset);
        p2.add(order);
        p2.add(count);
        blankTile.setVisible(false);
        reset.addActionListener(this);
        order.addActionListener(this);
        blankTile.addActionListener(this);
        for(var s:list2)
            System.out.println(s);
        System.out.println(list2.get(15) + "hej");
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
    public void sortering (){
        int x = 0;
        for (int i=0; i<4; i++){
            for (int j=0; j<4; j++){
                place [i][j] = list[x];
                x++;
            }
        }
    }
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==reset)

        {
            pussel.dispose();
            new Main();
        }
        else if(e.getSource()==order)

        {
            pussel.dispose();
            new Main(15);
        }
        else{JButton tile = (JButton) e.getSource();
            int tileIndex = getIndexOfButton(tile);

            if (
                    isValidMove(tileIndex)
            ) {
                swap(list2, list2.indexOf(tile.getText()), list2.lastIndexOf("blank"));

                blankTile.setText(tile.getText());
                blankTile.setVisible(true);
                tile.setVisible(false);
                blankTile = tile;
                x++;
                count.setText("Antal drag " + x);
                System.out.println(list2.indexOf(tile.getText()) + " " + list2.lastIndexOf("blank"));
                if (list2.equals(facit))
                    System.out.println("grattis");
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

    public static void main (String[]args){
        new Main(15);
    }
}