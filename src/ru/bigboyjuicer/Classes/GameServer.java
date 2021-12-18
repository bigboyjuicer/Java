package ru.bigboyjuicer.Classes;

import ru.bigboyjuicer.Classes.Entity.EntityGuard;
import ru.bigboyjuicer.Classes.Entity.EntityMonster;
import ru.bigboyjuicer.Classes.Entity.EntityPlayer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class GameServer {
    private static GameServer instance;
    private World world;
    private int updateCounter;

    public GameServer() {
        instance = this;
        updateCounter = 0;

        world = new World(new ArrayList<>(Arrays.asList(
                new EntityPlayer("player1", 2, 1, 100, 100, 0),
                new EntityPlayer("player2", -3, 7, 100, 100, 0),
                new EntityPlayer("player3", 2, 7, 100, 100, 0),
                new EntityGuard("guard1", -5, -3, 100, 100),
                new EntityGuard("guard2", -2, -1, 100, 100),
                new EntityGuard("guard3", -5, 0, 100, 100),
                new EntityMonster("monster1", 0,0, 30, 30, 15),
                new EntityMonster("monster2", -2,5, 30, 30, 15)
        )));
    }

    @Override
    public String toString() {
        return "GameServer{" +
                "world=" + world +
                ", updateCounter=" + updateCounter +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameServer that = (GameServer) o;
        return updateCounter == that.updateCounter && Objects.equals(world, that.world);
    }

    @Override
    public int hashCode() {
        return Objects.hash(world, updateCounter);
    }

    public void updateServer(){
        this.updateCounter++;
        this.world.updateWorld();
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public int getUpdateCounter() {
        return updateCounter;
    }

    public void setUpdateCounter(int updateCounter) {
        this.updateCounter = updateCounter;
    }

    public static GameServer getInstance() {
        return instance;
    }

    public static void setInstance(GameServer instance) {
        GameServer.instance = instance;
    }
}
