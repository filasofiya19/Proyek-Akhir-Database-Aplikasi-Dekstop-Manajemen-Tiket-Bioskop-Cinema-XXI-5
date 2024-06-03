/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package tiketonline;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JToggleButton;
import static tiketonline.TiketBioskop.kr1;
import static tiketonline.TiketBioskop.kr2;
import static tiketonline.TiketBioskop.kv1;
import static tiketonline.TiketBioskop.kv2;

/**
 *
 * @author fila sofiyati
 */
public class FrameKursi extends javax.swing.JFrame {
   String buttonTerpilih, buttonMana;
    int cmbfilm, cmbTanggal, cmbWaktu; 
     String selectedKategori;
    String kursiTerpilih;
    public FrameKursi(String buttonMana ,String dbkursi , int cmbfilm, int cmbTanggal, int cmbWaktu, String selectedKategori) {
        
        initComponents();
        setTitle("Pilihan Kursi");
        this.setLocationRelativeTo(null);
        A1.setActionCommand("A1");A2.setActionCommand("A2");A3.setActionCommand("A3");A4.setActionCommand("A4");A5.setActionCommand("A5");A6.setActionCommand("A6");A7.setActionCommand("A7");A8.setActionCommand("A8");A9.setActionCommand("A9");A10.setActionCommand("A10");
        B1.setActionCommand("B1");B2.setActionCommand("B2");B3.setActionCommand("B3");B4.setActionCommand("B4");B5.setActionCommand("B5");B6.setActionCommand("B6");B7.setActionCommand("B7");B8.setActionCommand("B8");B9.setActionCommand("B9");B10.setActionCommand("B10");
        C1.setActionCommand("C1");C2.setActionCommand("C2");C3.setActionCommand("C3");C4.setActionCommand("C4");C5.setActionCommand("C5");C6.setActionCommand("C6");C7.setActionCommand("C7");C8.setActionCommand("C8");C9.setActionCommand("C9");C10.setActionCommand("C10");
        D1.setActionCommand("D1");D2.setActionCommand("D2");D3.setActionCommand("D3");D4.setActionCommand("D4");D5.setActionCommand("D5");D6.setActionCommand("D6");D7.setActionCommand("D7");D8.setActionCommand("D8");D9.setActionCommand("D9");D10.setActionCommand("D10");
        E1.setActionCommand("E1");E2.setActionCommand("E2");E3.setActionCommand("E3");E4.setActionCommand("E4");E5.setActionCommand("E5");E6.setActionCommand("E6");E7.setActionCommand("E7");E8.setActionCommand("E8");E9.setActionCommand("E9");E10.setActionCommand("E10");


        buttonGroup1.add(A1);buttonGroup1.add(A2);buttonGroup1.add(A3);buttonGroup1.add(A4);buttonGroup1.add(A5);buttonGroup1.add(A6);buttonGroup1.add(A7);buttonGroup1.add(A8);buttonGroup1.add(A9);buttonGroup1.add(A10);
        buttonGroup1.add(B1);buttonGroup1.add(B2);buttonGroup1.add(B3);buttonGroup1.add(B4);buttonGroup1.add(B5);buttonGroup1.add(B6);buttonGroup1.add(B7);buttonGroup1.add(B8);buttonGroup1.add(B9);buttonGroup1.add(B10);
        buttonGroup1.add(C1);buttonGroup1.add(C2);buttonGroup1.add(C3);buttonGroup1.add(C4);buttonGroup1.add(C5);buttonGroup1.add(C6);buttonGroup1.add(C7);buttonGroup1.add(C8);buttonGroup1.add(C9);buttonGroup1.add(C10);
        buttonGroup1.add(D1);buttonGroup1.add(D2);buttonGroup1.add(D3);buttonGroup1.add(D4);buttonGroup1.add(D5);buttonGroup1.add(D6);buttonGroup1.add(D7);buttonGroup1.add(D8);buttonGroup1.add(D9);buttonGroup1.add(D10);
        buttonGroup1.add(E1);buttonGroup1.add(E2);buttonGroup1.add(E3);buttonGroup1.add(E4);buttonGroup1.add(E5);buttonGroup1.add(E6);buttonGroup1.add(E7);buttonGroup1.add(E8);buttonGroup1.add(E9);buttonGroup1.add(E10);
       
        this.cmbfilm = cmbfilm;
        this.cmbTanggal = cmbTanggal;
        this.cmbWaktu = cmbWaktu;
        this.selectedKategori = selectedKategori;
        this.buttonMana = buttonMana;
        this.buttonTerpilih = dbkursi;
         /* if(kursi.length() < 4 & kursi.length() > 0){
            try {   
                setAvailable(kursi,0);
            } catch (SQLException ex) {
                Logger.getLogger(FrameKursi.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
  
    private boolean isAvailable (String kursi) throws SQLException{
        Connection c = KoneksiDB.getKoneksi();
        Statement s = c.createStatement();
        String sql = "SELECT " + kursi + " from kursi WHERE id_kursi = '" + this.cmbfilm + "-" + this.cmbTanggal + "-" + this.cmbWaktu + "'";
        ResultSet rs = s.executeQuery(sql);
        while(rs.next()){
            return rs.getInt(1) == 0;
        }
        JOptionPane.showConfirmDialog(null, "Data Kursi Tidak Ditemukan");
        return false;
    }
    
    private void setAvailable(String kursi, int available) throws SQLException{
        Connection c = KoneksiDB.getKoneksi();
        Statement s = c.createStatement();
        String sql = "UPDATE kursi SET " + kursi + "= "+ available + " WHERE id_kursi = '" + this.cmbfilm + "-" + this.cmbTanggal + "-" + this.cmbWaktu + "'";
        s.execute(sql);*/
    }
    
    public void setButtonMana(String buttonMana) {
        this.buttonMana = buttonMana;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel4 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        A1 = new javax.swing.JToggleButton();
        B1 = new javax.swing.JToggleButton();
        C1 = new javax.swing.JToggleButton();
        D1 = new javax.swing.JToggleButton();
        E1 = new javax.swing.JToggleButton();
        A2 = new javax.swing.JToggleButton();
        B2 = new javax.swing.JToggleButton();
        C2 = new javax.swing.JToggleButton();
        D2 = new javax.swing.JToggleButton();
        E2 = new javax.swing.JToggleButton();
        E3 = new javax.swing.JToggleButton();
        A3 = new javax.swing.JToggleButton();
        B3 = new javax.swing.JToggleButton();
        D3 = new javax.swing.JToggleButton();
        C3 = new javax.swing.JToggleButton();
        E4 = new javax.swing.JToggleButton();
        A4 = new javax.swing.JToggleButton();
        D4 = new javax.swing.JToggleButton();
        B4 = new javax.swing.JToggleButton();
        C4 = new javax.swing.JToggleButton();
        A5 = new javax.swing.JToggleButton();
        C5 = new javax.swing.JToggleButton();
        E5 = new javax.swing.JToggleButton();
        B5 = new javax.swing.JToggleButton();
        D5 = new javax.swing.JToggleButton();
        B6 = new javax.swing.JToggleButton();
        A6 = new javax.swing.JToggleButton();
        C6 = new javax.swing.JToggleButton();
        E6 = new javax.swing.JToggleButton();
        D6 = new javax.swing.JToggleButton();
        B7 = new javax.swing.JToggleButton();
        C7 = new javax.swing.JToggleButton();
        A7 = new javax.swing.JToggleButton();
        D7 = new javax.swing.JToggleButton();
        E7 = new javax.swing.JToggleButton();
        D8 = new javax.swing.JToggleButton();
        B8 = new javax.swing.JToggleButton();
        A8 = new javax.swing.JToggleButton();
        E8 = new javax.swing.JToggleButton();
        C8 = new javax.swing.JToggleButton();
        E9 = new javax.swing.JToggleButton();
        C9 = new javax.swing.JToggleButton();
        B9 = new javax.swing.JToggleButton();
        D9 = new javax.swing.JToggleButton();
        A9 = new javax.swing.JToggleButton();
        C10 = new javax.swing.JToggleButton();
        B10 = new javax.swing.JToggleButton();
        D10 = new javax.swing.JToggleButton();
        E10 = new javax.swing.JToggleButton();
        A10 = new javax.swing.JToggleButton();
        save = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 0, 153));

        A1.setText("A1");

        B1.setText("B1");

        C1.setText("C1");

        D1.setText("D1");

        E1.setText("E1");

        A2.setText("A2");
        A2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                A2ActionPerformed(evt);
            }
        });

        B2.setText("B2");

        C2.setText("C2");

        D2.setText("D2");

        E2.setText("E2");

        E3.setText("E3");

        A3.setText("A3");

        B3.setText("B3");

        D3.setText("D3");

        C3.setText("C3");

        E4.setText("E4");

        A4.setText("A4");

        D4.setText("D4");

        B4.setText("B4");

        C4.setText("C4");

        A5.setText("A5");

        C5.setText("C5");

        E5.setText("E5");

        B5.setText("B5");

        D5.setText("D5");

        B6.setText("B6");

        A6.setText("A6");

        C6.setText("C6");

        E6.setText("E6");

        D6.setText("D6");

        B7.setText("B7");

        C7.setText("C7");

        A7.setText("A7");

        D7.setText("D7");

        E7.setText("E7");

        D8.setText("D8");

        B8.setText("B8");

        A8.setText("A8");

        E8.setText("E8");

        C8.setText("C8");

        E9.setText("E9");

        C9.setText("C9");

        B9.setText("B9");

        D9.setText("D9");

        A9.setText("A9");

        C10.setText("C10");

        B10.setText("B10");

        D10.setText("D10");

        E10.setText("E10");

        A10.setText("A10");

        save.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/save.png"))); // NOI18N
        save.setText("Simpan");
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator2)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator3)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(D1, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(A1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(B1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(C1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(E1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(D2, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(A2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(B2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(C2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(E2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(D3, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(A3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(B3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(C3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(E3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(D4, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(A4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(B4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(C4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(E4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(D5, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(A5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(B5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(C5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(E5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(D6, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(A6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(B6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(C6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(E6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(D7, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(A7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(B7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(C7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(E7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(D8, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(A8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(B8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(C8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(E8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(save)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(D9, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(A9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(B9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(C9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(E9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(A10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(B10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(C10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(0, 3, Short.MAX_VALUE))
                                    .addComponent(E10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(D10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(A1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(B1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(C1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(D1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(E1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(A2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(B2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(C2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(D2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(E2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(A3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(B3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(C3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(D3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(E3))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(A4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(B4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(C4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(D4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(E4))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(A5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(B5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(C5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(D5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(E5))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(A6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(B6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(C6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(D6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(E6))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(A7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(B7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(C7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(D7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(E7))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(A8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(B8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(C8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(D8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(E8))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(A9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(B9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(C9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(D9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(E9))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(A10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(B10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(C10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(D10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(E10)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(save)
                .addContainerGap(10, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(0, 51, 153));
        jPanel2.setForeground(new java.awt.Color(51, 255, 255));

        jLabel1.setFont(new java.awt.Font("Rockwell", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Silahkan Pilih Kursi");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 535, Short.MAX_VALUE)
            .addComponent(jSeparator4)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );

        jPanel5.setBackground(new java.awt.Color(204, 204, 204));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Layar Bioskop");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(114, 114, 114)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void A2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_A2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_A2ActionPerformed

    private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed
        try {
            // Mendapatkan pilihan kursi
            String kursiTerpilih = buttonGroup1.getSelection().getActionCommand();
            // Mendapatkan pilihan kategori
            String kategoriTerpilih = selectedKategori;
            Connection connection = KoneksiDB.getKoneksi();
            // Memeriksa keberadaan kursi dengan kombinasi yang sama
            String checkSql = "SELECT COUNT(*) FROM dbkursi WHERE kursi = ? AND kategori = ? AND id_film = ? AND id_tanggal = ? AND id_waktu = ?";
            PreparedStatement checkStatement = connection.prepareStatement(checkSql);
            checkStatement.setString(1, kursiTerpilih);
            checkStatement.setString(2, kategoriTerpilih);
            checkStatement.setInt(3, this.cmbfilm);
            checkStatement.setInt(4, this.cmbTanggal);
            checkStatement.setInt(5, this.cmbWaktu);
            ResultSet resultSet = checkStatement.executeQuery();
            resultSet.next();
            int kursiCount = resultSet.getInt(1);
            if (kursiCount > 0) {
             JOptionPane.showMessageDialog(this, "Kursi tersebut sudah dipilih sebelumnya dan tidak dapat dipilih lagi.", "Peringatan", JOptionPane.WARNING_MESSAGE);   
                return;
            }

            // Menyimpan data kursi ke dalam database
            String sql = "INSERT INTO dbkursi (id_kursi, kursi, kategori, status, id_film, id_tanggal, id_waktu) VALUES (DEFAULT, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            // Pastikan kursiTerpilih tidak null atau kosong
            if (kursiTerpilih == null || kursiTerpilih.isEmpty()) {
                System.out.println("Pilih kursi terlebih dahulu.");
                return;
            }

            // Mengatur parameter pada pernyataan SQL
            statement.setString(1, kursiTerpilih);
            statement.setString(2, kategoriTerpilih);
            statement.setString(3, "dipilih"); // Mengatur status kursi menjadi "dipilih"
            statement.setInt(4, this.cmbfilm);
            statement.setInt(5, this.cmbTanggal);
            statement.setInt(6, this.cmbWaktu);

            // Menjalankan pernyataan SQL untuk menyimpan data
            statement.executeUpdate();

            // Mendapatkan ID kursi yang baru saja disimpan
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int kursiId = generatedKeys.getInt(1);
                System.out.println("Kursi berhasil disimpan dengan ID: " + kursiId);
            }
            System.out.println("Data kursi berhasil disimpan ke dalam database.");
             if (buttonMana.equals("kv1")) {
        TiketBioskop.kv1.setText(kursiTerpilih);
    } else if (buttonMana.equals("kv2")) {
        TiketBioskop.kv2.setText(kursiTerpilih);
    } else if (buttonMana.equals("kr1")) {
        TiketBioskop.kr1.setText(kursiTerpilih);
    } else if (buttonMana.equals("kr2")) {
        TiketBioskop.kr2.setText(kursiTerpilih);
    }
    
    this.dispose();


            // Menutup koneksi dan pernyataan SQL
            generatedKeys.close();
            //statement.close();
            //checkStatement.close();
            //connection.close();

            // Menampilkan pesan sukses
            

            // Menonaktifkan pilihan kursi yang sudah dipilih
            buttonGroup1.clearSelection();
            // Mengatur status kursi yang sudah dipilih menjadi tidak tersedia di antarmuka pengguna
             
        } catch (SQLException e) {
            // Menampilkan pesan kesalahan jika terjadi error
            System.out.println("Terjadi kesalahan saat menyimpan data kursi: " + e.getMessage());
        }
    
       /* try {
            buttonTerpilih = ((JToggleButton.ToggleButtonModel)buttonGroup1.getSelection()).getActionCommand();
            
            if(isAvailable(buttonTerpilih)){
                setAvailable(buttonTerpilih, 1);
                switch(buttonMana){
                    case "kd1":
                        TiketBioskop.kv1.setText(buttonTerpilih);
                        break;
                    case "kd2":
                        TiketBioskop.kv2.setText(buttonTerpilih);
                        break;
                    case "ka1":
                        TiketBioskop.kr1.setText(buttonTerpilih);
                        break;
                    case "ka2":
                        TiketBioskop.kr2.setText(buttonTerpilih);
                        break;
                    default:
                        break;                    
                }
                
                this.dispose();
            } else {
                JOptionPane.showConfirmDialog(null, "Kursi " + buttonTerpilih + " tidak tersedia", "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FrameKursi.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }//GEN-LAST:event_saveActionPerformed
    
 
    /**
     * @param args the command line arguments
     */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton A1;
    private javax.swing.JToggleButton A10;
    private javax.swing.JToggleButton A2;
    private javax.swing.JToggleButton A3;
    private javax.swing.JToggleButton A4;
    private javax.swing.JToggleButton A5;
    private javax.swing.JToggleButton A6;
    private javax.swing.JToggleButton A7;
    private javax.swing.JToggleButton A8;
    private javax.swing.JToggleButton A9;
    private javax.swing.JToggleButton B1;
    private javax.swing.JToggleButton B10;
    private javax.swing.JToggleButton B2;
    private javax.swing.JToggleButton B3;
    private javax.swing.JToggleButton B4;
    private javax.swing.JToggleButton B5;
    private javax.swing.JToggleButton B6;
    private javax.swing.JToggleButton B7;
    private javax.swing.JToggleButton B8;
    private javax.swing.JToggleButton B9;
    private javax.swing.JToggleButton C1;
    private javax.swing.JToggleButton C10;
    private javax.swing.JToggleButton C2;
    private javax.swing.JToggleButton C3;
    private javax.swing.JToggleButton C4;
    private javax.swing.JToggleButton C5;
    private javax.swing.JToggleButton C6;
    private javax.swing.JToggleButton C7;
    private javax.swing.JToggleButton C8;
    private javax.swing.JToggleButton C9;
    private javax.swing.JToggleButton D1;
    private javax.swing.JToggleButton D10;
    private javax.swing.JToggleButton D2;
    private javax.swing.JToggleButton D3;
    private javax.swing.JToggleButton D4;
    private javax.swing.JToggleButton D5;
    private javax.swing.JToggleButton D6;
    private javax.swing.JToggleButton D7;
    private javax.swing.JToggleButton D8;
    private javax.swing.JToggleButton D9;
    private javax.swing.JToggleButton E1;
    private javax.swing.JToggleButton E10;
    private javax.swing.JToggleButton E2;
    private javax.swing.JToggleButton E3;
    private javax.swing.JToggleButton E4;
    private javax.swing.JToggleButton E5;
    private javax.swing.JToggleButton E6;
    private javax.swing.JToggleButton E7;
    private javax.swing.JToggleButton E8;
    private javax.swing.JToggleButton E9;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JButton save;
    // End of variables declaration//GEN-END:variables

    
}
