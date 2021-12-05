public class Team { //Team class which holds all the drivers' information AND the team name

    //Variable Declarations
    private String name;
    private Driver drivInfo;

    //array which holds the positions, race name and date
    private indvInfo drvRaceInfo[] = new indvInfo[5]; //array made to store race info and position of EACH individual driver
    
    //Getters
    public String getTNa(){
        return name;
    }

    public Driver getTDI(){
        return drivInfo;
    }
    public indvInfo getRaceInfo(int x) { //array getter, uses a placeholder to get to the specific value
        return drvRaceInfo[x];
    }

    //Constructor
    public Team(String nam, Driver drInfo){
        name = nam;
        drivInfo = drInfo;
    }

    public void InitializerRace(){ //member function which intializes the driver info class for question 3's search feature

        for (int y = 0; y < 5; y++) {
            drvRaceInfo[y] = new indvInfo("e", "e", 0);
        }
        return;
    }

    //Setters
    public void setTNA(String nAme){
        name = nAme;
    }

    public void setTDI(Driver dI){
        drivInfo = dI;
    }
    public void setRaceInfo(int x, indvInfo y) { //array setter, get the number u want to grab first, and then gets the value to set it
        drvRaceInfo[x] = y;
    }

}