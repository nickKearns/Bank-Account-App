/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assigment5;
import bankaccountapp.Person;
import bankaccountapp.BankAccount;
import java.util.ArrayList;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;
/**
 *
 * @author nickkearns
 */
public class BankManager 
{
    
    public BankManager()
    {
        
    }
    ArrayList<BankAccount> listOfAccounts = new ArrayList<BankAccount>();

    
    public void loadFromFile()
    {
        try
        {
            FileInputStream fis = new FileInputStream("/Users/nickkearns/desktop/assignment5.txt");
                Scanner fileScan = new Scanner(fis);
                String textLine = "";
                while (fileScan.hasNextLine())
                {
                    textLine = fileScan.nextLine();
                    BankAccount currentAccount = new BankAccount(0, "", 0.0, 0, null);
                    currentAccount.loadFromFile(textLine);
                    listOfAccounts.add(currentAccount);


                }
                fis.close();
        }
        catch (Exception e)
        {
            System.out.println("error");
        }
    }
    
    
    public void addAccount(BankAccount currentAccount)
    {
        currentAccount.setAccountNumber(listOfAccounts.size()+1);
        listOfAccounts.add(currentAccount);
        System.out.println("New account number is: " + currentAccount.getAccountNumber());
    }
    public BankAccount findAccount(int accountNumber)
    {
        BankAccount defaultAcc = null;
        for (int i = 0; i < listOfAccounts.size(); i++)
        {
            if (listOfAccounts.get(i).getAccountNumber() == accountNumber)
            {
                defaultAcc =  listOfAccounts.get(i);
            }
        }
        //might not find the account with that number so it wont return anything
        return defaultAcc;
    }
    
    
    public boolean deleteAccount(int accountNumber)
    {
        boolean success = false;
        for (int i = 0; i < listOfAccounts.size(); i ++)
        {
            if (listOfAccounts.get(i).getAccountNumber() == accountNumber)
            {
                listOfAccounts.remove(i);
                success = true;
            }
            
        }
        
        return success;
        
    }
    public double getAverageBalance()
    {
        double totalBalance = 0.0;
        
        for (int i = 0; i < listOfAccounts.size();i ++)
        {
            totalBalance += listOfAccounts.get(i).getCurrentBalance();
        }
        return totalBalance/listOfAccounts.size();
    }
    public double getMaximumBalance()
    {
        double maxBalance = listOfAccounts.get(0).getCurrentBalance();
        
        for (int i = 0; i < listOfAccounts.size(); i ++)
        {
            if (listOfAccounts.get(i).getCurrentBalance() > maxBalance)
            {
                maxBalance = listOfAccounts.get(i).getCurrentBalance();
            }
        }
        return maxBalance;
    }
    public double getMinimumBalance()
    {
        double minBalance = listOfAccounts.get(0).getCurrentBalance();
        for (int i = 0; i < listOfAccounts.size(); i ++)
        {
            if (listOfAccounts.get(i).getCurrentBalance() < minBalance)
            {
                minBalance = listOfAccounts.get(i).getCurrentBalance();
            }
        }
        return minBalance;
    }
    
    
    public void writeToFile()
    {
        File f = new File("/Users/nickkearns/desktop/assignment5.txt");
        try 
        {
            FileOutputStream fos = new FileOutputStream(f);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            for (int i = 0; i < listOfAccounts.size(); i ++)
            {
                osw.write(listOfAccounts.get(i).convertToString() + "\n");

            }
        osw.close();
        fos.close();
        }
        catch (Exception e)
        {
            System.out.println("error");
        }
        
    }
    
    
    
    
}
