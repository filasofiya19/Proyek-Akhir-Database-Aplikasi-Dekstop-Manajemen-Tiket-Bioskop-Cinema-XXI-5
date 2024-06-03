/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiketonline;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author ACER
 */
public class KoneksiDB {
    //membuat variable bertipe Connection
    private static Connection koneksi;
    
    public static Connection getKoneksi(){
        if (koneksi == null){
            try{
                String url = "jdbc:mysql://localhost:3306/cinemaxxi_db";
                String username = "root";
                String password = "";
                
                DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
                
                koneksi = DriverManager.getConnection(url, username, password);
            } catch (SQLException e){
                System.out.println("Gagal membuat koneksi");
            }
        }
        return koneksi;
    }

    Connection getKoneksiDB() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    Statement createStatement() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    void close() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    Connection getConnection() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    void connect() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    DefaultComboBoxModel retrieve() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    boolean update(String id, String text) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
