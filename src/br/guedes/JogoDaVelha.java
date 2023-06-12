package br.guedes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class JogoDaVelha extends JFrame {

	private JButton[] botoes = new JButton[9];
	private boolean jogador1Ativo = true;
	private int jogadas = 0;

	public JogoDaVelha() {
		// configurando layout e tamanho da janela
		setTitle("Jogo da Velha");
		setSize(300, 300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new GridLayout(3, 3));

		// adicionando os botões ao layout
		for (int i = 0; i < 9; i++) {
			botoes[i] = new JButton();
			botoes[i].setFont(new Font("Arial", Font.PLAIN, 40));
			botoes[i].addActionListener(new BotaoListener());
			add(botoes[i]);
		}

		setVisible(true);
	}

	private class BotaoListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JButton botaoClicado = (JButton) e.getSource();
			if (botaoClicado.getText().equals("")) {
				if (jogador1Ativo) {
					botaoClicado.setText("X");
				} else {
					botaoClicado.setText("O");
				}
				jogador1Ativo = !jogador1Ativo;
				jogadas++;
				verificarVencedor();
			}
		}
	}

	private void verificarVencedor() {
		// verificando vencedor nas linhas
		for (int i = 0; i < 9; i += 3) {
			if (!botoes[i].getText().equals("") && botoes[i].getText().equals(botoes[i + 1].getText())
					&& botoes[i].getText().equals(botoes[i + 2].getText())) {
				JOptionPane.showMessageDialog(null, botoes[i].getText() + " venceu!");
				limparTabuleiro();
				return;
			}
		}

		// verificando vencedor nas colunas
		for (int i = 0; i < 3; i++) {
			if (!botoes[i].getText().equals("") && botoes[i].getText().equals(botoes[i + 3].getText())
					&& botoes[i].getText().equals(botoes[i + 6].getText())) {
				JOptionPane.showMessageDialog(null, botoes[i].getText() + " venceu!");
				limparTabuleiro();
				return;
			}
		}

		// verificando vencedornas diagonais
		if (!botoes[0].getText().equals("") && botoes[0].getText().equals(botoes[4].getText())
				&& botoes[0].getText().equals(botoes[8].getText())) {
			JOptionPane.showMessageDialog(null, botoes[0].getText() + " venceu!");
			limparTabuleiro();
			return;
		}

		if (!botoes[2].getText().equals("") && botoes[2].getText().equals(botoes[4].getText())
				&& botoes[2].getText().equals(botoes[6].getText())) {
			JOptionPane.showMessageDialog(null, botoes[2].getText() + " venceu!");
			limparTabuleiro();
			return;
		}

		// verificando empate
		if (jogadas == 9) {
			JOptionPane.showMessageDialog(null, "Empate!");
			limparTabuleiro();
			return;
		}
	}

	private void limparTabuleiro() {
		for (int i = 0; i < 9; i++) {
			botoes[i].setText("");
		}
		jogador1Ativo = true;
		jogadas = 0;
	}

	public static void main(String[] args) {
		new JogoDaVelha();
	}
}
