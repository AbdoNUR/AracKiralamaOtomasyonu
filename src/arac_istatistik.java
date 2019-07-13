import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import veriislem.veriislem;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class arac_istatistik extends JFrame {

	private JPanel contentPane;
	private JTable tbl_araclar;
	private JPanel panel;
	ArrayList<java.sql.Date> arabagun=new ArrayList<java.sql.Date>();
	JPanel calender_panel = new JPanel();
	veriislem vi = new veriislem();
	RangeEvaluator evaluator = new RangeEvaluator();
	JDateChooser jchooser;
	JCalendar calendar = new JCalendar();
	ArrayList<String> dolugun= null;
	int arac_id=0;
	private JTextField txt_kiralanma_sayi;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					arac_istatistik frame = new arac_istatistik();
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
	public arac_istatistik() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 924, 510);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		
		scrollPane.setBounds(22, 115, 473, 342);
		contentPane.add(scrollPane);
		
		tbl_araclar = new JTable();
		tbl_araclar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				arac_id = Integer.parseInt(tbl_araclar.getValueAt(tbl_araclar.getSelectedRow(), 0)+"");
				arabacalenderyenile();
				txt_kiralanma_sayi.setText(vi.aylýk(arac_id)+"");
			}
		});
		scrollPane.setViewportView(tbl_araclar);
		
		JLabel lblAraBilgileri = new JLabel("Ara\u00E7 Bilgileri : ");
		lblAraBilgileri.setBounds(32, 90, 122, 14);
		contentPane.add(lblAraBilgileri);
		
		
		JPanel calendar_panel = new JPanel();
		calendar_panel.setBounds(555, 115, 332, 336);
		contentPane.add(calendar_panel);
		calendar_panel.setLayout(new BoxLayout(calendar_panel, BoxLayout.X_AXIS));
		calendar.getDayChooser().getDayPanel().setBackground(Color.WHITE);
		
		calendar.addPropertyChangeListener("day",new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
			arabacalenderyenile();
							
			}
		});
		calendar_panel.add(calendar);
		
		JLabel lblAracnAylk = new JLabel("ARACIN 1 AYLIK K\u0130RALANMA SAYISI : ");
		lblAracnAylk.setBounds(477, 69, 212, 14);
		contentPane.add(lblAracnAylk);
		
		txt_kiralanma_sayi = new JTextField();
		txt_kiralanma_sayi.setEnabled(false);
		txt_kiralanma_sayi.setBounds(716, 66, 106, 20);
		contentPane.add(txt_kiralanma_sayi);
		txt_kiralanma_sayi.setColumns(10);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent arg0) {
			try {
				tbl_araclar.setModel(vi.aracgoster());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		});
	}
	
	public void arabacalenderyenile() {
		arabagun=vi.gunlertum(arac_id);
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
