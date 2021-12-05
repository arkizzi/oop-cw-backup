public class indvInfo { //specified for the 'search' option in the gui...holds the name of the race, date and the driver's position for each individual driver

    //Variable Declaration
    private String raceName;
    private String date;
    private int position;

    //Getters
    public String getRaceName() {
        return this.raceName;
    }

    public String getDate() {
        return this.date;
    }
    
    public int getPosition() {
        return this.position;
    }

    //Constructor
    public indvInfo(String rn, String d, int pos) {
        raceName = rn;
        date = d;
        position = pos;  
    }
    
    //Setters
    public void setRaceName(String raceName) {
        this.raceName = raceName;
    }


    public void setDate(String date) {
        this.date = date;
    }


    public void setPosition(int position) {
        this.position = position;
    }

}
