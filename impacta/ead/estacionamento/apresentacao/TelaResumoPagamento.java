package impacta.ead.estacionamento.apresentacao;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import impacta.ead.estacionamento.negocio.Movimentacao;

public class TelaResumoPagamento extends JFrame implements ActionListener {
	
	private JFrame parent;

	public TelaResumoPagamento(Movimentacao movimentacao, JFrame parent) {
		this.parent = parent;
		getContentPane().setFont(new Font("Dialog", Font.BOLD, 14));
		setResizable(false);
		setTitle("Resumo de Pagamento");
		getContentPane().setLayout(null);
		
		JLabel lblPlaca = new JLabel("Placa:");
		lblPlaca.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPlaca.setBounds(112, 43, 45, 13);
		getContentPane().add(lblPlaca);
		
		JLabel lblDataEntrada = new JLabel("Entrada:");
		lblDataEntrada.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDataEntrada.setBounds(97, 84, 60, 13);
		getContentPane().add(lblDataEntrada);
		
		JLabel lblDataSaida = new JLabel("Saída");
		lblDataSaida.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDataSaida.setBounds(112, 129, 45, 13);
		getContentPane().add(lblDataSaida);
		
		JLabel lblValor = new JLabel("Valor:");
		lblValor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblValor.setBounds(112, 175, 45, 13);
		getContentPane().add(lblValor);
		
		JLabel lblValPlaca = new JLabel("[Placa]");
		lblValPlaca.setFont(new Font("Dialog", Font.BOLD, 14));
		lblValPlaca.setBounds(250, 43, 73, 13);
		getContentPane().add(lblValPlaca);
		
		JLabel lblValDataEntrada = new JLabel("[DataEntrada]");
		lblValDataEntrada.setFont(new Font("Dialog", Font.BOLD, 14));
		lblValDataEntrada.setBounds(250, 84, 152, 13);
		getContentPane().add(lblValDataEntrada);
		
		JLabel lblValDataSaida = new JLabel("[DataSaída]");
		lblValDataSaida.setFont(new Font("Dialog", Font.BOLD, 14));
		lblValDataSaida.setBounds(250, 129, 136, 13);
		getContentPane().add(lblValDataSaida);
		
		JLabel lblValValor = new JLabel("[Valor]");
		lblValValor.setFont(new Font("Dialog", Font.BOLD, 14));
		lblValValor.setBounds(250, 175, 101, 13);
		getContentPane().add(lblValValor);
		
		JButton btnOk = new JButton("Ok");
		btnOk.addActionListener(this);
		btnOk.setBounds(162, 216, 85, 21);
		getContentPane().add(btnOk);

		setLocationRelativeTo(null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		parent.setVisible(true);
		dispose();
		
	}
}
