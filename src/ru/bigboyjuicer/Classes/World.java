package ru.bigboyjuicer.Classes;

import ru.bigboyjuicer.Classes.AbstractEntity.Entity;
import ru.bigboyjuicer.Classes.Entity.EntityGuard;
import ru.bigboyjuicer.Classes.Entity.EntityPlayer;

import java.util.*;

public class World {
    private List<Entity> entities;

    public World(List<Entity> entities) {
        this.entities = entities;
    }

    @Override
    public String toString() {
        return "World{" +
                "entities=" + entities +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        World world = (World) o;
        return Objects.equals(entities, world.entities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(entities);
    }

    public void updateWorld(){
        for(Entity entity: entities){
            entity.update();
        }

        entities.removeIf(entity -> entity.getHealth() <= 0);
    }

    public List<Entity> getEntitiesInRegion(int x, int z, double range){
        Map<Entity, Double> entitiesRange = new HashMap<>();
        List<Entity> inRegionEntities = new ArrayList<>();
        for(Entity entity: entities){
            if(entity instanceof EntityPlayer && entity.findRadius(x, z) <= range){
                inRegionEntities.add(entity);
                entitiesRange.put(entity, entity.findRadius(x, z));
            }
        }
        inRegionEntities.sort(Comparator.comparing(entitiesRange::get));
        return inRegionEntities;
    }

    public List<Entity> getEntitiesNearEntity(Entity entity, double range){
        return getEntitiesInRegion(entity.getxPos(), entity.getzPos(), range);
    }

    public List<EntityGuard> getGuardiansInRegion(int x, int z, double range){
        Map<EntityGuard, Double> entitiesRange = new HashMap<>();
        List<EntityGuard> inRegionEntities = new ArrayList<>();
        for(Entity entity: entities){
            if(entity instanceof EntityGuard && entity.findRadius(x, z) <= range){
                inRegionEntities.add((EntityGuard) entity);
                entitiesRange.put((EntityGuard) entity, entity.findRadius(x, z));
            }
        }
        inRegionEntities.sort(Comparator.comparing(entitiesRange::get));
        return inRegionEntities;
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public void setEntities(List<Entity> entities) {
        this.entities = entities;
    }
}
