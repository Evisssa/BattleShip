public class Game {

    public void start(){
            BattleField battlefield = new BattleField();
            battlefield.placeAllShips();
            System.out.println("The game starts!");
            battlefield.printHidden();
            battlefield.shoot();
    }

}
