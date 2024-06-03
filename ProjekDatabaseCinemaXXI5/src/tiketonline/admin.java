/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package tiketonline;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import tiketonline.KoneksiDB;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.RowFilter;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import tiketonline.Tiket;
import tiketonline.staff;

/**
 *
 * @author fila sofiyati
 */
public class admin extends javax.swing.JFrame {
    //private String adminUsername;
     private String adminId;
     private String adminUsername;
   
   // public void setAdminUsername(String username) {
   //     this.adminUsername = username;
    //}
    Connection conn=null;
    ResultSet rs=null;
    PreparedStatement pst=null;
    String crudVerif="";
    private DefaultTableModel model = null;
    
     public void setAdminId(String adminId) {
        this.adminId = adminId;
        lbl_admin.setText(" " + adminId);
    }

    public String getAdminId() {
        return adminId;
    }
    public void setAdminUsername(String adminUsername) {
        this.adminUsername = adminUsername ;
    }

    public String getAdminUsername() {
        return adminUsername;
    }
    public void updateAdminIdText() {
    
}
    
    public admin(){
        initComponents();
         
        this.setLocationRelativeTo(null);
        setMenuAdmin();
      
        lbl_admin.setText(getAdminId());

       
        tabel1.getTableHeader().setFont(new Font("Perpetua", Font.BOLD,12));
        tabel1.addMouseListener(new MouseAdapter() {
         
            public void mouseClicked(MouseEvent evt) {
    int row = tabel1.getSelectedRow();
    TableModel model = tabel1.getModel();
    String id = model.getValueAt(row, 0).toString();
    String film = model.getValueAt(row, 1).toString();
    String tanggal = model.getValueAt(row, 2).toString();
    String waktu = model.getValueAt(row, 3).toString();
    String tiketvip = model.getValueAt(row, 4).toString();
    String tiketreg = model.getValueAt(row, 5).toString();
    String totalbayar = model.getValueAt(row, 6).toString();
    String kursivip1 = model.getValueAt(row, 7).toString();
    String kursivip2 = model.getValueAt(row, 8).toString();
    String kursireg1 = model.getValueAt(row, 9).toString();
    String kursireg2 = model.getValueAt(row, 10).toString();
    String kode = model.getValueAt(row, 11).toString();
    String status = model.getValueAt(row, 12).toString();

    if (status.equals("terverifikasi")) {
        verif.setSelected(true);
    } else {
        no.setSelected(true);
    }

    id_txt.setText(id);
    film_txt.setText(film);
    tanggal_txt.setText(tanggal);
    waktu_txt.setText(waktu);
    tiketvip_txt.setText(tiketvip);
    tiketreg_txt.setText(tiketreg);
    tbayar_txt.setText(totalbayar);
    kursivip1_txt.setText(kursivip1);
    kursivip2_txt.setText(kursivip2);
    kurreg1_txt.setText(kursireg1);
    kurreg2_txt.setText(kursireg2);
    kode_txt.setText(kode);
    status_txt.setText(status);
}
            });

    simpan_btn.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent evt) {
        String id = id_txt.getText();
        String film = film_txt.getText();
        String tanggal = tanggal_txt.getText();
        String waktu = waktu_txt.getText();
        String tiketvip = tiketvip_txt.getText();
        String tiketreg = tiketreg_txt.getText();
        String totalbayar = tbayar_txt.getText();
        String kursivip1 = kursivip1_txt.getText();
        String kursivip2 = kursivip2_txt.getText();
        String kursireg1 = kurreg1_txt.getText();
        String kursireg2 = kurreg2_txt.getText();
        String kode = kode_txt.getText();
        String status = "belum terverifikasi";
        if (verif.isSelected()) {
            status = "terverifikasi";
        }

        int row = tabel1.getSelectedRow();
        TableModel model = tabel1.getModel();
        model.setValueAt(id, row, 0);
        model.setValueAt(film, row, 1);
        model.setValueAt(tanggal, row, 2);
        model.setValueAt(waktu, row, 3);
        model.setValueAt(tiketvip, row, 4);
        model.setValueAt(tiketreg, row, 5);
        model.setValueAt(totalbayar, row, 6);
        model.setValueAt(kursivip1, row, 7);
        model.setValueAt(kursivip2, row, 8);
        model.setValueAt(kursireg1, row, 9);
        model.setValueAt(kursireg2, row, 10);
        model.setValueAt(kode, row, 11);
        model.setValueAt(status, row, 12);

        try {
            conn = KoneksiDB.getKoneksi();
            String sql = "UPDATE pembelian SET film=?, tanggal=?, waktu=?, tik_3D=?, tik_2D=?, total_bayar=?, kursi_1_3D=?, kursi_2_3D=?, kursi_1_2D=?, kursi_2_2D=?, Kode_bk=?, St_verif=? WHERE Id_pembelian=?";
                    pst = conn.prepareStatement(sql);
        pst.setString(1, film);
        pst.setString(2, tanggal);
        pst.setString(3, waktu);
        pst.setString(4, tiketvip);
        pst.setString(5, tiketreg);
        pst.setString(6, totalbayar);
        pst.setString(7, kursivip1);
        pst.setString(8, kursivip2);
        pst.setString(9, kursireg1);
        pst.setString(10, kursireg2);
        pst.setString(11, kode);
        pst.setString(12, status);
        pst.setString(13, id);
        pst.executeUpdate();
                
        JOptionPane.showMessageDialog(null, "Data berhasil diperbarui");
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, ex.getMessage());
    }
}
});
            
      
    }
            
     private void setMenuAdmin(){
        
         try {
            // Buat koneksi ke database
            Connection conn = KoneksiDB.getKoneksi();
            // Buat statement untuk mengambil data dari tabel1
            String sql = "SELECT Id_pembelian, film, tanggal, waktu, tik_3D, tik_2D, total_bayar, kursi_1_3D, kursi_2_3D, kursi_1_2D, kursi_2_2D, Kode_bk, St_verif FROM pembelian";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            // Buat model untuk tabel
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("ID");
            model.addColumn("Film");
            model.addColumn("Tanggal");
            model.addColumn("Waktu");
            model.addColumn("tiket 3D");
            model.addColumn("tiket 2D");
            model.addColumn("total bayar");
            model.addColumn("kursi 1 Film 3D");
            model.addColumn("kursi 2 Film 3D");
            model.addColumn("kursi 1 Film 2D");
            model.addColumn("kursi 2 Film 2D");
            model.addColumn("Kode booking");
            model.addColumn("Status");
            
            
            // Tambahkan data dari ResultSet ke model
            while (rs.next()) {
                Object[] row = new Object[13];
                row[0] = rs.getInt("Id_pembelian");
                row[1] = rs.getString("film");
                row[2] = rs.getString("tanggal");
                row[3] = rs.getString("waktu");
                row[4] = rs.getInt("tik_3D");
                row[5] = rs.getInt("tik_2D");
                row[6] = rs.getString("total_bayar");
                row[7] = rs.getString("kursi_1_3D");
                row[8] = rs.getString("kursi_2_3D");
                row[9] = rs.getString("kursi_1_2D");
                row[10] = rs.getString("kursi_2_2D");
                row[11] = rs.getString("Kode_bk");
                row[12] = rs.getString("St_verif");
                model.addRow(row);
            }
            
            // Tampilkan model pada JTable
            tabel1.setModel(model);
            
            // Tutup koneksi
            //rs.close();
            //stmt.close();
            //conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
     }
    public void search(String str){
        model = (DefaultTableModel) tabel1.getModel();
        TableRowSorter<DefaultTableModel> trs = new TableRowSorter<>(model);
        tabel1.setRowSorter(trs);
        trs.setRowFilter(RowFilter.regexFilter(str));
    }
    /**
     * Creates new form admin
     */
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // </editor-fold>
@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        buttonGroup5 = new javax.swing.ButtonGroup();
        buttonGroup6 = new javax.swing.ButtonGroup();
        buttonGroup7 = new javax.swing.ButtonGroup();
        buttonGroup8 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabel1 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        Exit = new javax.swing.JButton();
        tblsearch = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        penyuntinganjadwal = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        film_txt = new javax.swing.JLabel();
        tanggal_txt = new javax.swing.JLabel();
        waktu_txt = new javax.swing.JLabel();
        tiketvip_txt = new javax.swing.JLabel();
        tiketreg_txt = new javax.swing.JLabel();
        kursivip1_txt = new javax.swing.JLabel();
        kursivip2_txt = new javax.swing.JLabel();
        kurreg1_txt = new javax.swing.JLabel();
        kurreg2_txt = new javax.swing.JLabel();
        kode_txt = new javax.swing.JLabel();
        status_txt = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        id_txt = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        tbayar_txt = new javax.swing.JLabel();
        verif = new javax.swing.JRadioButton();
        no = new javax.swing.JRadioButton();
        simpan_btn = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        cetaktik = new javax.swing.JButton();
        lbl_admin = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 0, 153));

        tabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tabel1.setFont(new java.awt.Font("Perpetua", 1, 12)); // NOI18N
        tabel1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7", "Title 8", "Title 9", "Title 10", "Title 11"
            }
        ));
        tabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabel1);

        jPanel2.setBackground(new java.awt.Color(0, 0, 153));

        jLabel4.setFont(new java.awt.Font("Perpetua", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Data Pembelian Tiket Bioskop Cinema XXI 5");

        Exit.setFont(new java.awt.Font("Perpetua", 1, 14)); // NOI18N
        Exit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Exit application.png"))); // NOI18N
        Exit.setText("Keluar");
        Exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitActionPerformed(evt);
            }
        });

        tblsearch.setFont(new java.awt.Font("Perpetua", 1, 14)); // NOI18N
        tblsearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tblsearchActionPerformed(evt);
            }
        });
        tblsearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblsearchKeyReleased(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Perpetua", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Cari");

        penyuntinganjadwal.setFont(new java.awt.Font("Perpetua", 1, 14)); // NOI18N
        penyuntinganjadwal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/editabl.jpg"))); // NOI18N
        penyuntinganjadwal.setText("Penyuntingan Jadwal");
        penyuntinganjadwal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                penyuntinganjadwalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(tblsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(penyuntinganjadwal)
                .addGap(44, 44, 44)
                .addComponent(Exit)
                .addGap(95, 95, 95))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jSeparator1)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addGap(3, 3, 3)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(tblsearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(penyuntinganjadwal)
                        .addComponent(Exit)))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(new javax.swing.border.MatteBorder(null));
        jPanel3.setForeground(new java.awt.Color(255, 255, 204));

        jLabel3.setFont(new java.awt.Font("Perpetua", 1, 14)); // NOI18N
        jLabel3.setText("Film                              :");

        jLabel5.setFont(new java.awt.Font("Perpetua", 1, 14)); // NOI18N
        jLabel5.setText("Tanggal                       :");

        jLabel6.setFont(new java.awt.Font("Perpetua", 1, 14)); // NOI18N
        jLabel6.setText("Waktu                          :");

        jLabel7.setFont(new java.awt.Font("Perpetua", 1, 14)); // NOI18N
        jLabel7.setText("Tiket Film 3 D             :");

        jLabel8.setFont(new java.awt.Font("Perpetua", 1, 14)); // NOI18N
        jLabel8.setText("Tiket Film 2 D             :");

        jLabel9.setFont(new java.awt.Font("Perpetua", 1, 14)); // NOI18N
        jLabel9.setText("Kursi  1 Film 3 D    :");

        jLabel10.setFont(new java.awt.Font("Perpetua", 1, 14)); // NOI18N
        jLabel10.setText("Kursi 2 Film 3 D    :");

        jLabel11.setFont(new java.awt.Font("Perpetua", 1, 14)); // NOI18N
        jLabel11.setText("Kursi 1 Film 2 D    :");

        jLabel12.setFont(new java.awt.Font("Perpetua", 1, 14)); // NOI18N
        jLabel12.setText("Kursi 2 Film 2 D    :");

        jLabel13.setFont(new java.awt.Font("Perpetua", 1, 14)); // NOI18N
        jLabel13.setText("Kode                       :");

        jLabel14.setFont(new java.awt.Font("Perpetua", 1, 14)); // NOI18N
        jLabel14.setText("Status                           :");

        film_txt.setFont(new java.awt.Font("Perpetua", 1, 13)); // NOI18N
        film_txt.setText("-");
        film_txt.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tanggal_txt.setFont(new java.awt.Font("Perpetua", 1, 13)); // NOI18N
        tanggal_txt.setText("-");
        tanggal_txt.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        waktu_txt.setFont(new java.awt.Font("Perpetua", 1, 13)); // NOI18N
        waktu_txt.setText("-");
        waktu_txt.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tiketvip_txt.setFont(new java.awt.Font("Perpetua", 1, 13)); // NOI18N
        tiketvip_txt.setText("-                                                      ");
        tiketvip_txt.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tiketreg_txt.setFont(new java.awt.Font("Perpetua", 1, 13)); // NOI18N
        tiketreg_txt.setText("-                                                     ");
        tiketreg_txt.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        kursivip1_txt.setFont(new java.awt.Font("Perpetua", 1, 13)); // NOI18N
        kursivip1_txt.setText("-                  ");
        kursivip1_txt.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        kursivip2_txt.setFont(new java.awt.Font("Perpetua", 1, 13)); // NOI18N
        kursivip2_txt.setText("-                  ");
        kursivip2_txt.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        kurreg1_txt.setFont(new java.awt.Font("Perpetua", 1, 13)); // NOI18N
        kurreg1_txt.setText("-                   ");
        kurreg1_txt.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        kurreg2_txt.setFont(new java.awt.Font("Perpetua", 1, 13)); // NOI18N
        kurreg2_txt.setText("-                   ");
        kurreg2_txt.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        kode_txt.setFont(new java.awt.Font("Perpetua", 1, 13)); // NOI18N
        kode_txt.setText("-");
        kode_txt.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        status_txt.setFont(new java.awt.Font("Perpetua", 1, 13)); // NOI18N
        status_txt.setText("-");
        status_txt.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel15.setFont(new java.awt.Font("Perpetua", 1, 14)); // NOI18N
        jLabel15.setText("Id Pembelian              :");

        id_txt.setFont(new java.awt.Font("Perpetua", 1, 13)); // NOI18N
        id_txt.setText("-");
        id_txt.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel16.setFont(new java.awt.Font("Perpetua", 1, 14)); // NOI18N
        jLabel16.setText("Total bayar             :");

        tbayar_txt.setFont(new java.awt.Font("Perpetua", 1, 13)); // NOI18N
        tbayar_txt.setText("-");
        tbayar_txt.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        buttonGroup1.add(verif);
        verif.setFont(new java.awt.Font("Perpetua", 1, 14)); // NOI18N
        verif.setText("Verifikasi");

        buttonGroup1.add(no);
        no.setFont(new java.awt.Font("Perpetua", 1, 14)); // NOI18N
        no.setText("Tidak");
        no.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noActionPerformed(evt);
            }
        });

        simpan_btn.setFont(new java.awt.Font("Perpetua", 1, 14)); // NOI18N
        simpan_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/save.png"))); // NOI18N
        simpan_btn.setText("Simpan");
        simpan_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simpan_btnActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Perpetua", 1, 18)); // NOI18N
        jLabel17.setText("Detail Pembelian");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8))
                        .addGap(18, 18, 18)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tiketvip_txt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(waktu_txt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tanggal_txt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(film_txt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(id_txt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tiketreg_txt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel16)))
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel10)
                                .addComponent(jLabel9)
                                .addComponent(jLabel11)
                                .addComponent(jLabel12)
                                .addComponent(verif))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(status_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(237, 237, 237)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(kurreg2_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(kurreg1_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(kursivip2_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(kode_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tbayar_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(kursivip1_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(no))
                .addGap(19, 19, 19))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addGap(345, 345, 345))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(simpan_btn)
                        .addGap(177, 177, 177))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel17)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(tbayar_txt))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(kode_txt)
                            .addComponent(tanggal_txt)
                            .addComponent(jLabel5)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(id_txt)
                            .addComponent(jLabel15))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(film_txt)
                            .addComponent(jLabel3))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(waktu_txt)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(tiketvip_txt)
                    .addComponent(jLabel9)
                    .addComponent(kursivip1_txt))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(kursivip2_txt))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(kurreg1_txt)
                    .addComponent(jLabel11)
                    .addComponent(tiketreg_txt)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(kurreg2_txt)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(status_txt)
                    .addComponent(jLabel14)
                    .addComponent(verif)
                    .addComponent(no))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(simpan_btn)
                .addGap(45, 45, 45))
        );

        cetaktik.setFont(new java.awt.Font("Perpetua", 1, 14)); // NOI18N
        cetaktik.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/print.png"))); // NOI18N
        cetaktik.setText("Cetak Tiket");
        cetaktik.setEnabled(false);
        cetaktik.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cetaktikActionPerformed(evt);
            }
        });

        lbl_admin.setFont(new java.awt.Font("Perpetua", 1, 14)); // NOI18N
        lbl_admin.setForeground(new java.awt.Color(255, 255, 255));
        lbl_admin.setText("-                             ");

        jLabel2.setFont(new java.awt.Font("Perpetua", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Keterangan Admin :");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1307, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(215, 215, 215)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 824, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(lbl_admin, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(56, 56, 56)
                                .addComponent(cetaktik)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(lbl_admin))
                        .addGap(18, 18, 18)
                        .addComponent(cetaktik)
                        .addGap(16, 16, 16))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel1MouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_tabel1MouseClicked

    private void tblsearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblsearchKeyReleased
        // TODO add your handling code here:
        String searchString = tblsearch.getText();
        search (searchString);
    }//GEN-LAST:event_tblsearchKeyReleased

    private void ExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitActionPerformed
        // TODO add your handling code here:
        int a = JOptionPane.showConfirmDialog(null, "Apakah Anda yakin ingin keluar?", "Select", JOptionPane.YES_NO_OPTION);
        if(a == 0){
        setVisible(false);
         dispose();
        setAdminId(null);

        // Menampilkan pesan sukses dan kembali ke jendela login
        JOptionPane.showMessageDialog(null, "Berhasil Keluar Dari Akun Admin");
        
        // Membuka jendela LoginFrame
        staff loginFrame = new staff();
        loginFrame.setVisible(true);

        // Menutup jendela saat ini
        dispose();
        
        }
    }//GEN-LAST:event_ExitActionPerformed

        
    private void tblsearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tblsearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tblsearchActionPerformed
     
    private void penyuntinganjadwalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_penyuntinganjadwalActionPerformed
        // TODO add your handling code here:
       // 
        sunting pyt = new sunting();
         pyt.setAdminId(getAdminId()); // mengirim adminId ke frame sunting
        pyt.setAdminUsername(getAdminUsername());
        JOptionPane.showMessageDialog(null, "Masuk ke halaman penyuntingan");
        pyt.setVisible(true);
       
        
    }//GEN-LAST:event_penyuntinganjadwalActionPerformed

    private void simpan_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpan_btnActionPerformed
        // TODO add your handling code here:
        String insertdata = ("INSERT INTO pembelian (St_verif) VALUES (?)");
        PreparedStatement p = null;
        Connection conn = KoneksiDB.getKoneksi();
        
        if (verif.isSelected()){
           crudVerif="Terverifikasi" ;
           cetaktik.setEnabled(true);
        }else if(no.isSelected()){
            crudVerif="Tidak terverifikasi";
            cetaktik.setEnabled(false);
        }
        if(crudVerif.isEmpty()){
            JOptionPane.showMessageDialog(null, "Silahkan verifikasi dulu");
        }else{
            try{
                p= conn.prepareStatement(insertdata);
            
                p.setString (1, crudVerif );
                p.executeUpdate();
            p.close();
        } catch (SQLException e){
            System.out.println("Error");
            }
        }
    }//GEN-LAST:event_simpan_btnActionPerformed

    private void noActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noActionPerformed

    private void cetaktikActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cetaktikActionPerformed
        // TODO add your handling code here:
        getDataPembelian(Integer.parseInt(id_txt.getText()));
    }//GEN-LAST:event_cetaktikActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new admin().setVisible(true);
            }
        });
    }
    
    public void getDataPembelian(int idPembelian){
        try {
            String sql = "SELECT * FROM pembelian WHERE Id_pembelian = " + idPembelian;
            Connection c = KoneksiDB.getKoneksi();
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while(rs.next()){
                Tiket tiket1 = new Tiket(rs.getString(2), rs.getString(3), rs.getString(4), "Film 3 D", rs.getString(8), rs.getString(12));
                tiket1.dispose();
                Tiket tiket2 = new Tiket(rs.getString(2), rs.getString(3), rs.getString(4), "Film 3 D", rs.getString(9), rs.getString(12));
                tiket2.dispose();
                Tiket tiket3 = new Tiket(rs.getString(2), rs.getString(3), rs.getString(4), "Film 2 D", rs.getString(10), rs.getString(12));
                tiket3.dispose();               
                Tiket tiket4 = new Tiket(rs.getString(2), rs.getString(3), rs.getString(4), "Film 2 D", rs.getString(11), rs.getString(12));
                tiket4.dispose();  
            }
            s.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Tiket.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Exit;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.ButtonGroup buttonGroup5;
    private javax.swing.ButtonGroup buttonGroup6;
    private javax.swing.ButtonGroup buttonGroup7;
    private javax.swing.ButtonGroup buttonGroup8;
    private javax.swing.JButton cetaktik;
    private javax.swing.JLabel film_txt;
    private javax.swing.JLabel id_txt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel kode_txt;
    private javax.swing.JLabel kurreg1_txt;
    private javax.swing.JLabel kurreg2_txt;
    private javax.swing.JLabel kursivip1_txt;
    private javax.swing.JLabel kursivip2_txt;
    private javax.swing.JLabel lbl_admin;
    private javax.swing.JRadioButton no;
    private javax.swing.JButton penyuntinganjadwal;
    private javax.swing.JButton simpan_btn;
    private javax.swing.JLabel status_txt;
    private javax.swing.JTable tabel1;
    private javax.swing.JLabel tanggal_txt;
    private javax.swing.JLabel tbayar_txt;
    private javax.swing.JTextField tblsearch;
    private javax.swing.JLabel tiketreg_txt;
    private javax.swing.JLabel tiketvip_txt;
    private javax.swing.JRadioButton verif;
    private javax.swing.JLabel waktu_txt;
    // End of variables declaration//GEN-END:variables

        }

