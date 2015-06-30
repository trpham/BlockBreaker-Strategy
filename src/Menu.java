
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * The menu class of the game, consists of options to play, help and quit
 */
public class Menu extends javax.swing.JFrame {

    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;

    private javax.swing.JButton Helpbutton;
    private javax.swing.JButton Playbutton;
    private javax.swing.JButton QuitButton;
    
    
    HelpScreen help = new HelpScreen();
    
    /**
     * Constructor to initialize the game
     */
    public Menu() {
        initComponents();
    }
 /**
  * GUI menu design of the game
  */
    private void initComponents() {
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        Helpbutton = new javax.swing.JButton();
        Playbutton = new javax.swing.JButton();
        QuitButton = new javax.swing.JButton();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(640, 640));
        getContentPane().setLayout(null);
            
        jLabel1.setFont(new java.awt.Font("Eras Bold ITC", 1, 65));
        jLabel1.setForeground(new java.awt.Color(204, 102, 0));
        jLabel1.setText("BRICK");
        jLabel1.setBounds(100, -30, 280, 270);
        getContentPane().add(jLabel1);

        jLabel2.setFont(new java.awt.Font("Eras Bold ITC", 1, 65));
        jLabel2.setForeground(new java.awt.Color(204, 102, 0));
        jLabel2.setText("BREAKER");
        jLabel2.setBounds(100, 60, 370, 230);
        getContentPane().add(jLabel2);
        /**
         * Designing the buttons with different fonts, size and where it's at
         */
        Helpbutton.setBackground(new java.awt.Color(0, 0, 0));
        Helpbutton.setFont(new java.awt.Font("Tahoma", 1, 14));
        Helpbutton.setForeground(new java.awt.Color(255, 255, 255));
        Helpbutton.setText("About / Help");
        Helpbutton.setBounds(258, 370, 120, 37);
        getContentPane().add(Helpbutton);

        Playbutton.setBackground(new java.awt.Color(0, 0, 0));
        Playbutton.setFont(new java.awt.Font("Consolas", 1, 48)); // NOI18N
        Playbutton.setForeground(new java.awt.Color(255, 255, 255));
        Playbutton.setText("Play");
        Playbutton.setBounds(250, 280, 137, 70);
        getContentPane().add(Playbutton);

        QuitButton.setBackground(new java.awt.Color(0, 0, 0));
        QuitButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        QuitButton.setForeground(new java.awt.Color(255, 255, 255));
        QuitButton.setText("Quit");
        QuitButton.setBounds(275, 420, 80, 30);
        getContentPane().add(QuitButton);
        
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("main_background.jpg")));
        jLabel3.setBounds(0, 0, 640, 640);
        getContentPane().add(jLabel3);
        /**
         * What happens when the mouse is clicked on it
         * It will open a new window/JFrame which is the game itself
         */
        Playbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PlaybuttonActionPerformed(evt);
            }
        });
        
        Helpbutton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                HelpbuttonMouseClicked(evt);
            }
        });
        
        QuitButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                QuitButtonMouseClicked(evt);
            }
        });
    }

    private void HelpbuttonMouseClicked(java.awt.event.MouseEvent evt) {
        help.setVisible(true);
    }

    private void PlaybuttonActionPerformed(java.awt.event.ActionEvent evt) {
        setVisible(false);
        JFrame frame = new JFrame("Block Breaker");

        BlockPanel panel = new BlockPanel();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(panel);
        frame.setVisible(true);
        frame.setSize(640, 640);
        frame.setResizable(false);
         new SoundBackground();
    }

    private void QuitButtonMouseClicked(java.awt.event.MouseEvent evt) {
        System.exit(0);
    }
/**
 * Game exits when x button is clicked
 */
    class BlockPanel extends JPanel implements KeyListener {
/**
 * Blocks, ball, powerup are created with arrayList
 */
        ArrayList<Block> blocks = new ArrayList<>();
        ArrayList<Block> ball = new ArrayList<>();
        ArrayList<Block> powerup = new ArrayList<>();
        
        private boolean paused;
        
        Block paddle;
        Thread thread;
        Animate animate;
 
        int size = 25;
        
        Image img = Toolkit.getDefaultToolkit().createImage("img/game_background.jpg");
        int score = 0;
/**
 *  Blocks are being made on the panel and multiplied with different imported pictures
 *  Paddle is also being made
 */
        BlockPanel() {
            
            paddle = new Block(255, 480, 120, 15, "img/paddle.png"); 
            ball.add(new Block(305, 455, 30, 25, "img/ball.png"));
           
            for (int i = 0; i < 8; i++) 
                blocks.add(new Block((i * 60 + 70), 30, 55, 18, "img/brick_1.png"));

            for (int i = 0; i < 8; i++) 
                blocks.add(new Block((i * 60 + 70), 60, 55, 18, "img/brick_2.png"));
            
            for (int i = 0; i < 8; i++) 
                blocks.add(new Block((i * 60 + 70), 90, 55, 18, "img/brick_3.png"));
            
            for (int i = 0; i < 8; i++) 
                blocks.add(new Block((i * 60 + 70), 120, 55, 18, "img/brick_4.png"));
            
            for (int i = 0; i < 8; i++) 
                blocks.add(new Block((i * 60 + 70), 150, 55, 18, "img/brick_5.png"));
            /**
             * Powerup are spawned randomly behind the blocks
             * Will animate and fall when the block is destroyed
             */
            Random random = new Random();
            blocks.get(random.nextInt(32)).powerup = true;
            blocks.get(random.nextInt(32)).powerup = true;
            blocks.get(random.nextInt(32)).powerup = true;
            blocks.get(random.nextInt(32)).powerup = true;
            blocks.get(random.nextInt(32)).powerup = true;
            blocks.get(random.nextInt(32)).powerup = true;

            addKeyListener(this);
            setFocusable(true);
        }
         /**
          * When blocks are destroyed or any actions are performed, it will automatically update the current GUI
          * @param g 
          */
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Font f = new Font("Helvetica", Font.PLAIN, 18);
            g.setFont(f); 
            g.setColor(Color.yellow);
            g.drawImage(img, 0, 0, null);
            
            for (Block b : blocks) {
                b.draw(g, this);
            }
            
            for (Block b : ball) {
                b.draw(g, this);
     
            }
            
            for (Block p : powerup) {
                p.draw(g, this);
            }
            
            paddle.draw(g, this);
            
            if (score == 40)
                g.drawString(" You Win!", 280, 320);
            
            g.drawString("Score: ", 10, 530);
            g.drawString(Integer.toString(score), 70, 530);
        }
        /**
         * When the powerup touches the paddle, a new ball spawns from the paddle
         * acting as almost like the original ball
         */
        public void update() {
            
            
            
            for (Block p : powerup) {
                p.y += 1;
                if (p.intersects(paddle) && !p.destroyed) {
                    p.destroyed = true;
                    ball.add(new Block(paddle.x + 60, 437, 25, 25, "img/ball_new.png"));
                
                }
            }

            for (Block ba : ball) {
                
                if(ba.y > getHeight()) {
                 
                }
                
                ba.x += ba.dx;
                if (ba.x > (getWidth() - size) && ba.dx > 0 || ba.x < 0) {
                    ba.dx *= -1;
                }
                if (ba.y < 0 || ba.intersects(paddle)) {
                    ba.dy *= -1;
                }
                ba.y += ba.dy;
                for (Block b : blocks) {
                    if ((b.left.intersects(ba) || b.right.intersects(ba)) && !b.destroyed) {
                        score++;
                        ba.dx *= -1;
                        b.destroyed = true;
                        if (b.powerup) {
                            powerup.add(new Block(b.x, b.y, 25, 19, "img/ball_power.png"));
                        }

                    }
                    else if (ba.intersects(b) && !b.destroyed) {
                        score++;
                        b.destroyed = true;
                        ba.dy *= -1;
                        if (b.powerup) {
                            powerup.add(new Block(b.x, b.y, 25, 19, "img/ball_power.png"));
                        }
                    }
                }
            }
            repaint();
        }

        @Override
        public void keyTyped(KeyEvent ke) {

        }

        @Override
        /**
         * Actions performed when right and left keys are tapped
         * Move the paddle sideways to bounce back the ball
         * Enter to start the game
         */
        public void keyPressed(KeyEvent e) {
            
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                animate = new Animate(this);
                thread = new Thread(animate);
                thread.start();
            }
            if (e.getKeyCode() == KeyEvent.VK_LEFT && paddle.x > 0) {
                paddle.x -= 35;
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT && paddle.x < (getWidth() - paddle.width)) {
                paddle.x += 35;
            }
            if (e.getKeyCode() == KeyEvent.VK_P) {  
                thread.suspend();
              //JOptionPane.showMessageDialog(Keyevent e,"pause");
                
                
            }
            else {
                paused = false;
            }
        }

        @Override
        public void keyReleased(KeyEvent ke) {

        }
    }
    /**
     * Functions to activate the block panel to run and get updated to whatever happens
     */
    public class Animate implements Runnable {

        BlockPanel bp;

        Animate(BlockPanel b) {
            bp = b;
        }

        @Override
        public void run() {
            while (true) {
                bp.update();
                try {
                    Thread.sleep(12);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }

    }
    /**
     * Defines a block where it will be placed by the height and width of the panel
     */
    public class Block extends Rectangle {

        Image pic;
        int dx = 3;
        int dy = -3;
        Rectangle left, right;
        boolean destroyed = false;
        boolean powerup = false;
       

        Block(int a, int b, int w, int h, String s) {
            x = a;
            y = b;
            width = w;
            height = h;
            left = new Rectangle(a - 1, b, 1, h);
            right = new Rectangle(a + w + 1, b, 1, h);
            pic = Toolkit.getDefaultToolkit().getImage(s);
        }

        public void draw(Graphics g, Component c) {
            if (!destroyed) {
                g.drawImage(pic, x, y, width, height, c);
            }
        }
    }

    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }
        catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        /**
         * calls the Menu class in order to run the program
         */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu().setVisible(true);
            }
        });
    }

}
