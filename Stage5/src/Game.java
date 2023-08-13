import java.io.IOException;
public class Game {

    public void start(){
        BattleField battlefield1 = new BattleField();
        System.out.println("Player 1, place your ships on the game field");
        battlefield1.printfield();
        battlefield1.placeAllShips();

        promptEnterKey();
        System.out.println("Player 2, place your ships on the game field");
        battlefield1.printfield();
        BattleField battlefield2 = new BattleField();
        battlefield2.placeAllShips();


        System.out.println("The game starts!");

        while(true){

            System.out.println("Player 1, it's your turn:");
            promptEnterKey();
            battlefield2.printHidden();
            System.out.println("---------------------");
            battlefield1.printfield();
            battlefield2.shoot();
            if(battlefield2.getCountO()==0){
                System.out.println("Player 1 WON !!!!");
                return;
            }

            System.out.println("Player 2, it's your turn:");
            promptEnterKey();
            battlefield1.printHidden();
            System.out.println("---------------------");
            battlefield2.printfield();
            battlefield1.shoot();
            if(battlefield1.getCountO()==0){
                System.out.println("Player 2 WON !!!!");
                return;
            }
            promptEnterKey();
        }



    }


    public static void promptEnterKey() {
        System.out.println("Press Enter and pass the move to another player");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
