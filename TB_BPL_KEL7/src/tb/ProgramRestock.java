package tb;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class ProgramRestock {
	static Scanner scr = new Scanner(System.in);
	static RestockBarang restockBarang;
	
	public ProgramRestock() {
		try {
			restockBarang = new RestockBarang();
		} catch (NullPointerException e) {
			System.out.println("Masukkan data terlebih dahulu");
		}
	}
	
	public static void prorestock() throws SQLException {
        Integer option = 0;
        do {
        	System.out.println("\n=======================================");
            System.out.println(">>>> Pilihan Master Restock Barang <<<<");
            System.out.println("=======================================");
            System.out.println("---------------------------------------");
            System.out.println("         [ 1 ] Restock Barang          ");
            System.out.println("---------------------------------------");
            System.out.println("         [ 0 ] Exit                    ");
            System.out.println("---------------------------------------");
            System.out.print("Pilihan No : ");
            option = Integer.parseInt(scr.nextLine());
            
	        switch (option) {
	                    
	                    case 1:
	                    restock();
	                    tunggu();
	                    break;
	                    
	                    case 0:
	                    MainMenu.mainMenu();
	                    break;
	                    default:
		                    
	             System.out.println("========================================");
	             System.out.println("||       Inputan Menu Tidak Valid     ||");
	             System.out.println("========================================");
	            }
	          } while (option >= 0);  
	    }
	   private static void lihatKoleksi() throws SQLException {
	    	System.out.println("================================================================================================");
	    	System.out.println("************************************************************************************************");
	        System.out.println("                                    Tampilkan Data Barang                                       ");
	        System.out.println("************************************************************************************************");
	        
	        ArrayList<Barang> listBarang =  restockBarang.getAll();

	        for( Barang barang : listBarang) {
	        	String format = "SKU: %-5s | Nama Barang: %-10s | Stock: %-5s | Harga Jual: %-7s | Harga Beli: %-5s\n";
	        	System.out.printf(format, barang.getSKU(), barang.getNamaBarang(), barang.getStok(),barang.getHargaJual(), barang.getHargaBeli() );
	        } 
	    
	    }
	    private static void restock() throws SQLException{
	    	
	    lihatKoleksi();
	    
    	System.out.println("\n================================");
        System.out.println("||       Restock Barang       ||");
        System.out.println("================================");
        
        if(RestockBarang.restock()>0) {
        	System.out.println("\n>>> Stok Barang Berhasil Diperbarui <<<");
        }
       }
	    
	    private static void tunggu(){
	        System.out.print("       \nTekan Enter untuk Next    ");
	        scr.nextLine();
	}
}
