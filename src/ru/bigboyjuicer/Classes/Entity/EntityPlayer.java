package ru.bigboyjuicer.Classes.Entity;

import ru.bigboyjuicer.Classes.AbstractEntity.Entity;
import ru.bigboyjuicer.Classes.GameServer;

import java.util.Objects;

public class EntityPlayer extends Entity {
    private int exp;

    public EntityPlayer(String title, int xPos, int zPos, double maxHealth, double health, int exp) {
        super(title, xPos, zPos, maxHealth, health);
        this.exp = exp;
    }

    @Override
    public String toString() {
        return "EntityPlayer{" +
                "title='" + title + '\'' +
                ", xPos=" + xPos +
                ", zPos=" + zPos +
                ", age=" + age +
                ", maxHealth=" + maxHealth +
                ", health=" + health +
                ", exp=" + exp +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        EntityPlayer that = (EntityPlayer) o;
        return exp == that.exp;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), exp);
    }


    @Override
    public void update() {
        super.update();
        if(GameServer.getInstance().getUpdateCounter() % 2 == 0 && this.health < this.maxHealth && this.health > 0){
            this.health++;
        }
    }

    @Override
    public boolean attackEntityFrom(Entity entity, double damage) {
        for(Entity ent: GameServer.getInstance().getWorld().getEntities()){
            if(ent instanceof EntityGuard && this.findRadius(ent.getxPos(), ent.getzPos()) < 3){
                return ent.attackEntityFrom(entity, damage);
            }
        }

        if(super.attackEntityFrom(entity, damage)){
            return true;
        }
        else{
            if(entity.attackEntityFrom(this, calculateDamage())){
                exp++;
                return true;
            }
            else{
                return false;
            }
        }
    }

    public double calculateDamage(){
        return 3 + exp / 2;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }
}
