
import javax.swing.JFrame;

public class HelpScreen extends javax.swing.JFrame {

    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JButton quitButton;

    public HelpScreen() {
        initComponents();
    }

    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        
        quitButton = new javax.swing.JButton();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(640, 640));
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Ebrima", 1, 22));
        jLabel1.setForeground(new java.awt.Color(204, 102, 0));
        jLabel1.setText("Instructions: ");
        jLabel1.setBounds(21, 15, 295, 100);
        getContentPane().add(jLabel1);

        jLabel2.setFont(new java.awt.Font("Gadugi", 1, 15));
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("1. Hit \" ENTER\" to lanch the ball");
        jLabel2.setBounds(21, 46, 500, 115);
        getContentPane().add(jLabel2);

        jLabel3.setFont(new java.awt.Font("Gadugi", 1, 15));
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("2. Use arrow keys to move the paddle");
        jLabel3.setBounds(21, 76, 500, 115);
        getContentPane().add(jLabel3);

        jLabel4.setFont(new java.awt.Font("Gadugi", 1, 15));
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("3. You have only have one life so be careful! :D");
        jLabel4.setBounds(21, 106, 500, 115);
        getContentPane().add(jLabel4);

        quitButton.setBackground(new java.awt.Color(0, 0, 0));
        quitButton.setFont(new java.awt.Font("Tahoma", 1, 14)); 
        quitButton.setForeground(new java.awt.Color(255, 255, 255));
        quitButton.setText("Back");
        quitButton.setBounds(280, 530, 80, 30);
        getContentPane().add(quitButton);

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("help_background.jpg")));
        jLabel5.setBounds(0, 0, 640, 640);
        getContentPane().add(jLabel5);

        quitButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                quitButtonMouseClicked(evt);
            }
        });
    }

    public void quitButtonMouseClicked(java.awt.event.MouseEvent evt) {
        setVisible(false);
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
            java.util.logging.Logger.getLogger(HelpScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HelpScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HelpScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HelpScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HelpScreen().setVisible(true);
            }
        });
    }
}
