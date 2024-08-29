package fighter.items;

import fighter.Player;

public class CrossOfRevival extends Item{

    public CrossOfRevival() {
        this.name = "Крест возрождения";
    }

    @Override
    public void addHp(Player player) {
        if (player.getCurrentHp() < 0) {
            player.setCurrentHp(player.getCurrentHp() + ((int) (0.05 * player.getMaxHp())));
        }
    }

}
