import java.util.*;
import java.io.*;

class GoalManager {
    private String userId;
    private ArrayList<Goal> goals;
    Scanner sc = new Scanner(System.in);

    public GoalManager(String userId) {
        this.userId = userId;
        goals = new ArrayList<>();
        loadGoalsFromFile();
    }
    public void setNewGoal() throws Exception {
        String goalName = "";
        double targetAmount = 0;
        double savedAmount = 0;
        int day = 0;
        int month = 0;
        int year = 0;
        
        try {
            System.out.print("Add goal name : ");
            goalName = sc.nextLine();
    
            System.out.print("Enter target amount : ");
            targetAmount = sc.nextDouble();
    
            System.out.print("Enter Amount you saved already : ");
            savedAmount = sc.nextDouble();
    
            while (true) {
                System.out.print("Enter date : ");
                day = sc.nextInt();
    
                System.out.print("Enter Month : ");
                month = sc.nextInt();
    
                System.out.print("Enter year : ");
                year = sc.nextInt();
                sc.nextLine();
    
                boolean validDate = isValidDate(year, month, day);
                if (validDate) {
                    break;
                } else {
                    System.out.println("Invalid date format. Please try again.");
                }
            }
            
            Goal goal = new Goal(userId, goalName, targetAmount, savedAmount, day, month, year);
            goals.add(goal);
            updateGoalsFile();
            System.out.println("Goal has been saved.");
        } catch (InputMismatchException e) {
            sc.next();
            System.out.println("Custom Exception : You can't add characters. Goal not saved.");
        }
    }
    

    public void viewGoals() throws Exception {
        for (Goal goal : goals) {
            if (goal.getUserId().equals(userId)) {
                System.out.println("Goal Name: " + goal.getGoalName());
                System.out.println("Target Amount: " + goal.getTargetAmount());
                System.out.println("Saved Amount: " + goal.getSavedAmount());
                System.out.println("Date: " + goal.getDate() + "/" + goal.getMonth() + "/" + goal.getYear());
                System.out.println("---------------------------");
            }
        }
    }
    public void updateGoal() throws Exception {
        System.out.print("Enter goal name : ");
        String goalName = sc.next();
        double amount = 0;
        try
        {
            System.out.print("Enter the amount to be added/subtracted from your current saving. : ");
         amount = sc.nextDouble();
        }
        catch(InputMismatchException e)
        {
             sc.next();
            System.out.println("Custom Exception : You can't add characters. Goal not saved.");
        }
        for (Goal goal : goals) {
            if (goal.getGoalName().equalsIgnoreCase(goalName) && goal.getUserId().equalsIgnoreCase(userId)) {
                double currentSavedAmount = goal.getSavedAmount();
                goal.setSavedAmount(currentSavedAmount + amount);
                updateGoalsFile();
                System.out.println("SavedAmount for goal '" + goalName + "' updated successfully.");
                return;
            }
        }
        System.out.println("Goal with name '" + goalName + "' not found for the current user.");
    }
    public void removeGoal(String goalName) throws Exception {
        Goal goalToRemove = null;
        for (Goal goal : goals) {
            if (goal.getGoalName().equals(goalName) && goal.getUserId().equals(userId)) {
                goalToRemove = goal;
                break;
            }
        }
        if (goalToRemove != null) {
            goals.remove(goalToRemove);
            System.out.println("Goal removed successfully!!");
            updateGoalsFile();
        } else {
            System.out.println("No goal found with the specified name for the current user.");
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
        if (year >= 1 && year <= 9999) {
           return day <= daysInMonth[month];
        } else {
            return false;
        }

        
    }


    private void updateGoalsFile() throws Exception {
        try {
            File f = new File("Goal.txt");
            BufferedWriter writer = new BufferedWriter(new FileWriter(f));

            for (Goal goal : goals) {
                writer.write(goal.getUserId() + "," + goal.getGoalName() + ","
                        + goal.getTargetAmount() + "," + goal.getSavedAmount() + ","
                        + goal.getDate() + "," + goal.getMonth() + "," + goal.getYear());
                writer.newLine();
            }
            writer.close();
            System.out.println("Goals file updated successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    private void loadGoalsFromFile() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("Goal.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 7) {
                    String userId = parts[0].trim();
                    String goalName = parts[1].trim();
                    double targetAmount = Double.parseDouble(parts[2].trim());
                    double savedAmount = Double.parseDouble(parts[3].trim());
                    int date = Integer.parseInt(parts[4].trim());
                    int month = Integer.parseInt(parts[5].trim());
                    int year = Integer.parseInt(parts[6].trim());
                    goals.add(new Goal(userId, goalName, targetAmount, savedAmount, date, month, year));
                }
            }
            reader.close();
        } catch (IOException e) {
            System.err.println("Error reading goals file: " + e.getMessage());
        }
    }
}

class Goal {
    private String goalName;
    private double targetAmount;
    private double savedAmount;
    private int date;
    private int month;
    private int year;
    private String userId;

    private ArrayList<Goal> goals = new ArrayList<>();

    public Goal(String userId, String goalName, double targetAmount, double savedAmount, int date, int month, int year) {
        this.userId = userId;
        this.goalName = goalName;
        this.targetAmount = targetAmount;
        this.savedAmount = savedAmount;
        this.date = date;
        this.month = month;
        this.year = year;
    }

    public Goal() {
        
        goals = new ArrayList<>();
        
    }



    public String getGoalName() {
        return goalName;
    }

    public void setGoalName(String goalName) {
        this.goalName = goalName;
    }

    public double getTargetAmount() {
        return targetAmount;
    }

    public void setTargetAmount(double targetAmount) {
        this.targetAmount = targetAmount;
    }

    public double getSavedAmount() {
        return savedAmount;
    }

    public void setSavedAmount(double savedAmount) {
        this.savedAmount = savedAmount;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public ArrayList<Goal> getGoals() {
        return goals;
    }

    public void setGoals(ArrayList<Goal> goals) {
        this.goals = goals;
    }

    
}
 