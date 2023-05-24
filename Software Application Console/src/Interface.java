import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Interface {
    public Interface(){
        Display();
    }
    public void Display(){
        Scanner in =new Scanner(System.in);
        int choice;
        ALL:while(true){
            System.out.println("(1) Signing up as a Passenger\n" +
                               "(2) signing in as a Passenger\n" +
                               "(3) log in as a admin\n" +
                               "(4) exist program");
            choice=in.nextInt();
            if(choice==1){
                int SSN,year,month,day;
                String Fname,Sname,Email,phone,passport;
                LocalDate Birthday;Passenger p=new Passenger();
                System.out.print("please enter your ssn ");SSN=in.nextInt();
                in.nextLine();
                System.out.print("please enter your first name ");Fname=in.nextLine();
                System.out.print("please enter your second name ");Sname=in.nextLine();
                System.out.print("please enter your Email ");Email=in.next();

                System.out.print("please enter your year ");year=in.nextInt();
                System.out.print("please enter your month ");month=in.nextInt();
                System.out.print("please enter your day ");day=in.nextInt();
                Birthday = LocalDate.of(year, month, day);
                System.out.print("please enter your Phone ");phone=in.next();
                System.out.print("please enter your Passport ");passport=in.next();
                if(p.PassNamebySSN(SSN).equals("null")){
                    p.setSSN(SSN);p.setFname(Fname);
                    p.setSname(Sname);p.setEmail(Email);
                    p.setBirthday(Birthday);p.setPhone(phone);
                    p.setPassport(passport);
                    p.InsertPassenger();
                }
                else{
                    System.out.println("the user is already exist");
                }
                PassMenu(SSN);
                break ALL;
            }
            else if(choice==2){
                int SSN;Passenger p=new Passenger();
                Part:while(true){
                    System.out.print("please enter your ssn ");SSN=in.nextInt();
                    if(p.PassNamebySSN(SSN).equals("null")){
                        System.out.println("the user doesn't exist");
                    }
                    else{
                        System.out.println("the process successfully done");
                        break Part;
                    }
                }
                PassMenu(SSN);
                break ALL;
            }
            else if(choice==3){
                String Username,Password;Administrator Ad=new Administrator();
                Part:while(true){
                    System.out.println("Please enter your username:");
                    in.nextLine();
                    Username = in.nextLine();
                    System.out.println("Please enter your password:");
                    Password = in.nextLine();
                    System.out.println("username: "+Username+" password "+Password);
                    if(Ad.Login(Username,Password)){
                        System.out.println("the process successfully done");
                        break Part;
                    }
                    else{
                        System.out.println("the process failed, please enter valid data");
                    }
                }
                AdminMenu();
                break ALL;
            }
            else if(choice==4){
                break ALL;
            }
            else{
                System.out.println("please enter a valid choice");
            }
        }
    }
    public void AdminMenu(){
        Scanner in =new Scanner(System.in);int choice;
        Admin:while(true){
            System.out.println("(1) Adding an aircraft\n"+
                               "(2) Updating an aircraft details\n"+
                               "(3) Adding a flight\n"+
                               "(4) Updating a flight details\n"+
                               "(5) cancel flight\n" +
                               "(6) Printing All flight\n" +
                               "(7) Printing All aircraft\n" +
                               "(8) return to last Menue");
            choice=in.nextInt();
            if(choice==1){
                Airline Al=new Airline();
                int Airline_id,total_Num_seats;
                String model;
                while(true){
                    System.out.print("please enter Airline id ");
                    Airline_id=in.nextInt();
                    if(Al.NameAirlineid(Airline_id).equals("null")){
                        System.out.println("the Airline id is not valid please enter valid id");
                    }
                    else{
                        break;
                    }
                }
                System.out.print("please enter total number of Aircraft seats ");
                total_Num_seats=in.nextInt();
                System.out.print("please enter Aircraft model ");
                model=in.next();
                in.nextLine();
                Aircraft Af=new Aircraft(Airline_id,model,total_Num_seats);
                Af.InsertAircraft();
            }
            else if(choice==2){
                Aircraft Af=new Aircraft();
                int Aircraft_id;
                int status_Aircraft;
                while(true){
                    System.out.print("please enter Aircraft id ");Aircraft_id=in.nextInt();
                    if(Af.ModelAircraftbyid(Aircraft_id).equals("null")){
                        System.out.println("the Aircraft id is not valid please enter valid id");
                    }
                    else{
                        System.out.println("Please enter the Aircraft status");status_Aircraft=in.nextInt();
                        Af.ChangeStatus(Aircraft_id,status_Aircraft);
                        System.out.println("the process successfully done");
                        break;
                    }
                }
            }
            else if(choice==3){
                Aircraft Af=new Aircraft();
                int Aircraft_id,year,month,day,hour,minute,Economyprice,VIPprice,businessprice,Gate_n;
                LocalDateTime Dep_time,Arrival_time;
                String Dep_airport,Arrival_airport,Dep_country,Arrival_country;
                while(true){
                    System.out.print("please enter Aircraft id ");Aircraft_id=in.nextInt();
                    if(Af.ModelAircraftbyid(Aircraft_id).equals("null")){
                        System.out.println("the Aircraft id is not valid please enter valid id");
                    }
                    else{
                        if(!Af.GetStatus(Aircraft_id)){
                            Af.ChangeStatus(Aircraft_id,1);
                            break;
                        }
                        else{
                            System.out.println("Aircraft id you entered isn't available");
                        }
                    }
                }
                System.out.print("please enter year of Dep ");year=in.nextInt();
                System.out.print("please enter month of Dep ");month=in.nextInt();
                System.out.print("please enter day Dep ");day=in.nextInt();
                System.out.print("please enter hour Dep ");hour=in.nextInt();
                System.out.print("please enter minutes Dep ");minute=in.nextInt();
                Dep_time = LocalDateTime.of(year, month, day, hour, minute,0);

                System.out.print("please enter year of Arrival ");year=in.nextInt();
                System.out.print("please enter month of Arrival ");month=in.nextInt();
                System.out.print("please enter day of Arrival ");day=in.nextInt();
                System.out.print("please enter hour of Arrival ");hour=in.nextInt();
                System.out.print("please enter minutes of Arrival ");minute=in.nextInt();
                Arrival_time=LocalDateTime.of(year, month, day, hour, minute,0);

                in.nextLine();
                System.out.print("please enter Dep_airport ");Dep_airport=in.nextLine();
                System.out.print("please enter Arrival_airport ");Arrival_airport=in.nextLine();
                System.out.print("please enter Dep_country ");Dep_country=in.nextLine();
                System.out.print("please enter Arrival_country ");Arrival_country=in.nextLine();

                System.out.print("please enter Gate number ");Gate_n=in.nextInt();
                System.out.print("please enter Economy price ");Economyprice=in.nextInt();
                System.out.print("please enter VIP price ");VIPprice=in.nextInt();
                System.out.print("please enter business price ");businessprice=in.nextInt();

                Flight F=new Flight(Economyprice,VIPprice,businessprice,Aircraft_id,Gate_n,Dep_time,Arrival_time,Dep_airport,Arrival_airport,Dep_country,Arrival_country);
                F.InsertFlight();
            }
            else if(choice==4){
                int FLight_id,Aircraft_id,year,month,day,hour,minute,Economyprice,VIPprice,businessprice,Gate_n;
                LocalDateTime Dep_time,Arrival_time;
                String Arrival_airport;
                Aircraft Af=new Aircraft();
                Flight F=new Flight();
                while(true){
                    System.out.print("please enter Flight id ");FLight_id=in.nextInt();
                    if(!F.ExistFlight(FLight_id)){
                        System.out.println("the Flight id is not valid please enter valid id");
                    }
                    else{
                        break;
                    }
                }
                int optoin;
                Aircraft_id=F.GetAircraftIdByFlight(FLight_id);
                System.out.print("Do you want to change flight Aircraft 1-yes anyNumber-no ");optoin=in.nextInt();
                if(optoin==1){
                    Af.ChangeStatus(Aircraft_id,0);
                    while(true){
                        System.out.print("please enter Aircraft id ");Aircraft_id=in.nextInt();
                        if(Af.ModelAircraftbyid(Aircraft_id).equals("null")){
                            System.out.println("the Aircraft id is not valid please enter valid id");
                        }
                        else{
                            if(!Af.GetStatus(Aircraft_id)){
                                Af.ChangeStatus(Aircraft_id,1);
                                break;
                            }
                            else{
                                System.out.println("Aircraft id you entered isn't available");
                            }
                        }
                    }
                }
                System.out.print("please enter year of Dep ");year=in.nextInt();
                System.out.print("please enter month of Dep ");month=in.nextInt();
                System.out.print("please enter day Dep ");day=in.nextInt();
                System.out.print("please enter hour Dep ");hour=in.nextInt();
                System.out.print("please enter minutes Dep ");minute=in.nextInt();
                Dep_time = LocalDateTime.of(year, month, day, hour, minute,0);

                System.out.print("please enter year of Arrival ");year=in.nextInt();
                System.out.print("please enter month of Arrival ");month=in.nextInt();
                System.out.print("please enter day of Arrival ");day=in.nextInt();
                System.out.print("please enter hour of Arrival ");hour=in.nextInt();
                System.out.print("please enter minutes of Arrival ");minute=in.nextInt();
                Arrival_time=LocalDateTime.of(year, month, day, hour, minute,0);
                in.nextLine();
                System.out.print("please enter Arrival_airport ");Arrival_airport=in.nextLine();

                System.out.print("please enter Gate number ");Gate_n=in.nextInt();
                System.out.print("please enter Economy price ");Economyprice=in.nextInt();
                System.out.print("please enter VIP price ");VIPprice=in.nextInt();
                System.out.print("please enter business price ");businessprice=in.nextInt();

                F.UpdateFlight(FLight_id,Aircraft_id,Dep_time,Arrival_time,Arrival_airport,Gate_n,Economyprice,VIPprice,businessprice);
            }
            else if(choice==5){
                Flight F=new Flight();
                int FLight_id;
                while(true){
                    System.out.print("please enter Flight id ");FLight_id=in.nextInt();
                    if(!F.ExistFlight(FLight_id)){
                        System.out.println("the Flight id is not valid please enter valid id");
                    }
                    else{
                        break;
                    }
                }
                F.DeleteFlight(FLight_id);
            }
            else if(choice==6){
                new Flight().printFlights();
                System.out.println();
            }
            else if(choice==7){
                new Aircraft().printAircrafts();
                System.out.println();
            }
            else if(choice==8){
                Display();
                break Admin;
            }
            else{
                System.out.println("please enter valid choice");
            }
        }
    }
    public void PassMenu(int SSN){
        Scanner in=new Scanner(System.in);
        int choice;
        while(true){
            System.out.println("(1) Showing a list of available flights that satisfy certain criteria\n" +
                               "(2) Updating a user's details.\n" +
                               "(3) book flight\n" +
                               "(4) changing flight class\n"+
                               "(5) return to last Menue");
            choice=in.nextInt();
            if(choice==1){
                int num_Seats;
                String source,destination;Flight F=new Flight();
                System.out.print("please enter number of seats ");num_Seats=in.nextInt();
                in.nextLine();
                System.out.print("please enter source ");source=in.nextLine();
                System.out.print("please enter distination ");destination=in.nextLine();
                F.printFlightsccriteria(source,destination,num_Seats);
            }
            else if(choice==2){
                String Email,phone,passport;Passenger p=new Passenger();
                System.out.print("please enter your email ");Email=in.next();
                System.out.print("please enter your phone ");phone=in.next();
                System.out.print("please enter your passport ");passport=in.next();
                p.setEmail(Email);p.setPassport(passport);p.setPhone(phone);
                p.UpdatePassenger(SSN);
            }
            else if(choice==3){
                Flight F=new Flight();
                int _class=0,totalPrice=0;
                int Flight_id,num_Tickets;

                String source,destination,myBooking_id;
                System.out.print("please enter number of Tickets ");num_Tickets=in.nextInt();
                in.nextLine();
                System.out.print("please enter source ");source=in.nextLine();
                System.out.print("please enter distination ");destination=in.nextLine();
                F.printFlightsccriteria(source,destination,num_Tickets);
                while(true){
                    System.out.println("Enter Valid Flight Number");Flight_id=in.nextInt();
                    if(F.ExistFlight(Flight_id)){
                        break;
                    }
                    else{
                        System.out.print("THe Flight Id isn't available, please ");
                    }
                }
                ArrayList<Ticket>Tickets=new ArrayList<Ticket>(num_Tickets);
                ArrayList<Float>PricesSeats=F.GetPrices(Flight_id);
                ArrayList<Integer>NumSeats=F.GetSeatnum(Flight_id);
                for(int i=0;i<num_Tickets;i++){
                    Ticket t=new Ticket();
                    System.out.println("please enter Flight class" +
                            "\n\t(1) Economy price " +PricesSeats.get(0)+
                            "\n\t(2) VIP price " +PricesSeats.get(1)+
                            "\n\t(3) business price "+PricesSeats.get(2));
                    _class=in.nextInt();
                    if(_class==1){
                        totalPrice+=PricesSeats.get(0);
                        t.setPrice(PricesSeats.get(0));
                        t.setSeat_num(NumSeats.get(0));
                        NumSeats.set(0,NumSeats.get(0)+1);
                        t.set_class("Economy");
                    }
                    else if(_class==2){
                        totalPrice+=PricesSeats.get(1);
                        t.setPrice(PricesSeats.get(1));
                        t.setSeat_num(NumSeats.get(1));
                        NumSeats.set(1,NumSeats.get(1)+1);
                        t.set_class("VIP");
                    }
                    else if(_class==3){
                        totalPrice+=PricesSeats.get(2);
                        t.setPrice(PricesSeats.get(2));
                        t.setSeat_num(NumSeats.get(2));
                        NumSeats.set(2,NumSeats.get(2)+1);
                        t.set_class("Business");
                    }
                    t.setGate_Num(F.GetGAte_Num(Flight_id));
                    Tickets.add(t);
                }
                F.UpdateSeats(Flight_id,NumSeats.get(0),NumSeats.get(1),NumSeats.get(2));
                Booking b=new Booking(SSN,num_Tickets,totalPrice);
                myBooking_id=b.getBooking_id();
                b.InsertBooking();

                for(int i=0;i<num_Tickets;i++){
                    Tickets.get(i).setBooking_id(myBooking_id);
                    Tickets.get(i).setFlight_id(Flight_id);
                    Tickets.get(i).setGate_Num(F.GetGAte_Num(Flight_id));
                    Tickets.get(i).InsertTicket();
                }
                F.INcreReservedSeats(Flight_id,num_Tickets);
                int methodpay;String method="";
                while(true){
                    System.out.println("please enter the payment method\n" +
                                       "(1) Paypal\n" +
                                       "(2) Fawry\n" +
                                       "(3) cash\n" +
                                       "(4) master/visa (credit/debit) card");
                    methodpay=in.nextInt();
                    if(methodpay==1){
                        method="Paypal";
                        break;
                    }
                    else if(methodpay==2){
                        method="Fawry";
                        break;
                    }
                    else if(methodpay==3){
                        method="cash";
                        break;
                    }
                    else if(methodpay==4){
                        method="card";
                        break;
                    }
                    else{
                        System.out.println("please enter valid choice");
                    }
                }
                Payment p=new Payment(method,b.getBooking_id(),b.getBooking_Date());
                p.InsertPayment();
            }
            else if(choice==4){
                int Ticket_id,Flight_id;
                Ticket t=new Ticket();
                Flight F=new Flight();
                while(true){
                    System.out.println("Please enter your Ticket id");Ticket_id=in.nextInt();
                    if(t.ISExist(Ticket_id)){
                        if(t.TicketOfPass(SSN,Ticket_id)){
                            break;
                        }
                        else{
                            System.out.println("Entered Ticket is not related to your SSN");
                        }
                    }
                    else{
                        System.out.println("Entered id not valid");
                    }
                }
                Flight_id=t.GetFlight_id(Ticket_id);
                ArrayList<Float>PricesSeats=F.GetPrices(Flight_id);
                ArrayList<Integer>NumSeats=F.GetSeatnum(Flight_id);
                while(true){
                    System.out.println("please enter Flight class" +
                            "\n\t(1) Economy price " +PricesSeats.get(0)+
                            "\n\t(2) VIP price " +PricesSeats.get(1)+
                            "\n\t(3) business price "+PricesSeats.get(2));
                    int op=in.nextInt();
                    if(op==1){
                        t.setPrice(PricesSeats.get(0));
                        t.setSeat_num(NumSeats.get(0));
                        NumSeats.set(0,NumSeats.get(0)+1);
                        t.set_class("Economy");
                        break;
                    }
                    else if(op==2){
                        t.setPrice(PricesSeats.get(1));
                        t.setSeat_num(NumSeats.get(1));
                        NumSeats.set(1,NumSeats.get(1)+1);
                        t.set_class("VIP");
                        break;
                    }
                    else if(op==3){
                        t.setPrice(PricesSeats.get(2));
                        t.setSeat_num(NumSeats.get(2));
                        NumSeats.set(2,NumSeats.get(2)+1);
                        t.set_class("Business");
                        break;
                    }
                    else{
                        System.out.println("please enter valid option");
                    }
                }
                F.UpdateSeats(Flight_id,NumSeats.get(0),NumSeats.get(1),NumSeats.get(2));
                F.INcreReservedSeats(Flight_id,1);
                t.UpdateClass(Ticket_id,t.get_class(),t.getPrice(),t.GetPrice(Ticket_id),t.getSeat_num());
            }
            else if(choice==5){
                Display();
                break;
            }
            else{
                System.out.println("please enter valid choice");
            }
        }
    }
}
