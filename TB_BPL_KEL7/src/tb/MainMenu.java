package tb;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MainMenu {
	static void pilihanMenu(int menuPilihan) throws SQLException {
	    switch(menuPilihan) {
	        case 1:
	        	ProgramUser user = new ProgramUser();
	        	user.prouser();
	            break;
	        case 2:
	        	ProgramBarang barang = new ProgramBarang();
	        	barang.probarang();
	            break;
	        case 3:
	        	ProgramRestock stok = new ProgramRestock();
	        	stok.prorestock();
	            break;
	        case 4:
	        	Transaksi transaksi = new Transaksi();
	        	transaksi.Menu();
	            break;
	        case 5:
	            break;
	        case 6:
	            break;
	        default:
				System.out.println("Pilihan Anda Tidak Tersedia");
	    }while (menuPilihan!=6);
	}
	public static Scanner input =new Scanner(System.in);
	
	public static void mainMenu() throws SQLException {
	    int loopX = 0;
	    int menuPilihan = 0;
	    while(loopX == 0) 
	    {
	    System.out.println("\n|===============================================|");
		System.out.println("|\t\t\tMENU\t\t\t|");
		System.out.println("|===============================================|");
		System.out.println("1. PENGELOLAAN USER");
		System.out.println("2. PENGELOLAAN DATA MASTER BARANG");
		System.out.println("3. PENGELOLAAN RESTOCK BARANG");
		System.out.println("4. PENGELOLAAN TRANSAKSI PENJUALAN BARANG");
		System.out.println("5. LAPORAN ");
		System.out.println("6. LOG OUT");
		System.out.print("Masukkan Pilihan : [1]/[2]/[3]/[4]/[5]/[6] : ");
        try 
        {
            menuPilihan = input.nextInt();
            loopX = 1;
            if(menuPilihan<1 || menuPilihan>6)
            {
            	System.out.println("Masukkan Angka 1-6!");
            }
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