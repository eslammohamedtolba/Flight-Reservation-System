
create database Flight_Reservation_System

create table Passenger
(
	id int IDENTITY(1,1) primary key,
	SSN int unique NOT NULL,
	Fname varchar(15) NOT NULL,
	Sname varchar(15) NOT NULL,
	Email varchar(30) not NULL,
	Birthday DateTime not NULL,
	phone varchar(20) not NULL,
	passport varchar(30) not NULL,
);

create table Administrator
(
	username varchar(40) unique not null,
	password_ADmin varchar(30) not null
);

create table Booking
(
	id int IDENTITY(1,1) primary key,
	passenger_id int not null,
	Booking_Date DATETIME not null,
	Total_price DECIMAL(10,2) not null,
	Tickets_Num int not null,
	constraint Pass_Book_FK foreign key (passenger_id) references passenger(id)
);

create table Airline
(
	id INT Identity(1,1) PRIMARY KEY,
	Name_Airline varchar(20) not null,
	Headquarters varchar(30) not null
)


create table Aircraft
(
	id INT Identity(1,1) PRIMARY KEY,
	Airline_id int not null,
	model varchar(30) not null,
	total_Num_seats int not null,
	status_Aircraft Tinyint not null,
	constraint Aircraft_Airline_FK foreign key (Airline_id) references Airline(id)
);

create table Flight
(
	id INT Identity(1,1) PRIMARY KEY,
	Airline_id int not null,
	Aircraft_id int not null,
	Dep_time DATETIME not null,
	Arrival_time DATETIME not null,
	Dep_airport varchar(30) not null,
	Arrival_airport varchar(30) not null,
	Dep_country varchar(30) not null,
	Arrival_country varchar(30) not null,
	ReservedNum int not null,

	constraint Flight_Airline_FK foreign key (Airline_id) references Airline(id),
	constraint Flight_Aircraft_FK foreign key (Aircraft_id) references Aircraft(id)
);

create table ticket
(
	id int Identity(1,1) primary key,
	Booking_id int not null,
	Flight_id int not null,
	price int not null,
	seat_num int unique not null,
	class varchar(10) NOT NULL,
	Gate_Num int NOT NULL,

	constraint ticket_Booking_FK foreign key (Booking_id) references Booking(id),
	constraint ticket_Flight_FK foreign key (Flight_id) references Flight(id)
);

