/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ehacks.mod.wrapper;

import ehacks.mod.gui.reeszrbteam.YouAlwaysWinClickGui;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.ChannelPromise;

/**
 *
 * @author radioegor146
 */
public class PacketHandler extends ChannelDuplexHandler {
    private Events eventHandler;
    
    public PacketHandler(Events eventHandler) {
        this.eventHandler = eventHandler;
        try {
            ChannelPipeline pipeline = Wrapper.INSTANCE.mc().getNetHandler().getNetworkManager().channel().pipeline();
            pipeline.addBefore("packet_handler", "PacketHandler", (ChannelHandler)this);
            YouAlwaysWinClickGui.log("[PacketHandler] Attached");
        }
        catch (Exception exception) {
            YouAlwaysWinClickGui.log("[PacketHandler] Error on attaching");
            exception.printStackTrace();
        }
    }

    public void channelRead(ChannelHandlerContext ctx, Object packet) throws Exception {
        if (!eventHandler.onPacket(packet, Side.IN))
            return;
        super.channelRead(ctx, packet);
    }

    public void write(ChannelHandlerContext ctx, Object packet, ChannelPromise promise) throws Exception {
        if (!eventHandler.onPacket(packet, Side.OUT))
            return;
        super.write(ctx, packet, promise);
    }

    public static enum Side {
        IN,
        OUT;
    }
}
