package tb;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MainMenu {
	static void pilihanMenu(String menuPilihan) throws SQLException {
	    switch(menuPilihan) {
	        case "1":
	        	ProgramUser user = new ProgramUser();
	        	user.prouser();
	            break;
	        case "2":
       	        ProgramBarang barang = new ProgramBarang();
      	        barang.probarang();
	            break;
	        case "3":
	        	ProgramRestock stok = new ProgramRestock();
	        	ProgramRestock.prorestock();
	            break;
	        case "4":
	        	Transaksi.Menu();
	            break;
	        case "5":
	        	LaporanTransaksi.MenuLT();
	            break;
	        case "6":Login.login();
	            break;
	        default:
				System.out.println("PILIHAN TIDAK TERSEDIA");
				mainMenu();
	    }
	}
	public static Scanner input =new Scanner(System.in);
	
	public static void mainMenu() throws SQLException {
	    int loopX = 0;
	    String menuPilihan ="";
	    while(loopX==0) 
	    {
	    System.out.println("\n|===============================================|");
		System.out.println("|\t\t       M E N U                  |");
		System.out.println("|===============================================|");
		System.out.println("1. PENGELOLAAN USER");
		System.out.println("2. PENGELOLAAN DATA MASTER BARANG");
		System.out.println("3. PENGELOLAAN RESTOCK BARANG");
		System.out.println("4. PENGELOLAAN TRANSAKSI PENJUALAN BARANG");
		System.out.println("5. LAPORAN ");
		System.out.println("6. LOG OUT");
		System.out.print("MASUKKAN PILIHAN: [1]/[2]/[3]/[4]/[5]/[6] : ");
        try 
        {
            menuPilihan = input.next();
            loopX = 1;
        }
        catch(InputMismatchException e) 
        {
            System.out.println("Harus Memasukkan Angka!");
        }
    }
    System.out.println("=================================================");
    pilihanMenu(menuPilihan);
	}
}