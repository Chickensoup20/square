package net.chicken.projects.square.plotmanagement;

import com.infernalsuite.aswm.api.exceptions.CorruptedWorldException;
import com.infernalsuite.aswm.api.exceptions.NewerFormatException;
import com.infernalsuite.aswm.api.exceptions.UnknownWorldException;
import com.infernalsuite.aswm.api.world.SlimeWorld;
import com.infernalsuite.aswm.api.world.properties.SlimeProperties;
import com.infernalsuite.aswm.api.world.properties.SlimePropertyMap;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;

import java.io.IOException;

import static net.chicken.projects.square.Square.asp;
import static net.chicken.projects.square.Square.loader;

public class WorldManager {

    SlimePropertyMap properties = new SlimePropertyMap();
    public WorldManager(){
        properties.setValue(SlimeProperties.DIFFICULTY, "normal");
        properties.setValue(SlimeProperties.SPAWN_X, 123);
        properties.setValue(SlimeProperties.SPAWN_Y, 112);
        properties.setValue(SlimeProperties.SPAWN_Z, 170);
        properties.setValue(SlimeProperties.ALLOW_ANIMALS, false);
        properties.setValue(SlimeProperties.ALLOW_MONSTERS, false);
        properties.setValue(SlimeProperties.DRAGON_BATTLE, false);
        properties.setValue(SlimeProperties.PVP, true);
        properties.setValue(SlimeProperties.ENVIRONMENT, "normal");
        properties.setValue(SlimeProperties.WORLD_TYPE, "DEFAULT");
        properties.setValue(SlimeProperties.DEFAULT_BIOME, "minecraft:plains");
    }


    public void createWorld(String name) {
        try {
            SlimeWorld world = asp.createEmptyWorld(name, true, properties, loader);
            asp.readWorld(loader, name,false,properties);
            asp.loadWorld(world,true);
        } catch (Exception e){
            Bukkit.broadcast(Component.text(e.getMessage()));
        }
    }
}
