package fighter;

import java.util.Random;

public class Fight {

    public void doFight(Player player, Enemy enemy, boolean turn) {
        getPerfBehaviorType(player, enemy, turn);
        checkDebafEntity(player);
        checkDebafEntity(enemy);
        checkBoss(player, enemy, turn);
    }

    private void checkDebafEntity(Entity entity) {
        entity.setTimerDebaff(entity.getTimerDebaff() != 0 ? entity.getTimerDebaff() - 1 : 0);
        entity.setFlagDebaff(entity.getTimerDebaff() != 0);
    }

    private void getPerfBehaviorType(Player player, Enemy enemy, boolean turn) {
        switch (enemy.getNumberType()) {
            case 1:
                perfBehaviorType1(turn, player, enemy);
                break;
            case 2:
                perfBehaviorType2(turn, player, enemy);
                break;
            case 3:
                perfBehaviorType3(turn, player, enemy);
                break;
            default:
                System.out.println("Ошибка при определении поведения врага!");
        }
    }

    private void perfBehaviorType1(boolean turn, Player player, Enemy enemy) {

        if (enemy.getNumberStep() == 2) {
            enemy.setNumberStep(new Random().nextInt(1, 100) < 50 ? 2 : 3);
        }

        // Если враг не Маг
        if (!enemy.getType().equals("Маг")) {
            if (enemy.getNumberStep() == 1 || enemy.getNumberStep() == 2) {
                enemyAttack(turn, player, enemy);
                enemy.setNumberStep(enemy.getNumberStep() + 1);
            } else if (enemy.getNumberStep() == 3) {
                enemyDef(turn, player, enemy);
                enemy.setNumberStep(1);
            }
        } else {
            // Если игрок защищается, маг имеет шанс дебаффить
            if (new Random().nextInt(1, 100) > 75 && player.getMoveStatus() == Entity.MoveStatus.DEFENDING && !player.getFlagDebaff()) {
                player.setFlagDebaff(true);
                player.setTimerDebaff(enemy.getLevel());
            } else {
                if (enemy.getNumberStep() == 1 || enemy.getNumberStep() == 2) {
                    enemyAttack(turn, player, enemy);
                    enemy.setNumberStep(enemy.getNumberStep() + 1);
                } else if (enemy.getNumberStep() == 3) {
                    enemyDef(turn, player, enemy);
                    enemy.setNumberStep(1);
                }
            }
        }
    }

    private void perfBehaviorType2(boolean turn, Player player, Enemy enemy) {
        if (enemy.getNumberStep() == 1) {enemyDef(turn, player, enemy); enemy.setNumberStep(enemy.getNumberStep() + 1);}
        else if (enemy.getNumberStep() == 2) {enemyAttack(turn, player, enemy);
            enemy.setNumberStep(1);
        }
    }

    private void perfBehaviorType3(boolean turn, Player player, Enemy enemy) {
        enemyAttack(turn, player, enemy);
    }

    private void enemyAttack(boolean turn, Player player, Enemy enemy) {

        // Если враг под дебаффом и атакует, дебафф срывается и наносится 15% дополнительного урона
        if (enemy.getFlagDebaff()) {
            player.setCurrentHp(player.getCurrentHp() - (int) (enemy.getDamage() * 1.15));
        }

        enemy.setMoveStatus(Entity.MoveStatus.ATTACK);
        enemy.setFlagDebaff(false);

            // Если враг ходит вторым и игрок атакует
        if (turn && player.getMoveStatus() == Entity.MoveStatus.ATTACK) {
            enemy.setCurrentHp(calculateDamage(player, enemy, false));

            // Если враг ходит первым и игрок атакует
        } else if (!turn && player.getMoveStatus() == Entity.MoveStatus.ATTACK) {
            player.setCurrentHp(calculateDamage(enemy, player, false));

            // Если игрок защищается и первым ходит враг
        } else if (!turn && player.getMoveStatus() == Entity.MoveStatus.DEFENDING) {
            System.out.println("Игрок контратакует ");
            enemy.setCurrentHp(calculateDamage(player, enemy, true));

            // Если игрок оглушен
        } else if (player.getMoveStatus() == Entity.MoveStatus.STUNNING) {
            player.setCurrentHp(calculateDamage(enemy, player, false));

            // Если игрок дебаффит первым
        } else if (turn && player.getMoveStatus() == Entity.MoveStatus.DEBAFF) {
            enemy.setCurrentHp(calculateDamage(player, enemy, false));

            // Если игрок дебаффит вторым
        } else if(!turn && player.getMoveStatus() == Entity.MoveStatus.DEBAFF) {
            player.setCurrentHp(calculateDamage(enemy, player, false));

            // Если враг оглушен
        } else if (enemy.getMoveStatus() == Entity.MoveStatus.STUNNING) {
            enemy.setMoveStatus(Entity.MoveStatus.ATTACK);}
    }

    private void enemyDef(boolean turn, Player player, Enemy enemy) {

        // Враг защищается
        enemy.setMoveStatus(enemy.getMoveStatus() != Entity.MoveStatus.STUNNING ? Entity.MoveStatus.DEFENDING : Entity.MoveStatus.STUNNING);

            // Если игрок атакует первым и враг не оглушен
        if (turn && player.getMoveStatus() == Entity.MoveStatus.ATTACK && enemy.getMoveStatus() != Entity.MoveStatus.STUNNING) {
            System.out.println("Враг контратакует  : " + calculateDamage(enemy, player, true));
            player.setCurrentHp(calculateDamage(enemy, player, true));

            // Если игрок защищается первым (оглушение врага)
        } else if (turn && player.getMoveStatus() == Entity.MoveStatus.DEFENDING && enemy.getMoveStatus() != Entity.MoveStatus.STUNNING) {
            if (new Random().nextBoolean()) {
                enemy.setMoveStatus(Entity.MoveStatus.STUNNING);
            }

            // Если игрок атакует мага, срывается дебафф и наносится дополнительный урон
        } else if (!turn && enemy.getType().equals("Маг") && player.getMoveStatus() == Entity.MoveStatus.ATTACK) {
            if (enemy.getFlagDebaff()) {
                player.setCurrentHp(player.getCurrentHp() - (int) (enemy.getDamage() * 1.15));
                enemy.setFlagDebaff(false);
            }
            enemy.setCurrentHp(calculateDamage(player, enemy, false));

            // Если игрок дебаффит вторым (ослабление врага)
        } else if (player.getMoveStatus() == Entity.MoveStatus.DEBAFF) {
            int rand = new Random().nextInt(1, 100);
            if (rand < 75) {
                enemy.setFlagDebaff(true);
                enemy.setTimerDebaff(player.getLevel());
            }
            // Если враг защищается первым и игрок атакует
        } else if (!turn &&  player.getMoveStatus() == Entity.MoveStatus.ATTACK) {
            System.out.println("ничего не произошло ");

//            Если игрок защищается вторым (ослабление игрока)
        } else if (!turn && player.getMoveStatus() == Entity.MoveStatus.DEFENDING) {
            int rand = new Random().nextInt(1, 100);
            if (rand < 75) {
                player.setMoveStatus(Entity.MoveStatus.STUNNING);
                System.out.println("Игрок ослаблен");
            }
        }
        else {
            enemy.setMoveStatus(Entity.MoveStatus.DEFENDING);
        }
    }

    // Подсчет наносимого урона в зависимости от статуса дебаффов
    private int calculateDamage(Entity et1, Entity et2, boolean cAttack) {

        if (et1.getFlagDebaff() && et2.getFlagDebaff()) {
            return et2.getCurrentHp() - (int) (!cAttack ? (et1.getDamage() / 2) * 1.25 : ((et1.getDamage() / 2) * 1.25) / 2);

        } else if (et1.getFlagDebaff() && !et2.getFlagDebaff()) {
            return et2.getCurrentHp() - (!cAttack ? et1.getDamage() / 2 : (et1.getDamage() / 2) / 2);

        } else if (!et1.getFlagDebaff() && et2.getFlagDebaff()) {
            return et2.getCurrentHp() - (int) (!cAttack ? et1.getDamage() * 1.25 : (et1.getDamage() * 1.25) / 2);

        } else {
            return et2.getCurrentHp() - (!cAttack ? et1.getDamage() : et1.getDamage() / 2);
        }
    }

    private void regeneration(Player player, Enemy boss) {
        boss.setCurrentHp((int) (boss.getCurrentHp() + (player.getDamage() * 0.5)));
    }

    private void checkBoss(Player player, Enemy enemy, boolean turn) {
        if (enemy.getType().equals("Босс")) {
            if (!turn && player.getMoveStatus() == Entity.MoveStatus.DEFENDING && new Random().nextInt(1, 100) > 25) {
                regeneration(player, enemy);
                System.out.println("Враг регенерируется");
            }
        }
    }

}