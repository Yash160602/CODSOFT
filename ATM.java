import java.util.Scanner;

public class ATM {
    private BankAccount bankAccount;

    public ATM(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public void displayOptions() {
        System.out.println("Options:");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Exit");
    }

    public void checkBalance() {
        System.out.println("Your current balance is: $" + bankAccount.getBalance());
    }

    public void deposit(double amount) {
        bankAccount.deposit(amount);
        System.out.println("Deposit of $" + amount + " successful. New balance: $" + bankAccount.getBalance());
    }

    public void withdraw(double amount) {
        if (bankAccount.withdraw(amount)) {
            System.out.println("Withdrawal of $" + amount + " successful. New balance: $" + bankAccount.getBalance());
        } else {
            System.out.println("Insufficient balance. Withdrawal failed.");
        }
    }

    public static void main(String[] args) {
        BankAccount bankAccount = new BankAccount(1000.0);
        ATM atm = new ATM(bankAccount);

        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            atm.displayOptions();
            System.out.print("Enter your choice (1-4): ");
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    atm.checkBalance();
                    break;

                case 2:
                    System.out.print("Enter the amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    atm.deposit(depositAmount);
                    break;

                case 3:
                    System.out.print("Enter the amount to withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    atm.withdraw(withdrawAmount);
                    break;

                case 4:
                    System.out.println("Exiting the ATM. Have a nice day!");
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (option != 4);

        scanner.close();
    }
}
