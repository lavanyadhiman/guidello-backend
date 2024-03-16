package guidello;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Guides {
    private Connection connection;

    public Guides(Connection connection){
        this.connection = connection;
    }

    public void viewGuides(){
        String query = "select * from Guides";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("Users: ");
            System.out.println("+----+-----------------+-----------------------+--------------+------------+----------------+-----------------+-------------------+-------------+");
            System.out.println("|  ID | NAME           | EMAIL                 | PASSWORD     | GENDER     | P_NO           | NATIONALITY     | LANGUAGES         | V_ID        |");
            System.out.println("+----+-----------------+-----------------------+--------------+------------+----------------+-----------------+-------------------+-------------+");
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("NAME");
                String email = resultSet.getString("EMAIL");
                String password= resultSet.getString("PASSWORD");
                String gender = resultSet.getString("GENDER");
                String p_no = resultSet.getString("P_NO");
                String nationality = resultSet.getString("NATIONALITY");
                String languages = resultSet.getString("LANGUAGES");
                String v_id = resultSet.getString("V_ID");
                System.out.printf("| %-10s | %-18s | %-18s | %-18s | %-18s | %-18s | %-18s | %-18s |%-18s |\n", id, name, email, password, gender, p_no, nationality , languages, v_id);
                System.out.println("+----+-------------+-------------------+----------+--------+------------+-------------+---------------+-------------+");
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public boolean getGuidesById(int id){
        String query = "SELECT * FROM Guides WHERE id = ?";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return true;
            }else{
                return false;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
