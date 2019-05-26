package javaapplication2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.Border;

public class Board extends JPanel implements ActionListener {

    private final int B_WIDTH = 625;
    private final int B_HEIGHT = 625;
    private final int DOT_SIZE = 25;
    private final int ALL_DOTS = 625;
    private final int RAND_POS = 24;
    private final int DELAY = 160;

    private int score=0;
    private  int x[] = new int[ALL_DOTS];
    private  int y[] = new int[ALL_DOTS];

    private int dots;
    private int apple_x;
    private int apple_y;

    private boolean leftDirection = false;
    private boolean rightDirection = true;
    private boolean upDirection = false;
    private boolean downDirection = false;
    private boolean inGame = true;
    boolean isnear=false;

    private Timer timer;
    private Image ball;
    private Image apple;
    private Image head;
    private Image largehead;
    private Image lefthead;
    private Image righthead;
    private Image tophead;
    private Image bottomhead;
    private JButton button;
    private JFrame frame;
    private JButton restart;
    public Board() {
        initBoard();
        restart  = new JButton("Restart"); 
        restart.setBackground(Color.BLACK);
        restart.setForeground(Color.white);
        restart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                score=0;
                rightDirection=true;
                leftDirection=false;
                upDirection = false;
                downDirection = false;
                head=righthead;
            x=new int[ALL_DOTS];
            y=new int[ALL_DOTS];
            inGame=true;
              dots = 3;
         for (int z = 0; z < dots; z++) {
            x[z] = 75 - z * 25;
            y[z] = 50;
        }
        
        locateApple();

         restart.setVisible(false);
         button.setText("Your Score : 0");
         button.setVisible(true);
        timer = new Timer(DELAY, Board.this);
        timer.start();
            
               
            }
        });
          restart.setVisible(false);
          add(restart);
          button=new JButton();
         button.setFocusable(false);
         button.setOpaque(true);
          button.setBackground(new Color(0, 0, 0, 0));
          button.setForeground(Color.white);
          button.setText("Your Score : 0");
          add(button);
//        setLayout(new GridLayout(25, 25,0,0));
////       
////        //setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
////
//        JLabel[][] smallPanels = new JLabel[25][25];
//        for (int i = 0; i < smallPanels.length; i++) {
//            for (int j = 0; j < smallPanels[i].length; j++) {
//                smallPanels[i][j] = new JLabel("");
//                 Border border = BorderFactory.createLineBorder(Color.WHITE, 1);
// 
//        // set the border of this component
//                  smallPanels[i][j].setBorder(border);
//                smallPanels[i][j].setOpaque(false);
//                smallPanels[i][j].setBackground(new Color(0, 0, 0, 0));
//                add(smallPanels[i][j]);
//            }
//        }
    }
    
    private void initBoard() {

        addKeyListener(new TAdapter());
        setBackground(Color.BLACK);
        setFocusable(true);

        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        loadImages();
        initGame();
    }

    private void loadImages() {

        ImageIcon iid = new ImageIcon("src/javaapplication2/snakeimage.png");
        ball = iid.getImage();

        ImageIcon iia = new ImageIcon("src/javaapplication2/rat.png");
        apple = iia.getImage();

        ImageIcon iihr = new ImageIcon("src/javaapplication2/rightmouth.png");
        righthead = iihr.getImage();
        
         ImageIcon iihl = new ImageIcon("src/javaapplication2/leftmouth.png");
        lefthead = iihl.getImage();
        
        ImageIcon iiht = new ImageIcon("src/javaapplication2/upmouth.png");
        tophead = iiht.getImage();
        
         ImageIcon iihb = new ImageIcon("src/javaapplication2/downmouth.png");
         bottomhead = iihb.getImage();
         head=righthead;
         largehead=head.getScaledInstance(32, 32, Image.SCALE_SMOOTH);
        
    }

    private void initGame() {

        dots = 3;
        for (int z = 0; z < dots; z++) {
            x[z] = 75 - z * 25;
            y[z] = 50;
        }
        
        locateApple();

        timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
       
    }
    
    private void doDrawing(Graphics g) {
        if (inGame) {

            g.drawImage(apple, apple_x, apple_y, this);

            for (int z = 0; z < dots; z++) {
                if (z == 0) {
                    if(isnear){
                    
                        g.drawImage(largehead, x[z], y[z], this);
                    }
                    else{
                         g.drawImage(head, x[z], y[z], this);
                    }
                } else {
                    g.drawImage(ball, x[z], y[z], this);
                }
            }

            Toolkit.getDefaultToolkit().sync();

        } else {

            gameOver(g);
        }        
    }

    private void gameOver(Graphics g) {
        
//        String msg = "Game Over";
//        Font small = new Font("Helvetica", Font.BOLD, 14);
//        FontMetrics metr = getFontMetrics(small);
//
//        g.setColor(Color.white);
//        g.setFont(small);
//        
//        g.drawString(msg, (B_WIDTH - metr.stringWidth(msg)) / 2, B_HEIGHT / 2);
//       
        button.setVisible(false);
        restart.setVisible(true);

       
    }

    private void checkApple() {
        
        int xdiff=(x[0]-apple_x);
        int ydiff=(y[0]-apple_y);
        if (Math.abs(xdiff)<=50&&Math.abs(ydiff)==0) {
            isnear=true;
        }
        else if(Math.abs(ydiff)<=50&&Math.abs(xdiff)==0){
            isnear=true;
        }
        else{
            isnear=false;
        }
        System.err.println("near"+isnear);
        if ((x[0] == apple_x) && (y[0] == apple_y)) {
            score=score+5;
            button.setText("Your Score : "+score);
         
            dots++;
            locateApple();
        }
    }

    private void move() {

        for (int z = dots; z > 0; z--) {
            x[z] = x[(z - 1)];
            y[z] = y[(z - 1)];
        }

        if (leftDirection) {
            x[0] -= DOT_SIZE;
        }

        if (rightDirection) {
            x[0] += DOT_SIZE;
        }

        if (upDirection) {
            y[0] -= DOT_SIZE;
        }

        if (downDirection) {
            y[0] += DOT_SIZE;
        }
    }

    private void checkCollision() {
        
        for (int z = dots; z > 0; z--) {

            if ((x[0] == x[z]) && (y[0] == y[z])) {
                inGame = false;
                
            }
        }
        if (!inGame) {
            timer.stop();
        }
        if (y[0] >= B_HEIGHT) {
            y[0]=-25;
        }

        if (y[0] <-25) {
            y[0]=B_HEIGHT;
        }

        if (x[0] >= B_WIDTH) {
            x[0]=-25;
           // inGame = false;
        }

        if (x[0] <-25) {
             x[0]=B_WIDTH;
           // inGame = false;
        }
        
        
    }

    private void locateApple() {

        int r = (int) (Math.random() * RAND_POS);
        apple_x = ((r * DOT_SIZE));

        r = (int) (Math.random() * RAND_POS);
        apple_y = ((r * DOT_SIZE));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (inGame) {

            checkApple();
            checkCollision();
            move();
        }

        repaint();
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            if (x[0]<0||x[0]>625||y[0]<0||y[0]>625) {
                return;
            }
            if ((key == KeyEvent.VK_LEFT) && (!rightDirection)) {
                leftDirection = true;
                upDirection = false;
                downDirection = false;
                head=lefthead;
            }

            if ((key == KeyEvent.VK_RIGHT) && (!leftDirection)) {
                rightDirection = true;
                upDirection = false;
                downDirection = false;
                head=righthead;
            }

            if ((key == KeyEvent.VK_UP) && (!downDirection)) {
                upDirection = true;
                rightDirection = false;
                leftDirection = false;
                head=tophead;
            }

            if ((key == KeyEvent.VK_DOWN) && (!upDirection)) {
                downDirection = true;
                rightDirection = false;
                leftDirection = false;
                head=bottomhead;
            }
            largehead=head.getScaledInstance(32, 32, Image.SCALE_SMOOTH);

        }
    }
}
