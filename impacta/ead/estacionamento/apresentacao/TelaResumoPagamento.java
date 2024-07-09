package impacta.ead.estacionamento.apresentacao;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import impacta.ead.estacionamento.negocio.Movimentacao;
import impacta.ead.estacionamento.utilitario.EstacionamentoUtil;

public class TelaResumoPagamento extends JFrame implements ActionListener {
	
	private JFrame parent;

	public TelaResumoPagamento(Movimentacao movimentacao, JFrame parent) {
		this.parent = parent;
		getContentPane().setFont(new Font("Dialog", Font.BOLD, 14));
		setSize(new Dimension(420, 300));
		setResizable(false);
		setTitle("Resumo de Pagamento");
		getContentPane().setLayout(null);
		
		JLabel lblPlaca = new JLabel("Placa");
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
		
		String sPlaca = movimentacao.getVeiculo().getPlaca();
		JLabel lblValPlaca = new JLabel(sPlaca);
		lblValPlaca.setFont(new Font("Dialog", Font.BOLD, 14));
		lblValPlaca.setBounds(250, 43, 73, 13);
		getContentPane().add(lblValPlaca);
		
		String sEntrada = EstacionamentoUtil.getDisplayData(movimentacao.getDataHoraEntrada());
		JLabel lblValDataEntrada = new JLabel(sEntrada);
		lblValDataEntrada.setFont(new Font("Dialog", Font.BOLD, 14));
		lblValDataEntrada.setBounds(250, 84, 152, 13);
		getContentPane().add(lblValDataEntrada);
		
		String sSaida = EstacionamentoUtil.getDisplayData(movimentacao.getDataHoraSaida());
		JLabel lblValDataSaida = new JLabel(sSaida);
		lblValDataSaida.setFont(new Font("Dialog", Font.BOLD, 14));
		lblValDataSaida.setBounds(250, 129, 136, 13);
		getContentPane().add(lblValDataSaida);
		
		String sValor = "R$ " + movimentacao.getValor();
		JLabel lblValValor = new JLabel(sValor);
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
