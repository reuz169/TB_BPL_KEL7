package tb;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.Stack;

public class LaporanTransaksi {
	
	public static Scanner sc= new Scanner(System.in);
	static Statement stmt,stmt1,stmt2;
	public static Integer totPenjualan=0;
	public static Integer totKeuntunganK=0, totKeuntunganB=0;
	
	public static void MenuLT() throws SQLException
	{
		boolean ulang=true;
    	do 
    	{
    		
    		System.out.println("\n|===============================================|");
    		System.out.println("|\tMENU LAPORAN DATA TRANSAKSI             |");
    		System.out.println("|===============================================|");
	        System.out.println("1. LAPORAN PERHARI");
	        System.out.println("2. LAPORAN PERBULAN");
	        System.out.println("0. KEMBALI");
	        System.out.print("MASUKKAN PILIHAN : ");
	
	        try {
				String pilih = sc.next();
	            switch (pilih) {
	                case "0":
	                    MainMenu.mainMenu();
	                    ulang=false;
	                    break;
	                case "1":
	                    LPerhari();
	                    ulang=false;
	                    MenuLT();
	                    break;
	                case "2":
	                	LPerbulan();
	                    ulang=false;
	                    MenuLT();
	                    break;
	                default:
	                    System.out.println("Pilihan yang anda masukkan tidak valid!");
	            }
	        } 
	        catch (Exception e) {
	            e.printStackTrace();
	        }
    	}while(ulang);
	}
	
	public static void LPerhari() throws SQLException
	{
		Connection c= Koneksi.koneksiSQL();
		Integer cek=0;
		String pilih="";
		do
		{
		System.out.print("*MASUKKAN TANGGAL: ");
		pilih= sc.next();
		cek=Integer.parseInt(pilih);
		}
		while(cek<1 || cek>30);
		stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT noresi, tanggal FROM transaksi ORDER BY noresi");
		
		System.out.println("DAFTAR PENJUALAN");
		Integer i=1;
		System.out.println(" ______________________________________________________________");
		System.out.println("|	NO	|	NAMA	|	JUMLAH	|	HARGA	|");
		System.out.println(" ______________________________________________________________");
		while(rs.next()) 
		{ //Perbaris
		String noresi = rs.getString("noresi");
		Date tanggal = rs.getDate("tanggal");
		SimpleDateFormat ftanggal = new SimpleDateFormat("dd");
		String bulanTransaksi=ftanggal.format(tanggal);
				if(bulanTransaksi.equals(pilih))
				{
					
				Integer jumlah=0, hargaK=0, hargaB=0, harga_jual=0, harga_beli=0;
				String sku="", namaP="";
				stmt1 = c.createStatement();
				ResultSet rs1 = stmt1.executeQuery("SELECT sku, harga, jumlah FROM transaksi_detail WHERE noresi= '" + noresi + "';");
					while(rs1.next()) 
					{ //Perbaris
						
						 sku = rs1.getString("sku");
						 hargaK = rs1.getInt("harga");
						 jumlah = rs1.getInt("jumlah");
						 stmt2 = c.createStatement();
						 ResultSet rs2 = stmt2.executeQuery("SELECT * FROM barang WHERE sku= '" + sku + "';");
						 while(rs2.next()) 
							{ //Perbaris
								 namaP = rs2.getString("nama"); 
								 harga_jual = rs2.getInt("harga_jual");
								 harga_beli = rs2.getInt("harga_beli");
								 hargaB= harga_jual-harga_beli;
								 if(hargaB<0)
								 {
									 hargaB= hargaB*-1;
								 } 
								 hargaB=hargaB*jumlah;
								System.out.println("|	"+"["+i+"]"+"	|	"+namaP+"	|	"+jumlah+"	|	"+hargaK+"	|");
								System.out.println(" _______________________________________________________________");
								i++;
								 
							}
						 
					}
					
					totPenjualan+=jumlah;
					totKeuntunganK+=hargaK;
					totKeuntunganB+=hargaB;
					
				}
		}
		System.out.println("Total Penjualan  	: " + totPenjualan);
		System.out.println("Total Keuntungan Kotor 	: Rp." + totKeuntunganK);
		System.out.println("Total Keuntungan Bersih : Rp." + totKeuntunganB);
	}
	
	public static void LPerbulan() throws SQLException
	{
		Connection c= Koneksi.koneksiSQL();
		Integer cek=0;
		String pilih="";
		do {
		System.out.print("*MASUKKAN BULAN: ");
		pilih= sc.next();
		cek=Integer.parseInt(pilih);
		}
		while(cek<1 || cek>12);
		stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT noresi, tanggal FROM transaksi ORDER BY noresi");
		Integer i=1;
		System.out.println("DAFTAR PENJUALAN");
		System.out.println(" ______________________________________________________________");
		System.out.println("|	NO	|	NAMA	|	JUMLAH	|	HARGA	|");
		System.out.println(" ______________________________________________________________");
		while(rs.next()) 
		{ //Perbaris
		String noresi = rs.getString("noresi");
		Date tanggal = rs.getDate("tanggal");
		
		//System.out.println(noresi);
		
		SimpleDateFormat fbulan = new SimpleDateFormat("MM");
		String bulanTransaksi=fbulan.format(tanggal);
				if(bulanTransaksi.equals(pilih))
				{
				
					Integer jumlah=0, hargaK=0, hargaB=0, harga_jual=0, harga_beli=0;
					String sku="",namaP="";
					stmt1 = c.createStatement();
				
				ResultSet rs1 = stmt1.executeQuery("SELECT sku, harga, jumlah FROM transaksi_detail WHERE noresi= '" + noresi + "';");
					while(rs1.next()) 
					{ //Perbaris

						 sku = rs1.getString("sku");
						 hargaK = rs1.getInt("harga");
						 jumlah = rs1.getInt("jumlah");
						 stmt2 = c.createStatement();
						 ResultSet rs2 = stmt2.executeQuery("SELECT * FROM barang WHERE sku= '" + sku + "';");
						 while(rs2.next()) 
							{ //Perbaris
								
							 	 namaP= rs2.getString("nama");
								 harga_jual = rs2.getInt("harga_jual");
								 harga_beli = rs2.getInt("harga_beli");
								 hargaB= harga_jual-harga_beli;
								 if(hargaB<0)
								 {
									 hargaB= hargaB*-1;
								 }
								 hargaB=hargaB*jumlah;
								System.out.println("|	"+"["+i+"]"+"	|	"+namaP+"	|	"+jumlah+"	|	"+hargaK+"	|");
								System.out.println(" _______________________________________________________________");
								i++;
								 
							}
					}
					
					totPenjualan+=jumlah;
					totKeuntunganK+=hargaK;
					totKeuntunganB+=hargaB;
				}
		}
		System.out.println("Total Penjualan  	: " + totPenjualan);
		System.out.println("Total Keuntungan Kotor 	: Rp." + totKeuntunganK);
		System.out.println("Total Keuntungan Bersih : Rp." + totKeuntunganB);
	}

} 
