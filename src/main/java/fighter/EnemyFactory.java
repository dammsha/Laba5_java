package fighter;

import fighter.bosses.Blaze;
import fighter.bosses.DarkKahn;
import fighter.bosses.Onaga;
import fighter.bosses.ShaoKahn;
import fighter.fighters.JohnyCage;
import fighter.fighters.KungLao;
import fighter.fighters.SubZero;
import fighter.soldiers.Kitana;
import fighter.soldiers.Meleena;
import fighter.soldiers.Smoke;
import fighter.tanks.Baraka;
import fighter.tanks.LiMei;
import fighter.tanks.Tanya;
import fighter.wizards.Kenshi;
import fighter.wizards.LiuKang;
import fighter.wizards.Rain;

public class EnemyFactory {

    public Enemy createEnemy(int number) {
        switch(number) {
            case 1 :
                return new Baraka();
            case 2 :
                return new LiMei();
            case 3 :
                return new Tanya();
            case 4 :
                return new JohnyCage();
            case 5 :
                return new KungLao();
            case 6 :
                return new SubZero();
            case 7 :
                return new Kenshi();
            case 8 :
                return new LiuKang();
            case 9 :
                return new Rain();
            case 10 :
                return new Kitana();
            case 11:
                return new Meleena();
            case 12:
                return new Smoke();
            case 13 :
                return new ShaoKahn();
            case 14 :
                return new Onaga();
            case 15:
                return new Blaze();
            case 16 :
                return new DarkKahn();
            default:
                throw new IllegalArgumentException("Неизвестный тип врага");
        }
    }
}
