package veriislem;

import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.Blob;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;


public class veriislem {

final private String driver ="com.mysql.jdbc.Driver";
    final private String url="jdbc:mysql://localhost/";
    final private String encode="?useUnicode=true&characterEncoding=utf-8";
    private String dbName="basik_oto";
    private String dbUser="root";
    private String dbPass="12345";
    
    private java.sql.Connection conn=null;
    private java.sql.Statement st=null;
    private java.sql.PreparedStatement preSt=null;
    
    public veriislem(){
    }
    
    public veriislem(String dbName,String dbUser,String dbPass ){
    this.dbName=dbName;
    this.dbUser=dbUser;
    this.dbPass=dbPass;
    }
    
    public Statement baglan() throws SQLException{
        if (st!=null) {
            kapat();
        }
        try {
            Class.forName(driver);
            conn=DriverManager.getConnection(url+dbName+encode,dbUser,dbPass);
            st=conn.createStatement();
            System.out.println("Successful");
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Baglani Hatasi : "+e);
        }
   return (Statement) st;
    }
    
    public PreparedStatement preBaglan(String query){
        if (preSt!=null) {
            kapat();
        }
        try {
            Class.forName(driver);
            conn=DriverManager.getConnection(url+dbName+encode, dbUser, dbPass);
            preSt=conn.prepareStatement(query);
            System.out.println("Successful");
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Baglani Hatasi : "+e);
        }
    return (PreparedStatement) preSt;
    }
    //baðlantý kapatma
    public void kapat(){
        try {
            if(preSt!=null){
                preSt.close();
                preSt=null;
                System.out.println("preSt kapatildi");
            }
            if(st!=null){
                st.close();
                st=null;
                System.out.println("st kapatildi");
            }
            if (conn!=null) {
                conn.close();
                conn=null;
                System.out.println("conn kapatildi");
            }
        } catch (SQLException e) {
            System.err.println("Kapatma hatasi : "+e);
        }
    }
    public void arackaydet(String marka,String model,String vites,String yil,String yakit,String plaka,String fiyat,InputStream aracresim ,int resim_lengt) throws SQLException {
    	PreparedStatement pstm =preBaglan("INSERT INTO araclar values (DEFAULT,?,?,?,?,?,?,?,?)");
    	pstm.setString(1, marka);
    	pstm.setString(2, model);
    	pstm.setString(3, vites);
    	pstm.setString(4, yil);
    	pstm.setString(5, yakit);
    	pstm.setString(6, plaka);
    	pstm.setString(7, fiyat);
    	pstm.setBinaryStream(8, aracresim,resim_lengt);
    	pstm.executeUpdate();
    	
    }
    public void musteri_kaydet(String kiralayan_ad,String kiralayan_soyad,String kiralayan_tc,String kiralayan_adres,String kiralayan_telno) throws SQLException {
    	PreparedStatement pstm =preBaglan("INSERT INTO kiralayan_bilgileri values (DEFAULT,?,?,?,?,?)");
    	pstm.setString(1, kiralayan_ad);
    	pstm.setString(2,kiralayan_soyad);
    	pstm.setString(3, kiralayan_tc);
    	pstm.setString(4, kiralayan_adres);
    	pstm.setString(5, kiralayan_telno);
    	pstm.executeUpdate();
    	
    }
    public void iade_kaydet(int kiralayan_id,int arac_id,float topfiyat,java.sql.Date teslim_tarih) throws SQLException {
    	PreparedStatement pstm =preBaglan("INSERT INTO iade values (DEFAULT,?,?,?,?)");
    	pstm.setInt(1, kiralayan_id);
    	pstm.setInt(2,arac_id);
    	pstm.setFloat(3, topfiyat);
    	pstm.setDate(4, teslim_tarih);
    	pstm.executeUpdate();
    	
    }
    public void kiralama_kaydet(int kiralayan_id,int arac_id,java.sql.Date veril_tarih,boolean teslim_durumu) throws SQLException {
    	PreparedStatement pstm =preBaglan("INSERT INTO kiralama values (DEFAULT,?,?,?,?)");
    	pstm.setInt(1, kiralayan_id);
    	pstm.setInt(2,arac_id);
    	pstm.setDate(3, veril_tarih);
    	pstm.setBoolean(4, teslim_durumu);
    	pstm.executeUpdate();
    	
    }
    public void iade_guncelle(int kiralayan_id,int arac_id) throws SQLException {
    	PreparedStatement pstm =preBaglan("update kiralama set teslim_durumu=? where kiralayan_id=? and arac_id=? and teslim_durumu=?");
    	pstm.setBoolean(1, true);
    	pstm.setInt(2, kiralayan_id);
    	pstm.setInt(3,arac_id);
    	pstm.setBoolean(4, false);
    	pstm.executeUpdate();
    	
    }
    public DefaultTableModel musterigoster() throws SQLException {
    	DefaultTableModel dt=null;
    	ResultSet myRs=null;
    	myRs=baglan().executeQuery("SELECT kiralayan_id,kiralayan_ad,kiralayan_soyad,kiralayan_TC,kiralayan_adres,kiralayan_tel from kiralayan_bilgileri");
    	
    	int sutuns=myRs.getMetaData().getColumnCount();
    	dt=new DefaultTableModel();
    	dt.addColumn("MUSTERÝ ID");
    	dt.addColumn("MUSTERÝ AD");
    	dt.addColumn("MUSTERÝ SOYAD");
    	dt.addColumn("MUSTERÝ TC");
    	dt.addColumn("MUSTERÝ ADRES");
    	dt.addColumn("MUSTERÝ TELNO");
    	
    	 while (myRs.next()) {
		Object[] row =new Object[sutuns];
		for (int i = 1; i <= sutuns; i++) {
			row[i-1]=myRs.getObject(i);
		}
			dt.addRow(row);
		}
    	 return dt;
    }
    public DefaultTableModel aracgoster() throws SQLException {
    	DefaultTableModel dt=null;
    	ResultSet myRs=null;
    	myRs=baglan().executeQuery("SELECT arac_id,marka,model,vites,yil,yakit,plaka,fiyat from araclar");
    	
    	int sutuns=myRs.getMetaData().getColumnCount();
    	dt=new DefaultTableModel();
    	dt.addColumn("ARAC ID");
    	dt.addColumn("MARKA");
    	dt.addColumn("MODEL");
    	dt.addColumn("VÝTES");
    	dt.addColumn("YIL");
    	dt.addColumn("YAKIT");
    	dt.addColumn("PLAKA");
    	dt.addColumn("FÝYAT");
    	 while (myRs.next()) {
		Object[] row =new Object[sutuns];
		for (int i = 1; i <= sutuns; i++) {
			row[i-1]=myRs.getObject(i);
		}
			dt.addRow(row);
		}
    	 return dt;
    }
   
    public DefaultTableModel iadegoster() throws SQLException {
    	DefaultTableModel dt=null;
    	ResultSet myRs=null;
    	myRs=baglan().executeQuery("SELECT arac_id,marka,model,vites,yil,yakit,plaka,fiyat from araclar");
    	
    	int sutuns=myRs.getMetaData().getColumnCount();
    	dt=new DefaultTableModel();
    	dt.addColumn("ARAC ID");
    	dt.addColumn("MARKA");
    	dt.addColumn("MODEL");
    	dt.addColumn("VÝTES");
    	dt.addColumn("YIL");
    	dt.addColumn("YAKIT");
    	dt.addColumn("PLAKA");
    	dt.addColumn("FÝYAT");
    	 while (myRs.next()) {
		Object[] row =new Object[sutuns];
		for (int i = 1; i <= sutuns; i++) {
			row[i-1]=myRs.getObject(i);
		}
			dt.addRow(row);
		}
    	 return dt;
    }
    public DefaultTableModel kira_arac_goster(int musteri_id,int arac_id) throws SQLException {
    	DefaultTableModel dt=null;
    	ResultSet myRs=null;
    	myRs=baglan().executeQuery("SELECT marka,model,kiralayan_ad,kiralayan_soyad,veril_tarih,fiyat FROM kiralama inner join araclar on(kiralama.arac_id=araclar.arac_id)\r\n" + 
    			"  inner join kiralayan_bilgileri on(kiralama.kiralayan_id=kiralayan_bilgileri.kiralayan_id) \r\n" + 
    			"  where kiralama.kiralayan_id='"+musteri_id+"' and kiralama.arac_id='"+arac_id+"' and teslim_durumu=false");
    	
    	int sutuns=myRs.getMetaData().getColumnCount();
    	dt=new DefaultTableModel();
    	
    	dt.addColumn("MARKA");
    	dt.addColumn("MODEL");
    	dt.addColumn("MUSTERÝ ADI");
    	dt.addColumn("MUSTERÝ SOYADI");
    	dt.addColumn("TARÝH");
    	dt.addColumn("FÝYAT");
    	 while (myRs.next()) {
		Object[] row =new Object[sutuns];
		for (int i = 1; i <= sutuns; i++) {
			row[i-1]=myRs.getObject(i);
		}
			dt.addRow(row);
		}
    	 return dt;
    }
    public DefaultTableModel secgoster(String sec) throws SQLException {
    	DefaultTableModel dt=null;
    	ResultSet myRs=null;
    	if (sec=="araclar") {
    		myRs=baglan().executeQuery("SELECT arac_id,marka,model,vites,yil,yakit,plaka,fiyat from araclar");
        	
        	int sutuns=myRs.getMetaData().getColumnCount();
        	dt=new DefaultTableModel();
        	dt.addColumn("ARAC ID");
        	dt.addColumn("MARKA");
        	dt.addColumn("MODEL");
        	dt.addColumn("VÝTES");
        	dt.addColumn("YIL");
        	dt.addColumn("YAKIT");
        	dt.addColumn("PLAKA");
        	dt.addColumn("FÝYAT");
        	 while (myRs.next()) {
    		Object[] row =new Object[sutuns];
    		for (int i = 1; i <= sutuns; i++) {
    			row[i-1]=myRs.getObject(i);
    		}
    			dt.addRow(row);
    		}
		}
    	if (sec=="kiralayan_bilgileri") {
    		myRs=baglan().executeQuery("SELECT kiralayan_id,kiralayan_ad,kiralayan_soyad,kiralayan_TC,kiralayan_adres,kiralayan_tel from kiralayan_bilgileri");
        	
        	int sutuns=myRs.getMetaData().getColumnCount();
        	dt=new DefaultTableModel();
        	dt.addColumn("MUSTERÝ ID");
        	dt.addColumn("MUSTERÝ AD");
        	dt.addColumn("MUSTERÝ SOYAD");
        	dt.addColumn("MUSTERÝ TC");
        	dt.addColumn("MUSTERÝ ADRES");
        	dt.addColumn("MUSTERÝ TELNO");
        	
        	 while (myRs.next()) {
    		Object[] row =new Object[sutuns];
    		for (int i = 1; i <= sutuns; i++) {
    			row[i-1]=myRs.getObject(i);
    		}
    			dt.addRow(row);
    		}
		}
    	 return dt;
    }
    public void musteri_sil(int id) throws SQLException {
    	PreparedStatement pstmt = preBaglan("delete from kiralayan_bilgileri where kiralayan_id=?");
    	pstmt.setInt(1, id);
    	pstmt.executeUpdate();
    	
    }
    public void arac_sil(int id) throws SQLException {
    	PreparedStatement pstmt = preBaglan("delete from araclar where arac_id=?");
    	pstmt.setInt(1, id);
    	pstmt.executeUpdate();
    	
    }
    public ArrayList<java.sql.Date> gunler(int arac_id) {
    	ArrayList<java.sql.Date> arabagun= new ArrayList<java.sql.Date>();
    	ResultSet myRs=null;
    	try {
    		System.out.println(arac_id);
			myRs=baglan().executeQuery("SELECT veril_tarih FROM kiralama where arac_id= '"+arac_id+"' and teslim_durumu=false");
			
			while (myRs.next()) {
			
				arabagun.add(myRs.getDate("veril_tarih"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	System.out.println(arabagun);
    	return arabagun;
    	
    }
    public ArrayList<java.sql.Date> gunlertum(int arac_id) {
    	ArrayList<java.sql.Date> arabagun= new ArrayList<java.sql.Date>();
    	ResultSet myRs=null;
    	try {
    		System.out.println(arac_id);
			myRs=baglan().executeQuery("SELECT veril_tarih FROM kiralama where arac_id= '"+arac_id+"' ");
			
			while (myRs.next()) {
			
				arabagun.add(myRs.getDate("veril_tarih"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	System.out.println(arabagun);
    	return arabagun;
    	
    }
    public int aylýk(int arac_id) {
    	int sayi=0;
    	ResultSet myRs=null;
    	try {
    		
			myRs=baglan().executeQuery("SELECT iade_id FROM iade WHERE MONTH(teslim_tarihi) = MONTH(CURDATE()) and arac_id= "+arac_id);
			
			while (myRs.next()) {
			sayi++;
			
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return sayi;
    	
    }
}
