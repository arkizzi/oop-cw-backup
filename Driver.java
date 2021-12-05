public class Driver { //Holds the Drivers' Information

    //Variable Declarations
    private String drvName; 
    private String drvLocation; 
    private Formula1Driver drvstatistics;

    //Getters
    public String getDN(){
        return drvName;
    }
    public String getDL(){
        return drvLocation;
    }
    public Formula1Driver getStats(){ 
        return drvstatistics;
    }

    //Constructor
    public Driver(String dn, String dl, Formula1Driver ds){
        drvName = dn;
        drvLocation = dl;
        drvstatistics = ds;
    }

    //Overloading Constructor 
    public Driver(String dn, String dl){
        drvName = dn;
        drvLocation = dl;
    }

    //Setters
    public void setDN(String name){
        drvName = name;
    }
    public void setDL(String loco){
        drvLocation = loco;
    }
    public void setStats(Formula1Driver stats){ 
        drvstatistics = stats;
    }
}
