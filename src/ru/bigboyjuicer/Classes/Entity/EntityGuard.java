package ru.bigboyjuicer.Classes.Entity;

import ru.bigboyjuicer.Classes.AbstractEntity.Entity;
import ru.bigboyjuicer.Classes.GameServer;

import java.util.List;

public class EntityGuard extends Entity {
    public EntityGuard(String title, int xPos, int zPos, double maxHealth, double health) {
        super(title, xPos, zPos, maxHealth, health);
    }

    @Override
    public String toString() {
        return "EntityGuard{" +
                "title='" + title + '\'' +
                ", xPos=" + xPos +
                ", zPos=" + zPos +
                ", age=" + age +
                ", maxHealth=" + maxHealth +
                ", health=" + health +
                '}';
    }

    @Override
    public void update() {
        super.update();
        List<Entity> entities = GameServer.getInstance().getWorld().getEntitiesNearEntity(this, 25);
        if(entities.size() > 0){
            if(entities.get(0).getxPos() > this.xPos){
                xPos++;
            }
            else if(entities.get(0).getxPos() < this.xPos){
                xPos--;
            }

            if(entities.get(0).getzPos() > this.zPos){
                zPos++;
            }
            else if(entities.get(0).getzPos() < this.zPos){
                zPos--;
            }
        }
    }
}
