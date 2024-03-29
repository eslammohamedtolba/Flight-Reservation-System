USE [master]
GO
/****** Object:  Database [Flight_Reservation_System]    Script Date: 5/24/2023 7:35:54 AM ******/
CREATE DATABASE [Flight_Reservation_System]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'Flight_Reservation_System', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.MSSQLSERVER\MSSQL\DATA\Flight_Reservation_System.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'Flight_Reservation_System_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.MSSQLSERVER\MSSQL\DATA\Flight_Reservation_System_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT, LEDGER = OFF
GO
ALTER DATABASE [Flight_Reservation_System] SET COMPATIBILITY_LEVEL = 160
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [Flight_Reservation_System].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [Flight_Reservation_System] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [Flight_Reservation_System] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [Flight_Reservation_System] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [Flight_Reservation_System] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [Flight_Reservation_System] SET ARITHABORT OFF 
GO
ALTER DATABASE [Flight_Reservation_System] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [Flight_Reservation_System] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [Flight_Reservation_System] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [Flight_Reservation_System] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [Flight_Reservation_System] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [Flight_Reservation_System] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [Flight_Reservation_System] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [Flight_Reservation_System] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [Flight_Reservation_System] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [Flight_Reservation_System] SET  ENABLE_BROKER 
GO
ALTER DATABASE [Flight_Reservation_System] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [Flight_Reservation_System] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [Flight_Reservation_System] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [Flight_Reservation_System] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [Flight_Reservation_System] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [Flight_Reservation_System] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [Flight_Reservation_System] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [Flight_Reservation_System] SET RECOVERY FULL 
GO
ALTER DATABASE [Flight_Reservation_System] SET  MULTI_USER 
GO
ALTER DATABASE [Flight_Reservation_System] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [Flight_Reservation_System] SET DB_CHAINING OFF 
GO
ALTER DATABASE [Flight_Reservation_System] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [Flight_Reservation_System] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [Flight_Reservation_System] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [Flight_Reservation_System] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
EXEC sys.sp_db_vardecimal_storage_format N'Flight_Reservation_System', N'ON'
GO
ALTER DATABASE [Flight_Reservation_System] SET QUERY_STORE = ON
GO
ALTER DATABASE [Flight_Reservation_System] SET QUERY_STORE (OPERATION_MODE = READ_WRITE, CLEANUP_POLICY = (STALE_QUERY_THRESHOLD_DAYS = 30), DATA_FLUSH_INTERVAL_SECONDS = 900, INTERVAL_LENGTH_MINUTES = 60, MAX_STORAGE_SIZE_MB = 1000, QUERY_CAPTURE_MODE = AUTO, SIZE_BASED_CLEANUP_MODE = AUTO, MAX_PLANS_PER_QUERY = 200, WAIT_STATS_CAPTURE_MODE = ON)
GO
USE [Flight_Reservation_System]
GO
/****** Object:  User [Flight_RS]    Script Date: 5/24/2023 7:35:54 AM ******/
CREATE USER [Flight_RS] FOR LOGIN [Flight_RS] WITH DEFAULT_SCHEMA=[dbo]
GO
ALTER ROLE [db_owner] ADD MEMBER [Flight_RS]
GO
ALTER ROLE [db_datareader] ADD MEMBER [Flight_RS]
GO
ALTER ROLE [db_datawriter] ADD MEMBER [Flight_RS]
GO
USE [Flight_Reservation_System]
GO
/****** Object:  Sequence [dbo].[Ticket_SeatNumSequence]    Script Date: 5/24/2023 7:35:54 AM ******/
CREATE SEQUENCE [dbo].[Ticket_SeatNumSequence] 
 AS [int]
 START WITH 1
 INCREMENT BY 1
 MINVALUE -2147483648
 MAXVALUE 2147483647
 CACHE 
GO
/****** Object:  Table [dbo].[Administrator]    Script Date: 5/24/2023 7:35:54 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Administrator](
	[username] [varchar](40) NOT NULL,
	[password_ADmin] [varchar](30) NOT NULL,
UNIQUE NONCLUSTERED 
(
	[username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Aircraft]    Script Date: 5/24/2023 7:35:54 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Aircraft](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[Airline_id] [int] NOT NULL,
	[model] [varchar](30) NOT NULL,
	[total_Num_seats] [int] NOT NULL,
	[status_Aircraft] [tinyint] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Airline]    Script Date: 5/24/2023 7:35:54 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Airline](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[Name_Airline] [varchar](20) NOT NULL,
	[Headquarters] [varchar](30) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
 CONSTRAINT [AirlineName_UQ] UNIQUE NONCLUSTERED 
(
	[Name_Airline] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Booking]    Script Date: 5/24/2023 7:35:54 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Booking](
	[Booking_Date] [datetime] NOT NULL,
	[Total_price] [decimal](10, 2) NOT NULL,
	[Tickets_Num] [int] NOT NULL,
	[passenger_ssn] [int] NOT NULL,
	[id] [varchar](100) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Flight]    Script Date: 5/24/2023 7:35:54 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Flight](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[Aircraft_id] [int] NOT NULL,
	[Dep_time] [datetime] NOT NULL,
	[Arrival_time] [datetime] NOT NULL,
	[Dep_airport] [varchar](30) NOT NULL,
	[Arrival_airport] [varchar](30) NOT NULL,
	[Dep_country] [varchar](30) NOT NULL,
	[Arrival_country] [varchar](30) NOT NULL,
	[ReservedNum] [int] NOT NULL,
	[Economyprice] [float] NOT NULL,
	[VIPprice] [float] NOT NULL,
	[Businessprice] [float] NOT NULL,
	[Gate_num] [int] NOT NULL,
	[BusinessST] [int] NULL,
	[VIPST] [int] NULL,
	[EconomyST] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Passenger]    Script Date: 5/24/2023 7:35:54 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Passenger](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[SSN] [int] NOT NULL,
	[Fname] [varchar](15) NOT NULL,
	[Sname] [varchar](15) NOT NULL,
	[Email] [varchar](30) NOT NULL,
	[Birthday] [date] NULL,
	[phone] [varchar](20) NOT NULL,
	[passport] [varchar](30) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
UNIQUE NONCLUSTERED 
(
	[SSN] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Payment]    Script Date: 5/24/2023 7:35:54 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Payment](
	[method] [varchar](30) NOT NULL,
	[Booking_id] [varchar](100) NOT NULL,
	[Date_P] [datetime] NOT NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Ticket]    Script Date: 5/24/2023 7:35:54 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Ticket](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[Flight_id] [int] NOT NULL,
	[price] [float] NOT NULL,
	[class] [varchar](10) NOT NULL,
	[Gate_Num] [int] NOT NULL,
	[Booking_id] [varchar](100) NOT NULL,
	[seat_num] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[Flight] ADD  DEFAULT ((1)) FOR [BusinessST]
GO
ALTER TABLE [dbo].[Flight] ADD  DEFAULT ((1)) FOR [VIPST]
GO
ALTER TABLE [dbo].[Flight] ADD  DEFAULT ((1)) FOR [EconomyST]
GO
ALTER TABLE [dbo].[Aircraft]  WITH CHECK ADD  CONSTRAINT [Aircraft_Airline_FK] FOREIGN KEY([Airline_id])
REFERENCES [dbo].[Airline] ([id])
GO
ALTER TABLE [dbo].[Aircraft] CHECK CONSTRAINT [Aircraft_Airline_FK]
GO
ALTER TABLE [dbo].[Booking]  WITH CHECK ADD  CONSTRAINT [Booking_pass_FK] FOREIGN KEY([passenger_ssn])
REFERENCES [dbo].[Passenger] ([SSN])
GO
ALTER TABLE [dbo].[Booking] CHECK CONSTRAINT [Booking_pass_FK]
GO
ALTER TABLE [dbo].[Flight]  WITH CHECK ADD  CONSTRAINT [Flight_Aircraft_FK] FOREIGN KEY([Aircraft_id])
REFERENCES [dbo].[Aircraft] ([id])
GO
ALTER TABLE [dbo].[Flight] CHECK CONSTRAINT [Flight_Aircraft_FK]
GO
ALTER TABLE [dbo].[Payment]  WITH CHECK ADD  CONSTRAINT [payment_Booking_FK] FOREIGN KEY([Booking_id])
REFERENCES [dbo].[Booking] ([id])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Payment] CHECK CONSTRAINT [payment_Booking_FK]
GO
ALTER TABLE [dbo].[Ticket]  WITH CHECK ADD  CONSTRAINT [ticket_Booking_FK] FOREIGN KEY([Booking_id])
REFERENCES [dbo].[Booking] ([id])
GO
ALTER TABLE [dbo].[Ticket] CHECK CONSTRAINT [ticket_Booking_FK]
GO
ALTER TABLE [dbo].[Ticket]  WITH CHECK ADD  CONSTRAINT [ticket_Flight_FK] FOREIGN KEY([Flight_id])
REFERENCES [dbo].[Flight] ([id])
GO
ALTER TABLE [dbo].[Ticket] CHECK CONSTRAINT [ticket_Flight_FK]
GO
USE [master]
GO
ALTER DATABASE [Flight_Reservation_System] SET  READ_WRITE 
GO
