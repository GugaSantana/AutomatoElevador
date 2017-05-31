import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class TelaPainelElevador extends JDialog implements ActionListener {

	private JButton bAndar[], bFecharPorta;
	private JPanel pPainelElevador;
	private JLabel lAndarAtual;
	ListaChamadas lista = new ListaChamadas();
	Elevador elevador = new Elevador();

	public TelaPainelElevador(JFrame fr, String andarAtual, Elevador elevadorAtivo) {
		super(fr, true);
		setTitle("Painel Elevador");
		setLayout(new BorderLayout());

		pPainelElevador = new JPanel(new GridBagLayout());

		// instancia dos botoes elevador
		bAndar = new JButton[5];

		for (int i = 0; i < 5; i++) {
			bAndar[i] = new JButton("" + i);
			bAndar[i].addActionListener(this);

			// Pega as configurações ja existentes dentro do elevado
			// e repassa para essa tela
			if (elevadorAtivo.getAndares(i) == true && elevadorAtivo.getAndarAtual() != i) {
				bAndar[i].setBackground(Color.red);
				lista.add(i);
				elevador.setAndares(i, true);
			}
		}

		bFecharPorta = new JButton("> <");
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
		

		setSize(200, 400);
		setLocation(400, 200);
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
						bAndar[i].setBackground(Color.red);
						lista.add(i);
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
