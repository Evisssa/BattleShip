public class Ship {
    int c1_x;
    int c1_y;
    int c2_x;
    int c2_y;

    public Ship(int c1_x,int c1_y,int c2_x,int c2_y){
        this.c1_x=c1_x;
        this.c2_x = c2_x;
        this.c1_y = c1_y;
        this.c2_y = c2_y;
    }

    //In case the coordinates are not separated
    public static Ship separatedCoord(String cord){
        char cord1_x = cord.charAt(0);
        int cord1_y =Integer.parseInt(cord.substring(1,cord.indexOf(' ')));
        //int id_x_2 = cord.indexOf(' ')+1 ;
        // cord.
        String cord2 = (cord.substring(cord.indexOf(' '),cord.length())).trim();
        char cord2_x = cord2.charAt(0);
        int cord2_y =Integer.parseInt(cord2.substring(1,cord2.length()));

        // System.out.println(performCheck( ship_len);

        return new Ship(cord1_x,cord1_y,cord2_x,cord2_y);
    }




}