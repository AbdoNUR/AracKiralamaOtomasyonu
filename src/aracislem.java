import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileSystemView;

import veriislem.veriislem;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class aracislem extends JFrame {

	private JPanel contentPane;
	private JTextField txt_marka;
	private JTextField txt_model;
	private JTextField txt_vites;
	private JTextField txt_yakit;
	private JTextField txt_fiyat;
	private JTable tbl_aracgoster;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					aracislem frame = new aracislem();
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
	veriislem vi = new veriislem();
	public aracislem() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				try {
					tbl_aracgoster.setModel(vi.aracgoster());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1184, 593);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAraMarka = new JLabel("ARA\u00C7 MARKA :");
		lblAraMarka.setBounds(21, 31, 128, 14);
		contentPane.add(lblAraMarka);
		
		JLabel lblAraModel = new JLabel("ARA\u00C7 MODEL : ");
		lblAraModel.setBounds(21, 72, 117, 14);
		contentPane.add(lblAraModel);
		
		JLabel lblAraVites = new JLabel("ARA\u00C7 V\u0130TES : ");
		lblAraVites.setBounds(21, 107, 117, 14);
		contentPane.add(lblAraVites);
		
		JLabel lblAraYl = new JLabel("ARA\u00C7 YIL : ");
		lblAraYl.setBounds(36, 147, 102, 14);
		contentPane.add(lblAraYl);
		
		JLabel lblAraAyakt = new JLabel("ARA\u00C7 YAKIT : ");
		lblAraAyakt.setBounds(26, 185, 102, 14);
		contentPane.add(lblAraAyakt);
		
		JLabel lblAraPlaka = new JLabel("ARA\u00C7 PLAKA : ");
		lblAraPlaka.setBounds(21, 229, 117, 14);
		contentPane.add(lblAraPlaka);
		
		JLabel lblAraFiyat = new JLabel("ARA\u00C7 F\u0130YAT : ");
		lblAraFiyat.setBounds(21, 268, 117, 14);
		contentPane.add(lblAraFiyat);
		
		JLabel lblAracResim = new JLabel("ARAC RES\u0130M : ");
		lblAracResim.setBounds(23, 361, 115, 14);
		contentPane.add(lblAracResim);
		
		txt_marka = new JTextField();
		txt_marka.setBounds(148, 28, 207, 20);
		contentPane.add(txt_marka);
		txt_marka.setColumns(10);
		
		txt_model = new JTextField();
		txt_model.setBounds(148, 69, 207, 20);
		contentPane.add(txt_model);
		txt_model.setColumns(10);
		
		txt_vites = new JTextField();
		txt_vites.setBounds(148, 104, 207, 20);
		contentPane.add(txt_vites);
		txt_vites.setColumns(10);
		
		txt_yakit = new JTextField();
		txt_yakit.setBounds(148, 182, 207, 20);
		contentPane.add(txt_yakit);
		txt_yakit.setColumns(10);
		
		txt_fiyat = new JTextField();
		txt_fiyat.setBounds(148, 265, 207, 20);
		contentPane.add(txt_fiyat);
		txt_fiyat.setColumns(10);
		
	
		
		JButton btn_arac_resmi = new JButton("ARA\u00C7 RESM\u0130 SE\u00C7");
		
		btn_arac_resmi.setBounds(140, 357, 170, 23);
		contentPane.add(btn_arac_resmi);
		
		JLabel lbl_resimkonum = new JLabel("____________");
		lbl_resimkonum.setBounds(141, 391, 269, 14);
		contentPane.add(lbl_resimkonum);
		
		JFormattedTextField txt_plaka = new JFormattedTextField();
		txt_plaka.setBounds(148, 226, 207, 20);
		contentPane.add(txt_plaka);
		
		JFormattedTextField txt_yil = new JFormattedTextField();
		txt_yil.setBounds(148, 144, 207, 20);
		contentPane.add(txt_yil);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(442, 47, 703, 414);
		contentPane.add(scrollPane);
		
		tbl_aracgoster = new JTable();
		scrollPane.setViewportView(tbl_aracgoster);
		
		JButton btnKaydet = new JButton("KAYDET");
		
		btnKaydet.setBounds(236, 465, 119, 23);
		contentPane.add(btnKaydet);
		
		JButton btn_arac_sil = new JButton("ARA\u00C7 S\u0130L");
		
		btn_arac_sil.setBounds(926, 498, 154, 23);
		contentPane.add(btn_arac_sil);
		
		btn_arac_resmi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc;
				String path ="C:\\Users\\HP\\Desktop";
				File dosya= new File("C:\\Users\\HP\\Desktop\\");
				FileSystemView fsv=FileSystemView.getFileSystemView();
				jfc = new JFileChooser();
				jfc = new JFileChooser(path);
				jfc = new JFileChooser(dosya);
				jfc = new JFileChooser(fsv);
				jfc = new JFileChooser(path,fsv);
				jfc = new JFileChooser(dosya,fsv);
				jfc.showOpenDialog(null);
				lbl_resimkonum.setText(jfc.getSelectedFile().toString());
			}
		});
		btnKaydet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			FileInputStream inputs=null;
			File image = new File(lbl_resimkonum.getText().toString());
			
			try {
				inputs=new FileInputStream(image);
				
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				vi.arackaydet(txt_marka.getText(), txt_model.getText(), txt_vites.getText(), txt_yil.getText(), txt_yakit.getText(), txt_plaka.getText(), txt_fiyat.getText(), inputs, (int)image.length());
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				tbl_aracgoster.setModel(vi.aracgoster());
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			}
		
		});
		btn_arac_sil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			int satir = tbl_aracgoster.getSelectedRow();
			try {
				vi.arac_sil(Integer.parseInt(String.valueOf(tbl_aracgoster.getValueAt(satir, 0))));
			} catch (NumberFormatException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		try {
			tbl_aracgoster.setModel(vi.aracgoster());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			}
		});
	}
	
	
}
