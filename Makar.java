import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Makar extends JFrame {
    public Makar() {
        this.setTitle("hskfjsd");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(new Dimension(500, 500));
        this.setLocationRelativeTo(null); // ??????
        this.getContentPane().setLayout(new BorderLayout()); 
        this.getContentPane().add(new GamePanel());
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new Pacman();
    }

    public class Makar extends JPanel {
        private Image pmImage; // ????? (64*64 pixels)
        private int xCoordinate = 200; // ???????
        private int yCoordinate = 200;
        boolean key_right, key_left, key_down, key_up, key_space; // Input booleans

        URL urlForImage;
        ImageIcon usFlag;

        public GamePanel() {
            loadImage("C");
            this.setFocusable(true); // ????, ??KeyListener????
            addKeyListener(new KeyInput()); // Add it to the JPanel
        }

        // ????
        public void loadImage(String img) {
            urlForImage = getClass().getResource("makar.png");
            usFlag = new ImageIcon(urlForImage);
            pmImage = usFlag.getImage();
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            setBackground(Color.white); // ????
            g.drawImage(pmImage, xCoordinate, yCoordinate, this); // ??

            // ??
            if (key_down) { loadImage("D"); yCoordinate++; }

            // ??
            else if (key_up) { loadImage("U"); yCoordinate--; }

            // ??
            else if (key_right) { loadImage("R"); xCoordinate++; }

            // ?
            else if (key_left) { loadImage("L"); xCoordinate--; }
            
            else if (key_space) {
            //check to see if we collided with a gem
            //if yes then pick up the gem
            // if no then do nothing
            // if wrong gem display message oops
            // otherwise make the gem disappear
            //add it to the game board menu as a recently picked up gem

            }

            // ?? - ??????? 
            //for (int index = 0; index < 10000000; index++) {}

            repaint();
        }

        private class KeyInput implements KeyListener {
            public void keyTyped(KeyEvent e) {}

            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == e.VK_DOWN) key_down = false;
                if (e.getKeyCode() == e.VK_UP) key_up = false;
                if (e.getKeyCode() == e.VK_RIGHT) key_right = false;
                if (e.getKeyCode() == e.VK_LEFT) key_left = false;
                if (e.getKeyCode() == e.VK_SPACE) key_space = false;
            }

            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == e.VK_DOWN) key_down = true;
                if (e.getKeyCode() == e.VK_UP) key_up = true;
                if (e.getKeyCode() == e.VK_RIGHT) key_right = true;
                if (e.getKeyCode() == e.VK_LEFT) key_left = true;
                if (e.getKeyCode() == e.VK_SPACE) key_left = true;
            }
        }
    }
}