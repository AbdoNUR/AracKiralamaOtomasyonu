import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import veriislem.veriislem;

import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Date;
import java.sql.SQLException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class arac_iade extends JFrame {

	private JPanel contentPane;
	private JTable tbl_arac_iade;
	private JTextField txt_fiyat;
	private JLabel lblAracId;
	private JLabel lblMteriId;
	private JTextField txt_aracid;
	private JTextField txt_musteriid;
	private JButton btn_arac_Sec;
	private JButton btn_musteri_sec;
	public static String tabloisim=null;
	veriislem veri = new veriislem();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					arac_iade frame = new arac_iade();
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
	public arac_iade() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 653, 457);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btn_iadeet = new JButton("\u0130ade Et");
		
		btn_iadeet.setBounds(473, 350, 89, 35);
		contentPane.add(btn_iadeet);
		
		JLabel lblFiyat = new JLabel("Fiyat : ");
		lblFiyat.setBounds(213, 366, 58, 14);
		contentPane.add(lblFiyat);
		
		txt_fiyat = new JTextField();
		txt_fiyat.setEnabled(false);
		txt_fiyat.setBounds(281, 363, 115, 20);
		contentPane.add(txt_fiyat);
		txt_fiyat.setColumns(10);
		
		lblAracId = new JLabel("Arac ID : ");
		lblAracId.setBounds(26, 25, 58, 14);
		contentPane.add(lblAracId);
		
		lblMteriId = new JLabel("M\u00FC\u015Fteri ID : ");
		lblMteriId.setBounds(12, 62, 72, 14);
		contentPane.add(lblMteriId);
		
		txt_aracid = new JTextField();
	
		txt_aracid.setEnabled(false);
		txt_aracid.setBounds(87, 22, 115, 20);
		contentPane.add(txt_aracid);
		txt_aracid.setColumns(10);
		
		txt_musteriid = new JTextField();
		
		txt_musteriid.setEnabled(false);
		txt_musteriid.setBounds(87, 59, 115, 20);
		contentPane.add(txt_musteriid);
		txt_musteriid.setColumns(10);
		
		btn_arac_Sec = new JButton("SE\u00C7....");
		btn_arac_Sec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			 tabloisim="araclar";
			
			sec_frm secc_frm= new sec_frm();
			secc_frm.setVisible(true);
			secc_frm.setTitle("Araç Seç");
			secc_frm.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
			}
		});
		btn_arac_Sec.setBounds(225, 21, 89, 23);
		contentPane.add(btn_arac_Sec);
		
		btn_musteri_sec = new JButton("SE\u00C7....");
		btn_musteri_sec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tabloisim="kiralayan_bilgileri";
				sec_frm secc_frm= new sec_frm();
				secc_frm.setVisible(true);
				secc_frm.setTitle("Müsteri Seç");
				secc_frm.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
			}
			
		});
		btn_musteri_sec.setBounds(225, 58, 89, 23);
		contentPane.add(btn_musteri_sec);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 94, 568, 223);
		contentPane.add(scrollPane);
		
		tbl_arac_iade = new JTable();
		scrollPane.setViewportView(tbl_arac_iade);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent arg0) {
			if (tabloisim=="araclar") {
				txt_aracid.setText(sec_frm.secilen_id+"");
			}
			if (tabloisim=="kiralayan_bilgileri") {
				txt_musteriid.setText(sec_frm.secilen_id+"");
			}
try {
				
				tbl_arac_iade.setModel(veri.kira_arac_goster(Integer.parseInt(txt_musteriid.getText()), Integer.parseInt(txt_aracid.getText())));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
int fiyattop=0;
              for (int i = 0; i <tbl_arac_iade.getRowCount(); i++) {
				fiyattop+= Integer.parseInt(tbl_arac_iade.getValueAt(i, 5)+"");
		
			}
              txt_fiyat.setText(fiyattop+"");
			}
		});
		btn_iadeet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					java.util.Date date = new java.util.Date();
					java.sql.Date simdi = new java.sql.Date(date.getTime());
				
veri.iade_kaydet(Integer.parseInt(txt_musteriid.getText()), Integer.parseInt(txt_aracid.getText()), Integer.parseInt(txt_fiyat.getText()), simdi);
				veri.iade_guncelle(Integer.parseInt(txt_musteriid.getText()), Integer.parseInt(txt_aracid.getText()));
				tbl_arac_iade.setModel(veri.kira_arac_goster(Integer.parseInt(txt_musteriid.getText()), Integer.parseInt(txt_aracid.getText())));
				
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		});
		
	}
}
