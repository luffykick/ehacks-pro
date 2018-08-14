package ehacks.mod.gui.window;

import ehacks.api.module.ModController;
import ehacks.api.module.Mod;
import ehacks.mod.gui.element.ModWindow;
import ehacks.mod.wrapper.ModuleCategory;

public class WindowNoCheatPlus
extends ModWindow {
    public WindowNoCheatPlus() {
        super("NoCheatPlus", 462, 2);
    }

    public ModWindow init() {
        for (Mod mod : ModController.INSTANCE.mods) {
            if (mod.getCategory() != ModuleCategory.NOCHEATPLUS) continue;
            this.addButton(mod);
        }
        this.setup();
        return this;
    }
}
