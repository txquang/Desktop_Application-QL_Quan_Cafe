
import java.awt.event.KeyEvent;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class HoaDon extends javax.swing.JFrame {

    /**
     * Creates new form HoaDon
     */
    public HoaDon() 
    {
        initComponents();
        LoadDB();
        //LoadBangGiaCB();
        LoadNhanVienCB();
        
        LoadDongCTTB("CT03");
        LoadTopHD("CT03");
        jButton2.setVisible(false);
    }
    public HoaDon(Q_ChungTu A) 
    {
        initComponents();
        LoadDB();
        //LoadBangGiaCB();
        LoadNhanVienCB();
        jTextField1.setText(A.MaNV);
        ThuNganCB.setSelectedItem(A.TenNV);
        LoadDongCTTB(A.MaHD);
        LoadTopHD(A.MaHD);
        jButton2.setVisible(false);
    }
    
    java.sql.Connection conn;
    Statement truyvan;
    ResultSet rs,rs1;
    String S;
    private DefaultTableModel dtm  = new DefaultTableModel();
    
    public void LoadDB()
    {
        try
	{   
            CT_ConnetDB x =new CT_ConnetDB();
            conn = x.ConnetDB();
            truyvan = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            if (conn != null) 
            {
		System.out.println("KẾT NỐI THÀNH  CÔNG ");
                  //this.dispose();
            } 	          
        }
        catch (SQLException e)
        {
            System.err.println("KẾT NỐI THẤT BẠI");   
            this.dispose();      
        }
    }
    
    /*public void LoadBangGiaCB()
    {
        try
	{
            rs= truyvan.executeQuery("Select TenBG from LoaiBangGia");
            while (rs.next())
                BangGiaCB.addItem(rs.getString(1)); 
        }
        catch(SQLException ex)
	{}

    }*/
    public void LoadNhanVienCB()
    {
        try
	{
            rs= truyvan.executeQuery("select HoTen from NhanVien WHERE MaBP like 'BPTN'");
            while (rs.next())
                ThuNganCB.addItem(rs.getString(1)); 
        }
        catch(SQLException ex)
	{   }
    }
     public void LoadDongCTTB(String S)
    {
		// LoadTable dữ liệu DataBase vào bảng
        dtm.setRowCount(0);
	try 
	{
	    //MatKhauTB.getColumn(0).setPreferredWidth(50);
            Vector<String> row,colum1;//lay gia tri
	    colum1=new Vector<String>();
            
 
	    rs=truyvan.executeQuery("select CT.MaSP AS 'Mã SP', SP.TenSP AS 'Tên SP', CT.MaDVT AS 'ĐVT', CT.SoLuong AS 'Số Lượng', CT.TraLai AS 'Trả Lại',CT.DonGia AS 'Đơn Giá', CT.GhiChu AS 'Ghi Chú'\n" +
                                            "from DongCT CT join SanPham SP\n" +
                                            "	on CT.MaSP = SP.MaSP\n" +
                                            "where SoCT = '"+S+"'");
	    ResultSetMetaData metaData1 =rs.getMetaData();
	          
	    for (int i=1; i<=7;i++)//so cot
	    {
	        colum1.add(metaData1.getColumnName(i));
	    }
	    dtm.setColumnIdentifiers(colum1);
	    while (rs.next())
	    {
	        	
	        row = new Vector<String>();
	        for (int i=1; i<=7;i++)//so hang
	           row.add(rs.getString(i));
                dtm.addRow(row);
	              
	    }
	    jTable1.setModel(dtm);
	    } 
	      
	    catch (SQLException ex) 
	    {
	    	
	        System.out.print("loi"+ex);
                this.dispose();
	    }
    }
    public void LoadTopHD(String S)
    {
        try 
        {
            DecimalFormat formatter = new DecimalFormat("#,###,###");
	    rs1=truyvan.executeQuery("SELECT  SoBan , SoCT, SoKhach, convert(varchar, NgayCT, 20),\n" +
                                    "		 SoTien, TrangThai, Giam , ThueVAT , PhiDV , TraTruoc,\n" +
                                    "		 ConNo, MaKH, MaNhanVien , convert(varchar, NgayDat, 20)\n" +
                                    "FROM ChungTu\n" +
                                    "WHERE SoCT = '"+S+"'");
            while (rs1.next())
            { 

                SoBanT.setText(rs1.getString(1));//
                SoPhieuTF.setText(rs1.getString(2));//
                SoKHTF.setText(rs1.getString(3));//
                GioVaoTF.setText(rs1.getString(4));//
                TongTienTF.setText( formatter.format( Double.valueOf(rs1.getString(5)) ));//
                if ( rs1.getString(6).equals("0") )   
                    TrangThaiTF.setText("Chưa TT");//
                else
                    TrangThaiTF.setText("Đã TT");
                GiamTF.setText(rs1.getString(7));//
                VATTF.setText(rs1.getString(8));//
                PhiDVTF.setText(rs1.getString(9));//
                TraTrucTF.setText(rs1.getString(10));// 
                ConNoTF.setText( formatter.format( Double.valueOf(rs1.getString(11))) );
                MaKH.setText(rs1.getString(12));
                MaTNTF.setText(rs1.getString(13));
                GioRaTF.setText(rs1.getString(14));
                
               
                
                
            }
        } 
        catch (Exception e) 
        {
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        SoBanT = new javax.swing.JTextField();
        SoKHTF = new javax.swing.JTextField();
        TraTrucTF = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        SoPhieuTF = new javax.swing.JTextField();
        GioVaoTF = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        GioRaTF = new javax.swing.JTextField();
        ConNoTF = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        GiamTF = new javax.swing.JTextField();
        VATTF = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        PhiDVTF = new javax.swing.JTextField();
        TongTienTF = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        LuuHDBT = new javax.swing.JButton();
        ThuNganCB = new javax.swing.JComboBox<>();
        MaKH = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        ThanhToanBT = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        TrangThaiTF = new javax.swing.JTextField();
        MaTNTF = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(102, 255, 255));

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
        jTable1.setGridColor(new java.awt.Color(102, 102, 0));
        jTable1.setSelectionBackground(new java.awt.Color(204, 255, 204));
        jTable1.setSelectionForeground(new java.awt.Color(255, 51, 51));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setText("Số Bàn:");

        jLabel2.setText("Số Khách:");

        jLabel3.setText("Trạng Thái");

        jLabel4.setText("Trả Trước:");

        SoBanT.setEditable(false);
        SoBanT.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        SoBanT.setForeground(new java.awt.Color(51, 51, 0));

        SoKHTF.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        TraTrucTF.setFont(new java.awt.Font("Segoe UI Historic", 1, 12)); // NOI18N
        TraTrucTF.setForeground(new java.awt.Color(255, 51, 51));
        TraTrucTF.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        TraTrucTF.setText("0");

        jLabel5.setText("Số Phiếu");

        SoPhieuTF.setEditable(false);
        SoPhieuTF.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        SoPhieuTF.setForeground(new java.awt.Color(51, 51, 0));

        GioVaoTF.setEditable(false);

        jLabel6.setText("Giờ Vào");

        jLabel7.setText("Giờ Ra");

        GioRaTF.setEditable(false);

        ConNoTF.setEditable(false);
        ConNoTF.setFont(new java.awt.Font("Segoe UI Historic", 1, 12)); // NOI18N
        ConNoTF.setForeground(new java.awt.Color(255, 51, 51));
        ConNoTF.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        ConNoTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConNoTFActionPerformed(evt);
            }
        });

        jLabel8.setText("Còn Nợ");

        jLabel9.setText("Khách Hàng");

        jLabel10.setText("Mã NV");

        jLabel11.setText("Tên NV");

        jLabel13.setText("Giảm (%)");

        jLabel14.setText("VAT (%)");

        jLabel15.setText("Phí DV (%)");

        TongTienTF.setEditable(false);
        TongTienTF.setFont(new java.awt.Font("Segoe UI Historic", 1, 12)); // NOI18N
        TongTienTF.setForeground(new java.awt.Color(255, 51, 51));
        TongTienTF.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel16.setText("Thanh Toán");

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton1.setForeground(new java.awt.Color(153, 0, 0));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Anh/icons8-update-20.png"))); // NOI18N
        jButton1.setText("Quay Lại");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 51, 51));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Anh/icons8-delete-20.png"))); // NOI18N
        jButton2.setText("Xóa Sản Phẩm");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        LuuHDBT.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        LuuHDBT.setForeground(new java.awt.Color(153, 0, 0));
        LuuHDBT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Anh/icons8-save-20.png"))); // NOI18N
        LuuHDBT.setText("Lưu Lại");
        LuuHDBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LuuHDBTActionPerformed(evt);
            }
        });

        MaKH.setEditable(false);
        MaKH.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        MaKH.setForeground(new java.awt.Color(51, 51, 0));

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel21.setText("Trần Xuân Quang-18I3");

        ThanhToanBT.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ThanhToanBT.setForeground(new java.awt.Color(153, 0, 0));
        ThanhToanBT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Anh/icons8-pay-wall-20.png"))); // NOI18N
        ThanhToanBT.setText("Thanh Toán");
        ThanhToanBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ThanhToanBTActionPerformed(evt);
            }
        });

        jTextField1.setEditable(false);

        TrangThaiTF.setEditable(false);

        MaTNTF.setEditable(false);

        jLabel12.setText("Mã TN");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(34, 34, 34)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel1)
                                            .addComponent(jLabel2)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(33, 33, 33)
                                        .addComponent(jLabel3)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(SoKHTF, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
                                    .addComponent(SoBanT)
                                    .addComponent(TrangThaiTF)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(TraTrucTF, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(29, 29, 29)
                                        .addComponent(jLabel5)
                                        .addGap(32, 32, 32)
                                        .addComponent(SoPhieuTF))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(37, 37, 37)
                                                .addComponent(jLabel7))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel6)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(GioVaoTF)
                                            .addComponent(GioRaTF, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(17, 17, 17)
                                        .addComponent(jLabel9)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(MaKH))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(43, 43, 43)
                                                .addComponent(jLabel10)
                                                .addGap(9, 9, 9))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jLabel12)
                                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jTextField1)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(ThuNganCB, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE))
                                            .addComponent(MaTNTF)))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(ConNoTF, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(GiamTF, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
                                    .addComponent(VATTF)
                                    .addComponent(PhiDVTF, javax.swing.GroupLayout.Alignment.TRAILING)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(TongTienTF, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel21))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(LuuHDBT, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66)
                .addComponent(ThanhToanBT, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(69, 69, 69)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel5)
                                .addComponent(SoPhieuTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1)
                                    .addComponent(SoBanT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(GioVaoTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel6))
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel2)
                                        .addComponent(SoKHTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel7)
                                    .addComponent(GioRaTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(TrangThaiTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel8)
                                .addComponent(ConNoTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel16)
                                .addComponent(TongTienTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(MaTNTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel12))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel4)
                                .addComponent(TraTrucTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel13)
                        .addComponent(GiamTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(MaKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(jLabel14)
                            .addComponent(VATTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(PhiDVTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ThuNganCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LuuHDBT)
                    .addComponent(jButton2)
                    .addComponent(ThanhToanBT)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel21))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void ConNoTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConNoTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ConNoTFActionPerformed

    private void LuuHDBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LuuHDBTActionPerformed
        // TODO add your handling code here:
        if ( TrangThaiTF.getText().trim().equals("Chưa TT") )
        {
            CT_HoaDon HD = new CT_HoaDon();
            String MaHD = SoPhieuTF.getText().trim();
            int SoKhach = Integer.valueOf(SoKHTF.getText().trim());
            float Giam = Float.valueOf( GiamTF.getText().trim() );
            float VAT = Float.valueOf( VATTF.getText().trim() );
            float PhiDV = Float.valueOf( PhiDVTF.getText().trim() );
            float TraTruoc = 0;
            if ( TraTrucTF.getText().trim().length() >0 )
                TraTruoc = Float.valueOf( TraTrucTF.getText().trim() );
            
            HD.LuuHD(MaHD, SoKhach, Giam, VAT, PhiDV, TraTruoc);
        }
        else
            JOptionPane.showMessageDialog(null, "Không Thể chỉnh Sủa Chứng Từ Đã Thanh Toán");
        
       
       
        
    }//GEN-LAST:event_LuuHDBTActionPerformed

    private void ThanhToanBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ThanhToanBTActionPerformed
        // TODO add your handling code here:
        if ( TrangThaiTF.getText().trim().equals("Chưa TT") )
        {
            CT_HoaDon HD = new CT_HoaDon();
            String MaHD = SoPhieuTF.getText().trim();
            String SoBan = SoBanT.getText().trim();
            int SoKhach = Integer.valueOf(SoKHTF.getText().trim());
            float Giam = Float.valueOf( GiamTF.getText().trim() );
            float VAT = Float.valueOf( VATTF.getText().trim() );
            float PhiDV = Float.valueOf( PhiDVTF.getText().trim() );
            float TraTruoc = 0;
            if ( TraTrucTF.getText().trim().length() >0 )
                TraTruoc = Float.valueOf( TraTrucTF.getText().trim() );
            String  MaNV = jTextField1.getText().trim();
            String GioVao = GioVaoTF.getText().trim();
            HD.ThanhToan(MaHD, SoBan, SoKhach, Giam, VAT, PhiDV, TraTruoc, MaNV, GioVao);
        }
        else
            JOptionPane.showMessageDialog(null, "Không Thể chỉnh Sủa Chứng Từ Đã Thanh Toán");
    }//GEN-LAST:event_ThanhToanBTActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        if ( TrangThaiTF.getText().trim().equals("Chưa TT") )
        {
            CT_HoaDon HD = new CT_HoaDon();
            HD.XoaSP( SoPhieuTF.getText().trim() , (String) jTable1.getValueAt(jTable1.getSelectedRow(), 0));
            LoadDongCTTB(SoPhieuTF.getText().trim());
            LoadTopHD( SoPhieuTF.getText().trim() );
        }
        else
            JOptionPane.showMessageDialog(null, "Không Thể chỉnh Sủa Chứng Từ Đã Thanh Toán");
        
        jButton2.setVisible(false);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        jButton2.setVisible(true);
    }//GEN-LAST:event_jTable1MouseClicked

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
            java.util.logging.Logger.getLogger(HoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HoaDon().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField ConNoTF;
    private javax.swing.JTextField GiamTF;
    private javax.swing.JTextField GioRaTF;
    private javax.swing.JTextField GioVaoTF;
    private javax.swing.JButton LuuHDBT;
    private javax.swing.JTextField MaKH;
    private javax.swing.JTextField MaTNTF;
    private javax.swing.JTextField PhiDVTF;
    private javax.swing.JTextField SoBanT;
    private javax.swing.JTextField SoKHTF;
    private javax.swing.JTextField SoPhieuTF;
    private javax.swing.JButton ThanhToanBT;
    private javax.swing.JComboBox<String> ThuNganCB;
    private javax.swing.JTextField TongTienTF;
    private javax.swing.JTextField TraTrucTF;
    private javax.swing.JTextField TrangThaiTF;
    private javax.swing.JTextField VATTF;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
