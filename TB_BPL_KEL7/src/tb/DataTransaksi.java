package tb;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class DataTransaksi {
	
	public static String NoresiT, UsernameT, TanggalT, IdT, pilihSKU ;
	public static int jumSKU, hargaJSKU;
	Scanner sc = new Scanner(System.in);
	public static String url = "jdbc:mysql://localhost/tb_bpl?serverTimezone=Asia/Jakarta";
	public static String user = "root";
	public static String password = "";
	
	public static void noresi() {
		try
		{
			Connection c = DriverManager.getConnection(url, user, password);
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT noresi FROM transaksi");
			
		String bln=null,bln1="AA",bln2="BB",bln3="CC",bln4="DD",bln5="EE",bln6="FF",
				bln7="GG",bln8="HH",bln9="II",bln10="JJ",bln11="KK",bln12="LL";
		
		String bln01="01",bln02="02",bln03="03",bln04="04",bln05="05",bln06="06",
				bln07="07",bln08="08",bln09="09",bln010="10",bln011="11",bln012="12";
			
		Date date = new Date(); 
		SimpleDateFormat bulan = new SimpleDateFormat("MM");
		SimpleDateFormat tanggal = new SimpleDateFormat("dd");
		String bulanTransaksi=bulan.format(date);
		String tanggalTransaksi=tanggal.format(date);
		if(bulanTransaksi.equals(bln01))
		{
			bln=bln1;
		}
		else if(bulanTransaksi.equals(bln02))
		{
			bln=bln2;
		}
		else if(bulanTransaksi.equals(bln03))
		{
			bln=bln3;
		}
		else if(bulanTransaksi.equals(bln04))
		{
			bln=bln4;
		}
		else if(bulanTransaksi.equals(bln05))
		{
			bln=bln5;
		}
		else if(bulanTransaksi.equals(bln06))
		{
			bln=bln6;
		}
		else if(bulanTransaksi.equals(bln07))
		{
			bln=bln7;
		}
		else if(bulanTransaksi.equals(bln08))
		{
			bln=bln8;
		}
		else if(bulanTransaksi.equals(bln09))
		{
			bln=bln9;
		}
		else if(bulanTransaksi.equals(bln010))
		{
			bln=bln10;
		}
		else if(bulanTransaksi.equals(bln011))
		{
			bln=bln11;
		}
		else if(bulanTransaksi.equals(bln012))
		{
			bln=bln12;
		}

		int i=1;
		while(rs.next()) { //Perbaris
		String rsNORESI = rs.getString("noresi");
		 i++;
		}
		 String formatResi=bln+tanggalTransaksi+i;
		 NoresiT = formatResi;
		}
		
		catch(SQLException exc) 
		{
			System.err.println("Terjadi error : " + exc.toString());
		}

	}
	
	public void Tanggal() {
		Date sekarang = new Date();
		TanggalT= String.format("%tF", sekarang);
	}
	
	public void DTransaksi()
	{
		try
		{
			boolean ulangStok=true;
			int stok = 0;
			Connection c = DriverManager.getConnection(url, user, password);
			Statement stmt = c.createStatement();
			
			System.out.print("PILIH SKU: ");
			pilihSKU= sc.next().toUpperCase();
			ResultSet rs = stmt.executeQuery("SELECT sku, harga_jual,stock FROM barang WHERE sku= '" + pilihSKU + "';");
			if(rs.next()) 
		    {
			pilihSKU= rs.getString("sku");
			hargaJSKU= rs.getInt("harga_jual");
			stok= rs.getInt("stock");
			System.out.println(pilihSKU);
			
			do {
				System.out.print("MASUKKAN JUMLAH: ");
				jumSKU= sc.nextInt();
					if(stok>=jumSKU && jumSKU>0)
					{
						hargaJSKU=hargaJSKU*jumSKU;
						stok=stok-jumSKU;
						String sqlupdate = "UPDATE barang SET  stock=? where sku=?";
						PreparedStatement update = c.prepareStatement(sqlupdate);
						stmt = c.createStatement();
						update.setInt(1, stok);
						update.setString(2, pilihSKU);
						update.executeUpdate();
						ulangStok= false;
					}
					else
					{
						System.out.println("PERSEDIAAN GUDANG KURANG");
						System.out.println("JUMLAH HARUS LEBIH KECIL DARI STOK!MASUKKAN LAGI!");
					}
				}while(ulangStok);
		    }
			else
			{	System.out.println("------------------------");
				System.out.println("PILIH SKU YANG TERSEDIA!");
				System.out.println("------------------------");
				DTransaksi();
			}
			
			
		
		}
		catch(SQLException e) 
		{
			System.err.println("Terjadi error : " + e.toString());
		}
	}
	}
