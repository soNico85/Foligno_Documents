package dev.nico85.commands;

import dev.nico85.database.PlayerDataManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.jetbrains.annotations.NotNull;

public class Documenticommand implements CommandExecutor
{

    private PlayerDataManager playerDataManager;

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args)
    {
        Player p = (Player) sender;
        if(p.hasPermission("Foligno.municipio.cartaid"))
        {
            if(!(args.length == 3)){
                messageSender("Errore Argomenti",p);

            }else {
                String arg0 = args[0];
                String arg1 = args[1];
                Player arg2 = Bukkit.getPlayer(args[2]);
                if(arg0.equalsIgnoreCase("crea"))
                {

                    if(arg1.equalsIgnoreCase("cartaid"))
                    {
                        if(arg2 != null)
                        {
                            if(p.getNearbyEntities(3, 3, 3).contains(arg2))
                            {
                                if (playerDataManager.isPlayerRegistered(arg2))
                                {
                                    ItemStack documento = new ItemStack(Material.WRITTEN_BOOK);
                                    BookMeta bookMeta = (BookMeta) documento;
                                    getDocumentsinfo("cartaid", bookMeta, arg2, playerDataManager.getPlayerAge(arg2), playerDataManager.getPlayerSex(arg2));
                                    documento.setItemMeta(bookMeta);
                                    p.getInventory().addItem(documento);
                                }else{

                                }
                            }else
                            {
                                messageSender("Giocatore Distante",p);
                            }
                        }else
                        {
                            messageSender("Giocatore Offline",p);
                        }
                    }else
                    {
                        messageSender("Errore Argomenti",p);
                    }
                }else
                {
                    messageSender("Errore Argomenti",p);
                }

            }
        }
        return false;
    }


    public void getDocumentsinfo(String scelta, BookMeta documento, Player p, int eta, String sesso) {
        if (scelta.equalsIgnoreCase("cartaid")) {
            documento.setAuthor("§bFoligno");
            documento.setTitle("§bCarta di identità");
            documento.setDisplayName("§bCarta di identita");
            documento.addPage( "§7§lCarta di identità \n\n §bCittadino: §r \n - %player% \n\n §bEtà: §r\n - %eta% \n\n §bSesso: §r \n - %sesso%".replace("%sesso%", sesso).replace("%eta%", String.valueOf(eta)).replace("%player%", p.getName()));
        }
    }

    public void messageSender(String scelta, Player p){
        if ( scelta.equalsIgnoreCase("Errore Argomenti")){
            p.sendMessage("§7------ FOLIGNO - DOCUMENTS ------");
            p.sendMessage("");
            p.sendMessage("§b§l/documenti crea [cartaid,patente,pda] [player]");
            p.sendMessage("");
            p.sendMessage("§7------ FOLIGNO - DOCUMENTS ------");
        } else if (scelta.equalsIgnoreCase("Giocatore Offline")) {
            p.sendMessage("§7------ FOLIGNO - DOCUMENTS ------");
            p.sendMessage("");
            p.sendMessage("§c§lIl giocatore inserito non risulta online");
            p.sendMessage("");
            p.sendMessage("§7------ FOLIGNO - DOCUMENTS ------");

        } else if (scelta.equalsIgnoreCase("Giocatore Distante")) {
            p.sendMessage("§7------ FOLIGNO - DOCUMENTS ------");
            p.sendMessage("");
            p.sendMessage("§c§lIl giocatore inserito è troppo distante");
            p.sendMessage("");
            p.sendMessage("§7------ FOLIGNO - DOCUMENTS ------");
        }
    }
}
