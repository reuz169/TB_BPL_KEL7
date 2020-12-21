package tb;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class ProgramUser {
	
	static Scanner scanner;
	static ManageUser ManageUser;

	public static void prouser() throws SQLException {
		
		ManageUser = new ManageUser();
		 scanner = new Scanner(System.in);
	        Integer option = 0;
	            do {
	            	System.out.println("\n=====================================");
	                System.out.println("      Menu Tersedia User Control     ");
	                System.out.println("=====================================");
	                System.out.println("-------------------------------------");
	                System.out.println("         [ 1 ] Lihat User            ");
	                System.out.println("-------------------------------------");
	                System.out.println("         [ 2 ] Tambah User           ");
	                System.out.println("-------------------------------------");
	                System.out.println("         [ 3 ] Edit User             ");
	                System.out.println("-------------------------------------");
	                System.out.println("         [ 4 ] Cari User             ");
	                System.out.println("-------------------------------------");
	                System.out.println("         [ 5 ] Hapus User            ");
	                System.out.println("-------------------------------------");
	                System.out.println("         [ 0 ] Exit                  ");
	                System.out.println("-------------------------------------");
	                System.out.println("=====================================");
	                System.out.print  ("  Input No [ 1 / 2 / 3 / 4 / 5 / 0 ]  ");
	                
	                option = Integer.parseInt(scanner.nextLine());
	                
	                switch (option) {
	                    
	                    case 1:
	                    lihatKoleksi();
	                    tunggu();
	                    break;
	                    
	                    case 2:
	                    tambahKoleksi();
	                    tunggu();
	                    break;
	                    
	                    case 3:
	                    editKoleksi();
	                    tunggu();
	                    break;
	                    
	                    case 4:
	                    cariKoleksi();
	                    tunggu();
	                    break;
	                   
	                    case 5:
		                hapusKoleksi();
		                tunggu();
		                break;
		                    
	                    case 0:
	                    	MainMenu.mainMenu();
	                    break;
	                    default:
	                    System.out.println("\n========================================");
	                    System.out.println("||       Inputan Data Tidak Valid     ||");
	                    System.out.println("========================================");
	                }
	                
	            } while (option != 0);
	         
	    }
	
	private static void lihatKoleksi() throws SQLException {
    	System.out.println("\n=====================================");
        System.out.println("||         Tampilkan User          ||");
        System.out.println("=====================================");
        
        ArrayList<User> ListUser =  ManageUser.getAll();
        
        for( User user : ListUser) {
        	System.out.println("\nUsername \t: " + user.getusername());
        	System.out.println("Login Terakhir \t: " + user.getlogin());
        	System.out.println("Email \t\t: " + user.getemail());
        	System.out.println("Password \t: " + user.getpassword());
        }
    }
	    
	private static void tambahKoleksi() throws SQLException{
		System.out.println("\n==================================");
		System.out.println("||          Tambah User         ||");
		System.out.println("==================================");
    
		if(ManageUser.tambahData()>0) {
    		System.out.println("=========================================");
    		System.out.println("||       User Berhasil Ditambahkan     ||");
    		System.out.println("=========================================");
		}
		else {
    		System.out.println("==================================");
    		System.out.println("||      User Gagal Ditambah     ||");
    		System.out.println("==================================");
		}
	}

	private static void editKoleksi() throws SQLException {
    	System.out.println("\n================================");
        System.out.println("||          Edit User         ||");
        System.out.println("================================");
         
        ArrayList<User> ListUser =  ManageUser.getAll();
        
        for( User user : ListUser) {
        	System.out.println("\nUsername \t: " + user.getusername());
        	System.out.println("Login Terakhir \t: " + user.getlogin());
        	System.out.println("Email \t\t: " + user.getemail());
        	System.out.println("Password \t: " + user.getpassword());
        	}
        
        if(ManageUser.edit()>0) {
        	System.out.println("\n==================================");
        	System.out.println("       User Berhasil Diedit       ");
        	System.out.println("==================================");
        }
        else {
        	System.out.println("\n==================================");
        	System.out.println("         User Gagal Diedit        ");
        	System.out.println("==================================");
        }
    }
	    
	private static void cariKoleksi() throws SQLException {
    	System.out.println("\n================================");
    	System.out.println("||         Cari User          ||");
    	System.out.println("================================");
        
    	ArrayList<User> ListUser =  ManageUser.cari();
        
        for( User user : ListUser) {
        	System.out.println("\nUsername \t: " + user.getusername());
        	System.out.println("Login Terakhir \t: " + user.getlogin());
        	System.out.println("Email \t\t: " + user.getemail());
        	System.out.println("Password \t: " + user.getpassword());
        
        } 
    }
	
	private static void hapusKoleksi() throws SQLException{
    	System.out.println("=================================");
        System.out.println("||           Hapus User        ||");
        System.out.println("=================================");
        
        ArrayList<User> ListUser =  ManageUser.getAll();
        
        for( User user : ListUser) {
        	System.out.println("\nUsername \t: " + user.getusername());
        	System.out.println("Login Terakhir \t: " + user.getlogin());
        	System.out.println("Email \t\t: " + user.getemail());
        	System.out.println("Password \t: " + user.getpassword());
        	}
        
        if(ManageUser.hapus()>0) {
    	    System.out.println("=====================================");
        	System.out.println("||       Data Berhasil Dihapus     ||");
        	System.out.println("=====================================");
        }
        else {
    	  	System.out.println("=====================================");
        	System.out.println("||        Data Gagal Dihapus       ||");
        	System.out.println("=====================================");
      	}
    }
	
	private static void tunggu(){
        System.out.print("       \nTekan Enter untuk Next    ");
        scanner.nextLine();  
    }
}

