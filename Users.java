import java.util.*;
import java.sql.*;
interface user_authentication
{
    public void log_in() throws Exception;

    public void sign_up () throws Exception;
}

public class Users implements user_authentication
{
    public Users() throws Exception {
         String DriverName = "com.mysql.cj.jdbc.Driver";
         Class.forName(DriverName);
        
    }
    Scanner sc = new Scanner(System.in);
    String f_name;
    String l_name;
    int age;
    String email;
    String mobile_no;
    String user_id;
    String password;
    public Users( String f_name, String l_name, int age, String email, String mobile_no, String user_id,
            String password) {
       
        this.f_name = f_name;
        this.l_name = l_name;
        this.age = age;
        this.email = email;
        this.mobile_no = mobile_no;
        this.user_id = user_id;
        this.password = password;
    }
    public String getF_name() {
        return f_name;
    }
    public void setF_name(String f_name) {
        this.f_name = f_name;
    }
    public String getL_name() {
        return l_name;
    }
    public void setL_name(String l_name) {
        this.l_name = l_name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getMobile_no() {
        return mobile_no;
    }
    public void setMobile_no(String mobile_no) {
        this.mobile_no = mobile_no;
    }
    public String getUser_id() {
        return user_id;
    }
    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    @Override
    public String toString() {
        return "Users \n"+"f_name=" + f_name + "\nl_name=" + l_name + "\nage=" + age + "\nemail=" + email
                + "\nmobile_no=" + mobile_no + "\nuser_id=" + user_id + "\npassword=" + password;
    }
    public void log_in() throws Exception {
        String dburl = "jdbc:mysql://localhost:3306/Finance";
        String dbusername = "root";
        String dbpassword = "";
        Connection con = DriverManager.getConnection(dburl, dbusername, dbpassword);
        int maxAttempts = 2;
        int attempts = 0;
        String userOrEmail;
        String password;
        while (true) {
            System.out.print("Enter User ID or Email: ");
            userOrEmail = sc.next();
            System.out.print("Enter password: ");
            password = sc.next();
            String query;
    
            if (isValidEmail(userOrEmail)) {
                query = "SELECT * FROM Users WHERE email = ? AND password = ?";
            } else {
                query = "SELECT * FROM Users WHERE user_id = ? AND password = ?";
            }
    
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, userOrEmail);
            preparedStatement.setString(2, password);
            ResultSet rs = preparedStatement.executeQuery();
    
            if (rs.next()) {
                System.out.println("Login successfully");
                System.out.println();
                System.out.println("_________________________________");
                System.out.println("                                 ");
                System.out.println("        Welcome " + rs.getString("f_name") + "...!!  ");
                System.out.println("_________________________________");
    
                Transactions transactions = new Transactions(rs.getString("user_id"));
                transactions.displayDashboard();
                break;
            } else {
                System.out.println("Login failed. Please try again.");
                attempts++;
                if (maxAttempts < attempts) {
                    System.out.println("Oh! You have made too many attempts. Try again later....!!");
                    System.exit(0);
                }
            }
        }
    }

    public void sign_up () throws Exception
    {       
         String dburl = "jdbc:mysql://localhost:3306/Finance"; 
        String dbusername = "root"; 
        String dbpassword = ""; 
        Connection con = DriverManager.getConnection(dburl, dbusername, dbpassword);
                System.out.print("Enter your First name : ");
                String f_name = sc.next();
             
                System.out.print("Enter your last name : ");
                String l_name = sc.next();
                sc.nextLine();
                System.out.print("Enter your age (above 18) : ");
                int age ;
               
                while(true)
                {
                      try{
                             age = sc.nextInt();
                             if (age <=18) {
                                System.out.println("You are not eligible to use this app");
                                System.out.println("Enter 18+ age");    
                            }
                            else
                            {
                                break;
                            }
                               
                      }
                      catch(InputMismatchException e )
                      {
                        throw new InvalidChoiceException("Custom Exception : Invalid input, you can not add character");
                       
                      }
                }
                 System.out.print("Enter your Email_id : ");
                String email;

                while (true) {
                       
                        email = sc.next();
                        if (!email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}"))
                        {
                            System.out.println("Invalid email address");
                            System.out.println("Enter a valid email address");
                        } 
                        else
                        {
                            break;
                        }
                }
               
             String mobile_no = "";
             while (true)
             {
                     System.out.print("Enter your mobile number : ");
                        mobile_no = sc.next();

                        if (mobile_no.length() == 10)
                        {
                            boolean valid = true;
                            for (int i = 0; i < mobile_no.length(); i++) 
                            {
                                char digit = mobile_no.charAt(i);
                                if (!Character.isDigit(digit)) {
                                    valid = false;
                                    System.out.println("Invalid number. Enter a valid 10-digit number.");
                                    break; 
                            }}
                            if (valid) {
                            break;
                         }
                         }
                        else 
                        {
                            System.out.println("Invalid number. Enter a valid 10-digit number.");
                        }
                    }
               
                String user_id = generateUserID(f_name,l_name);
                System.out.println("Your user id is : "+user_id);
                String password;
                    while (true) {
                        System.out.print("Enter password (Password must be 8 characters and contain a special character) : ");
                         password = sc.next();
            
                        if (password.length() != 8 || !password.matches(".*[^a-zA-Z0-9].*")) {
                            System.out.println("Invalid password");
                            System.out.println("Password must be 8 characters and contain at least one special character");
                        } else {
                            break;
                        }
                    }
               
                String sql1 = "insert into Users values (?,?,?,?,?,?,?)";
                PreparedStatement pst = con.prepareStatement(sql1);
              
                pst.setString(1,user_id);
                pst.setString(2,f_name);
                pst.setString(3,l_name);
                pst.setInt(4,age);
                pst.setString(5,email);
                pst.setString(6,mobile_no);
                pst.setString(7, password);
                int r = pst.executeUpdate();
                System.out.println();
                System.out.println("SIGN UP SUCCESSFULLY !!!!");
                System.out.println();

                System.out.println();
                System.out.println("_________________________________");
                System.out.println("                                 ");
                System.out.println(  "        Welcome "+f_name+"...!!  ");
                System.out.println("_________________________________"); 
               
                System.out.println();
               
            Transactions transactions = new Transactions(user_id);
                transactions.setupInitialBalance();
                transactions.displayDashboard();
                pst.close();
                con.close();
    }
   public static String generateUserID(String firstName, String lastName) {
      
        int randomNum = (int) (Math.random() * 9000) + 1000;
    
       
        String userID = firstName.substring(0, 2).toLowerCase() + lastName.substring(0, 2).toLowerCase() + randomNum;
    
        return userID;
    }
   
    public static boolean isValidEmail(String input) {
   
    return input.contains("@") && input.contains(".");
}

    }
    

