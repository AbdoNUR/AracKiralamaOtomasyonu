import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import veriislem.veriislem;

import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class sec_frm extends JFrame {

	private JPanel contentPane;
	private JTable tbl_sec;
	public static int secilen_id;
	
veriislem veri= new veriislem();
private JScrollPane scrollPane;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					sec_frm frame = new sec_frm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public sec_frm() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 516, 471);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btn_sec = new JButton("Se\u00E7");
		
		btn_sec.setBounds(387, 365, 89, 51);
		contentPane.add(btn_sec);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(27, 11, 329, 405);
		contentPane.add(scrollPane);
		
		tbl_sec = new JTable();
		scrollPane.setViewportView(tbl_sec);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent arg0) {
			try {
				tbl_sec.setModel(veri.secgoster(arac_iade.tabloisim));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		});
		btn_sec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			secilen_id=Integer.parseInt(tbl_sec.getValueAt(tbl_sec.getSelectedRow(), 0)+"");
			dispose();
			}
		});
	}

}
