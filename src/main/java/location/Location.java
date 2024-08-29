package location;

import fighter.Enemy;
import fighter.EnemyFactory;

import java.util.ArrayList;
import java.util.Random;

public class Location {

    private ArrayList<Enemy> listEnemy;
    private EnemyFactory enemyFactory = new EnemyFactory();
    private int countEnemy;

    public Location( int numberLocation) {
        this.countEnemy = generateCountEnemy(numberLocation);
        this.listEnemy = generateLocation();
    }

    public ArrayList<Enemy> getListEnemy() {
        return listEnemy;
    }

     private ArrayList<Enemy> generateLocation(){
        ArrayList<Enemy> temp = new ArrayList<Enemy>();
        for (int i = 0; i < this.countEnemy; i++){
            if ((i + 1) < this.countEnemy){
                temp.add(enemyFactory.createEnemy(new Random().nextInt(1,13)));
            } else{
                temp.add(enemyFactory.createEnemy(new Random().nextInt(13,17)));
            }
        }
        return temp;
    }

    int generateCountEnemy(int numberLocation) {
        switch (numberLocation) {
            case 1:
                return 2;
             case 2:
                return new Random().nextInt(2, 4);
            case 3:
                return new Random().nextInt(3, 5);
            case 4:
                return new Random().nextInt(4, 6);
             case 5:
                return new Random().nextInt(5, 7);
            default:
                System.out.println("Ошибка генерации локации!" + numberLocation);
                return 0;
           }
    }

}

