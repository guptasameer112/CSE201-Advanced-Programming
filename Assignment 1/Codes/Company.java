// Imports
import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.*;

/*
CompanyFunctionalities
{
    AddCompany();
    ChooseCompany();
    AvailableCompanies();
    Back();
}
*/

// <----------------------------------------Company---------------------------------------------------->

public class Company 
{
    String name;
    String role;
    int CTC;
    float CGPA;
    boolean registered = false;

    static Scanner companyobj = new Scanner(System.in);

    // <--------------------------------Company (Constructor)------------------------------------------->

    public Company(String name, String role, int CTC, float CGPA, boolean registered) {
        this.name = name;
        this.role = role;
        this.CTC = CTC;
        this.CGPA = CGPA;
        this.registered = registered;
    }

    // <--------------------------------Setter Functions------------------------------------------->  
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public void setRole(String role) 
    {
        this.role = role;
    }

    public void SetPackage(int newpackage) 
    {
        this.CTC = newpackage;
    }

    public void setnewcgpa(float cgpa) {
        this.CGPA = cgpa;
    }

    // <--------------------------------Getter Functions------------------------------------------->

    public String getName()
    {
        return this.name;
    }

    public String getRole()
    {
        return this.role;
    }

    public int getPackage()
    {
        return this.CTC;
    }

    public float getCGPA()
    {
        return this.CGPA;
    }

    // <--------------------------------Menu------------------------------------------->

    public static void menu() {
        System.out.println("\n<--------------------------------Menu------------------------------------------->\n");
        System.out.println("Choose the Company Query to perform:");
        System.out.println("1. Add Company and Details\n2. Choose Company\n3. Get Available Companies\n4. Back");
        System.out.print("Input: ");
        int companychoice = companyobj.nextInt();

        // System.out.println("Chosen Query: " + companychoice);

        switch (companychoice) {
            case 1:
                addcompany();
                break;
            case 2:
                choosecompany();
                break;
            case 3:
                availablecompanies(PlacementCell.companies);
                break;
            case 4:
                Main.EnterApplication();
                break;
        }
    }

    // <--------------------------------Add Company------------------------------------------->

    public static void addcompany() 
    {
        System.out.println("\n<--------------------------------Add Company------------------------------------------->\n");
        System.out.println("Enter the Company's Name: ");
        companyobj.nextLine();
        System.out.print("Input: ");
        String name = companyobj.nextLine();

        System.out.println("Enter the Company's Role: ");
        System.out.print("Input: ");
        
        String role = companyobj.nextLine();

        System.out.println("Enter the Company's CTC: ");
        System.out.print("Input: ");
        
        int ctc = companyobj.nextInt();

        System.out.println("Enter the Company's CGPA requirement: ");
        companyobj.nextLine();
        System.out.print("Input: ");
        
        float cgpa = companyobj.nextFloat();

        Company company = new Company(name, role, ctc, cgpa, true);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");  
        LocalDateTime now = LocalDateTime.now();  
        System.out.println(dtf.format(now));
        String current = dtf.format(now);

        if (PlacementCell.comparedatetime(current, PlacementCell.companyopeningtime) && PlacementCell.comparedatetime(PlacementCell.companyclosingtime, current))
        {
            PlacementCell.companies.add(company);
            System.out.println("Company Added Successfully!");
        }
        else
        {
            System.out.println("Company Registration is Closed!");
        }

        

        menu();
    }

    // <--------------------------------Choose Company------------------------------------------->

    public static void choosecompany() 
    {
        System.out.println("\n<--------------------------------Choose Company------------------------------------------->\n");
        System.out.println("\nChoose to enter into mode of Available Companies:");
        System.out.println();
        Company chosencompany;

        int count = 1;
        for (Company company : PlacementCell.companies) 
        {
            System.out.println(count + ". Name: " + company.name);
            count++;
        }
        // 1. Google
        // 2. Microsoft
        System.out.print("Input: ");

        int chosen = companyobj.nextInt();

        chosencompany = PlacementCell.companies.get(chosen - 1);

        displaycompany(chosencompany);

    }


    // <--------------------------------Available Companies------------------------------------------->

    public static void availablecompanies(ArrayList<Company> companies) 
    {
        System.out.println("\n<--------------------------------Available Companies------------------------------------------->\n");
        System.out.println("Available Companies: ");
        System.out.println();

        for (int i = 0; i < PlacementCell.companies.size(); i++) 
        {
            System.out.println("Name: " + PlacementCell.companies.get(i).name + "\nRole: " + PlacementCell.companies.get(i).role + "\nCTC: " + PlacementCell.companies.get(i).CTC + "\nCGPA: " + PlacementCell.companies.get(i).CGPA + "\n");
        }

        menu();

    }

    // <--------------------------------Display Company------------------------------------------->

    public static void displaycompany(Company chosencompany) {
        System.out.println("\n<--------------------------------Display Company------------------------------------------->\n");

        System.out.println("Welcome " + chosencompany.name + "!");
        System.out.println("1. Update Role\n2. Update Package\n3. Update CGPA criteria\n4. Register to Institute Drive\n5. Back");
        System.out.print("Input: ");
        
        int choice = companyobj.nextInt();

        switch (choice) {
            case 1:
                updaterole(chosencompany);
                break;
            case 2:
                updatepackage(chosencompany);
                break;
            case 3:
                updatecgpa(chosencompany);
                break;
            case 4:
                register(chosencompany);
                break;
            case 5:
                menu();
                break;
        }
    }

    // <--------------------------------Update Role------------------------------------------->

    public static void updaterole(Company chosencompany) 
    {
        System.out.println("\n<--------------------------------Update Role------------------------------------------->\n");
        System.out.println("Enter the new Role: ");
        System.out.print("Input: ");
        
        String newrole = companyobj.next();
        chosencompany.role = newrole;

        displaycompany(chosencompany);
    }

    // <--------------------------------Update Package------------------------------------------->
    
    public static void updatepackage(Company chosencompany) {
        System.out.println("\n<--------------------------------Update Package------------------------------------------->\n");
        System.out.println("\nEnter the new Package: ");
        System.out.print("Input: ");
        
        int newpackage = companyobj.nextInt();
        chosencompany.CTC = newpackage; // Don't update if lower than previous

        displaycompany(chosencompany);
    }

        // <--------------------------------Update CGPA------------------------------------------->
    
    public static void updatecgpa(Company chosencompany) {
        System.out.println("\n<--------------------------------Update CGPA------------------------------------------->\n");
        System.out.println("Enter the new CGPA criteria: ");
        System.out.print("Input: ");
        
        float newcgpareq = companyobj.nextInt();
        chosencompany.CGPA = newcgpareq; // Same here
        
        displaycompany(chosencompany);
    }

    // <--------------------------------Register------------------------------------------->
    
    public static void register(Company chosencompany) {
        System.out.println("\n<--------------------------------Register------------------------------------------->\n");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        LocalDateTime now = LocalDateTime.now();
        String current2 = dtf.format(now);
        if (PlacementCell.comparedatetime(current2, PlacementCell.companyopeningtime) && PlacementCell.comparedatetime(PlacementCell.companyclosingtime, current2))
        {
            System.out.println(current2);
            // PlacementCell.companies.add(company);
            System.out.println("Registered!!");
        }
        else
        {
            System.out.println("Company Registration is Closed!");
        }
        displaycompany(chosencompany);
    }

    // <--------------------------------main------------------------------------------->

    public static void main(String[] args) {
        menu();
    }

}


// <----------------------------------------End---------------------------------------------------->