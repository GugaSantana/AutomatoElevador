import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.AnnotatedArrayType;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class TelaElevador extends JFrame implements ActionListener {

	private JPanel pElevador;
	private JLabel lElevador;
	private JButton bt, bAndar[];
	private JLabel cont;
	private Timer tm = new Timer(100, this);
	Elevador elevador = new Elevador();
	TelaPainelElevador painelElevador;

	public TelaElevador() {
		super("Elevador");
		setLayout(new BorderLayout());

		ImageIcon imagem = new ImageIcon("C:/Users/Guga/Desktop/Elevador/predio(fechado).jpg");
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
		ImageIcon imagem = new ImageIcon(getClass().getResource(andar + "-semi.jpg"));
		lElevador.setIcon(imagem);

		Timer time = new Timer(250, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ImageIcon imagem = new ImageIcon(getClass().getResource("predio(fechado).jpg"));
				lElevador.setIcon(imagem);
			}
		});
		time.setRepeats(false);
		time.start();
	}

	// Animação Abre porta
	public void abrirPorta(String andar) {
		ImageIcon imagem = new ImageIcon(getClass().getResource(andar + "-semi.jpg"));
		lElevador.setIcon(imagem);

		Timer time = new Timer(250, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ImageIcon imagem = new ImageIcon(getClass().getResource(andar + "-aberto.jpg"));
				lElevador.setIcon(imagem);
			}
		});
		time.setRepeats(false);
		time.start();
	}

	// Animação abre porta terreo
	public void abrirPortaTerreo() {
		abrirPorta("terreo");
	}

	// Animação fecha porta terreo;
	public void fecharPortaTerreo() {
		fecharPorta("terreo");
	}

	// Animação abre porta 1 andar
	public void abrirPorta1Andar() {
		abrirPorta("1");
	}

	// Animação fecha porta 1 andar;
	public void fecharPorta1Andar() {
		fecharPorta("1");
	}

	// Animação abre porta 2 andar
	public void abrirPorta2Andar() {
		abrirPorta("2");
	}

	// Animação fecha porta 2 andar;
	public void fecharPorta2Andar() {
		fecharPorta("2");
	}

	// Animação abre porta 3 andar
	public void abrirPorta3Andar() {
		abrirPorta("3");
	}

	// Animação fecha porta 3 andar;
	public void fecharPorta3Andar() {
		fecharPorta("3");
	}

	// Animação abre porta 4 andar
	public void abrirPorta4Andar() {
		abrirPorta("4");
	}

	// Animação fecha porta 4 andar;
	public void fecharPorta4Andar() {
		fecharPorta("4");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == bt) {

		}

		for (int i = 0; i < 5; i++) {
			if (e.getSource() == bAndar[i]) {
				if (elevador.isDesativado()) {
					abrirPorta(String.valueOf(i));
					elevador.setAndarAtual(i);
					if (i == 4)
						elevador.setDirecao(0);
					else if (i == 0)
						elevador.setDirecao(1);

					JFrame fr = this;

					Timer timer = new Timer(800, new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub

							String andarAtual = String.valueOf(elevador.getAndarAtual());
							if (andarAtual.equals("0"))
								andarAtual = "T";
							TelaPainelElevador painelElevador = new TelaPainelElevador(fr, andarAtual, elevador);

							elevador.setAndares(painelElevador.elevador.getAndares());
							controleElevador();
						}
					});
					timer.setRepeats(false);
					timer.start();

				}
				if (!elevador.getAndares(i)) {
					bAndar[i].setBackground(Color.red);
					elevador.setAndares(i, true);
				}
			}
		}
	}

	public void controleElevador() {
		// fecha porta
		fecharPorta(String.valueOf(elevador.getAndarAtual()));
		// Desativar o botao do andar atual
		bAndar[elevador.getAndarAtual()].setBackground(null);
		elevador.setAndares(elevador.getAndarAtual(), false);

		proximoAndar();
	}

	public void proximoAndar() {
		// descendo
		if (elevador.getDirecao() == 0) {
			// verifica os andares abaixo de tem algum ativado
			boolean temAtivado = false;
			int qtdParadas = 0;
			for (int i = elevador.getAndarAtual() - 1; i >= 0; i--) {
				if (elevador.getAndares(i)) {
					temAtivado = true;
					qtdParadas++;
				}
			}

			if (temAtivado) {
				for (int i = elevador.getAndarAtual() - 1; i >= 0; i--) {

					if (elevador.getAndares(i)) {
						// Quando chegar no terreo mudar a direção para subindo
						if (i == 0)
							elevador.setDirecao(1);

						abrirPortaExibirPainel(i);
						return;
					}
				}
			} else {
				elevador.setDirecao(1);
				proximoAndar();
			}
		}
		// subindo
		else {
			// verifica os andares abaixo de tem algum ativado
			boolean temAtivado = false;
			int qtdParadas = 0;
			for (int i = elevador.getAndarAtual() + 1; i < 5; i++) {
				{
					if (elevador.getAndares(i)) {
						temAtivado = true;
						qtdParadas++;
					}
				}
			}

			if (temAtivado) {
				for (int i = elevador.getAndarAtual() + 1; i < 5; i++) {

					if (elevador.getAndares(i)) {
						// Quando chegar no terreo mudar a direção para subindo
						if (i == 4)
							elevador.setDirecao(0);

						abrirPortaExibirPainel(i);
						return;
					}
				}
			} else {
				elevador.setDirecao(0);
				proximoAndar();
			}

		}
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
				controleElevador();
			}
		});
		timer.setRepeats(false);
		timer.start();
	}

}
