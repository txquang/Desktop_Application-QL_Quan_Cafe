
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class QuanLyBoPhan extends javax.swing.JFrame {

    java.sql.Connection conn;
    Statement truyvan;
    ResultSet rs;
    private DefaultTableModel dtm  = new DefaultTableModel();
    public QuanLyBoPhan() 
    {
        initComponents();
        LoadDB();
        LoadTableBP();
        AnNut();   
    }
    public void AnNut()
    {
        SuaNVBT.setVisible(false);
        XoaNVBT.setVisible(false);  
    }

    public void LoadDB()
    {
        try
	{   
            CT_ConnetDB x =new CT_ConnetDB();
            conn = x.ConnetDB();
            truyvan = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);       
        }
        catch (SQLException e)
        {
            System.err.println("KẾT NỐI THẤT BẠI");   
            this.dispose();   
        }
    }
     public void LoadTableBP()
    {
	try 
        {
            Vector<String> row,colum;//lay gia tri
	    colum=new Vector<String>();
	    rs=truyvan.executeQuery("select MaBP as' Mã Bộ Phận ',TenBP as 'Tên Bộ Phận'"
                                        + "from BoPhan");
	    ResultSetMetaData metaData =rs.getMetaData(); 
	    for (int i=1; i<=2;i++)//so cot
	       colum.add(metaData.getColumnName(i));
	    dtm.setColumnIdentifiers(colum);
	    while (rs.next())
	    {	
	        row = new Vector<String>();
	        for (int i=1; i<=2;i++)//so hang
	           row.add(rs.getString(i));
                dtm.addRow(row);         
	    }
	    jTable1.setModel(dtm);
	} 
	      
        catch (SQLException ex) 
	{ 	
	    System.out.print("loi"+ex);
	}
    }
    public boolean ThemBP(String a ,String b)
    {
        try 
        {
            PreparedStatement ps = conn.prepareStatement("insert into BoPhan  values(?,?)");
            ps.setString(1, a.trim() );
            ps.setString(2, b.trim() );
            ps.executeUpdate();
            dtm.setRowCount(0);
            LoadTableBP();
            return true;
        } 
        catch (Exception e) 
        {
            System.out.print("Loi :\n" + e);
            dtm.setRowCount(0);
            LoadTableBP();
            return false;
        }
        
        
    }
    @SuppressWarnings("unchecked") 
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        ThemNVBT = new javax.swing.JButton();
        SuaNVBT = new javax.swing.JButton();
        XoaNVBT = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel33 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Quản Lý Bộ Phận");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(102, 255, 204));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Quản Lý Bộ Phận");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.setGridColor(new java.awt.Color(153, 0, 0));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(153, 102, 0));
        jLabel2.setText("Mã Bộ Phận:");

        ThemNVBT.setBackground(new java.awt.Color(204, 255, 255));
        ThemNVBT.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ThemNVBT.setForeground(new java.awt.Color(102, 0, 0));
        ThemNVBT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Anh/icons8-add-property-20.png"))); // NOI18N
        ThemNVBT.setText("Thêm BP");
        ThemNVBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ThemNVBTActionPerformed(evt);
            }
        });

        SuaNVBT.setBackground(new java.awt.Color(204, 255, 255));
        SuaNVBT.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        SuaNVBT.setForeground(new java.awt.Color(102, 0, 0));
        SuaNVBT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Anh/icons8-edit-20 (2).png"))); // NOI18N
        SuaNVBT.setText("Sửa BP");
        SuaNVBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SuaNVBTActionPerformed(evt);
            }
        });

        XoaNVBT.setBackground(new java.awt.Color(204, 255, 255));
        XoaNVBT.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        XoaNVBT.setForeground(new java.awt.Color(255, 51, 0));
        XoaNVBT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Anh/icons8-delete-20.png"))); // NOI18N
        XoaNVBT.setText("Xóa BP");
        XoaNVBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                XoaNVBTActionPerformed(evt);
            }
        });

        jTextField1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(153, 102, 0));
        jLabel3.setText("Tên Bộ Phận");

        jTextField2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Anh/icons8-cancel-20.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel33.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel33.setText("Trần Xuân Quang-18I3");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
                            .addComponent(jTextField2))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(SuaNVBT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(XoaNVBT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ThemNVBT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(46, 46, 46))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 530, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 10, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel33))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addComponent(ThemNVBT)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(SuaNVBT)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(XoaNVBT)))
                .addGap(1, 1, 1)
                .addComponent(jLabel33))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void ThemNVBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ThemNVBTActionPerformed
        // TODO add your handling code here:
        String Name = JOptionPane.showInputDialog("Nhập Mã Bộ Phận");
        if ( Name.length() > 0 ) 
        {
            String Name1 = JOptionPane.showInputDialog("Nhập Tên Bộ Phận");
            if ( Name1.length() > 0 ) 
            {
                if ( ThemBP(Name, Name1) == true )
                    JOptionPane.showMessageDialog(null,"Thêm Bộ Phân Thành Công");
                else
                    JOptionPane.showMessageDialog(null,"Thêm Bộ Phận Thất Bại");
            }
            else
                JOptionPane.showMessageDialog(null,"Tên Bộ Phận Không được để Trống");
        }
        else
            JOptionPane.showMessageDialog(null,"Mã Bộ Phận Không được để Trống");
    }//GEN-LAST:event_ThemNVBTActionPerformed

    private void SuaNVBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SuaNVBTActionPerformed
        // TODO add your handling code here:
        String MaBP = jTextField1.getText().toString().trim();
        try {
            PreparedStatement ps = conn.prepareStatement("update  BoPhan set TenBP = ? where MaBP ='" + MaBP + "'");
            //ps.setString(1, MaBNTF.getText());
            ps.setString(1, jTextField2.getText());
           
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Bộ Phận có mã " + MaBP + " đã được sửa!",
                    "Thông báo", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException ex) {
            System.out.println("Loi :\n" + ex);
        }
         dtm.setRowCount(0);
        LoadTableBP();
        AnNut();
    }//GEN-LAST:event_SuaNVBTActionPerformed

    private void XoaNVBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_XoaNVBTActionPerformed
        // TODO add your handling code here:
        int returnValue = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa Bộ Phận " + jTextField1.getText().toString().trim(), "Bạn chắc chứ?", JOptionPane.YES_NO_OPTION);
        if (returnValue == JOptionPane.YES_OPTION) 
        {
            try 
            {
                PreparedStatement ps = conn.prepareStatement("delete from BoPhan where MaBP = '"+jTextField1.getText().toString().trim()+"'");
                ps.executeUpdate();
                dtm.setRowCount(0);
                LoadTableBP();
                JOptionPane.showMessageDialog(null," Bộ Phân "+jTextField1.getText().toString().trim()+" Đã Xóa");
            } 
            catch (Exception e) 
            {
            }
        }
      
    }//GEN-LAST:event_XoaNVBTActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        SuaNVBT.setVisible(true);
        XoaNVBT.setVisible(true);
        ThemNVBT.setVisible(false);
        jTextField1.setText((String) jTable1.getValueAt(jTable1.getSelectedRow(), 0));
        jTextField2.setText((String) jTable1.getValueAt(jTable1.getSelectedRow(), 1));
        
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    public static void main(String args[]) 
    {
        java.awt.EventQueue.invokeLater(new Runnable() 
        {
            public void run() 
            {
                new QuanLyBoPhan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton SuaNVBT;
    private javax.swing.JButton ThemNVBT;
    private javax.swing.JButton XoaNVBT;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
