
package wat.lynx.FastSwitch;

import java.util.logging.Logger;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author Lynx
 */
public class FastSwitch extends JavaPlugin {

    public int x;
    public String oplayer;
    public String namn = ChatColor.GOLD + "[" + ChatColor.DARK_GREEN + "FastSwitch" + ChatColor.GOLD + "]" + ChatColor.GRAY + " ";
    public Boolean found;
    public Player dude;
    static final Logger log = Logger.getLogger("Minecraft");

    public void onDisable() {
        log.info("[FastSwitch]Disabled!");
    }

    public void onEnable() {
        log.info("[FastSwitch]Enabled!");
    }

    public void Creative(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        if (args.length < 1) {
            if (player.isOp() || player.hasPermission("fastswitch.creative")) {
                player.setGameMode(GameMode.CREATIVE);
                sender.sendMessage(namn + "You changed your game mode to " + ChatColor.GREEN + "Creative" + ChatColor.GRAY + "!");
                log.info("[FastSwitch] " + player.getDisplayName() + " changed game mode to Creative!");
            } else {
                NoPerms(sender);
            }
        } else if (args.length == 1) {
            if (player.isOp() || player.hasPermission("fastswitch.creative.other")) {
                if (isOnline(sender, args)) {
                    dude.setGameMode(GameMode.CREATIVE);
                    dude.sendMessage(namn + ChatColor.DARK_RED + player.getDisplayName() + ChatColor.GRAY + " changed your game mode  to " + ChatColor.GREEN + "Creative" + ChatColor.GRAY + "!");
                    log.info("[FastSwitch] " + player.getDisplayName() + " changed " + dude.getDisplayName() + "'s game mode to Creative!");
                }
            } else {
                NoPerms(sender);
            }
        }
    }

    public boolean isOnline(CommandSender sender, String[] args) {
        Player list[] = sender.getServer().getOnlinePlayers();
        for (Player checkname : list) {
            if (checkname.getDisplayName().equalsIgnoreCase(args[x])) {
                dude = checkname;
                found = true;
            }
        }
        if (found) {
            return true;
        } else {
            sender.sendMessage(namn + "Unable to find player " + ChatColor.DARK_RED + args[0] + ChatColor.GRAY + "!");
            return false;
        }
    }

    public void NoPerms(CommandSender sender) {
        sender.sendMessage(namn + ChatColor.RED + "You don't have permissions to do that!");
    }

    public void Survival(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        if (args.length < 1) {
            if (player.isOp() || player.hasPermission("fastswitch.survival")) {
                player.setGameMode(GameMode.SURVIVAL);
                sender.sendMessage(namn + "You changed your game mode to " + ChatColor.GREEN + "Survival" + ChatColor.GRAY + "!");
                log.info("[FastSwitch] " + player.getDisplayName() + " changed game mode to Survival!");
            } else {
                NoPerms(sender);
            }
        } else if (args.length == 1) {
            if (player.isOp() || player.hasPermission("fastswitch.survival.other")) {
                if (isOnline(sender, args)) {
                    dude.setGameMode(GameMode.SURVIVAL);
                    dude.sendMessage(namn + ChatColor.DARK_RED + player.getDisplayName() + ChatColor.GRAY + " changed your game mode to " + ChatColor.GREEN + "Survival" + ChatColor.GRAY + "!");
                    log.info("[FastSwitch] " + player.getDisplayName() + " changed " + dude.getDisplayName() + "'s game mode to Survival!");
                }
            } else {
                NoPerms(sender);
            }
        }
    }

    public void Console(CommandSender sender, Command command, String label, String[] args) {
        if (args.length > 1) {
            log.info("[FastSwitch] Too many arguments!");
        }
        if (args.length < 1) {
            log.info("[FastSwitch] Too few arguments!");
        }
        if (args.length == 1 && label.equalsIgnoreCase("survival")) {
            if (isOnline(sender, args)) {
                dude.setGameMode(GameMode.SURVIVAL);

                dude.sendMessage(namn + ChatColor.DARK_RED + "Console" + ChatColor.GRAY + " changed your game mode to " + ChatColor.GREEN + "Survival" + ChatColor.GRAY + "!");
                log.info("[FastSwitch] You changed " + dude.getDisplayName() + "'s game mode to Survival");
            }
        }
        if (args.length == 1 && label.equalsIgnoreCase("creative")) {
            if (isOnline(sender, args)) {
                dude.setGameMode(GameMode.CREATIVE);

                dude.sendMessage(namn + ChatColor.DARK_RED + "Console" + ChatColor.GRAY + " changed your game mode to " + ChatColor.GREEN + "Creative" + ChatColor.GRAY + "!");
                log.info("[FastSwitch] You changed " + dude.getDisplayName() + "'s game mode to Creative");
            }
        }
    }

    public void toggle(CommandSender sender) {
        Player player = (Player) sender;
        if (player.getGameMode().toString().equals("SURVIVAL")) {
            player.setGameMode(GameMode.CREATIVE);
            sender.sendMessage(namn + "You toggled your game mode to " + ChatColor.GREEN + "Creative" + ChatColor.GRAY + "!");
            log.info("[FastSwitch] " + player.getDisplayName() + " toggled game mode to Creative!");
        } else {
            player.setGameMode(GameMode.SURVIVAL);
            sender.sendMessage(namn + "You toggled your game mode to " + ChatColor.GREEN + "Survival" + ChatColor.GRAY + "!");
            log.info("[FastSwitch] " + player.getDisplayName() + " toggled game mode to Survival!");
        }
    }
    public void toggleother(CommandSender sender, String[] args){
        
    }
    public void FastSwitch(CommandSender sender, String[] args) {
        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("t") && (sender.isOp() || sender.hasPermission("fastswitch.toggle"))) {
                toggle(sender);
            }
        }
        if (args.length == 2 && (sender.isOp() || sender.hasPermission("fastswitch.toggle"))) {
            if (args[0].equalsIgnoreCase("t")) {
                x = 1;
                if(isOnline(sender, args))
                toggleother(sender, args);
            }
        }
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        x = 0;
        if (!(sender instanceof Player)) {
            Console(sender, command, label, args);
            found = false;
        } else {
            if (args.length > 1) {
                sender.sendMessage(namn + "Ahhrrgg! Can't handle the amount of arguments!");
            } else {
                if (label.equalsIgnoreCase("survival")) {
                    Survival(sender, args);
                }
                if (label.equalsIgnoreCase("fs")) {
                    FastSwitch(sender, args);
                }
                if (label.equalsIgnoreCase("creative")) {
                    Creative(sender, args);
                }

            }

            return true;
        }
        return true;
    }
}
