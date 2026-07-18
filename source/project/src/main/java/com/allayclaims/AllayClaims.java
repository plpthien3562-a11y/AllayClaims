package com.allayclaims;

import org.bukkit.plugin.java.JavaPlugin;
import com.allayclaims.commands.*;
import com.allayclaims.listeners.*;
import com.allayclaims.claims.ClaimManager;
import com.allayclaims.economy.EconomyManager;
import com.allayclaims.messages.MessageManager;

public class AllayClaims extends JavaPlugin {
    private static AllayClaims instance;
    private ClaimManager claimManager;
    private EconomyManager economyManager;
    private MessageManager messageManager;

    @Override
    public void onEnable() {
        instance = this;
        
        // Initialize managers
        messageManager = new MessageManager(this);
        claimManager = new ClaimManager(this);
        economyManager = new EconomyManager(this);
        
        // Register commands
        registerCommands();
        
        // Register listeners
        registerListeners();
        
        getLogger().info("AllayClaims has been enabled!");
    }

    @Override
    public void onDisable() {
        if (claimManager != null) {
            claimManager.save();
        }
        getLogger().info("AllayClaims has been disabled!");
    }

    private void registerCommands() {
        getCommand("claim").setExecutor(new ClaimExplosionCommand(this));
        getCommand("unclaim").setExecutor(new UnclaimCommand(this));
        getCommand("admin").setExecutor(new AdminCommand(this));
        getCommand("trust").setExecutor(new TrustCommand(this));
        getCommand("untrust").setExecutor(new UntrustCommand(this));
        getCommand("buyclaimblocks").setExecutor(new BuyClaimBlocksCommand(this));
        getCommand("sellclaimblocks").setExecutor(new SellClaimBlocksCommand(this));
        getCommand("info").setExecutor(new InfoCommand(this));
        getCommand("uc").setExecutor(new UcCommand(this));
        getCommand("unallclaim").setExecutor(new UnallclaimCommand(this));
    }

    private void registerListeners() {
        getServer().getPluginManager().registerEvents(new ProtectionListener(this), this);
        getServer().getPluginManager().registerEvents(new PlayerMoveListener(this), this);
        getServer().getPluginManager().registerEvents(new ClaimToolListener(this), this);
    }

    public static AllayClaims getInstance() {
        return instance;
    }

    public ClaimManager getClaimManager() {
        return claimManager;
    }

    public EconomyManager getEconomyManager() {
        return economyManager;
    }

    public MessageManager getMessageManager() {
        return messageManager;
    }
}