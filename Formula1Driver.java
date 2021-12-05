public class Formula1Driver { //Statistics class which stores all of the Drivers' statistics

    //Variable Declarations
    private int position;
    private int points;
    private int noRaces;
    private int first = 0;
    private int second = 0;
    private int third = 0;
    
    //Getters
    public int getPos(){
        return position;
    }
    public int getPnt(){
        return points;
    }
    public int getNor(){
        return noRaces;
    }
    public int getF(){
        return first;
    }
    public int getS(){
        return second;
    }
    public int getT(){
        return third;
    }

    //Ccnstructor
    public Formula1Driver(int pos){
        position = pos;

        switch (position){ //makes sure to filter through the positions
            case 1:
                points += 25; //adds the specific number to the points
                noRaces++;    //increases the number of races
                first++;      //the number of first places (second and third for the specific values)
                break;
            case 2:
                points += 18;
                noRaces++;
                second++;
                break;
            case 3:
                points += 15;
                noRaces++;
                third++;
                break;
            case 4:
                points += 12;
                noRaces++;
                break;
            case 5:
                points += 10;
                noRaces++;
                break;
            case 6:
                points += 8;
                noRaces++;
                break;
            case 7:
                points += 6;
                noRaces++;
                break;
            case 8:
                points += 4;
                noRaces++;
                break;
            case 9:
                points += 2;
                noRaces++;
                break;
            case 10:
                points += 1;
                noRaces++;
                break;
            default:
                points += 0;
                noRaces++;
                break;
        }
    }

    //Overloading Constructor 
    public Formula1Driver(int pos, int pnt, int nor, int f, int s, int t){
        position = pos;
        points = pnt;
        noRaces = nor;
        first = f;
        second = s;
        third = t;
    }

    //member function
    public void setPos(int Posi){
        position = Posi;

        switch (position){
            case 1:
                points += 25;
                noRaces++;
                first++;
                break;
            case 2:
                points += 18;
                noRaces++;
                second++;
                break;
            case 3:
                points += 15;
                noRaces++;
                third++;
                break;
            case 4:
                points += 12;
                noRaces++;
                break;
            case 5:
                points += 10;
                noRaces++;
                break;
            case 6:
                points += 8;
                noRaces++;
                break;
            case 7:
                points += 6;
                noRaces++;
                break;
            case 8:
                points += 4;
                noRaces++;
                break;
            case 9:
                points += 2;
                noRaces++;
                break;
            case 10:
                points += 1;
                noRaces++;
                break;
            default:
                points += 0;
                noRaces++;
                break;
        }
    }
    //Setters 
    public void setPnt(int poin){
        points += poin;
    }
    public void setNor(int numRaces){
        noRaces += numRaces;
    }
    public void setF(int f){
        first += f;
    }
    public void setS(int s){
        second += s;
    }
    public void setT(int t){
        third += t;
    }
}
