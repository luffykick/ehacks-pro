/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ehacks.mod.modulesystem.classes;

import ehacks.api.module.Mod;
import ehacks.mod.gui.reeszrbteam.Tuple;
import ehacks.mod.gui.reeszrbteam.YouAlwaysWinClickGui;
import ehacks.mod.wrapper.ModuleCategories;
import ehacks.mod.wrapper.Wrapper;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.client.C17PacketCustomPayload;
import net.minecraft.util.MovingObjectPosition;
import net.minecraftforge.client.event.MouseEvent;
import org.lwjgl.input.Mouse;

/**
 *
 * @author radioegor146
 */
public class CarpenterOpener extends Mod {

    public CarpenterOpener() {
        super(ModuleCategories.EHACKS);
    }

    @Override
    public String getName() {
        return "ContOpener";
    }

    @Override
    public String getDescription() {
        return "Allows to open containers";
    }
    
    @Override
    public void onTick() {
        
    }
    
    @Override
    public void onEnableMod() {
        try {
            Class.forName("com.carpentersblocks.network.PacketActivateBlock").getConstructor();
        } catch (Exception ex) {
            this.off();
            YouAlwaysWinClickGui.logData.add(new Tuple<String, Integer>("[Container Opener] \u0422\u0443\u0442 \u043d\u0435 \u0440\u0430\u0431\u043e\u0442\u0430\u0435\u0442", 0));
        }
        canOpen = true;
    }
    
    public boolean canOpen = true;
    
    @Override
    public void onMouse(MouseEvent event) {
        try
        {
            MovingObjectPosition position = Wrapper.INSTANCE.mc().objectMouseOver;
            if (!Mouse.isButtonDown(1))
                canOpen = true;
            if (position.sideHit != -1 && Mouse.isButtonDown(1) && canOpen)
            {
                canOpen = false;
                ByteBuf buf = Unpooled.buffer();
                buf.writeInt(0);
                buf.writeInt(position.blockX);
                buf.writeInt(position.blockY);
                buf.writeInt(position.blockZ);
                buf.writeInt(position.sideHit);
                C17PacketCustomPayload packet = new C17PacketCustomPayload("CarpentersBlocks", buf);
                Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(packet);
                if (event.isCancelable())
                    event.setCanceled(true);
            }
        }
        catch (Exception e)
        {

        }
    }
    
}