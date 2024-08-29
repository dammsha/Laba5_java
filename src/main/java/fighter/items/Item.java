package fighter.items;

import fighter.Player;

public abstract class Item {
    protected int value;
    protected String name;

    public Item() {
        this.value = 0;
        this.name = "";
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    protected abstract void addHp(Player player);
}
