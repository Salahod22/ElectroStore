
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Firstone extends JFrame implements ActionListener {
    private JPanel p, pbuttons, pimage,pb,pb1;
    private JButton b, b1;
    private JLabel limage, lwelcome;
    private ImageIcon image;

    public Firstone() {
    	
    	Color redback = new Color(230,36,1);
    	Color bgray = new Color(74,82,97);

        b = new JButton("<html><div style='text-align: center; font-size: 14px;'>I'm a Manager</div></html>");
        b1 = new JButton("<html><div style='text-align: center; font-size: 14px;'>I'm a Custmer</div></html>");
        b.setBackground(bgray); 
        b.setForeground(Color.WHITE); 
        b1.setBackground(bgray); 
        b1.setForeground(Color.WHITE); 
        b.setPreferredSize(new Dimension(400, 80)); 
        b1.setPreferredSize(new Dimension(400, 80)); 
        
        
        pb = new JPanel(new FlowLayout(FlowLayout.CENTER)); 
        pb.add(b);
        pb.setBackground(redback);
        
        b.addActionListener(this); 
        b1.addActionListener(this);
        
        
        pb1 = new JPanel();
        pb1.setLayout(new FlowLayout(FlowLayout.CENTER));
        pb1.add(b1);
        pb1.setBackground(redback); 

        image = new ImageIcon("C:\\Users\\John\\eclipse-workspace\\Project1\\src\\ele11.png");   
        limage = new JLabel(image);

        lwelcome = new JLabel("<html><div style='text-align: center;  font-size: 20px;'>Welcome to<br> Electroplanet</div></html>");
        lwelcome.setHorizontalAlignment(SwingConstants.CENTER); 
        lwelcome.setForeground(Color.BLACK); 
      
        pimage = new JPanel();  
        pimage.setLayout(new FlowLayout(FlowLayout.CENTER));
        pimage.add(limage);
        pimage.setBackground(redback); 

        pbuttons = new JPanel();
        pbuttons.setLayout(new FlowLayout(FlowLayout.CENTER,0,15));   
        pbuttons.setBackground(redback); 
        pbuttons.add(lwelcome);
        pbuttons.add(pb);
        pbuttons.add(pb1);

        p = new JPanel();
        p.setLayout(new BorderLayout()); 
        p.setBorder(BorderFactory.createEmptyBorder(5, 20, 30, 20)); 
        p.add(pimage, BorderLayout.NORTH); 
        p.add(pbuttons, BorderLayout.CENTER);
        p.setBackground(redback); 
        
        this.add(p);
        this.setVisible(true);
        this.setTitle("Dashboard");
        this.setSize(460, 490);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        Firstone f = new Firstone();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == b) {
        	Connexion co = new Connexion();
            this.setVisible(false);

        } else if (e.getSource() == b1) {
        	Customer c = new Customer(); 
        	c.setVisible(true);
            this.setVisible(false);
            
        	
        }
    }
}
