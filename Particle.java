
import java.awt.event.MouseEvent;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
//import javax.swing.event.MouseInputListener;



public class Particle extends JPanel{
    private static final Random rng = new Random();
    private static final double BUZZY = 10; //0.7 is deafault
    double heading = Math.PI*2*rng.nextDouble();

 
    int x,y;
    int dirX, dirY;
    int width, height;
    int size = 2; 


    public Particle(int width, int height){
        this.width = width;
        this.height = height;
        this.setPreferredSize(new Dimension(200, 200));
        this.setBackground(new Color(2,2,3));

        x = rng.nextInt(width);
        y = rng.nextInt(height);

    }

    public void move(){
       
            x += Math.cos(heading) + dirX;
            y += Math.sin(heading) + dirY;
  
        heading += rng.nextGaussian()*BUZZY;
    }

    
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }


    

    
    @Override 
    public void paintComponent(Graphics g){
        super.paintComponent(g); // erase previous contents
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLUE);
        g2d.fillRect(x, y, width,height);
    }

        public static void main(String[] args){
            JFrame f = new JFrame("Particles");
            // Tell the frame to obey the close button
            f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            f.setLayout(new FlowLayout());
            f.add(new Particle(3,3));
            // Let's add two separate ShapePanel instances, just to see how it works.
            f.pack();
            f.setVisible(true);   
        
        }
        

}
