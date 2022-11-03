package Pusslet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Scanner;

public class Pussel extends JFrame implements ActionListener{
    JFrame pussel = new JFrame();
    JPanel p1 = new JPanel();
    JPanel p2 = new JPanel();
    JButton blankTile = new JButton();
    JButton reset = new JButton("Reset");

    JButton order = new JButton("Order");
    JButton två = new JButton("2x2");
    JButton tre = new JButton("3x3");
    JButton fyra = new JButton("4x4");
    JButton fem = new JButton("5x5");
    JButton sex = new JButton("6x6");
    int x = 0;
    JLabel count = new JLabel("Antal drag " + x);


    int dimension = 4;
    int antal;
    int[] nummer;

    public Pussel(int d){
        setDimension(d);
        antal = (int) (Math.pow(dimension, 2));
        nummer = new int [antal];
        for (int i=0; i <antal-1; i++)
            nummer[i] = i + 1;
        skapaPanel();
        knappar();
    }
    public Pussel(){
        antal = (int) (Math.pow(dimension, 2));
        nummer = new int [antal];
        skapaPanel();
        knapparLösning();
    }

    public void skapaPanel(){
        pussel.setBounds(0, 0, 600, 600);
        p1.setBounds(0, 0, 500,500);
        p1.setLayout(new GridLayout(dimension, dimension));
        pussel.add(p1);
        pussel.add(p2, BorderLayout.SOUTH);
        p2.add(reset);
        p2.add(order);
        p2.add(count);
        p2.add(två);
        p2.add(tre);
        p2.add(fyra);
        p2.add(fem);
        p2.add(sex);
        två.addActionListener(this);
        tre.addActionListener(this);
        fyra.addActionListener(this);
        fem.addActionListener(this);
        sex.addActionListener(this);
        pussel.setVisible(true);
        pussel.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public String numret() {
        Random random = new Random();
        int n = random.nextInt(antal-1);
        String s = " ";

        while (s.equals(" ")) {
            if (nummer[n] != 0) {
                s = String.valueOf(nummer[n]);
                nummer[n] = 0;
            } else {
                n = random.nextInt(antal-1);

            }
        }return s;

    }
    public void knappar() {
        for (int i = 1; i < antal; i++) {
            String buttonLabel = numret();
            JButton tile = new JButton(buttonLabel);
            p1.add(tile);
            tile.addActionListener(this);
        }
        p1.add(blankTile);
        blankTile.setVisible(false);
        reset.addActionListener(this);
        order.addActionListener(this);
        blankTile.addActionListener(this);
    }
    public void knapparLösning() {
        for(int i=1; i<antal-1; i++){
            String buttonLabel = String.valueOf(i);
            JButton tile = new JButton(buttonLabel);
            p1.add(tile);
            tile.addActionListener(this);
        }
        p1.add(blankTile);
        JButton tile = new JButton(String.valueOf(antal-1));
        p1.add(tile);
        blankTile.setVisible(false);
        tile.addActionListener(this);
        reset.addActionListener(this);
        order.addActionListener(this);
        blankTile.addActionListener(this);
    }
    public boolean didIWin() {
        for (int i = 0; i < p1.getComponents().length-1; i++) {
            JButton tile = (JButton) p1.getComponent(i);
            if (!tile.isVisible()) {
                return false;
            }
            String tileText = tile.getText();
            String expectedText = Integer.toString(i + 1);
            if (!tileText.equals(expectedText)) {
                return false;
            }

        }return true;

    }
    public void speletVunnet(){
        JOptionPane.showMessageDialog(null,  "Grattis! Det tog " + x + " drag",
                "Grattis!", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("src\\Bild\\giphy.gif"));
    }

    public int getDimension(){return dimension;}
    public void setDimension(int d){
        dimension = d;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==två) {
            pussel.dispose();
            new Pussel(2);
        }
        else if(e.getSource()==tre) {
            pussel.dispose();
            new Pussel(3);
        }
        else if(e.getSource()==fyra) {
            pussel.dispose();
            new Pussel(4);
        }
        else if(e.getSource()==fem) {
            pussel.dispose();
            new Pussel(5);
        }
        else if(e.getSource()==sex) {
            pussel.dispose();
            new Pussel(6);
        }
        else if(e.getSource()==reset)

        {
            pussel.dispose();
            new Pussel(getDimension());
        }
        else if(e.getSource()==order)

        {
            pussel.dispose();
            new Pussel();
        }
        else{JButton tile = (JButton) e.getSource();
            int tileIndex = getIndexOfButton(tile);

            if (
                    isValidMove(tileIndex)
            ) {
                blankTile.setText(tile.getText());
                blankTile.setVisible(true);
                tile.setVisible(false);
                blankTile = tile;
                x++;
                count.setText("Antal drag " + x);
                if(didIWin())
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
        int DownIndex = index +dimension;
        int UpIndex = index -dimension;
        if (UpIndex > -1 && p1.getComponent(UpIndex) == blankTile) {
            return true;
        }
        if (DownIndex < antal && p1.getComponent(DownIndex) == blankTile) {
            return true;
        }
        if((LeftIndex +1 )% dimension !=0 && p1.getComponent(LeftIndex) == blankTile){
            return true;
        }
        if (RightIndex % dimension !=0 && p1.getComponent(RightIndex) == blankTile){
            return true;
        }

        return false;
    }

    }

