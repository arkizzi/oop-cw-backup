import java.util.Scanner; //scanner

public class Formula1DriverTest //Initial Test class for the Drivers' statistics class
{
    public static Scanner in = new Scanner(System.in); //setting scanner 
    public static Formula1Driver[] TestArray = new Formula1Driver[2]; //test array to insert values into

    public static void main(String[] args) //main function which would call the initializer and "Stuff" which is where everything's implemented
    {
        initialise(TestArray);
        Stuff(in);
    }

    private static void initialise(Formula1Driver testtArray[]) //Array initializer to make sure the values are set as "empty" or null
    { 
        testtArray[1] = new Formula1Driver(0);
    }

    public static void Stuff(Scanner in) 
    {
        System.out.println("Enter Driver Position: ");
        int inpt = Integer.parseInt(in.nextLine()); 
        TestArray[1].setPos(inpt); //sets the driver name into the "pushPos" setter.
        System.out.println("Enter number of Races: ");
        int inpt1 = Integer.parseInt(in.nextLine());
        TestArray[1].setNor(inpt1); //sets the driver name into the "pushNor" setter.

        //for testing purpose t make sure everything has been passed proerly
        System.out.println("Driver Position: " + TestArray[1].getPos()); //gets the driver's position of the first array through the "pullPos" getter
        System.out.println("Driver Points: " + TestArray[1].getPnt()); //gets the driver's points of the first array through the "pullPnt" getter
        System.out.println("Number of Races: " + TestArray[1].getNor()); //gets the driver's number of races of the first array through the "pullNor" getter
    }
}
