import java.awt.*;
import java.awt.event.*;  
import javax.swing.*;
import java.util.Random;    


public class ChampionshipManager extends Formula1ChampionshipManager{

    public static Team[] BackupDriverArray = new Team[DriverArray.length]; 

    //main panel [whole template referanced from : (https://stackhowto.com/how-to-update-a-row-in-jtable/)]
    private JPanel mainPanel = new JPanel(new BorderLayout());

    //Table
    String row[][] = Formula1ChampionshipManager.loadGUI();
    String races[][] = Formula1ChampionshipManager.racesForGUI();

    //Driver Name Initializer
    public String drvName = "Driver";

    public ChampionshipManager(){
        String column[]={"Name","Team", "Location", "Last Race Postions", "Num Of Races", "Points", "Third Places","Second Places","First Places"}; //initializing columns
        String columnTwo[] = {"Race Name", "Race Date"};

        //Tables
        JTable Table = new JTable(row,column);
        Table.setBounds(30,40,500,300);

        JTable TableTwo = new JTable(races,columnTwo);
        TableTwo.setBounds(30,40,500,300);

        //Sorting referanced (https://stackhowto.com/how-to-sort-jtable-column-in-java-2-methods/)
        Table.setAutoCreateRowSorter(true);
        TableTwo.setAutoCreateRowSorter(true);

        //Text Fields  
        JTextField tf1 = new JTextField();  
        tf1.setColumns(50); //resize feild (https://stackoverflow.com/questions/14805124/how-to-set-the-height-and-the-width-of-a-textfield-in-java)
        tf1.setBounds(50,100, 50,30);   

        //TextArea
        JTextArea txtBox = new JTextArea("");
        JScrollPane scroll = new JScrollPane(txtBox);
        txtBox.setLineWrap(true);
        scroll.setBounds(320, 100, 800,200);  

        //Buttons
        JButton buttonOne = new JButton("Randomize");  
        JButton buttonTwo = new JButton("Clear");
        JButton buttonThree = new JButton("%-Randomizer");
        JButton buttonFour = new JButton("Search Button");

        //Lables
        JLabel lableOne = new JLabel("                                                      Races Info Table");
        JLabel lableTwo = new JLabel("                                                                                          Drivers Info Table");
        JLabel lableThree = new JLabel("                                    Information Box");

        //action listners
        buttonOne.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                int temp = 0;  
                String RNTemp = "";
                String RDTemp = "";
                String numRc = ""; 
                if ( racesCounter > (RacesArray.length - 1)) {
                    tf1.setText("YOU CANNOT ADD MORE RACES");
                } else{

                    for (int x = 0; x < RacesArray.length; x++) {  //generates totally random and new races!
                        if (RacesArray[x].getRN().equals("z")) {
                            RNTemp = "Grand Prix " + String.valueOf(racesCounter); 
                            RacesArray[x].setRN(RNTemp);
                            races[x][0] = RNTemp;
                            RDTemp = "0" + String.valueOf(racesCounter) + "/01/22";  
                            RacesArray[x].setD(RDTemp);  
                            races[x][1] = RDTemp;
                            racesCounter++;
                            break;
                        }
                    }  
                    rand(); //randomizer function

                    for (int x=0; x<DriverArray.length; x++){
                        BackupDriverArray[x].getTDI().getStats().setPos(BackupDriverArray[x].getlastPos()); //sets value into the position slot 
                    }

                    for (int x = 0; x < driverCounter; x++) {  //randomizes the particular column
                        temp = DriverArray[x].getTDI().getStats().getPos();
                        row[x][3] = String.valueOf(temp);
                        numRc = String.valueOf(DriverArray[x].getTDI().getStats().getNor());
                        row[x][4] = numRc;
                        String numPnt;

                        if (BackupDriverArray[x].getTDI().getStats().getPnt() > 99){
                            numPnt = String.valueOf(BackupDriverArray[x].getTDI().getStats().getPnt());
                        }else if (BackupDriverArray[x].getTDI().getStats().getPnt() > 9) {
                            numPnt = "0"+String.valueOf(BackupDriverArray[x].getTDI().getStats().getPnt());
                        }else{
                            numPnt = "00"+String.valueOf(BackupDriverArray[x].getTDI().getStats().getPnt());
                        }

                        row[x][5] = numPnt;
                        String F = String.valueOf(DriverArray[x].getTDI().getStats().getF());
                        row[x][6] = F;
                        String S = String.valueOf(DriverArray[x].getTDI().getStats().getS());
                        row[x][7] = S;
                        String T = String.valueOf(DriverArray[x].getTDI().getStats().getT());
                        row[x][8] = T;

                        for (int y = 0; y < RacesArray.length; y++) {
                            if (DriverArray[x].getRaceInfo(y).getRaceName().equals("e")) {
                                DriverArray[x].setRaceInfo(y, new indvInfo(RNTemp, RDTemp, temp)); 
                                break;
                            }
                        }
                        
                    }        
                }
                Table.repaint(); //refreshes table
                TableTwo.repaint();
                return;
            }  
        });
        buttonTwo.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                tf1.setText(""); //clears text
            }  
        }); 
        buttonThree.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                int firstCount = 0;
                int drvCount = 0;
                String RNTemp = "";
                String RDTemp = "";
                String numRc = ""; 
                String tempString = "";
                double tempDouble =  0.0;
                if ( racesCounter > (RacesArray.length - 1)) {
                    tf1.setText("YOU CANNOT ADD MORE RACES");
                } else{
                    for (int x = 0; x < RacesArray.length; x++) {  
                        if (RacesArray[x].getRN().equals("z")) {
                            RNTemp = "Grand Prix " + String.valueOf(racesCounter); 
                            RacesArray[x].setRN(RNTemp);
                            races[x][0] = RNTemp;
                            RDTemp = "0" + String.valueOf(racesCounter) + "/01/22";  
                            RacesArray[x].setD(RDTemp);  
                            races[x][1] = RDTemp;
                            racesCounter++;
                            break;
                        }
                    }  
                    rand(); //randomizer
                    
                    for (int x = 0; x < driverCounter; x++) {  //randomizes starting position then determines the win percentage and first person through probability
                        int tempTwo = DriverArray[x].getTDI().getStats().getPos();
                        double winPercent = percentage(tempTwo);
                        tempDouble = winPercent;
                        if (firstCount != 1){ 
                            boolean firstPerson = new Random().nextDouble() <= winPercent;  //https://stackoverflow.com/questions/8183840/probability-in-java
                            if((firstPerson == true || drvCount == driverCounter - 1)){
                                DriverArray[x].setfirstPos(1);
                                firstCount++;
                                drvCount++;
                            }else{ 
                                sortTwo();
                                drvCount++;
                            }
                        }else {
                            sortTwo();
                            drvCount++;
                        }
                    }   

                    for (int x=0; x<DriverArray.length; x++){
                        BackupDriverArray[x].getTDI().getStats().setPos(BackupDriverArray[x].getlastPos()); 
                    }

                    for (int x = 0; x < driverCounter; x++) {  //final values set 
                        int tempThree = DriverArray[x].getTDI().getStats().getPos();
                        row[x][3] = String.valueOf(tempThree);
                        numRc = String.valueOf(DriverArray[x].getTDI().getStats().getNor());
                        row[x][4] = numRc;
                        String numPnt;

                        if (BackupDriverArray[x].getTDI().getStats().getPnt() > 99){
                            numPnt = String.valueOf(BackupDriverArray[x].getTDI().getStats().getPnt());
                        }else if (BackupDriverArray[x].getTDI().getStats().getPnt() > 9) {
                            numPnt = "0"+String.valueOf(BackupDriverArray[x].getTDI().getStats().getPnt());
                        }else{
                            numPnt = "00"+String.valueOf(BackupDriverArray[x].getTDI().getStats().getPnt());
                        }

                        row[x][5] = numPnt;
                        String F = String.valueOf(DriverArray[x].getTDI().getStats().getF());
                        row[x][6] = F;
                        String S = String.valueOf(DriverArray[x].getTDI().getStats().getS());
                        row[x][7] = S;
                        String T = String.valueOf(DriverArray[x].getTDI().getStats().getT());
                        row[x][8] = T;

                        for (int y = 0; y < RacesArray.length; y++) {
                            if (DriverArray[x].getRaceInfo(y).getRaceName().equals("e")) {
                                DriverArray[x].setRaceInfo(y, new indvInfo(RNTemp, RDTemp, tempThree)); 
                                break;
                            }
                        }

                        if (DriverArray[x].getTDI().getStats().getPos() == 1){
                            tempString = DriverArray[x].getTDI().getDN();
                        }
                        
                    }  
                    tf1.setText( tempString + " has won with a " + (tempDouble*100) + "% win rate.");      
                }
                Table.repaint(); //refreshes table
                TableTwo.repaint();
                return;
            }  
        });
        buttonFour.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                drvName = tf1.getText();
                String rcStat = "";
                for (int x = 0; x < DriverArray.length; x++){
                    if (DriverArray[x].getTDI().getDN().equals(drvName)) { //looks for a simmilar word stored into the array
                        tf1.setText(drvName + " has been selected.");
                        lableThree.setText("                                    " + drvName + "'s Statistics : ");
                        for(int y = 0; y < RacesArray.length; y++){
                            if (!DriverArray[x].getRaceInfo(y).getRaceName().equals("e")){
                                String name = DriverArray[x].getRaceInfo(y).getRaceName();
                                String date = DriverArray[x].getRaceInfo(y).getDate();
                                String pos = String.valueOf(DriverArray[x].getRaceInfo(y).getPosition());
                                rcStat = rcStat.concat("\nName : " + name + "\n" 
                                +"\nDate : " + date + "\n" 
                                +"\nPosition : " + pos + "\n\n");
                            }
                        }
                        break;
                    }else {
                        tf1.setText("Invalid characters entered!");
                    }
                }
                txtBox.setText(rcStat);
            }  
        });   

        //Table Tweaking
        Table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);   //https://tips4java.wordpress.com/2008/11/10/table-column-adjuster/

        //Panels
        JPanel textPanel = new JPanel();
        JPanel buttonPanel = new JPanel();
        JPanel TableOne = new JPanel(new BorderLayout());
        JPanel SecondTable = new JPanel(new BorderLayout());
        JPanel textPanelTwo = new JPanel(new BorderLayout());

        //Panel Sizing
        textPanelTwo.setPreferredSize(new Dimension(300, 300)); //https://stackoverflow.com/questions/5921175/how-to-set-jpanels-width-and-height

        //adding all of the elements into the panels
        buttonPanel.add(buttonOne);
        buttonPanel.add(buttonThree);
        buttonPanel.add(buttonTwo);
        buttonPanel.add(buttonFour);

        textPanel.add(tf1);
        textPanelTwo.add(lableThree, BorderLayout.NORTH);
        textPanelTwo.add(new JScrollPane(txtBox), BorderLayout.CENTER);

        TableOne.add(lableTwo, BorderLayout.NORTH);
        TableOne.add(new JScrollPane(Table), BorderLayout.CENTER);
        SecondTable.add(lableOne, BorderLayout.NORTH);
        SecondTable.add(new JScrollPane(TableTwo), BorderLayout.CENTER);

        mainPanel.add(TableOne, BorderLayout.CENTER);
        mainPanel.add(SecondTable, BorderLayout.WEST);
        mainPanel.add(textPanelTwo, BorderLayout.EAST);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        mainPanel.add(textPanel, BorderLayout.NORTH);

    }

    //grabbing the main component
    public JComponent getComponent() {
        return mainPanel;
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JFrame f = new JFrame("Championship Manager");
                f.getContentPane().add(new ChampionshipManager().getComponent());
                f.setSize(1500,800);
                f.setLocationRelativeTo(null);
                f.setVisible(true);
            }
        });
    }

    //----------------------------------Extra Stuff]--------------------------------------------------------------------------------------------------------

    public static void cloneArray(){
        for (int x=0; x<DriverArray.length; x++){
            BackupDriverArray[x] = DriverArray[x]; //clones the array into a backup array to not tweak with the original array's values
        }
    }

    public static double percentage(int n){ 
        double tempVal = 0;
        if (n == 1){ 
            tempVal = 0.4; 

        }else if (n == 2){
            tempVal = 0.3;  

        }else if (n == 3 || n == 4 ){
            tempVal = 0.1; 

        }else if (n == 5 || n == 6 || n == 7 || n == 8 || n == 9){
            tempVal = 0.02;

        }else {
            tempVal = 0.0; 
        }

        return tempVal;
    }

    public static void rand(){
        cloneArray();
        for (int x = 0; x < BackupDriverArray.length; x++) { // gives out initial random postions from 1-12 to all racers
            boolean Race = true;
            while (Race) {
                int randomStartPosition = randomizer();
                if (randomStartPosition !=0) {
                    BackupDriverArray[x].setfirstPos(randomStartPosition);
                    Race = false;
                }
            } 
        }

        Sorter(); // sorts the array in ascending order

        boolean checkdone = false;
        while (checkdone == false){
            for (int z = 0; z < BackupDriverArray.length-1; z++) {
                if (BackupDriverArray[z].getfirstPos() == 1){
                    checkdone = true;
                }else {
                    BackupDriverArray[z].setfirstPos(1);
                }
            }
        }


        if (checkdone){
            sortTwo();
        }

        for (int x=0; x<DriverArray.length; x++){
            BackupDriverArray[x].setlastPos(BackupDriverArray[x].getfirstPos()); 
        }
    }

    public static void sortTwo(){
        for (int x = 0; x < BackupDriverArray.length-1; x++) { // Checks for each array postion if the future index is previous index + 1 if not it will make it +1
            if (BackupDriverArray[x + 1].getfirstPos() != BackupDriverArray[x].getfirstPos()+ 1){
                BackupDriverArray[x + 1].setfirstPos(BackupDriverArray[x].getfirstPos() + 1);
            }
        }
        Sorter();  //one last sorting
    }
    
    public static int randomizer(){
        Random random = new Random();
        int RandPos = random.nextInt(BackupDriverArray.length+1);
        return RandPos;
    }

    public static void Sorter(){
        for (int x = 0; x < BackupDriverArray.length-1; x++) {              //Bubble sort technique
            for (int y = 0; y < BackupDriverArray.length-1 - x; y++) {
                if (BackupDriverArray[y].getfirstPos() > BackupDriverArray[y + 1].getfirstPos()) {
                    Team Holder = BackupDriverArray[y];
                    BackupDriverArray[y] = BackupDriverArray[y + 1];
                    BackupDriverArray[y + 1] = Holder;
                }
            }
        }
    }

}