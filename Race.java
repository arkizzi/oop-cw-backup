public class Race { //Race class which holds the name of the race and the date

    //Variable Declarations
    private String raceName;
    private String date;


    //Setters
    public String getRN(){
        return raceName;
    }
    public String getD(){
        return date;
    }

    //Constructor
    public Race(String rn, String dt)
    {
        raceName = rn;
        date = dt;
    }

    //Setters
    public void setRN(String name){
        raceName = name;
    }
    public void setD(String d){
        date = d;
    }
}
