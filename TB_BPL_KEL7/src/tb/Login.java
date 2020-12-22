package tb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

public class Login {
	static Connection conn;
	
	static String kataAcak="";
	static String USERR=null, PASS=null;
	static String rsUSERNAME=null, rsPASSWORD=null;
	
	public static void login() throws SQLException {
			
		Scanner input = new Scanner (System.in);
		conn = Koneksi.koneksiSQL();
		Statement stmt = conn.createStatement();
		
		String sqlupdate = "UPDATE tbbpl SET password=?  where username=?";
		int salahMaksimal=0,indikatorLogin = 0;
		
		
		while(true && salahMaksimal<3) 
		{
		System.out.println("=========================");
		System.out.println("=========LOG IN==========");
		System.out.println("=========================");
		System.out.print("MASUKKAN USERNAME: ");
		USERR = input.nextLine();
		
		System.out.print("MASUKKAN PASSWORD: ");
		PASS = input.nextLine();
		System.out.println("=========================");
		try 
		{
			ResultSet rs = stmt.executeQuery("SELECT username, password FROM user WHERE username= '" + USERR + "';"); 
			if(rs.next()) 
			{ //Perbaris
			rsUSERNAME = rs.getString("username");
			rsPASSWORD = rs.getString("password");
			}
			
			if(USERR.equals(rsUSERNAME))
			{
				if(PASS.equals(rsPASSWORD))
				{
					
				System.out.println(">>> Berhasil Log in <<<");
				String sql2 = "UPDATE user SET login_terakhir=? WHERE username=?";
				PreparedStatement pstT = conn.prepareStatement(sql2);
				Timestamp timestamp = new Timestamp(new Date().getTime());
				pstT.setTimestamp(1, timestamp);
				pstT.setString(2, USERR);
				pstT.executeUpdate();
				MainMenu.mainMenu();
				}
				else
				{
					salahMaksimal=salahMaksimal+1;
					System.out.println("\nPERINGATAN: PASSWORD SALAH "+ salahMaksimal + " KALI\n");
					if(salahMaksimal==3)
					{
						salahMaksimal=0;
						String characters = "QWERTYUIOPASDFGHJKLZXCVBNM123789456-=[]',./~`!@#$%^&*(";
						int length = 10;
						Random acak= new Random();
						char[] text= new char [length];
						for(int i=0; i<length; i++)
						{
							text[i]= characters.charAt(acak.nextInt(characters.length()));
						}
						for(int i=0; i<text.length; i++)
						{
							kataAcak+=text[i];
						}
						
						try 
						{
							//RESET PASSWORD
							String sqlReset = "UPDATE user SET password=? WHERE username=?";
							PreparedStatement update = conn.prepareStatement(sqlReset);
							
							System.out.println("USERNAME: "+USERR);
							System.out.println("PASSWORD: "+kataAcak);
							
							update.setString(1, kataAcak);
							update.setString(2, USERR);
							update.executeUpdate();
							System.out.println("PASSWORD TELAH DIRESET.\n");
						}
						catch (Exception e) 
						{
							System.out.println("USERNAME TIDAK ADA");
							System.out.println("PASSWORD GAGAL DIRESET");
						}
						
					}
				}
			}
			else
			{
				System.out.println("\nMASUKKAN USERNAME YANG BENAR!\n");
			}
		}
		
		catch(SQLException e) 
		{
		System.out.println("KONEKSI DATABASE GAGAL");
		e.printStackTrace();
		}
		
		}
	}
}
