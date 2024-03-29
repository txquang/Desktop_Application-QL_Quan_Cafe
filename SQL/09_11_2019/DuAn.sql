USE [master]
GO
/****** Object:  Database [DuAn]    Script Date: 11/9/2019 5:57:17 PM ******/
CREATE DATABASE [DuAn]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'DuAn', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.MSSQLSERVER\MSSQL\DATA\DuAn.mdf' , SIZE = 3072KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'DuAn_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.MSSQLSERVER\MSSQL\DATA\DuAn_log.ldf' , SIZE = 1024KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [DuAn] SET COMPATIBILITY_LEVEL = 120
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [DuAn].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [DuAn] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [DuAn] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [DuAn] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [DuAn] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [DuAn] SET ARITHABORT OFF 
GO
ALTER DATABASE [DuAn] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [DuAn] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [DuAn] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [DuAn] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [DuAn] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [DuAn] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [DuAn] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [DuAn] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [DuAn] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [DuAn] SET  DISABLE_BROKER 
GO
ALTER DATABASE [DuAn] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [DuAn] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [DuAn] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [DuAn] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [DuAn] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [DuAn] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [DuAn] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [DuAn] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [DuAn] SET  RESTRICTED_USER 
GO
ALTER DATABASE [DuAn] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [DuAn] SET DB_CHAINING OFF 
GO
ALTER DATABASE [DuAn] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [DuAn] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
ALTER DATABASE [DuAn] SET DELAYED_DURABILITY = DISABLED 
GO
USE [DuAn]
GO
/****** Object:  Table [dbo].[BangGia]    Script Date: 11/9/2019 5:57:17 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[BangGia](
	[ID] [int] NOT NULL,
	[MaSP] [nvarchar](20) NULL,
	[MaBG] [nvarchar](5) NULL,
	[DonGia] [float] NULL,
	[Giam] [float] NULL,
 CONSTRAINT [PK_BangGia] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[BanPhong]    Script Date: 11/9/2019 5:57:17 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[BanPhong](
	[SoBan] [nvarchar](5) NOT NULL,
	[MaKV] [nvarchar](5) NULL,
	[MaBG] [nvarchar](5) NULL,
	[NoUse] [tinyint] NULL,
 CONSTRAINT [PK_BanPhong] PRIMARY KEY CLUSTERED 
(
	[SoBan] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[BoPhan]    Script Date: 11/9/2019 5:57:17 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[BoPhan](
	[MaBP] [nvarchar](5) NOT NULL,
	[MaCha] [nvarchar](5) NULL,
	[TenBP] [nvarchar](50) NULL,
 CONSTRAINT [PK_BoPhan] PRIMARY KEY CLUSTERED 
(
	[MaBP] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[ChungTu]    Script Date: 11/9/2019 5:57:17 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ChungTu](
	[SoCT] [nvarchar](7) NOT NULL,
	[NgayCT] [datetime] NULL,
	[Loai] [tinyint] NULL,
	[SoBan] [nvarchar](5) NULL,
	[MaKH] [nvarchar](7) NULL,
	[SoKhach] [int] NULL,
	[NoiDung] [nvarchar](250) NULL,
	[MaThuNgan] [nvarchar](7) NULL,
	[MaNhanVien] [nvarchar](7) NULL,
	[Giam] [float] NULL,
	[ThueVAT] [float] NULL,
	[PhiDV] [float] NULL,
	[SoTien] [float] NULL,
	[TraTruoc] [float] NULL,
	[ConNo] [float] NULL,
	[NgayDat] [datetime] NULL,
	[TrangThai] [tinyint] NULL,
 CONSTRAINT [PK_ChungTu] PRIMARY KEY CLUSTERED 
(
	[SoCT] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[DongCT]    Script Date: 11/9/2019 5:57:17 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DongCT](
	[ID] [int] NOT NULL,
	[SoCT] [nvarchar](7) NULL,
	[MaSP] [nvarchar](20) NULL,
	[MaDVT] [nvarchar](7) NULL,
	[SoLuong] [float] NULL,
	[DonGia] [float] NULL,
	[GioVao] [datetime] NULL,
	[GioRa] [datetime] NULL,
	[TraLai] [float] NULL,
	[Giam] [float] NULL,
	[GhiChu] [nvarchar](250) NULL,
 CONSTRAINT [PK_DongCT] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[DonViTinh]    Script Date: 11/9/2019 5:57:17 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DonViTinh](
	[MaDVT] [nvarchar](7) NOT NULL,
	[MaDinh] [tinyint] NULL,
 CONSTRAINT [PK_DonViTinh] PRIMARY KEY CLUSTERED 
(
	[MaDVT] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[DVTKhac]    Script Date: 11/9/2019 5:57:17 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DVTKhac](
	[ID] [int] NOT NULL,
	[MaSP] [nvarchar](20) NULL,
	[MaDVT] [nvarchar](7) NULL,
	[QuyDoi] [float] NULL,
	[IsMenu] [tinyint] NULL,
 CONSTRAINT [PK_DVTKhac] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[KhachHang]    Script Date: 11/9/2019 5:57:17 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[KhachHang](
	[MaKH] [nvarchar](7) NOT NULL,
	[MaNhom] [nvarchar](5) NULL,
	[TenKH] [nvarchar](50) NULL,
	[DiaChi] [nvarchar](250) NULL,
	[DienThoai] [nvarchar](10) NULL,
	[MaSoThue] [nvarchar](12) NULL,
	[ThuDK] [float] NULL,
	[TraDK] [float] NULL,
 CONSTRAINT [PK_KhachHang] PRIMARY KEY CLUSTERED 
(
	[MaKH] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[KhuVuc]    Script Date: 11/9/2019 5:57:17 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[KhuVuc](
	[MaKV] [nvarchar](5) NOT NULL,
	[TenKV] [nvarchar](50) NULL,
 CONSTRAINT [PK_KhuVuc] PRIMARY KEY CLUSTERED 
(
	[MaKV] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[LoaiBangGia]    Script Date: 11/9/2019 5:57:17 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[LoaiBangGia](
	[MaBG] [nvarchar](5) NOT NULL,
	[TenBG] [nvarchar](50) NULL,
	[MatDinh] [tinyint] NULL,
 CONSTRAINT [PK_LoaiBangGia] PRIMARY KEY CLUSTERED 
(
	[MaBG] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[NhanVien]    Script Date: 11/9/2019 5:57:17 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NhanVien](
	[MaNV] [nvarchar](7) NOT NULL,
	[MaBP] [nvarchar](5) NULL,
	[HoTen] [nvarchar](50) NULL,
	[DiaChi] [nvarchar](250) NULL,
	[DienThoai] [nvarchar](10) NULL,
	[MaSoThue] [nvarchar](21) NULL,
	[IsKeToan] [tinyint] NULL,
	[IsThuNgan] [tinyint] NULL,
 CONSTRAINT [PK_NhanVien] PRIMARY KEY CLUSTERED 
(
	[MaNV] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[NhomHang]    Script Date: 11/9/2019 5:57:17 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NhomHang](
	[MaNhom] [nvarchar](5) NOT NULL,
	[MaCha] [nvarchar](5) NULL,
	[TenNhom] [nvarchar](50) NULL,
	[LoaiNhom] [tinyint] NULL,
 CONSTRAINT [PK_NhomHang] PRIMARY KEY CLUSTERED 
(
	[MaNhom] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[NhomKhach]    Script Date: 11/9/2019 5:57:17 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NhomKhach](
	[MaNhom] [nvarchar](5) NOT NULL,
	[MaCha] [nvarchar](5) NULL,
	[TenNhom] [nvarchar](50) NULL,
	[LoaiNhom] [tinyint] NULL,
 CONSTRAINT [PK_NhomKhach] PRIMARY KEY CLUSTERED 
(
	[MaNhom] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[SanPham]    Script Date: 11/9/2019 5:57:17 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[SanPham](
	[MaSP] [nvarchar](20) NOT NULL,
	[MaNhom] [nvarchar](5) NULL,
	[TenSP] [nvarchar](50) NULL,
	[MaDVT] [nvarchar](7) NULL,
	[SLDK] [float] NULL,
	[GTDK] [float] NULL,
	[IsMenu] [tinyint] NULL,
 CONSTRAINT [PK_SanPham] PRIMARY KEY CLUSTERED 
(
	[MaSP] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[TaiKhoan]    Script Date: 11/9/2019 5:57:17 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[TaiKhoan](
	[MaNV] [nvarchar](7) NOT NULL,
	[TaiKhoan] [char](10) NULL,
	[MatKhau] [char](10) NULL,
 CONSTRAINT [PK_TaiKhoan] PRIMARY KEY CLUSTERED 
(
	[MaNV] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[ThanhPhan]    Script Date: 11/9/2019 5:57:17 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ThanhPhan](
	[ID] [int] NOT NULL,
	[MaSP] [nvarchar](20) NULL,
	[MaDVT] [nvarchar](7) NULL,
	[MaTP] [nvarchar](20) NULL,
	[MaDVTTP] [nvarchar](7) NULL,
	[SoLuong] [float] NULL,
 CONSTRAINT [PK_ThanhPhan] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
INSERT [dbo].[BangGia] ([ID], [MaSP], [MaBG], [DonGia], [Giam]) VALUES (1, N'SP01', N'BGBS', 3000, NULL)
INSERT [dbo].[BangGia] ([ID], [MaSP], [MaBG], [DonGia], [Giam]) VALUES (2, N'SP02', N'BGBL', 10000, NULL)
INSERT [dbo].[BangGia] ([ID], [MaSP], [MaBG], [DonGia], [Giam]) VALUES (3, N'SP03', N'BGBL', 6000, NULL)
INSERT [dbo].[BangGia] ([ID], [MaSP], [MaBG], [DonGia], [Giam]) VALUES (4, N'SP01', N'BGBS', 4000, NULL)
INSERT [dbo].[BangGia] ([ID], [MaSP], [MaBG], [DonGia], [Giam]) VALUES (5, N'SP02', N'BGBL', 11000, NULL)
INSERT [dbo].[BangGia] ([ID], [MaSP], [MaBG], [DonGia], [Giam]) VALUES (6, N'SP03', N'BGBS', 7000, NULL)
INSERT [dbo].[BanPhong] ([SoBan], [MaKV], [MaBG], [NoUse]) VALUES (N'SB01', N'KVPL', N'BGPL', NULL)
INSERT [dbo].[BanPhong] ([SoBan], [MaKV], [MaBG], [NoUse]) VALUES (N'SB02', N'KVPL', N'BGPL', NULL)
INSERT [dbo].[BanPhong] ([SoBan], [MaKV], [MaBG], [NoUse]) VALUES (N'SB03', N'KVPL', N'BGPL', NULL)
INSERT [dbo].[BanPhong] ([SoBan], [MaKV], [MaBG], [NoUse]) VALUES (N'SB04', N'KVNT', N'BGNT', NULL)
INSERT [dbo].[BanPhong] ([SoBan], [MaKV], [MaBG], [NoUse]) VALUES (N'SB05', N'KVNT', N'BGNT', NULL)
INSERT [dbo].[BanPhong] ([SoBan], [MaKV], [MaBG], [NoUse]) VALUES (N'SB06', N'KVNT', N'BGNT', NULL)
INSERT [dbo].[BanPhong] ([SoBan], [MaKV], [MaBG], [NoUse]) VALUES (N'SB07', N'KVTP', N'BGTP', NULL)
INSERT [dbo].[BanPhong] ([SoBan], [MaKV], [MaBG], [NoUse]) VALUES (N'SB08', N'KVTP', N'BGTP', NULL)
INSERT [dbo].[BanPhong] ([SoBan], [MaKV], [MaBG], [NoUse]) VALUES (N'SB09', N'KVTP', N'BGTP', NULL)
INSERT [dbo].[BoPhan] ([MaBP], [MaCha], [TenBP]) VALUES (N'BPKT', NULL, N'Ke toán')
INSERT [dbo].[BoPhan] ([MaBP], [MaCha], [TenBP]) VALUES (N'BPOT', NULL, N'Nghi lam')
INSERT [dbo].[BoPhan] ([MaBP], [MaCha], [TenBP]) VALUES (N'BPQL', NULL, N'Quản lý')
INSERT [dbo].[BoPhan] ([MaBP], [MaCha], [TenBP]) VALUES (N'BPTN', NULL, N'Thu ngân')
INSERT [dbo].[ChungTu] ([SoCT], [NgayCT], [Loai], [SoBan], [MaKH], [SoKhach], [NoiDung], [MaThuNgan], [MaNhanVien], [Giam], [ThueVAT], [PhiDV], [SoTien], [TraTruoc], [ConNo], [NgayDat], [TrangThai]) VALUES (N'CT01', CAST(N'2019-09-17 16:04:25.000' AS DateTime), 0, N'SB01', N'KH06', 1, N'Nhập Kho', N'NV07', N'NV07', 0, 0, 0, 21000, 100000, 79000, CAST(N'2019-09-16 00:00:00.000' AS DateTime), 1)
INSERT [dbo].[ChungTu] ([SoCT], [NgayCT], [Loai], [SoBan], [MaKH], [SoKhach], [NoiDung], [MaThuNgan], [MaNhanVien], [Giam], [ThueVAT], [PhiDV], [SoTien], [TraTruoc], [ConNo], [NgayDat], [TrangThai]) VALUES (N'CT02', CAST(N'2019-09-18 16:04:25.000' AS DateTime), 2, N'SB02', N'KH01', 5, N'Bán Lẻ', N'NV03', N'NV03', 0, 0, 0, 13000, 10000, -3000, CAST(N'2019-09-17 17:04:25.000' AS DateTime), 1)
INSERT [dbo].[DongCT] ([ID], [SoCT], [MaSP], [MaDVT], [SoLuong], [DonGia], [GioVao], [GioRa], [TraLai], [Giam], [GhiChu]) VALUES (1, N'CT01', N'SP08', N'Lon', 1, 15000, CAST(N'2019-09-17 05:00:00.000' AS DateTime), CAST(N'2019-09-17 05:01:00.000' AS DateTime), 0, 0, N'')
INSERT [dbo].[DongCT] ([ID], [SoCT], [MaSP], [MaDVT], [SoLuong], [DonGia], [GioVao], [GioRa], [TraLai], [Giam], [GhiChu]) VALUES (2, N'CT01', N'SP03', N'Lon', 2, 6000, NULL, NULL, 1, NULL, NULL)
INSERT [dbo].[DongCT] ([ID], [SoCT], [MaSP], [MaDVT], [SoLuong], [DonGia], [GioVao], [GioRa], [TraLai], [Giam], [GhiChu]) VALUES (3, N'CT02', N'SP09', N'Lon', 1, 13000, CAST(N'2019-09-17 08:00:00.000' AS DateTime), CAST(N'2019-09-17 08:00:00.000' AS DateTime), 0, 0, NULL)
INSERT [dbo].[DonViTinh] ([MaDVT], [MaDinh]) VALUES (N'Bao', NULL)
INSERT [dbo].[DonViTinh] ([MaDVT], [MaDinh]) VALUES (N'Chai', NULL)
INSERT [dbo].[DonViTinh] ([MaDVT], [MaDinh]) VALUES (N'Hộp', NULL)
INSERT [dbo].[DonViTinh] ([MaDVT], [MaDinh]) VALUES (N'Kg', NULL)
INSERT [dbo].[DonViTinh] ([MaDVT], [MaDinh]) VALUES (N'Lan', NULL)
INSERT [dbo].[DonViTinh] ([MaDVT], [MaDinh]) VALUES (N'Lon', NULL)
INSERT [dbo].[DonViTinh] ([MaDVT], [MaDinh]) VALUES (N'Ly', NULL)
INSERT [dbo].[DVTKhac] ([ID], [MaSP], [MaDVT], [QuyDoi], [IsMenu]) VALUES (1, N'SP04', N'Kg', 1000, NULL)
INSERT [dbo].[DVTKhac] ([ID], [MaSP], [MaDVT], [QuyDoi], [IsMenu]) VALUES (2, N'SP02', N'Thung', 24, NULL)
INSERT [dbo].[DVTKhac] ([ID], [MaSP], [MaDVT], [QuyDoi], [IsMenu]) VALUES (3, N'SP10', N'Bao', 10, NULL)
INSERT [dbo].[KhachHang] ([MaKH], [MaNhom], [TenKH], [DiaChi], [DienThoai], [MaSoThue], [ThuDK], [TraDK]) VALUES (N'KH01', N'MNKH', N'Khoa', N'Huế', N'0123456798', N'MT01', 0, 0)
INSERT [dbo].[KhachHang] ([MaKH], [MaNhom], [TenKH], [DiaChi], [DienThoai], [MaSoThue], [ThuDK], [TraDK]) VALUES (N'KH02', N'MNKH', N'Nghĩa', N'Huế', N'0123456798', N'MT02', 0, 0)
INSERT [dbo].[KhachHang] ([MaKH], [MaNhom], [TenKH], [DiaChi], [DienThoai], [MaSoThue], [ThuDK], [TraDK]) VALUES (N'KH03', N'MNKH', N'Bạch', N'Huế', N'0123465789', N'MT03', 0, 0)
INSERT [dbo].[KhachHang] ([MaKH], [MaNhom], [TenKH], [DiaChi], [DienThoai], [MaSoThue], [ThuDK], [TraDK]) VALUES (N'KH04', N'MNKH', N'Bé', N'Huế', N'0123456987', N'MT04', 0, 0)
INSERT [dbo].[KhachHang] ([MaKH], [MaNhom], [TenKH], [DiaChi], [DienThoai], [MaSoThue], [ThuDK], [TraDK]) VALUES (N'KH05', N'MNKH', N'Trang', N'Huế', N'0123654789', N'MT05', 0, 0)
INSERT [dbo].[KhachHang] ([MaKH], [MaNhom], [TenKH], [DiaChi], [DienThoai], [MaSoThue], [ThuDK], [TraDK]) VALUES (N'KH06', N'MNNC', N'Công ty A', N'Đà Nẵng', N'0123454668', N'MT06', 8000000, 4000000)
INSERT [dbo].[KhachHang] ([MaKH], [MaNhom], [TenKH], [DiaChi], [DienThoai], [MaSoThue], [ThuDK], [TraDK]) VALUES (N'KH07', N'MNNC', N'Cơ sở Xay sát', N'Huế', N'0123478594', N'MT07', 100000, 100000)
INSERT [dbo].[KhuVuc] ([MaKV], [TenKV]) VALUES (N'KVNT', N'Ngoai trơi')
INSERT [dbo].[KhuVuc] ([MaKV], [TenKV]) VALUES (N'KVPL', N'Máy lạnh')
INSERT [dbo].[KhuVuc] ([MaKV], [TenKV]) VALUES (N'KVTP', N'Trong phong')
INSERT [dbo].[LoaiBangGia] ([MaBG], [TenBG], [MatDinh]) VALUES (N'BGBL', N'Bán Lẻ', NULL)
INSERT [dbo].[LoaiBangGia] ([MaBG], [TenBG], [MatDinh]) VALUES (N'BGBS', N'Bán Sỉ', NULL)
INSERT [dbo].[NhanVien] ([MaNV], [MaBP], [HoTen], [DiaChi], [DienThoai], [MaSoThue], [IsKeToan], [IsThuNgan]) VALUES (N'NV01', N'BPKT', N'Trần Văn Cường', N'Đà Nẵng', N'0123456789', N'MT11', NULL, NULL)
INSERT [dbo].[NhanVien] ([MaNV], [MaBP], [HoTen], [DiaChi], [DienThoai], [MaSoThue], [IsKeToan], [IsThuNgan]) VALUES (N'NV02', N'BPOT', N'Bùi Xuân Lộc', N'Huế', N'0123456789', N'MT12', NULL, NULL)
INSERT [dbo].[NhanVien] ([MaNV], [MaBP], [HoTen], [DiaChi], [DienThoai], [MaSoThue], [IsKeToan], [IsThuNgan]) VALUES (N'NV03', N'BPKT', N'Nguyễn Tiến Đạt', N'Huế', N'0123456789', N'MT13', 1, 1)
INSERT [dbo].[NhanVien] ([MaNV], [MaBP], [HoTen], [DiaChi], [DienThoai], [MaSoThue], [IsKeToan], [IsThuNgan]) VALUES (N'NV04', N'BPKT', N'Trần  Văn Thắng', N'Huế', N'0123456789', N'MT14', 1, 1)
INSERT [dbo].[NhanVien] ([MaNV], [MaBP], [HoTen], [DiaChi], [DienThoai], [MaSoThue], [IsKeToan], [IsThuNgan]) VALUES (N'NV05', N'BPKT', N'Trần Văn Phông', N'Huế', N'0123456789', N'MT15', 1, NULL)
INSERT [dbo].[NhanVien] ([MaNV], [MaBP], [HoTen], [DiaChi], [DienThoai], [MaSoThue], [IsKeToan], [IsThuNgan]) VALUES (N'NV06', N'BPTN', N'Bùi Văn Tư', N'Vinh', N'0123456789', N'MT16', 1, NULL)
INSERT [dbo].[NhanVien] ([MaNV], [MaBP], [HoTen], [DiaChi], [DienThoai], [MaSoThue], [IsKeToan], [IsThuNgan]) VALUES (N'NV07', N'BPTN', N'Trương trung Hiếu', N'Huế', N'0123456789', N'MT17', NULL, 1)
INSERT [dbo].[NhanVien] ([MaNV], [MaBP], [HoTen], [DiaChi], [DienThoai], [MaSoThue], [IsKeToan], [IsThuNgan]) VALUES (N'NV08', N'BPTN', N'Lê Văn Thiện', N'Huế', N'0123469787', N'MT18', NULL, 1)
INSERT [dbo].[NhanVien] ([MaNV], [MaBP], [HoTen], [DiaChi], [DienThoai], [MaSoThue], [IsKeToan], [IsThuNgan]) VALUES (N'NV09', N'BPTN', N'Kha Ban', N'Vinh', N'0123469787', N'MT19', NULL, 1)
INSERT [dbo].[NhanVien] ([MaNV], [MaBP], [HoTen], [DiaChi], [DienThoai], [MaSoThue], [IsKeToan], [IsThuNgan]) VALUES (N'NV10', N'BPTN', N'Khoa', NULL, NULL, NULL, NULL, NULL)
INSERT [dbo].[NhanVien] ([MaNV], [MaBP], [HoTen], [DiaChi], [DienThoai], [MaSoThue], [IsKeToan], [IsThuNgan]) VALUES (N'NV99', N'BPQL', N'Admin', NULL, NULL, NULL, NULL, NULL)
INSERT [dbo].[NhomHang] ([MaNhom], [MaCha], [TenNhom], [LoaiNhom]) VALUES (N'MNAU', NULL, N'Thức ăn', 0)
INSERT [dbo].[NhomHang] ([MaNhom], [MaCha], [TenNhom], [LoaiNhom]) VALUES (N'MNCB', NULL, N'Chế Biến', 2)
INSERT [dbo].[NhomHang] ([MaNhom], [MaCha], [TenNhom], [LoaiNhom]) VALUES (N'MNDV', NULL, N'Dịch vụ', 3)
INSERT [dbo].[NhomHang] ([MaNhom], [MaCha], [TenNhom], [LoaiNhom]) VALUES (N'MNNL', NULL, N'Nguyên liệu', 1)
INSERT [dbo].[NhomHang] ([MaNhom], [MaCha], [TenNhom], [LoaiNhom]) VALUES (N'MNNU', NULL, N'Nước uống', 0)
INSERT [dbo].[NhomKhach] ([MaNhom], [MaCha], [TenNhom], [LoaiNhom]) VALUES (N'MNKH', NULL, N'Nhóm khách hàng', 0)
INSERT [dbo].[NhomKhach] ([MaNhom], [MaCha], [TenNhom], [LoaiNhom]) VALUES (N'MNNC', NULL, N'Nhà cung cấp', 1)
INSERT [dbo].[NhomKhach] ([MaNhom], [MaCha], [TenNhom], [LoaiNhom]) VALUES (N'MNTX', NULL, N'Khách hàng thường xuyên', 0)
INSERT [dbo].[SanPham] ([MaSP], [MaNhom], [TenSP], [MaDVT], [SLDK], [GTDK], [IsMenu]) VALUES (N'SP01', N'MNNU', N'Nước lọc', N'Chai', 50, 3000, NULL)
INSERT [dbo].[SanPham] ([MaSP], [MaNhom], [TenSP], [MaDVT], [SLDK], [GTDK], [IsMenu]) VALUES (N'SP02', N'MNNU', N'Nước bò húc', N'Lon', 55, 10000, NULL)
INSERT [dbo].[SanPham] ([MaSP], [MaNhom], [TenSP], [MaDVT], [SLDK], [GTDK], [IsMenu]) VALUES (N'SP03', N'MNNU', N'Nước CocaCola', N'Chai', 60, 6000, NULL)
INSERT [dbo].[SanPham] ([MaSP], [MaNhom], [TenSP], [MaDVT], [SLDK], [GTDK], [IsMenu]) VALUES (N'SP04', N'MNNL', N'Bột cafe', N'Kg', 12, 120000, NULL)
INSERT [dbo].[SanPham] ([MaSP], [MaNhom], [TenSP], [MaDVT], [SLDK], [GTDK], [IsMenu]) VALUES (N'SP05', N'MNNL', N'Đường', N'Kg', 14, 12000, NULL)
INSERT [dbo].[SanPham] ([MaSP], [MaNhom], [TenSP], [MaDVT], [SLDK], [GTDK], [IsMenu]) VALUES (N'SP06', N'MNNL', N'Muối', N'kg', 100, 5000, NULL)
INSERT [dbo].[SanPham] ([MaSP], [MaNhom], [TenSP], [MaDVT], [SLDK], [GTDK], [IsMenu]) VALUES (N'SP07', N'MNCB', N'Cam', N'Kg', 3, 35000, NULL)
INSERT [dbo].[SanPham] ([MaSP], [MaNhom], [TenSP], [MaDVT], [SLDK], [GTDK], [IsMenu]) VALUES (N'SP08', N'MNCB', N'Ổi', N'Kg', 4, 15000, NULL)
INSERT [dbo].[SanPham] ([MaSP], [MaNhom], [TenSP], [MaDVT], [SLDK], [GTDK], [IsMenu]) VALUES (N'SP09', N'MNCB', N'Nho', N'Kg', 5, 13000, NULL)
INSERT [dbo].[SanPham] ([MaSP], [MaNhom], [TenSP], [MaDVT], [SLDK], [GTDK], [IsMenu]) VALUES (N'SP10', N'MNAU', N'Thuốc lá', N'Bao', 12, 150000, NULL)
INSERT [dbo].[TaiKhoan] ([MaNV], [TaiKhoan], [MatKhau]) VALUES (N'NV01', N'NV01      ', N'1         ')
INSERT [dbo].[TaiKhoan] ([MaNV], [TaiKhoan], [MatKhau]) VALUES (N'NV02', N'NV02      ', N'1         ')
INSERT [dbo].[TaiKhoan] ([MaNV], [TaiKhoan], [MatKhau]) VALUES (N'NV03', N'NV03      ', N'chao      ')
INSERT [dbo].[TaiKhoan] ([MaNV], [TaiKhoan], [MatKhau]) VALUES (N'NV04', N'NV04      ', N'fdfgh1    ')
INSERT [dbo].[TaiKhoan] ([MaNV], [TaiKhoan], [MatKhau]) VALUES (N'NV05', N'NV05      ', N'ftruhh7   ')
INSERT [dbo].[TaiKhoan] ([MaNV], [TaiKhoan], [MatKhau]) VALUES (N'NV06', N'NV06      ', N'2542g     ')
INSERT [dbo].[TaiKhoan] ([MaNV], [TaiKhoan], [MatKhau]) VALUES (N'NV07', N'NV07      ', N'8         ')
INSERT [dbo].[TaiKhoan] ([MaNV], [TaiKhoan], [MatKhau]) VALUES (N'NV08', N'NV08      ', N'tghthjyh5 ')
INSERT [dbo].[TaiKhoan] ([MaNV], [TaiKhoan], [MatKhau]) VALUES (N'NV09', N'NV09      ', N'jl54      ')
INSERT [dbo].[TaiKhoan] ([MaNV], [TaiKhoan], [MatKhau]) VALUES (N'NV10', N'NV10      ', N'1         ')
INSERT [dbo].[TaiKhoan] ([MaNV], [TaiKhoan], [MatKhau]) VALUES (N'NV99', N'NV99      ', N'1         ')
ALTER TABLE [dbo].[BangGia]  WITH CHECK ADD  CONSTRAINT [FK_BangGia_LoaiBangGia] FOREIGN KEY([MaBG])
REFERENCES [dbo].[LoaiBangGia] ([MaBG])
GO
ALTER TABLE [dbo].[BangGia] CHECK CONSTRAINT [FK_BangGia_LoaiBangGia]
GO
ALTER TABLE [dbo].[BangGia]  WITH CHECK ADD  CONSTRAINT [FK_BangGia_SanPham] FOREIGN KEY([MaSP])
REFERENCES [dbo].[SanPham] ([MaSP])
GO
ALTER TABLE [dbo].[BangGia] CHECK CONSTRAINT [FK_BangGia_SanPham]
GO
ALTER TABLE [dbo].[BanPhong]  WITH CHECK ADD  CONSTRAINT [FK_BanPhong_KhuVuc] FOREIGN KEY([MaKV])
REFERENCES [dbo].[KhuVuc] ([MaKV])
GO
ALTER TABLE [dbo].[BanPhong] CHECK CONSTRAINT [FK_BanPhong_KhuVuc]
GO
ALTER TABLE [dbo].[ChungTu]  WITH CHECK ADD  CONSTRAINT [FK_ChungTu_BanPhong] FOREIGN KEY([SoBan])
REFERENCES [dbo].[BanPhong] ([SoBan])
GO
ALTER TABLE [dbo].[ChungTu] CHECK CONSTRAINT [FK_ChungTu_BanPhong]
GO
ALTER TABLE [dbo].[ChungTu]  WITH CHECK ADD  CONSTRAINT [FK_ChungTu_KhachHang] FOREIGN KEY([MaKH])
REFERENCES [dbo].[KhachHang] ([MaKH])
GO
ALTER TABLE [dbo].[ChungTu] CHECK CONSTRAINT [FK_ChungTu_KhachHang]
GO
ALTER TABLE [dbo].[ChungTu]  WITH CHECK ADD  CONSTRAINT [FK_ChungTu_NhanVien] FOREIGN KEY([MaNhanVien])
REFERENCES [dbo].[NhanVien] ([MaNV])
GO
ALTER TABLE [dbo].[ChungTu] CHECK CONSTRAINT [FK_ChungTu_NhanVien]
GO
ALTER TABLE [dbo].[DongCT]  WITH CHECK ADD  CONSTRAINT [FK_DongCT_ChungTu] FOREIGN KEY([SoCT])
REFERENCES [dbo].[ChungTu] ([SoCT])
GO
ALTER TABLE [dbo].[DongCT] CHECK CONSTRAINT [FK_DongCT_ChungTu]
GO
ALTER TABLE [dbo].[DongCT]  WITH CHECK ADD  CONSTRAINT [FK_DongCT_SanPham] FOREIGN KEY([MaSP])
REFERENCES [dbo].[SanPham] ([MaSP])
GO
ALTER TABLE [dbo].[DongCT] CHECK CONSTRAINT [FK_DongCT_SanPham]
GO
ALTER TABLE [dbo].[DVTKhac]  WITH CHECK ADD  CONSTRAINT [FK_DVTKhac_SanPham] FOREIGN KEY([MaSP])
REFERENCES [dbo].[SanPham] ([MaSP])
GO
ALTER TABLE [dbo].[DVTKhac] CHECK CONSTRAINT [FK_DVTKhac_SanPham]
GO
ALTER TABLE [dbo].[KhachHang]  WITH CHECK ADD  CONSTRAINT [FK_KhachHang_NhomKhach] FOREIGN KEY([MaNhom])
REFERENCES [dbo].[NhomKhach] ([MaNhom])
GO
ALTER TABLE [dbo].[KhachHang] CHECK CONSTRAINT [FK_KhachHang_NhomKhach]
GO
ALTER TABLE [dbo].[NhanVien]  WITH CHECK ADD  CONSTRAINT [FK_NhanVien_BoPhan] FOREIGN KEY([MaBP])
REFERENCES [dbo].[BoPhan] ([MaBP])
GO
ALTER TABLE [dbo].[NhanVien] CHECK CONSTRAINT [FK_NhanVien_BoPhan]
GO
ALTER TABLE [dbo].[SanPham]  WITH CHECK ADD  CONSTRAINT [FK_SanPham_DonViTinh] FOREIGN KEY([MaDVT])
REFERENCES [dbo].[DonViTinh] ([MaDVT])
GO
ALTER TABLE [dbo].[SanPham] CHECK CONSTRAINT [FK_SanPham_DonViTinh]
GO
ALTER TABLE [dbo].[SanPham]  WITH CHECK ADD  CONSTRAINT [FK_SanPham_NhomHang] FOREIGN KEY([MaNhom])
REFERENCES [dbo].[NhomHang] ([MaNhom])
GO
ALTER TABLE [dbo].[SanPham] CHECK CONSTRAINT [FK_SanPham_NhomHang]
GO
ALTER TABLE [dbo].[TaiKhoan]  WITH CHECK ADD  CONSTRAINT [FK_TaiKhoan_NhanVien] FOREIGN KEY([MaNV])
REFERENCES [dbo].[NhanVien] ([MaNV])
GO
ALTER TABLE [dbo].[TaiKhoan] CHECK CONSTRAINT [FK_TaiKhoan_NhanVien]
GO
ALTER TABLE [dbo].[ThanhPhan]  WITH CHECK ADD  CONSTRAINT [FK_ThanhPhan_SanPham] FOREIGN KEY([MaSP])
REFERENCES [dbo].[SanPham] ([MaSP])
GO
ALTER TABLE [dbo].[ThanhPhan] CHECK CONSTRAINT [FK_ThanhPhan_SanPham]
GO
USE [master]
GO
ALTER DATABASE [DuAn] SET  READ_WRITE 
GO
