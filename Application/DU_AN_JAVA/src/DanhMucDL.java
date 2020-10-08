
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class DanhMucDL extends javax.swing.JFrame {

    public DanhMucDL() {
        initComponents();
        LoadDB();
        DanhSanhDLCB.setSelectedIndex(2);
        Xóa.setVisible(false);
        
    }
    java.sql.Connection conn;
    Statement truyvan;
    ResultSet rs,rs1;
    String S;
    private DefaultTableModel dtm  = new DefaultTableModel();
    private DefaultTableModel dtm1 = new DefaultTableModel();
    private DefaultTableModel dtm2 = new DefaultTableModel();
    public void LoadDB()
    {
        try
	{   
            CT_ConnetDB x =new CT_ConnetDB();
            conn = x.ConnetDB();
            //conn=DriverManager.getConnection("jdbc:sqlserver://DESKTOP-37PC9RI:1433; databaseName =DuAn","sa","sa");
            truyvan = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            if (conn != null) 
            {
		System.out.println("KẾT NỐI THÀNH  CÔNG ");
                  //this.dispose();
            } 	          
        }
        catch (SQLException e)
        {
            JOptionPane.showMessageDialog(null, "Kết Nối SQL Lỗi");
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        DanhSanhDLCB = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        Lưu = new javax.swing.JButton();
        Xóa = new javax.swing.JButton();
        Thoát = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Quản Lý Danh Mục");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setIconImages(null);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(153, 255, 255));

        DanhSanhDLCB.setBackground(new java.awt.Color(255, 255, 0));
        DanhSanhDLCB.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        DanhSanhDLCB.setForeground(new java.awt.Color(255, 51, 51));
        DanhSanhDLCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Bảng Giá Bán Hàng", "Khu Vực ", "Đơn Vị Tính" }));
        DanhSanhDLCB.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                DanhSanhDLCBItemStateChanged(evt);
            }
        });

        jTable1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
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
        jTable1.setGridColor(new java.awt.Color(153, 255, 153));
        jTable1.setSelectionBackground(new java.awt.Color(102, 255, 255));
        jTable1.setSelectionForeground(new java.awt.Color(255, 0, 0));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable1MousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        Lưu.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        Lưu.setForeground(new java.awt.Color(102, 0, 0));
        Lưu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Anh/icons8-save-20.png"))); // NOI18N
        Lưu.setText("Thêm");
        Lưu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LưuActionPerformed(evt);
            }
        });

        Xóa.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        Xóa.setForeground(new java.awt.Color(102, 0, 0));
        Xóa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Anh/icons8-cancel-20.png"))); // NOI18N
        Xóa.setText("Xóa");
        Xóa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                XóaActionPerformed(evt);
            }
        });

        Thoát.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        Thoát.setForeground(new java.awt.Color(255, 51, 51));
        Thoát.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Anh/icons8-close-window-20.png"))); // NOI18N
        Thoát.setText("Đóng");
        Thoát.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ThoátActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(DanhSanhDLCB, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(Lưu, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Xóa, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(Thoát, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(DanhSanhDLCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Xóa)
                    .addComponent(Lưu)
                    .addComponent(Thoát))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void DanhSanhDLCBItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_DanhSanhDLCBItemStateChanged
        // TODO add your handling code here:
        Xóa.setVisible(false);
        String S;
        int a=0;
        if( DanhSanhDLCB.getSelectedIndex() == 0 )
        {
             S = "select MaBG as ' Mã Bảng Giá ' , TenBG as 'Tên Bảng Giá ' , MatDinh as ' Mặt Định 'from LoaiBangGia";
             a = 3;
             LoadDL(S, a,dtm);
        }   
        else
            if( DanhSanhDLCB.getSelectedIndex() == 1 )
            {
                S = "select  MaKV as ' Mã Khu Vực ' ,TenKV as ' Tên Khu Vực ' from KhuVuc";
                a = 2;
               LoadDL(S, a,dtm1);
            }   
            else 
            {
                S = "select MaDVT as ' Mã Đơn Vị Tính ', MaDinh as ' Mặt Định ' from DonViTinh";
                a = 2;
                LoadDL(S, a,dtm2);
            }
    }//GEN-LAST:event_DanhSanhDLCBItemStateChanged

    private void ThoátActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ThoátActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_ThoátActionPerformed

    private void jTable1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MousePressed
        // TODO add your handling code here:
        //kich dup
        int a = DanhSanhDLCB.getSelectedIndex();
        int b = jTable1.getSelectedRow();
        String Ma = (String) jTable1.getValueAt(b,0);
        String Ten = (String) jTable1.getValueAt(b,1);
        System.err.println(evt.getClickCount());
        if (evt.getClickCount() == 2 && !evt.isConsumed() ) 
        {
       
           //String code = JOptionPane.showInputDialog(null, "Bạn Có Muốn Sủa ", "Secret code needed (title)", JOptionPane.WARNING_MESSAGE);
        
            switch (a)
            {
                case 0:
                    String code = JOptionPane.showInputDialog(null,"Bán Có Muốn Sửa: "+ Ma, Ten);
                    System.err.println(code);
                    if( code.length() >0 )
                    {
                        try 
                         {
                            PreparedStatement ps = conn.prepareStatement("update  LoaiBangGia set TenBG = ? where MaBG ='" + Ma+ "'");
                            //ps.setString(1, MaBNTF.getText());
                            ps.setString(1, code);

                            ps.executeUpdate();
                            JOptionPane.showMessageDialog(null, "Loại Bảng Giá Có Mã " + Ma + " đã được sửa!",
                                    "Thông báo", JOptionPane.INFORMATION_MESSAGE);

                        } catch (SQLException ex) {
                            System.out.println("Loi :\n" + ex);
                        }
                    }
                    DanhSanhDLCBItemStateChanged(null);
                    break;
                case 1:
                    String code1 = JOptionPane.showInputDialog(null,"Bán Có Muốn Sửa: "+ Ma, Ten);
                   
                    if( code1.length() >0 )
                    {
                        try 
                         {
                            PreparedStatement ps = conn.prepareStatement("update  KhuVuc set TenKV = ? where MaKV ='" + Ma+ "'");
                            //ps.setString(1, MaBNTF.getText());
                            ps.setString(1, code1);

                            ps.executeUpdate();
                            JOptionPane.showMessageDialog(null, "Khu Vực có mã " + Ma + " đã được sửa!",
                                    "Thông báo", JOptionPane.INFORMATION_MESSAGE);

                        } catch (SQLException ex) {
                            System.out.println("Loi :\n" + ex);
                        }
                    }
                    DanhSanhDLCBItemStateChanged(null);
                    break;
                case 2:
                    String code2 = JOptionPane.showInputDialog(null,"Bán Có Muốn Sửa: "+ Ma, Ma);
                   
                    if( code2.length() >0 )
                    {
                        try 
                         {
                            PreparedStatement ps = conn.prepareStatement("update  DonViTinh set MaDVT = ? where MaDVT ='" + Ma+ "'");
                            //ps.setString(1, MaBNTF.getText());
                            ps.setString(1, code2);

                            ps.executeUpdate();
                            JOptionPane.showMessageDialog(null, "Đon Vị Tính có mã " + Ma + " đã được sửa!",
                                    "Thông báo", JOptionPane.INFORMATION_MESSAGE);

                        } catch (SQLException ex) {
                            System.out.println("Loi :\n" + ex);
                        }
                    }
                    DanhSanhDLCBItemStateChanged(null);
                    break;
            }
        }
    }//GEN-LAST:event_jTable1MousePressed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
         Xóa.setVisible(true);
    }//GEN-LAST:event_jTable1MouseClicked

    private void LưuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LưuActionPerformed
        // TODO add your handling code here:
        //String Name = JOptionPane.showInputDialog("Nhập Mã Bộ Phận");
        int a = DanhSanhDLCB.getSelectedIndex();
        switch (a)
        {
            case 0:
                String MaBG = JOptionPane.showInputDialog("Nhập Mã Bản Giá");
                String TenBG = JOptionPane.showInputDialog("Nhập Tên Bảng Giá");
                String MatDinh = "";
                if (MaBG.length() >0 && TenBG.length() >0) 
                {
                    if ( ThemDL(MaBG, TenBG, MatDinh, a) == true )
                        JOptionPane.showMessageDialog(null,"Thêm Thành Công");
                    else
                        JOptionPane.showMessageDialog(null,"Thêm Thất Bại");  
                }
                else
                    JOptionPane.showMessageDialog(null,"Không Đuọc Để Trống");
                
                DanhSanhDLCBItemStateChanged(null);
                break;
            case 1:
                String MaKV = JOptionPane.showInputDialog("Nhập Mã Khu Vực");
                String TenKV = JOptionPane.showInputDialog("Nhập Tên Khu Vực");
                if (MaKV.length() >0 && TenKV.length() >0) 
                {
                    if ( ThemDL(MaKV, TenKV, "", a) == true )
                        JOptionPane.showMessageDialog(null,"Thêm Thành Công");
                    else
                        JOptionPane.showMessageDialog(null,"Thêm Thất Bại");  
                }
                else
                    JOptionPane.showMessageDialog(null,"Không Đuọc Để Trống");
                
                DanhSanhDLCBItemStateChanged(null);
                break;
            case 2:
                String MaDVT = JOptionPane.showInputDialog("Nhập Mã DVT");
                if (MaDVT.length() >0 ) 
                {
                    if ( ThemDL(MaDVT, "", "", a) == true )
                        JOptionPane.showMessageDialog(null,"Thêm Thành Công");
                    else
                        JOptionPane.showMessageDialog(null,"Thêm Thất Bại");  
                }
                else
                    JOptionPane.showMessageDialog(null,"Không Đuọc Để Trống");
                DanhSanhDLCBItemStateChanged(null);
                break;
        }
                
    }//GEN-LAST:event_LưuActionPerformed

    private void XóaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_XóaActionPerformed
        // TODO add your handling code here:
        int a = DanhSanhDLCB.getSelectedIndex();
        int b = jTable1.getSelectedRow();
        String S = (String) jTable1.getValueAt(b,0);
        switch (a)
        {
            case 0:
                int returnValue = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa " + S, "Bạn chắc chứ?", JOptionPane.YES_NO_OPTION);
                if (returnValue == JOptionPane.YES_OPTION) 
                {
                    try 
                    {
                        PreparedStatement ps = conn.prepareStatement("delete from LoaiBangGia where MaBG = '"+S+"'");
                        ps.executeUpdate();
                        JOptionPane.showMessageDialog(null," Mã "+S+" Đã Xóa");
                    } 
                    catch (Exception e) 
                    {
                    }
                }
                DanhSanhDLCBItemStateChanged(null);
                break;
            case 1:
                int returnValue1 = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa " + S, "Bạn chắc chứ?", JOptionPane.YES_NO_OPTION);
                if (returnValue1 == JOptionPane.YES_OPTION) 
                {
                    try 
                    {
                        PreparedStatement ps = conn.prepareStatement("delete from KhuVuc where MaKV = '"+S+"'");
                        ps.executeUpdate();
                        JOptionPane.showMessageDialog(null," Mã "+S+" Đã Xóa");
                    } 
                    catch (Exception e) 
                    {
                    }
                }
                DanhSanhDLCBItemStateChanged(null);
                break;
            case 2:
                int returnValue2 = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa " + S, "Bạn chắc chứ?", JOptionPane.YES_NO_OPTION);
                if (returnValue2 == JOptionPane.YES_OPTION) 
                {
                    try 
                    {
                        PreparedStatement ps = conn.prepareStatement("delete from DonViTinh where MaDVT = '"+S+"'");
                        ps.executeUpdate();
                        JOptionPane.showMessageDialog(null," Mã "+S+" Đã Xóa");
                    } 
                    catch (Exception e) 
                    {
                    }
                }
                DanhSanhDLCBItemStateChanged(null);
                break;
                
        }
         
    }//GEN-LAST:event_XóaActionPerformed
    
    public boolean ThemDL(String Ma , String Ten , String MD ,int a )
    {
        switch (a)
        {
            case 0:
                try 
                {
                    PreparedStatement ps = conn.prepareStatement("insert into LoaiBangGia  values(?,?,?)");
                    ps.setString(1, Ma.trim() );
                    ps.setString(2, Ten.trim() );
                    ps.setString(3, MD.trim() );
                    ps.executeUpdate();
                    return true;
                } 
                catch (Exception e) 
                {
                    System.err.println(e);
                    return false;
                }
            case 1:
                try 
                {
                    PreparedStatement ps = conn.prepareStatement("insert into KhuVuc  values(?,?)");
                    ps.setString(1, Ma.trim() );
                    ps.setString(2, Ten.trim() );
                    ps.executeUpdate();
                    return true;
                } 
                catch (Exception e) 
                {
                    System.err.println(e);
                    return false;
                }
             case 2:
                try 
                {
                    PreparedStatement ps = conn.prepareStatement("insert into DonViTinh  values(?,?)");
                    ps.setString(1, Ma.trim() );
                    ps.setString(2, Ten.trim() );
                    ps.executeUpdate();
                    return true;
                } 
                catch (Exception e) 
                {
                    System.err.println(e);
                    return false;
                }
        }
        return false;
    }
    
    public void LoadDL( String S ,int a,DefaultTableModel A ) 
    {
        try 
        {
            A.setRowCount(0);
            Vector<String> row,colum1;//lay gia tri
	    colum1=new Vector<String>();
            rs=truyvan.executeQuery(S);
	    ResultSetMetaData metaData1 =rs.getMetaData();
	          
	    for (int i=1; i<=a;i++)//so cot
	    {
	        colum1.add(metaData1.getColumnName(i));
	    }
	    A.setColumnIdentifiers(colum1);
	    while (rs.next())
	    {
	        	
	        row = new Vector<String>();
	        for (int i=1; i<=a;i++)//so hang
	           row.add(rs.getString(i));
                A.addRow(row);
	              
	    }
	    jTable1.setModel(A);
	    } 
	      
	    catch (SQLException ex) 
	    {
	    	
	        System.out.print("loi"+ex);
                this.dispose();
	    }
        
        catch (Exception e) 
        {
        }
        
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() 
        {
            public void run() 
            {
                new DanhMucDL().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> DanhSanhDLCB;
    private javax.swing.JButton Lưu;
    private javax.swing.JButton Thoát;
    private javax.swing.JButton Xóa;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
