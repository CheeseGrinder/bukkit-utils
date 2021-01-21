package fr.cheeseGrinder.bukkitUtils.world;

import org.bukkit.Bukkit;
import org.bukkit.World;

public class GameRulesUtils {
    
    private final World world;

    public GameRulesUtils(String name) {
        this(name, true);
    }

    public GameRulesUtils(String name, boolean setDefaultGameRules) {
        this(Bukkit.getWorld(name), setDefaultGameRules);
    }

    public GameRulesUtils(World world) {
        this(world, true);
    }

    public GameRulesUtils(World world, boolean setDefaultGameRules) {
        this.world = world;
        if (setDefaultGameRules) initialize();
    }

    private void initialize() {
        setAnnounceAdvancements(false);
        setCommandBlockOutput(false);
        setDisableElytraMovementCheck(false);
        setDoDaylightCycle(false);
        setDoEntityDrops(false);
        setDoFireTick(false);
        setDoLimitedCrafting(true);
        setDoMobLoot(false);
        setDoMobSpawning(false);
        setDoTileDrops(false);
        setDoWeatherCycle(false);
        // setGameLoopFunction(false);
        setKeepInventory(true);
        setLogAdminCommands(true);
        // setMaxCommandChainLength(32768); // default value
        setMaxEntityCramming(30);
        setMobGriefing(false);
        setNaturalRegeneration(false);
        // setRandomTickSpeed(3); //default value
        setReducedDebugInfo(false);
        setSendCommandFeedback(true);
        setShowDeathMessages(true);
        setSpawnRadius(1);
        setSpectatorGenerateChunks(false);
    }

    public boolean setAnnounceAdvancements(boolean value) {
        return world.setGameRuleValue(GameRule.ANNOUNCE_ADVANCEMENTS, String.valueOf(value));
    }

    public boolean getAnnounceAdvancements() {
        String gameRule = world.getGameRuleValue(GameRule.ANNOUNCE_ADVANCEMENTS);
        return Boolean.parseBoolean(gameRule);
    }

    public boolean setCommandBlockOutput(boolean value) {
        return world.setGameRuleValue(GameRule.COMMAND_BLOCK_OUTPUT, String.valueOf(value));
    }

    public boolean getCommandBlockOutput() {
        String gameRule = world.getGameRuleValue(GameRule.COMMAND_BLOCK_OUTPUT);
        return Boolean.parseBoolean(gameRule);
    }

    public boolean setDisableElytraMovementCheck(boolean value) {
        return world.setGameRuleValue(GameRule.DISABLE_ELYTRA_MOVEMENT_CHECK, String.valueOf(value));
    }

    public boolean getDisableElytraMovementCheck() {
        String gameRule = world.getGameRuleValue(GameRule.DISABLE_ELYTRA_MOVEMENT_CHECK);
        return Boolean.parseBoolean(gameRule);
    }

    public boolean setDoDaylightCycle(boolean value) {
        return world.setGameRuleValue(GameRule.DO_DAYLIGHT_CYCLE, String.valueOf(value));
    }

    public boolean getDoDaylightCycle() {
        String gameRule = world.getGameRuleValue(GameRule.DO_DAYLIGHT_CYCLE);
        return Boolean.parseBoolean(gameRule);
    }

    public boolean setDoEntityDrops(boolean value) {
        return world.setGameRuleValue(GameRule.DO_ENTITY_DROPS, String.valueOf(value));
    }

    public boolean getDoDoEntityDrops() {
        String gameRule = world.getGameRuleValue(GameRule.DO_ENTITY_DROPS);
        return Boolean.parseBoolean(gameRule);
    }

    public boolean setDoFireTick(boolean value) {
        return world.setGameRuleValue(GameRule.DO_FIRE_TICK, String.valueOf(value));
    }

    public boolean getDoFireTick() {
        String gameRule = world.getGameRuleValue(GameRule.DO_FIRE_TICK);
        return Boolean.parseBoolean(gameRule);
    }

    public boolean setDoLimitedCrafting(boolean value) {
        return world.setGameRuleValue(GameRule.DO_LIMITED_CRAFTING, String.valueOf(value));
    }

    public boolean getDoLimitedCrafting() {
        String gameRule = world.getGameRuleValue(GameRule.DO_LIMITED_CRAFTING);
        return Boolean.parseBoolean(gameRule);
    }

    public boolean setDoMobLoot(boolean value) {
        return world.setGameRuleValue(GameRule.DO_MOB_LOOT, String.valueOf(value));
    }

    public boolean getDoMobLoot() {
        String gameRule = world.getGameRuleValue(GameRule.DO_MOB_LOOT);
        return Boolean.parseBoolean(gameRule);
    }

    public boolean setDoMobSpawning(boolean value) {
        return world.setGameRuleValue(GameRule.DO_MOB_SPAWNING, String.valueOf(value));
    }

    public boolean getDoMobSpawning() {
        String gameRule = world.getGameRuleValue(GameRule.DO_MOB_SPAWNING);
        return Boolean.parseBoolean(gameRule);
    }

    public boolean setDoTileDrops(boolean value) {
        return world.setGameRuleValue(GameRule.DO_TILE_DROPS, String.valueOf(value));
    }

    public boolean getDoTileDrops() {
        String gameRule = world.getGameRuleValue(GameRule.DO_TILE_DROPS);
        return Boolean.parseBoolean(gameRule);
    }

    public boolean setDoWeatherCycle(boolean value) {
        return world.setGameRuleValue(GameRule.DO_WEATHER_CYCLE, String.valueOf(value));
    }

    public boolean getDoWeatherCycle() {
        String gameRule = world.getGameRuleValue(GameRule.DO_WEATHER_CYCLE);
        return Boolean.parseBoolean(gameRule);
    }

    public boolean setGameLoopFunction(boolean value) {
        return world.setGameRuleValue(GameRule.GAME_LOOP_FUNCTION, String.valueOf(value));
    }

    public boolean getGameLoopFunction() {
        String gameRule = world.getGameRuleValue(GameRule.GAME_LOOP_FUNCTION);
        return Boolean.parseBoolean(gameRule);
    }

    public boolean setKeepInventory(boolean value) {
        return world.setGameRuleValue(GameRule.KEEP_INVENTORY, String.valueOf(value));
    }

    public boolean getKeepInventory() {
        String gameRule = world.getGameRuleValue(GameRule.KEEP_INVENTORY);
        return Boolean.parseBoolean(gameRule);
    }

    public boolean setLogAdminCommands(boolean value) {
        return world.setGameRuleValue(GameRule.LOG_ADMIN_COMMANDS, String.valueOf(value));
    }

    public boolean getLogAdminCommands() {
        String gameRule = world.getGameRuleValue(GameRule.LOG_ADMIN_COMMANDS);
        return Boolean.parseBoolean(gameRule);
    }

    public boolean setMaxCommandChainLength(int value) {
        return world.setGameRuleValue(GameRule.MAX_COMMAND_CHAIN_LENGTH, String.valueOf(value));
    }

    public int getMaxCommandChainLength() {
        String gameRule = world.getGameRuleValue(GameRule.MAX_COMMAND_CHAIN_LENGTH);
        return Integer.parseInt(gameRule);
    }

    public boolean setMaxEntityCramming(int value) {
        return world.setGameRuleValue(GameRule.MAX_ENTITY_CRAMMING, String.valueOf(value));
    }

    public int getMaxEntityCramming() {
        String gameRule = world.getGameRuleValue(GameRule.MAX_ENTITY_CRAMMING);
        return Integer.parseInt(gameRule);
    }

    public boolean setMobGriefing(boolean value) {
        return world.setGameRuleValue(GameRule.MOB_GRIEFING, String.valueOf(value));
    }

    public boolean getMobGriefing() {
        String gameRule = world.getGameRuleValue(GameRule.MOB_GRIEFING);
        return Boolean.parseBoolean(gameRule);
    }

    public boolean setNaturalRegeneration(boolean value) {
        return world.setGameRuleValue(GameRule.NATURAL_REGENERATION, String.valueOf(value));
    }

    public boolean getNaturalRegeneration() {
        String gameRule = world.getGameRuleValue(GameRule.NATURAL_REGENERATION);
        return Boolean.parseBoolean(gameRule);
    }

    public boolean setRandomTickSpeed(int value) {
        return world.setGameRuleValue(GameRule.RANDOM_TICK_SPEED, String.valueOf(value));
    }

    public int getRandomTickSpeed() {
        String gameRule = world.getGameRuleValue(GameRule.RANDOM_TICK_SPEED);
        return Integer.parseInt(gameRule);
    }

    public boolean setReducedDebugInfo(boolean value) {
        return world.setGameRuleValue(GameRule.REDUCED_DEBUG_INFO, String.valueOf(value));
    }

    public boolean getReducedDebugInfo() {
        String gameRule = world.getGameRuleValue(GameRule.REDUCED_DEBUG_INFO);
        return Boolean.parseBoolean(gameRule);
    }

    public boolean setSendCommandFeedback(boolean value) {
        return world.setGameRuleValue(GameRule.SEND_COMMAND_FEEDBACK, String.valueOf(value));
    }

    public boolean getSendCommandFeedback() {
        String gameRule = world.getGameRuleValue(GameRule.SEND_COMMAND_FEEDBACK);
        return Boolean.parseBoolean(gameRule);
    }

    public boolean setShowDeathMessages(boolean value) {
        return world.setGameRuleValue(GameRule.SHOW_DEATH_MESSAGES, String.valueOf(value));
    }

    public boolean getShowDeathMessages() {
        String gameRule = world.getGameRuleValue(GameRule.SHOW_DEATH_MESSAGES);
        return Boolean.parseBoolean(gameRule);
    }

    public boolean setSpawnRadius(int value) {
        return world.setGameRuleValue(GameRule.SPAWN_RADIUS, String.valueOf(value));
    }

    public int getSpawnRadius() {
        String gameRule = world.getGameRuleValue(GameRule.SPAWN_RADIUS);
        return Integer.parseInt(gameRule);
    }

    public boolean setSpectatorGenerateChunks(boolean value) {
        return world.setGameRuleValue(GameRule.SPECTATOR_GENERATE_CHUNCKS, String.valueOf(value));
    }

    public boolean getSpectatorGenerateChunks() {
        String gameRule = world.getGameRuleValue(GameRule.SPECTATOR_GENERATE_CHUNCKS);
        return Boolean.parseBoolean(gameRule);
    }
}
