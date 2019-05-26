package javaapplication2;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.GridLayout;
import javafx.scene.layout.GridPane;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Snake extends JFrame {
Board b;
    public Snake() {
        
        initUI();
        
    }
    
    private void initUI() {
        
       b=new Board();
        
        add(b);
        
              
        setResizable(false);
        pack();
        
        setTitle("Snake");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

  public void removepane(){
      remove(b);
  }
    

    public static void main(String[] args) {
        
        EventQueue.invokeLater(() -> {
            JFrame ex = new Snake();
            ex.setVisible(true);
        });
    }
}
