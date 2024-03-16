package guidello;

import java.sql.*;
import java.util.Scanner;
import javax.servlet.annotation.WebServlet;
public class Guidello {
    private static final String url = "jdbc:mysql://localhost:3306/guidello";
    private static final String username = "root";
    private static final String password = "lavanya123";

    public static void main(String[] args) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        Scanner scanner = new Scanner(System.in);
        try{
            Connection connection = DriverManager.getConnection(url, username, password);
            Users users = new Users(connection, scanner);
            Guides guides = new Guides(connection);
            while(true){
                System.out.println("Guidello Database");
                System.out.println("1. Add User");
                System.out.println("2. View users");
                System.out.println("3. View guides");
                System.out.println("4. Book trips");
                System.out.println("5. View Trips");
                System.out.println("6. Exit");
                System.out.println("Enter your choice: ");
                int choice = scanner.nextInt();

                switch(choice){
                    case 1:
                        users.addUsers();
                        System.out.println();
                        break;
                    case 2:
                        users.viewUsers();
                        System.out.println();
                        break;
                    case 3:
                        guides.viewGuides();
                        System.out.println();
                        break;
                    case 4:
                        booktrips(users, guides, connection, scanner);
                        System.out.println();
                        break;
                    case 5:
                        viewTrips(users, guides, connection, scanner);
                        System.out.println();
                        break;
                    case 6:
                        System.out.println("THANK YOU FOR USING GUIDELLO!");
                        return;
                    default:
                        System.out.println("Enter valid choice!!!");
                        break;
                }

            }

        }catch (SQLException e){
            e.printStackTrace();
        }
    }


    public static void booktrips(Users users, Guides guides, Connection connection, Scanner scanner){
        System.out.print("Enter User Id: ");
        int user_Id = scanner.nextInt();
        System.out.print("Enter Guide Id: ");
        int guide_Id = scanner.nextInt();
        System.out.print("Enter trip date (YYYY-MM-DD): ");
        String trip_Date = scanner.next();
        System.out.print("Enter city");
        String city=scanner.next();
        if(users.getUsersById(user_Id) && guides.getGuidesById(guide_Id)){
            
                String tripsQuery = "INSERT INTO trips(USER_ID, GUIDE_ID, TRIP_DATE, CITY) VALUES(?, ?, ?,?)";
                try {
                    PreparedStatement preparedStatement = connection.prepareStatement(tripsQuery);
                    preparedStatement.setInt(1, user_Id);
                    preparedStatement.setInt(2, guide_Id);
                    preparedStatement.setString(3, trip_Date);
                    preparedStatement.setString(4, city);
                    int rowsAffected = preparedStatement.executeUpdate();
                    if(rowsAffected>0){
                        System.out.println("trips Booked!");
                    }else{
                        System.out.println("Failed to Book trips!");
                    }
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }/*else{
                System.out.println("guides not available on this date!!");
            }
        }if(checkguidesAvailability(guide_Id, trip_Date, city, connection))*/else{
            System.out.println("Either guides or users doesn't exist!!!");
        }
    }
    public static void viewTrips(Users users, Guides guides, Connection connection, Scanner scanner){
        String query = "select * from Trips";
        try{
        	
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("Trips: ");
            System.out.println("+----+-------------+-------------------+-----------+--------+");
            System.out.println("|  ID | USER_ID    | GUIDE_ID          | TRIP_DATE  | CITY  |");
            System.out.println("+----+-------------+-------------------+-----------+--------+");
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String user_id = resultSet.getString("USER_ID");
                String guide_id = resultSet.getString("GUIDE_ID");
                String trip_date= resultSet.getString("TRIP_DATE");
                String city = resultSet.getString("CITY");
                
                
                System.out.printf("| %-10s | %-18s | %-18s | %-18s | %-18s |  \n", id, user_id, guide_id, trip_date,city);
                System.out.println("+----+-------------+-------------------+-----------+--------+");
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    /*public static boolean checkguidesAvailability(int guidesId, String tripsDate, String city, Connection connection){
        String query = "SELECT COUNT(*) FROM trips WHERE guides_id = ? AND trips_date = ? AND city=?";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, guidesId);
            preparedStatement.setString(2, tripsDate);
            preparedStatement.setString(3, city);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                int count = resultSet.getInt(1);
                if(count==0){
                    return true;
                }else{
                    return false;
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }*/
}
