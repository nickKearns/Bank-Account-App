package assigment5;
import java.util.Scanner;
import java.text.DecimalFormat;
import bankaccountapp.Person;
import bankaccountapp.BankAccount;
public class Assigment5 {

    
    public static void main(String[] args) {
        
        Scanner scan = new Scanner(System.in);
        BankManager currentBank = new BankManager();
        currentBank.loadFromFile();
        int userChoice;
        char userContinue = 'y';
        DecimalFormat moneyFormat = new DecimalFormat("$#,###.00");
        
      
        
        while (userContinue == 'y')
        {
                
        
        System.out.println("1: Create New Account");
        System.out.println("2: Perform Operations in an Existing Account");
        System.out.println("3: Delete an Account");
        System.out.println("4: Display the Average of all Account Balances");
        System.out.println("5: Display the Maximum and Minimum Account Balances");
        System.out.println("6: Quit");
        userChoice = scan.nextInt();
            switch (userChoice)
            {

                case 1:
                    // do all the code to create new person and then create a bank account for that person
                       int age;
                       String name;
                       String address;
                       String birthDay;
                       int accountNumber;
                       String createdDate;
                       double currentBalance;
                       int withdrawalLimit;
                       System.out.println("Please enter your age");
                       age = scan.nextInt();
                       System.out.println("Please enter your name");
                       name = scan.next();
                       scan.nextLine();
                       System.out.println("Please enter your address");
                       address = scan.nextLine();
                       System.out.println("Please enter your birthday");
                       birthDay = scan.nextLine();
                       Person tmpPerson = new Person(age, name, address, birthDay);
                       System.out.println("Enter todays date");
                       createdDate = scan.nextLine();
                       System.out.println("please enter how much you are depositing");
                       currentBalance = scan.nextDouble();
                       System.out.println("Please enter your desired withdrawal limit");
                       withdrawalLimit = scan.nextInt();
                       scan.nextLine();
                       accountNumber = 0;
                       BankAccount tmpAccount = new BankAccount(accountNumber, createdDate, currentBalance, withdrawalLimit, tmpPerson);
                       currentBank.addAccount(tmpAccount);
                       System.out.println("Successfully created a new acount!");
                       
                       
                    
                    
                    break;
                case 2: 
                    //do all the code from assignment 3
                    char userAnswer = 'y';
                    while (userAnswer == 'y')
                   {
                       System.out.println("Please enter the account number of the account you would like to access");
                       int currentAccNum = scan.nextInt();
                       if (currentBank.findAccount(currentAccNum) != null)
                       {
                           System.out.println("What would you like to do?");
                           System.out.println("1: deposit");
                           System.out.println("2: withdraw");
                           System.out.println("3: see balance");
                           System.out.println("4: see account details");
                           System.out.println("5: quit");
                           int choice = scan.nextInt();

                           switch (choice)
                           {
                               case 1:
                                   System.out.println("How much would you like to deposit?");
                                   double depositAmount = scan.nextDouble();
                                   if (currentBank.findAccount(currentAccNum).deposit(depositAmount))
                                   {
                                       System.out.println("Successful deposit, your new balance is: " + moneyFormat.format(currentBank.findAccount(currentAccNum).getCurrentBalance()));
                                   }
                                   else 
                                   {
                                       System.out.println("Unsuccessful deposit");
                                   }
                               break;
                               case 2: 
                                   System.out.println("How much would you like to withdraw?");
                                   double withdrawAmount = scan.nextDouble();
                                   if (currentBank.findAccount(currentAccNum).withdraw(withdrawAmount))
                                   {
                                       System.out.println("Successful withdrawal. Your new balance is: " + moneyFormat.format(currentBank.findAccount(currentAccNum).getCurrentBalance()));
                                   }
                                   else 
                                   {
                                       System.out.println("Unsuccessful withdrawal");
                                   }
                               break;
                               case 3: 
                                   System.out.println("Your current balance is: " + moneyFormat.format(currentBank.findAccount(currentAccNum).getCurrentBalance()));
                                break;
                               case 4: 
                                   System.out.println("Bank account details: " + currentBank.findAccount(currentAccNum).toString());
                                break;
                               case 5:
                                   System.out.println("Thank you for doing business with us.");
                                   userAnswer = 'n';
                               default: 

                           }
                       }
                       else 
                       {
                           System.out.println("could not find that account");
                       }
                       scan.nextLine();
                       System.out.println("would you like to do another action within an account?");
                       userAnswer = scan.nextLine().charAt(0);

                   }
                    break;
                case 3: 
                    System.out.println("Please enter the account number of the account you would like to delete");
                    int tmpAccNum = scan.nextInt();
                    if (currentBank.deleteAccount(tmpAccNum))
                    {
                        System.out.println("Successfully deleted that account");
                    }
                    else {
                        System.out.println("Could not find that account");
                    }
                    break;
                case 4: 
                    System.out.println("The average of all account balances is: " + currentBank.getAverageBalance());
                    break;
                case 5: 
                    System.out.println("The current maximum balance is: " + currentBank.getMaximumBalance() + 
                            " and the current minimum balance is: " + currentBank.getMinimumBalance());
                    break;
                case 6:
                    System.out.println("Thank you for doing business with us!");
                    userContinue = 'n';
            }
            if (userContinue != 'n')
            {
                System.out.println("Would you like to perform another action? y/n");
                userContinue = scan.next().charAt(0);
            }
        }
        
        
        
        
        currentBank.writeToFile();
        
        
        
        
        
    }
    
}
