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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class TelaElevador extends JFrame implements ActionListener {

	private JPanel pElevador;
	private JLabel lElevador;
	private JButton bAndar[];
	private JLabel cont;
	private Timer tm = new Timer(100, this);
	Elevador elevador = new Elevador();
	TelaPainelElevador painelElevador;
	private boolean tudoFechado = false;

	URL urlSomElevador = getClass().getResource("somelevador.wav");
	AudioClip audioElevador = Applet.newAudioClip(urlSomElevador);

	URL urlSomGato = getClass().getResource("miau.wav");
	AudioClip audioGato = Applet.newAudioClip(urlSomGato);

	public TelaElevador() {
		super("Elevador");
		setLayout(new BorderLayout());

		ImageIcon imagem = new ImageIcon(getClass().getResource("0-aberto.jpg"));
		lElevador = new JLabel("");
		lElevador.setIcon(imagem);

		pElevador = new JPanel(new GridBagLayout());

		// instancia dos botoes elevador
		bAndar = new JButton[5];

		for (int i = 0; i < 5; i++) {
			bAndar[i] = new JButton("O");
			bAndar[i].addActionListener(this);
			bAndar[i].setBackground(Color.GRAY);
		}

		// formatacao do formulario
		GridBagConstraints gBC = new GridBagConstraints();
		gBC.fill = GridBagConstraints.HORIZONTAL;
		gBC.anchor = GridBagConstraints.NORTHWEST;

		gBC.gridx = 0;
		gBC.gridy = 0;
		gBC.insets = new Insets(50, 5, 50, 5);
		pElevador.add(bAndar[4], gBC);

		gBC.gridx = 0;
		gBC.gridy = 1;
		gBC.insets = new Insets(50, 5, 50, 5);
		pElevador.add(bAndar[3], gBC);

		gBC.fill = gBC.gridx = 0;
		gBC.gridy = 2;
		gBC.insets = new Insets(50, 5, 50, 5);
		pElevador.add(bAndar[2], gBC);

		gBC.gridx = 0;
		gBC.gridy = 3;
		gBC.insets = new Insets(50, 5, 50, 5);
		pElevador.add(bAndar[1], gBC);

		gBC.gridx = 0;
		gBC.gridy = 4;
		gBC.insets = new Insets(50, 5, 50, 5);
		pElevador.add(bAndar[0], gBC);

		gBC.gridx = 1;
		gBC.gridy = 0;
		gBC.gridheight = 5;
		gBC.insets = new Insets(5, 5, 5, 5);
		pElevador.add(lElevador, gBC);

		add(pElevador, BorderLayout.CENTER);

		cont = new JLabel("ANDAR: T");
		Font fonteTitulo = new Font("Arial", Font.BOLD, 20);
		cont.setFont(fonteTitulo);
		cont.setAlignmentX(CENTER_ALIGNMENT);
		cont.setHorizontalAlignment(SwingConstants.CENTER);
		cont.setOpaque(true);
		add(cont, BorderLayout.NORTH);

		pElevador.setBackground(new Color(15, 47, 96));

		setVisible(true);
		setSize(800, 500);
		setLocationRelativeTo(null);
		setExtendedState(JFrame.MAXIMIZED_BOTH);

		Toolkit kit = Toolkit.getDefaultToolkit();
		Image image = kit.createImage(getClass().getResource("nian.png"));
		Point point = new Point(1, 1); //
		// Coordenada do clique em relação à imagem
		String nameCursor = "Image Cursor";
		Cursor cursor = kit.createCustomCursor(image, point, nameCursor);
		setCursor(cursor);
	}

	public static void main(String[] args) {
		new TelaElevador();
	}

	// Todas as portas do elevador ficam fechadas
	public void fecharPorta(String andar) {
		new Thread(new Runnable() {
			public void run() {
				tudoFechado = true;
				ImageIcon imagem = new ImageIcon(getClass().getResource(andar + "-semi.jpg"));
				lElevador.setIcon(imagem);

				try {
					Thread.sleep(250);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				imagem = new ImageIcon(getClass().getResource("predio(fechado).jpg"));
				lElevador.setIcon(imagem);
			}
		}).start();
	}

	// Animação Abre porta
	public void abrirPorta(String andar) {
		new Thread(new Runnable() {
			public void run() {
				tudoFechado = false;
				ImageIcon imagem = new ImageIcon(getClass().getResource(andar + "-semi.jpg"));
				lElevador.setIcon(imagem);

				try {
					Thread.sleep(250);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				imagem = new ImageIcon(getClass().getResource(andar + "-aberto.jpg"));
				lElevador.setIcon(imagem);

				// Audio
				URL url = getClass().getResource("clarinete.wav");
				AudioClip audio = Applet.newAudioClip(url);
				audioElevador.stop();
				audio.play();

			}
		}).start();

		new Thread(new Runnable() {
			public void run() {

				try {
					Thread.sleep(300);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// Nyan cat saindo
				Gato gato = new Gato();
				
				int x = 0;
				int y = 0;
						
				
				if(andar.equals("0"))
				{
					x = 760;
					y = 560;
				}
				
				if(andar.equals("1"))
				{
					x = 760;
					y = 430;
				}
				
				if(andar.equals("2"))
				{
					x = 760;
					y = 310;
				}
				
				if(andar.equals("3"))
				{
					x = 760;
					y = 200;
				}
				
				if(andar.equals("4"))
				{
					x = 760;
					y = 70;
				}
				
				gato.setLocation(760, 450);
				
				for (int i = 0; i < 150; i++) {
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					x += 5;

					gato.setLocation(x, y);

				}
				gato.dispose();
			}
		}).start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		for (int i = 0; i < 5; i++) {
			if (e.getSource() == bAndar[i]) {
				if (elevador.getAndarAtual() != i) {
					elevador.setAndares(i, true);
					controleElevador();
					bAndar[i].setBackground(Color.red);
					audioGato.play();
					bloquearBotoes();
				}
			}
		}
	}

	public void controleElevador() {
		if (!tudoFechado) {
			// fecha porta
			fecharPorta(String.valueOf(elevador.getAndarAtual()));
		} // Desativar o botao do andar atual

		bAndar[elevador.getAndarAtual()].setBackground(Color.GRAY);
		elevador.setAndares(elevador.getAndarAtual(), false);

		proximoAndar();

	}

	public void descerAndar() {
		// Descendo
		new Thread(new Runnable() {
			public void run() {
				for (int i = elevador.getAndarAtual() - 1; i >= 0; i--) {

					try {
						Thread.sleep(1500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					String andar = "" + i;
					if (i == 0)
						andar = "T";

					cont.setText("ANDAR: " + andar);

					if (elevador.getAndares(i)) {
						// Quando chegar no terreo mudar a direção para
						// subindo
						if (i == 0)
							elevador.setDirecao(1);

						abrirPortaExibirPainel(i);
						return;
					}

				}
			}
		}).start();
	}

	public void subirAndar() {
		new Thread(new Runnable() {
			public void run() {

				for (int i = elevador.getAndarAtual() + 1; i < 5; i++) {

					try {
						Thread.sleep(1500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					String andar = "" + i;
					if (i == 0)
						andar = "T";

					cont.setText("ANDAR: " + andar);

					if (elevador.getAndares(i)) {
						// Quando chegar no terreo mudar a direção para
						// subindo
						if (i == 4)
							elevador.setDirecao(0);

						abrirPortaExibirPainel(i);
						return;
					}

				}
			}
		}).start();
	}

	public void proximoAndar() {
		new Thread(new Runnable() {
			public void run() {

				audioElevador.play();

				// descendo
				// verifica os andares abaixo de tem algum ativado
				boolean temAtivadoDescendo = false;
				for (int i = elevador.getAndarAtual() - 1; i >= 0; i--) {
					if (elevador.getAndares(i)) {
						temAtivadoDescendo = true;
					}
				}

				// subindo
				// verifica os andares acima de tem algum ativado
				boolean temAtivadoSubindo = false;
				for (int i = elevador.getAndarAtual() + 1; i < 5; i++) {
					{
						if (elevador.getAndares(i)) {
							temAtivadoSubindo = true;
						}
					}
				}

				// Verificações
				if (temAtivadoDescendo && !temAtivadoSubindo) {
					elevador.setDirecao(0);
					descerAndar();
				} else if (!temAtivadoDescendo && temAtivadoSubindo) {
					elevador.setDirecao(1);
					subirAndar();
				} else if (temAtivadoDescendo && temAtivadoSubindo) {
					if (elevador.getDirecao() == 0) {
						descerAndar();
					} else {
						subirAndar();

					}
				}

			}
		}).start();
	}

	public void abrirPortaExibirPainel(int i) {
		// abrir porta
		abrirPorta(String.valueOf(i));

		// Verificar se alguem apertou o botão externo
		// Abre painel para pessoa que entrou
		JFrame fr = this;
		elevador.setAndarAtual(i);
		Timer timer = new Timer(2000, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				String andarAtual = String.valueOf(elevador.getAndarAtual());
				if (andarAtual.equals("0"))
					andarAtual = "T";

				TelaPainelElevador painelElevador = new TelaPainelElevador(fr, andarAtual, elevador);
				desbloquearBotoes();
				elevador.setAndares(painelElevador.elevador.getAndares());

				if (!elevador.isDesativado())
					controleElevador();
			}
		});
		timer.setRepeats(false);
		timer.start();
	}

	public void bloquearBotoes() {
		for (int i = 0; i < 5; i++) {
			bAndar[i].setEnabled(false);
		}
	}

	public void desbloquearBotoes() {
		for (int i = 0; i < 5; i++) {
			bAndar[i].setEnabled(true);
		}
	}

}
