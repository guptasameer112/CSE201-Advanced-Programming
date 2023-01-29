// Imports 
import java.util.Scanner;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;
import java.util.*;

/*
StudentFunctionality
{
    EnterStudent();
        PlacementDrive( Name, RollNumber, CGPA, Branch );
        RegisterForCompany();
        AvailableCompanies();
        GetCurrentStatus();
        UpdateCGPA();
        AcceptOffers();
        RejectOffers();
        Back();
    AddStudent();
    Back();
} 
*/

// <----------------------------------------Student---------------------------------------------------->


public class Student 
{
    String name;
    int rollnumber;
    float cgpa;
    String branch;
    int status = 0; // 1. Placed 2. Not Placed 3. Blocked
    String placedCompany;

    ArrayList<Company> registeredcompanies = new ArrayList<Company>();
    

    static Scanner studentobj = new Scanner(System.in);

    // <--------------------------------Student (Constructor)------------------------------------------->

    public Student(String name, int rollnumber, float cgpa, String branch, int status)
    {
        this.name = name;
        this.rollnumber = rollnumber;
        this.cgpa = cgpa;
        this.branch = branch;
        this.status = status;
    }

    // <--------------------------------Setter Functions------------------------------------------->
    

    public void setName(String name)
    {
        this.name = name;
    }

    public void setrollNo(int rollNumber)
    {
        this.rollnumber = rollNumber;
    }

    public void setcgpa(float cgpa)
    {
        this.cgpa = cgpa;
    }
    
    public void setbranch(String branch)
    {
        this.branch = branch;
    }

    public void setstatus(int status)
    {
        this.status = status;
    }

    // <--------------------------------Getter Functions------------------------------------------->


    public String getName()
    {
        return this.name;
    }

    public int getrollNo()
    {
        return this.rollnumber;
    }

    public float getcgpa()
    {
        return this.cgpa;
    }

    public String getbranch()
    {
        return this.branch;
    }

    public int getstatus()
    {
        return this.status;
    }

   // <--------------------------------Other Functions-------------------------------------------> 

   static int maximum_CTC = 0; 

   public static Student getMaxCTC(Student student)
   {
        for(int i = 0; i < student.registeredcompanies.size(); i++)
        {
            if(student.registeredcompanies.get(i).CTC > maximum_CTC)
            {
                maximum_CTC = student.registeredcompanies.get(i).CTC;
            }
        }
        return student;
   }

    // <--------------------------------Menu------------------------------------------->

    static void menu()
    {
        System.out.println("\n<--------------------------------Menu------------------------------------------->\n");
        System.out.println("Choose the Student Query to perform:");
        System.out.println("1. Enter as a Student\n2. Add students\n3. Back");
        System.out.print("Input: ");
        
        int studentchoice = studentobj.nextInt();


        switch(studentchoice)
        {
            case 1:
                enterstudent();
                break;
            case 2:
                addstudent();
                break;
            case 3:
                Main.EnterApplication();
                break;
        }
    }

    // <--------------------------------Enter Student------------------------------------------->

    public static void enterstudent()
    {
        System.out.println("\n<--------------------------------Enter Student------------------------------------------->\n");
        System.out.println("Enter the Student's Name: ");
        studentobj.nextLine();
        System.out.print("Input: ");

        String name = studentobj.nextLine();

        System.out.println("Enter the Student's Roll Number: ");
        System.out.print("Input: ");
        
        int rollnumber = studentobj.nextInt();

        for (int i = 0; i < PlacementCell.students.size(); i++)
        {
            if (PlacementCell.students.get(i).name.equals(name) && PlacementCell.students.get(i).rollnumber == rollnumber)
            {
                System.out.println("\nWelcome " + name + "!");
                studentchoices(PlacementCell.students.get(i));
                break;
            }
        }

        menu();
    }

     // <--------------------------------Add Student------------------------------------------->

    public static void addstudent()
    // Bechare IIITD aane ki galti kardi lmao
    {
        System.out.println("\n<--------------------------------Add Student------------------------------------------->\n");
        System.out.println("Number of students to add: ");
        System.out.print("Input: ");
        
        int numberofstudents = studentobj.nextInt();

        for (int i = 0; i < numberofstudents; i++)
        {
            System.out.println("\nStudent " + (i+1) + ": ");
            System.out.println();
            System.out.println("Enter the Student's Name: ");
            studentobj.nextLine();
            System.out.print("Input: ");

            String name = studentobj.nextLine();

            System.out.println("Enter the Student's Roll Number: ");
            System.out.print("Input: ");
            
            int rollnumber = studentobj.nextInt();

            System.out.println("Enter the Student's CGPA: ");
            System.out.print("Input: ");
            
            float cgpa = studentobj.nextFloat();

            System.out.println("Enter the Student's Branch: ");
            System.out.print("Input: ");
            
            String branch = studentobj.next();

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");  
            LocalDateTime now = LocalDateTime.now();  
            System.out.println(dtf.format(now));
            
            Student student = new Student(name, rollnumber, cgpa, branch, 2);
            
            if (PlacementCell.comparedatetime(dtf.format(now), PlacementCell.studentopeningtime) && PlacementCell.comparedatetime(PlacementCell.studentclosingtime, dtf.format(now)))
                {
                    PlacementCell.students.add(student);
                    System.out.println("Student Added Successfully!");
                }
            else
                {
                    System.out.println("Student Registration is Closed!");
                }
        }
        menu();
    }

    // <--------------------------------Student Choices------------------------------------------->
    
    public static void studentchoices(Student student)
    {
        System.out.println("\n<--------------------------------Student Choices------------------------------------------->\n");
        System.out.println("What would you like to do " + student.name + " :");
        System.out.println("1. Register For Placement Drive\n2. Register for Company\n3. Get All Available Companies\n4. Get Current Status\n5. Update CGPA\n6. Accept Offers\n7. Reject Offers\n8. Back");
        System.out.print("Input: ");
        
        int studentchoice = studentobj.nextInt();

        
        switch(studentchoice)
        {
            case 1:
                placementdrive(student);
                break;
            case 2:
                registerforcompany(student);
                break;
            case 3:
                availablecompanies(student);
                break;
            case 4:
                getcurrentstatus(student);
                break;
            case 5:
                updatecgpa(student);
                break;
            case 6:
                acceptoffers(student);
                break;
            case 7:
                rejectoffers(student);
                break;
            case 8:
                menu();
                break;
        }
    }

    // <--------------------------------Placement Drive------------------------------------------->

    public static void placementdrive(Student student)
    {
        System.out.println("\n<--------------------------------Placement Drive------------------------------------------->\n");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        LocalDateTime now = LocalDateTime.now();
        String current2 = dtf.format(now);
        if (PlacementCell.comparedatetime(current2, PlacementCell.companyopeningtime) && PlacementCell.comparedatetime(PlacementCell.companyclosingtime, current2))
        {
            System.out.println(current2);
            // System.out.println(dtf.format(now));
            System.out.println(student.name + " Registered for the Placment Drive at IIITD!");
            System.out.println("Your details are: ");
        
            System.out.println("Name: " + student.name + "\nRoll Number: " + student.rollnumber + "\nCGPA: " + student.cgpa + "\nBranch: " + student.branch + "\nStatus: " + student.status + "\n");

        }
        else
        {
            System.out.println("Student Registration is Closed!");
        }

        System.out.println("You have entered the Battlefield, God Speed!");
        
        studentchoices(student);
    }

    // <--------------------------------Register for Company------------------------------------------->

    public static void registerforcompany(Student student)
    {
        System.out.println("\n<--------------------------------Register for Company------------------------------------------->\n");
        Student studentMaxCTC = getMaxCTC(student);
        
        System.out.println("Number of companies to register in:");
        System.out.print("Input: ");
        studentobj.nextLine();
        int numberofcompanies = studentobj.nextInt();
    
        for (int k = 0; k < numberofcompanies; k++)
        {
            System.out.println("\nEnter the Company Name: ");
            System.out.print("Input: ");
            String company = studentobj.next();
            
            for (int i = 0; i < PlacementCell.companies.size(); i++)
            {
                if (PlacementCell.companies.get(i).name.equals(company))
                {
                    if (PlacementCell.companies.get(i).CTC >= 3*maximum_CTC && PlacementCell.companies.get(i).CGPA <= student.cgpa) 
                    {
                        System.out.println("Succesfully Registered for " + PlacementCell.companies.get(i).role + " role at " + company + "!" + "\n");
                        student.registeredcompanies.add(PlacementCell.companies.get(i));
                    }
                    else
                    {
                        System.out.println("Sorry, you are not eligible for this role either because CTC is not enough or CGPA.");
                    }
                }
            }
        }
        
        System.out.println("\nCompanies Registered for: ");
        System.out.println();
        for (int i = 0; i < student.registeredcompanies.size(); i++)
        {
            System.out.println("Company: " + student.registeredcompanies.get(i).name + "\nRole: " + student.registeredcompanies.get(i).role +  "\nPackage: " + student.registeredcompanies.get(i).CTC + "\n");
        }

        studentchoices(student);
    }

    // <--------------------------------Available Companies------------------------------------------->

    public static void availablecompanies(Student student)
    {
        System.out.println("\n<--------------------------------Available Companies------------------------------------------->\n");
        int count = 1;
        System.out.println("Following are the list of companies: ");
        System.out.println();
        Student studentMaxCTC = getMaxCTC(student);

        for (Company company : PlacementCell.companies) 
        {
            if (company.CGPA < student.cgpa && company.CTC >= 3*maximum_CTC)
            {
                System.out.println("Student's Status: ");
                System.out.println();
                if (student.status == 1) 
                {
                    System.out.println("Placed");
                    break;
                } 
                else if (student.status == 2)
                {
                    System.out.println("Not Placed");
                }
                else if (student.status == 3)
                {
                    System.out.println("Blocked");
                    break;
                }
                System.out.println(count + ". Name: " + company.name + "\n    Role: " + company.role + "\n    CTC: " + company.CTC + "\n    CGPA: "
                        + company.CGPA + "\n");
                count++;
            }
        }

        studentchoices(student);
    }
   
    // <--------------------------------Current Status------------------------------------------->

    public static void getcurrentstatus(Student student)
    {
        System.out.println("\n<--------------------------------Current Status------------------------------------------->\n\n");
        Student studentMaxCTC = getMaxCTC(student);
        
        if (student.status == 1)
        {
            System.out.println("You are already placed at " + student.placedCompany + "!");
        }
        else if (student.status == 2)
        {
            if (student.registeredcompanies.size() > 1)
            {
                for (int i = 0; i < student.registeredcompanies.size(); i++)
                {
                    if (student.registeredcompanies.get(i).CTC == maximum_CTC)
                    {
                        System.out.println("You have been offered by " + student.registeredcompanies.get(i).name + "!! Please accept the offer."); // Agar week 1 company he tabhi 
                        break;
                    }
                }
            }
            else
            {
                System.out.println("You have been offered by " + student.registeredcompanies.get(0).name + "!! Please accept the offer.");
            }
        }
        else if (student.status == 3)
        {
            System.out.println("You have been blocked!");
        }
        studentchoices(student);
    }

    // <--------------------------------Update CGPA------------------------------------------->

    public static void updatecgpa(Student student)
    {
        System.out.println("\n<--------------------------------Current CGPA------------------------------------------->\n");
        System.out.println("Enter the Updated CGPA: ");
        System.out.print("Input: ");
        
        float newcgpa = studentobj.nextFloat();
        PlacementCell.updatestudentcgpa(student, newcgpa);
        studentchoices(student);
    }

    // <--------------------------------Accept Offers------------------------------------------->

    public static void acceptoffers(Student student)
    {
        System.out.println("\n<--------------------------------Accept Offers------------------------------------------->\n");
        Student studentMaxCTC = getMaxCTC(student);

        for (int i = 0; i < student.registeredcompanies.size(); i++)
        {
            if (student.registeredcompanies.get(i).CTC == maximum_CTC)
            {
                System.out.println("Congrats " + student.name + "!!" + "You have accepted the offer by " + student.registeredcompanies.get(i).name + "!!" + "\n");
                student.placedCompany = student.registeredcompanies.get(i).name;
                break;
            }
        }
        student.status = 1;

        studentchoices(student);
    }

    // <--------------------------------Reject Offers------------------------------------------->

    public static void rejectoffers(Student student)
    {
        System.out.println("\n<--------------------------------Reject Offers------------------------------------------->\n");
        for (int i = 0; i < student.registeredcompanies.size(); i++)
        {
            System.out.println("Rejected offer by " + student.registeredcompanies.get(i).name + "!!" + "\n"); 
            System.out.println("Wow itna special he kya vai tu");
            student.registeredcompanies.remove(i);
        }
        if (student.registeredcompanies.size() == 0)
        {
            System.out.println("You are blocked"); // Aur karle reject, usne to kiya hi tha block ab you bhi F
            student.status = 3;
        }
        studentchoices(student);
    }

    // <--------------------------------main------------------------------------------->

    public static void main(String[] args)
    {
        menu();
    }

}

// <----------------------------------------End---------------------------------------------------->