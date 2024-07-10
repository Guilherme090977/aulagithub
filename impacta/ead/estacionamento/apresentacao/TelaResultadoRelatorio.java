package impacta.ead.estacionamento.apresentacao;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import impacta.ead.estacionamento.negocio.Movimentacao;

public class TelaResultadoRelatorio extends JFrame implements ActionListener{
	private JTable tblFaturamento;
	private JFrame parent;

	public TelaResultadoRelatorio(TelaInicialRelatorio telaInicialRelatorio, List<Movimentacao> movimentacoes) {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setSize(new Dimension(600,300));
		setResizable(false);
		setTitle("Relatorio de Faturamento");
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.SOUTH);
		
		JButton btnOk = new JButton("Ok");
		panel.add(btnOk);
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2, BorderLayout.SOUTH);
		
		JLabel lblTextoFaturamento = new JLabel("[Faturamento]");
		lblTextoFaturamento.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_2.add(lblTextoFaturamento);
		
		JScrollPane scrollPane = new JScrollPane();
		panel_1.add(scrollPane, BorderLayout.CENTER);
		
		tblFaturamento = new JTable();
		tblFaturamento.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					"Placa","Entrada","Sa\u00EDda","Valor"   
			}
		));
		scrollPane.setViewportView(tblFaturamento);
		// TODO Auto-generated constructor stub
		
		setLocationRelativeTo(null);
	}

	@Override
	public void actionPerformed(ActionEvent evento) {
		parent.setVisible(true);
		dispose();
		
	}

}
