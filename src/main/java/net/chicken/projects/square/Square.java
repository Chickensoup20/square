package net.chicken.projects.square;

import com.infernalsuite.aswm.api.AdvancedSlimePaperAPI;
import com.infernalsuite.aswm.api.loaders.SlimeLoader;
import com.infernalsuite.aswm.api.world.SlimeWorldInstance;
import com.infernalsuite.aswm.loaders.file.FileLoader;
import com.infernalsuite.aswm.loaders.mongo.MongoLoader;
import net.chicken.projects.square.database.MongoDB;
import net.chicken.projects.square.events.InternalEvents;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;


public final class Square extends JavaPlugin {
    public static MongoDB db;
    public static SlimeLoader loader;
    public static AdvancedSlimePaperAPI asp = AdvancedSlimePaperAPI.instance();

    @Override
    public void onEnable() {
        loader = new MongoLoader("slimeworldmanager","worlds","slimeworldmanager", "", "admin","127.0.0.1",27017,"mongodb://localhost:27017/");
        db = new MongoDB("mongodb://localhost:27017/");
        db.saveInt("test", 1);
        System.out.println(db.getInt("test"));
        getServer().getPluginManager().registerEvents(new InternalEvents(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
