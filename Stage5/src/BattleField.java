import java.util.Scanner;

public class BattleField {

    String[][] field = new String[11][11];
    final int FIELDSIZE= 11;
    final int AIRCRAFT_LENGTH= 5;
    final int BATTLESHIP_LENGTH = 4;
    final int SUBMARINE_LENGTH= 3;
    final int CRUISER_LENGTH= 3;
    static int count_o=0;




    public BattleField(){
        field[1][0] = "A" ;field[2][0] = "B" ;
        field[3][0] = "C" ;field[4][0] = "D" ;
        field[5][0] = "E" ;field[6][0] = "F" ;
        field[7][0] = "G" ;field[8][0] = "H" ;
        field[9][0] = "I" ;field[10][0] = "J" ;

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
        count_o+=5;
        placeBattleShip();
        count_o+=4;
        // printfield();
        placeSubmarine();
        count_o+=3;
        // printfield();
        placeCruiser();
        count_o+=3;
        // printfield();
        placeDestroyer();count_o+=2;
        //printfield();
        System.out.println("count_o"+count_o);

    }

    public void placeAircraftCarrier(){
        System.out.println("Enter the coordinates of the Aircraft Carrier (5 cells)");
        Boolean flag= true;
        do{
            flag = placement(AIRCRAFT_LENGTH);
            // System.out.println(flag);
        }
        while(!flag);

        //placement(AIRCRAFT_LENGTH);
    }

    public void placeBattleShip(){
        System.out.println("Enter the coordinates of the battleship (4 cells)");
        Boolean flag= true;
        //  do{
        this.placement(BATTLESHIP_LENGTH);
        // System.out.println(flag);
        //}
        //while(!flag);
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
        System.out.println(performCheck(air.c1_x,air.c1_y,air.c2_x,air.c2_y,5));
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
        // System.out.println("c1_Y "+c1_y);
        // System.out.println("c2_Y "+c2_y);
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
        //do {
        String cord="";


        cord = s.nextLine();
        int c_x =  cord.charAt(0)-64;


        int c_y =  Integer.parseInt(cord.substring(1,cord.length()));
        if (c_x >=1 && c_x < FIELDSIZE && c_y >=1 && c_y < FIELDSIZE) {
            if (field[c_x][c_y].equalsIgnoreCase("O")||field[c_x][c_y].equalsIgnoreCase("X")) {

                if(field[c_x][c_y].equalsIgnoreCase("O"))
                {count_o--;}
                field[c_x][c_y] = "X";
                System.out.println("count_o: "+count_o);

                boolean b11 = true;
                for (int i =0;i<4;i++){
                    for (int j=0;j<4;j++){
                        int n1=c_x-i;
                        if (n1 >=1 ){  if(field[n1][j].equals("O")){ b11 = false; break;}}
                        n1 = c_x+i;
                        if (n1 < FIELDSIZE ){if(field[n1][j].equals("O")){b11 = false; break;}}
                        n1 = c_y-i;
                        if (n1 >=1){ if(field[i][n1].equals("O")){b11 = false; break;}}
                        n1 = c_y+i;
                        if (n1 < FIELDSIZE ){ if(field[i][n1].equals("O")){b11 = false; break;}}
                    }
                }
                System.out.println("You hit a ship !");
                if(b11){System.out.println("You sank a ship! Congratulations!");}
                //.. c_x+i < FIELDSIZE && c_y-i>=1 && c_y+i< FIELDSIZE) {

                printHidden();
                // b1 = false;
            } else {
                field[c_x][c_y] = "M";
                //  printHidden();
                System.out.println("You missed ! ");// b1 = false;
            }
            // printfield();
        } else {System.out.println("Error! You entered wrong coordinates! ");
            System.out.println("count_o"+count_o);
        }
        // }while (count_o>=0);
    }


    public void reduceCountO(){
        count_o--;
    }

    public int getCountO(){
        return count_o;
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
