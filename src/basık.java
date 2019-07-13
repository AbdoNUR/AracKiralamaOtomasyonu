
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import veriislem.veriislem;

import javax.imageio.ImageIO;

import javax.swing.ImageIcon;

import javax.swing.JLabel;

import java.awt.Font;

import java.awt.Color;

import java.awt.Toolkit;

import javax.swing.JButton;
import java.awt.event.ActionListener;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import java.awt.event.ActionEvent;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.io.InputStream;
import java.beans.PropertyChangeEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


import javax.swing.ListSelectionModel;
import javax.swing.JTabbedPane;



import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import java.awt.Component;

import javax.swing.BoxLayout;

public class basýk extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					basýk frame = new basýk();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.++
	 */
	static int i;
	private JTable tbl_arac;
	private JTable tbl;
	public static String marka=null;
	public static String model=null;
	public static String kiralayanad=null;
	public static String kiralayansoyad=null;
	
	public static java.sql.Date veris_tarihi=null;
	public static int kiralayan_id =0;
	public static int arac_id=0;
	public static int fiyat = 0;
	
	ArrayList<java.sql.Date> arabagun=new ArrayList<java.sql.Date>();
	JPanel calender_panel = new JPanel();
	veriislem vi = new veriislem();
	RangeEvaluator evaluator = new RangeEvaluator();
	JDateChooser jchooser;
	JCalendar calendar = new JCalendar();
	ArrayList<String> dolugun= null;
	public basýk() throws ParseException {
		setForeground(new Color(255, 255, 255));
		
		setTitle("BASIK RENT A CAR");
		setType(Type.POPUP);
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\HP\\Desktop\\efwfdsdfsdfsdfs.PNG"));
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1282, 788);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
		
		JTabbedPane ana_tab = new JTabbedPane(JTabbedPane.TOP);
		ana_tab.setBackground(new Color(255, 255, 255));
		ana_tab.setForeground(new Color(0, 0, 0));
		ana_tab.setOpaque(true);
		contentPane.add(ana_tab);
		MaskFormatter mf= new MaskFormatter("###########");
			
			JPanel araclar = new JPanel();
			araclar.setBackground(new Color(255, 255, 255));
			ana_tab.addTab("Ara\u00E7lar", null, araclar, null);
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setForeground(new Color(255, 255, 255));
			scrollPane.setBackground(new Color(255, 255, 255));
			scrollPane.setBounds(22, 112, 429, 403);
			scrollPane.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					int satir=tbl_arac.getSelectedRow();
					String marka=String.valueOf(tbl_arac.getValueAt(satir, 1));
					String model=String.valueOf(tbl_arac.getValueAt(satir, 2));
					
					
				}
			});
			
			araclar.setLayout(null);
			araclar.add(scrollPane);
			scrollPane.setViewportView(tbl_arac);
			
			tbl = new JTable();
			tbl.setSurrendersFocusOnKeystroke(true);
			tbl.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			
			
			
			
			scrollPane.setViewportView(tbl);
			
			JLabel label = new JLabel("New label");
			scrollPane.setColumnHeaderView(label);
			
			JPanel panel = new JPanel();
			panel.setBackground(new Color(255, 255, 255));
			panel.setBounds(461, 112, 390, 453);
			araclar.add(panel);
			panel.setLayout(null);
			
			JLabel lbl_marka = new JLabel("-----");
			lbl_marka.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lbl_marka.setBounds(293, 131, 120, 14);
			panel.add(lbl_marka);
			
			JLabel lblNewLabel_5 = new JLabel("Araba Marka : ");
			lblNewLabel_5.setBounds(180, 130, 103, 14);
			panel.add(lblNewLabel_5);
			lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 14));
			
			
			JLabel lblNewLabel_2 = new JLabel("Model :");
			lblNewLabel_2.setBounds(180, 179, 103, 14);
			panel.add(lblNewLabel_2);
			lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
			
			JLabel lbl_model = new JLabel("-----");
			lbl_model.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lbl_model.setBounds(271, 181, 140, 14);
			panel.add(lbl_model);
			
			JLabel lblNewLabel_3 = new JLabel("Vites : ");
			lblNewLabel_3.setBounds(180, 224, 58, 14);
			panel.add(lblNewLabel_3);
			lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 14));
			
			JLabel lbl_vites = new JLabel("-----");
			lbl_vites.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lbl_vites.setBounds(272, 226, 140, 14);
			panel.add(lbl_vites);
			
			JLabel lblNewLabel_4 = new JLabel("Y\u0131l :");
			lblNewLabel_4.setBounds(191, 271, 58, 14);
			panel.add(lblNewLabel_4);
			lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 14));
			
			JLabel lbl_yil = new JLabel("-----");
			lbl_yil.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lbl_yil.setBounds(271, 273, 152, 14);
			panel.add(lbl_yil);
			
			JLabel lbl_resim = new JLabel("");
			lbl_resim.setBounds(10, 30, 160, 139);
			panel.add(lbl_resim);
			lbl_resim.setIcon(null);
			
			JLabel lblNewLabel_1 = new JLabel("Yak\u0131t:");
			lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblNewLabel_1.setBounds(180, 306, 48, 14);
			panel.add(lblNewLabel_1);
			
			JLabel lbl_yakit = new JLabel("-----");
			lbl_yakit.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lbl_yakit.setBounds(272, 308, 120, 14);
			panel.add(lbl_yakit);
			
			JLabel lblNewLabel_6 = new JLabel("Plaka:");
			lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblNewLabel_6.setBounds(180, 345, 48, 14);
			panel.add(lblNewLabel_6);
			
			JLabel lbl_plaka = new JLabel("-----");
			lbl_plaka.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lbl_plaka.setBounds(272, 347, 120, 14);
			panel.add(lbl_plaka);
			
			JLabel lblNewLabel_7 = new JLabel("Fiyat:");
			lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblNewLabel_7.setBounds(180, 382, 48, 14);
			panel.add(lblNewLabel_7);
			
			JLabel lbl_fiyat = new JLabel("-----");
			lbl_fiyat.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lbl_fiyat.setBounds(268, 384, 124, 14);
			panel.add(lbl_fiyat);
			
			JLabel lblAralarmz = new JLabel("Ara\u00E7lar\u0131m\u0131z");
			lblAralarmz.setBackground(new Color(0, 0, 0));
			lblAralarmz.setForeground(new Color(0, 0, 0));
			lblAralarmz.setBounds(22, 56, 216, 61);
			lblAralarmz.setFont(new Font("MV Boli", Font.BOLD, 30));
			araclar.add(lblAralarmz);
			
			JLabel iconlbl = new JLabel("");
			iconlbl.setIcon(new ImageIcon("C:\\Users\\HP\\Desktop\\indir.png"));
			iconlbl.setBounds(37, 29, 308, 195);
			araclar.add(iconlbl);
			
			JPanel calendar_panel = new JPanel();
			calendar_panel.setBounds(857, 112, 369, 403);
			araclar.add(calendar_panel);
			calendar_panel.setLayout(new BoxLayout(calendar_panel, BoxLayout.X_AXIS));
			calendar.getDayChooser().getDayPanel().setBackground(Color.WHITE);
			
			calendar.addPropertyChangeListener("day",new PropertyChangeListener() {
				public void propertyChange(PropertyChangeEvent evt) {
					arabacalenderyenile();
								
				}
			});
			calendar_panel.add(calendar);
			
			JButton btn_kirala = new JButton("Arac Se\u00E7 Kirala");
		
			
			btn_kirala.setBounds(1031, 562, 152, 38);
			araclar.add(btn_kirala);
				
				
				
				SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
				 	
				 	calendar.setSelectableDateRange(new java.util.Date(), sdf.parse("9999-01-01"));
				
				JPanel islemler = new JPanel();
				ana_tab.addTab("\u0130\u015Flemler", null, islemler, null);
				islemler.setLayout(null);
				
				JButton btn_aracislem = new JButton("Ara\u00E7 \u0130\u015Flemleri");
				
				btn_aracislem.setFont(new Font("Tahoma", Font.BOLD, 18));
				btn_aracislem.setBounds(40, 45, 237, 109);
				islemler.add(btn_aracislem);
				
				JButton btn_arac_al = new JButton("Ara\u00E7 \u0130ade");
				
				btn_arac_al.setFont(new Font("Tahoma", Font.BOLD, 18));
				btn_arac_al.setBounds(308, 45, 237, 109);
				islemler.add(btn_arac_al);
				
				JButton btnista = new JButton("Ara\u00E7 Ayl\u0131k Kullan\u0131m");
				btnista.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						arac_istatistik arac_is= new arac_istatistik();
						arac_is.setVisible(true);
						arac_is.setTitle("Araç Ýstatistik");
						arac_is.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
					}
				});
				btnista.setFont(new Font("Tahoma", Font.BOLD, 18));
				btnista.setBounds(586, 45, 237, 109);
				islemler.add(btnista);
				addWindowListener(new WindowAdapter() {
					
					public void windowActivated(WindowEvent e) {
						 arabacalenderyenile();
						Calendar cal =Calendar.getInstance();
						veriislem baglan = new veriislem(); //veriiþlem clasýna baglan adýnda yeni nesnesini oluþturur
						ResultSet rs;
						DefaultTableModel tdt=null;
						int sutuns;
						try {
							 rs = baglan.baglan().executeQuery("select arac_id,marka,model,vites,yil,yakit,plaka,fiyat,arac_res from araclar");//rs verileri tutar.
					            sutuns=rs.getMetaData().getColumnCount();//kolon sayýsýný verir
					           
					           tdt = new DefaultTableModel();//model oluþturur.
					          for (int i = 1; i < sutuns; i++) 
					              tdt.addColumn(rs.getMetaData().getColumnName(i));
					          
					            while(rs.next()){
					                Object[] row = new Object[sutuns];
					                for (int i = 1; i <= sutuns; i++) 
					                    row[i-1]=rs.getObject(i);
					                    tdt.addRow(row);
					                    
					                
							}
					           
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						 tbl.setModel(tdt);
						 
							
					
						 
						 
					}
				
				});
			tbl.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					veriislem baglan = new veriislem();
					int sutun= tbl.getSelectedRow();
					 arac_id=Integer.parseInt(tbl.getValueAt(sutun, 0)+"");
					 System.out.println(arac_id);
					BufferedImage res=null;
					String ad=tbl.getValueAt(sutun, 1).toString();
						lbl_marka.setText(ad);
						lbl_model.setText(tbl.getValueAt(sutun, 2).toString());
						lbl_vites.setText(tbl.getValueAt(sutun, 3).toString());
						lbl_yil.setText(tbl.getValueAt(sutun, 4).toString());
						lbl_yakit.setText(tbl.getValueAt(sutun,5).toString());
						lbl_plaka.setText(tbl.getValueAt(sutun,6).toString());
						lbl_fiyat.setText(tbl.getValueAt(sutun,7).toString());
						marka=lbl_marka.getText();
						model=lbl_model.getText();
						
						
						try {
							ResultSet rs =  baglan.baglan().executeQuery("select * from araclar where arac_id = " +tbl.getValueAt(sutun, 0).toString());
							while (rs.next()) {
								InputStream restut=rs.getBinaryStream("arac_res");
							res=ImageIO.read(restut);
								lbl_resim.setIcon(new ImageIcon(res));
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						arabacalenderyenile();
				}
			});
		
			btn_aracislem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				
				aracislem arac_frm= new aracislem();
				arac_frm.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				arac_frm.setVisible(true);
					
				}
				
				
				
			});
			btn_kirala.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				arac_id=Integer.parseInt(tbl.getValueAt(tbl.getSelectedRow(), 0)+"");
				java.util.Date tarih = calendar.getDate();
				veris_tarihi=new java.sql.Date(tarih.getTime());
				arac_kirala frmkirala= new arac_kirala();
				frmkirala.setVisible(true);
				frmkirala.setTitle("Araç Kirala");
				frmkirala.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
				}
			});
			btn_arac_al.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					arac_iade araciade_frm= new arac_iade();
					araciade_frm.setVisible(true);
					araciade_frm.setTitle("Araç Ýade");
					araciade_frm.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
				}
			});
	}
	
	public void arabacalenderyenile() {
		arabagun=vi.gunler(arac_id);
		evaluator.setDates(arabagun);
		System.out.println(arabagun);
		calendar.getDayChooser().addDateEvaluator(evaluator);
		calendar.setDate(Calendar.getInstance().getTime());
		JButton btn;
	Component[] com=calendar.getDayChooser().getDayPanel().getComponents();
	dolugun= new ArrayList<String>();
	System.out.println("sayi="+com.length);
	for (int i = 0; i < com.length; i++) {
	 btn= new JButton();
	 btn=(JButton)com[i];
	 
	 if(btn.getBackground()==Color.RED) {
		 dolugun.add(btn.getText());
		
	 }
	}
	
		
		
		
		
		
	}
}
