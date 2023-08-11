import java.util.Scanner;

public class BattleField {

    String[][] field = new String[11][11];
    final int FIELDSIZE= 11;
    final int AIRCRAFT_LENGTH= 5;
    final int BATTLESHIP_LENGTH = 4;
    final int SUBMARINE_LENGTH= 3;
    final int CRUISER_LENGTH= 3;
     
     
     

    //Creating the empty battlefield
    public BattleField(){
        field[1][0] = "A" ;field[6][0] = "B" ;
        field[2][0] = "C" ;field[7][0] = "D" ;
        field[3][0] = "E" ;field[8][0] = "F" ;
        field[4][0] = "G" ;field[9][0] = "H" ;
        field[5][0] = "I" ;field[10][0] = "J" ;

        for(int i =0; i<field.length;i++){
            if(i==0){
                for(int j =0;j<field[0].length;j++){
                    field[0][j] =""+ j ;
                } continue;
            }
            for(int j =1;j<field[i].length;j++){
                field[i][j]="~";
            }
        }
    }


    public void placeAllShips(){
        placeAircraftCarrier();
        placeBattleShip();
        // printfield();
        placeSubmarine();
        // printfield();
        placeCruiser();
        // printfield();
        placeDestroyer();
        //printfield();
    }

    public void placeAircraftCarrier(){
        System.out.println("Enter the coordinates of the Aircraft Carrier (5 cells)");
        placement(AIRCRAFT_LENGTH);
    }

    public void placeBattleShip(){
        System.out.println("Enter the coordinates of the battleship (4 cells)");
        Boolean flag= true;
        do{
            flag = placement(BATTLESHIP_LENGTH);
            // System.out.println(flag);
        }
        while(!flag);
    }

    public void placeSubmarine(){
        System.out.println("Enter the coordinates of the Submarine (3 cells)");
        Boolean flag= true;
        do{
            flag = placement(SUBMARINE_LENGTH);
            //System.out.println(flag);
        }
        while(!flag);
    }

    public void placeCruiser(){
        System.out.println("Enter the coordinates of the Cruiser (3 cells)");
        Boolean flag= true;
        do{
            flag = placement(CRUISER_LENGTH);
            //System.out.println(flag);
        }
        while(!flag);
    }

    public void placeDestroyer(){
        final int destroyerLen=2;
        System.out.println("Enter the coordinates of the Destroyer (2 cells)");
        Boolean flag= true;
        do{
            flag = placement(destroyerLen);
            //System.out.println(flag);
        }
        while(!flag);
    }


    //Placing the ship in the field
    public Boolean placement(int len){
        Scanner s = new Scanner(System.in);
        //do{
        String cord= s.nextLine();
        Ship air = Ship.separatedCoord(cord);


        int x_start = Math.min(air.c1_x,air.c2_x)-64;
        int x_end = Math.max(air.c1_x,air.c2_x)-64;
        int y_start = Math.min(air.c1_y,air.c2_y);
        int y_end = Math.max(air.c1_y,air.c2_y);
        //Boolean flag =  performCheck(air.c1_x,air.c1_y,air.c2_x,air.c2_y,aircraftLen);
        //System.out.println(performCheck(air.c1_x,air.c1_y,air.c2_x,air.c2_y,aircraftLen));
        if(performCheck(air.c1_x,air.c1_y,air.c2_x,air.c2_y,len)) {
            for(int i = x_start;i<=x_end;i++){
                for(int j= y_start;j<=y_end;j++){
                    field[i][j]="O";
                }
            }

            printfield();
            return true;
        } return false;

    }


    // Checking that the new ship is entered correctly
    public boolean performCheck(int c1_x,int c1_y,int c2_x,int c2_y,int ship_len){
        c1_x= c1_x-64;
        c2_x= c2_x-64;
        // System.out.println("c1_x "+c1_x);
        // System.out.println("c2_x "+c2_x);
        int y_start = Math.min(c1_y,c2_y);
        int y_end = Math.max(c2_y,c1_y);
        int x_start = Math.min(c1_x,c2_x);
        int x_end = Math.max(c2_x,c1_x);
        if (c1_x ==c2_x ){
            if(Math.abs(c1_y- c2_y)+1== ship_len){
                for(int j = y_start;j<=y_end;j++){
                    if(field[c1_x][j].equalsIgnoreCase("O")) {
                        System.out.println("Error! You placed it on top of another one. Try again:");
                        return false;
                    }
                    if(((j+1)<=10 &&field[c1_x][j+1].equalsIgnoreCase("O"))||
                            ((j-1)>=0 &&field[c1_x][j-1].equalsIgnoreCase("O"))||
                            ((c1_x+1)<=10 &&field[c1_x+1][j].equalsIgnoreCase("O"))||
                            ((c1_x-1)>=0 &&field[c1_x-1][j].equalsIgnoreCase("O"))
                    ){ System.out.println("Error! You placed it too close to another one. Try again:"); return false ;}
                }
                return true;
            }
            else {System.out.println("Error! Wrong length of the Submarine! Try again:");return false;}
        }
        else if (c1_y ==c2_y){
            // System.out.println("Math.abs(c1_x- c2_x+1) "+(Math.abs(c1_x- c2_x)+1));
            if(Math.abs(c1_x- c2_x)+1== ship_len){

                // System.out.println("Math.abs(c1_x- c2_x+1) "+(Math.abs(c1_x- c2_x)+1));

                for(int i = Math.min(c1_x,c2_x);i<=Math.max(c2_x,c1_x);i++) {
                    if (field[i][c1_y].equalsIgnoreCase("O")) {
                        System.out.println("Error! You placed it too close to another one. Try again:");
                        return false;
                    }
                    if(((c1_y+1)<=10 &&field[i][c1_y+1].equalsIgnoreCase("O"))||
                            ((c1_y-1)>=0 &&field[i][c1_y-1].equalsIgnoreCase("O"))||
                            ((i+1)<=10 &&field[i+1][c1_y].equalsIgnoreCase("O"))||
                            ((i-1)>=0 &&field[i-1][c1_y].equalsIgnoreCase("O"))
                    ){ System.out.println("Error! You placed it too close to another one. Try again:"); return false ;}


                }
                return true;
            }
            System.out.println("Error! Wrong length of the Submarine! Try again:");
            return false;
        }
        else {
            System.out.println("Error! Wrong ship location! Try again:\n");return false;}
    }

    public void shoot(){
        System.out.println("Take a shot! ");
        Scanner s = new Scanner(System.in);



        Boolean b1 = true;
        do {
            String cord = s.nextLine();
            int c_x =  cord.charAt(0)-64;
            int c_y =  Integer.parseInt(cord.substring(1,cord.length()));
            if (c_x >=1 && c_x < FIELDSIZE && c_y >=1 && c_y < FIELDSIZE) {
                if (field[c_x][c_y].equalsIgnoreCase("O")) {
                    field[c_x][c_y] = "X";
                    printHidden();
                    System.out.println("You hit a ship !");
                    b1 = false;

                } else {
                    field[c_x][c_y] = "M";
                    printHidden();
                    System.out.println("You missed ! Try again! ");
                    b1 = false;
                }
                // printfield();

            } else {System.out.println("Error! You entered wrong coordinates! Try again:");

            }
        }while (b1);
    }



    public void printfield() {
        for (String f1[] : field) {
            for (String f : f1) {
                System.out.print(f + " ");
            }
            System.out.println(" ");
        }
    }

    public void printHidden() {
        for (String f1[] : field) {
            for (String f : f1) {

                if(f.equalsIgnoreCase("O")) {
                    System.out.print("~" + " ");}
                else{
                    System.out.print(f + " ");
                }
            }
            System.out.println(" ");
        }
    }


}
