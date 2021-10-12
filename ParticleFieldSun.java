import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.event.MouseInputListener;

public class ParticleFieldSun extends JPanel {
    private boolean running = true;

    private List<Particle> particles = new ArrayList<Particle>();
    private List<Particle> stars = new ArrayList<Particle>();
    private static final Random rng = new Random();
    Color color;
    
    int width;
    int height;
    int x = 0;
    int y = 0;

    int sunX = 0;
    int sunY = 0;

    boolean explode = false;
    public ParticleFieldSun(int n, int width, int height){
        this.width = width;
        this.height = height;
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(new Color(2,2,3));
        //this.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        color = new Color(rng.nextInt(255), rng.nextInt(255), rng.nextInt(255));

        for (int i=0; i<n; i++){
            particles.add(new Particle(width,height));
        }

        for (int i = 0; i < 1000; i++){
            stars.add(new Particle(width,height));
        }
        
        class ft implements Runnable {
            public void run(){
                animate();
            }
        }
        Thread animate = new Thread(new ft());

        animate.start();

    }

    @Override 
    public void paintComponent(Graphics g){
        super.paintComponent(g); // erase previous contents
        Graphics2D g2d = (Graphics2D) g;
        //Displaying stars
        g2d.setColor(new Color(255,255,255));
        for (Particle star : stars){
            g2d.fillOval(star.x, star.y, 2, 1);
        }
        //Color color = new Color(rng.nextInt(255), rng.nextInt(255), rng.nextInt(255));
        g2d.setColor(color);
        for (Particle particle : particles){
            if (particle.dirX > 0){
                particle.size = 2;
                g2d.fillRect(particle.x, particle.y, particle.size, particle.size);
            }
        }

        g2d.setColor(new Color(255, 234, 46));
        g2d.fillOval(sunX-250,sunY-250,500,500);

        g2d.setColor(color);
        for (Particle particle : particles){
            if (particle.dirX < 0){
                particle.size = 5;
                g2d.fillRect(particle.x, particle.y, particle.size, particle.size);
            }
        }

    }

    public void animate(){
        while (running){

            PointerInfo a = MouseInfo.getPointerInfo();
            Point b = a.getLocation();
            SwingUtilities.convertPointFromScreen(b,this);
            int mouseX = (int) b.getX();
            int mouseY = (int) b.getY();

            sunX = mouseX;
            sunY = mouseY;
            
            try {
                Thread.sleep(30);
                this.repaint();
            }
            catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
            
            if (explode == false){
                for (Particle particle : particles){
                    if (mouseX > particle.x){ // ??
                        particle.dirX +=  1;
                        
                    }

                        //particle.dirX = (mouseX - particle.x) ;
                        
                    else if (mouseX < particle.x) {// shouldn't it be particle.x??
                        particle.dirX +=  -2;
                        
                        //particle.dirY = (mouseY - particle.y) ;
                    }   
                    else 
                        particle.dirX += 0;

                    if (mouseY > particle.y){ // ??
                        particle.dirY +=  1; // should add 1
                        
                    }
                    else if (mouseY < particle.x) {// Shouldn't it be particle.y?? make it particle.x
                        particle.dirY +=  -1; // should add -1
                        
                    }   
                    else
                        particle.dirY = 0;

                   

                    particle.move();
                }
    
            }
            this.repaint();
        }
        
    }

    public void terminate() {
        running = false;
    }
    
    public static void main(String[] args){
        JFrame f = new JFrame("Particles");
        // Tell the frame to obey the close button
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(new FlowLayout());
        f.setPreferredSize(new Dimension(800,800));        

        ParticleFieldSun particleField = new ParticleFieldSun(1000,800,800);
        f.add(particleField);
        //f.pack();
        f.setVisible(true);  
        f.pack();
        //f.addMouseMotionListener(particleField.new mouseFollower());
        //.addMouseListener(particleField.new mouseFollower());
        //particleField.animate();
    }
    
    class mouseFollower implements MouseMotionListener, MouseListener{

        @Override
        public void mouseDragged(MouseEvent e) {
            
            
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            /*
            int mouseX = e.getX();
            int mouseY = e.getY();
            for (Particle particle : particles){
            //// X Direction
                if (mouseX > particle.x) particle.dirX = (mouseX - particle.x) ;
                
                else if (mouseX < particle.x){
                    particle.dirX =  (mouseX - particle.x) ;
                }
                else{
                    //particle.dirX = 0;
                }
                ////Y Direction
                if (mouseY > particle.y){
                    particle.dirY = (mouseY - particle.y) ;
                }
                else if (mouseY < particle.y){
                    particle.dirY =  (mouseY - particle.y) ;
                }
                else{
                    //particle.dirY = 0;
                }

            }
            */
        }
        
        
        @Override
        public void mouseClicked(MouseEvent e) {
            if (explode == false)
                explode = true;
            else
                explode = false;
        }
    
        @Override
        public void mousePressed(MouseEvent e) {
            
        }
    
        @Override
        public void mouseReleased(MouseEvent e) {
            
            
        }
    
        @Override
        public void mouseEntered(MouseEvent e) {
            /*
            int mouseX = e.getX();
            int mouseY = e.getY();
            for (Particle particle : particles){
            //// X Direction
                if (mouseX > particle.x){
                    particle.dirX = 1;
                }
                else if (mouseX < particle.x){
                    particle.dirX = -1;
                }
                else{
                    particle.dirX = 0;
                }
                ////Y Direction
                if (mouseY > particle.y){
                    particle.dirY = 1;
                }
                else if (mouseY < particle.y){
                    particle.dirY = -1;
                }
                else{
                    particle.dirY = 0;
                }

            }
            */
        }
    
        @Override
        public void mouseExited(MouseEvent e) {
            // TODO Auto-generated method stub
            
        }
        
    }
    
    
    
}