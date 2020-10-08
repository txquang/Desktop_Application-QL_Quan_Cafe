update B
set B.Gia=A.Gia
from A A ,B B
where B.ID=A.ID


select *
from A


select *
from B



--tính tong từ dòng ct sang chứng từ tạch
UPDATE DONGCT
SET Tong = ( SELECT SUM(Gia)
			 FROM B
			 WHERE B.CT=CT  )
FROM B b,DongCT CT
WHERE b.CT =CT.CT

--Tính Tổng
SELECT SUM(Gia)
FROM B,DongCT
WHERE B.CT=DongCT.CT



select *
from BanPhong BP   join BangGia BG
	on BP.MaBG = BG.MaBG


select CT.SoBan 
from DongCT DCT  join ChungTu CT
	on DCT.SoCT = CT.SoCT

select * from NhanVien where MaBP not like 'BPOT' and MaBP not like 'BPQL'
select MaBP from NhanVien where MaNV='NV01'


SELECT * FROM NhanVien 
where MaNV like '' 
	or MaBP like '' 
	or HoTen like '' 
	or DiaChi like '' 
	or DienThoai like '' 
	or MaSoThue like ''
	or IsKeToan like '1'
	or IsThuNgan like '%1%'

Select SoBan , KV.TenKV , LBG.TenBG
from 
	BanPhong BP  Join KhuVuc KV
		on Bp.MaKV = KV.MaKV
	 join LoaiBangGia LBG
		on BP.MaBG = LBG.MaBG

Select SoBan ' Số Bàn ', KV.TenKV as 'Tên Khu Vực ', LBG.TenBG as ' Tên Bộ Phận ' ,NoUse as' Trạng Thái '    
from 
	BanPhong BP  Join KhuVuc KV
		on Bp.MaKV = KV.MaKV
	 join LoaiBangGia LBG
		on BP.MaBG = LBG.MaBG


select MaDVT as ' Mã Đơn Vị Tính ', MaDinh as ' Mặt Định ' from DonViTinh
select  MaKV as ' Mã Khu Vực ' ,TenKV as ' Tên Khu Vực ' from KhuVuc
select MaBG as ' Mã Bảng Giá ' , TenBG as 'Tên Bảng Giá ' , MatDinh as ' Mặt Định 'from LoaiBangGia


--phia trn hoa don
select CT.SoCT , CT.NoiDung as 'Nội Dung',CT.NgayCT , CT.NgayDat ,KH.TenKH as 'Khách Hang',NV.HoTen as 'Nhân Vin',CT.Giam , CT.ThueVAT, CT.PhiDV, CT.SoTien, CT.TraTruoc 
from ChungTu CT  
	join  KhachHang KH 
			on CT.MaKH = KH.MaKH
	join  NhanVien NV
			on CT.MaNhanVien = NV.MaNV
--bn trong hoa don
select SP.TenSP as 'dsg', CT.MaDVT , CT.SoLuong ,CT.TraLai,CT.DonGia, CT.GhiChu
from DongCT CT join SanPham SP
	on CT.MaSP = SP.MaSP
where SoCT = 'CT02'

 

select *
from NhanVien 
WHERE MaBP like 'BPQL'

--tính tiên sp vào chứng từ
	--thêm giá Sp vào bảng dòng chứng từ
	UPDATE DongCT
	SET  DonGia = SP.GTDK
	FROM DongCT DCT ,SanPham SP
	WHERE DCT.MaSP = SP.MaSP
	--trả về chứng từ

	select distinct SoCT
	from DongCT

	--tính tông tung chưng tu
	select sum (  (DonGia * (SoLuong - TraLai)) - ( DonGia * (SoLuong - TraLai) * Giam/100 )   )
	from DongCT
	where SoCT = 'CT01'--thay bằng gọi hàm

	--cap nhạt vào chunhws từ
	update ChungTu
	set SoTien = (select sum (DonGia * (SoLuong - TraLai) )
						from DongCT
						where SoCT = 'CT02')
	from ChungTu
	where SoCT = 'CT02'
	--tính số tiền khách hàng còn nợ
	update ChungTu
	set ConNo = TraTruoc - SoTien--ra dương thì sẻ trả lại ng dung  , ra am người dùng trả thêm
--Xong chứng từ

--FORM HOA DON

	--TRUY XUAT TIEU DE FROM HOA DON
	select SoBan , SoKhach, TraTruoc, SoCT, ConNo, MaKH, MaNhanVien , Giam , ThueVAT , PhiDV , SoTien, NgayCT, NgayDat
	from ChungTu
	where SoCT = 'CT02'--thay CT02 bang du li3u truy3n vao
	--XONG

	--TRUY XUAT NOI DUNG FROM HOA DON
	select SP.TenSP, CT.MaDVT , CT.SoLuong ,CT.TraLai,CT.DonGia, CT.GhiChu
    from DongCT CT join SanPham SP
      on CT.MaSP = SP.MaSP
    where SoCT = 'CT01'
	--XONG




	--THEM GIA TU BAN GIA VAO DONG CT ,DUNG CON TRO DE TINH HOA DON TAI DONG CHUNG TU , THEM TONG TIEN VAO CHUNG TU -----------------------------
		--THEM GIA VAO DONG CHUNG TU
		UPDATE DongCT
			SET  DonGia = SP.GTDK
			FROM DongCT DCT ,SanPham SP
			WHERE DCT.MaSP = SP.MaSP

		--TAO CON TRO
		DECLARE DongCTTEN CURSOR FOR SELECT DISTINCT  SoCT FROM DongCT

		OPEN DongCTTEN
		DECLARE @Ten CHAR(10)
		FETCH NEXT FROM DongCTTEN INTO @Ten
		WHILE @@FETCH_STATUS = 0--DIEU KIEN DUNG
			BEGIN
				--THEM TONG TIEN VAO CUNG TU 
				UPDATE ChungTu
				SET SoTien = (SELECT SUM (DonGia * (SoLuong - TraLai) )
									FROM DongCT
								WHERE  SoCT = @Ten)
				from ChungTu
				WHERE  SoCT = @Ten
				FETCH NEXT FROM DongCTTEN INTO @Ten--THEM VONG LAP
			END
		CLOSE DongCTTEN
		DEALLOCATE DongCTTEN
		--tính số tiền khách hàng còn nợ
		update ChungTu
			set ConNo = TraTruoc - SoTien--ra dương thì sẻ trả lại ng dung  , ra am người dùng trả thêm
	------------------XONG  CAP NHAT ------------
--XONG FROM HOA DON
--tim kiem bang mk
select NV.MaNV as 'Mã NV', NV.HoTen as 'Họ và Tên',NV.MaBP as 'Mã BP',TK.TaiKhoan as 'Tài Khoản',TK.MatKhau as 'Mật Khẩu'
        from NhanVien NV full join TaiKhoan TK
                on NV.MaNV =TK.MaNV
		where	NV.MaNV  LIKE '%NV05%' 
			OR	NV.HoTen LIKE '%NV05%'
			OR  NV.MaBP  LIKE '%NV05%'
			OR  TK.TaiKhoan LIKE '%NV05%'
--trang thai
select NoUse from BanPhong
SELECT DISTINCT MaNhom from NhomKhach
SELECT DISTINCT MaNhom from NhomHang
SELECT DISTINCT MaDVT from DonViTinh
SELECT MaNhom , TenNhom FROM NhomHang 
	WHERE MaNhom NOT LIKE 'MNCB' 
			AND MaNhom NOT LIKE 'MNNL'

SELECT MaSP ,TenSP FROM SanPham WHERE MaNhom = 'MNDV'
  

/------------------------------------HD
SELECT COUNT(SoCT)+1 as CT  FROM ChungTu
/----HIEN CHUNG TU
SELECT TOP 1 SoCT
	FROM ChungTu
	WHERE SoBan = 'SB10' AND TrangThai = 0
	ORDER BY SoCT DESC
	/----------------hien len trang chu 
SELECT SP.TenSP AS 'Tên Món',CT.SoLuong,SP.GTDK 
	FROM DongCT CT 
		JOIN SanPham SP
			ON SP.MaSP = CT.MaSP
	
	WHERE CT.SoCT = (SELECT TOP 1 SoCT
						FROM ChungTu
						WHERE SoBan = 'SB01' AND TrangThai = 0
						ORDER BY SoCT DESC)


IF EXISTS(SELECT SoCT FROM DongCT WHERE SoCT = 'CT04' AND MaSP = 'SP09')
	BEGIN
		DECLARE @SoLuongC int = (SELECT SoLuong FROM DongCT WHERE SoCT = 'CT04' AND MaSP = 'SP01');--TRA VE SL CU
		DECLARE @GiaC int = (SELECT GTDK FROM SanPham WHERE MaSP = 'SP01');
		DECLARE @DVTC NVARCHAR(7) = (SELECT MaDVT FROM SanPham WHERE MaSP = 'SP01');
		UPDATE DongCT --CAP NHAT SL
			SET SoLuong = @SoLuongC +5,
				DonGia = @GiaC,
				MaDVT = @DVTC
		WHERE SoCT = 'CT04' AND MaSP = 'SP01';
	END
ELSE
	BEGIN 
		DECLARE @GiaM int = (SELECT GTDK FROM SanPham WHERE MaSP = 'SP01');
		DECLARE @DVTM NVARCHAR(7) = (SELECT MaDVT FROM SanPham WHERE MaSP = 'SP01');
		INSERT DongCT ([SoCT], [MaSP], [MaDVT], [SoLuong], [DonGia]) VALUES (N'CT04', N'SP09', @DVTM, 2, @GiaM)
		Print 'dsghsfdhsfuyhfjfj';
	END

SELECT SoBan FROM BanPhong WHERE NoUse = 0

BEGIN
	DELETE DongCT WHERE SoCT = 'CT06'
	DELETE ChungTu WHERE SoCT = 'CT06'
END
--top HD
SELECT  SoBan , SoCT, SoKhach, convert(varchar, NgayCT, 20),
		 SoTien, TrangThai, Giam , ThueVAT , PhiDV , TraTruoc,
		 ConNo, MaKH, MaNhanVien , convert(varchar, NgayDat, 20)
FROM ChungTu
WHERE SoCT = 'CT03'

UPDATE ChungTu--tính tong tien
				SET SoTien = (SELECT SUM (DonGia * (SoLuong - TraLai) )
									FROM DongCT
								WHERE  SoCT = 'CT04')
				from ChungTu
				WHERE  SoCT = @Ten
select * FROM ChungTu 
	SELECT --danh sach chung tu

		SoCT AS 'Số CT', SoBan AS 'Số Bàn', MaKH AS 'Mã KH', SoKhach AS 'Số Khách',
		 NoiDung AS 'Nội Dung', MaNhanVien AS 'Mã NV', MaThuNgan AS 'Mã TN',
		 SoTien AS 'Tổng Tiền' , convert(varchar, NgayCT, 20) AS 'Ngày Lập', convert(varchar, NgayDat, 20) AS 'Ngày TT', TrangThai AS 'Trạng Thái'
	FROM ChungTu
	Where MONTH(NgayCT) BETWEEN 1 AND 12

SELECT MaSP AS 'Mã SP', MaNhom AS 'Mã Nhóm',
		 TenSP AS 'Tên Sản Phẩm', MaDVT AS 'Mã ĐVT', SLDK AS 'SL', GTDK AS 'Giá' 
FROM SanPham 
ORDER BY MaDVT DESC

SELECT COUNT (SoLuong)  from DongCT HAVING COUNT(1) > 1

select MaSP,t.cnt-1,t.DonGia from (
       select MaSP,SUM(SoLuong) over(Partition by SoLuong) as cnt,DonGia from DongCT
) t
where t.cnt>1

SELECT  CT.MaSP, TenSP, SUM (CT.SoLuong) 
From DongCT CT JOIN SanPham SP
	on CT.MaSP = SP.MaSP
GROUP BY CT.MaSP

SELECT  MaSP , SLDK * GTDK
From  SanPham 
GROUP BY MaSP
-----------------hien ds báo cao
SELECT  MaSP AS 'Mã SP', (select TenSP From SanPham Where MaSP = CT.MaSP) AS 'Tên Sản Phẩm',
		(select  DISTINCT DongCT.MaDVT  from DongCT where MaSP = CT.MaSP) AS 'ĐVT', 
		SUM (SoLuong) AS 'Số Lượng', (select  DISTINCT DonGia  from DongCT where MaSP = CT.MaSP) AS 'Giá Bán',
		(select  DISTINCT DonGia*SUM (CT.SoLuong)  from DongCT where MaSP = CT.MaSP) AS 'Doanh Số' 		
From DongCT CT
	WHERE YEAR(GioVao)  BETWEEN 2018 AND 2019
			AND MONTH(GioVao) BETWEEN 1 AND 12
			AND DAY(GioVao) BETWEEN 1 AND 18
GROUP BY MaSP
------------- cap nhat ngay
UPDATE DongCT
SET GioVao = (select NgayCT from ChungTu WHERE SoCT = 'CT02') 
WHERE SoCT = 'CT02'

------------------------luu hd
UPDATE ChungTu
	SET SoKhach = 1,
		Giam = 0.0,
		ThueVAT = 0.0,
		PhiDV = 0.0,
		TraTruoc = 0
WHERE SoCT = 'gf'

--------------------------thanh toan

UPDATE ChungTu
	SET MaThuNgan = '54',
		ConNo = TraTruoc - SoTien,
		NgayDat = 'gsdgsdg',
		TrangThai = 1
WHERE SoCT = 