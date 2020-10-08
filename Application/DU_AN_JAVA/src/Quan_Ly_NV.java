
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class Quan_Ly_NV extends javax.swing.JFrame 
{
    
     public Quan_Ly_NV() 
    {
        initComponents();
        LoadDB();
        LoadTableNV();//load dữ liệu tu bản nhan viên
        LoadTableMK();//load dữ liệu từ bảng mạt khẩu
        MaNVCB();//load du lieu vào combobox
        // Cay();
        clock();
    }
    public Quan_Ly_NV( CT_DangNhap a ) 
    {
        initComponents();
        LoadDB();
        LoadTableNV();//load dữ liệu tu bản nhan viên
        LoadTableMK();//load dữ liệu từ bảng mạt khẩu
        MaNVCB();//load du lieu vào combobox
        clock();
        MaNVLB.setText( a.getMaN() );
        TenNVLB.setText( a.getTen() );
    }
    
        //Ham load du lị-----------------------------
    
   
    java.sql.Connection conn;
    Statement truyvan;
    ResultSet rs,rs1;
    String S;
   
    private DefaultTableModel dtm  = new DefaultTableModel();
    private DefaultTableModel dtm1 = new DefaultTableModel();
    
    public void clock() {
	Thread clock = new Thread() 
        {
		public void run() {
			try {
				while (true) {
					Calendar cal = new GregorianCalendar();
					int second = cal.get(Calendar.SECOND);
					int minute = cal.get(Calendar.MINUTE);
					int hour = cal.get(Calendar.HOUR_OF_DAY);
					String thu;
					int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
					if(dayOfWeek==1){thu ="Chủ nhật"; }
					else{thu ="Thứ "+ Integer.toString(dayOfWeek);}
					int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
					int month = cal.get(Calendar.MONTH);
					int year = cal.get(Calendar.YEAR);

                                        jLabel24.setText(hour + ":" + minute + ":" + second);
					jLabel25.setText(thu + " ngày " + dayOfMonth + " tháng " + (month + 1) + " năm " + year);
					sleep(1000);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	};
	clock.start();
    }
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
    //hàm dém số dòng trong bảng đẻ gọi hàm  
   
    // LoadTable dữ liệu DataBase vào bảng
    public void LoadTableNV()
    {
		
	try 
	{
	    	
            Vector<String> row,colum;//lay gia tri
	    colum=new Vector<String>();
	    rs=truyvan.executeQuery("select MaNV ,MaBP ,HoTen as '   Họ Và Tên    ', DiaChi ,DienThoai, MaSoThue , IsKeToan ,IsThuNgan  "
                                        + "from NhanVien "
                                        + "where MaBP not like 'BPOT' and MaBP not like 'BPQL'");
	    ResultSetMetaData metaData =rs.getMetaData();
	          
	    for (int i=1; i<=8;i++)//so cot
	    {
	        colum.add(metaData.getColumnName(i));
	    }
	    dtm.setColumnIdentifiers(colum);
	    while (rs.next())
	    {
	        	
	        row = new Vector<String>();
	        for (int i=1; i<=8;i++)//so hang
	           row.add(rs.getString(i));
                dtm.addRow(row);
	              
	    }
	    BangNVTB.setModel(dtm);
	    } 
	      
	    catch (SQLException ex) 
	    {
	    	
	        System.out.print("loi"+ex);
                this.dispose();
	    }
            //BangNVTB.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            BangNVTB.getColumnModel().getColumn(0).setPreferredWidth(50);
            BangNVTB.getColumnModel().getColumn(1).setPreferredWidth(50);
            BangNVTB.getColumnModel().getColumn(2).setPreferredWidth(120);
            
	} 
    // LoadTable dữ liệu DataBase vào bảng
    public void LoadTableMK()
    {
		// LoadTable dữ liệu DataBase vào bảng
	try 
	{
	    //MatKhauTB.getColumn(0).setPreferredWidth(50);
            Vector<String> row,colum1;//lay gia tri
	    colum1=new Vector<String>();
            
 
	    rs=truyvan.executeQuery("select NV.MaNV as 'Mã NV', NV.HoTen as 'Họ và Tên',NV.MaBP as 'Mã BP',TK.TaiKhoan as 'Tài Khoản',TK.MatKhau as 'Mật Khẩu'" +
                                            "from NhanVien NV full join TaiKhoan TK\n" +
                                                        "	on NV.MaNV =TK.MaNV");
	    ResultSetMetaData metaData1 =rs.getMetaData();
	          
	    for (int i=1; i<=5;i++)//so cot
	    {
	        colum1.add(metaData1.getColumnName(i));
	    }
	    dtm1.setColumnIdentifiers(colum1);
	    while (rs.next())
	    {
	        	
	        row = new Vector<String>();
	        for (int i=1; i<=5;i++)//so hang
	           row.add(rs.getString(i));
                dtm1.addRow(row);
	              
	    }
	    MatKhauTB.setModel(dtm1);
	    } 
	      
	    catch (SQLException ex) 
	    {
	    	
	        System.out.print("loi"+ex);
                this.dispose();
	    }
	    //MaThuocTF.setText("");
		//LoaiCB.setText(""); doi thanh textfield
	    
	 } //End LoadTable()  
        
    public void TimKiem(String SQL,DefaultTableModel A,int a)
    {
        A.setRowCount(0);
        if( TimKiemTF.toString().trim().length() >0 )
            try 
            {
                 Vector<String> row, colum1;//lay gia tri
                colum1 = new Vector<String>();

                rs = truyvan.executeQuery(SQL);
                ResultSetMetaData metaData1 = rs.getMetaData();

                for (int i = 1; i <= a; i++)//so cot
                {
                    colum1.add(metaData1.getColumnName(i));
                }
                A.setColumnIdentifiers(colum1);
                while (rs.next()) {

                    row = new Vector<String>();
                    for (int i = 1; i <= a; i++)//so hang
                    {
                        row.add(rs.getString(i));
                    }
                    A.addRow(row);

                }
                if( a== 8 )
                    BangNVTB.setModel(A);
                else 
                    MatKhauTB.setModel(A);
            }
            catch (Exception e) 
            {
            }
    }
    
    public void MaNVCB()
    {
        try
	{

            rs= truyvan.executeQuery("Select MaNV from NhanVien where MaBP not like 'BPOT' and MaBP not like 'BPQL'");
            while (rs.next())
            { 

                MaNVCB.addItem(rs.getString(1));

            }
        }
        catch(SQLException ex)
	{       	
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

        ChuyenBPJF = new javax.swing.JFrame();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jTextField2 = new javax.swing.JTextField();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        BangNVTB = new javax.swing.JTable();
        jPanel12 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        MaNVCB = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        MaBPTF = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        TenNVTF = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        DiaChiTF = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        MaThueTF = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        SDTTF = new javax.swing.JTextField();
        ThemNVBT = new javax.swing.JButton();
        SuaNVBT = new javax.swing.JButton();
        ChuyenBPNVBT = new javax.swing.JButton();
        XoaNVBT = new javax.swing.JButton();
        TimKiemTF = new javax.swing.JTextField();
        TimKiemNVBT = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        TimKT = new javax.swing.JTextField();
        TimKiemNVBT1 = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        MaBPTKTF = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        TenNVTKTF = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        QuyenTKCB = new javax.swing.JComboBox<>();
        ThemTKMKTB = new javax.swing.JButton();
        SuaTKMKTB = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        TaiKhoanTF = new javax.swing.JTextField();
        MatKhauTF = new javax.swing.JTextField();
        MaNVTKTF = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        MatKhauTB = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        QLBanPhongBT = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        MaNVLB = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        TenNVLB = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();

        ChuyenBPJF.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        ChuyenBPJF.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        ChuyenBPJF.setLocationByPlatform(true);

        jPanel3.setBackground(new java.awt.Color(153, 255, 153));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Chuyển Bộ phận");
        jLabel6.setToolTipText("");

        jLabel7.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        jLabel7.setText("Mã Nhân Viên");

        jLabel8.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        jLabel8.setText("Tên Nhân Viên");

        jLabel9.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        jLabel9.setText("Mã Bộ Phận");

        jLabel20.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        jLabel20.setText("Tên Bộ phận");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jTextField2.setText("jTextField2");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel8)
                        .addComponent(jLabel7))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel20))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(180, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(83, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout ChuyenBPJFLayout = new javax.swing.GroupLayout(ChuyenBPJF.getContentPane());
        ChuyenBPJF.getContentPane().setLayout(ChuyenBPJFLayout);
        ChuyenBPJFLayout.setHorizontalGroup(
            ChuyenBPJFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ChuyenBPJFLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        ChuyenBPJFLayout.setVerticalGroup(
            ChuyenBPJFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ChuyenBPJFLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAutoRequestFocus(false);
        setBackground(new java.awt.Color(153, 204, 255));
        setLocation(new java.awt.Point(0, 0));
        setLocationByPlatform(true);

        jTabbedPane1.setBackground(new java.awt.Color(153, 204, 255));

        jPanel2.setBackground(new java.awt.Color(153, 204, 255));

        BangNVTB.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 51)));
        BangNVTB.setModel(new javax.swing.table.DefaultTableModel(
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
        BangNVTB.setToolTipText("");
        BangNVTB.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        BangNVTB.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BangNVTB.setGridColor(new java.awt.Color(153, 255, 102));
        BangNVTB.setSelectionBackground(new java.awt.Color(204, 204, 255));
        BangNVTB.setSelectionForeground(new java.awt.Color(204, 0, 0));
        BangNVTB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BangNVTBMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(BangNVTB);

        jPanel12.setBackground(new java.awt.Color(204, 255, 204));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 51, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Thông Tin Cụ Thể");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 102, 51));
        jLabel10.setText("Mã Nhân Viên");

        MaNVCB.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        MaNVCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MaNVCBActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 102, 51));
        jLabel11.setText("Mã BP");

        MaBPTF.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 102, 51));
        jLabel12.setText("Tên NV");

        TenNVTF.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 102, 51));
        jLabel13.setText("Địa Chỉ");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 102, 51));
        jLabel14.setText("Mã Thuế ");

        MaThueTF.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 102, 51));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel15.setText("SDT");

        ThemNVBT.setBackground(new java.awt.Color(204, 255, 255));
        ThemNVBT.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ThemNVBT.setForeground(new java.awt.Color(102, 0, 0));
        ThemNVBT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Anh/icons8-add-property-20.png"))); // NOI18N
        ThemNVBT.setText("Thêm NV");
        ThemNVBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ThemNVBTActionPerformed(evt);
            }
        });

        SuaNVBT.setBackground(new java.awt.Color(204, 255, 255));
        SuaNVBT.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        SuaNVBT.setForeground(new java.awt.Color(102, 0, 0));
        SuaNVBT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Anh/icons8-edit-20 (2).png"))); // NOI18N
        SuaNVBT.setText("Sửa NV");
        SuaNVBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SuaNVBTActionPerformed(evt);
            }
        });

        ChuyenBPNVBT.setBackground(new java.awt.Color(204, 255, 255));
        ChuyenBPNVBT.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ChuyenBPNVBT.setForeground(new java.awt.Color(102, 0, 0));
        ChuyenBPNVBT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Anh/icons8-topic-moved-20.png"))); // NOI18N
        ChuyenBPNVBT.setText("Chuyển BP");
        ChuyenBPNVBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChuyenBPNVBTActionPerformed(evt);
            }
        });

        XoaNVBT.setBackground(new java.awt.Color(204, 255, 255));
        XoaNVBT.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        XoaNVBT.setForeground(new java.awt.Color(255, 51, 51));
        XoaNVBT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Anh/icons8-delete-20.png"))); // NOI18N
        XoaNVBT.setText("Xóa NV");
        XoaNVBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                XoaNVBTActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(18, 18, 18)
                        .addComponent(MaNVCB, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(MaBPTF))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(ChuyenBPNVBT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(ThemNVBT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(26, 26, 26)
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(SuaNVBT, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
                                    .addComponent(XoaNVBT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel13))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel12Layout.createSequentialGroup()
                                        .addComponent(MaThueTF)
                                        .addGap(122, 122, 122))
                                    .addGroup(jPanel12Layout.createSequentialGroup()
                                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(SDTTF, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(DiaChiTF, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(TenNVTF, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, Short.MAX_VALUE)))))
                        .addGap(0, 37, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(MaNVCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(MaBPTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(MaThueTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(TenNVTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(DiaChiTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(SDTTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ThemNVBT)
                    .addComponent(SuaNVBT))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ChuyenBPNVBT)
                    .addComponent(XoaNVBT))
                .addContainerGap(78, Short.MAX_VALUE))
        );

        TimKiemTF.setText("Tìm Kiếm");
        TimKiemTF.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TimKiemTFMouseClicked(evt);
            }
        });

        TimKiemNVBT.setBackground(new java.awt.Color(204, 255, 255));
        TimKiemNVBT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Anh/icons8-search-20.png"))); // NOI18N
        TimKiemNVBT.setText("Tìm Kiếm");
        TimKiemNVBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TimKiemNVBTActionPerformed(evt);
            }
        });

        jButton1.setText("Làm Mới");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel21.setText("Trần Xuân Quang-18I3");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 580, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(TimKiemTF, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(TimKiemNVBT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TimKiemNVBT)
                            .addComponent(TimKiemTF, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel21))
        );

        jTabbedPane1.addTab("Quản Lý Nhân Viên", jPanel2);

        jPanel1.setBackground(new java.awt.Color(153, 204, 255));

        TimKT.setText("Tìm Kiếm");
        TimKT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TimKTMouseClicked(evt);
            }
        });

        TimKiemNVBT1.setBackground(new java.awt.Color(204, 255, 255));
        TimKiemNVBT1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Anh/icons8-search-20.png"))); // NOI18N
        TimKiemNVBT1.setText("Tìm Kiếm");
        TimKiemNVBT1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TimKiemNVBT1ActionPerformed(evt);
            }
        });

        jPanel13.setBackground(new java.awt.Color(204, 255, 204));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 51, 0));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Thông Tin Cụ Thể");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(51, 102, 0));
        jLabel17.setText("Mã Nhân Viên");

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(51, 102, 0));
        jLabel18.setText("Mã BP");

        MaBPTKTF.setEditable(false);
        MaBPTKTF.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        MaBPTKTF.setForeground(new java.awt.Color(255, 102, 51));

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(51, 102, 0));
        jLabel19.setText("Tên NV");

        TenNVTKTF.setEditable(false);
        TenNVTKTF.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(51, 102, 0));
        jLabel23.setText("Quyền");

        QuyenTKCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Kế Toán Và Thu ngân", "Kế Toán", " Thu ngân" }));

        ThemTKMKTB.setBackground(new java.awt.Color(204, 255, 255));
        ThemTKMKTB.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ThemTKMKTB.setForeground(new java.awt.Color(102, 0, 0));
        ThemTKMKTB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Anh/icons8-add-property-20.png"))); // NOI18N
        ThemTKMKTB.setText("Thêm MK");
        ThemTKMKTB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ThemTKMKTBActionPerformed(evt);
            }
        });

        SuaTKMKTB.setBackground(new java.awt.Color(204, 255, 255));
        SuaTKMKTB.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        SuaTKMKTB.setForeground(new java.awt.Color(102, 0, 0));
        SuaTKMKTB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Anh/icons8-edit-20 (2).png"))); // NOI18N
        SuaTKMKTB.setText("Sửa MK");
        SuaTKMKTB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SuaTKMKTBActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 102, 0));
        jLabel3.setText("Tài Khoản");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 102, 0));
        jLabel4.setText("Mật Khẩu");

        TaiKhoanTF.setEditable(false);

        MaNVTKTF.setEditable(false);
        MaNVTKTF.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        MaNVTKTF.setForeground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addComponent(jLabel23)
                                .addGap(18, 18, 18)
                                .addComponent(QuyenTKCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel13Layout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addGap(18, 18, 18)
                                    .addComponent(TaiKhoanTF, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel13Layout.createSequentialGroup()
                                    .addComponent(jLabel4)
                                    .addGap(18, 18, 18)
                                    .addComponent(MatKhauTF, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(MaNVTKTF, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel18)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(MaBPTKTF, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jPanel13Layout.createSequentialGroup()
                                    .addComponent(ThemTKMKTB)
                                    .addGap(19, 19, 19)
                                    .addComponent(SuaTKMKTB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(jPanel13Layout.createSequentialGroup()
                                    .addComponent(jLabel19)
                                    .addGap(31, 31, 31)
                                    .addComponent(TenNVTKTF, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel18)
                        .addComponent(MaBPTKTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel17)
                        .addComponent(MaNVTKTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(TenNVTKTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(TaiKhoanTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(MatKhauTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(QuyenTKCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(55, 55, 55)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ThemTKMKTB)
                    .addComponent(SuaTKMKTB))
                .addGap(47, 47, 47))
        );

        MatKhauTB.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Title 1", "Title 2"
            }
        ));
        MatKhauTB.setGridColor(new java.awt.Color(153, 255, 102));
        MatKhauTB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MatKhauTBMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(MatKhauTB);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Thông Tin Tài Khoản Và Mật khẩu ");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 595, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jButton2.setText("Làm Mới");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(10, 10, 10))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(TimKT, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(TimKiemNVBT1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)))
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TimKT, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TimKiemNVBT1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Quản Lý Đăng Nhập", jPanel1);

        jPanel5.setBackground(new java.awt.Color(153, 204, 255));

        QLBanPhongBT.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        QLBanPhongBT.setForeground(new java.awt.Color(204, 0, 204));
        QLBanPhongBT.setText("Quản Lý Bàn Phòng");
        QLBanPhongBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                QLBanPhongBTActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(204, 0, 204));
        jButton3.setText("Quản Lý Bộ Phận");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton5.setForeground(new java.awt.Color(204, 0, 204));
        jButton5.setText("Quản Lý Danh Mục");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(534, Short.MAX_VALUE)
                .addComponent(QLBanPhongBT, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addGap(20, 20, 20)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(536, Short.MAX_VALUE)))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addGap(28, 28, 28)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(528, Short.MAX_VALUE)))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(QLBanPhongBT, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(297, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addGap(36, 36, 36)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(298, Short.MAX_VALUE)))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                    .addContainerGap(176, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(158, 158, 158)))
        );

        jTabbedPane1.addTab("Quản Lý Khác", jPanel5);

        jPanel10.setBackground(new java.awt.Color(204, 255, 255));
        jPanel10.setForeground(new java.awt.Color(51, 0, 255));

        MaNVLB.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        MaNVLB.setForeground(new java.awt.Color(255, 102, 51));
        MaNVLB.setText("jLabel20");
        MaNVLB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MaNVLBMouseClicked(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 51, 0));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Anh/icons8-logout-rounded-left-20.png"))); // NOI18N
        jButton4.setText("Đăng Xuất");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 51, 51));
        jLabel22.setText("Xin Chào :");

        TenNVLB.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TenNVLB.setForeground(new java.awt.Color(255, 102, 51));
        TenNVLB.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        TenNVLB.setText("jLabel3");

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel24.setText("jLabel16");

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel25.setText("jLabel16");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(120, 120, 120)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(MaNVLB, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TenNVLB, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13))
            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel10Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(680, Short.MAX_VALUE)))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(MaNVLB, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22)
                    .addComponent(TenNVLB)
                    .addComponent(jButton4)
                    .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel10Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 423, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void MaNVCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MaNVCBActionPerformed
        // TODO add your handling code here:
        MaNVCB.addItemListener(new ItemListener()
            {
                public void itemStateChanged(ItemEvent arg0)
                {
                    aa();
                }
            });
    }//GEN-LAST:event_MaNVCBActionPerformed

    private void SuaNVBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SuaNVBTActionPerformed
        // TODO add your handling code here:
        String MaNV = MaNVCB.getSelectedItem().toString();
        try {
            PreparedStatement ps = conn.prepareStatement("update  NhanVien set HoTen=?, DiaChi=?, DienThoai=? where MaNV ='" + MaNV + "'");
            //ps.setString(1, MaBNTF.getText());
            ps.setString(1, TenNVTF.getText());
            ps.setString(2, DiaChiTF.getText());
            ps.setString(3, SDTTF.getText());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Nhân Viên có mã " + MaNV + " đã được sửa!",
                    "Thông báo", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException ex) {
            System.out.println("Loi :\n" + ex);
        }
         dtm.setRowCount(0);
        LoadTableNV();
    }//GEN-LAST:event_SuaNVBTActionPerformed

    public void ThemTKMK()
    {
        try 
        {
            PreparedStatement ps = conn.prepareStatement("insert into TaiKhoan (MaNV, TaiKhoan , MatKhau) values(?,?,?)");

            ps.setString(1, MaNVTKTF.getText().toString().trim());
            ps.setString(2, TaiKhoanTF.getText().toString().trim());
            ps.setString(3, MatKhauTF.getText().toString().trim());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Thêm Nhân viên có mã " + S + " Thành Công");

        } 
        catch (SQLException ex) 
        {
            System.out.print("Loi :\n" + ex);
        }
        dtm1.setRowCount(0);
        LoadTableMK();
    }
    public void SuaTKMK()
    {
        try 
        {
            PreparedStatement ps = conn.prepareStatement("update  TaiKhoan set MatKhau = ? where MaNV ='" + MaNVTKTF.getText().toString().trim() + "'");

            ps.setString(1, MatKhauTF.getText().toString().trim());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Nhân Viên có mã " + MaNVTKTF.getText().toString().trim() + " đã được sửa!",
                    "Thông báo", JOptionPane.INFORMATION_MESSAGE);

        } 
        catch (SQLException ex) 
        {
            System.out.println("Loi :\n" + ex);
        }
        dtm1.setRowCount(0);
        LoadTableMK();
    }
    private void ThemNVBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ThemNVBTActionPerformed
        // TODO add your handling code here:
        new ThemNV().setVisible(true); 
    }//GEN-LAST:event_ThemNVBTActionPerformed

    private void XoaNVBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_XoaNVBTActionPerformed
        // TODO add your handling code here:
        String MaNV = MaNVCB.getSelectedItem().toString();
        try {
            PreparedStatement ps = conn.prepareStatement("update  NhanVien set MaBP=? where MaNV ='" + MaNV + "'");
            //ps.setString(1, MaBNTF.getText());
            ps.setString(1, "BPOT");
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Nhân Viên có mã " + MaNV + " đã được Xóa!",
                    "Thông báo", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException ex) {
            System.out.println("Loi :\n" + ex);
        }
         dtm.setRowCount(0);
        LoadTableNV();
    }//GEN-LAST:event_XoaNVBTActionPerformed

    private void ChuyenBPNVBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChuyenBPNVBTActionPerformed
        // TODO add your handling code here:
        //jFrame1.setVisible(rootPaneCheckingEnabled);
        //ChuyenBPJF.setVisible(true);
        ChuyenBPJF.setVisible(true);
    }//GEN-LAST:event_ChuyenBPNVBTActionPerformed

    private void ThemTKMKTBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ThemTKMKTBActionPerformed
        // TODO add your handling code here:
        ThemTKMK();
    }//GEN-LAST:event_ThemTKMKTBActionPerformed

    private void SuaTKMKTBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SuaTKMKTBActionPerformed
        // TODO add your handling code here:
       SuaTKMK();
    }//GEN-LAST:event_SuaTKMKTBActionPerformed

    private void TimKiemNVBT1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TimKiemNVBT1ActionPerformed
        // TODO add your handling code here:
        TimKiem("select NV.MaNV as 'Mã NV', NV.HoTen as 'Họ và Tên',NV.MaBP as 'Mã BP',TK.TaiKhoan as 'Tài Khoản',TK.MatKhau as 'Mật Khẩu'\n" +
"        from NhanVien NV full join TaiKhoan TK\n" +
"                on NV.MaNV =TK.MaNV\n" +
"		where       NV.MaNV  LIKE '%"+TimKT.getText().trim()+"%' \n" +
"			OR  NV.HoTen LIKE '"+TimKT.getText().trim()+"%'\n" +
"			OR  NV.MaBP  LIKE '%"+TimKT.getText().trim()+"%'\n" +
"			OR  TK.TaiKhoan LIKE '%"+TimKT.getText().trim()+"%'", dtm1, 5);
        
    }//GEN-LAST:event_TimKiemNVBT1ActionPerformed

    //kich vao bảng mat khau tai khoản
    private void MatKhauTBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MatKhauTBMouseClicked
        // TODO add your handling code here:
        int qa = MatKhauTB.getSelectedRow();
        MaNVTKTF  .setText((String) MatKhauTB.getValueAt(qa, 0));
        MaBPTKTF  .setText((String) MatKhauTB.getValueAt(qa, 2));
        TenNVTKTF .setText((String) MatKhauTB.getValueAt(qa, 1));
        TaiKhoanTF.setText((String) MatKhauTB.getValueAt(qa, 0));
        MatKhauTF .setText((String) MatKhauTB.getValueAt(qa, 4));
        S = MaBPTKTF.getText().toString().trim();
        System.err.println(S);
       if( S.equals("BPTN"))
            QuyenTKCB.setSelectedIndex(1);
       //QuyenTKCB.setSelectedIndex(1);
       else 
           QuyenTKCB.setSelectedIndex(0);
            
        
        
        
    }//GEN-LAST:event_MatKhauTBMouseClicked

    private void TimKiemNVBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TimKiemNVBTActionPerformed
         // TODO add your handling code here:
         TimKiem("SELECT * FROM NhanVien \n" +
                    "where     MaNV like    '%"+TimKiemTF.getText()+"%' \n" +
                    "	or MaBP like    '%"+TimKiemTF.getText()+"%' \n" +
                    "	or HoTen like   '%"+TimKiemTF.getText()+"%' \n" +
                    "	or DiaChi like  '%"+TimKiemTF.getText()+"%' \n" +
                    "	or DienThoai like '%"+TimKiemTF.getText()+"%' \n" +
                    "	or MaSoThue like  '%"+TimKiemTF.getText()+"%'\n" +
                    "	or IsKeToan like  '%"+TimKiemTF.getText()+"%'\n" +
                    "	or IsThuNgan like '%"+TimKiemTF.getText()+"%'", dtm,8);
         
    }//GEN-LAST:event_TimKiemNVBTActionPerformed

    private void TimKTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TimKTMouseClicked
        // TODO add your handling code here:
        TimKT.setText("");
        
    }//GEN-LAST:event_TimKTMouseClicked
    //sk khi kich vào bảng
    private void BangNVTBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BangNVTBMouseClicked
     // TODO add your handling code here:
        int qa = BangNVTB.getSelectedRow();
        //MaNVTKTF  .setText((String) BangNVTB.getValueAt(qa, 0));
        MaBPTF  .setText((String) BangNVTB.getValueAt(qa, 1));
        TenNVTF .setText((String) BangNVTB.getValueAt(qa, 2));
        DiaChiTF.setText((String) BangNVTB.getValueAt(qa, 3));
        SDTTF .setText((String) BangNVTB.getValueAt(qa, 4));
        MaThueTF .setText((String) BangNVTB.getValueAt(qa, 5));
        MaNVCB.setSelectedItem((String) BangNVTB.getValueAt(qa, 0));
       /* S = MaBPTKTF.getText().toString().trim();
        System.err.println(S);
       if( S.equals("BPTN"))
            QuyenTKCB.setSelectedIndex(1);
       //QuyenTKCB.setSelectedIndex(1);
       else 
           QuyenTKCB.setSelectedIndex(0);*/
    }//GEN-LAST:event_BangNVTBMouseClicked

    private void TimKiemTFMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TimKiemTFMouseClicked
        // TODO add your handling code here:
        TimKiemTF.setText("");
    }//GEN-LAST:event_TimKiemTFMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        dtm.setRowCount(0);
        LoadTableNV();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void QLBanPhongBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_QLBanPhongBTActionPerformed
        // TODO add your handling code here:
        new BanPhong().setVisible(true);
    }//GEN-LAST:event_QLBanPhongBTActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        new DanhMucDL().setVisible(true);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        new QuanLyBoPhan().setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        dtm1.setRowCount(0);
        LoadTableMK();
    }//GEN-LAST:event_jButton2ActionPerformed

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

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        this.dispose();
        new DangNhap().setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    //load du lieu tu combobox
    public void aa()
    {
         S=MaNVCB.getSelectedItem().toString().trim();
         String aaa ="select MaBP,HoTen, DiaChi, DienThoai, MaSoThue , isKeToan ,isThuNgan from NhanVien where MaNV='"+S+"'";
        try
        {
            rs=truyvan.executeQuery(aaa);
            rs.next();
            if(rs.getString(1).trim().length()>0)
		MaBPTF.setText(rs.getString(1).trim());
            if(rs.getString(2).trim().length()>0)
		TenNVTF.setText(rs.getString(2).trim()); 
            if(rs.getString(3).trim().length()>0)
		DiaChiTF.setText(rs.getString(3).trim());
            if(rs.getString(4).trim().length()>0)
		SDTTF.setText(rs.getString(4).trim());
            if(rs.getString(5).trim().length()>0)
		MaThueTF.setText(rs.getString(5).trim());		  
        }
	catch (SQLException ex)
        {
             System.out.println("Lỗi :\t"+ex);
        }
        
    }
 
   
    public static void main(String args[]) 
    {
        java.awt.EventQueue.invokeLater(new Runnable() 
        {
            public void run() 
            {
                new Quan_Ly_NV().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable BangNVTB;
    private javax.swing.JFrame ChuyenBPJF;
    private javax.swing.JButton ChuyenBPNVBT;
    private javax.swing.JTextField DiaChiTF;
    private javax.swing.JTextField MaBPTF;
    private javax.swing.JTextField MaBPTKTF;
    private javax.swing.JComboBox<String> MaNVCB;
    private javax.swing.JLabel MaNVLB;
    private javax.swing.JTextField MaNVTKTF;
    private javax.swing.JTextField MaThueTF;
    private javax.swing.JTable MatKhauTB;
    private javax.swing.JTextField MatKhauTF;
    private javax.swing.JButton QLBanPhongBT;
    private javax.swing.JComboBox<String> QuyenTKCB;
    private javax.swing.JTextField SDTTF;
    private javax.swing.JButton SuaNVBT;
    private javax.swing.JButton SuaTKMKTB;
    private javax.swing.JTextField TaiKhoanTF;
    private javax.swing.JLabel TenNVLB;
    private javax.swing.JTextField TenNVTF;
    private javax.swing.JTextField TenNVTKTF;
    private javax.swing.JButton ThemNVBT;
    private javax.swing.JButton ThemTKMKTB;
    private javax.swing.JTextField TimKT;
    private javax.swing.JButton TimKiemNVBT;
    private javax.swing.JButton TimKiemNVBT1;
    private javax.swing.JTextField TimKiemTF;
    private javax.swing.JButton XoaNVBT;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
