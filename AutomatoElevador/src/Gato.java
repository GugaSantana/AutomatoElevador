import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Gato extends JFrame{
	
	public Gato()
	{
		super("Gato");
		ImageIcon img = new ImageIcon(getClass().getResource("nian.gif"));
		img.setImage(img.getImage().getScaledInstance(200, 200, 100));
		JLabel nyan = new JLabel(img);
		
		add(nyan);
		
		getContentPane().setBackground(new Color(15, 47, 96));
		
		setUndecorated(true);
		//setLocationRelativeTo(null);
		setVisible(true);
		setSize(150, 150);
		setLocation(380, 100);
		}
	
	 
	public static void main(String [] args)
	{
		new Gato();
	}
	
}
