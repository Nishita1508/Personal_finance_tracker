import java.util.*;
import java.io.*;
import java.sql.*;

public class Transactions{
    Scanner sc = new Scanner(System.in);
    Transactions()
    {
        
    }
       String userId;
       private static int transactionCounter = 1;
       private static final String COUNTER_FILE = "transaction_counter.txt";
   
       static {
           try {
               File file = new File(COUNTER_FILE);
               if (file.exists()) {
                   BufferedReader reader = new BufferedReader(new FileReader(file));
                   String counterValue = reader.readLine();
                   reader.close();
                   transactionCounter = Integer.parseInt(counterValue);
               }
           } catch (IOException e) {
               e.printStackTrace();
           }
       }
       
   
       public static String generateTransactionID(String userID) {
           String transactionID = userID + "_" + transactionCounter;
           transactionCounter++;

           try {
               BufferedWriter writer = new BufferedWriter(new FileWriter(COUNTER_FILE));
               writer.write(Integer.toString(transactionCounter));
               writer.close();
           } catch (IOException e) {
               e.printStackTrace();
           }
   
           return transactionID;
       } 
    
    Transactions(String user_id)
    {
        this.userId = user_id;   
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getTransactionID() {
        return transactionID;
    }
    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public int getMonth() {
        return month;
    }
    public void setMonth(int month) {
        this.month = month;
    }
    public String getDate() {
        return day+"-"+month+"-"+year;
    }
    public void setDay(int day) {
        this.day = day;
    }
    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    String transactionID;
    String description;
    String type;
    String category;
    int month;
    int day;
    int year;
    double amount;
     double initialBalance;
    public Transactions(String userId, String transactionID, String description, String type, String category, int month,
            int day, int year, double amount) {
        this.userId = userId;
        this.transactionID = transactionID;
        this.description = description;
        this.type = type;
        this.category = category;
        this.month = month;
        this.day = day;
        this.year = year;
        this.amount = amount;
    }
    @Override
    public String toString() {
        return "Transaction \n userId=" + userId + "\n transactionID=" + transactionID + "\n description=" + description
                + "\n type=" + type + "\n category=" + category + "\n date = "+day+"-"+month+"-"+year
                + "\n amount=" + amount ;
    }
    void displayDashboard () throws Exception
    {
        while(true)
        {
        System.out.println();
         System.out.println("How can we assist you on your financial journey today?");
         System.out.println();

         System.out.println(" ===============================");
         System.out.println("|                               |");
         System.out.println("| 1. Manage Transactions        |");
         System.out.println("| 2. Budget Overview            |");
         System.out.println("| 3. Financial Goals            |");
         System.out.println("| 4. My Profile                 |");
         System.out.println("| 5. Exit                       |");
         System.out.println("|                               |");
         System.out.println(" ===============================");
         System.out.print("Select an option: ");
         int mainChoice ;
        try
        {
              mainChoice = sc.nextInt();
        }
        catch (InputMismatchException e)
        {
            sc.next();
             System.out.println("Custom Exception : Invalid input, you can not add character, Enter a valid choice");
            continue;
        }

         switch (mainChoice) {
             case 1:
                 handleTransactionsMenu();
                 break;
             case 2:
                 handleBudgetsMenu();
                 break;
             case 3:
                 handleGoalsMenu();
                 break;
             case 4:
                 handleProfileMenu();
                 break;
             case 5:
                 System.out.println("Exiting the application. Goodbye!");
                 System.exit(0);
             default:
                 System.out.println("Invalid choice. Please try again.");
         }
     }
    }
    void handleTransactionsMenu() throws Exception
    {
        while (true) {
            System.out.println();
            System.out.println("=========== Transactions Menu ==========");
            System.out.println("                                        ");
            System.out.println("1.  Show Balance");
            System.out.println("2.  Add Transaction");
            System.out.println("3.  Delete Transaction");
            System.out.println("4.  Update Transaction");
            System.out.println("5.  Search Transaction");
            System.out.println("6.  Display All Transactions");
            System.out.println("7.  Sort By Date");
            System.out.println("8.  Display By Type");
            System.out.println("9.  Display By Category");
            System.out.println("10. Export Transactions By Date Range"); 
            System.out.println("11. Calculate Total Expenses For Month"); 
            System.out.println("0.  Back to Main Menu");
            System.out.println("                                      ");
            System.out.println("=======================================");

            System.out.print("Select an option: ");
           
            int transactionChoice = 0;
        try
        {
              transactionChoice = sc.nextInt();
        }
        catch (InputMismatchException e)
        {
           sc.next();
           System.out.println("Custom Exception : Invalid input, you can not add character, Enter a valid choice");
            continue;
        }

            switch (transactionChoice) {
                case 1:
                   {
                        showBalance();
                   }
                    break;
                case 2:
                    {
                        addTransactions();
                    }
                    break;
                case 3:
                    {
                        deleteTransaction();
                    }
                    break;
                case 4:
                    {
                        updateTransaction();
                    }
                    break;
                case 5:
                   {
                      searchTransaction(); 
                   }
                    break;
                case 6:
                    {
                        displayAllTransactions();
                    }
                    break;
                case 7:
                   {
                       SortByDate();
                   }
                    break;
                case 8:
                    {
                       displayByType();
                    }
                    break;
                case 9:
                    {
                        displayByCategory();
                    }
                    break;
                case 10:
                    { 
                        ExportTransactionsByDateRange();
                    }
                    break;
                case 11 :
                    {
                        totalExpense();
                    }
                    break;
                case 0 :
                {
                    return;
                }

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

     void handleBudgetsMenu() throws Exception {
      BudgetManager b = new BudgetManager("jdbc:mysql://localhost:3306/Finance","root","");
        while (true) {
            System.out.println();
            System.out.println("===== Budgets Menu =====");
            System.out.println("                        ");
            System.out.println("1. View Budgets");
            System.out.println("2. Set Budget");
            System.out.println("3. Edit Budget");
            System.out.println("4. Delete Budget");
            System.out.println("0. Back to Main Menu");
            System.out.println("                        ");
            System.out.println("========================");
            System.out.println();
            System.out.print("Select an option: ");
           
            int budgetChoice = 0;
        try
        {
            budgetChoice = sc.nextInt();
        }
        catch (InputMismatchException e)
        {
           sc.next();
            System.out.println("Custom Exception : Invalid input, you can not add character, Enter a valid choice");
            continue;
        }

    
            switch (budgetChoice) {
                case 1:
               {
                    b.showBudgets();
               }
                    break;
                case 2:  
                {
                    b.setbudgets(userId);
                }
                    break;
                case 3:     
                {
                    b.editBudget();
                }
                    break;
                case 4:
                {
                    b.deleteBudget();
                }
                    break;
                case 0:
                    return; 
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    
    void handleGoalsMenu() throws Exception
    {
           GoalManager g = new GoalManager(userId);
           
                while (true) {
                    System.out.println();
                    System.out.println("===== Goals Menu =====");
                    System.out.println("1. View Goals");
                    System.out.println("2. Set New Goal");
                    System.out.println("3. Update Goal");
                    System.out.println("0. Back to Main Menu");
                    System.out.println("======================");
                    System.out.println();
                    System.out.print("Select an option: ");
                    
                    int goalChoice = 0;
        try
        {
             goalChoice = sc.nextInt();
        }
        catch (InputMismatchException e)
        {
           sc.next();
            System.out.println("Custom Exception : Invalid input, you can not add character, Enter a valid choice");
            continue;
        }

                    switch (goalChoice) {
                        case 1:
                        {
                            g.viewGoals();

                        }
                        break;
                        case 2:
                        {
                           g.setNewGoal();
                        }
                            break;
                        case 3:
                    {
                            g.updateGoal();
                    }
                            break;
                            
                        case 0:
                        
                            return; // Return to the main menu
                        default:
                            System.out.println("Invalid choice. Please try again.");
                    }
                }
    }
            
        
        
        void handleProfileMenu() throws Exception
        {
            viewProfile();
        }
    
           
       
    public void showBalance() throws Exception {
        String dburl = "jdbc:mysql://localhost:3306/Finance";
        String dbuser = "root";
        String dbpassword = "";
        
        try (Connection con = DriverManager.getConnection(dburl, dbuser, dbpassword)) {
            String sql = "SELECT balance FROM balance WHERE user_id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, userId);
            
            ResultSet rs = pst.executeQuery();
            
            if (rs.next()) {
                double userBalance = rs.getDouble("balance");
                System.out.println();
                System.out.println("Your Current balance is: " + userBalance);
                System.out.println();
            } else {
                System.out.println("User balance not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void setupInitialBalance()throws Exception {
        Scanner sc = new Scanner(System.in);

       
        System.out.print("Please enter your initial balance: ");
        initialBalance = sc.nextDouble();
        if (initialBalance < 0) {
            System.out.println("Invalid initial balance. Please enter a non-negative amount.");
            setupInitialBalance(); 
        } else {
           
            String sp = "Insert into balance values (?,?)";
            String dburl = "jdbc:mysql://localhost:3306/Finance";
            String dbuser = "root";
            String dbpassword = "";
            Connection con = DriverManager.getConnection(dburl, dbuser, dbpassword);
             PreparedStatement pst = con.prepareStatement(sp);
            pst.setString(1,userId);
            pst.setDouble(2,initialBalance);
            int r = pst.executeUpdate();
            if(r>0)
            {
                  System.out.println("Initial balance set to " + initialBalance);
                    System.out.println();
                }
                  else
                  {
                    System.out.println("Balance not set up");
                  }
            }
        }
        public void addTransactions() throws Exception {
            try {
                String dburl = "jdbc:mysql://localhost:3306/Finance";
                String dbuser = "root";
                String dbpassword = "";
    
                String[] ECATEGORIES = { "Food", "Transportation", "Housing", "Entertainment", "Shopping", "Communication", "Investments",  "Other" };
                String[] ICATEGORIES = { "Checks,coupons", "Gifts", "Interests,dividends", "lending,renting", "Freelance", "Refunds", "Sale", "Salary", "Other" };
                
                int day;
                int month;
                int year;
                String type = "";
                String selectedCategory = "";
                
                System.out.println("Enter Amount: ");
                double amount = 0;
                try {
                    amount = sc.nextDouble();
                } catch (InputMismatchException e) {
                    sc.next();
                    System.out.println("Custom Exception: Invalid input, you cannot add characters.");
                    return; // Exit the program or handle the error as needed
                }
                
                while (true) {
                    try {
                        System.out.print("Enter day: ");
                        day = sc.nextInt();
                        System.out.print("Enter Month: ");
                        month = sc.nextInt();
                        System.out.print("Enter year: ");
                        year = sc.nextInt();
                        sc.nextLine();
                        boolean check = isValidDate(year, month, day);
                        if (check) {
                            break;
                        } else {
                            System.out.println("Your date format is wrong");
                        }
                    } catch (InputMismatchException e) {
                        sc.next();
                        System.out.println("Custom Exception: Invalid input, you can not add characters");
                    }
                }
                
                System.out.println("Enter type ");
                System.out.println("1. income");
                System.out.println("2. expense");
                int choice = sc.nextInt();
                if (choice == 1) {
                    type = "Income";
                } else if (choice == 2) {
                    type = "Expense";
                } else {
                    System.out.println("Invalid transaction type.");
                    return; 
                }
                
                if (type.equalsIgnoreCase("Income")) {
                    System.out.println("Enter category : ");
                    for (int i = 0; i < ICATEGORIES.length; i++) {
                        System.out.println((i + 1) + ". " + ICATEGORIES[i]);
                    }
                    int categoryChoice;
                    do {
                        System.out.print("Enter the number of the category: ");
                        categoryChoice = sc.nextInt();
                        sc.nextLine(); 
                        if (categoryChoice < 1 || categoryChoice > ICATEGORIES.length) {
                            System.out.println("Invalid category choice. Please select a valid category.");
                        }
                    } while (categoryChoice < 1 || categoryChoice > ICATEGORIES.length);
                    selectedCategory = ICATEGORIES[categoryChoice - 1];
                    if (categoryChoice == 9) {
                        System.out.println("Enter new category name");
                        selectedCategory = sc.nextLine();
                    }
                }
                
                if (type.equalsIgnoreCase("Expense")) {
                    System.out.println("Enter category");
                    for (int i = 0; i < ECATEGORIES.length; i++) {
                        System.out.println((i + 1) + ". " + ECATEGORIES[i]);
                    }
                    int categoryChoice;
                    do {
                        System.out.print("Enter the number of the category: ");
                        categoryChoice = sc.nextInt();
                        sc.nextLine(); 
                        if (categoryChoice < 1 || categoryChoice > ECATEGORIES.length) {
                            System.out.println("Invalid category choice. Please select a valid category.");
                        }
                    } while (categoryChoice < 1 || categoryChoice > ECATEGORIES.length);
                    selectedCategory = ECATEGORIES[categoryChoice - 1];
                    if (categoryChoice == 8) {
                        System.out.print("Enter new category name: ");
                        selectedCategory = sc.nextLine();
                    }
                }
                
                System.out.println();
                System.out.print("Enter description: ");
                String description = sc.nextLine();
                String transactionID = generateTransactionID(userId);
                
                try (Connection con = DriverManager.getConnection(dburl, dbuser, dbpassword);
                     PreparedStatement pst = con.prepareStatement("INSERT INTO transactions VALUES(?,?,?,?,?,?,?,?,?)")) {
                    pst.setString(1, userId);
                    pst.setString(2, transactionID);
                    pst.setString(3, type);
                    pst.setString(4, selectedCategory);
                    pst.setString(5, description);
                    pst.setInt(6, day);
                    pst.setInt(7, month);
                    pst.setInt(8, year);
                    pst.setDouble(9, amount);
                    
                    int r = pst.executeUpdate();
                    if (r > 0) {
                        System.out.println();
                        System.out.println("Transaction added successfully");
                        System.out.println();
                    } else {
                        System.out.println("Transaction could not be added");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                sc.next();
                System.out.println("Custom Exception : Enter valid choice cant add character");
            }
        }

    
        public static boolean isValidDate(int year, int month, int day) {
            if (year < 1 || month < 1 || month > 12 || day < 1) {
                return false;
            }
    
            int[] daysInMonth = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
 
            if (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)) {
                daysInMonth[2] = 29;
            }
            if (year >= 1000 && year <= 9999) {
               return day <= daysInMonth[month];
            } else {
                return false;
            }
    
            
        }

    void searchTransaction() throws Exception
    {
                 sc.nextLine();
                 boolean b = false;
                 System.out.println();
                System.out.println("Add transaction id you want to search");
                String transactionId1 = sc.next();
                String dburl = "jdbc:mysql://localhost:3306/Finance";
                String dbuser = "root";
                String dbpassword = "";
                Connection con = DriverManager.getConnection(dburl, dbuser, dbpassword);
                String sql = "select *  from transactions where transaction_id = ? AND user_id = ?";
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setString(1, transactionId1);
                pst.setString(2, userId);
               ResultSet rs = pst.executeQuery();
               while(rs.next())
               {
                        String userId = rs.getString("user_id");
                        String transactionId2 = rs.getString("transaction_id");
                        double amount = rs.getDouble("amount");
                        int day = rs.getInt("day");
                        int month = rs.getInt("month");
                        int year = rs.getInt("year");
                        String type = rs.getString("type");
                        String category = rs.getString("category");
                        String description = rs.getString("description");
    
                       
                        System.out.println();
                        System.out.println("User ID: " + userId);
                        System.out.println("Transaction ID: " + transactionId2);
                        System.out.println("Amount : "+amount);
                        System.out.println("Date : "+day+"-"+month+"-"+year);
                        System.out.println("Type : "+type);
                        System.out.println("Category : "+category);
                        System.out.println("Description : "+description);
                        System.out.println();
                        b = true;
               }
              if(b==false)
               {
                System.out.println("No transaction id found");
                System.out.println();
               }
    }
    void deleteTransaction() throws Exception
    {   
                sc.nextLine();
                System.out.print("Add transaction id you want to delete : ");
                String transactionId = sc.next();
                String dburl = "jdbc:mysql://localhost:3306/Finance";
                String dbuser = "root";
                String dbpassword = "";
                Connection con = DriverManager.getConnection(dburl, dbuser, dbpassword);
                String sql = "Delete from transactions where transaction_id = ? AND user_id = ?";
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setString(1, transactionId);
                pst.setString(2, userId);
                int r = pst.executeUpdate();
                 if(r>0)
                {
                    System.out.println();
                    System.out.println("Transaction deleted Succeesfully");
                    System.out.println(); 
                }
                else
                {
                    System.out.println("Transaction not be deleted");
                }
    }
    public void updateTransaction() throws Exception {
        try {
            sc.nextLine();
            System.out.print("Enter the transaction ID you want to update:");
            String transactionId = sc.next();
    
           
            String dburl = "jdbc:mysql://localhost:3306/Finance";
            String dbuser = "root";
            String dbpassword = "";
            Connection con = DriverManager.getConnection(dburl, dbuser, dbpassword);
    
           
            String selectSql = "SELECT * FROM transactions WHERE transaction_id = ? AND user_id=? ";
            PreparedStatement selectStatement = con.prepareStatement(selectSql);
            selectStatement.setString(1, transactionId);
            selectStatement.setString(2, userId);
            ResultSet rs = selectStatement.executeQuery();
    
            if (rs.next()) {
                double currentAmount = rs.getDouble("amount");
                int currentDay = rs.getInt("day");
                int currentMonth = rs.getInt("month");
                int currentYear = rs.getInt("year");
                String currentType = rs.getString("type");
                String currentCategory = rs.getString("category");
                String currentDescription = rs.getString("description");
                System.out.println();
                System.out.println("Current Transaction Details:");
                System.out.println();
                System.out.println("User ID: " + userId);
                System.out.println("Transaction ID: " + transactionId);
                System.out.println("Amount : " + currentAmount);
                System.out.println("Date : " + currentDay + "-" + currentMonth + "-" + currentYear);
                System.out.println("Type : " + currentType);
                System.out.println("Category : " + currentCategory);
                System.out.println("Description : " + currentDescription);
    
                System.out.println("\nWhat do you want to update?");
               
                System.out.println("1. Amount");
                System.out.println("2. Date");
                System.out.println("3. Type");
                System.out.println("4. Category");
                System.out.println("5. Description");
    
                int choice = sc.nextInt();
    
                
                switch (choice) {
                   
                    case 1:
                   
                    System.out.println("Enter new amount:");
                    double newAmount = sc.nextDouble();
                
                   
                    double amountDifference = newAmount - currentAmount;
                
                    
                    String updateAmountSql = "UPDATE transactions SET amount = ? WHERE transaction_id = ?";
                    PreparedStatement updateAmountStatement = con.prepareStatement(updateAmountSql);
                    updateAmountStatement.setDouble(1, newAmount);
                    updateAmountStatement.setString(2, transactionId);
                    updateAmountStatement.executeUpdate();
                    
                    updateBalance(userId, currentType, amountDifference);
                
                    System.out.println("Amount updated successfully.");
                    break;
                

                
                
                    case 2:
                    int newDay;
                    int newMonth;
                    int newYear;
                             while(true)
                            {
                            System.out.println("Enter date");
                            newDay = sc.nextInt();
                            System.out.println("Enter Month");
                            newMonth  = sc.nextInt();
                            System.out.println("Enter year");
                            newYear = sc.nextInt();
                            boolean check = isValidDate(newYear,newMonth, newDay);
                            if(check)
                            {
                                 break;
                            }
                            else
                            {
                                System.out.println("Your date format is wrong");
                            }
                            }
                       
                        String updateDateSql = "UPDATE transactions SET day = ?, month = ?, year = ? WHERE transaction_id = ?";
                        PreparedStatement updateDateStatement = con.prepareStatement(updateDateSql);
                        updateDateStatement.setInt(1, newDay);
                        updateDateStatement.setInt(2, newMonth);
                        updateDateStatement.setInt(3, newYear);
                        updateDateStatement.setString(4, transactionId);
                        updateDateStatement.executeUpdate();
                        System.out.println("Date updated successfully.");
                        break;
                    case 3:
                    
                    System.out.println("Enter new type:");
                    String newType = sc.next();
                
                    // Update the type in the database
                    String updateTypeSql = "UPDATE transactions SET type = ? WHERE transaction_id = ?";
                    PreparedStatement updateTypeStatement = con.prepareStatement(updateTypeSql);
                    updateTypeStatement.setString(1, newType);
                    updateTypeStatement.setString(2, transactionId);
                    updateTypeStatement.executeUpdate();
                    System.out.println("Type updated successfully.");
                
                   
                    if (currentType.equalsIgnoreCase("Income") && newType.equalsIgnoreCase("Expense")) {
                        
                        updateBalance(userId, "Expense", currentAmount);
                    } else if (currentType.equalsIgnoreCase("Expense") && newType.equalsIgnoreCase("Income")) {
                        // If it was Expense and changed to Income, add the amount to the balance
                        updateBalance(userId, "Income", currentAmount);
                    }
                    break;
                
                    case 4:
                        System.out.println("Enter new category:");
                        String newCategory = sc.nextLine();
                        String updateCategorySql = "UPDATE transactions SET category = ? WHERE transaction_id = ?";
                        PreparedStatement updateCategoryStatement = con.prepareStatement(updateCategorySql);
                        updateCategoryStatement.setString(1, newCategory);
                        updateCategoryStatement.setString(2, transactionId);
                        updateCategoryStatement.executeUpdate();
                        System.out.println("Category updated successfully.");
                        break;
                    case 5:
                        sc.nextLine();
                        System.out.println("Enter new description:");
                        String newDescription = sc.nextLine();
                        
                        String updateDescriptionSql = "UPDATE transactions SET description = ? WHERE transaction_id = ?";
                        PreparedStatement updateDescriptionStatement = con.prepareStatement(updateDescriptionSql);
                        updateDescriptionStatement.setString(1, newDescription);
                        updateDescriptionStatement.setString(2, transactionId);
                        updateDescriptionStatement.executeUpdate();
                        System.out.println("Description updated successfully.");
                        break;
                   
                }
            } else {
                System.out.println("Transaction with ID " + transactionId + " not found.");
            }
    
            // Close resources
            rs.close();
            selectStatement.close();
            con.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
        
    void displayAllTransactions() throws SQLException {
        String dburl = "jdbc:mysql://localhost:3306/Finance";
        String dbuser = "root";
        String dbpassword = "";
    
        try (Connection con = DriverManager.getConnection(dburl, dbuser, dbpassword)) {
            String sql = "SELECT * FROM transactions WHERE user_id = ? ";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, userId);
    
            try (ResultSet rs = pst.executeQuery()) {
                System.out.println("Transaction   Amount     Date          Type     Category         Description           User ID");
                System.out.println("-------------------------------------------------------------------------------------------");
    
                while (rs.next()) {
                    // Retrieve and display transaction data
                    String transactionId = rs.getString("transaction_id");
                    double amount = rs.getDouble("amount");
                    int day = rs.getInt("day");
                    int month = rs.getInt("month");
                    int year = rs.getInt("year");
                    String type = rs.getString("type");
                    String category = rs.getString("category");
                    String description = rs.getString("description");
    
                    // Format and display the transaction data
                    String formattedAmount = String.format("%.2f", amount); // Fixed decimal precision
                    String formattedDate = String.format("%02d-%02d-%04d", day, month, year);
    
                    System.out.printf("%-13s %-10s %-12s %-8s %-16s %-20s %-10s%n",
                            transactionId, formattedAmount, formattedDate, type, category, description, userId);
                }
                System.out.println("-------------------------------------------------------------------------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    } 

    
        void totalExpense()
        {int Month1 = 0;
            int year1 = 0;
           try 
           {
             System.out.print("Enter Month : ");
            Month1 = sc.nextInt();
            System.out.print("Enter Year : ");
            year1 = sc.nextInt();
           }
           catch (InputMismatchException e)
           {
            sc.next();
            System.out.println("Custom Exception : Invalid input, you can not add character");
           }
            double totalExpenses = 0;
            String dburl = "jdbc:mysql://localhost:3306/Finance";
            String dbuser = "root";
            String dbpassword = "";
            
            try (Connection con = DriverManager.getConnection(dburl, dbuser, dbpassword);
                PreparedStatement pst = con.prepareStatement("SELECT SUM(amount) FROM transactions WHERE user_id = ? AND type = 'Expense' AND month=? AND year=?")) {
                
                pst.setString(1, userId);
                pst.setInt(2, Month1);
                pst.setInt(3, year1);
                ResultSet rs = pst.executeQuery();
                
                if (rs.next()) {
                    totalExpenses = rs.getDouble(1);
                    System.out.println("Total Expense In This Month Is : "+totalExpenses);
                }
                
            } catch (SQLException e) {
                e.printStackTrace();
               
            }
          
        }
        void SortByDate() throws Exception {
            String dburl = "jdbc:mysql://localhost:3306/Finance";
            String dbuser = "root";
            String dbpassword = "";
        
            try (Connection con = DriverManager.getConnection(dburl, dbuser, dbpassword)) {
                String sql = "SELECT * FROM transactions WHERE user_id = ? ORDER BY year, month, day";
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setString(1, userId);
        
                try (ResultSet rs = pst.executeQuery()) {
                    System.out.println("Transaction   Amount     Date          Type     Category         Description           User ID");
                    System.out.println("-------------------------------------------------------------------------------------------");
        
                    while (rs.next()) {
                        // Retrieve and display transaction data
                        String transactionId = rs.getString("transaction_id");
                        double amount = rs.getDouble("amount");
                        int day = rs.getInt("day");
                        int month = rs.getInt("month");
                        int year = rs.getInt("year");
                        String type = rs.getString("type");
                        String category = rs.getString("category");
                        String description = rs.getString("description");
        
                        // Format and display the transaction data
                        String formattedAmount = String.format("%.2f", amount);
                        String formattedDate = String.format("%02d-%02d-%04d", day, month, year);
        
                        System.out.println(String.format("%-13s %-10s %-12s %-8s %-15s %-20s %-12s",
                                transactionId, formattedAmount, formattedDate, type, category, description, userId));
                    }
                    System.out.println("-------------------------------------------------------------------------------------------");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw e;
            }
        }
        
        void displayByType() throws SQLException {
            String dburl = "jdbc:mysql://localhost:3306/Finance";
            String dbuser = "root";
            String dbpassword = "";
        
            System.out.print("Enter type you want to show : ");
            String typeToDisplay = sc.next();
        
            try (Connection con = DriverManager.getConnection(dburl, dbuser, dbpassword)) {
                String sql = "SELECT * FROM transactions WHERE user_id = ? AND type = ?";
                try (PreparedStatement pst = con.prepareStatement(sql)) {
                    pst.setString(1, userId); // Ensure userId is declared or passed as an argument
                    pst.setString(2, typeToDisplay);
        
                    try (ResultSet rs = pst.executeQuery()) {
                        boolean found = false;
                        System.out.println();
                        System.out.println("Transaction   Amount     Date          Type     Category         Description           User ID");
                        System.out.println("-------------------------------------------------------------------------------------------");
        
                        while (rs.next()) {
                            // Retrieve and display transaction data
                            String transactionId = rs.getString("transaction_id");
                            double amount = rs.getDouble("amount");
                            int day = rs.getInt("day");
                            int month = rs.getInt("month");
                            int year = rs.getInt("year");
                            String type = rs.getString("type");
                            String category = rs.getString("category");
                            String description = rs.getString("description");
        
                            // Format and display the transaction data
                            String formattedAmount = String.format("%.2f", amount);
                            String formattedDate = String.format("%02d-%02d-%04d", day, month, year);
        
                            System.out.println(String.format("%-13s %-10s %-12s %-8s %-15s %-20s %-12s",
                                    transactionId, formattedAmount, formattedDate, type, category, description, userId));
        
                            found = true;
                        }
        
                        System.out.println("-------------------------------------------------------------------------------------------");
        
                        if (!found) {
                            System.out.println("No transactions found for this type");
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw e;
            }
        }
        
        
        void displayByCategory() throws SQLException {
            String dburl = "jdbc:mysql://localhost:3306/Finance";
            String dbuser = "root";
            String dbpassword = "";
        
            System.out.print("Enter category you want to show : ");
            String categoryToDisplay = sc.next();
        
            try (Connection con = DriverManager.getConnection(dburl, dbuser, dbpassword)) {
                String sql = "SELECT * FROM transactions WHERE user_id = ? AND category = ?";
                try (PreparedStatement pst = con.prepareStatement(sql)) {
                    pst.setString(1, userId); // Ensure userId is declared or passed as an argument
                    pst.setString(2, categoryToDisplay);
        
                    try (ResultSet rs = pst.executeQuery()) {
                        boolean found = false;
                        System.out.println();
                        System.out.println("Transaction   Amount     Date          Type     Category         Description           User ID");
                        System.out.println("-------------------------------------------------------------------------------------------");
        
                        while (rs.next()) {
                            // Retrieve and display transaction data
                            String transactionId = rs.getString("transaction_id");
                            double amount = rs.getDouble("amount");
                            int day = rs.getInt("day");
                            int month = rs.getInt("month");
                            int year = rs.getInt("year");
                            String type = rs.getString("type");
                            String category = rs.getString("category");
                            String description = rs.getString("description");
        
                           
                            String formattedAmount = String.format("%.2f", amount);
                            String formattedDate = String.format("%02d-%02d-%04d", day, month, year);
        
                            System.out.println(String.format("%-13s %-10s %-12s %-8s %-15s %-20s %-12s",
                                    transactionId, formattedAmount, formattedDate, type, category, description, userId));
        
                            found = true;
                        }
        
                        System.out.println("-------------------------------------------------------------------------------------------");
        
                        if (!found) {
                            System.out.println("No transactions found in this category");
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw e;
            }
        }
        
    void viewProfile() throws Exception {
        System.out.println();
        System.out.println("|==========================================|");
        System.out.println("|          Your Profile Information        |");
        System.out.println("|==========================================|");
    
        String dburl = "jdbc:mysql://localhost:3306/Finance";
        String dbuser = "root";
        String dbpassword = "";
    
        Connection con = DriverManager.getConnection(dburl, dbuser, dbpassword);
        String sql = "SELECT * FROM users WHERE user_id = ? ";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, userId);
    
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            System.out.println("| User ID      : " + rs.getString(1));
            System.out.println("| First Name   : " + rs.getString(2));
            System.out.println("| Last Name    : " + rs.getString(3));
            System.out.println("| Age          : " + rs.getInt(4));
            System.out.println("| Email        : " + rs.getString(5));
            System.out.println("| Mobile No.   : " + rs.getString(6));
            System.out.println("| Password     : " + rs.getString(7));
        }
    
          System.out.println("|__________________________________________");
        System.out.println();
        System.out.println("you want to edit your profile?");
        System.out.println("1. YES");
        System.out.println("2. NO");
       try
       {
         int choice = sc.nextInt();
        switch(choice)
        {
            case 1 :
            {
                System.out.println("what you want to edit?");
                System.out.println("1. First name");
                System.out.println("2. Last name ");
                System.out.println("3. Age");
                System.out.println("4. Email");
                System.out.println("5. Mobile no.");
                System.out.println("6. Password");
                int ch=sc.nextInt();
                switch(ch)
                {
                    case 1 :
                    {
                        System.out.print("Enter new first name : ");
                        String nName = sc.next();
                        String sm = "Update Users set f_name = ? where user_id=? ";
                        PreparedStatement update = con.prepareStatement(sm);
                        update.setString(1, nName);
                        update.setString(2, userId);
                       int r = update.executeUpdate();
                       if(r>0)
                       {
                        System.out.println("Update is succsessfully !!");
                       }
                       else
                       {
                        System.out.println("Update is failed");
                       }
                    }
                    break;
                    case 2 :
                    {
                        System.out.print("Enter new last name : ");
                        String nName = sc.next();
                        String sm = "Update Users set l_name = ? where user_id=? ";
                        PreparedStatement update = con.prepareStatement(sm);
                        update.setString(1, nName);
                        update.setString(2, userId);
                       int r = update.executeUpdate();
                       if(r>0)
                       {
                        System.out.println("Update is succsessfully !!");
                       }
                       else
                       {
                        System.out.println("Update is failed");
                       }
                    }
                    break;
                    case 3 :
                    {
                        int nage = 0;

                        try
                        {
                            System.out.print("Enter new age : ");
                        nage = sc.nextInt();
                        String sm = "Update Users set age = ? where user_id=? ";
                        PreparedStatement update = con.prepareStatement(sm);
                        update.setInt(1, nage);
                        update.setString(2, userId);
                       int r = update.executeUpdate();
                        
                       if(r>0)
                       {
                        System.out.println("Update is succsessfully !!");
                       }
                       else
                       {
                        System.out.println("Update is failed");
                       }}
                       catch (InputMismatchException e)
                       {
                        sc.next();
                        System.out.println("Custom Exception : Invalid input, you can not add character");
                       }
                    }
                    break;
                    case 4:
                    {
                         System.out.print("Enter new Email_Id : ");
                        String nEmail ;
                        
                while (true) {
                       
                    nEmail = sc.next();
                    if (!nEmail.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}"))
                    {
                        System.out.println("Invalid email address");
                        System.out.println("Enter a valid email address");
                    } 
                    else
                    {
                        break;
                    }
            }
                        String sm = "Update Users set email = ? where user_id=? ";
                        PreparedStatement update = con.prepareStatement(sm);
                        update.setString(1, nEmail);
                        update.setString(2, userId);
                       int r = update.executeUpdate();
                       if(r>0)
                       {
                        System.out.println("Update is succsessfully !!");
                       }
                       else
                       {
                        System.out.println("Update is failed");
                       }

                    }
                    break;
                    case 5 :
                    {

                        System.out.print("Enter new mobile_no. : ");
                        String nMobile_no ;
                           
           
             while (true)
             {
                  
                        nMobile_no = sc.next();

                        if (nMobile_no.length() == 10)
                        {
                            boolean valid = true;
                            for (int i = 0; i < nMobile_no.length(); i++) 
                            {
                                char digit = nMobile_no.charAt(i);
                                if (!Character.isDigit(digit)) {
                                    valid = false;
                                    System.out.println("Invalid number. Enter a valid 10-digit number.");
                                    break; // Break the loop if an invalid character is found
                                }
                            }
                            if (valid) {
                            // Valid mobile number, exit the loop
                            break;
                         }
                        } 
                        else 
                        {
                            System.out.println("Invalid number. Enter a valid 10-digit number.");
                        }
                    }
                        String sm = "Update Users set mobile_no = ? where user_id=? ";
                        PreparedStatement update = con.prepareStatement(sm);
                        update.setString(1, nMobile_no);
                        update.setString(2, userId);
                       int r = update.executeUpdate();
                       if(r>0)
                       {
                        System.out.println("Update is succsessfully !!");
                       }
                       else
                       {
                        System.out.println("Update is failed");
                       }
                    }
                    break;
                    case 6 :
                    {
                        String npassword;
                        while (true) {
                            System.out.print("Enter password (Password must be 8 characters and contain a special character) : ");
                            npassword = sc.next();
                            
                            if (npassword.length() != 8 || !npassword.matches(".*[^a-zA-Z0-9].*")) {
                                System.out.println("Invalid password");
                                System.out.println("Password must be 8 characters and contain at least one special character");
                            } else {
                                break;
                            }
                        }
                        String sm = "Update Users set password = ? where user_id=? ";
                        PreparedStatement update = con.prepareStatement(sm);
                        update.setString(1, npassword);
                        update.setString(2, userId);
                       int r = update.executeUpdate();
                       if(r>0)
                       {
                        System.out.println("Update is succsessfully !!");
                       }
                       else
                       {
                        System.out.println("Update is failed");
                       }
                    }
                    break;
                    default:
                    {
                        System.out.println("Enter valid choice");
                    }
                }
            }
            break;
            case 2 :
            {
                break;
            }
        }
    
       }
       catch(InputMismatchException e)
       {
        sc.next();
        System.out.println("Custom Exception : Invalid input you cant add character");
       }
    }
 
    void ExportTransactionsByDateRange() throws Exception
    {
                
        String dburl = "jdbc:mysql://localhost:3306/Finance";
        String dbuser = "root";
        String dbpassword = "";
        int count;
        Connection con = DriverManager.getConnection(dburl, dbuser, dbpassword);
       int s_day = 0;
       int s_month = 0;
       int s_year = 0;
       int e_day = 0;
       int e_month = 0;
       int e_year = 0;  
       try
       {
         System.out.print("Enter starting date : ");
         s_day = sc.nextInt();
        System.out.print("Enter month : ");
         s_month = sc.nextInt();
        System.out.print("Enter year : ");
         s_year = sc.nextInt();
        System.out.println();
        System.out.print("Enter ending date : ");
        e_day = sc.nextInt();
        System.out.print("Enter month : ");
         e_month = sc.nextInt();
        System.out.print("ENter year : ");
        e_year = sc.nextInt();
       }
       catch(InputMismatchException e)
       {
         sc.next();
         System.out.println("Custom Exception : Invalid input, you can not add character");
         
       }
     
        String s = "transactions_" + s_day + "-" + s_month + "-" + s_year + "_to_" + e_day + "-" + e_month + "-" + e_year + ".txt";

         File f = new File("C:\\Users\\joshi\\OneDrive\\Documents\\Desktop\\Project\\Project\\"+s);

        String selectQuery = "SELECT * FROM transactions WHERE user_id = ? " + "AND DATE(CONCAT(year, '-', LPAD(month, 2, '0'), '-', LPAD(day, 2, '0'))) " +  "BETWEEN ? AND ?";
    
   
         PreparedStatement selectStatement = con.prepareStatement(selectQuery);
        selectStatement.setString(1, userId);
        selectStatement.setString(2, s_year + "-" + s_month + "-" + s_day);
        selectStatement.setString(3, e_year + "-" + e_month + "-" + e_day);
        
       
        

        ResultSet resultSet = selectStatement.executeQuery();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(f))) {
            while (resultSet.next()) {
                String transactionID = resultSet.getString("transaction_id");
                String description = resultSet.getString("description");
                String type = resultSet.getString("type");
                String category = resultSet.getString("category");
                String day = resultSet.getString("day");
                String month = resultSet.getString("month");
                String year = resultSet.getString("year");
                double amount = resultSet.getDouble("amount");

                // Format and write transaction details to the file
                String transactionInfo = String.format("Transaction ID: %s%nDescription: %s%nType: %s%nCategory: %s%nDate: %s%nAmount: %.2f%n%n",
                        transactionID, description, type, category, day+"-"+month+"-"+year, amount);
                writer.write(transactionInfo);
                writer.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Transactions within the specified date range have been stored in " + f);
    } 
    public static void updateBalance(String userId, String transactionType, double newAmount) throws SQLException {
        String sql;
        double newAmount1 = newAmount ;
     

        if (transactionType.equals("Income")) {
            sql = "UPDATE balance SET balance = balance + ? WHERE user_id = ?";
        } else if (transactionType.equals("Expense")) {
            sql = "UPDATE balance SET balance = balance - ? WHERE user_id = ?";
        } else {
            throw new SQLException("Unknown transaction type: " + transactionType);
        }

        PreparedStatement statement = DriverManager.getConnection("jdbc:mysql://localhost:3306/finance", "root", "").prepareStatement(sql);
        statement.setDouble(1, Math.abs(newAmount1));
        statement.setString(2, userId);
        statement.executeUpdate();
    }
}

    

class InvalidChoiceException extends Exception {
    public InvalidChoiceException(String message) {
        super(message);
    }
}
