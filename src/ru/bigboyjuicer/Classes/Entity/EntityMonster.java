package ru.bigboyjuicer.Classes.Entity;

import ru.bigboyjuicer.Classes.AbstractEntity.Entity;
import ru.bigboyjuicer.Classes.GameServer;

import java.util.List;
import java.util.Objects;

public class EntityMonster extends Entity {
    private double damage;

    public EntityMonster(String title, int xPos, int zPos, double maxHealth, double health, double damage) {
        super(title, xPos, zPos, maxHealth, health);
        this.damage = damage;
    }

    @Override
    public String toString() {
        return "EntityMonster{" +
                "title='" + title + '\'' +
                ", xPos=" + xPos +
                ", zPos=" + zPos +
                ", age=" + age +
                ", maxHealth=" + maxHealth +
                ", health=" + health +
                ", damage=" + damage +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        EntityMonster that = (EntityMonster) o;
        return Double.compare(that.damage, damage) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), damage);
    }

    @Override
    public void update() {
        super.update();
        List<Entity> entities = GameServer.getInstance().getWorld().getEntitiesNearEntity(this, 100);
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

            if(findRadius(entities.get(0).getxPos(), entities.get(0).getzPos()) < 2){
                entities.get(0).attackEntityFrom(this, this.damage);
            }
        }
    }

    public double getDamage() {
        return damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }
}
