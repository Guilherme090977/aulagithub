package impacta.ead.estacionamento.apresentacao;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;

public class TelaLogin extends JFrame implements ActionListener {
	private JPasswordField txtSenha;
	private JPanel panel_2;
	private JRadioButton rdbtnOperacional;
	private JRadioButton rdbtnRelatorio;
	private JButton btnOK;
	
	//TODO Remover ao entregar o projeto
	public static void main(String[] args) {
		TelaLogin tela = new TelaLogin();
		tela.setVisible(true);
	}
	public TelaLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(new Dimension(500,150));
		setResizable(false);
		setTitle("Login");
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.SOUTH);
		
		btnOK = new JButton("Ok");
		btnOK.addActionListener(this);
		btnOK.setActionCommand("ok");
		panel.add(btnOK);
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		panel_2 = new JPanel();
		panel_1.add(panel_2, BorderLayout.SOUTH);
		
		JLabel lblSenha = new JLabel("Senha:");
		panel_2.add(lblSenha);
		
		txtSenha = new JPasswordField();
		txtSenha.setColumns(20);
		panel_2.add(txtSenha);
		
		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3, BorderLayout.CENTER);
		
		ButtonGroup grupo = new ButtonGroup();
		
		rdbtnOperacional = new JRadioButton("Operacional");
		rdbtnOperacional.setSelected(true);
		rdbtnOperacional.addActionListener(this);
		rdbtnOperacional.setActionCommand("operacional");
		grupo.add(rdbtnOperacional);
		panel_3.add(rdbtnOperacional);
		
		rdbtnRelatorio = new JRadioButton("Relatório");
		rdbtnRelatorio.addActionListener(this);
		rdbtnRelatorio.setActionCommand("relatorio");
		grupo.add(rdbtnRelatorio);
		panel_3.add(rdbtnRelatorio);
		
		panel_2.setVisible(false);
		
		setLocationRelativeTo(null);
	}
	@Override
	public void actionPerformed(ActionEvent evento) {
		String cmd = evento.getActionCommand();
		JFrame tela = null;
		
		if(cmd.equals("operacional")) {
			panel_2.setVisible(false);
		}else if(cmd.equals("relatorio")) {
			txtSenha.setText("");
			panel_2.setVisible(true);
		}else if(cmd.equals("ok")) {
			if(rdbtnOperacional.isSelected()) {
				tela = new TelaInicialMovimentacao();
				tela.setVisible(true);
			}else {
				String senha = new String(txtSenha.getPassword());
				if(senha.equals("impacta")) {
					tela = new TelaInicialRelatorio();
					tela.setVisible(true);
				}else {
					JOptionPane.showMessageDialog(null, "Senha inválida", "Falha Login",
																JOptionPane.ERROR_MESSAGE);
					txtSenha.setText("");
					return;
				}
			}
			this.dispose();
		}
		
		
		
	}

}
