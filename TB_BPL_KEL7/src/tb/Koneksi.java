package tb;

import java.sql.*;

public class Koneksi {
	public static java.sql.Connection koneksi;

	public static Connection koneksiSQL() throws SQLException{
	try {
		String url = "jdbc:mysql://localhost/tbbpl?serverTimezone=Asia/Jakarta";
		String user = "root";
		String pass = "";
		koneksi = DriverManager.getConnection(url, user, pass);
		}catch(SQLException e){
			System.out.println("Koneksi Database gagal");
		}
	return koneksi;
	
	}
}
