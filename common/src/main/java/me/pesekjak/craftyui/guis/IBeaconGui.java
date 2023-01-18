package me.pesekjak.craftyui.guis;

import me.pesekjak.craftyui.Gui;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;

public interface IBeaconGui extends Gui {

    /**
     * Returns item in the first input slot of an anvil gui.
     * @return first input slot
     */
    @NotNull ItemStack getInput();

    /**
     * Changes item in the second input slot of this beacon gui.
     * @param item item
     */
    void setInput(@Nullable ItemStack item);

    /**
     * Gets current power level of this beacon gui.
     */
    @Range(from = 0, to = 4) int getPowerLevel();

    /**
     * Changes power level of the beacon and enabled effects.
     * @param powerLevel new power level
     */
    void setPowerLevel(@Range(from = 0, to = 4) int powerLevel);

    /**
     * Returns the first effect selected on the beacon.
     * @return first effect
     */
    @Nullable PotionEffectType getFirstEffect();

    /**
     * Changes the first selected effect of this beacon gui.
     * @param effect new effect
     */
    void setFirstEffect(@Nullable PotionEffectType effect);

    /**
     * Returns the second effect selected on the beacon.
     * @return second effect
     */
    @Nullable PotionEffectType getSecondEffect();

    /**
     * Changes the first selected effect of this beacon gui.
     * @param effect new effect
     */
    void setSecondEffect(@Nullable PotionEffectType effect);

    /**
     * Called when player apply change of active beacon effects.
     * @param effect1 first effect
     * @param effect2 second effect
     */
    void onEffectChange(@Nullable PotionEffectType effect1, @Nullable PotionEffectType effect2);

}
