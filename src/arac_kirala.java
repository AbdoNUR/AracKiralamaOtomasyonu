import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import veriislem.veriislem;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class arac_kirala extends JFrame {

	private JPanel contentPane;
	private JTextField txt_ad;
	private JTextField txt_soyad;
	private JTextField txt_tc;
	private JTextField txt_adres;
	private JTextField txt_tel;
	private JTable tbl_musteri;
	
	
   veriislem veri = new veriislem();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					arac_kirala frame = new arac_kirala();
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
	public arac_kirala() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 569, 597);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Kiralayan Ad: ");
		lblNewLabel.setBounds(50, 41, 86, 14);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Kiaralayan Soyad : ");
		lblNewLabel_1.setBounds(29, 83, 107, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Kiralayan TC:");
		lblNewLabel_2.setBounds(50, 131, 87, 14);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Kiralayan Adres : ");
		lblNewLabel_3.setBounds(39, 174, 98, 14);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Kiralayan TelNo :");
		lblNewLabel_4.setBounds(39, 218, 98, 14);
		panel.add(lblNewLabel_4);
		
		txt_ad = new JTextField();
		txt_ad.setBounds(147, 38, 128, 20);
		panel.add(txt_ad);
		txt_ad.setColumns(10);
		
		txt_soyad = new JTextField();
		txt_soyad.setBounds(147, 80, 128, 20);
		panel.add(txt_soyad);
		txt_soyad.setColumns(10);
		
		txt_tc = new JTextField();
		txt_tc.setBounds(147, 128, 128, 20);
		panel.add(txt_tc);
		txt_tc.setColumns(10);
		
		txt_adres = new JTextField();
		txt_adres.setBounds(147, 171, 128, 20);
		panel.add(txt_adres);
		txt_adres.setColumns(10);
		
		txt_tel = new JTextField();
		txt_tel.setBounds(147, 215, 128, 20);
		panel.add(txt_tel);
		txt_tel.setColumns(10);
		JButton btn_musteri_ekle = new JButton("Yeni M\u00FCsteri Ekle");
		
	
		btn_musteri_ekle.setBounds(221, 258, 141, 23);
		panel.add(btn_musteri_ekle);
		
		tbl_musteri = new JTable();
		tbl_musteri.setBounds(21, 306, 492, 184);
		panel.add(tbl_musteri);
		
		JButton btn_kirala = new JButton("Kirala");
		btn_kirala.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			try {
				veri.kiralama_kaydet(Integer.parseInt(tbl_musteri.getValueAt(tbl_musteri.getSelectedRow(), 0)+""), basýk.arac_id, basýk.veris_tarihi, false);
				dispose();
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		});
		btn_kirala.setBounds(424, 501, 89, 44);
		panel.add(btn_kirala);
		
		JButton btn_musteri_sil = new JButton("M\u00FC\u015Fteri Sil");
		
		btn_musteri_sil.setBounds(29, 501, 89, 44);
		panel.add(btn_musteri_sil);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent arg0) {
				try {
					tbl_musteri.setModel(veri.musterigoster());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btn_musteri_ekle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			try {
				veri.musteri_kaydet(txt_ad.getText(),txt_soyad.getText(), txt_tc.getText(), txt_adres.getText(), txt_tel.getText());
				tbl_musteri.setModel(veri.musterigoster());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			}
		});
		btn_musteri_sil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			int satir= tbl_musteri.getSelectedRow();
			try {
				veri.arac_sil(Integer.parseInt(tbl_musteri.getValueAt(satir, 0)+""));
				tbl_musteri.setModel(veri.musterigoster());
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
