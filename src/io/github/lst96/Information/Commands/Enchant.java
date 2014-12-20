package io.github.lst96.Information.Commands;

import io.github.lst96.Information.Information;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;

public class Enchant
  implements CommandExecutor
{
  public Enchant(Information instance) {}
  
  public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
  {
    if ((!commandLabel.equalsIgnoreCase("enchantall")) || 
      (!(sender instanceof Player)))
    {
      sender.sendMessage(ChatColor.RED + "Error:" + ChatColor.DARK_RED + " Command can only be used by a player!");
      return true;
    }
    if ((sender.isOp()) || (sender.hasPermission("information.enchantall")))
    {
      Player player = (Player)sender;
      Material i = player.getItemInHand().getType();
      if ((i == Material.DIAMOND_SWORD) || (i == Material.GOLD_SWORD) || (i == Material.IRON_SWORD) || (i == Material.WOOD_SWORD) || (i == Material.STONE_SWORD))
      {
        player.getItemInHand().addEnchantment(Enchantment.DURABILITY, 3);
        player.getItemInHand().addEnchantment(Enchantment.FIRE_ASPECT, 2);
        player.getItemInHand().addEnchantment(Enchantment.DAMAGE_UNDEAD, 5);
        player.getItemInHand().addEnchantment(Enchantment.DAMAGE_ALL, 5);
        player.getItemInHand().addEnchantment(Enchantment.KNOCKBACK, 2);
        player.getItemInHand().addEnchantment(Enchantment.DAMAGE_ARTHROPODS, 5);
        player.getItemInHand().addEnchantment(Enchantment.LOOT_BONUS_MOBS, 3);
        return true;
      }
      if ((i == Material.DIAMOND_PICKAXE) || (i == Material.GOLD_PICKAXE) || (i == Material.IRON_PICKAXE) || (i == Material.WOOD_PICKAXE) || (i == Material.STONE_PICKAXE))
      {
        player.getItemInHand().addEnchantment(Enchantment.DURABILITY, 3);
        player.getItemInHand().addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 3);
        player.getItemInHand().addEnchantment(Enchantment.DIG_SPEED, 5);
        return true;
      }
      if ((i == Material.DIAMOND_AXE) || (i == Material.GOLD_AXE) || (i == Material.IRON_AXE) || (i == Material.WOOD_AXE) || (i == Material.STONE_AXE))
      {
        player.getItemInHand().addEnchantment(Enchantment.DURABILITY, 3);
        player.getItemInHand().addEnchantment(Enchantment.DIG_SPEED, 5);
        player.getItemInHand().addEnchantment(Enchantment.DAMAGE_UNDEAD, 5);
        player.getItemInHand().addEnchantment(Enchantment.DAMAGE_ALL, 5);
        player.getItemInHand().addEnchantment(Enchantment.DAMAGE_ARTHROPODS, 5);
        player.getItemInHand().addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 3);
        return true;
      }
      if ((i == Material.DIAMOND_SPADE) || (i == Material.GOLD_SPADE) || (i == Material.IRON_SPADE) || (i == Material.WOOD_SPADE) || (i == Material.STONE_SPADE))
      {
        player.getItemInHand().addEnchantment(Enchantment.DURABILITY, 3);
        player.getItemInHand().addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 3);
        player.getItemInHand().addEnchantment(Enchantment.DIG_SPEED, 5);
        return true;
      }
      if ((i == Material.DIAMOND_HOE) || (i == Material.GOLD_HOE) || (i == Material.IRON_HOE) || (i == Material.WOOD_HOE) || (i == Material.STONE_HOE))
      {
        player.getItemInHand().addEnchantment(Enchantment.DURABILITY, 3);
        return true;
      }
      if (i == Material.FISHING_ROD)
      {
        player.getItemInHand().addEnchantment(Enchantment.DURABILITY, 3);
        player.getItemInHand().addEnchantment(Enchantment.LUCK, 3);
        player.getItemInHand().addEnchantment(Enchantment.LURE, 3);
        return true;
      }
      if (i == Material.BOW)
      {
        player.getItemInHand().addEnchantment(Enchantment.ARROW_INFINITE, 1);
        player.getItemInHand().addEnchantment(Enchantment.ARROW_DAMAGE, 5);
        player.getItemInHand().addEnchantment(Enchantment.ARROW_FIRE, 1);
        player.getItemInHand().addEnchantment(Enchantment.ARROW_KNOCKBACK, 2);
        player.getItemInHand().addEnchantment(Enchantment.DURABILITY, 3);
        return true;
      }
      if ((i == Material.DIAMOND_HELMET) || (i == Material.GOLD_HELMET) || (i == Material.IRON_HELMET) || (i == Material.LEATHER_HELMET) || (i == Material.CHAINMAIL_HELMET))
      {
        player.getItemInHand().addEnchantment(Enchantment.DURABILITY, 3);
        player.getItemInHand().addEnchantment(Enchantment.WATER_WORKER, 1);
        player.getItemInHand().addEnchantment(Enchantment.OXYGEN, 3);
        player.getItemInHand().addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
        player.getItemInHand().addEnchantment(Enchantment.PROTECTION_FIRE, 4);
        player.getItemInHand().addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
        player.getItemInHand().addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
        player.getItemInHand().addEnchantment(Enchantment.THORNS, 3);
        return true;
      }
      if ((i == Material.DIAMOND_CHESTPLATE) || (i == Material.GOLD_CHESTPLATE) || (i == Material.IRON_CHESTPLATE) || (i == Material.LEATHER_CHESTPLATE) || (i == Material.CHAINMAIL_CHESTPLATE))
      {
        player.getItemInHand().addEnchantment(Enchantment.DURABILITY, 3);
        player.getItemInHand().addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
        player.getItemInHand().addEnchantment(Enchantment.PROTECTION_FIRE, 4);
        player.getItemInHand().addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
        player.getItemInHand().addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
        player.getItemInHand().addEnchantment(Enchantment.THORNS, 3);
        return true;
      }
      if ((i == Material.DIAMOND_LEGGINGS) || (i == Material.GOLD_LEGGINGS) || (i == Material.IRON_LEGGINGS) || (i == Material.LEATHER_LEGGINGS) || (i == Material.CHAINMAIL_LEGGINGS))
      {
        player.getItemInHand().addEnchantment(Enchantment.DURABILITY, 3);
        player.getItemInHand().addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
        player.getItemInHand().addEnchantment(Enchantment.PROTECTION_FIRE, 4);
        player.getItemInHand().addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
        player.getItemInHand().addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
        player.getItemInHand().addEnchantment(Enchantment.THORNS, 3);
        return true;
      }
      if ((i == Material.DIAMOND_BOOTS) || (i == Material.GOLD_BOOTS) || (i == Material.IRON_BOOTS) || (i == Material.LEATHER_BOOTS) || (i == Material.CHAINMAIL_BOOTS))
      {
        player.getItemInHand().addEnchantment(Enchantment.DURABILITY, 3);
        player.getItemInHand().addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
        player.getItemInHand().addEnchantment(Enchantment.PROTECTION_FIRE, 4);
        player.getItemInHand().addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
        player.getItemInHand().addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
        player.getItemInHand().addEnchantment(Enchantment.THORNS, 3);
        player.getItemInHand().addEnchantment(Enchantment.PROTECTION_FALL, 4);
        return true;
      }
      if ((i == Material.DIAMOND_SWORD) && (i == Material.GOLD_SWORD) && (i == Material.IRON_SWORD) && (i == Material.WOOD_SWORD) && 
        (i == Material.STONE_SWORD) && (i == Material.DIAMOND_PICKAXE) && (i == Material.GOLD_PICKAXE) && (i == Material.IRON_PICKAXE) && 
        (i == Material.WOOD_PICKAXE) && (i == Material.STONE_PICKAXE) && (i == Material.DIAMOND_AXE) && (i == Material.GOLD_AXE) && 
        (i == Material.IRON_AXE) && (i == Material.WOOD_AXE) && (i == Material.STONE_AXE) && (i == Material.DIAMOND_SPADE) && 
        (i == Material.GOLD_SPADE) && (i == Material.IRON_SPADE) && (i == Material.WOOD_SPADE) && (i == Material.STONE_SPADE) && 
        (i == Material.DIAMOND_HOE) && (i == Material.GOLD_HOE) && (i == Material.IRON_HOE) && (i == Material.WOOD_HOE) && 
        (i == Material.STONE_HOE) && (i == Material.FISHING_ROD) && (i == Material.BOW) && (i == Material.DIAMOND_HELMET) && 
        (i == Material.GOLD_HELMET) && (i == Material.IRON_HELMET) && (i == Material.LEATHER_HELMET) && (i == Material.CHAINMAIL_HELMET) && 
        (i == Material.DIAMOND_CHESTPLATE) && (i == Material.GOLD_CHESTPLATE) && (i == Material.IRON_CHESTPLATE) && 
        (i == Material.LEATHER_CHESTPLATE) && (i == Material.CHAINMAIL_CHESTPLATE) && (i == Material.DIAMOND_LEGGINGS) && 
        (i == Material.GOLD_LEGGINGS) && (i == Material.IRON_LEGGINGS) && (i == Material.LEATHER_LEGGINGS) && 
        (i == Material.CHAINMAIL_LEGGINGS) && (i == Material.DIAMOND_BOOTS) && (i == Material.GOLD_BOOTS) && (i == Material.IRON_BOOTS) && 
        (i == Material.LEATHER_BOOTS) && (i == Material.CHAINMAIL_BOOTS) && (i == Material.AIR)) {}
      player.sendMessage(ChatColor.RED + "Error:" + ChatColor.DARK_RED + " Item in hand can't be enchanted!");
      return true;
    }
    return false;
  }
}
