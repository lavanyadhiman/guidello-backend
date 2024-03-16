package guidello;

import java.sql.*;
import java.util.Scanner;

public class Users {
    private Connection connection;
    private Scanner scanner;
    private String name;
    private String email;
    private String password;
    private String gender;
    private String p_no;
    private String nationality;
    private String languages;
    private String v_id;

    public Users(Connection connection, Scanner scanner){
        this.connection = connection;
        this.scanner = scanner;
    }

    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getP_no() {
		return p_no;
	}

	public void setP_no(String p_no) {
		this.p_no = p_no;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getLanguages() {
		return languages;
	}

	public void setLanguages(String languages) {
		this.languages = languages;
	}

	public String getV_id() {
		return v_id;
	}

	public void setV_id(String v_id) {
		this.v_id = v_id;
	}

	public void addUsers(){
    	System.out.println("Enter User's Name: ");
    	name = scanner.nextLine();
    	scanner.nextLine();  // Consume newline left-over

    	System.out.println("Enter User's Email: ");
    	 email = scanner.nextLine();
    	scanner.nextLine();  // Consume newline left-over

        System.out.print("Enter User's Password: ");
        password = scanner.nextLine();
        System.out.print("Enter User's Gender: ");
         gender = scanner.nextLine();
        System.out.print("Enter User's Ph_No: ");
        p_no = scanner.nextLine();
        System.out.print("Enter User's Nationality: ");
         nationality = scanner.nextLine();
        System.out.print("Enter User's languages: ");
         languages = scanner.nextLine();
        System.out.print("Enter User's v_id: ");
         v_id = scanner.nextLine();

        try{
            String query = "INSERT INTO Users(name, email, password, gender, p_no, nationality, languages, v_id) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, password);
            preparedStatement.setString(4, gender);
            preparedStatement.setString(5, p_no);
            preparedStatement.setString(6, nationality);
            preparedStatement.setString(7, languages);
            preparedStatement.setString(8, v_id);
            int affectedRows = preparedStatement.executeUpdate();
            if(affectedRows>0){
                System.out.println("Users Added Successfully!!");
            }else{
                System.out.println("Failed to add Users!!");
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void viewUsers(){
        String query = "select * from Users";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("Users: ");
            System.out.println("+----+-------------+-------------------+----------+--------+------------+-------------+---------------+-------------+");
            System.out.println("|  ID | NAME        | EMAIL             | PASSWORD | GENDER | P_NO       | NATIONALITY | LANGUAGES     | V_ID        |");
            System.out.println("+----+-------------+-------------------+----------+--------+------------+-------------+---------------+-------------+i");
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
                System.out.printf("| %-3s | %-10s | %-10s | %-8s | %-8s | %-13s | %-13s | %-16s |%-16s |\n", id, name, email, password, gender, p_no, nationality , languages, v_id);
                System.out.println("+----+-------------+-------------------+----------+--------+------------+-------------+---------------+-------------+");
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public boolean getUsersById(int id){
        String query = "SELECT * FROM Users WHERE id = ?";
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
