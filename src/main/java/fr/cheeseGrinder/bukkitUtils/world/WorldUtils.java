package fr.cheeseGrinder.bukkitUtils.world;

import fr.cheeseGrinder.bukkitUtils.color.ConsoleColor;
import fr.cheeseGrinder.bukkitUtils.color.MotdColor;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Creature;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;

import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

public class WorldUtils {

    private final Logger logger = Bukkit.getLogger();

    private final String name;

    public WorldUtils(String name) {
        this.name = name;
    }

    public static WorldUtils name(String name) {
        return new WorldUtils(name);
    }

    /**
     * Create new world if not exist
     * @return A new World
     *
     * @see #create(boolean)
     */
    public Optional<World> create() {
        return create(false);
    }

    /**
     * Create a new world
     * @param overwrite if {@code true} overwrite world with same name
     * @return A new World
     *
     * @see #exist()
     */
    public Optional<World> create(boolean overwrite) {
        if (!overwrite && exist()) {
            return Optional.empty();
        }
        return Optional.of(WorldCreator.name(name).createWorld());
    }

    /**
     * Delete a world if exist
     * @return {@code true} if world has been deleted, otherwise false
     *
     * @see #exist()
     * @see #unload()
     */
    public boolean delete() {
        if (notExist()) return false;
        if (isNotLoad()) return false;

        unload(false);
        logger.info(ConsoleColor.GREEN + "world '" + name + "' has been deleted" + ConsoleColor.RESET);
        delete(new File(name));
        return true;
    }

    /**
     * Create a copy world world with targeted name
     * @param target name of copy
     * @return an new instance of {@code WorldUtils}
     *
     * @see #copy(String, boolean)
     */
    public Optional<WorldUtils> copy(String target) {
        return copy(target, false);
    }

    /**
     * Create a copy world world with targeted name
     * @param target name of copy
     * @param overwrite if {@code true} world with targeted name will be overwrite
     * @return an new instance of {@code WorldUtils}
     */
    public Optional<WorldUtils> copy(String target, boolean overwrite) {
        if (overwrite && exist(target)) {
            logger.severe(ConsoleColor.RED + "can't create copy of '" + name + "', " + target + "' already exist");
            return Optional.empty();
        }

        Bukkit.getWorld(name).save();
        copy(new File(name), new File(target));
        return Optional.of(WorldUtils.name(target));
    }

    /**
     * Save the world if exist and is load
     * @return {@code true} if world has been saved, otherwise false
     *
     * @see #exist()
     * @see #isNotLoad()
     */
    public boolean save() {
        if (notExist()) {
            logger.severe(ConsoleColor.RED + "Can't save world '" + name + "' because it doesn't exist" + ConsoleColor.RESET);
            return false;
        }
        if (isNotLoad()) {
            logger.severe(ConsoleColor.RED + "Can't save world '" + name + "' because it doesn't load" + ConsoleColor.RESET);
            return false;
        }
        Bukkit.getWorld(name).save();
        return true;
    }

    /**
     * Purge all friendly creature in world
     *
     * @return number of entities to have been purged
     *
     * @see Creature
     */
    public int purgeFriendlyEntity() {
        AtomicInteger i = new AtomicInteger();

        Bukkit.getWorld(name).getEntities().forEach(entity -> {
            if (entity instanceof Creature) {
                if (!(entity instanceof Monster)) {
                    entity.remove();
                    i.incrementAndGet();
                }
            }
        });
        return i.get();
    }

    /**
     * Purge all aggressive creature in world
     *
     * @return number of entities to have been purged
     *
     * @see Creature
     * @see Monster
     */
    public int purgeMonsterEntity() {
        AtomicInteger i = new AtomicInteger();

        Bukkit.getWorld(name).getEntities().forEach(entity -> {
            if (entity instanceof Creature) {
                if (entity instanceof Monster) {
                    entity.remove();
                    i.incrementAndGet();
                }
            }
        });
        return i.get();
    }

    /**
     * Purge all {@code item_frame} and {@code painting} in world
     * @return number of entities to have been purged
     */
    public int purgeTileEntity() {
        AtomicInteger i = new AtomicInteger();

        Bukkit.getWorld(name).getEntities().forEach(entity -> {
            if (entity.getType() == EntityType.ITEM_FRAME || entity.getType() == EntityType.PAINTING) {
                entity.remove();
                i.incrementAndGet();
            }
        });
        return i.get();
    }

    /**
     * Purge all {@code item} in world
     * @return number of entities to have been purged
     */
    public int purgeDroppedItem() {
        AtomicInteger i = new AtomicInteger();

        Bukkit.getWorld(name).getEntities().forEach(entity -> {
            if (entity.getType() == EntityType.DROPPED_ITEM) {
                entity.remove();
                i.incrementAndGet();
            }
        });
        return i.get();
    }

    /**
     * Purge all except {@code Players}
     * @return number of entities to have been purged
     */
    public int purge() {
        AtomicInteger i = new AtomicInteger();

        Bukkit.getWorld(name).getEntities().forEach(entity -> {
            if (!(entity instanceof Player)) {
                entity.remove();
                i.incrementAndGet();
            }
        });
        return i.get();
    }

    /**
     * Load a world if exist
     * @return {@code true} if world is load, otherwise false
     *
     * @see #exist()
     * @see #isLoad()
     */
    public boolean load() {
        if (notExist()) {
            logger.severe(ConsoleColor.RED + "Can't load world '" + name + "' because it doesn't exist" + ConsoleColor.RESET);
            return false;
        }
        if (isLoad()) {
            logger.severe(ConsoleColor.RED + "Can't load world '" + name + "' because it already load" + ConsoleColor.RESET);
            return false;
        }

        File worldFile = new File(name);
        WorldCreator creator = WorldCreator.name(name);
        World.Environment env = World.Environment.NORMAL;
        String[] files = worldFile.list((dir, name) -> name.startsWith("DIM") || name.equals("region"));

        assert files != null;
        dim: for (String file : files) {
            switch (file) {
                case "region": env = World.Environment.NORMAL; break dim;
                case "DIM-1": env = World.Environment.NETHER; break dim;
                case "DIM1": env = World.Environment.THE_END; break dim;
            }
        }

        creator.environment(env);
        creator.createWorld();
        return true;
    }

    /**
     * Unload the world and save it if exist
     * @return true if world is unloaded, otherwise false
     *
     * @see #unload(boolean)
     */
    public boolean unload() {
        return unload(true);
    }

    /**
     * Unload the world and save it if exist
     * @param save if {@code true} the world has saved before unload
     * @return true if world is unloaded, otherwise false
     *
     * @see #exist()
     * @see #isLoad()
     */
    public boolean unload(boolean save) {
        if (notExist()) {
            logger.severe(ConsoleColor.RED + "Can't unload world '" + name + "' because it doesn't exist" + ConsoleColor.RESET);
            return false;
        }
        if (isNotLoad()) {
            logger.severe(ConsoleColor.RED + "Can't unload world '" + name + "' because it already unload" + ConsoleColor.RESET);
            return false;
        }
        for (Chunk chunk : Bukkit.getWorld(name).getLoadedChunks()) {
            chunk.unload(save);
        }

        return Bukkit.unloadWorld(name, save);
    }

    /**
     * Indicate if world is load
     * @return {@code true} if world is load, otherwise false
     */
    public boolean isLoad() {
        return exist() && Bukkit.getWorlds().stream().filter(world -> name.equals(world.getName())).count() == 1;
    }

    /**
     * Indicate if world is unload
     * @return {@code true} if world is unload, otherwise false
     */
    public boolean isNotLoad() {
        return !isLoad();
    }

    /**
     * Indicate if world exist
     * @return {@code true} if world exist, otherwise false
     */
    public boolean exist() {
        return exist(name);
    }

    /**
     * Indicate if world doesn't exist
     * @return {@code true} if world doesn't exist, otherwise false
     */
    public boolean notExist() {
        return !exist(name);
    }

    private boolean exist(String name) {
        File world = new File(name);

        return world.exists() && world.isDirectory() &&
                Objects.requireNonNull(Bukkit.getWorldContainer()
                        .list((dir, fileName) -> name.equals(fileName))
                ).length == 1;
    }

    private void copy(File source, File target) {
        List<String> ignore = new ArrayList<>(Arrays.asList("uid.dat", "session.dat", "session.lock"));
        List<String> parent = new ArrayList<>(Arrays.asList("advancements", "stats", "playerdata"));

        if (!ignore.contains(source.getName())) {
            if (source.isDirectory()) {
                if (!target.exists()) target.mkdirs();

                if (source.list().length > 0) {
                    for (String file : source.list()) {
                        File srcFile = new File(source, file);
                        File targetFile = new File(target, file);
                        copy(srcFile, targetFile);
                    }
                }

            } else {
                if (!parent.contains(source.getParentFile().getName())) {
                    try {
                        InputStream in = new FileInputStream(source);
                        OutputStream out = new FileOutputStream(target);
                        byte[] buffer = new byte[1024];
                        int length;
                        while ((length = in.read(buffer)) > 0)
                            out.write(buffer, 0, length);
                        in.close();
                        out.close();
                    } catch (IOException ioe) {
                        System.out.println(ioe.getMessage());
                    }
                }
            }
        }
    }

    private void delete(File source) {
        if (source.isDirectory()) {
            for (File file : source.listFiles()) {
                delete(file);
            }
        }
        source.delete();
    }

}
