import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileInputStream; 

public class Formula1ChampionshipManager{
    //Initializing public Variables and Arrays
    public static Scanner in = new Scanner(System.in); 
    public static Team[] DriverArray = new Team[12]; //There can only be 12 racers max in a race
    public static Race[] RacesArray = new Race[5]; //There can only be 5 races in a Tournament
    public static int driverCounter = 0; //counts the drivers
    public static int racesCounter = 1; //counts the races

    public static void main(String[] args){ //Loading Main Functions
        initialise(DriverArray, RacesArray);
        loadFunction(); //auto load feature
        mainMenu(args);
    }

    private static void initialise(Team PosArray[], Race rArray[]){ //Initializer to set the values as null
        for (int n = 0; n < 12; n++){
            PosArray[n] = new Team("n", new Driver("n", "n", new Formula1Driver(0)));
            PosArray[n].InitializerRace();
        }
        for (int z=0; z < 5; z++){
            rArray[z] = new Race("z", "z");
        }
    }

    public static void mainMenu(String[] args){ //Main Menu which loads the Functions
        boolean checker = true;
        while (checker){
            System.out.print("\n" + "                   Formula 01 Championship Manager\n"
                    + "____________________________________________________________________\n" + "\n"
                    + "              1: Create A New Driver\n"
                    + "              2: Delete A Driver\n"
                    + "              3: Change the Driver in a Team\n"
                    + "              4: Show Driver Statistics\n"
                    + "              5: View all Drivers Table\n"
                    + "              6: Add Race\n"
                    + "              7: Store Program Data into file\n"
                    + "              8: Load GUI\n"
                    + "            999: Exit the Program\n" + "\n"
                    + "____________________________________________________________________\n" + "\n"
                    + "Type in an option you want to display: ");
            String inpt = in.nextLine();
            switch (inpt){
                case "1":
                    System.out.print("\n" + "'Create A New Driver' loaded!" + "\n");
                    addRacers();
                    break;
                case "2":
                    System.out.print("\n" + "'Delete A Driver' loaded!" + "\n");
                    removeRacers();
                    break;
                case "3":
                    System.out.print("\n" + "'Change Driver Team' loaded!" + "\n");
                    changeTeam();
                    break;
                case "4":
                    System.out.print("\n" + "'Show Driver Statistics' loaded!" + "\n");
                    showStats();
                    break;
                case "5":
                    System.out.print("\n" + "'View all Drivers Table' loaded!" + "\n");
                    driversTable();
                    break;
                case "6":
                    System.out.print("\n" + "'Add Race' loaded!" + "\n");
                    addRace();
                    break;
                case "7":
                    System.out.print("\n" + "'Store Program Data into file' loaded!" + "\n");
                    saveFunction();
                    break;
                case "8":
                    System.out.print("\n" + "'Load GUI' loaded!" + "\n");
                    ChampionshipManager.main(args);
                    break;
                case "999":
                    System.out.print("\n" + "Exiting program...." + "\n");
                    saveFunction(); //auto save feature
                    System.exit(0);
                default:
                    System.out.print("\n" + "'Wrong value entered!" + "\n");
                    break;
            }
        }
    }

    public static void addRacers(){ //Function to add the drivers
        if (driverCounter==12){
            System.out.print("\n All 12 Positions contains Drivers already! \n"); //Checks if all 12 slots are occupied
            return;
        }

        System.out.print("\n Enter the Driver's name : "); 
        String DrvdrvName = in.nextLine(); //get's the driver's name
        System.out.print("\n Enter Driver Location : "); 
        String DrvdrvLoco = in.nextLine(); //get's the driver's location

        String DrvdrvTeam = " ";
        Boolean uniqueTeam = true;
        while(uniqueTeam){ //Checks if the Team already has a Driver
            int check = 0;
            System.out.print("\n Enter Driver's Team : ");
            DrvdrvTeam = in.nextLine();
            for (int n =0; n < 12; n++){
                if (DriverArray[n].getTNa().equals(DrvdrvTeam.toLowerCase())){ //Sets everything to lowercase to catch typos
                    check++;
                }
            }    
            if (check > 0){ //the checker if there's an existing team even if there are typos
                System.out.print("\n A Driver has already been assigned to this Team! \n"); 
            }else{
                uniqueTeam = false;
            }
        }

        int DrvdrvPos = 0;
        Boolean posCount = true;
        while (posCount){ 
            System.out.print("\n Enter the Position the Driver was placed in the race : ");
            DrvdrvPos = Integer.parseInt(in.nextLine()); //get's the driver's position
            if (DrvdrvPos < 0 || DrvdrvPos > 12){
                System.out.print("Invalid Range!"); //makes sure that the positions are only within the range
            }else {
                posCount = false;
            }
        }
        for (int n = 0; n < 12; n++) {
            if (DriverArray[n].getTDI().getDN().equals("n")) {
                DriverArray[n] = new Team(DrvdrvTeam, new Driver(DrvdrvName, DrvdrvLoco, new Formula1Driver(DrvdrvPos))); //set's the driver into the immediate empty index 
                DriverArray[n].InitializerRace(); //initializes the info driver class
                DriverArray[n].setRaceInfo(0, new indvInfo("Trial Grand Prix", "21/02/21", DrvdrvPos)); //makes sure to add everything into thr info driver class
                driverCounter ++;
                System.out.print("\n Driver " + DriverArray[n].getTDI().getDN() + " of team " + DriverArray[n].getTNa() + " has been entered! \n"); //verifies that the values have been set properly
                break;
            }
        }
        if (driverCounter > 0){
            RacesArray[0] = new Race("Trial Grand Prix", "21/02/21"); //adds a race as a driver can not exist without a race
        }
    }

    public static void removeRacers(){ //Function to remove the drivers
        boolean checker = true;
        if (driverCounter == 0){
            System.out.print("\n You need to Add Drivers first before wanting to Delete them! \n"); //checker to check if there's atleast 1 driver
        }
        else{
            driverDisplay(); //shows the drivers avalilable
            while (checker){
                System.out.print("\n Enter the number you wish to remove the Driver from : " + "\n");
                int num = Integer.parseInt(in.nextLine());
                if (num < 12){
                    DriverArray[num] = new Team("n", new Driver("n", "n", new Formula1Driver(0))); //initializes the particular slot
                    DriverArray[num].InitializerRace(); //initializes everything in the info driver class as well
                    System.out.print("\n Driver placed " +  num +" has been removed successfully! \n");
                    checker = false;
                } 
                else{
                    System.out.print("Invalid Number Entered! \n");
                    checker = true;
                }
            }
        }
    }

    public static void changeTeam(){ //changes the driver for a particular team

        driverDisplay(); //shows the list of drivers

        boolean checker = true;
        if (driverCounter == 0){
            System.out.print("\n You need to Add Drivers first before wanting to swap Drivers for a Team! \n"); //Checks if there are Drivers Available to swap
        }
        else{
            while (checker){
                System.out.print("\n" + "\n Enter the number you wish to remove the Driver from : ");
                int num = Integer.parseInt(in.nextLine()); //asks for the team the user wants to remove the driver from
    
                System.out.print("\n" + "\n Enter the new Driver's name from number " +  DriverArray[num].getTNa() + " : "); 
                String DrvdrvName = in.nextLine(); //gets the driver's name
    
                if (num < 12){
                    System.out.print("\n Are you sure you want to replace" + DriverArray[num].getTDI().getDN() + "from the Team? \n"
                    + "\n [yes: yes/y no: no/n] : "); 
                    String yesOrno = in.nextLine();  //double verifies if they really want to remove the driver
                    
                    switch (yesOrno){ //double verification
                        case "yes":
                        case "y":
                            DriverArray[num].getTDI().setDN(DrvdrvName); //replaces the existing driver name with the new driver
                            System.out.print("\n" + DriverArray[num].getTDI().getDN() + " has been added successfully! \n");
                            checker = false;
                        break;
                        
                        case "no":
                        case "n":
                            checker = false; 
                        break;
                        
                        default:
                            System.out.print("\n" + "Wrong value entered!" + "\n");
                            checker = true;
                        break;
                    }
                } 
                else{
                    System.out.print("Invalid Number Entered!");
                }
            }
        }
    }
    
    public static void showStats(){ //Displays the statistics of the desired driver
        driverDisplay();

        if (driverCounter == 0){
            System.out.print("\n You need to Add Drivers first before wanting to check their Statistics \n"); //checker to check if there's atleast 1 driver
        }
        else{
            boolean checker = true;
            while (checker){
                System.out.print("\n" + "\n Enter the  number of the Driver you want to view : ");
                int posNum = Integer.parseInt(in.nextLine()); //asks for the driver's number to show stats
                if (posNum < 12){
                    moreDriverInfo(posNum); //function which loads the statistics
                    checker = false;
                } else{
                    System.out.print("Invalid Number Entered!");
                    checker = true;
                }
            }
        }
    }

    public static void addRace(){ //Adds a race to the championship
        int tempPosDrv = 0;
        for (int z = 0; z < 5; z++){
            if (!RacesArray[z].getRN().equals("z")){ //Displays all of the existing races
                System.out.print("\n Race Number " + z + " : " + RacesArray[z].getRN() + " || Date : " + RacesArray[z].getD() + " \n");
            }    
        }
        if (racesCounter == 5){
            System.out.print("\n All Four Races have been Compleated! \n"); //Checker to see if all the slots have been added to the race
        }else if (driverCounter < 3){
            System.out.print("\n You need to Add more Drivers first before wanting to add a Race! \n"); //The user can not add a race until there are atleast three drivers
        }   
        else{
                System.out.print("\n Enter the Race's name : "); 
                String rcrcName = in.nextLine(); //asks for the name of the race
                System.out.print("\n Enter the Date it was held on [dd/mm/yy]: "); 
                String datedate = in.nextLine(); //asks for the date
                //positions
                for (int n = 0; n < 12; n++){
                    if (!DriverArray[n].getTDI().getDN().equals("n")){
                        System.out.print("\n What position did Driver " + DriverArray[n].getTDI().getDN() //filters through the racers array
                        + " take? \n");
                        tempPosDrv = Integer.parseInt(in.nextLine()); //gets the position of each driver again
                        DriverArray[n].getTDI().getStats().setPos(tempPosDrv);
                        for (int y = 0; y < 5; y++) {
                            if (DriverArray[n].getRaceInfo(y).getRaceName().equals("e")) {
                            DriverArray[n].setRaceInfo(y, new indvInfo(rcrcName, datedate, tempPosDrv)); //updates the race class
                            break;
                            }
                        }
                    }
                }
    
                for (int n = 0; n < 5; n++) {
                if (RacesArray[n].getRN().equals("z")) {
                    RacesArray[n] = new Race(rcrcName, datedate);
                    System.out.print("\n Race '" + RacesArray[n].getRN() + "' which was on " + RacesArray[n].getD() + " has been entered! \n"); //double verifies that it has been added
                    racesCounter++;
                    break;
                }
            }
        }
    }

    public static void driversTable(){ //Displays the Drivers table which consists of all of the records

        if (driverCounter == 0){
            System.out.print("\n You need to Add Drivers first before wanting to Display the Table \n"); //Checks if there are Drivers Available
        } else {
            Team[] BackupDriverArray = new Team[12]; 
            for (int x=0; x<12; x++){
                BackupDriverArray[x] = DriverArray[x]; //clones the array into a backup array to not tweak with the original array's values
            }

            for (int n = 0; n < 11; n++) {         
                for (int m = 0; m < 11 - n; m++) {
                    if (BackupDriverArray[m].getTDI().getStats().getPnt() < BackupDriverArray[m + 1].getTDI().getStats().getPnt()) { //bubble sort (https://www.geeksforgeeks.org/bubble-sort/)
                        Team tempHold = BackupDriverArray[m];
                        BackupDriverArray[m] = BackupDriverArray[m + 1];
                        BackupDriverArray[m + 1] = tempHold;
                    }
                }
            }
            for (int n = 0; n <11; n++) {        
                for (int m = 0; m < 11 - n; m++) {
                    if (BackupDriverArray[m].getTDI().getStats().getPnt() == BackupDriverArray[m + 1].getTDI().getStats().getPnt()) { //bubble sort
                        if (BackupDriverArray[m].getTDI().getStats().getF() < BackupDriverArray[m + 1].getTDI().getStats().getF()) {
                            Team tempHold = BackupDriverArray[m];
                            BackupDriverArray[m] = BackupDriverArray[m + 1];
                            BackupDriverArray[m + 1] = tempHold;
                        }
                    }
                }
            }

            String TF = "\n| %-8d | %-16s | %-18s | %-14d | %-16d | %-10d |"; //table constructed with ASCII (https://stackoverflow.com/questions/15215326/how-can-i-create-table-using-ascii-in-a-console)
            System.out.print("\n┌───────────────────────────────────────────────────────────────────────────────────────────────────┐");
            System.out.print("\n|                                       Formula 1 Driver Table                                      |");
            System.out.print("\n|---------------------------------------------------------------------------------------------------|");
            System.out.print("\n| Position |       Team       |    Driver Name     |  No. of Races  | No. of 1st Place |   Points   |");
            System.out.print("\n|---------------------------------------------------------------------------------------------------|");
            for (int x = 0; x < 12; x++){
                if (!BackupDriverArray[x].getTNa().equals("n")){
                    System.out.printf(TF,BackupDriverArray[x].getTDI().getStats().getPos(), BackupDriverArray[x].getTNa(), BackupDriverArray[x].getTDI().getDN(), BackupDriverArray[x].getTDI().getStats().getNor(), 
                    BackupDriverArray[x].getTDI().getStats().getF(), BackupDriverArray[x].getTDI().getStats().getPnt());
                }
            }
            System.out.print("\n└───────────────────────────────────────────────────────────────────────────────────────────────────┘");
        }
    }

    public static void saveFunction(){ //Saves the values into a txt file

        try (BufferedWriter in = new BufferedWriter(new FileWriter("DRIVERSAVEDATA.txt"))) { //sotores all of the info in a txt file (https://www.codejava.net/java-se/file-io/how-to-read-and-write-text-file-in-java)
        in.write(String.valueOf(driverCounter)); //drivers count
        in.newLine();
        in.write(String.valueOf(racesCounter)); //races count
        in.newLine();
        
        for (int x = 0; x < 12; x++) { //Values in the Teams Class
            in.write(DriverArray[x].getTNa());
            in.newLine();
            in.write(DriverArray[x].getTDI().getDN());
            in.newLine();
            in.write(DriverArray[x].getTDI().getDL());
            in.newLine();
            in.write(String.valueOf(DriverArray[x].getTDI().getStats().getPos()));
            in.newLine();
            in.write(String.valueOf(DriverArray[x].getTDI().getStats().getPnt()));
            in.newLine();
            in.write(String.valueOf(DriverArray[x].getTDI().getStats().getNor()));
            in.newLine();
            in.write(String.valueOf(DriverArray[x].getTDI().getStats().getF()));
            in.newLine();
            in.write(String.valueOf(DriverArray[x].getTDI().getStats().getS()));
            in.newLine();
            in.write(String.valueOf(DriverArray[x].getTDI().getStats().getT()));
            in.newLine();

            for (int y = 0; y < 5; y++){ //Values in the Drivers' Individual Info Class
                in.write(DriverArray[x].getRaceInfo(y).getRaceName());  
                in.newLine();
                in.write(DriverArray[x].getRaceInfo(y).getDate());          
                in.newLine();
                in.write(String.valueOf(DriverArray[x].getRaceInfo(y).getPosition())); 
                in.newLine();
            }
        }
    } catch (IOException y){
        y.printStackTrace(); 
    }
    try (BufferedWriter in = new BufferedWriter(new FileWriter("RACESAVEDATA.txt"))){ //sets another txt file for the races info
 
        for (int x = 0; x < 5; x++){  //Values in the Races Class
            in.write(RacesArray[x].getRN());
            in.newLine(); 
            in.write(RacesArray[x].getD());
            in.newLine();
        }
    } catch (IOException y){
        y.printStackTrace(); 
    }
    System.out.println("Saved Data into the files!"); //Double Verification
    }

    public static void loadFunction(){ //Loads the values into a txt file
        try {
            Scanner pull = new Scanner(new FileInputStream("DRIVERSAVEDATA.txt")); //loading txt file by pulling each value out
            driverCounter = Integer.parseInt(pull.nextLine()); //drivers count
            racesCounter = Integer.parseInt(pull.nextLine()); //races count
            for (int x = 0; x < 12; x++) { //Values in the Teams Class
                String teamName = pull.nextLine();
                String driverName = pull.nextLine();
                String driverLoco = pull.nextLine();

                int driverPos = Integer.parseInt(pull.nextLine());
                int driverPoints = Integer.parseInt(pull.nextLine());
                int driverNoRaces = Integer.parseInt(pull.nextLine());

                int driverFirst = Integer.parseInt(pull.nextLine());
                int driverSecond = Integer.parseInt(pull.nextLine());
                int driverThird = Integer.parseInt(pull.nextLine());

                DriverArray[x] = new Team(teamName, new Driver(driverName, driverLoco));
                DriverArray[x].getTDI().setStats(new Formula1Driver(driverPos, driverPoints, driverNoRaces, driverFirst, driverSecond, driverThird));

                for (int y = 0; y < 5; y++){  //Values in the Drivers' Individual Info Class

                    String rn = pull.nextLine(); 
                    String date = pull.nextLine(); 
                    int pos = Integer.parseInt(pull.nextLine());

                    DriverArray[x].setRaceInfo(y,new indvInfo(rn, date, pos));
                }
            }
        } catch (IOException y){
        }
        try{
            Scanner pull = new Scanner(new FileInputStream("RACESAVEDATA.txt")); //Loads another txt file for the races info
            for (int x = 0; x < 5; x++){ //Values in the Races Class
                String raceName = pull.nextLine();
                String raceDate = pull.nextLine();
                RacesArray[x] = new Race(raceName, raceDate);
            }
        } catch (IOException y){
        }
    }
    
    public static String[][] loadGUI() { //Loads the Gui 
        //cloning team array
        Team[] BackupDriverArray = new Team[12]; 
        for (int x=0; x<12; x++){
            BackupDriverArray[x] = DriverArray[x];
        }

        String row[][] = new String[driverCounter][9]; //generating a 2d array
        for (int x = 0; x < driverCounter; x++) {  //grabs all of the driver info needed
            if (!BackupDriverArray[x].getTNa().equals("n")) {
                String drvNam = BackupDriverArray[x].getTDI().getDN();
                row[x][0] = drvNam;
                String TNa = BackupDriverArray[x].getTNa();
                row[x][1] = TNa;
                String Loco = BackupDriverArray[x].getTDI().getDL();
                row[x][2] = Loco;
                String Pos = String.valueOf(BackupDriverArray[x].getTDI().getStats().getPos());
                row[x][3] = Pos;
                String numRc = String.valueOf(BackupDriverArray[x].getTDI().getStats().getNor());
                row[x][4] = numRc;
                String numPnt = String.valueOf(BackupDriverArray[x].getTDI().getStats().getPnt());
                row[x][5] = numPnt;
                String F = String.valueOf(BackupDriverArray[x].getTDI().getStats().getF());
                row[x][6] = F;
                String S = String.valueOf(BackupDriverArray[x].getTDI().getStats().getS());
                row[x][7] = S;
                String T = String.valueOf(BackupDriverArray[x].getTDI().getStats().getT());
                row[x][8] = T;
            }
        }

        return row;
    }
//------------------------------------------------------------------------EXTRA STUFF-----------------------------------------------------------------------------

    public static String[][] racesForGUI(){
        //cloning race array
        Race[] BackupRacesArray = new Race[5]; 
        for (int x=0; x<5; x++){
            BackupRacesArray[x] = RacesArray[x];
        }

        String races[][] = new String[5][2]; //generating a 2d array
        for (int x = 0; x < 5; x++) {  //grabs all of the races info needed
            if (!BackupRacesArray[x].getRN().equals("z")) {
                String drvNam = BackupRacesArray[x].getRN();
                races[x][0] = drvNam;
                String date = BackupRacesArray[x].getD();
                races[x][1] = date;
            }
        }
        return races;
    }

    public static void driverDisplay(){
        driverCounter = 0;
        for (int n = 0; n < 12; n++){
            
            if (!DriverArray[n].getTDI().getDN().equals("n")){
                System.out.print("\n" +n + ")Driver " + DriverArray[n].getTDI().getDN() + " of the " + DriverArray[n].getTNa() + " Team" + "\n");
                driverCounter ++;
            }    
        }
    }

    public static void moreDriverInfo(int m){
        String TFS = "\n| %-25s | %-22s |";
        String TFD = "\n| %-25s | %-22d |";
        System.out.print("┌────────────────────────────────────────────────────┐");
        System.out.print("\n|                    Driver No. "+m+"                    |");
        System.out.print("\n|----------------------------------------------------|");
        System.out.printf(TFS, "Name                    ",DriverArray[m].getTDI().getDN());
        System.out.printf(TFS, "Team                    ",DriverArray[m].getTNa());
        System.out.printf(TFS, "Location                ",DriverArray[m].getTDI().getDL());
        System.out.print("\n|----------------------------------------------------|");
        System.out.printf(TFD, "No. of Races Driven     ",DriverArray[m].getTDI().getStats().getNor());
        System.out.printf(TFD, "Total Points Collected  ",DriverArray[m].getTDI().getStats().getPnt());
        System.out.print("\n|----------------------------------------------------|");
        System.out.printf(TFD, "No. of 1st Place        ",DriverArray[m].getTDI().getStats().getF());
        System.out.printf(TFD, "No. of 2nd Place        ",DriverArray[m].getTDI().getStats().getS());
        System.out.printf(TFD, "No. of 3rd Place        ",DriverArray[m].getTDI().getStats().getT());
        System.out.printf("\n└────────────────────────────────────────────────────┘");
    }
}