
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while(true){
            System.out.println("\n=== USER AUTH SYSTEM ===");
            System.out.println("1) Register");
            System.out.println("2) Login");
            System.out.println("3) Forgot Password");
            System.out.println("4) Exit");
            System.out.print("Choose: ");
            String ch = sc.nextLine();

            try {
                switch(ch){
                    case "1": register(); break;
                    case "2": login(); break;
                    case "3": reset(); break;
                    case "4": System.out.println("Goodbye!"); return;
                    default: System.out.println("Invalid choice.");
                }
            } catch (Exception e){
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    static void register() throws Exception {
        System.out.print("Full Name: ");
        String name = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Password: ");
        String pw = sc.nextLine();

        if(UserService.register(name,email,pw))
            System.out.println("Registration successful. You may login now.");
    }

    static void login() throws Exception {
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Password: ");
        String pw = sc.nextLine();

        if(UserService.login(email,pw))
            System.out.println("Login successful.");
        else
            System.out.println("Invalid email or password.");
    }

    static void reset() throws Exception {
        System.out.print("Registered Email: ");
        String email = sc.nextLine();
        System.out.print("New Password: ");
        String pw = sc.nextLine();

        if(UserService.resetPassword(email,pw))
            System.out.println("If this email is registered, password has been reset.");
    }
}
