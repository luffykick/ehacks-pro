package ehacks.mod.gui.reeszrbteam.window;

import ehacks.api.module.ModController;
import ehacks.api.module.Mod;
import ehacks.mod.gui.reeszrbteam.element.YAWWindow;
import ehacks.mod.wrapper.ModuleCategory;

public class WindowPlayer
extends YAWWindow {
    public WindowPlayer() {
        super("Player", 94, 2);
    }

    public YAWWindow init() {
        for (Mod mod : ModController.INSTANCE.mods) {
            if (mod.getCategory() != ModuleCategory.PLAYER) continue;
            this.addButton(mod);
        }
        this.setup();
        return this;
    }
}

