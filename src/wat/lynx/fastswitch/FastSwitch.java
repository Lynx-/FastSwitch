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
    public void Creative(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        if (args.length < 1) {
            if (player.isOp() || player.hasPermission("fastswitch.creative") || player.hasPermission("fastswitch.*")) {
                player.setGameMode(GameMode.CREATIVE);
                sender.sendMessage(namn + "You changed your game mode to " + ChatColor.GREEN + "Creative" + ChatColor.GRAY + "!");
                log.info("[FastSwitch] " + player.getDisplayName() + " changed game mode to Creative!");
            } else {
                NoPerms(sender);
            }
        } else if (args.length == 1) {
            if (player.isOp() || player.hasPermission("fastswitch.creative.other") || player.hasPermission("fastswitch.*")) {
                if (isOnline(sender, args)) {
                    dude.setGameMode(GameMode.CREATIVE);
                    sender.sendMessage(namn + "You changed " + ChatColor.DARK_RED + dude.getDisplayName() + ChatColor.GRAY + "'s game mode to " + ChatColor.GREEN + "Creative" + ChatColor.GRAY + "!");
                    dude.sendMessage(namn + ChatColor.DARK_RED + player.getDisplayName() + ChatColor.GRAY + " changed your game mode to " + ChatColor.GREEN + "Creative" + ChatColor.GRAY + "!");
                    log.info("[FastSwitch] " + player.getDisplayName() + " changed " + dude.getDisplayName() + "'s game mode to Creative!");
                }
            } else {
                NoPerms(sender);
            }
        }
    }

    public boolean isOnline(CommandSender sender, String[] args) {
        Player list[] = sender.getServer().getOnlinePlayers();
        found = false;
        if ("t".equals(args[0]) || "toggle".equals(args[0])) {
            x = 1;
        } else {
            x = 0;
        }
        for (Player checkname : list) {
            if (checkname.getDisplayName().equalsIgnoreCase(args[x])) {
                dude = checkname;
                found = true;
            }
        }
        if (found) {
            return true;
        } else {
            sender.sendMessage(namn + "Unable to find player " + ChatColor.DARK_RED + args[x] + ChatColor.GRAY + "!");
            return false;
        }
    }

    public void NoPerms(CommandSender sender) {
        sender.sendMessage(namn + ChatColor.RED + "You don't have permissions to do that!");
    }

    public void Survival(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        if (args.length < 1) {
            if (player.isOp() || player.hasPermission("fastswitch.survival") || player.hasPermission("fastswitch.*")) {
                player.setGameMode(GameMode.SURVIVAL);
                sender.sendMessage(namn + "You changed your game mode to " + ChatColor.GREEN + "Survival" + ChatColor.GRAY + "!");
                log.info("[FastSwitch] " + player.getDisplayName() + " changed game mode to Survival!");
            } else {
                NoPerms(sender);
            }
        } else if (args.length == 1) {
            if (player.isOp() || player.hasPermission("fastswitch.survival.other") || player.hasPermission("fastswitch.*")) {
                if (isOnline(sender, args)) {
                    dude.setGameMode(GameMode.SURVIVAL);
                    sender.sendMessage(namn + "You changed " + ChatColor.DARK_RED + dude.getDisplayName() + ChatColor.GRAY + "'s game mode to " + ChatColor.GREEN + "Survival" + ChatColor.GRAY + "!");
                    dude.sendMessage(namn + ChatColor.DARK_RED + player.getDisplayName() + ChatColor.GRAY + " changed your game mode to " + ChatColor.GREEN + "Survival" + ChatColor.GRAY + "!");
                    log.info("[FastSwitch] " + player.getDisplayName() + " changed " + dude.getDisplayName() + "'s game mode to Survival!");
                }
            } else {
                NoPerms(sender);
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
        if (args.length == 0 && (sender.isOp() || sender.hasPermission("fastswitch.help") || sender.hasPermission("fastswitch.*"))) {
            sendHelp(sender);
        }
        if (args.length == 1) {
            if ((args[0].equalsIgnoreCase("t") || args[0].equalsIgnoreCase("toggle")) && (sender.isOp() || sender.hasPermission("fastswitch.toggle") || sender.hasPermission("fastswitch.*"))) {
                toggle(sender);
            } else if ((args[0].equalsIgnoreCase("t") || args[0].equalsIgnoreCase("toggle")) && !sender.hasPermission("fastswitch.toggle") && !sender.isOp()) {
                NoPerms(sender);
            }
            if ((args[0].equalsIgnoreCase("h") || args[0].equalsIgnoreCase("help")) && (sender.isOp() || sender.hasPermission("fastswitch.help") || sender.hasPermission("fastswitch.*"))) {
                sendHelp(sender);
            } else if ((args[0].equalsIgnoreCase("h") || args[0].equalsIgnoreCase("help")) && (!sender.isOp() && !sender.hasPermission("fastswitch.help"))) {
                NoPerms(sender);
            }
        }
        if (args.length == 2 && (sender.isOp() || sender.hasPermission("fastswitch.toggle.other") || sender.hasPermission("fastswitch.*"))) {
            if (args[0].equalsIgnoreCase("t")) {
                if (isOnline(sender, args)) {
                    toggleother(sender, args);
                }
            }
        } else if (!sender.isOp() && !sender.hasPermission("fastswitch.toggle.other") && !sender.hasPermission("fastswitch.*")) {
            NoPerms(sender);
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