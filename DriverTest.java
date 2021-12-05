import java.util.Scanner; //scanner

public class DriverTest //initial test class for the Driver Class
{
    public static Scanner in = new Scanner(System.in); //setting scanner 
    public static Team[] DriversArray = new Team[2]; //test array to insert values into

    public static void main(String[] args) //main function which would call the initializer and "Stuff" which is where everything's implemented
    {
        initialize(DriversArray);
        Stuff(in);
    }

    private static void initialize(Team[] DriversArray) //Array initializer to make sure the values are set as "empty" or null
    { 
        DriversArray[1] = new Team("n", new Driver("n", "n", new Formula1Driver(0)));
    }

    public static void Stuff(Scanner in) 
    {
        System.out.print("Enter Driver Name: ");
        String nam = in.nextLine();
        System.out.print("Enter Driver Location: ");
        String loco = in.nextLine(); 
        System.out.print("Enter Driver's Team: ");
        String tea = in.nextLine();
        DriversArray[1].setTNA(tea); 
        System.out.print("Enter Driver Position: ");
        int inpt = Integer.parseInt(in.nextLine()); 
        DriversArray[1].setTDI(new Driver(nam, loco, (new Formula1Driver(inpt)))); 

        //test printing to check if it has gone through
        System.out.print("\n Driver Name: " + DriversArray[1].getTDI().getDN()); 
        System.out.print("Driver Location: " + DriversArray[1].getTDI().getDL()); 
        System.out.print("Driver Position: " + DriversArray[1].getTDI().getStats().getPos()); 
        System.out.print("Driver Points: " + DriversArray[1].getTDI().getStats().getPnt()); 
        System.out.print("Number of Races: " + DriversArray[1].getTDI().getStats().getNor()); 
    }
}
