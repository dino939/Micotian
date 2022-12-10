/*
 * Decompiled with CFR 0.150.
 */
package Caesium.listeners;

import Caesium.components.GuiButton;
import com.denger.naomitian.Micotian;
import com.denger.naomitian.module.Module;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClickListener
implements ActionListener {
    private GuiButton button;

    public ClickListener(GuiButton button) {
        this.button = button;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        Module m = Micotian.instance.moduleManager.getModule(this.button.getText());
        m.toggle();
    }
}

