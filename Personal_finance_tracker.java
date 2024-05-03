import java.util.*;

public class Personal_finance_tracker {
    public static void main(String[] args) throws Exception {
        Users users = new Users();
        Scanner sc = new Scanner(System.in);
               
            
        title(sc);

        while (true) {
            try {
                System.out.println("What do you want to perform ?");
                System.out.println("1. Log in");
                System.out.println("2. Sign up");
                System.out.println("3. Exit");
                int choice;

                try {
                   choice  = sc.nextInt();
                } catch (InputMismatchException e) {
                    sc.next(); 
                    throw new InvalidChoiceException("Custom Exception : Invalid input, you can not add character, Enter a valid choice (1, 2, or 3).");
                }

                switch (choice) {
                    case 1: {
                        users.log_in();
                    }
                    break;
                    case 2: {
                        users.sign_up();
                    }
                    break;
                    case 3: {
                        System.exit(0);
                    }
                    break;
                    default: {
                        System.out.println("Enter valid choice");
                        throw new InvalidChoiceException("Invalid choice, you can not add character, Enter a valid choice (1, 2, or 3).");
                    }
                }
            } catch (InvalidChoiceException e) {
                System.out.println(e.getMessage());
            }
        } 
       
    }
    public static void title(Scanner sc)
    {
                System.out.println();
                System.out.println("                                        |==========================================|             ");
                System.out.println("                                        |        $                      $          |             ");
                System.out.println("                                        |       WELCOME TO  PERSONAL FINANCE       |             ");
                System.out.println("                                        |                 TRACKER                  |             ");
                System.out.println("                                        |        $                       $         |             ");
                System.out.println("                                        |==========================================|             ");
                System.out.println();
                System.out.println("                                     Get control of your finances and start tracking your        ");
                System.out.println("                                                income and expenses with ease.                   ");
                System.out.println();
                System.out.println("                                                    [ Get Started ]                              ");
                System.out.println();
                System.out.println("                                      _______________________________________________            ");
                System.out.println("                                               Press Enter to Get Started...");
                sc.nextLine(); 
                
                System.out.println("                                              You have started the application...                  ");
                System.out.println();
                
    }
}

class InvalidChoiceException extends Exception {
    public InvalidChoiceException(String message) {
        super(message);
    }
}


