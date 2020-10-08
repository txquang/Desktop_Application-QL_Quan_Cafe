
import ThucThi.SanPham;
import ThucThi.SanPham1;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.Vector;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JToggleButton;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;

public class Form_Chinh extends javax.swing.JFrame implements ActionListener
{

    /**
     * Creates new form NewJFrame
     */
    public Form_Chinh() 
    {
        initComponents();
        LoadDB();
        LoadTableKH();
        LoadTableSP();
        SoDoBan();
        NhomHang();
        DanhSachHD();
        DanhSachMonPN.setVisible(false);
       
        //jPanel6.setVisible(true);
    }
    public Form_Chinh(CT_DangNhap a)
    {
        initComponents();
        LoadDB();
        LoadTableKH();
        LoadTableSP();
        SoDoBan();
        NhomHang();
        DanhSachMonPN.setVisible(false);
        XoaHDBT.setVisible(false);
        MaNVLB.setText(a.getMaN());
        TenNVLB.setText(a.getTen());
        DanhSachHD();
       
    }
    
    
    //Ham load du lị-----------------------------
    
    java.sql.Connection conn;
    Statement truyvan;
    ResultSet rs,rs1;
    String S;
    private DefaultTableModel dtm=new DefaultTableModel();
    private DefaultTableModel dtm1=new DefaultTableModel();
    private DefaultTableModel dtm2=new DefaultTableModel();
    private DefaultTableModel dtm3=new DefaultTableModel();
    
    public void LoadDB()
    {
        try
	{   
            CT_ConnetDB x =new CT_ConnetDB();
            conn = x.ConnetDB();
            truyvan = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            LoadCB("SELECT DISTINCT MaNhom from NhomKhach", 1);
            LoadCB("SELECT DISTINCT MaNhom from NhomHang", 2);
            LoadCB("SELECT DISTINCT MaDVT from DonViTinh", 3);
        }
        catch (SQLException e)
        {
            JOptionPane.showMessageDialog(null, "Kết nối sql thất bại");  
        }
    }

    public void LoadCB(String S,int a)
    {
        try
	{
            rs= truyvan.executeQuery(S);
            while (rs.next())
            { 
                if( a == 1 )
                    MaNhomCB.addItem(rs.getString(1));
                else
                    if( a == 2 )
                        MaNhomCB2.addItem(rs.getString(1));
                    else
                        jComboBox6.addItem(rs.getString(1));
            }
        }
        catch(SQLException ex)
	{}
    }
    public void LoadCBBanPhong()
    {
        try
	{
            jComboBox1.removeAllItems();
            rs= truyvan.executeQuery("SELECT SoBan FROM BanPhong WHERE NoUse = 0");
            while (rs.next())
            { 
               jComboBox1.addItem(rs.getString(1));
            }
        }
        catch(SQLException ex)
	{}
    }
    
    public void DanhSachHD()
    {
        CT_HoaDon CTHD = new CT_HoaDon();
        jTable1.setModel( CTHD.HienDSHD() );
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(10);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(10);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(10);
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(10);
        jTable1.getColumnModel().getColumn(4).setPreferredWidth(60);
        jTable1.getColumnModel().getColumn(5).setPreferredWidth(10);
        jTable1.getColumnModel().getColumn(6).setPreferredWidth(10);
        jTable1.getColumnModel().getColumn(7).setPreferredWidth(50);
        jTable1.getColumnModel().getColumn(8).setPreferredWidth(90);
        jTable1.getColumnModel().getColumn(9).setPreferredWidth(90);
        jTable1.getColumnModel().getColumn(10).setPreferredWidth(10);
    }
    public void LoadTableKH()
    {	
	try 
	{ 	
            Vector<String> row,colum;//lay gia tri
	    colum=new Vector<String>();
	    rs=truyvan.executeQuery("select * from KhachHang");
	    ResultSetMetaData metaData =rs.getMetaData();
	          
	    for (int i=1; i<=8;i++)//so cot
	    {
	        colum.add(metaData.getColumnName(i));
	    }
	    dtm2.setColumnIdentifiers(colum);
	    while (rs.next())
	    {
	        	
	        row = new Vector<String>();
	        for (int i=1; i<=8;i++)//so hang
	           row.add(rs.getString(i));
                dtm2.addRow(row);
	              
	    }
	    KhachHangBT.setModel(dtm2);
	    } 
	      
	    catch (SQLException ex) 
	    {
	    	
	        System.out.print("loi"+ex);
                this.dispose();
	    }
	 } 
    // LoadTable dữ liệu DataBase vào bảng
    public void LoadTableSP()
    {
		
     
	try 
	{
	    	
            Vector<String> row,colum;//lay gia tri
	    colum=new Vector<String>();
	    rs=truyvan.executeQuery("SELECT MaSP AS 'Mã SP', MaNhom AS 'Mã Nhóm',\n" +
                                    "		 TenSP AS 'Tên Sản Phẩm', MaDVT AS 'Mã ĐVT', SLDK AS 'SL', GTDK AS 'Giá' \n" +
                                    "FROM SanPham \n" +
                                    "ORDER BY MaDVT DESC");
	    ResultSetMetaData metaData =rs.getMetaData();
	          
	    for (int i=1; i<=6;i++)//so cot
	    {
	        colum.add(metaData.getColumnName(i));
	    }
	    dtm3.setColumnIdentifiers(colum);
	    while (rs.next())
	    {
	        	
	        row = new Vector<String>();
	        for (int i=1; i<=6;i++)//so hang
	           row.add(rs.getString(i));
                dtm3.addRow(row);
	              
	    }
	    jTable3.setModel(dtm3);
	    } 
	      
	    catch (SQLException ex) 
	    {
	    	
	        System.out.print("loi"+ex);
                this.dispose();
	    }
        jTable3.getColumnModel().getColumn(0).setPreferredWidth(30);
        jTable3.getColumnModel().getColumn(1).setPreferredWidth(30);
        jTable3.getColumnModel().getColumn(2).setPreferredWidth(130);
        jTable3.getColumnModel().getColumn(3).setPreferredWidth(30);
        jTable3.getColumnModel().getColumn(4).setPreferredWidth(30);
        jTable3.getColumnModel().getColumn(5).setPreferredWidth(60);
    } 
    public void SoDoBan()
    {
        SoDoBanPN.setVisible(false);
        SoDoBanPN.removeAll();
        try 
        {
            rs=truyvan.executeQuery("select SoBan,NoUse from BanPhong");
            while (rs.next())
            {
	      
                JButton button = new JButton("Bàn "+rs.getString(1));
               
                if ( rs.getString(2).equals("1") )
                    button.setBackground( new Color(255, 255, 153));
                else
                    button.setBackground( new Color(191,239,255));
               
                button.setActionCommand(rs.getString(1));
                button.addActionListener( this);
                SoDoBanPN.add(button); 
	    }
        } 
        catch (Exception e) 
        {
            System.err.println(e);
        }
        SoDoBanPN.setVisible(true);
    }
//---------------------------------------
    public void NhomHang()//hien liên ds nhóm sp
    {
        try 
        {
            rs=truyvan.executeQuery("SELECT MaNhom , TenNhom FROM NhomHang \n" +
                                    "	WHERE MaNhom NOT LIKE 'MNCB' \n" +
                                    "       AND MaNhom NOT LIKE 'MNNL'");
            ButtonGroup buttonGroup = new ButtonGroup();
            while (rs.next())
            {
                JToggleButton button = new JToggleButton(rs.getString(2));
                button.setBackground(Color.getHSBColor(0,204,204));
                button.setActionCommand(rs.getString(1));
                button.addActionListener( this);
                buttonGroup.add(button);
                DanhSachMonPN.add(button); 
	    }
        } 
        catch (Exception e) 
        {
            System.err.println(e);
        }
    }
    
 //--------------------------------------------
    public void SanPham(String MaNhom)//hiện liên ds sản phẩm
    {
        MonAnPN.setVisible(false);
        MonAnPN.removeAll();
        try 
        {
            rs=truyvan.executeQuery("SELECT MaSP ,TenSP,GTDK,MaDVT FROM SanPham WHERE MaNhom = '"+MaNhom+"'");
            while (rs.next())
            {
                DecimalFormat formatter = new DecimalFormat("#,###,###");
                String S = formatter.format(Double.valueOf(rs.getString(3)));
                String html = "<html>"
                        + "<div style=\"text-align: center;\"><span style=\" color: #008000; font-size: 140%;\"><strong>"+rs.getString(2)+"</strong></span></div>\n" +
                           "<div style=\"text-align: center;\"><strong><span style=\"color: #ff6600;\">"+S+".đ  "+"<span style=\"color: #808080;\">"+rs.getString(4)+"</span></span></strong></div>"
                        + "</html>" ;
                //JButton button = new JButton(rs.getString(2)+"    "+rs.getString(3)+"đ");
                JButton button = new JButton(html);
                button.setBackground( new Color(250,240,230));
                button.setActionCommand(rs.getString(1));
                button.addActionListener( this);
                MonAnPN.add(button); 
	    }
        } 
        catch (Exception e) 
        {
            System.err.println(e);
        }
        MonAnPN.setVisible(true);
    }
    
    //---------------------------------------------------
    public void actionPerformed( ActionEvent EV)//------SuKien
    {
        String S = EV.getActionCommand();//lấy chuổi của nút
        if( S.compareTo("SB") >1 && S.compareTo("SB") <3 )//SK của khi bấn vào ds bàn
        {
            SoBanLB.setText(S);
            DanhSachMonPN.setVisible(true);
            
            CT_BanPhong CTBP =new CT_BanPhong();
            CT_HoaDon CTHD = new CT_HoaDon();
            
            if( CTBP.KiemTraBanPhong(S).equals("1") )//bàn phong mà dã có nguoi su dụng
            {
                MonAnTB.setModel( CTHD.HienCT(S) );//tra ve bang ban do
                ChungTuLB.setText( CTHD.LayMaCT(S) );
                XoaHDBT.setVisible(true);
            }
            else//bàn phong mà k co có nguoi su dung
            {
                MonAnTB.setModel(new DefaultTableModel());
                ChungTuLB.setText("N/A");
                XoaHDBT.setVisible(false);
            }
        }
        else 
        {
            if( S.compareTo("SP") >0 )//SK khi bấm vào Từng món ăn
            {
                SpinnerNumberModel sModel = new SpinnerNumberModel(1, -5, 10, 1);
                JSpinner spinner = new JSpinner(sModel);
                int option = JOptionPane.showOptionDialog(null, spinner, "Vui Lòng Nhâp Số Lượng", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
                if (option == JOptionPane.OK_OPTION) 
                {
                    String MaHD = ChungTuLB.getText().trim();
                    String MaSP = S.trim();
                    int SoLuong = (int) spinner.getValue();
                    CT_HoaDon CTHD = new CT_HoaDon();
                    CTHD.ThemSPVaoHD(MaHD, MaSP, SoLuong);
                    MonAnTB.setModel( CTHD.HienCT(S) );
                } 
            }
            else
                if( S.compareTo("MN") >0 )//SK khi bấm vào Danh muc SP
                   SanPham(S);
                
             
            
        }
    }
    
    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        ThuNgan = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        MonAnTB = new javax.swing.JTable();
        jLabel17 = new javax.swing.JLabel();
        TinhTienBT = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        BanMoiBT = new javax.swing.JButton();
        SoBanLB = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        ChungTuLB = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        SoDoBanPN = new javax.swing.JPanel();
        CNBanBT = new javax.swing.JButton();
        ChuyenBanBT = new javax.swing.JButton();
        XoaHDBT = new javax.swing.JButton();
        DanhSachMonPN = new javax.swing.JPanel();
        MonAnPN = new javax.swing.JPanel();
        KhachHang = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        KhachHangBT = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        MaKHTF = new javax.swing.JTextField();
        TenKHTF = new javax.swing.JTextField();
        MaNhomCB = new javax.swing.JComboBox<>();
        DiaChiKHTF = new javax.swing.JTextField();
        MaThueTF = new javax.swing.JTextField();
        ThuDKTF = new javax.swing.JTextField();
        TraCKTF = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        SDTTF = new javax.swing.JTextField();
        ThemNVBT = new javax.swing.JButton();
        SuaKHBT = new javax.swing.JButton();
        XoaKHBT = new javax.swing.JButton();
        jLabel33 = new javax.swing.JLabel();
        SanPham = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jLabel38 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        TenKHTF2 = new javax.swing.JTextField();
        MaNhomCB2 = new javax.swing.JComboBox<>();
        ThuDKTF2 = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        ThemSPBT = new javax.swing.JButton();
        SuaSPBT = new javax.swing.JButton();
        XoaSPBT = new javax.swing.JButton();
        jComboBox6 = new javax.swing.JComboBox<>();
        jSpinner6 = new javax.swing.JSpinner();
        jTextField3 = new javax.swing.JTextField();
        Khác = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        MaNVLB = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        TenNVLB = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTabbedPane1.setBackground(new java.awt.Color(153, 255, 102));
        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        ThuNgan.setBackground(new java.awt.Color(153, 255, 204));

        jPanel8.setLayout(new java.awt.GridLayout(5, 1, 10, 10));

        MonAnTB.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        MonAnTB.setModel(new javax.swing.table.DefaultTableModel(
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
        MonAnTB.setGridColor(new java.awt.Color(0, 255, 102));
        MonAnTB.setSelectionBackground(new java.awt.Color(153, 255, 153));
        MonAnTB.setSelectionForeground(new java.awt.Color(199, 7, 27));
        jScrollPane3.setViewportView(MonAnTB);

        jLabel17.setBackground(new java.awt.Color(255, 255, 255));
        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(51, 153, 255));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Danh Sách các món ");

        TinhTienBT.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        TinhTienBT.setForeground(new java.awt.Color(51, 51, 255));
        TinhTienBT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Anh/icons8-pay-wall-20.png"))); // NOI18N
        TinhTienBT.setText("Thanh Toán");
        TinhTienBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TinhTienBTActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 0, 51));
        jLabel18.setText("Bàn Số:");

        BanMoiBT.setBackground(new java.awt.Color(255, 255, 255));
        BanMoiBT.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BanMoiBT.setForeground(new java.awt.Color(102, 0, 0));
        BanMoiBT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Anh/icons8-new-20.png"))); // NOI18N
        BanMoiBT.setText("Tạo HD Mới");
        BanMoiBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BanMoiBTActionPerformed(evt);
            }
        });

        SoBanLB.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        SoBanLB.setForeground(new java.awt.Color(204, 0, 0));
        SoBanLB.setText("N/A");

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 0, 51));
        jLabel20.setText("Chứng từ:");

        ChungTuLB.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        ChungTuLB.setForeground(new java.awt.Color(204, 0, 0));
        ChungTuLB.setText("N/A");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SoBanLB, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ChungTuLB, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                                .addComponent(BanMoiBT, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(TinhTienBT, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ChungTuLB, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(SoBanLB, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TinhTienBT)
                    .addComponent(BanMoiBT))
                .addContainerGap())
        );

        SoDoBanPN.setLayout(new java.awt.GridLayout(4, 3, 5, 5));
        jScrollPane1.setViewportView(SoDoBanPN);

        CNBanBT.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        CNBanBT.setForeground(new java.awt.Color(102, 0, 0));
        CNBanBT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Anh/icons8-update-20.png"))); // NOI18N
        CNBanBT.setText("Cập Nhật");
        CNBanBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CNBanBTActionPerformed(evt);
            }
        });

        ChuyenBanBT.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ChuyenBanBT.setForeground(new java.awt.Color(102, 0, 0));
        ChuyenBanBT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Anh/icons8-topic-moved-20.png"))); // NOI18N
        ChuyenBanBT.setText("Chuyển");
        ChuyenBanBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChuyenBanBTActionPerformed(evt);
            }
        });

        XoaHDBT.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        XoaHDBT.setForeground(new java.awt.Color(255, 102, 51));
        XoaHDBT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Anh/icons8-connected-20.png"))); // NOI18N
        XoaHDBT.setText("Xóa HD");
        XoaHDBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                XoaHDBTActionPerformed(evt);
            }
        });

        DanhSachMonPN.setBackground(new java.awt.Color(153, 255, 204));
        DanhSachMonPN.setLayout(new java.awt.GridLayout(5, 1, 10, 10));

        MonAnPN.setBackground(new java.awt.Color(153, 255, 204));
        MonAnPN.setLayout(new java.awt.GridLayout(5, 1, 10, 10));

        javax.swing.GroupLayout ThuNganLayout = new javax.swing.GroupLayout(ThuNgan);
        ThuNgan.setLayout(ThuNganLayout);
        ThuNganLayout.setHorizontalGroup(
            ThuNganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ThuNganLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ThuNganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(ThuNganLayout.createSequentialGroup()
                        .addComponent(CNBanBT)
                        .addGap(13, 13, 13)
                        .addComponent(ChuyenBanBT, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(XoaHDBT, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(DanhSachMonPN, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(MonAnPN, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );
        ThuNganLayout.setVerticalGroup(
            ThuNganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ThuNganLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ThuNganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ThuNganLayout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(ThuNganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(CNBanBT)
                            .addComponent(ChuyenBanBT)
                            .addComponent(XoaHDBT)))
                    .addComponent(DanhSachMonPN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(MonAnPN, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("     Sơ Đồ  ", ThuNgan);

        KhachHang.setBackground(new java.awt.Color(153, 255, 204));

        KhachHangBT.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        KhachHangBT.setModel(new javax.swing.table.DefaultTableModel(
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
        KhachHangBT.setGridColor(new java.awt.Color(153, 255, 153));
        KhachHangBT.setSelectionBackground(new java.awt.Color(204, 204, 255));
        KhachHangBT.setSelectionForeground(new java.awt.Color(204, 0, 0));
        KhachHangBT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                KhachHangBTMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(KhachHangBT);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 592, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 572, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(48, 48, 48)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 153, 153));
        jLabel10.setText("Mã KH");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 153, 153));
        jLabel11.setText("Mã Nhóm");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 153, 153));
        jLabel12.setText("Tên KH");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 153, 153));
        jLabel13.setText("Địa Chỉ");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 153, 153));
        jLabel14.setText("Mã Thuế");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 153, 153));
        jLabel15.setText("Thu DK");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 153, 153));
        jLabel16.setText("Trả CK");

        MaKHTF.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        MaKHTF.setForeground(new java.awt.Color(255, 0, 0));
        MaKHTF.setDisabledTextColor(new java.awt.Color(255, 0, 0));
        MaKHTF.setSelectionColor(new java.awt.Color(255, 255, 255));

        TenKHTF.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        ThuDKTF.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        TraCKTF.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel19.setBackground(new java.awt.Color(255, 255, 255));
        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 51, 0));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("Thông Tin Chi Tiết");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 153, 153));
        jLabel1.setText("SDT");

        ThemNVBT.setBackground(new java.awt.Color(204, 255, 255));
        ThemNVBT.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ThemNVBT.setForeground(new java.awt.Color(102, 0, 0));
        ThemNVBT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Anh/icons8-add-property-20.png"))); // NOI18N
        ThemNVBT.setText("Thêm KH");
        ThemNVBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ThemNVBTActionPerformed(evt);
            }
        });

        SuaKHBT.setBackground(new java.awt.Color(204, 255, 255));
        SuaKHBT.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        SuaKHBT.setForeground(new java.awt.Color(102, 0, 0));
        SuaKHBT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Anh/icons8-edit-20 (2).png"))); // NOI18N
        SuaKHBT.setText("Sửa KH");
        SuaKHBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SuaKHBTActionPerformed(evt);
            }
        });

        XoaKHBT.setBackground(new java.awt.Color(204, 255, 255));
        XoaKHBT.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        XoaKHBT.setForeground(new java.awt.Color(255, 51, 51));
        XoaKHBT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Anh/icons8-delete-20.png"))); // NOI18N
        XoaKHBT.setText("Xóa KH");
        XoaKHBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                XoaKHBTActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel10)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(MaKHTF, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel14)
                                        .addGap(18, 18, 18)
                                        .addComponent(MaThueTF, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(MaNhomCB, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel1)
                                        .addGap(18, 18, 18)
                                        .addComponent(SDTTF, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(DiaChiKHTF, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(TenKHTF, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addGap(18, 18, 18)
                                .addComponent(TraCKTF))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addGap(18, 18, 18)
                                .addComponent(ThuDKTF, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(155, 155, 155))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(ThemNVBT, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(SuaKHBT, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(XoaKHBT, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                        .addGap(21, 21, 21))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(MaKHTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(MaNhomCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(MaThueTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(SDTTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TenKHTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(DiaChiKHTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(ThuDKTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(TraCKTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ThemNVBT)
                    .addComponent(SuaKHBT)
                    .addComponent(XoaKHBT))
                .addGap(22, 22, 22))
        );

        jLabel33.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel33.setText("Trần Xuân Quang-18I3");

        javax.swing.GroupLayout KhachHangLayout = new javax.swing.GroupLayout(KhachHang);
        KhachHang.setLayout(KhachHangLayout);
        KhachHangLayout.setHorizontalGroup(
            KhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(KhachHangLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(KhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, KhachHangLayout.createSequentialGroup()
                        .addComponent(jLabel33)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, KhachHangLayout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        KhachHangLayout.setVerticalGroup(
            KhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(KhachHangLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(KhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel33))
        );

        jTabbedPane1.addTab("Khách Hàng", KhachHang);

        SanPham.setBackground(new java.awt.Color(204, 255, 255));

        jTable3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jTable3.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable3.setGridColor(new java.awt.Color(153, 255, 153));
        jTable3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable3MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable3);

        jLabel38.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(255, 51, 0));
        jLabel38.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel38.setText("Danh Sách Các Món");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 638, Short.MAX_VALUE)
                    .addComponent(jLabel38, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(51, 153, 255));
        jLabel29.setText("Mã SP");

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(51, 153, 255));
        jLabel30.setText("Mã Nhóm");

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(51, 153, 255));
        jLabel31.setText("Tên SP");

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(51, 153, 255));
        jLabel32.setText("MaDVT");

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(51, 153, 255));
        jLabel34.setText("GIA");

        TenKHTF2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TenKHTF2ActionPerformed(evt);
            }
        });

        ThuDKTF2.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel36.setBackground(new java.awt.Color(255, 255, 255));
        jLabel36.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(51, 153, 255));
        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel36.setText("Thông Tin Chi Tiết");

        jLabel37.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(51, 153, 255));
        jLabel37.setText("SO LUONG");

        ThemSPBT.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ThemSPBT.setForeground(new java.awt.Color(102, 0, 0));
        ThemSPBT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Anh/icons8-add-20 (1).png"))); // NOI18N
        ThemSPBT.setText("THÊM");
        ThemSPBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ThemSPBTActionPerformed(evt);
            }
        });

        SuaSPBT.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        SuaSPBT.setForeground(new java.awt.Color(102, 0, 0));
        SuaSPBT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Anh/icons8-edit-20 (2).png"))); // NOI18N
        SuaSPBT.setText("SỬA");
        SuaSPBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SuaSPBTActionPerformed(evt);
            }
        });

        XoaSPBT.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        XoaSPBT.setForeground(new java.awt.Color(255, 51, 51));
        XoaSPBT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Anh/icons8-delete-20.png"))); // NOI18N
        XoaSPBT.setText("XÓA");
        XoaSPBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                XoaSPBTActionPerformed(evt);
            }
        });

        jSpinner6.setModel(new javax.swing.SpinnerNumberModel(1, 0, 1000, 1));

        jTextField3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTextField3.setForeground(new java.awt.Color(204, 0, 0));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel36, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel30)
                            .addComponent(jLabel29)
                            .addComponent(jLabel31)
                            .addComponent(jLabel32)
                            .addComponent(jLabel34)
                            .addComponent(jLabel37))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(MaNhomCB2, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TenKHTF2, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSpinner6, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ThuDKTF2, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(ThemSPBT, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(SuaSPBT, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(XoaSPBT, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(MaNhomCB2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TenKHTF2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel31))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel37)
                    .addComponent(jSpinner6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34)
                    .addComponent(ThuDKTF2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ThemSPBT)
                    .addComponent(SuaSPBT)
                    .addComponent(XoaSPBT))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout SanPhamLayout = new javax.swing.GroupLayout(SanPham);
        SanPham.setLayout(SanPhamLayout);
        SanPhamLayout.setHorizontalGroup(
            SanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SanPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        SanPhamLayout.setVerticalGroup(
            SanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SanPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(SanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Sản Phẩm", SanPham);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 102, 102));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Danh Sách Các Chứng Từ");

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
        jTable1.setGridColor(new java.awt.Color(102, 255, 102));
        jTable1.setSelectionForeground(new java.awt.Color(102, 0, 0));
        jScrollPane4.setViewportView(jTable1);

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(153, 0, 0));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Anh/iconfinder_Sync_13265.png"))); // NOI18N
        jButton2.setText("  Làm Mới");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(153, 0, 0));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Anh/icons8-show-property-40.png"))); // NOI18N
        jButton3.setText("Xem Chi Tiết");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 51, 0));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Anh/icons8-delete-bin-40.png"))); // NOI18N
        jButton4.setText("Xóa CT");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton5.setForeground(new java.awt.Color(153, 0, 0));
        jButton5.setText("Báo Cáo Doanh Thu");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout KhácLayout = new javax.swing.GroupLayout(Khác);
        Khác.setLayout(KhácLayout);
        KhácLayout.setHorizontalGroup(
            KhácLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(KhácLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(KhácLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 717, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(KhácLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE))
                .addContainerGap())
        );
        KhácLayout.setVerticalGroup(
            KhácLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(KhácLayout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(KhácLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, KhácLayout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(46, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Danh Sách CT", Khác);

        jPanel10.setBackground(new java.awt.Color(204, 255, 255));
        jPanel10.setForeground(new java.awt.Color(51, 0, 255));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 51, 51));
        jLabel5.setText("Bán Hàng");

        MaNVLB.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        MaNVLB.setForeground(new java.awt.Color(204, 51, 0));
        MaNVLB.setText("jLabel20");
        MaNVLB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MaNVLBMouseClicked(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Anh/icons8-logout-rounded-left-20.png"))); // NOI18N
        jButton1.setText("Đăng Xuất");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 51, 51));
        jLabel2.setText("Xin Chào :");

        TenNVLB.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TenNVLB.setForeground(new java.awt.Color(204, 51, 0));
        TenNVLB.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        TenNVLB.setText("jLabel3");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(206, 206, 206)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(97, 97, 97)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(MaNVLB, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(TenNVLB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(MaNVLB, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TenNVLB))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addComponent(jButton1))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void ChuyenBanBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChuyenBanBTActionPerformed
        // TODO add your handling code here:
        LoadCBBanPhong();
        int option = JOptionPane.showOptionDialog(null, jComboBox1, "Vui Lòng Nhâp Số Lượng", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
        if (option == JOptionPane.OK_OPTION) 
        {
            String MaHD = ChungTuLB.getText().trim();
            String  BanCu= SoBanLB.getText().trim();
            String  BanMoi =  jComboBox1.getSelectedItem().toString().trim();
            CT_HoaDon CTHD = new CT_HoaDon();
            CTHD.ChuyenBan(BanCu, BanMoi, MaHD);
            SoDoBan();
        }
        
    }//GEN-LAST:event_ChuyenBanBTActionPerformed

    private void CNBanBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CNBanBTActionPerformed
        // TODO add your handling code here:
        SoDoBanPN.removeAll();
        SoDoBanPN.setVisible(false);
        SoDoBan();
        SoDoBanPN.setVisible(true);
    }//GEN-LAST:event_CNBanBTActionPerformed

    private void BanMoiBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BanMoiBTActionPerformed
        
        CT_HoaDon HD = new CT_HoaDon();
        String SoBan = SoBanLB.getText();
        String MaNV = MaNVLB.getText();
        HD.TaoHD(SoBan, MaNV);
        SoDoBan();
    }//GEN-LAST:event_BanMoiBTActionPerformed

    private void KhachHangBTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_KhachHangBTMouseClicked
        // TODO add your handling code here:
        int qa = KhachHangBT.getSelectedRow();
        MaKHTF  .setText((String) KhachHangBT.getValueAt(qa, 0));
        TenKHTF  .setText((String) KhachHangBT.getValueAt(qa, 2));
        DiaChiKHTF.setText((String) KhachHangBT.getValueAt(qa, 3));
        SDTTF .setText((String) KhachHangBT.getValueAt(qa, 4));
        MaThueTF .setText((String) KhachHangBT.getValueAt(qa, 5));
        ThuDKTF .setText((String) KhachHangBT.getValueAt(qa, 6));
        TraCKTF .setText((String) KhachHangBT.getValueAt(qa, 7));
                  
    }//GEN-LAST:event_KhachHangBTMouseClicked

    private void jTable3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MouseClicked
        // TODO add your handling code here:
        int qa = jTable3.getSelectedRow();
        jTextField3.setText((String) jTable3.getValueAt(qa, 0));
        TenKHTF2.setText((String) jTable3.getValueAt(qa, 2));
        ThuDKTF2.setText((String) jTable3.getValueAt(qa, 5));
        String a = jTable3.getValueAt(qa, 4).toString();
        //int a =Integer.valueOf((String)jTable3.getValueAt(qa, 5));
        jSpinner6.setValue(Integer.valueOf(a));
    }//GEN-LAST:event_jTable3MouseClicked

    private void ThemSPBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ThemSPBTActionPerformed
        // TODO add your handling code here:
        SanPham SP = new SanPham();
        SanPham1 SP1 = new SanPham1();
        SP.setMaSP( jTextField3.getText().trim() );
        SP.setMaNhom( MaNhomCB2.getSelectedItem().toString().trim() );
        SP.setTenSP( TenKHTF2.getText().trim() );
        SP.setMaDVT( jComboBox6.getSelectedItem().toString().trim() );
        int a = Integer.valueOf( jSpinner6.getValue().toString() );
        SP.setSLDK(a);
        Float b = Float.valueOf( ThuDKTF2.getText().trim() );
        SP.setSLCK(b);
        if (SP1.ThemSP(SP) >0) 
        {
            JOptionPane.showMessageDialog(this, "Sản Phẩm Đã Được Thêm");
        } 
        else 
        {
            JOptionPane.showMessageDialog(this, "Sản Phẩm Không Được Thêm");
        }
        dtm3.setRowCount(0);
        LoadTableSP();
    }//GEN-LAST:event_ThemSPBTActionPerformed

    private void TenKHTF2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TenKHTF2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TenKHTF2ActionPerformed

    private void SuaSPBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SuaSPBTActionPerformed
        // TODO add your handling code here:
        SanPham SP = new SanPham();
        SanPham1 SP1 = new SanPham1();
        SP.setMaSP( jTextField3.getText().trim() );
        SP.setMaNhom( MaNhomCB2.getSelectedItem().toString().trim() );
        SP.setTenSP( TenKHTF2.getText().trim() );
        SP.setMaDVT( jComboBox6.getSelectedItem().toString().trim() );
        int a = Integer.valueOf( jSpinner6.getValue().toString() );
        SP.setSLDK(a);
        Float b = Float.valueOf( ThuDKTF2.getText().trim() );
        SP.setSLCK(b);
        if (SP1.CapNhatSP(SP) >0) 
        {
            JOptionPane.showMessageDialog(this, "Sản Phẩm Đã Được Sủa");
        } 
        else 
        {
            JOptionPane.showMessageDialog(this, "Sản Phẩm Không Được Sửa");
        }
        dtm3.setRowCount(0);
        LoadTableSP();
    }//GEN-LAST:event_SuaSPBTActionPerformed

    private void XoaSPBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_XoaSPBTActionPerformed
        // TODO add your handling code here:
        int returnValue = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa Sản Phẩm  " + jTextField3.getText().toString().trim(), "Bạn chắc chứ?", JOptionPane.YES_NO_OPTION);
        if (returnValue == JOptionPane.YES_OPTION) 
        {
            SanPham1 SP1 = new SanPham1();
            if ( SP1.XoaSP( jTextField3.getText().trim()) >0) 
            {

                JOptionPane.showMessageDialog(this, "Sản Phẩm Đã Được Xóa");
            } else {
                JOptionPane.showMessageDialog(this, "Sản Phẩm Không Được Xóa");
            }
        }
        dtm3.setRowCount(0);
        LoadTableSP();
    }//GEN-LAST:event_XoaSPBTActionPerformed

    private void ThemNVBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ThemNVBTActionPerformed
        // TODO add your handling code here:
        String sql = "insert into KhachHang values(?,?,?,?,?,?,?,?)";
        try 
        {
            
            PreparedStatement ps = conn.prepareStatement(sql);
           
            //ps.setString(6, SP.getMaSP());
            ps.setString(2, MaNhomCB.getSelectedItem().toString().trim());
            ps.setString(3, TenKHTF.getText().trim());
            ps.setString(4, DiaChiKHTF.getText().trim());
            ps.setString(5, SDTTF.getText().trim());
            ps.setString(6, MaThueTF.getText().trim());
            ps.setFloat(7, Float.valueOf(ThuDKTF.getText().trim()));
            ps.setFloat(8, Float.valueOf(TraCKTF.getText().trim()));
            ps.setString(1, MaKHTF.getText().trim());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Thêm Khách Hàng "+MaKHTF.getText().trim()+" Thành Công");
        } 
        catch (Exception e) 
        {
            //JOptionPane.showMessageDialog(null,e);
            JOptionPane.showMessageDialog(this, "Thêm Khách Hàng "+MaKHTF.getText().trim()+" Thất Bại");
            
        }
        dtm2.setRowCount(0);
        LoadTableKH();
    }//GEN-LAST:event_ThemNVBTActionPerformed

    private void SuaKHBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SuaKHBTActionPerformed
        // TODO add your handling code here:
        String sql = "update KhachHang  set "
                    + "MaNhom = ?,TenKH = ?,DiaChi = ?,DienThoai = ?,MaSoThue = ?,ThuDK = ? ,TraDK = ? "
                        + "where MaKH = ?";
        try 
        {
            
            PreparedStatement ps = conn.prepareStatement(sql);
           
            //ps.setString(6, SP.getMaSP());
            ps.setString(1, MaNhomCB.getSelectedItem().toString().trim());
            ps.setString(2, TenKHTF.getText().trim());
            ps.setString(3, DiaChiKHTF.getText().trim());
            ps.setString(4, SDTTF.getText().trim());
            ps.setString(5, MaThueTF.getText().trim());
            ps.setFloat(6, Float.valueOf(ThuDKTF.getText().trim()));
            ps.setFloat(7, Float.valueOf(TraCKTF.getText().trim()));
            ps.setString(8, MaKHTF.getText().trim());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Sửa Khách Hàng "+MaKHTF.getText().trim()+" Thành Công");
        } 
        catch (Exception e) 
        {
            JOptionPane.showMessageDialog(null,e);
            //JOptionPane.showMessageDialog(this, "Sửa Khách Hàng "+MaKHTF.getText().trim()+" Thất Bại");
            
        }
        dtm2.setRowCount(0);
        LoadTableKH();
       
    }//GEN-LAST:event_SuaKHBTActionPerformed

    private void XoaKHBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_XoaKHBTActionPerformed
        // TODO add your handling code here:
        String sql = "delete from KhachHang where MaKH = ?";
        int returnValue = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa Khách Hàng  " + MaKHTF.getText().toString().trim(), "Bạn chắc chứ?", JOptionPane.YES_NO_OPTION);
        if (returnValue == JOptionPane.YES_OPTION) 
        {
            try 
            {

                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, MaKHTF.getText().trim());
                ps.executeUpdate();
                JOptionPane.showMessageDialog(this, "Xóa Khách Hàng "+MaKHTF.getText().trim()+" Thành Công");
            } 
            catch (Exception e) 
            {
                JOptionPane.showMessageDialog(this, "Xóa Khách Hàng "+MaKHTF.getText().trim()+" Thất Bại");
            }
            dtm2.setRowCount(0);
            LoadTableKH();
        }
       
    }//GEN-LAST:event_XoaKHBTActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.dispose();
        new DangNhap().setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void MaNVLBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MaNVLBMouseClicked
        // TODO add your handling code here:
        String MaNV = MaNVLB.getText().toString().trim();
        String MaK = JOptionPane.showInputDialog("Nhập mật Khẩu Mới");
        if( MaK.length()>0 )
        {
            try {
                PreparedStatement ps = conn.prepareStatement("update  TaiKhoan set MatKhau = ? where MaNV ='" + MaNV + "'");
                //ps.setString(1, MaBNTF.getText());
                ps.setString(1, MaK);

                ps.executeUpdate();
                JOptionPane.showMessageDialog(null, " Đổi Mật Khẩu Thành Công " + MaNV ,
                        "Thông báo", JOptionPane.INFORMATION_MESSAGE);

            } catch (SQLException ex) {
                System.out.println("Loi :\n" + ex);
            }
        }
    }//GEN-LAST:event_MaNVLBMouseClicked

    private void TinhTienBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TinhTienBTActionPerformed

        Q_ChungTu h = new Q_ChungTu(MaNVLB.getText().trim(), TenNVLB.getText().trim(), ChungTuLB.getText().trim());
        HoaDon HD = new HoaDon(h);
        HD.setVisible(true);
        
    }//GEN-LAST:event_TinhTienBTActionPerformed

    private void XoaHDBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_XoaHDBTActionPerformed
        // TODO add your handling code here:
        int returnValue = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa Chứng   " + ChungTuLB.getText().toString().trim(), "Bạn chắc chứ?", JOptionPane.YES_NO_OPTION);
        if (returnValue == JOptionPane.YES_OPTION) 
        {
            CT_HoaDon HD = new CT_HoaDon();
            HD.XoaHD( ChungTuLB.getText().trim(), SoBanLB.getText().trim() );
            SoDoBan();
            MonAnTB.setModel(new DefaultTableModel());
            ChungTuLB.setText("N/A");
        }
    }//GEN-LAST:event_XoaHDBTActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        DanhSachHD();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        int qa = jTable1.getSelectedRow();
        String MaHD = (String) jTable1.getValueAt(qa, 0);
        String BanPhong = (String) jTable1.getValueAt(qa, 1);
        String TrangThai = (String) jTable1.getValueAt(qa, 10);
        if ( TrangThai.equals("1") )
            JOptionPane.showMessageDialog(null, "Không Thể Xóa Hóa Đơn Đã Thanh toán");
        else
        {
            int returnValue = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa Hóa Đơn   " + MaHD, "Bạn chắc chứ?", JOptionPane.YES_NO_OPTION);
            if (returnValue == JOptionPane.YES_OPTION) 
            {
                CT_HoaDon HD = new CT_HoaDon();
                HD.XoaHD( MaHD, BanPhong );
                SoDoBan();
                MonAnTB.setModel(new DefaultTableModel());
                ChungTuLB.setText("N/A");
                DanhSachHD();
            }
        }
        
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        int qa = jTable1.getSelectedRow();
        String MaHD = (String) jTable1.getValueAt(qa, 0);
        Q_ChungTu h = new Q_ChungTu(MaNVLB.getText().trim(), TenNVLB.getText().trim(), MaHD);
        HoaDon HD = new HoaDon(h);
        HD.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        new BaoCao().setVisible(true);
    }//GEN-LAST:event_jButton5ActionPerformed

    public static void main(String args[]) {


        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Form_Chinh().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BanMoiBT;
    private javax.swing.JButton CNBanBT;
    private javax.swing.JLabel ChungTuLB;
    private javax.swing.JButton ChuyenBanBT;
    private javax.swing.JPanel DanhSachMonPN;
    private javax.swing.JTextField DiaChiKHTF;
    private javax.swing.JPanel KhachHang;
    private javax.swing.JTable KhachHangBT;
    private javax.swing.JPanel Khác;
    private javax.swing.JTextField MaKHTF;
    private javax.swing.JLabel MaNVLB;
    private javax.swing.JComboBox<String> MaNhomCB;
    private javax.swing.JComboBox<String> MaNhomCB2;
    private javax.swing.JTextField MaThueTF;
    private javax.swing.JPanel MonAnPN;
    private javax.swing.JTable MonAnTB;
    private javax.swing.JTextField SDTTF;
    private javax.swing.JPanel SanPham;
    private javax.swing.JLabel SoBanLB;
    private javax.swing.JPanel SoDoBanPN;
    private javax.swing.JButton SuaKHBT;
    private javax.swing.JButton SuaSPBT;
    private javax.swing.JTextField TenKHTF;
    private javax.swing.JTextField TenKHTF2;
    private javax.swing.JLabel TenNVLB;
    private javax.swing.JButton ThemNVBT;
    private javax.swing.JButton ThemSPBT;
    private javax.swing.JTextField ThuDKTF;
    private javax.swing.JTextField ThuDKTF2;
    private javax.swing.JPanel ThuNgan;
    private javax.swing.JButton TinhTienBT;
    private javax.swing.JTextField TraCKTF;
    private javax.swing.JButton XoaHDBT;
    private javax.swing.JButton XoaKHBT;
    private javax.swing.JButton XoaSPBT;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox6;
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
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSpinner jSpinner6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable3;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables
}
