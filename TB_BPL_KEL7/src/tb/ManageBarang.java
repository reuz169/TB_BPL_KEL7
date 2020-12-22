package tb;

import java.util.ArrayList;
import java.util.Scanner;
import java.sql.*;

public class ManageBarang {
	
	
    Scanner scanner = new Scanner(System.in);
    
    Connection connection;

public ManageBarang() {
		
	try {
		 connection = Koneksi.koneksiSQL();
			
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
	}

public ArrayList<Barang> getAll() {
    	
       ArrayList<Barang> listBarang = new ArrayList<>();
       Statement statement;
    	
   try {
			statement = connection.createStatement();
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
    
public int tambahData() {
    
    Integer result = 0;
        
        System.out.print("SKU         : ");
        String sku = scanner.nextLine();
        
        
        System.out.print("Nama Barang : ");
        String nama = scanner.nextLine();
        
        
        System.out.print("Stock       : ");
        Integer stok = Integer.parseInt(scanner.nextLine());
        
        
        System.out.print("Harga Beli  : ");
        Integer hBeli = Integer.parseInt(scanner.nextLine());
        
        
        System.out.print("Harga Jual  : ");
        Integer hJual = Integer.parseInt(scanner.nextLine());
       
        
        if(stok<0 && hBeli<0 && hJual<0) {
        System.out.println("\n--------------------------------------------------------------------------------------------------");
        System.out.println("---------------------------------->> Inputkan Angka Dengan Benar <<--------------------------------");
        tambahData() ;
        }
        	else if (hJual<hBeli) { 
        		System.out.println("\n--------------------------------------------------------------------------------------------------");
        		System.out.println("---------------->> Tidak Valid !! Harga Jual harus lebih Besar dari harga Beli <<-----------------");	
        		System.out.println("--------------------------------------------------------------------------------------------------");
        		tambahData() ; 
        	}
        	else {
     String sql = "INSERT INTO barang (sku, nama, stock, harga_beli, harga_jual) VALUES(?,?,?,?,?)";
                
     try {
         
    	 PreparedStatement statement = (PreparedStatement) connection.prepareStatement(sql);
           statement.setString(1, sku);
           statement.setString(2, nama);
           statement.setInt   (3, stok);
           statement.setInt   (4, hBeli);
           statement.setInt   (5, hJual);
           result = statement.executeUpdate();
                    
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
        }
        return result;
    }

public ArrayList<Barang> cari() {
    	
    	ArrayList<Barang> listBarang = new ArrayList<>();
    	if(!getAll().isEmpty()) {
    	
        System.out.print("\nInputkan SKU : ");
        String keyword = scanner.nextLine();
        
        PreparedStatement statement;
  try {
	  
	String ambilSKU="";
	String sql = "SELECT * FROM barang WHERE sku LIKE ?";
	statement = connection.prepareStatement(sql);
	statement.setString(1, "%"+keyword+"%");
			
	ResultSet rs = statement.executeQuery();
		        
	if(rs.next()){
		   
		Barang barang = new Barang(
							
		ambilSKU= rs.getString("sku"),
		rs.getString("nama"),
		rs.getInt("stock"),
		rs.getInt("harga_beli"),
		rs.getInt("harga_jual")
		);
			   listBarang.add(barang);
		     }
			
} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	}
    }
    return listBarang;
      
    	
    }
    
public int edit() {
    	
    Integer result=0;
            
    System.out.print("\nSKU Barang Diedit ? :  ");
    String id = scanner.nextLine();
            
    String sql = "SELECT * FROM barang WHERE sku = ?";
            
    try {
           
    	PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, id);
        ResultSet rs = statement.executeQuery();
          
    if(rs.next()){
                
        System.out.print("SKU ["+rs.getString("sku")+"]: ");
        String sku = scanner.nextLine();
                
    
        System.out.print("Nama ["+rs.getString("nama")+"]: ");
        String nama = scanner.nextLine();
                
    
        System.out.print("Harga Beli ["+rs.getInt("harga_beli")+"]: ");
        Integer hBeli = Integer.parseInt(scanner.nextLine());
                
    
        System.out.print("Harga Jual ["+rs.getInt("harga_jual")+"]: ");
        Integer hJual = Integer.parseInt(scanner.nextLine());
                
    if(hBeli<0 && hJual<0) {
    	System.out.println("\n--------------------------------------------------------------------------------------------------");       	
    	System.out.println("---------------------------------->> Inputkan Angka Dengan Benar <<--------------------------------");
        edit() ;
        }
    
    else if (hJual<hBeli) {
    	System.out.println("\n--------------------------------------------------------------------------------------------------");
    	System.out.println("----------------------->> Harga Jual harus lebih Besar dari harga Beli <<--------------------------");
       
        edit() ; 
                    		
        String update = "UPDATE barang SET sku = ? , nama = ?, harga_beli = ?, harga_jual = ? WHERE sku = ? "; 
                
         statement = connection.prepareStatement(update);
         statement.setString(1, sku);
         statement.setString(2, nama);
         statement.setInt   (3, hBeli);
         statement.setInt   (4, hJual);
         statement.setString(5, id);
                
         result = statement.executeUpdate();
    }
    else{ 
    	System.out.println("\n--------------------------------------------------------------------------------------------------");
    	System.out.println("---------------------------------->> Inputkan Angka Dengan Benar <<--------------------------------");
          
    }
         }
    else {
    	  System.out.println("\n--------------------------------------------------------------------------------------------------");
	      System.out.println("-------------------------------------->> Data Tidak Tersedia <<------------------------------------");
            
    }
    
    }catch (SQLException e) {
		   System.out.println(e.getMessage());
	}
                  
     return result;
    	
    }
 
public int hapus() {
    	
   Integer result = 0;
    		
    try {
          System.out.print("\nSKU Barang Dihapus ? ");
          String id = scanner.nextLine();
              
          String sql = "DELETE FROM barang WHERE sku= ?";
          PreparedStatement statement = connection.prepareStatement(sql);
          statement.setString(1, id);
          result = statement.executeUpdate();
            
    }catch(SQLException e){
    	  System.out.println("\n--------------------------------------------------------------------------------------------------");
    	  System.out.println("-------------------------------------->> Data Gagal Dihapus <<-------------------------------------");
        
          }
    	  
    return result;
    }
    

}

