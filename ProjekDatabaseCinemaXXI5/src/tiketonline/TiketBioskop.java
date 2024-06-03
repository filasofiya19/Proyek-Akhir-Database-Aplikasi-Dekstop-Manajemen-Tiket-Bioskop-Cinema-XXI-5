/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiketonline;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Date;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import java.text.DecimalFormat;
import java.text.DateFormat;
import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.print.PrintService;
import javax.print.StreamPrintServiceFactory;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.OrientationRequested;
import javax.swing.JButton;
/**
 *
 * @author fila sofiyati
 */
public class TiketBioskop extends javax.swing.JFrame {
    String [][] purchasesave = new String [5][1];
    double overralltotalsale = 0;
    int jumlahVIP = 0;
    int jumlahReguler = 0;
    int ps =0;
    String password;
    String cekpassword = "kosonginaja";
    FrameKursi frameKursi;
    RiwayatPembelian RP;
    Connection conn=null;
    ResultSet rs=null;
    PreparedStatement pst=null;
    /*private int idFilm;
    private int idTanggal;
    private int idWaktu;*/
    String kategori;
    
    
    String[][] itemFilm = new String[8][5];
    File fileImage[] = new File[8];
    Image image[] = new Image[8];
    ImageIcon icon[] = new ImageIcon[8];
    String[] itemTanggal = new String[14];
    
    /**
     * Creates new form TiketBioskop
     */
    
     public void getFilmData(){
        conn=KoneksiDB.getKoneksi();
        String sql = "SELECT * FROM pilih_film"; 
        Statement s;
        try {
            s = conn.createStatement();
            rs = s.executeQuery(sql);
            int n = 0;
            while (rs.next()){  
                itemFilm[n][0] = rs.getString(2);
                itemFilm[n][1] = rs.getString(3);
                itemFilm[n][2] = rs.getString(4);
                
                fileImage[n] = new File(itemFilm[n][0]);
                try (FileOutputStream fos = new FileOutputStream(fileImage[n])) {
                    byte[] buffer = new byte[1024];

                    // Get the binary stream of our BLOB data
                    InputStream is = rs.getBinaryStream(5);
                    while (is.read(buffer) > 0) {
                        fos.write(buffer);
                    }
                 
                } catch (IOException e) {
                    e.printStackTrace();
                }
                
                n++;
            }
            try {
                for (int i = 0; i < 8; i++){
                    image[i] = ImageIO.read(fileImage[i]);
                    icon[i] = new ImageIcon(image[i]);
                }
            } catch (IOException ex) {
                Logger.getLogger(TiketBioskop.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            rs.close();
            s.close();
            for (int i = 0; i < 8; i++){
                cmbfilm.addItem(itemFilm[i][0]);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(TiketBioskop.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void getTanggal(){
        conn=KoneksiDB.getKoneksi();
        String sql = "SELECT tanggal FROM pilih_tanggal"; 
        Statement s;
        try {
            s = conn.createStatement();
            rs = s.executeQuery(sql);
            int n = 0;
            while (rs.next()){
                itemTanggal[n] = rs.getString(1);
                n++;
            }
            rs.close();
            s.close();
            for (int i = 0; i < 14; i++){
                cmbTanggal.addItem(itemTanggal[i]);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TiketBioskop.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private void fillComboBox() {
    String sql = "SELECT waktu FROM pilih_jam";
    
    try {
        Connection conn = KoneksiDB.getKoneksi();
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        cmbWaktu.removeAllItems(); 
        while (resultSet.next()) {
            String waktu = resultSet.getString("waktu");
            cmbWaktu.addItem(waktu);
        }
        
        resultSet.close();
        statement.close();
        
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
}
 
    public TiketBioskop() {
        initComponents();
        fillComboBox();
        initializeComponents();
        setTitle("Halaman Pembelian");
//        populateComboBox();
        //kv1.setActionCommand("kd1"); kv2.setActionCommand("kd2"); kr1.setActionCommand("ka1"); kr2.setActionCommand("ka2");
        this.setLocationRelativeTo(null);// center form in the screen
        DateFormat dateFormat = new SimpleDateFormat("EEEE, dd MMMMM, yyyy");
        Date date = new Date();
        lblmaindate.setText(dateFormat.format(date));
        getFilmData();
        getTanggal();
    }
    
    private void initializeComponents() {
        uname.setText(sesi.getUsername());

    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnriwayat = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        lblsubtotal = new javax.swing.JLabel();
        lblPajak = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        cmbVIP = new javax.swing.JComboBox<>();
        cmbReguler = new javax.swing.JComboBox<>();
        kv1 = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        kv2 = new javax.swing.JButton();
        kr1 = new javax.swing.JButton();
        kr2 = new javax.swing.JButton();
        lblmaindate = new javax.swing.JLabel();
        Printtiket = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        lblNamaFilm = new javax.swing.JLabel();
        lblJumTikVIP = new javax.swing.JLabel();
        lblJumTikReguler = new javax.swing.JLabel();
        lblTgldanWaktu = new javax.swing.JLabel();
        lblTotalBayar = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel19 = new javax.swing.JLabel();
        kode_bk = new javax.swing.JLabel();
        verif = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        cmbfilm = new javax.swing.JComboBox<>();
        jLabel20 = new javax.swing.JLabel();
        uname = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cmbTanggal = new javax.swing.JComboBox<>();
        cmbWaktu = new javax.swing.JComboBox<>();
        jPanel8 = new javax.swing.JPanel();
        btnBaru = new javax.swing.JButton();
        btnHitung = new javax.swing.JButton();
        btnDetailPembelian = new javax.swing.JButton();
        btnCetak = new javax.swing.JButton();
        btnBayar = new javax.swing.JButton();
        btnexit = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        lblimage = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnriwayat.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnriwayat.setForeground(new java.awt.Color(0, 0, 153));
        btnriwayat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/tagihin.jpg"))); // NOI18N
        btnriwayat.setText("Riwayat Pembeliuan");
        btnriwayat.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnriwayat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnriwayatActionPerformed(evt);
            }
        });
        getContentPane().add(btnriwayat, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 610, -1, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("CINEMA XXI 5");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 1280, -1));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel12.setText("Sub-Total");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setText("Pajak (5%)");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel17.setText("Total");

        lblsubtotal.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblsubtotal.setText("0");

        lblPajak.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblPajak.setText("0");

        lblTotal.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblTotal.setText("0");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 153));
        jLabel6.setText("Jumlah Tiket dan Nomor Kursi");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("Film 3 D");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setText("Film 2 D");

        cmbVIP.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cmbVIP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2" }));
        cmbVIP.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cmbVIP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbVIPActionPerformed(evt);
            }
        });

        cmbReguler.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cmbReguler.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2" }));
        cmbReguler.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cmbReguler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbRegulerActionPerformed(evt);
            }
        });

        kv1.setEnabled(false);
        kv1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kv1ActionPerformed(evt);
            }
        });

        kv2.setEnabled(false);
        kv2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kv2ActionPerformed(evt);
            }
        });

        kr1.setEnabled(false);
        kr1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kr1ActionPerformed(evt);
            }
        });

        kr2.setEnabled(false);
        kr2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kr2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 717, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE))
                                .addGap(102, 102, 102)
                                .addComponent(cmbVIP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(cmbReguler, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(42, 42, 42)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(kr1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(kv1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(kv2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(kr2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(43, 43, 43)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel17)
                                    .addComponent(jLabel10))
                                .addGap(41, 41, 41)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblPajak, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addGap(47, 47, 47)
                                .addComponent(lblsubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(186, 186, 186)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 989, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(kv2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(jLabel12)
                            .addComponent(kv1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(kr1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(kr2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(jLabel10)
                                .addGap(13, 13, 13)
                                .addComponent(jLabel17))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(lblsubtotal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblPajak)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblTotal))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(cmbVIP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(cmbReguler, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2))
        );

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 260, 800, 130));

        lblmaindate.setForeground(new java.awt.Color(255, 0, 0));
        lblmaindate.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblmaindate.setText(" ");
        getContentPane().add(lblmaindate, new org.netbeans.lib.awtextra.AbsoluteConstraints(-2, 40, 1280, -1));

        Printtiket.setBackground(new java.awt.Color(255, 255, 255));
        Printtiket.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Printtiket.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel15.setText("Nama Film");
        Printtiket.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, -1));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel16.setText("Tiket Film 3 D | No. Kursi");
        Printtiket.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, -1));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel13.setText("Tiket Film 2 D | No. Kursi");
        Printtiket.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, -1));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel14.setText("Tanggal dan Waktu");
        Printtiket.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, -1, -1));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setText("Total Bayar");
        Printtiket.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, -1, -1));

        lblNamaFilm.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblNamaFilm.setText("-");
        Printtiket.add(lblNamaFilm, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 30, 220, -1));

        lblJumTikVIP.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblJumTikVIP.setText("-");
        Printtiket.add(lblJumTikVIP, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 50, 94, -1));

        lblJumTikReguler.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblJumTikReguler.setText("-");
        Printtiket.add(lblJumTikReguler, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 70, 103, -1));

        lblTgldanWaktu.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblTgldanWaktu.setText("-");
        Printtiket.add(lblTgldanWaktu, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 90, 330, -1));

        lblTotalBayar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblTotalBayar.setText("-");
        Printtiket.add(lblTotalBayar, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 110, 103, -1));

        jTextField1.setForeground(new java.awt.Color(51, 51, 51));
        jTextField1.setText("     Gedung KWU UNNES, Cinema XXI 5, Sekaran, Kec. Gn. Pati, Jawa Tengah 50229");
        jTextField1.setBorder(null);
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        Printtiket.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, 450, 20));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Desain lo.png"))); // NOI18N
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
        });
        Printtiket.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 10, 320, 150));
        Printtiket.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 780, -1));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel19.setText("Kode Pemesanan");
        Printtiket.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, -1, -1));

        kode_bk.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        kode_bk.setText("-");
        Printtiket.add(kode_bk, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 130, 200, 20));
        Printtiket.add(verif, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 130, -1, -1));

        getContentPane().add(Printtiket, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 440, 780, -1));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Pilih Film            :");

        cmbfilm.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cmbfilm.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cmbfilm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbfilmActionPerformed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel20.setText("Nama Pengguna :");

        uname.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        uname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                unameActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel20)))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cmbfilm, 0, 216, Short.MAX_VALUE)
                    .addComponent(uname))
                .addContainerGap(373, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(0, 7, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(uname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cmbfilm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        getContentPane().add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 70, 750, 80));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setForeground(new java.awt.Color(255, 255, 255));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 153));
        jLabel5.setText("Tanggal dan Waktu");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Tanggal Tonton :");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Waktu Tonton : ");

        cmbTanggal.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cmbTanggal.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cmbTanggal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTanggalActionPerformed(evt);
            }
        });

        cmbWaktu.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cmbWaktu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cmbWaktu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbWaktuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel4)
                        .addComponent(jLabel3)))
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbWaktu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 674, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(103, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel5))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cmbTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cmbWaktu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        getContentPane().add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 150, 900, 110));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        btnBaru.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnBaru.setForeground(new java.awt.Color(0, 0, 153));
        btnBaru.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Add new.png"))); // NOI18N
        btnBaru.setText("Baru");
        btnBaru.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBaru.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBaruActionPerformed(evt);
            }
        });

        btnHitung.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnHitung.setForeground(new java.awt.Color(0, 0, 153));
        btnHitung.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/hitungan.png"))); // NOI18N
        btnHitung.setText("Hitung");
        btnHitung.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnHitung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHitungActionPerformed(evt);
            }
        });

        btnDetailPembelian.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnDetailPembelian.setForeground(new java.awt.Color(0, 0, 153));
        btnDetailPembelian.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/belii.png"))); // NOI18N
        btnDetailPembelian.setText("Beli");
        btnDetailPembelian.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDetailPembelian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDetailPembelianActionPerformed(evt);
            }
        });

        btnCetak.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnCetak.setForeground(new java.awt.Color(0, 0, 153));
        btnCetak.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/print.png"))); // NOI18N
        btnCetak.setText("Cetak Pembayaran");
        btnCetak.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCetak.setEnabled(false);
        btnCetak.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCetakMouseClicked(evt);
            }
        });
        btnCetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCetakActionPerformed(evt);
            }
        });

        btnBayar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnBayar.setForeground(new java.awt.Color(0, 0, 153));
        btnBayar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/bayaran.png"))); // NOI18N
        btnBayar.setText("Bayar");
        btnBayar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBayarActionPerformed(evt);
            }
        });

        btnexit.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnexit.setForeground(new java.awt.Color(0, 0, 153));
        btnexit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Exit application.png"))); // NOI18N
        btnexit.setText("Keluar");
        btnexit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnexit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnexitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(btnBaru)
                .addGap(68, 68, 68)
                .addComponent(btnHitung)
                .addGap(65, 65, 65)
                .addComponent(btnDetailPembelian)
                .addGap(74, 74, 74)
                .addComponent(btnBayar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addComponent(btnCetak)
                .addGap(52, 52, 52)
                .addComponent(btnexit)
                .addGap(46, 46, 46))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDetailPembelian)
                    .addComponent(btnCetak)
                    .addComponent(btnBayar)
                    .addComponent(btnexit)
                    .addComponent(btnHitung)
                    .addComponent(btnBaru))
                .addGap(0, 8, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 390, 970, 40));

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 204, 204), null));

        lblimage.setText(" ");
        lblimage.setMaximumSize(new java.awt.Dimension(200, 300));
        lblimage.setMinimumSize(new java.awt.Dimension(200, 300));
        lblimage.setPreferredSize(new java.awt.Dimension(200, 300));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblimage, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(10, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblimage, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 100, 180, 250));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 51, 255));
        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/bg puih.png"))); // NOI18N
        getContentPane().add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, -1));
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 63, 960, 19));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbfilmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbfilmActionPerformed
        // TODO add your handling code here:
          for(int i = 0; i < 8; i++){
            if(cmbfilm.getSelectedIndex() == i){
                lblimage.setIcon(icon[i]);
            }
          }
    }//GEN-LAST:event_cmbfilmActionPerformed

    private void btnHitungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHitungActionPerformed
        // TODO add your handling code here:
        final double TikVIP = 45000;
        final double TikReguler = 30000;
        double subtotal = 0;
        double subtotalVIP = 0;
        double subtotalReguler = 0;
        double pajak = 0;
        double total = 0;

        DecimalFormat formatter = new DecimalFormat("Rp ");

        String VIPstring = (String)cmbVIP.getSelectedItem();
        String Regulerstring = (String)cmbReguler.getSelectedItem();

        int VIP = Integer.parseInt(VIPstring);
        int Reguler = Integer.parseInt(Regulerstring);

        subtotalVIP = VIP * TikVIP;
        subtotalReguler = Reguler * TikReguler;

        subtotal = subtotalVIP+subtotalReguler;

        pajak = 0.05*subtotal;

        total = pajak + subtotal;

        lblsubtotal.setText(formatter.format(subtotal));
        lblPajak.setText(formatter.format(pajak));
        lblTotal .setText(formatter.format(total));

        overralltotalsale = overralltotalsale+total;
        jumlahVIP = jumlahVIP + Integer.parseInt((String)cmbVIP.getSelectedItem());
        jumlahReguler = jumlahReguler+ Integer.parseInt((String)cmbReguler.getSelectedItem());

    }//GEN-LAST:event_btnHitungActionPerformed

    private void btnDetailPembelianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetailPembelianActionPerformed
        // TODO add your handling code here:
        jumlahVIP = Integer.parseInt((String) cmbVIP.getSelectedItem());
        lblNamaFilm.setText((String)cmbfilm.getSelectedItem());
        lblJumTikVIP.setText((String)cmbVIP.getSelectedItem()+" | "+kv1.getText()+" "+kv2.getText());
        lblJumTikReguler.setText((String)cmbReguler.getSelectedItem()+" | "+kr1.getText()+" "+kr2.getText());
        lblTgldanWaktu.setText((String)cmbTanggal.getSelectedItem()+ " | " + (String)cmbWaktu.getSelectedItem());
        lblTotalBayar.setText(lblTotal.getText());

        purchasesave[0][0] = "Nama Film: " + (String)cmbfilm.getSelectedItem();
        purchasesave[1][0] = "Tiket VIP | No. Kursi: "+(String)cmbVIP.getSelectedItem()+" | "+kv1.getText()+" "+kv2.getText();
        purchasesave[2][0] = "Tiket Reguler | No. Kursi : "+(String)cmbReguler.getSelectedItem()+" | "+kr1.getText()+" "+kr2.getText();
        purchasesave[3][0] = "Tanggal dan Waktu: "+(String)cmbTanggal.getSelectedItem()+ " | " +(String)cmbWaktu.getSelectedItem();
        purchasesave[4][0] = "Total Bayar: "+lblTotal.getText();
       
    }//GEN-LAST:event_btnDetailPembelianActionPerformed

    private void insertDataToDatabase() {
    String selectedFilm = cmbfilm.getSelectedItem().toString(); // Mendapatkan film yang dipilih dari ComboBox
    String selectedTanggal = cmbTanggal.getSelectedItem().toString();
    String selectedWaktu = cmbWaktu.getSelectedItem().toString();
    int selectedVIP = Integer.parseInt(cmbVIP.getSelectedItem().toString()); // Mendapatkan angka yang dipilih dari ComboBox
    int selectedReguler = Integer.parseInt(cmbReguler.getSelectedItem().toString());
    String total = lblTotal.getText();
    String kurvip1 = kv1.getText();
    String kurvip2 = kv2.getText();
    String kurreg1 = kr1.getText();
    String kurreg2 = kr2.getText();
    String kodebk = kode_bk.getText();
    String verifikasi = verif.getText();
 
    try {
        Connection conn = KoneksiDB.getKoneksi(); // Mendapatkan koneksi ke database (gunakan metode sesuai dengan driver dan konfigurasi Anda)
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO pembelian (film, tanggal, waktu, tik_3D, tik_2D, total_bayar, kursi_1_3D, kursi_2_3D, kursi_1_2D, kursi_2_2D, Kode_bk, St_verif, username) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)"); // Ganti "pembelian_tiket" dengan nama tabel yang sesuai di database Anda

        stmt.setString(1, selectedFilm); // Mengisi nilai parameter pertama dengan film yang dipilih
        stmt.setString(2, selectedTanggal);
        stmt.setString(3, selectedWaktu);
        stmt.setInt(4, selectedVIP);
        stmt.setInt(5, selectedReguler);
        stmt.setString(6, total);
        stmt.setString(7, kurvip1);
        stmt.setString(8, kurvip2);
        stmt.setString(9, kurreg1);
        stmt.setString(10, kurreg2);
        stmt.setString(11, kodebk);
        stmt.setString(12, verifikasi);
        stmt.setString(13, sesi.getUsername());
        stmt.executeUpdate();

        // Tutup koneksi dan sumber daya
        //stmt.close();
        //conn.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
    
    private void btnCetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCetakActionPerformed

// Metode utilitas untuk mengonversi milimeter menjadi points (1 mm = 2.8346 points)

        PrinterJob job = PrinterJob.getPrinterJob() ;
        job.setJobName("print data");
        
        job.setPrintable(new Printable(){
        @Override
        public int print(Graphics pg, PageFormat pf, int pageNum ){
        pf.setOrientation(PageFormat.LANDSCAPE);
        if(pageNum>0){
        return Printable.NO_SUCH_PAGE;
        }
        Graphics2D g2 = (Graphics2D)pg;
        g2.translate(pf.getImageableX(), pf.getImageableY());
        g2.scale(0.47, 0.47);
        Printtiket.print(g2);
        return Printable.PAGE_EXISTS;
        }       
        });
        boolean ok = job.printDialog();
        if(ok){
            try{
                job.print();
            }
            catch (PrinterException ex){
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_btnCetakActionPerformed

    private void btnBaruActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBaruActionPerformed
        // TODO add your handling code here:
        lblNamaFilm.setText(" - ");
        lblJumTikVIP.setText(" - ");
        lblJumTikReguler.setText(" - ");
        lblTgldanWaktu.setText(" - ");
        lblTotalBayar.setText(" - ");
        kode_bk.setText(" - ");
        lblTotal.setText(" 0 ");
        lblPajak.setText(" 0 ");
        lblsubtotal.setText(" 0 ");
        cmbfilm.setSelectedItem(0);
        cmbTanggal.setSelectedItem(0);
        cmbTanggal.setSelectedIndex(0);
        cmbWaktu.setSelectedItem(0);
        cmbWaktu.setSelectedIndex(0);
        cmbVIP.setSelectedItem(0);
        cmbVIP.setSelectedIndex(0);
        cmbReguler.setSelectedItem(0);
        cmbReguler.setSelectedIndex(0);
        cmbfilm.setSelectedIndex(0);
        lblimage.setIcon(new javax.swing.ImageIcon(getClass().getResource("C:\\Users\\fila sofiyati\\Documents\\poster film\\poster sewu dino.jpg")));
        btnCetak.setEnabled(false);
    }//GEN-LAST:event_btnBaruActionPerformed

    private void btnriwayatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnriwayatActionPerformed
        // TODO add your handling code here:
        //DecimalFormat formatter = new DecimalFormat("Rp ");
        //JOptionPane.showMessageDialog(null,"Tiket Bayar\n\n Dewasa: "+Integer.toString(jumlahVIP)+" Anak: "+Integer.toString(jumlahReguler)+"\n\nTotal Bayar: "+formatter.format(overralltotalsale));
        RiwayatPembelian RP = new RiwayatPembelian();
        RP.setTiketBioskop(this);
        RP.setVisible(true);
        
    }//GEN-LAST:event_btnriwayatActionPerformed
    private String generateKodeBooking() {
    long currentTime = System.currentTimeMillis();
    Random random = new Random();
    int randomValue = random.nextInt(10000); // Ubah sesuai dengan rentang nilai acak yang diinginkan
    
    String kodeBooking = String.valueOf(currentTime) + String.valueOf(randomValue);
    return kodeBooking;
}
    private void btnBayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBayarActionPerformed
        // TODO add your handling code here:
         ps = Integer.parseInt(JOptionPane.showInputDialog(null,"Masukkan nomor kartu kredit Anda"));
         password = JOptionPane.showInputDialog(null,"Password");
        if (password.equals(cekpassword)){
            JOptionPane.showMessageDialog(null, "Pembayaran Sukses");
            btnCetak.setEnabled(true);
            String kodeBooking = generateKodeBooking();
            kode_bk.setText(kodeBooking);
            insertDataToDatabase();
            
        } else{
            JOptionPane.showMessageDialog(null,"Password Salah");
            btnCetak.setEnabled(false);
        }
         
        
    }//GEN-LAST:event_btnBayarActionPerformed
    
    private void btnexitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnexitActionPerformed
        // TODO add your handling code here:
        int a = JOptionPane.showConfirmDialog(null, "Apakah Anda yakin ingin keluar?", "Select", JOptionPane.YES_NO_OPTION);
        if(a == 0){
        setVisible(false);
        dispose();
        sesi.setUsername(null);

        // Menampilkan pesan sukses dan kembali ke jendela login
        JOptionPane.showMessageDialog(null, "Berhasil Keluar Dari Akun Pembeli");
        
        // Membuka jendela LoginFrame
        Login loginFrame = new Login();
        loginFrame.setVisible(true);

        // Menutup jendela saat ini
        dispose();
        }
    }//GEN-LAST:event_btnexitActionPerformed

    
    private void cmbVIPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbVIPActionPerformed
         String selectedKursi1 = kv1.getActionCommand();
    String selectedKursi2 = kv2.getActionCommand();
    int selectedFilm = cmbfilm.getSelectedIndex()+1;
    int selectedTanggal = cmbTanggal.getSelectedIndex()+1;
    int selectedWaktu = cmbWaktu.getSelectedIndex()+1;
    String selectedKategori = "";
    
    switch (cmbVIP.getSelectedIndex()) {
        case 0:
            kv1.setEnabled(false);
            kv2.setEnabled(false);
            kv1.setText("");
            kv2.setText("");
            selectedKategori = ""; // Tidak ada kategori terpilih
            break;
        case 1:
            kv1.setEnabled(true);
            kv1.setText("Pilih Kursi");
            kv2.setEnabled(false);
            kv2.setText("");
            selectedKategori = "3D"; // Kategori terpilih 3D
            break;
        case 2:
            kv1.setEnabled(true);
            kv2.setEnabled(true);
            kv1.setText("Pilih Kursi");
            kv2.setText("Pilih Kursi");
            selectedKategori = "2D"; // Kategori terpilih 2D
            break;
    }
    
    frameKursi = new FrameKursi(selectedKursi1, kv1.getText(), selectedFilm, selectedTanggal, selectedWaktu, selectedKategori);
    frameKursi = new FrameKursi(selectedKursi2, kv2.getText(), selectedFilm, selectedTanggal, selectedWaktu, selectedKategori);             
        /*frameKursi = new FrameKursi(kv1.getActionCommand(),kv1.getText(), cmbfilm.getSelectedIndex(), cmbTanggal.getSelectedIndex(), cmbWaktu.getSelectedIndex());
        frameKursi = new FrameKursi(kv2.getActionCommand(),kv2.getText(), cmbfilm.getSelectedIndex(), cmbTanggal.getSelectedIndex(), cmbWaktu.getSelectedIndex());              
          
        switch (cmbVIP.getSelectedIndex()){
            case 0:
                kv1.setEnabled(false);
                kv2.setEnabled(false);
                kv1.setText("");
                kv2.setText("");
                break;
            case 1:
                kv1.setEnabled(true);
                kv1.setText("Pilih Kursi");
                kv2.setEnabled(false);
                kv2.setText("");break;
            case 2:
                kv1.setEnabled(true);
                kv2.setEnabled(true);
                kv1.setText("Pilih Kursi");
                kv2.setText("Pilih Kursi");
                break;
        }*/
    }//GEN-LAST:event_cmbVIPActionPerformed

    private void cmbRegulerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbRegulerActionPerformed
        String selectedKursi1 = kr1.getActionCommand();
    String selectedKursi2 = kr2.getActionCommand();
    int selectedFilm = cmbfilm.getSelectedIndex() +1;
    int selectedTanggal = cmbTanggal.getSelectedIndex()+1;
    int selectedWaktu = cmbWaktu.getSelectedIndex()+1;
    String selectedKategori = "";
    
    switch (cmbReguler.getSelectedIndex()) {
        case 0:
            kr1.setEnabled(false);
            kr2.setEnabled(false);
            kr1.setText("");
            kr2.setText("");
            selectedKategori = ""; // Tidak ada kategori terpilih
            break;
        case 1:
            kr1.setEnabled(true);
            kr1.setText("Pilih Kursi");
            kr2.setEnabled(false);
            kr2.setText("");
            selectedKategori = "2D"; // Kategori terpilih 2D
            break;
        case 2:
            kr1.setEnabled(true);
            kr2.setEnabled(true);
            kr1.setText("Pilih Kursi");
            kr2.setText("Pilih Kursi");
            selectedKategori = "2D"; // Kategori terpilih 2D
            break;
    }
    
    frameKursi = new FrameKursi(selectedKursi1, kr1.getText(), selectedFilm, selectedTanggal, selectedWaktu, selectedKategori);
    frameKursi = new FrameKursi(selectedKursi2, kr2.getText(), selectedFilm, selectedTanggal, selectedWaktu, selectedKategori);
        /* frameKursi = new FrameKursi(kr1.getActionCommand(),kr1.getText(), cmbfilm.getSelectedIndex(), cmbTanggal.getSelectedIndex(), cmbWaktu.getSelectedIndex());
        frameKursi = new FrameKursi(kr2.getActionCommand(),kr2.getText(), cmbfilm.getSelectedIndex(), cmbTanggal.getSelectedIndex(), cmbWaktu.getSelectedIndex());              
         
        switch (cmbReguler.getSelectedIndex()){
            case 0:
                kr1.setEnabled(false);
                kr2.setEnabled(false);
                kr1.setText("");
                kr2.setText("");
                break;
            case 1:
                kr1.setEnabled(true);
                kr1.setText("Pilih Kursi");
                kr2.setEnabled(false);
                kr2.setText("");
                break;
            case 2:
                kr1.setEnabled(true);
                kr2.setEnabled(true);
                kr1.setText("Pilih Kursi");
                kr2.setText("Pilih Kursi");
                break;
        }*/
    }//GEN-LAST:event_cmbRegulerActionPerformed

    private void btnCetakMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCetakMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_btnCetakMouseClicked

    private void cmbWaktuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbWaktuActionPerformed

    }//GEN-LAST:event_cmbWaktuActionPerformed

    private void cmbTanggalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTanggalActionPerformed

    }//GEN-LAST:event_cmbTanggalActionPerformed

    private void kv1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kv1ActionPerformed
        String selectedKategori = "3D";
        // TODO add your handling code here:
         
       frameKursi = new FrameKursi(kv1.getActionCommand(), kv1.getText(), cmbfilm.getSelectedIndex(), cmbTanggal.getSelectedIndex(), cmbWaktu.getSelectedIndex(), selectedKategori);
        frameKursi.setButtonMana("kv1");
       frameKursi.setVisible(true);
       /*frameKursi = new FrameKursi(kv1.getActionCommand(),kv1.getText(), cmbfilm.getSelectedIndex(), cmbTanggal.getSelectedIndex(), cmbWaktu.getSelectedIndex());
        frameKursi.setVisible(true);*/
    }//GEN-LAST:event_kv1ActionPerformed

    private void kv2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kv2ActionPerformed
       String selectedKategori = "3D";
        
        frameKursi = new FrameKursi(kv2.getActionCommand(), kv2.getText(), cmbfilm.getSelectedIndex(), cmbTanggal.getSelectedIndex(), cmbWaktu.getSelectedIndex(), selectedKategori);
        frameKursi.setButtonMana("kv2");
        frameKursi.setVisible(true); 
      /*frameKursi = new FrameKursi(kv2.getActionCommand(),kv2.getText(), cmbfilm.getSelectedIndex(), cmbTanggal.getSelectedIndex(), cmbWaktu.getSelectedIndex());
        frameKursi.setVisible(true);*/
    }//GEN-LAST:event_kv2ActionPerformed

    private void kr1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kr1ActionPerformed
         String selectedKategori = "2D";
          
        frameKursi = new FrameKursi(kr1.getActionCommand(), kr1.getText(), cmbfilm.getSelectedIndex(), cmbTanggal.getSelectedIndex(), cmbWaktu.getSelectedIndex(), selectedKategori);
        frameKursi.setButtonMana("kr1");
        frameKursi.setVisible(true);
       /*frameKursi = new FrameKursi(kr1.getActionCommand(),kr1.getText(), cmbfilm.getSelectedIndex(), cmbTanggal.getSelectedIndex(), cmbWaktu.getSelectedIndex());
        frameKursi.setVisible(true);*/
    }//GEN-LAST:event_kr1ActionPerformed

    private void kr2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kr2ActionPerformed
      String selectedKategori = "2D";
      
        frameKursi = new FrameKursi(kr2.getActionCommand(), kr2.getText(), cmbfilm.getSelectedIndex(), cmbTanggal.getSelectedIndex(), cmbWaktu.getSelectedIndex(), selectedKategori);
        frameKursi.setButtonMana("kr2");
        frameKursi.setVisible(true);
       /*frameKursi = new FrameKursi(kr2.getActionCommand(),kr2.getText(), cmbfilm.getSelectedIndex(), cmbTanggal.getSelectedIndex(), cmbWaktu.getSelectedIndex());
        frameKursi.setVisible(true);*/
    }//GEN-LAST:event_kr2ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel9MouseClicked

    private void unameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_unameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_unameActionPerformed

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
            java.util.logging.Logger.getLogger(TiketBioskop.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TiketBioskop.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TiketBioskop.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TiketBioskop.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TiketBioskop().setVisible(true);
            }
        });
        
    }

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Printtiket;
    private javax.swing.JButton btnBaru;
    private javax.swing.JButton btnBayar;
    private javax.swing.JButton btnCetak;
    private javax.swing.JButton btnDetailPembelian;
    private javax.swing.JButton btnHitung;
    private javax.swing.JButton btnexit;
    private javax.swing.JButton btnriwayat;
    private javax.swing.JComboBox<String> cmbReguler;
    private javax.swing.JComboBox<String> cmbTanggal;
    private javax.swing.JComboBox<String> cmbVIP;
    private javax.swing.JComboBox<String> cmbWaktu;
    private javax.swing.JComboBox<String> cmbfilm;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel kode_bk;
    public static javax.swing.JButton kr1;
    public static javax.swing.JButton kr2;
    public static javax.swing.JButton kv1;
    public static javax.swing.JButton kv2;
    private javax.swing.JLabel lblJumTikReguler;
    private javax.swing.JLabel lblJumTikVIP;
    private javax.swing.JLabel lblNamaFilm;
    private javax.swing.JLabel lblPajak;
    private javax.swing.JLabel lblTgldanWaktu;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JLabel lblTotalBayar;
    private javax.swing.JLabel lblimage;
    private javax.swing.JLabel lblmaindate;
    private javax.swing.JLabel lblsubtotal;
    private javax.swing.JTextField uname;
    private javax.swing.JLabel verif;
    // End of variables declaration//GEN-END:variables

    
    }
    

