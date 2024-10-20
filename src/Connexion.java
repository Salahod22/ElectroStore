
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Connexion extends JFrame implements ActionListener {
    private JPanel p, ptfieldslabels , pbuttons, pimage;
    private JLabel limage, lenter, lusername, lpw;
    private JTextField tfuser;
    private JPasswordField tfpw;
    private JButton bcon, bback;
    private ImageIcon image;

    public Connexion() {
        
        Color redback = new Color(230, 36, 1);
        Color bgray = new Color(74, 82, 97);

        lenter = new JLabel("Enter your info");
        lenter.setHorizontalAlignment(SwingConstants.CENTER); 
        lenter.setForeground(Color.BLACK);
        
        lusername = new JLabel("<html><div style='text-align: center; font-size: 14px;'>Username :</div></html>");
        lusername.setForeground(Color.WHITE);
        lpw = new JLabel("<html><div style='text-align: center; font-size: 14px;'>Password :</div></html>");
        lpw.setForeground(Color.WHITE);

        tfuser = new JTextField();
        tfpw = new JPasswordField();
        bcon = new JButton("Login");
        bback = new JButton("Back");
        tfuser.setCaretColor(redback);
        
        bcon.setBackground(bgray);
        bcon.setForeground(Color.WHITE);
        bcon.setPreferredSize(new Dimension(400, 50));
        bback.setBackground(bgray);
        bback.setForeground(Color.WHITE);
        bback.setPreferredSize(new Dimension(400, 50));

        bcon.addActionListener(this);
        bback.addActionListener(this);

        ptfieldslabels = new JPanel();
        ptfieldslabels.setLayout(new GridLayout(2, 2, 10, 10));

        ptfieldslabels.add(lusername); 
        ptfieldslabels.add(tfuser);
        ptfieldslabels.add(lpw);
        ptfieldslabels.add(tfpw);
        ptfieldslabels.setBorder(BorderFactory.createEmptyBorder(10, 50, 15, 50));

        ptfieldslabels.setBackground(redback);

        pbuttons = new JPanel();
        pbuttons.setLayout(new GridLayout(2, 1, 10, 10));
        pbuttons.add(bcon);
        pbuttons.add(bback);
        pbuttons.setBackground(redback);

        image = new ImageIcon("C:\\\\Users\\\\John\\\\eclipse-workspace\\\\Project1\\\\src\\ele11.png");
        limage = new JLabel(image);
        pimage = new JPanel();
        pimage.setLayout(new FlowLayout(FlowLayout.CENTER));
        pimage.add(limage);
        pimage.setBackground(redback);

        p = new JPanel();
        p.setLayout(new BorderLayout());
        p.add(pimage, BorderLayout.NORTH);
        p.add(ptfieldslabels, BorderLayout.CENTER);
        p.add(pbuttons, BorderLayout.SOUTH);
        p.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        p.setBackground(redback);

        this.add(p);

        this.setVisible(true);
        this.setTitle("Connection");
        this.setSize(460, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        Connexion co = new Connexion();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
  
        if (e.getSource() == bcon) {
            String username = tfuser.getText().trim();
            String password = new String(tfpw.getPassword()).trim();
            
            if (username.equals("admin") && password.equals("123456")) {
         
                JOptionPane.showMessageDialog(this, "Login successful. Welcome, Manager!");
                
                Manager manager = new Manager();
                this.dispose(); 
            } else {
               
                JOptionPane.showMessageDialog(this, "Invalid username or password. Please retry.");
            }
        } else if (e.getSource() == bback) {
            Firstone f = new Firstone();
            this.dispose(); 
        }
    }
}

