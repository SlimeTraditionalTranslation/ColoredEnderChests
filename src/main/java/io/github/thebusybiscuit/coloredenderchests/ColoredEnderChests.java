package io.github.thebusybiscuit.coloredenderchests;

import java.util.HashMap;
import java.util.Map;

//import org.bstats.bukkit.Metrics;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.java.JavaPlugin;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.researches.Research;
import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.libraries.dough.config.Config;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
//import io.github.thebusybiscuit.slimefun4.libraries.dough.updater.GitHubBuildsUpdater;

public class ColoredEnderChests extends JavaPlugin implements SlimefunAddon {

    protected Config cfg;
    protected Map<Integer, String> colors = new HashMap<>();
    protected ItemGroup itemGroup;

    @Override
    public void onEnable() {
        cfg = new Config(this);

        // Setting up bStats
        //new Metrics(this, 4907);

        // Setting up the Auto-Updater
        /*if (cfg.getBoolean("options.auto-update") && getDescription().getVersion().startsWith("DEV - ")) {
            new GitHubBuildsUpdater(this, getFile(), "TheBusyBiscuit/ColoredEnderChests/master").start();
        }*/

        Research enderChestsResearch = new Research(new NamespacedKey(this, "colored_enderchests"), 2610, "彩色終界箱", 20);
        Research bigEnderChestsResearch = new Research(new NamespacedKey(this, "big_colored_enderchests"), 2611, "大彩色終界箱", 30);

        enderChestsResearch.register();
        bigEnderChestsResearch.register();

        colors.put(0, "&f白");
        colors.put(1, "&6橙");
        colors.put(2, "&d品紅");
        colors.put(3, "&b淺藍");
        colors.put(4, "&e黃");
        colors.put(5, "&a酸橙");
        colors.put(6, "&d粉紅");
        colors.put(7, "&8深灰");
        colors.put(8, "&7淺灰");
        colors.put(9, "&3青");
        colors.put(10, "&5紫");
        colors.put(11, "&9藍");
        colors.put(12, "&6棕");
        colors.put(13, "&2綠");
        colors.put(14, "&4紅");
        colors.put(15, "&8黑");

        itemGroup = new ItemGroup(new NamespacedKey(this, "colored_enderchests"), new CustomItemStack(Material.ENDER_CHEST, "&5彩色終界箱"), 2);

        for (int c1 = 0; c1 < 16; c1++) {
            for (int c2 = 0; c2 < 16; c2++) {
                for (int c3 = 0; c3 < 16; c3++) {
                    registerEnderChest(enderChestsResearch, bigEnderChestsResearch, c1, c2, c3);
                }
            }
        }

    }

    private void registerEnderChest(Research smallResearch, Research bigResearch, final int c1, final int c2, final int c3) {
        if (cfg.getBoolean("small_chests")) {
            ColoredEnderChest item = new ColoredEnderChest(this, 27, c1, c2, c3);
            item.register(this);
            smallResearch.addItems(item);
        }

        if (cfg.getBoolean("big_chests")) {
            ColoredEnderChest item = new ColoredEnderChest(this, 54, c1, c2, c3);
            item.register(this);
            bigResearch.addItems(item);
        }
    }

    @Override
    public JavaPlugin getJavaPlugin() {
        return this;
    }

    @Override
    public String getBugTrackerURL() {
        return "https://github.com/SlimeTraditionalTranslation/ColoredEnderChests/issues";
    }
}
