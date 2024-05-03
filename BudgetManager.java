import java.sql.*;
import java.util.*;

public class BudgetManager {

    private Connection con;

    public BudgetManager(String dbUrl, String dbUser, String dbPassword) throws SQLException {
        this.con = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
    }
     Scanner sc = new Scanner(System.in);
  
    public void close() throws SQLException {
        con.close();
    }

       public void setbudgets(String userId) throws Exception {
      
            
            String budgetName = "";
           
            String period = "";
           
            double amount = 0;
            
            String category ="";
            System.out.print("Add budget name : "); 
            budgetName = sc.next();
            
            System.out.print("Add period: ");
            period = sc.next();
            
            try {
                System.out.print("Enter amount: ");
                amount = sc.nextDouble();
            } catch (InputMismatchException e) {
                sc.next(); 
                System.out.println("Custom Exception: Invalid input for amount. Please enter a valid number.");
                return; 
            }

           
           
    
            double totalSpent = getTotalSpent(con, userId,category);
    
            String insertQuery = "INSERT INTO budgets (user_id, budget_name, period, amount, category) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement insertStatement = con.prepareStatement(insertQuery);
    
            insertStatement.setString(1, userId);
            insertStatement.setString(2, budgetName);
            insertStatement.setString(3, period);
            insertStatement.setDouble(4, amount);
            insertStatement.setString(5, category);
    
            int rowCount = insertStatement.executeUpdate();
    
            if (rowCount > 0) {
                System.out.println("Budget '" + budgetName + "' added successfully.");
                if (totalSpent >= amount) {
                    sendNotification("Budget limit reached for '" + budgetName + "'. Total spent: " + totalSpent);
                }
            } else {
                System.out.println("Failed to add the budget. Please try again.");
            }
        }
    
    public void editBudget() throws Exception{
        Scanner sc = new Scanner(System.in);
      
           
            System.out.print("Enter user ID : ");
            String userId = sc.next(); 
    
            System.out.print("Enter the budget name you want to edit : ");
            String budgetNameToEdit = sc.next();
    
            
          
String selectQuery = "SELECT * FROM budgets WHERE user_id = ? AND budget_name = ?";
PreparedStatement selectStatement = con.prepareStatement(selectQuery);
selectStatement.setString(1, userId);
selectStatement.setString(2, budgetNameToEdit);
ResultSet resultSet = selectStatement.executeQuery();

if (resultSet.next()) {
    
    double currentAmount = resultSet.getDouble("amount");
    System.out.println("Current Budget Amount: " + currentAmount);

    String currentBudgetName = resultSet.getString("budget_name");
    System.out.println("Current Budget Name: " + currentBudgetName);

    
    System.out.println("Choose what to update:");
    System.out.println("1. Update Budget Amount");
    System.out.println("2. Update Budget Name");
    System.out.println("3. Cancel");
    
    int choice = sc.nextInt();
    sc.nextLine(); 
    
    switch (choice) {
        case 1:
            
            System.out.println("Enter new amount for the budget (or press Enter to keep current):");
            String amountInput = sc.nextLine();
            double newAmount = (amountInput.isEmpty()) ? currentAmount : Double.parseDouble(amountInput);
            String updateAmountQuery = "UPDATE budgets SET amount = ? WHERE user_id = ? AND budget_name = ?";
            PreparedStatement updateAmountStatement = con.prepareStatement(updateAmountQuery);
            updateAmountStatement.setDouble(1, newAmount);
            updateAmountStatement.setString(2, userId);
            updateAmountStatement.setString(3, budgetNameToEdit);
            
            int rowCount = updateAmountStatement.executeUpdate();
            
            if (rowCount > 0) {
                System.out.println("Budget amount updated successfully.");
            } else {
                System.out.println("Failed to update budget amount. Please try again.");
            }
            break;
        case 2:
            
            System.out.println("Enter new budget name (or press Enter to keep current):");
            String nameInput = sc.nextLine();
            String newBudgetName = (nameInput.isEmpty()) ? currentBudgetName : nameInput;
            String updateNameQuery = "UPDATE budgets SET budget_name = ? WHERE user_id = ? AND budget_name = ?";
            PreparedStatement updateNameStatement = con.prepareStatement(updateNameQuery);
            updateNameStatement.setString(1, newBudgetName);
            updateNameStatement.setString(2, userId);
            updateNameStatement.setString(3, budgetNameToEdit);
            
            int nameRowCount = updateNameStatement.executeUpdate();
            
            if (nameRowCount > 0) {
                System.out.println("Budget name updated successfully.");
            } else {
                System.out.println("Failed to update budget name. Please try again.");
            }
            break;
        case 3:
            System.out.println("Update canceled.");
            break;
        default:
            System.out.println("Invalid choice. Update canceled.");
    }
} else {
    System.out.println("Budget '" + budgetNameToEdit + "' not found for User ID: " + userId);
}



        }
    
        public void deleteBudget() throws Exception {
            Scanner sc = new Scanner(System.in);
        
            System.out.print("Enter user ID: ");
            String userId = sc.next();
        
            System.out.print("Enter the budget name you want to delete: ");
            String budgetNameToDelete = sc.next();
        
            String deleteQuery = "DELETE FROM budgets WHERE user_id = ? AND budget_name = ?";
            PreparedStatement deleteStatement = con.prepareStatement(deleteQuery);
            deleteStatement.setString(1, userId);
            deleteStatement.setString(2, budgetNameToDelete);
        
            int rowCount = deleteStatement.executeUpdate();
        
            if (rowCount > 0) {
                System.out.println("Budget '" + budgetNameToDelete + "' deleted successfully.");
            } else {
                System.out.println("Failed to delete the budget. Please check the user ID and budget name.");
            }
        }
        
    
    
    
    public void showBudgets() throws Exception {
        Scanner sc = new Scanner(System.in);
    
            System.out.print("Enter user ID : ");
            String userId = sc.next(); 
            System.out.println();
    
            String selectQuery = "SELECT * FROM budgets WHERE user_id = ?";
        PreparedStatement selectStatement = con.prepareStatement(selectQuery);

        selectStatement.setString(1, userId);

        ResultSet resultSet = selectStatement.executeQuery();

        if (resultSet.isBeforeFirst()) {
            System.out.println("Budgets for User ID: " + userId);
            System.out.println("----------------------------------");

            while (resultSet.next()) {
                String budgetName = resultSet.getString("budget_name");
                String period = resultSet.getString("period");
                double budgetAmount = resultSet.getDouble("amount");
                String category = resultSet.getString("category");

                System.out.println("Budget Name: " + budgetName);
                System.out.println("Period: " + period);
                System.out.println("Budget Amount: " + budgetAmount);
                System.out.println("Category: " + category);
                System.out.println();
            }
        } else {
            System.out.println("No budgets found for User ID: " + userId);
        }
    } 
    private double getTotalSpent(Connection con, String userId,String type) throws SQLException {
        String query = "SELECT SUM(amount) FROM Transactions WHERE user_id = ? and category=?";
        PreparedStatement statement = con.prepareStatement(query);
        statement.setString(1, userId);
        statement.setString(2,type);
        ResultSet resultSet = statement.executeQuery();
    
        if (resultSet.next()) {
            return resultSet.getDouble(1);
        }
        return 0.0;
    }
    private void sendNotification(String message) {
        
        System.out.println("Notification: " + message);
    }
} 
 

