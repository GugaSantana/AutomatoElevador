import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
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
	private JButton bt, bAndar[];
	private JLabel cont;
	private Timer tm = new Timer(100, this);
	Elevador elevador = new Elevador();
	TelaPainelElevador painelElevador;
	private boolean tudoFechado = true;

	public TelaElevador() {
		super("Elevador");
		setLayout(new BorderLayout());

		ImageIcon imagem = new ImageIcon(getClass().getResource("predio(fechado).jpg"));
		lElevador = new JLabel("");
		lElevador.setIcon(imagem);

		pElevador = new JPanel(new GridBagLayout());

		// instancia dos botoes elevador
		bAndar = new JButton[5];

		for (int i = 0; i < 5; i++) {
			bAndar[i] = new JButton("O");
			bAndar[i].addActionListener(this);
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

		bt = new JButton("Cont");
		bt.addActionListener(this);

		add(bt, BorderLayout.WEST);
		add(pElevador, BorderLayout.CENTER);

		cont = new JLabel("Contador: ");
		Font fonteTitulo = new Font("Arial", Font.BOLD, 20);
		cont.setFont(fonteTitulo);
		cont.setAlignmentX(CENTER_ALIGNMENT);
		cont.setHorizontalAlignment(SwingConstants.CENTER);
		cont.setOpaque(true);
		cont.setBackground(Color.WHITE);
		add(cont, BorderLayout.NORTH);

		setVisible(true);
		setSize(800, 500);
		setLocationRelativeTo(null);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
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
				audio.play();
			}
		}).start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == bt) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					for (int i = 0; i < 20; i++) {
						cont.setText("Contador: " + i);
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}).start();
		}

		for (int i = 0; i < 5; i++) {
			if (e.getSource() == bAndar[i]) {
				elevador.setAndares(i, true);
				controleElevador();
				bAndar[i].setBackground(Color.red);
			}
		}
	}

	public void controleElevador() {
		if (!tudoFechado) {
			// fecha porta
			fecharPorta(String.valueOf(elevador.getAndarAtual()));
		} // Desativar o botao do andar atual

		bAndar[elevador.getAndarAtual()].setBackground(null);
		elevador.setAndares(elevador.getAndarAtual(), false);

		proximoAndar();

	}

	public void descerAndar() {
		// Descendo
		new Thread(new Runnable() {
			public void run() {
				for (int i = elevador.getAndarAtual() - 1; i >= 0; i--) {

					try {
						Thread.sleep(1000);
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
						Thread.sleep(1000);
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

				//Verificações
				if(temAtivadoDescendo && !temAtivadoSubindo)
				{
					elevador.setDirecao(0);
					descerAndar();										
				}
				else if(!temAtivadoDescendo && temAtivadoSubindo)
				{
					elevador.setDirecao(1);
					subirAndar();
				}
				else if(temAtivadoDescendo && temAtivadoSubindo)
				{
					if(elevador.getDirecao() == 0)
					{
						descerAndar();
					}
					else
					{
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
		Timer timer = new Timer(800, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				String andarAtual = String.valueOf(elevador.getAndarAtual());
				if (andarAtual.equals("0"))
					andarAtual = "T";

				TelaPainelElevador painelElevador = new TelaPainelElevador(fr, andarAtual, elevador);

				elevador.setAndares(painelElevador.elevador.getAndares());

				if (!elevador.isDesativado())
					controleElevador();
			}
		});
		timer.setRepeats(false);
		timer.start();
	}

}
