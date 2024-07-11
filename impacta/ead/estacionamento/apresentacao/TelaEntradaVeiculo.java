package impacta.ead.estacionamento.apresentacao;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import impacta.ead.estacionamento.controle.EstacionamentoController;
import impacta.ead.estacionamento.controle.EstacionamentoException;
import impacta.ead.estacionamento.controle.veiculoException;

public class TelaEntradaVeiculo extends JFrame implements ActionListener{
	
	private JFrame parent;
	private JTextField txtMarca;
	private JTextField txtModelo;
	private JTextField txtCor;
	private JFormattedTextField txfPlaca;
	private JButton btnOk;
	private JButton btnCancel;
	
	
	

	public TelaEntradaVeiculo(JFrame parent) {
		setResizable(false);
		setSize(400,300);
		setTitle("Entrada de veículo");
		// TODO Auto-generated constructor stub
		this.parent = parent;
		getContentPane().setLayout(null);
		
		JLabel lblPlaca = new JLabel("Placa:");
		lblPlaca.setBounds(103, 56, 45, 13);
		getContentPane().add(lblPlaca);
		
		JLabel lblMarca = new JLabel("Marca:");
		lblMarca.setBounds(103, 98, 45, 13);
		getContentPane().add(lblMarca);
		
		JLabel lblModelo = new JLabel("Modelo:");
		lblModelo.setBounds(103, 146, 45, 13);
		getContentPane().add(lblModelo);
		
		JLabel lblCor = new JLabel("Cor:");
		lblCor.setBounds(103, 193, 45, 13);
		getContentPane().add(lblCor);
		
		txtMarca = new JTextField();
		txtMarca.setBounds(200, 95, 96, 19);
		getContentPane().add(txtMarca);
		txtMarca.setColumns(10);
		
		txtModelo = new JTextField();
		txtModelo.setBounds(200, 143, 96, 19);
		getContentPane().add(txtModelo);
		txtModelo.setColumns(10);
		
		txtCor = new JTextField();
		txtCor.setBounds(200, 190, 96, 19);
		getContentPane().add(txtCor);
		txtCor.setColumns(10);
		
		btnOk = new JButton("Ok");
		btnOk.setBounds(103, 232, 85, 21);
		btnOk.addActionListener(this);
		btnOk.setActionCommand("ok");
		getContentPane().add(btnOk);
		
		btnCancel = new JButton("Cancelar");
		btnCancel.setBounds(211, 232, 85, 21);
		btnCancel.addActionListener(this);
		btnCancel.setActionCommand("cancel");
		getContentPane().add(btnCancel);
		
		try {
			txfPlaca = new JFormattedTextField(new MaskFormatter("UUU-####"));
		} catch (ParseException e) {
			assert false : "Padrão de placa errado!";
		}
		txfPlaca.setBounds(200, 56, 96, 19);
		getContentPane().add(txfPlaca);
		
		setLocationRelativeTo(null);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("ok")) {
			EstacionamentoController controle = new EstacionamentoController();
			try {
				controle.processarEntrada(txfPlaca.getText(), 
										  txtMarca.getText(), 
										  txtModelo.getText(), 
										  txtCor.getText());
				JOptionPane.showMessageDialog(null, "Veiculo registrado com sucesso!", 
						"Entrada de veiculo!", JOptionPane.INFORMATION_MESSAGE);
			} catch (EstacionamentoException | veiculoException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), 
						"Falha na entrada!", JOptionPane.ERROR_MESSAGE);
			}
			this.parent.setVisible(true);
			this.dispose();
		}else {
			this.parent.setVisible(true);
			this.dispose();
		}
		
	}
}
