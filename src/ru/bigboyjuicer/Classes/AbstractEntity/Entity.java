package ru.bigboyjuicer.Classes.AbstractEntity;

import java.util.Objects;

public abstract class Entity {
    protected String title;
    protected int xPos;
    protected int zPos;
    protected int age = 0;
    protected double maxHealth;
    protected double health;

    public Entity(String title, int xPos, int zPos, double maxHealth, double health){
        this.title = title;
        this.xPos = xPos;
        this.zPos = zPos;
        this.maxHealth = maxHealth;
        this.health = health;
    }

    @Override
    public String toString() {
        return "Entity{" +
                "title='" + title + '\'' +
                ", xPos=" + xPos +
                ", zPos=" + zPos +
                ", age=" + age +
                ", maxHealth=" + maxHealth +
                ", health=" + health +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entity entity = (Entity) o;
        return xPos == entity.xPos && zPos == entity.zPos && age == entity.age && Double.compare(entity.maxHealth, maxHealth) == 0 && Double.compare(entity.health, health) == 0 && Objects.equals(title, entity.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, xPos, zPos, age, maxHealth, health);
    }

    public double findRadius(double x, double z){
        return Math.sqrt(Math.pow((x - xPos), 2) + Math.pow((z - zPos), 2));
    }

    public void update(){
        this.age++;
    }

    public boolean attackEntityFrom(Entity entity, double damage){
        if (this.health <= 0) {
            return true;
        }
        this.health -= damage;
        if(this.health <= 0){
            System.out.println(this.title + " was killed by " + entity.getTitle());
            return true;
        }
        return false;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getxPos() {
        return xPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public int getzPos() {
        return zPos;
    }

    public void setzPos(int zPos) {
        this.zPos = zPos;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(double maxHealth) {
        this.maxHealth = maxHealth;
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }
}

