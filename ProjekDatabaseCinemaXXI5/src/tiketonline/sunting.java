/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package tiketonline;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;


/**
 *
 * @author fila sofiyati
 */
public class sunting extends javax.swing.JFrame {
    String crudImagePath = "";
    String crudImageNama = "";
    String crudJudul = "";
    String crudSinopsis = "";
    String crudGenre = "";
    // Connection conn;
    //private PreparedStatement pst;
    String[] itemTanggal = new String[14];
    Connection conn=null;
    ResultSet rs=null;
    PreparedStatement pst=null;
    private String adminId;
   
    private String adminUsername;

    public void setAdminId(String adminId) {
        this.adminId = adminId; // Update the class variable
         updateAdminIdText();
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminUsername(String adminUsername) {
        this.adminUsername = adminUsername;
    }

    public String getAdminUsername() {
        return adminUsername;
    }
    public void updateAdminIdText() {
    adm_txt.setText(adminId);
}
  
    public sunting() {
        initComponents();
        this.setLocationRelativeTo(null);
        fillComboBox() ;
        tabelfilm.getTableHeader().setFont(new Font("Tahoma", Font.BOLD,12));
        DateFormat dateFormat = new SimpleDateFormat("EEEE, dd MMMMM, yyyy");
        Date date = new Date();
        lblmaindate.setText(dateFormat.format(date));
        getTanggal();
        //adm_txt.setText(getAdminId());
        //setMenuSunting();
        //populatetabelfilm();
        /*CRUDList();*/
       loadData();
        
        tabelfilm.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                // Ambil data dari baris yang dipilih
                int row = tabelfilm.getSelectedRow();
                TableModel model = tabelfilm.getModel();
                String Id_film = model.getValueAt(row, 0).toString();
                String Judul = model.getValueAt(row, 1).toString();
                String Sinopsis = model.getValueAt(row, 2).toString();
                String Genre = model.getValueAt(row, 3).toString();
                //String ImagePath= model.getValueAt(row, 4).toString();
                byte[] Foto_film = (byte[]) model.getValueAt(row, 4);
                
                
                // Tampilkan data pada JTextField, JTextArea, dan JLabel
                id_txt.setText(Id_film);
                judul_txt.setText(Judul);
                sinop.setText(Sinopsis);
                genre_txt.setText(Genre);
                
        ImageIcon imageIcon = new ImageIcon(Foto_film);
        Image imageResize = imageIcon.getImage().getScaledInstance(poster.getWidth(), poster.getHeight(), Image.SCALE_SMOOTH);
                //Namapath.setText(ImagePath);
                poster.setIcon(new ImageIcon(imageResize));
            }
        });
            ubah_btn.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent evt) {
        // Ambil data dari JTextField, JTextArea, dan JLabel
        int row = tabelfilm.getSelectedRow();
        TableModel model = tabelfilm.getModel();
        String Id_film = id_txt.getText();
        String Judul = judul_txt.getText();
        String Sinopsis = sinop.getText();
        String Genre = genre_txt.getText();
        
        // Ambil byte[] dari gambar di JLabel poster
        ImageIcon imageIcon = (ImageIcon) poster.getIcon();
        Image image = imageIcon.getImage();
        BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_RGB);
        Graphics g = bufferedImage.createGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(bufferedImage, "jpg", baos);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        byte[] foto_film = baos.toByteArray();
        
        // Update data pada jTable1
        model.setValueAt(Id_film, row, 0);
        model.setValueAt(Judul, row, 1);
        model.setValueAt(Sinopsis, row, 2);
        model.setValueAt(Genre, row, 3);
        
        // Update data gambar di database
        String sql = "UPDATE pilih_film SET Judul = ?, Sinopsis = ?, Genre = ?, Foto_film = ?, Id_Admin = ?, desk_sunting = ?, tanggal_sunting = ? WHERE Id_film = ?";
        try {
            conn = KoneksiDB.getKoneksi();
            pst = conn.prepareStatement(sql);
            pst.setString(1, Judul);
            pst.setString(2, Sinopsis);
            pst.setString(3, Genre);
            pst.setBytes(4, foto_film);
            pst.setString(5, getAdminId());
           
            pst.setString(6, "Menyunting Data Film");
            pst.setTimestamp(7, new java.sql.Timestamp(new Date().getTime())); // Menggunakan timestamp saat ini
            pst.setString(8, Id_film);

            pst.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Data berhasil diupdate");
            loadData();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
});
    }
    private void loadData() {
        try {
            // Buat koneksi ke database
            Connection conn = KoneksiDB.getKoneksi();
            // Buat statement untuk mengambil data dari tabel1
            String sql = "SELECT Id_film, Judul, Sinopsis, Genre, Foto_film FROM pilih_film";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            // Buat model untuk tabel
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("ID");
            model.addColumn("Judul");
            model.addColumn("Sinopsis");
            model.addColumn("Genre");
            
           // model.addColumn("Path Gambar");
            model.addColumn("Gambar");
            
            // Tambahkan data dari ResultSet ke model
            while (rs.next()) {
                Object[] row = new Object[5];
                row[0] = rs.getInt("Id_Film");
                row[1] = rs.getString("Judul");
                row[2] = rs.getString("Sinopsis");
                row[3] = rs.getString("Genre");
                
               // row[4] = rs.getString("ImagePath");
                row[4] = rs.getBytes("Foto_film");
                model.addRow(row);
            }
            
            // Tampilkan model pada JTable
            tabelfilm.setModel(model);
            tabelfilm.getColumnModel().getColumn(4).setCellRenderer(new ImageRenderer());

            TableColumn imageColumn = tabelfilm.getColumnModel().getColumn(4);
            imageColumn.setPreferredWidth(160); // Atur lebar kolom gambar sesuai kebutuhan
        
        // Atur tinggi baris
           tabelfilm.setRowHeight(235);
            // Tutup koneksi
            //rs.close();
            //stmt.close();
            //conn.close();
        } catch (Exception e) {
            e.printStackTrace();
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
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblmaindate = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        judul_txt = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        sinop = new javax.swing.JTextArea();
        genre_txt = new javax.swing.JTextField();
        poster = new javax.swing.JLabel();
        pilih_gambar = new javax.swing.JButton();
        id_txt = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        ubah_btn = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelfilm = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        cmbTanggal = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        tglbaru = new com.toedter.calendar.JDateChooser();
        jPanel9 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        adm_txt = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        cmbWaktu = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        ubahwkt = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        waktu_txt = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        ubah_tanggal = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();

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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 0, 153));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 28)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Penyuntingan Jadwal");

        lblmaindate.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblmaindate.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(558, Short.MAX_VALUE)
                .addComponent(lblmaindate, javax.swing.GroupLayout.PREFERRED_SIZE, 743, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(432, 432, 432)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblmaindate)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Judul Film :");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Sinopsis    :");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Genre       :");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Poster     :");

        sinop.setColumns(20);
        sinop.setLineWrap(true);
        sinop.setRows(5);
        sinop.setWrapStyleWord(true);
        jScrollPane1.setViewportView(sinop);

        genre_txt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                genre_txtActionPerformed(evt);
            }
        });

        poster.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        pilih_gambar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        pilih_gambar.setText("Pilih Gambar");
        pilih_gambar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pilih_gambarActionPerformed(evt);
            }
        });

        id_txt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                id_txtActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Id               :");

        jPanel8.setBackground(new java.awt.Color(0, 0, 153));
        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        ubah_btn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        ubah_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ubah.png"))); // NOI18N
        ubah_btn.setText("Ubah");
        ubah_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ubah_btnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(202, 202, 202)
                .addComponent(ubah_btn)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ubah_btn)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel5)
                        .addComponent(jLabel4)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addComponent(genre_txt)
                            .addComponent(id_txt)
                            .addComponent(judul_txt))
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(poster, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(pilih_gambar)
                        .addGap(0, 202, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(id_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(judul_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(genre_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(poster, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(pilih_gambar)
                        .addGap(15, 15, 15)))
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tabelfilm.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tabelfilm.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Id", "Judul", "Sinospsis", "Genre", "path_foto"
            }
        ));
        jScrollPane2.setViewportView(tabelfilm);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setText("Tabel Detail Film");

        jPanel5.setBackground(new java.awt.Color(0, 0, 153));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 16, Short.MAX_VALUE)
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("Tanggal Baru");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setText("Tanggal Lama");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(51, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addGap(35, 35, 35)
                .addComponent(cmbTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(74, 74, 74)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(tglbaru, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7)
                    .addComponent(tglbaru, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cmbTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8)))
                .addGap(22, 22, 22))
        );

        jPanel9.setBackground(new java.awt.Color(0, 0, 153));
        jPanel9.setForeground(new java.awt.Color(0, 0, 255));

        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Exit application.png"))); // NOI18N
        jButton2.setText("Kembali");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Keterangan Admin : ");

        adm_txt.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        adm_txt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adm_txtActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel12)
                .addGap(18, 18, 18)
                .addComponent(adm_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addGap(46, 46, 46))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jLabel12)
                    .addComponent(adm_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setText("Waktu Lama");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setText("Waktu Baru");

        jPanel10.setBackground(new java.awt.Color(0, 0, 153));

        ubahwkt.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        ubahwkt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ubah.png"))); // NOI18N
        ubahwkt.setText("Ubah");
        ubahwkt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ubahwktActionPerformed(evt);
            }
        });

        jLabel14.setText("jLabel14");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(ubahwkt)
                .addGap(34, 34, 34)
                .addComponent(jLabel14)
                .addGap(241, 241, 241))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ubahwkt)
                    .addComponent(jLabel14))
                .addContainerGap())
        );

        jPanel7.setBackground(new java.awt.Color(0, 0, 153));
        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        ubah_tanggal.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        ubah_tanggal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ubah.png"))); // NOI18N
        ubah_tanggal.setText("Ubah");
        ubah_tanggal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ubah_tanggalActionPerformed(evt);
            }
        });

        jLabel13.setText("jLabel13");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(ubah_tanggal)
                .addGap(49, 49, 49)
                .addComponent(jLabel13)
                .addGap(222, 222, 222))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ubah_tanggal)
                    .addComponent(jLabel13))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addComponent(jLabel10)
                .addGap(31, 31, 31)
                .addComponent(cmbWaktu, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel11)
                .addGap(39, 39, 39)
                .addComponent(waktu_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(346, 346, 346)
                        .addComponent(jLabel9))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbWaktu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(waktu_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void id_txtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_id_txtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_id_txtActionPerformed

    private void ubah_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ubah_btnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ubah_btnActionPerformed

    private void pilih_gambarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pilih_gambarActionPerformed
        // TODO add your handling code here:
       String currentDirectoryPath ="";
        JFileChooser chooser = new JFileChooser(currentDirectoryPath );

        chooser.setDialogTitle("PILIH GAMBAR");
        FileNameExtensionFilter imageFNEF = new   FileNameExtensionFilter("IMAGES", "png","jpeg","jpg");
        chooser.setFileFilter(imageFNEF);
        int imageChooser = chooser.showOpenDialog(null);
        if(imageChooser==JFileChooser.APPROVE_OPTION){
            File imageFile = chooser.getSelectedFile();
            crudImagePath = imageFile.getAbsolutePath();
            crudImageNama = imageFile.getName();
//            Namapath.setText(crudImagePath);
            ImageIcon imageIcon = new ImageIcon(crudImagePath);
            Image imageResize = imageIcon.getImage().getScaledInstance(poster.getWidth(), poster.getHeight(), Image.SCALE_SMOOTH);

            poster.setIcon(new ImageIcon(imageResize));
        }
    }//GEN-LAST:event_pilih_gambarActionPerformed

    private void genre_txtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_genre_txtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_genre_txtActionPerformed

    
    private void ubah_tanggalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ubah_tanggalActionPerformed
     String tanggal = cmbTanggal.getSelectedItem().toString();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    String tanggalBaru = dateFormat.format(tglbaru.getDate());
    String sql = "UPDATE pilih_tanggal SET tanggal = ?, Id_Admin = ?, desk_sunting = ?, tanggal_sunting = ? WHERE tanggal = ?";
    
    try {
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, tanggalBaru);
        statement.setString(2, getAdminId());
        statement.setString(3, "Menyunting Data tanggal");
        statement.setTimestamp(4, new java.sql.Timestamp(new Date().getTime()));
        statement.setString(5, tanggal);
        
        
        statement.executeUpdate();
        
        JOptionPane.showMessageDialog(null, "Ubah tanggal berhasil");
        //cmbTanggal.removeAllItems();
        //loadComboBoxData();
        refreshComboBox();
        
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    }//GEN-LAST:event_ubah_tanggalActionPerformed
    private void refreshComboBox() {
    cmbTanggal.removeAllItems();
    loadComboBoxData();
}

    private void loadComboBoxData() {
    // Code to load the data into the combo box
    // Assuming the data source is a ResultSet named 'resultSet'
    try {
        // Clear the combo box
        cmbTanggal.removeAllItems();
        
        // Execute a query to retrieve the updated data
        String sql = "SELECT tanggal FROM pilih_tanggal";
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        
        // Add the retrieved data to the combo box
        while (resultSet.next()) {
            String tanggal = resultSet.getString("tanggal");
            cmbTanggal.addItem(tanggal);
        }
        
        // Close the ResultSet and statement
        resultSet.close();
        statement.close();
        
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
}
   
    
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        int a = JOptionPane.showConfirmDialog(null, "Apakah Anda yakin ingin kembali?", "Select", JOptionPane.YES_NO_OPTION);
        if(a == 0){
        setVisible(false);
        dispose();
        admin idx = new admin();
         idx.setAdminId(adminId);
        idx.setVisible(true);
        //dispose();
        }
    }//GEN-LAST:event_jButton2ActionPerformed
    
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
    private String getSelectedTime() {
    return cmbWaktu.getSelectedItem().toString();
    }
    
    private void ubahwktActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ubahwktActionPerformed
        // TODO add your handling code here:
        String newTime = waktu_txt.getText(); // Mendapatkan waktu baru dari JTextField
        String selectedTime = getSelectedTime(); // Mendapatkan waktu yang dipilih dari combo box
        String sql = "UPDATE pilih_jam SET waktu = ?, Id_Admin = ?, desk_sunting = ?, tanggal_sunting = ? WHERE waktu = ?";
    
    try {
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, newTime);
         statement.setString(2, getAdminId());
           
         statement.setString(3, "Menyunting Data waktu");
         statement.setTimestamp(4, new java.sql.Timestamp(new Date().getTime()));
        statement.setString(5, selectedTime);
        
        int rowsAffected = statement.executeUpdate();
        
        if (rowsAffected > 0) {
            JOptionPane.showMessageDialog(null, "Data waktu berhasil diperbarui");
             waktu_txt.setText(""); // Mengosongkan JTextField setelah pembaruan berhasil
             fillComboBox(); 
            // Melakukan tindakan selanjutnya setelah pembaruan sukses
            // ...
        } else {
            JOptionPane.showMessageDialog(null, "Gagal memperbarui data waktu");
        }
        
        statement.close();
    }   catch (SQLException ex) {
        ex.printStackTrace();
    } 
    }//GEN-LAST:event_ubahwktActionPerformed

    private void adm_txtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adm_txtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_adm_txtActionPerformed
    
    
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
            java.util.logging.Logger.getLogger(sunting.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(sunting.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(sunting.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(sunting.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
       
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new sunting().setVisible(true);
                
        }
    });
}
            
       
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField adm_txt;
    private javax.swing.JComboBox<String> cmbTanggal;
    private javax.swing.JComboBox<String> cmbWaktu;
    private javax.swing.JTextField genre_txt;
    private javax.swing.JTextField id_txt;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField judul_txt;
    private javax.swing.JLabel lblmaindate;
    private javax.swing.JButton pilih_gambar;
    private javax.swing.JLabel poster;
    private javax.swing.JTextArea sinop;
    private javax.swing.JTable tabelfilm;
    private com.toedter.calendar.JDateChooser tglbaru;
    private javax.swing.JButton ubah_btn;
    private javax.swing.JButton ubah_tanggal;
    private javax.swing.JButton ubahwkt;
    private javax.swing.JTextField waktu_txt;
    // End of variables declaration//GEN-END:variables

 

   
}
