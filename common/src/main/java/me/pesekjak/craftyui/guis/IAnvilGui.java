package me.pesekjak.craftyui.guis;

import me.pesekjak.craftyui.Gui;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface IAnvilGui extends Gui {

    /**
     * Returns item in the first input slot of this anvil gui.
     * @return first input slot
     */
    @NotNull ItemStack getFirstInput();

    /**
     * Changes item in the first input slot of this anvil gui.
     * @param item item
     */
    void setFirstInput(@Nullable ItemStack item);

    /**
     * Returns item in the second input slot of this anvil gui.
     * @return second input slot
     */
    @NotNull ItemStack getSecondInput();

    /**
     * Changes item in the second input slot of this anvil gui.
     * @param item item
     */
    void setSecondInput(@Nullable ItemStack item);

    /**
     * Returns item in the output slot of this anvil gui.
     * @return output slot
     */
    @NotNull ItemStack getOutput();

    /**
     * Changes item in the output slot of this anvil gui.
     * @param item item
     */
    void setOutput(@Nullable ItemStack item);

    /**
     * Changes the name of the output item.
     * @param name new name
     */
    void setOutputName(@Nullable String name);

    /**
     * Returns repair cost displayed in this anvil gui.
     * @return repair cost
     */
    int getRepairCost();

    /**
     * Changes the repair cost of the anvil to a new value.
     * @param repairCost new repair cost
     */
    void setRepairCost(int repairCost);

    /**
     * Returns current text input in the anvil text field.
     * @return input field content
     */
    @NotNull @NonNls String getTextInput();

    /**
     * Changes the text input of the anvil text field by changing
     * the name of the item in the input slot.
     */
    void setTextInput(@NotNull String input);

    /**
     * Called when text input field is updated.
     * @param input new input
     */
    void onTextInputChange(@NotNull String input);

}
