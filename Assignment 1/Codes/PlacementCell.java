// Imports
import java.util.*;

/*
PlacementCellFunctionality
{
    OpenStudentRegistration();
    OpenCompanyRegistration();
    NumberOfStudentsRegistrations();
    NumberOfCompanyRegistrations();
    NumberofOffered/Unoffered/BlockedStudents();
    StudentDetails();
    CompanyDetails();
    AveragePackage();
    CompanyProcessResults();
}
*/

// <----------------------------------------PlacementCell---------------------------------------------------->

public class PlacementCell 
{
    static boolean companyreg = false;

    static String studentopeningtime;
    static String studentclosingtime;
    static String companyopeningtime;
    static String companyclosingtime;

    static ArrayList<Student> students = new ArrayList<Student>();
    static ArrayList<Company> companies = new ArrayList<Company>();

    static Scanner placementcellchoiceobj = new Scanner(System.in);

    // <--------------------------------DateTime helper------------------------------------------->

    // Damn, it was so hard to implement this comparison thing, spent hours working on the built in library instead of just one myself.
    public static boolean comparedatetime(String string1, String string2)
    {
        String[] split1 = string1.split(" ");
        String[] split2 = string2.split(" ");
        String[] date1 = split1[0].split("-");
        String[] date2 = split2[0].split("-");
        String[] time1 = split1[1].split(":");
        String[] time2 = split2[1].split(":");
        int[] date1int = new int[3];
        int[] time1int = new int[2];
        int[] date2int = new int[3];
        int[] time2int = new int[2];

        for (int i = 0; i < date1.length; i++)
        {
            date1int[i] = Integer.parseInt(date1[i]);
        }
        for (int i = 0; i < date2.length; i++)
        {
            date2int[i] = Integer.parseInt(date2[i]);
        }
        for (int i = 0; i < time1.length; i++)
        {
            time1int[i] = Integer.parseInt(time1[i]);
        }
        for (int i = 0; i < time2.length; i++)
        {
            time2int[i] = Integer.parseInt(time2[i]);
        }

        // compare the dates and times

        if (date1int[2] > date2int[2])
        {
            return true;
        }
        else if (date1int[2] == date2int[2])
        {
            if (date1int[1] > date2int[1])
            {
                return true;
            }
            else if (date1int[1] == date2int[1])
            {
                if (date1int[0] > date2int[0])
                {
                    return true;
                }
                else if (date1int[0] == date2int[0])
                {
                    if (time1int[0] > time2int[0])
                    {
                        return true;
                    }
                    else if (time1int[0] == time2int[0])
                    {
                        if (time1int[1] > time2int[1])
                        {
                            return true;
                        }
                        else if (time1int[1] == time2int[1])
                        {
                            return true;
                        }
                        else
                        {
                            return false;
                        }
                    }
                    else
                    {
                        return false;
                    }
                }
                else
                {
                    return false;
                }
            }
            else
            {
                return false;
            }
        }
        else
        {
            return false;
        }
    }

    // <--------------------------------Student CGPA updater------------------------------------------->

    public static void updatestudentcgpa(Student student, float newcgpa)
    {    
        student.cgpa = newcgpa;
    }

    // <--------------------------------Menu------------------------------------------->

    public static void menu()
    {
        System.out.println("\n<--------------------------------Menu------------------------------------------->\n");   
        System.out.println("Welcome to IIITD Placement Cell");
        System.out.println("1. Open Student Registration\n2. Open Company Registration\n3. Get Number of Student Registrations\n4. Get Number of Company Registrations\n5. Get Number of Offered/Unoffered/Blocked Students\n6. Get Student Details\n7. Get Company Details\n8. Get Average Package\n9. Get Company Process Results\n10. Back");
        System.out.print("Input: ");
        int placementcellchoice = placementcellchoiceobj.nextInt();

        switch(placementcellchoice)
        {
            case 1:
            if (companyreg == true)
            {
                OpenStudentRegistration();
                break;
            }
            else
            {
                System.out.println("Company Registration is not open yet");
                menu();
                break;
            }
            case 2:
                OpenCompanyRegistration();
                break;
            case 3:
                NumberOfStudentsRegistrations();
                break;
            case 4:
                NumberofCompanyRegistrations();
                break;
            case 5:
                OUBStudents();
                break;
            case 6:
                StudentDetails();
                break;
            case 7:
                CompanyDetails();
                break;
            case 8:
                AveragePackage();
                break;
            case 9:
                System.out.println("\nEnter the Company Name: ");
                System.out.print("Input: ");
                String companyname = placementcellchoiceobj.next();
                CompanyProcessResults(companyname);
                break;
            case 10:
                Main.EnterApplication();
                break;
            default:
                System.out.println("Invalid Input, Try again: ");
                break;

        }
    }

    // <--------------------------------Open Student Registration------------------------------------------->

    public static void OpenStudentRegistration()
    {
        System.out.println("\n<--------------------------------Open Student Registration------------------------------------------->\n");
        System.out.println("1. Set the Opening time for student registrations\n2. Set the Closing time for student registrations");
        placementcellchoiceobj.nextLine();
        System.out.print("Input: ");
        studentopeningtime = placementcellchoiceobj.nextLine();
        // Format is dd-mm-yyyy hh:mm 
        System.out.println("Opening Time: " + studentopeningtime);
        System.out.print("Input: ");
        studentclosingtime = placementcellchoiceobj.nextLine();
        // Format is dd-mm-yyyy hh:mm 
        System.out.println("Closing Time: " + studentclosingtime);
        
        menu();
    }
    
    // <--------------------------------Open Company Registration------------------------------------------->
    
    public static void OpenCompanyRegistration()
    {
        System.out.println("\n<--------------------------------Open Company Registration------------------------------------------->\n");
        System.out.println("1. Set the Opening time for company registrations\n2. Set the Closing time for company registrations");
        
        placementcellchoiceobj.nextLine();
        System.out.print("Input: ");
        companyopeningtime = placementcellchoiceobj.nextLine();
        // Format is dd-mm-yyyy hh:mm 
        System.out.println("Opening Time: " + companyopeningtime);
        System.out.print("Input: ");
        companyclosingtime = placementcellchoiceobj.nextLine();
        // Format is dd-mm-yyyy hh:mm 
        System.out.println("Closing Time: " + companyclosingtime);
        companyreg = true;
        menu();
    }

    // <--------------------------------Number of Students Registrations------------------------------------------->

    public static void NumberOfStudentsRegistrations()
    {
        System.out.println("\n<--------------------------------Number of Students Registrations------------------------------------------->\n");
        System.out.println("Number of Students Registered: ");
        System.out.println(students.size());
        menu();
    }

    // <--------------------------------Number of Company Registrations------------------------------------------->

    public static void NumberofCompanyRegistrations()
    {
        System.out.println("\n<--------------------------------Number of Company Registrations------------------------------------------->\n");
        System.out.println("Number of Companies Registered: ");
        System.out.println(companies.size());
        menu();
    }

    // <--------------------------------Number of Offered/Unoffered/Blocked Students------------------------------------------->

    public static void OUBStudents()
    {
        System.out.println("\n<--------------------------------Number of Offered/Unoffered/Blocked Students------------------------------------------->\n");
        int offered = 0;
        int unoffered = 0;
        int blocked = 0;

        for (int i = 0 ; i < students.size(); i++)
        {
            if (students.get(i).status == 3)
            {
                blocked++;
                unoffered--;
                offered--;
            }
            else if (students.get(i).status == 1)
            {
                offered++;
                unoffered--;

            }
            else if (students.get(i).status == 2)
            {
                unoffered++;
            }
        }

        System.out.println("Number of Offered Students: " + offered);
        System.out.println("Number of Unoffered Students: " + unoffered + "\n Hi berozgaaro"); // Most of the batch 
        System.out.println("Number of Blocked Students: " + blocked + "\n Hahaha Loser"); // Oops, not you Priyansh :P
        menu();
    }

    // <--------------------------------Student Details------------------------------------------->

    public static void StudentDetails()
    {
        System.out.println("\n<--------------------------------Student Details------------------------------------------->\n");
        System.out.println("\nFollowing are the Students: \n");
        for (int i = 0; i < students.size(); i++)
        {
            System.out.println("Name: " + students.get(i).name + "\nRoll Number: " + students.get(i).rollnumber + "\nCGPA: " + students.get(i).cgpa + "\nBranch: " + students.get(i).branch + "\n");
        }
        menu();
    }

    // <--------------------------------Company Details------------------------------------------->

    public static void CompanyDetails()
    {
        System.out.println("\n<--------------------------------Company Details------------------------------------------->\n");
        System.out.println("\nFollowing are the Companies: \n");
        for (int i = 0; i < companies.size(); i++)
        {
            System.out.println("Name: " + companies.get(i).name + "\nRole: " + companies.get(i).role + "\nCTC: " +companies.get(i).CTC + "\nCGPA: " + companies.get(i).CGPA + "\n");
        }
        menu();
    }

    // <--------------------------------Average Package------------------------------------------->

    public static void AveragePackage()
    {
        System.out.println("\n<--------------------------------Average Package------------------------------------------->\n");
        System.out.println("Average Package: ");
        int sum = 0;
        for (int i = 0; i < companies.size(); i++)
        {
            sum += companies.get(i).CTC;
        }
        System.out.println(sum/companies.size());
        System.out.println("That's pretty neat ngl, chalo IIITD me aane ka koi to fayda hua"); // Not really, mehnat karke IITD chale jaate
        menu();
    }

    // <--------------------------------Company Process Results------------------------------------------->

    public static void CompanyProcessResults(String companyname)
    {
        System.out.println("\n<--------------------------------Company Process Results------------------------------------------->\n");
        int index = 1;
        System.out.println("Following are the students selected:");
        
        
        for (int i = 0; i < students.size(); i++)
        {
            for (int j = 0; j < students.get(i).registeredcompanies.size(); j++)
            {
                if (students.get(i).registeredcompanies.get(j).name.equals(companyname))
                {
                    System.out.println(index + ". Name: " + students.get(i).name + "\nRoll Number: " + students.get(i).rollnumber + "\nCGPA: " + students.get(i).cgpa + "\nBranch: " + students.get(i).branch + "\n");
                    index++;
                    break;
                }
            }
        }

        System.out.println("Party when?"); // Now you can finally "enjoy" college, hahaha no you can't

        System.out.println();

        menu();
    }

    // <--------------------------------main------------------------------------------->

    public static void main(String[] args) 
    {
        menu();
    }
}

// <----------------------------------------End---------------------------------------------------->