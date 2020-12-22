package tb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class RestockBarang {
	static Scanner input = new Scanner(System.in);
	static Connection conn;
	
	public RestockBarang() {
		try {
			conn = Koneksi.koneksiSQL();			
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	 public static int restock() {
	    	
   		Integer result=0;
           
           System.out.print("\nSKU Barang Yang akan direstock ? :  ");
           String id = input.nextLine();
           
           String sql = "SELECT * FROM barang WHERE sku = ?";
           
           try {
           PreparedStatement statement = conn.prepareStatement(sql);
           statement.setString(1, id);
           ResultSet rs = statement.executeQuery();
         
           
           if(rs.next()){
               
           	   System.out.println("---------------------------------------------------");
               System.out.println("> SKU \t\t\t\t: "+rs.getString("sku"));
               System.out.println("> Nama Barang \t\t\t: "+rs.getString("nama"));
               System.out.print  ("> Tambah Stok Barang ["+rs.getString("stock")+"] \t: ");
               int stok = Integer.parseInt(input.nextLine());
               
               if(stok>=0) {
               int rsStock = rs.getInt("stock");
               int Stock = rsStock + stok;
               
               String update = "UPDATE barang SET stock = ? WHERE sku = ? "; 
     
               statement = conn.prepareStatement(update);
               statement.setInt(1, Stock);
               statement.setString(2, id);
               result = statement.executeUpdate();}
               else {
            	   System.out.println("\n>>> Inputkan Stok yang Benar <<<");
               }
           }
           else {
           	System.out.println("\n>>> Data Tidak Tersedia <<<");
           }
           
           }catch (SQLException e) {
				System.out.println(e.getMessage());
			}
                 
       return result;	
	 }
	 public ArrayList<Barang> getAll() {
	    	
			ArrayList<Barang> listBarang = new ArrayList<>();
	    	Statement statement;
	    	
	    	try {
				statement = conn.createStatement();
				
				String sql = "SELECT * FROM barang";
				
				ResultSet rs = statement.executeQuery(sql);
			
				
				
				while(rs.next()) {
					
					Barang barang = new Barang(
							
							rs.getString("sku"),
							rs.getString("nama"),
							rs.getInt("stock"),
							rs.getInt("harga_beli"),
							rs.getInt("harga_jual")
							
							);
					listBarang.add(barang);
				}
				
				
				if(listBarang.isEmpty()) System.out.println("Barang Kosong");
				 
	    		
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
	    	
	    	return listBarang;
	    }
}
