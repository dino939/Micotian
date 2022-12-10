//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package com.denger.naomitian.module.misc;

import com.denger.naomitian.utils.*;
import com.denger.naomitian.module.*;
import net.minecraftforge.fml.common.gameevent.*;
import net.minecraft.client.gui.inventory.*;
import net.minecraft.client.renderer.*;
import net.minecraft.item.*;
import net.minecraft.entity.player.*;
import java.util.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.inventory.*;
import net.minecraft.init.*;
import net.minecraft.util.*;
import net.minecraft.enchantment.*;

public class AutoArmor extends Module
{
    public TimerUtils timer;
    
    public AutoArmor() {
        super("AutoArmor", Category.OUTHER);
        this.timer = new TimerUtils();
    }
    
    public static boolean isNullOrEmpty(final ItemStack itemStack) {
        return itemStack == null;
    }
    
    @SubscribeEvent
    public void onClientTickEvent(final TickEvent.ClientTickEvent clientTickEvent) {
        if (!(AutoArmor.mc.currentScreen instanceof GuiContainer) || AutoArmor.mc.currentScreen instanceof InventoryEffectRenderer) {
            final InventoryPlayer inventory = AutoArmor.mc.player.inventory;
            if (AutoArmor.mc.player.movementInput.moveStrafe == 0.0f && AutoArmor.mc.player.movementInput.moveStrafe == 0.0f) {
                final int[] array = new int[4];
                final int[] array2 = new int[4];
                for (int i = 0; i < 4; ++i) {
                    array[i] = -1;
                    final ItemStack armorItemInSlot = inventory.armorItemInSlot(i);
                    if (!isNullOrEmpty(armorItemInSlot) && armorItemInSlot.getItem() instanceof ItemArmor) {
                        array2[i] = this.getArmorValue((ItemArmor)armorItemInSlot.getItem(), armorItemInSlot);
                    }
                }
                for (int j = 0; j < 36; ++j) {
                    final ItemStack getStackInSlot = inventory.getStackInSlot(j);
                    if (!isNullOrEmpty(getStackInSlot) && getStackInSlot.getItem() instanceof ItemArmor) {
                        final ItemArmor itemArmor = (ItemArmor)getStackInSlot.getItem();
                        final int getIndex = itemArmor.armorType.getIndex();
                        final int armorValue = this.getArmorValue(itemArmor, getStackInSlot);
                        if (armorValue > array2[getIndex]) {
                            array[getIndex] = j;
                            array2[getIndex] = armorValue;
                        }
                    }
                }
                final ArrayList<Integer> list = new ArrayList<Integer>(Arrays.asList(0, 1, 2, 3));
                Collections.shuffle(list);
                for (final int intValue : list) {
                    int n = array[intValue];
                    final ItemStack armorItemInSlot2;
                    if (n != -1 && (isNullOrEmpty(armorItemInSlot2 = inventory.armorItemInSlot(intValue)) || inventory.getFirstEmptyStack() != -1)) {
                        if (n < 9) {
                            n += 36;
                        }
                        if (!isNullOrEmpty(armorItemInSlot2)) {
                            AutoArmor.mc.playerController.windowClick(0, 8 - intValue, 0, ClickType.QUICK_MOVE, (EntityPlayer)AutoArmor.mc.player);
                        }
                        AutoArmor.mc.playerController.windowClick(0, n, 0, ClickType.QUICK_MOVE, (EntityPlayer)AutoArmor.mc.player);
                        break;
                    }
                }
            }
        }
    }
    
    int getArmorValue(final ItemArmor itemArmor, final ItemStack itemStack) {
        final int damageReduceAmount = itemArmor.damageReduceAmount;
        final int n = (int)itemArmor.toughness;
        final int getDamageReductionAmount = itemArmor.getArmorMaterial().getDamageReductionAmount(EntityEquipmentSlot.LEGS);
        final Enchantment PROTECTION = Enchantments.PROTECTION;
        return damageReduceAmount * 5 + PROTECTION.calcModifierDamage(EnchantmentHelper.getEnchantmentLevel(PROTECTION, itemStack), DamageSource.causePlayerDamage((EntityPlayer)AutoArmor.mc.player)) * 3 + n + getDamageReductionAmount;
    }
}
