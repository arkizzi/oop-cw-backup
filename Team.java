public class Team { //Team class which holds all the drivers' information AND the team name

    //Variable Declarations
    private String name;
    private Driver drivInfo;
    private int firstPos;
    private int lastPos;

    private int limit =Formula1ChampionshipManager.RacesArray.length;
    //array which holds the positions, race name and date
    private indvInfo drvRaceInfo[] = new indvInfo[limit]; //array made to store race info and position of EACH individual driver
    
    //Getters
    public String getTNa(){
        return name;
    }

    public Driver getTDI(){
        return drivInfo;
    }
    public int getfirstPos(){
        return firstPos;
    }
    public int getlastPos(){
        return lastPos;
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

        for (int y = 0; y < limit; y++) {
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
    public void setfirstPos(int a){
        firstPos = a;
    }
    public void setlastPos(int b){
        lastPos = b;
    }
    public void setRaceInfo(int x, indvInfo y) { //array setter, get the number u want to grab first, and then gets the value to set it
        drvRaceInfo[x] = y;
    }

}
