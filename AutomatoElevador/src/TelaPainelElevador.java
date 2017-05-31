import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class TelaPainelElevador extends JDialog implements ActionListener {

	private JButton bAndar[], bFecharPorta;
	private ImageIcon img[], imgFechar;
	private JPanel pPainelElevador;
	private JLabel lAndarAtual;
	Elevador elevador = new Elevador();

	URL urlSomGato= getClass().getResource("miau.wav");
	AudioClip audioGato= Applet.newAudioClip(urlSomGato);
	
	public TelaPainelElevador(JFrame fr, String andarAtual, Elevador elevadorAtivo) {
		super(fr, true);
		setTitle("Painel Elevador");
		setLayout(new BorderLayout());
		
		pPainelElevador = new JPanel(new GridBagLayout());
		pPainelElevador.setBackground(new Color(15, 47, 96));
		
		
		// instancia dos botoes elevador
		bAndar = new JButton[5];
		img = new ImageIcon[5];
		
		for (int i = 0; i < 5; i++) {
			img[i] = new ImageIcon(getClass().getResource(i + ".png"));				
			bAndar[i] = new JButton(img[i]);
			bAndar[i].addActionListener(this);

			// Pega as configurações ja existentes dentro do elevado
			// e repassa para essa tela
			if (elevadorAtivo.getAndares(i) == true && elevadorAtivo.getAndarAtual() != i) {
//				bAndar[i].setBackground(Color.red);
				img[i] = new ImageIcon(getClass().getResource(i + "a.png"));									
				bAndar[i].setIcon(img[i]);
				
				elevador.setAndares(i, true);
			}
		}

		imgFechar = new ImageIcon(getClass().getResource("fechar.png"));	
		bFecharPorta = new JButton(imgFechar);
		bFecharPorta.addActionListener(this);

		lAndarAtual = new JLabel(andarAtual);
		Font fonteTitulo = new Font("Arial", Font.BOLD, 20);
		lAndarAtual.setFont(fonteTitulo);
		lAndarAtual.setAlignmentX(CENTER_ALIGNMENT);
		lAndarAtual.setHorizontalAlignment(SwingConstants.CENTER);
		lAndarAtual.setOpaque(true);
		lAndarAtual.setBackground(Color.WHITE);

		// formatacao do formulario
		GridBagConstraints gBC = new GridBagConstraints();
		gBC.fill = GridBagConstraints.HORIZONTAL;
		gBC.anchor = GridBagConstraints.NORTHWEST;

		gBC.gridx = 0;
		gBC.gridy = 0;
		gBC.insets = new Insets(5, 5, 20, 5);
		pPainelElevador.add(lAndarAtual, gBC);

		gBC.gridx = 0;
		gBC.gridy = 1;
		gBC.insets = new Insets(5, 5, 5, 5);
		pPainelElevador.add(bAndar[4], gBC);

		gBC.gridx = 0;
		gBC.gridy = 2;
		gBC.insets = new Insets(5, 5, 5, 5);
		pPainelElevador.add(bAndar[3], gBC);

		gBC.gridx = 0;
		gBC.gridy = 3;
		gBC.insets = new Insets(5, 5, 5, 5);
		pPainelElevador.add(bAndar[2], gBC);

		gBC.gridx = 0;
		gBC.gridy = 4;
		gBC.insets = new Insets(5, 5, 5, 5);
		pPainelElevador.add(bAndar[1], gBC);

		gBC.gridx = 0;
		gBC.gridy = 5;
		gBC.insets = new Insets(5, 5, 5, 5);
		pPainelElevador.add(bAndar[0], gBC);

		gBC.gridx = 0;
		gBC.gridy = 6;
		gBC.insets = new Insets(20, 5, 5, 5);
		pPainelElevador.add(bFecharPorta, gBC);

		add(pPainelElevador, BorderLayout.CENTER);
		
		Toolkit kit = Toolkit.getDefaultToolkit();
		Image image = kit.createImage(getClass().getResource("nian.png"));
		Point point = new Point(1, 1); //
		// Coordenada do clique em relação à imagem
		String nameCursor = "Image Cursor";
		Cursor cursor = kit.createCustomCursor(image, point, nameCursor);
		setCursor(cursor);
		
		setSize(200, 550);
		setLocation(380, 100);
		//setLocationRelativeTo(null);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		for (int i = 0; i < 5; i++) {
			if (e.getSource() == bAndar[i]) {
				int andarAtual;
				try {
					andarAtual = Integer.parseInt(lAndarAtual.getText());
				} catch (Exception ex) {
					andarAtual = 0;
				}

				if (i != andarAtual) {
					if (elevador.getAndares(i) == false) {
						audioGato.play();
						//bAndar[i].setBackground(Color.red);
						img[i] = new ImageIcon(getClass().getResource(i + "a.png"));									
						bAndar[i].setIcon(img[i]);
						
						elevador.setAndares(i, true);
					}

				}
			}
		}

		if (e.getSource() == bFecharPorta) {
			boolean sequencia[] = elevador.getAndares();
			String texto = "";
			for (int i = 0; i < sequencia.length; i++) {
				texto += i + " - " + sequencia[i] + "\n";
			}
			
			this.dispose();			
		}
	}
}
