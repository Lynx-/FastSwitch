package wat.lynx.FastSwitch;

import java.util.logging.Logger;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class FastSwitch extends JavaPlugin {

    public int y;
    public Player playa;
    public Player checkname;
    public String matchednames = "";
    public int amt = 0;
    public String amtp;
    public int x;
    public String oplayer;
    public String namn = ChatColor.GOLD + "[" + ChatColor.DARK_GREEN + "FastSwitch" + ChatColor.GOLD + "]" + ChatColor.GRAY + " ";
    public Boolean found;
    public Player dude;
    public Plugin instance;
    static final Logger log = Logger.getLogger("Minecraft");

    public void onDisable() {
        log.info("[FastSwitch] Disabled!");
    }

    public void onEnable() {
        log.info("[FastSwitch] Enabled!");
    }

    public boolean hasPerms(Player player, String perm) {
        String perms;
        if(!player.isOp()){
        if (!player.hasPermission("fastswitch.*")) {
            if ("fastswitch.creative".equals(perm)) {
                perms = perm;
                if (player.hasPermission(perms) || player.hasPermission("fastswitch.change")) {
                    return true;
                } else {
                    NoPerms(player);
                    return false;
                }
            }
            if ("fastswitch.survival".equals(perm)) {
                perms = perm;
                if (player.hasPermission(perms) || player.hasPermission("fastswitch.change")) {
                    return true;
                } else {
                    NoPerms(player);
                    return false;
                }
            }
            if ("fastswitch.creative.other".equals(perm)) {
                perms = perm;
                if (player.hasPermission(perms) || player.hasPermission("fastswitch.change.other.*")) {
                    return true;
                } else {
                    NoPerms(player);
                    return false;
                }
            }
            if ("fastswitch.survival.other".equals(perm)) {
                perms = perm;
                if (player.hasPermission(perms) || player.hasPermission("fastswitch.change.other.*")) {
                    return true;
                } else {
                    NoPerms(player);
                    return false;
                }
            }
            if ("fastswitch.toggle".equals(perm)) {
                perms = perm;
                if (player.hasPermission(perms) || player.hasPermission("fastswitch.toggle.*")) {
                    return true;
                } else {
                    NoPerms(player);
                    return false;
                }
            }
            if ("fastswitch.toggle.other".equals(perm)) {
                perms = perm;
                if (player.hasPermission(perms) || player.hasPermission("fastswitch.toggle.*")) {
                    return true;
                } else {
                    NoPerms(player);
                    return false;
                }
            }
            if ("fastswitch.help".equals(perm)) {
                perms = perm;
                if (player.hasPermission(perms)) {
                    return true;
                } else {
                    NoPerms(player);
                    return false;
                }
            }
            NoPerms(player);
            return false;

        } else 
            return true;
        } else
            return true;
    }

    public void Creative(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        if (args.length < 1) {
            if (hasPerms(player, "fastswitch.creative")) {
                player.setGameMode(GameMode.CREATIVE);
                sender.sendMessage(namn + "You changed your game mode to " + ChatColor.GREEN + "Creative" + ChatColor.GRAY + "!");
                log.info("[FastSwitch] " + player.getDisplayName() + " changed game mode to Creative!");
            }
        } else if (args.length == 1) {
            if (hasPerms(player, "fastswitch.creative.other")) {
                if (isOnline(sender, args)) {
                    dude.setGameMode(GameMode.CREATIVE);
                    sender.sendMessage(namn + "You changed " + ChatColor.DARK_RED + dude.getDisplayName() + ChatColor.GRAY + "'s game mode to " + ChatColor.GREEN + "Creative" + ChatColor.GRAY + "!");
                    dude.sendMessage(namn + ChatColor.DARK_RED + player.getDisplayName() + ChatColor.GRAY + " changed your game mode to " + ChatColor.GREEN + "Creative" + ChatColor.GRAY + "!");
                    log.info("[FastSwitch] " + player.getDisplayName() + " changed " + dude.getDisplayName() + "'s game mode to Creative!");
                }
            }
        }
    }

    public boolean isOnline(CommandSender sender, String[] args) {
        Player list[] = sender.getServer().getOnlinePlayers();
        found = false;
        matchednames = "";
        checkname = null;
        amt = 0;
        if ("t".equals(args[0]) || "toggle".equals(args[0])) {
            x = 1;
        } else {
            x = 0;
        }
        for (Player checkname : list) {
            if (checkname.getDisplayName().toLowerCase().contains(args[x].toLowerCase())) {
                amt++;
                playa = checkname;
                matchednames += ChatColor.DARK_RED + checkname.getDisplayName() + ChatColor.GRAY + ", ";
            }
        }
        if (amt == 1) {
            dude = playa;
            found = true;
        }
        if (found) {
            return true;
        } else if (!found && amt == 0) {
            sender.sendMessage(namn + "Unable to find player " + ChatColor.DARK_RED + args[x] + ChatColor.GRAY + "!");
            return false;
        } else if (!found && amt > 1) {
            sender.sendMessage(namn + "Too many names matched!");
            sender.sendMessage(namn + "String entered: " + ChatColor.DARK_RED + args[x]);
            sender.sendMessage(namn + "Names matched: " + matchednames);
            return false;
        }
        return false;
    }

    public void NoPerms(CommandSender sender) {
        y++;
        if (y == 1) {
            sender.sendMessage(namn + ChatColor.RED + "You don't have permissions to do that!");
        }
    }

    public void Survival(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        if (args.length < 1) {
            if (hasPerms(player, "fastswitch.survival")) {
                player.setGameMode(GameMode.SURVIVAL);
                sender.sendMessage(namn + "You changed your game mode to " + ChatColor.GREEN + "Survival" + ChatColor.GRAY + "!");
                log.info("[FastSwitch] " + player.getDisplayName() + " changed game mode to Survival!");
            }
        } else if (args.length == 1) {
            if (hasPerms(player, "fastswitch.survival.other")) {
                if (isOnline(sender, args)) {
                    dude.setGameMode(GameMode.SURVIVAL);
                    sender.sendMessage(namn + "You changed " + ChatColor.DARK_RED + dude.getDisplayName() + ChatColor.GRAY + "'s game mode to " + ChatColor.GREEN + "Survival" + ChatColor.GRAY + "!");
                    dude.sendMessage(namn + ChatColor.DARK_RED + player.getDisplayName() + ChatColor.GRAY + " changed your game mode to " + ChatColor.GREEN + "Survival" + ChatColor.GRAY + "!");
                    log.info("[FastSwitch] " + player.getDisplayName() + " changed " + dude.getDisplayName() + "'s game mode to Survival!");
                }
            }
        }
    }

    public void Console(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("fs") && args.length != 0) {
            if (label.equalsIgnoreCase("fs") && !args[0].equalsIgnoreCase("t") && !args[0].equalsIgnoreCase("toggle") && !args[0].equalsIgnoreCase("t") && !args[0].equalsIgnoreCase("h") && !args[0].equalsIgnoreCase("help")) {
                log.info("[FastSwitch] Unknown Command!");
            }
        }
        if (args.length > 2) {
            log.info("[FastSwitch] Too many arguments!");
        }
        if (args.length < 1) {
            sendCHelp();
        }
        if (args.length > 0 && label.equalsIgnoreCase("fs") && (args[0].equalsIgnoreCase("h") || args[0].equalsIgnoreCase("help"))) {
            sendCHelp();
        }

        if (label.equalsIgnoreCase("fs") && args.length > 0 && (args[0].equalsIgnoreCase("t") || args[0].equalsIgnoreCase("toggle"))) {
            if (args.length == 2) {
                if (isOnline(sender, args)) {
                    Consoletoggle(sender, args);
                }
            }
            if (args.length == 1) {
                log.info("[FastSwitch] Too few arguments!");
            }

        }


        if (args.length == 1 && label.equalsIgnoreCase("survival")) {
            if (isOnline(sender, args)) {
                dude.setGameMode(GameMode.SURVIVAL);

                dude.sendMessage(namn + ChatColor.DARK_RED + "Console" + ChatColor.GRAY + " changed your game mode to " + ChatColor.GREEN + "Survival" + ChatColor.GRAY + "!");
                log.info("[FastSwitch] You changed " + dude.getDisplayName() + "'s game mode to Survival");
            }
        }

        if (args.length == 1 && label.equalsIgnoreCase(
                "creative")) {
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

    public void toggleother(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        if (dude.getGameMode().toString().equals("SURVIVAL")) {
            dude.setGameMode(GameMode.CREATIVE);
            sender.sendMessage(namn + "You changed " + dude.getDisplayName() + "'s game mode to " + ChatColor.GREEN + "Creative" + ChatColor.GRAY + "!");
            dude.sendMessage(namn + ChatColor.DARK_RED + player.getDisplayName() + ChatColor.GRAY + " toggled your game mode to " + ChatColor.GREEN + "Creative" + ChatColor.GRAY + "!");
            log.info("[FastSwitch] " + player.getDisplayName() + " toggled " + dude.getDisplayName() + "'s game mode to Creative!");

        } else {
            dude.setGameMode(GameMode.SURVIVAL);
            sender.sendMessage(namn + "You changed " + dude.getDisplayName() + "'s game mode  to " + ChatColor.GREEN + "Survival" + ChatColor.GRAY + "!");
            dude.sendMessage(namn + ChatColor.DARK_RED + player.getDisplayName() + ChatColor.GRAY + " toggled your game mode to " + ChatColor.GREEN + "Survival" + ChatColor.GRAY + "!");
            log.info("[FastSwitch] " + player.getDisplayName() + " toggled " + dude.getDisplayName() + "'s game mode to Survival!");
        }
    }

    public void sendCHelp() {
        String name = "[FastSwitch] ";
        log.info("[FastSwitch] Help Page");
        log.info(name + "FastSwitch Commands:");
        log.info(name + "/creative - Change to Creative mode");
        log.info(name + "/survival - Change to Survival mode");
        log.info(name + "/creative <name> - Creative mode for <name>");
        log.info(name + "/survival <name> - Survival mode for <name>");
        log.info(name + "/fs (t/toggle) - Toggles between the two modes");
        log.info(name + "/fs (t/toggle) <name> - Toggle a player's mode");
        log.info(name + "/fs (h/help) - Displays the Help Page");
        log.info(name + "/fs - Lists all commands");
    }

    public void sendHelp(CommandSender sender) {
        sender.sendMessage(namn + "FastSwitch Commands:");
        sender.sendMessage(namn + ChatColor.AQUA + "/creative " + ChatColor.RED + "-" + ChatColor.GRAY + " Change to Creative mode");
        sender.sendMessage(namn + ChatColor.AQUA + "/survival " + ChatColor.RED + "-" + ChatColor.GRAY + " Change to Survival mode");
        sender.sendMessage(namn + ChatColor.AQUA + "/creative <name> " + ChatColor.RED + "-" + ChatColor.GRAY + " Creative mode for <name>");
        sender.sendMessage(namn + ChatColor.AQUA + "/survival <name> " + ChatColor.RED + "-" + ChatColor.GRAY + " Survival mode for <name>");
        sender.sendMessage(namn + ChatColor.AQUA + "/fs (t/toggle) " + ChatColor.RED + "-" + ChatColor.GRAY + " Toggles between the two modes");
        sender.sendMessage(namn + ChatColor.AQUA + "/fs (t/toggle) <name> " + ChatColor.RED + "-" + ChatColor.GRAY + " Toggle a player's mode");
        sender.sendMessage(namn + ChatColor.AQUA + "/fs (h/help) " + ChatColor.RED + "-" + ChatColor.GRAY + " Displays the Help Page");
        sender.sendMessage(namn + ChatColor.AQUA + "/fs " + ChatColor.RED + "-" + ChatColor.GRAY + " Lists all commands");
    }

    public void FastSwitch(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        if (args.length == 0 && hasPerms(player, "fastswitch.help")) {
            sendHelp(sender);
        }
        if (args.length == 1) {
            if ((args[0].equalsIgnoreCase("t") || args[0].equalsIgnoreCase("toggle")) && hasPerms(player, "fastswitch.toggle")) {
                toggle(sender);
            }
            if ((args[0].equalsIgnoreCase("h") || args[0].equalsIgnoreCase("help")) && hasPerms(player, "fastswitch.help")) {
                sendHelp(sender);
            }
        }
        if (args.length == 2 && hasPerms(player, "fastswitch.toggle.other")) {
            if (args[0].equalsIgnoreCase("t")) {
                if (isOnline(sender, args)) {
                    toggleother(sender, args);
                }
            }
        }
    }

    public void Consoletoggle(CommandSender sender, String[] args) {
        if (dude.getGameMode().toString().equals("SURVIVAL")) {
            dude.setGameMode(GameMode.CREATIVE);
            dude.sendMessage(namn + ChatColor.DARK_RED + "Console" + ChatColor.GRAY + " toggled your game mode to " + ChatColor.GREEN + "Creative" + ChatColor.GRAY + "!");
            log.info("[FastSwitch] Console toggled " + dude.getDisplayName() + "'s game mode to Creative!");

        } else {
            dude.setGameMode(GameMode.SURVIVAL);
            dude.sendMessage(namn + ChatColor.DARK_RED + "Console" + ChatColor.GRAY + " toggled your game mode to " + ChatColor.GREEN + "Survival" + ChatColor.GRAY + "!");
            log.info("[FastSwitch] Console toggled " + dude.getDisplayName() + "'s game mode to Survival!");
        }
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        y = 0;
        x = 0;
        dude = null;
        found = false;
        if (!(sender instanceof Player)) {
            Console(sender, command, label, args);
        } else {
            if (args.length > 2) {
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