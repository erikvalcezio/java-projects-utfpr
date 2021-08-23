package br.edu.utfpr.jarc.atividade03.view;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import br.edu.utfpr.jarc.atividade03.controller.ServidorController;
import br.edu.utfpr.jarc.atividade03.model.Pessoa;
import br.edu.utfpr.jarc.atividade03.socket.Cliente;
import br.edu.utfpr.jarc.atividade03.util.ValidadorCpf;
import br.edu.utfpr.jarc.atividade03.utils.SocketEnum;
import br.edu.utfpr.jarc.atividade03.utils.StatusServerEnum;

public class ClienteServidorFrame extends JFrame {
	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;

	private JLabel labelNome, labelCPF, labelStatusServidor, labelRetornoServidor;
	private JTextField textoNome, textoCPF, textStatus;
	private JButton botaoDesligar, botaoLimpar, botaoEnviar, botaoLigar;
	private JTable tabela;
	private DefaultTableModel modelo;

	private ServidorController servidorControl;
	private Cliente cliente;
	private Thread thread;

	public ClienteServidorFrame() {
		super("SOCKET - Validador de CPF");
		Container container = getContentPane();
		setLayout(null);

		labelNome = new JLabel("Nome");
		labelCPF = new JLabel("CPF");
		labelStatusServidor = new JLabel("Status do Servidor");
		labelRetornoServidor = new JLabel("Retorno do Servidor");

		labelNome.setBounds(10, 10, 280, 15);
		labelCPF.setBounds(10, 50, 280, 15);
		labelStatusServidor.setBounds(10, 90, 280, 15);
		labelRetornoServidor.setBounds(10, 170, 280, 15);

		labelNome.setForeground(Color.BLACK);
		labelCPF.setForeground(Color.BLACK);
		labelStatusServidor.setForeground(Color.BLACK);
		labelRetornoServidor.setForeground(Color.BLACK);

		container.add(labelNome);
		container.add(labelCPF);
		container.add(labelStatusServidor);
		container.add(labelRetornoServidor);

		textoNome = new JTextField();
		textoCPF = new JTextField();
		textStatus = new JTextField();

		textoNome.setBounds(10, 25, 340, 20);
		textoCPF.setBounds(10, 65, 340, 20);
		textStatus.setBounds(10, 105, 600, 20);

		textStatus.setEditable(false);
		textStatus.setForeground(Color.RED);

		container.add(textoNome);
		container.add(textoCPF);
		container.add(textStatus);
		textStatus.setText(StatusServerEnum.STATUS_OFF.getStatus());

		botaoLigar = new JButton("Ligar");
		botaoEnviar = new JButton("Enviar");
		botaoEnviar.setEnabled(false);

		botaoLigar.setBounds(10, 135, 100, 20);
		botaoEnviar.setBounds(120, 135, 100, 20);

		container.add(botaoLigar);
		container.add(botaoEnviar);

		tabela = new JTable();

		tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		modelo = (DefaultTableModel) tabela.getModel();

		modelo.addColumn("NOME CLIENTE");
		modelo.addColumn("CFF");
		modelo.addColumn("RESULTADO");

		tabela.setBounds(10, 185, 760, 300);
		container.add(tabela);

		botaoLimpar = new JButton("Limpar");
		botaoDesligar = new JButton("Desligar");

		botaoLimpar.setBounds(10, 500, 100, 20);
		botaoDesligar.setBounds(120, 500, 100, 20);
		botaoDesligar.setEnabled(false);
		

		container.add(botaoLimpar);
		container.add(botaoDesligar);

		setSize(800, 600);
		setVisible(true);
		setLocationRelativeTo(null);

		if (this.servidorControl == null) {
			servidorControl = new ServidorController();
		}

		botaoEnviar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				enviar();
			}
		});

		botaoLimpar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				limparTabela();
			}
		});

		botaoDesligar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				desligar();
				limparTabela();
			}
		});

		botaoLigar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ligar();
			}
		});
	}

	private void limparTabela() {
		modelo.setRowCount(0);
		modelo.getDataVector().clear();
	}
	
	/**
	 * thread.start();	Inicia o Servidor por methodo run() implicido usando recursos do lambda 
	 * na classe ControllerServidor
	 */
	private void ligar() {
		
		this.servidorControl.turnOnServer(true);

		this.thread = this.servidorControl.excutarThreadServidor();
		this.thread.start();		

		while (this.servidorControl.getStatusServidor() == null) {
			// awaits customer response SERVIDOR
		}

		if (this.servidorControl.getStatusServidor().equals(StatusServerEnum.STATUS_WAIT.getStatus())) {
			textStatus.setForeground(Color.GREEN);
			textStatus.setText(this.thread.getState() + " SERVIDOR " + this.servidorControl.getStatusServidor() + " "
					+ Calendar.getInstance().getTime());
		}
		if (this.servidorControl.getStatusServidor().equals(StatusServerEnum.STATUS_RUNNABLE.getStatus())) {
			textStatus.setForeground(Color.ORANGE);
			textStatus.setText(this.thread.getState() + " CLIENTE "  + this.servidorControl.getStatusServidor() + " "
					+ Calendar.getInstance().getTime());
		}

		//botaoDesligar.setEnabled(true); //em desenvolvimento
		botaoLigar.setEnabled(false);
		botaoEnviar.setEnabled(true);

	}

	private void addLine(String status, String validador, String string) {
		modelo.addRow(new Object[] { status, validador, string });

	}
	
	/**
	 * Semântica para Botao desligar em desenvolvimento, não está ativo na tela da API
	 */

	private void desligar() {
		if (this.servidorControl.getStatusServidor().equals(StatusServerEnum.STATUS_WAIT.getStatus())) {
			if (this.servidorControl == null) {

				JOptionPane.showMessageDialog(this, "Servidor Não está ligado!");
			}

			this.servidorControl.turnOnServer(false);
			this.textStatus.setText(StatusServerEnum.STATUS_OFF.getStatus());
			this.textStatus.setForeground(Color.RED);
			botaoDesligar.setEnabled(false);
			botaoLigar.setEnabled(true);
			botaoEnviar.setEnabled(false);
			JOptionPane.showMessageDialog(this, "Servidor Desligado!");
			this.thread.interrupt();

		} else {
			JOptionPane.showMessageDialog(this, "Aplicação Servidor ativa em outra execução!");
		}
	}
	
	/**
	 * Remover validação do CPF e colocar do lado Servidor
	 * nesse momento da matéria apenas apresenta comunicação por thread + socket com objeto
	 */

	private void enviar() {
		if (!textoNome.getText().equals("") && !textoCPF.getText().equals("")) {

			if (cliente == null) {
				cliente = new Cliente();
			}
			
		
			if (this.servidorControl.getStatusServidor().equals(StatusServerEnum.STATUS_RUNNABLE.getStatus())
					|| this.servidorControl.getStatusServidor().equals(StatusServerEnum.STATUS_WAIT.getStatus())) {

				Pessoa pessoa = cliente.comunicarServidor(SocketEnum.HOST1.getHost(),
						Integer.parseInt(SocketEnum.HOST1.getPort()), textoNome.getText().trim(), textoCPF.getText().trim());

				JOptionPane.showMessageDialog(this, "Dados enviados para servidor!");
				
				boolean result = new ValidadorCpf(pessoa.getCpf()).verificarCPF();
				
					addLine("NOME CLIENTE : " + pessoa.getNome(), "CFF: " + pessoa.getCpf(),
							result ? "RESULTADO SERVIDOR: VALIDO" : "RESULTADO: INVÁLIDO");
					
					this.limpar();
			
			} else {

				JOptionPane.showMessageDialog(this, "Atenção: Ligar o servidor!");
			}

		} else {
			JOptionPane.showMessageDialog(this, "Nome e CPF devem ser informados.");
		}
	}

	private void limpar() {
		this.textoNome.setText("");
		this.textoCPF.setText("");
	}
}
