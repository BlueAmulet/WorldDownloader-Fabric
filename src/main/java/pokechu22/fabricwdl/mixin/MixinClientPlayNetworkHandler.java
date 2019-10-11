/*
 * This file is part of FabricWDL.  FabricWDL contains the fabric-specific
 * code for World Downloader: A mod to make backups of your multiplayer worlds.
 * http://www.minecraftforum.net/forums/mapping-and-modding/minecraft-mods/2520465
 *
 * Copyright (c) 2014 nairol, cubic72
 * Copyright (c) 2017-2018 Pokechu22, julialy
 *
 * This project is licensed under the MMPLv2.  The full text of the MMPL can be
 * found in LICENSE.md, or online at https://github.com/iopleke/MMPLv2/blob/master/LICENSE.md
 * For information about this the MMPLv2, see http://stopmodreposts.org/
 *
 * Do not redistribute (in modified or unmodified form) without prior permission.
 */
package pokechu22.fabricwdl.mixin;

import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.network.packet.BlockActionS2CPacket;
import net.minecraft.client.network.packet.ChatMessageS2CPacket;
import net.minecraft.client.network.packet.CustomPayloadS2CPacket;
import net.minecraft.client.network.packet.MapUpdateS2CPacket;
import net.minecraft.client.network.packet.UnloadChunkS2CPacket;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.text.Text;
import wdl.WDLHooks;
import wdl.ducks.IBaseChangesApplied;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={ClientPlayNetworkHandler.class})
public abstract class MixinClientPlayNetworkHandler implements ClientPlayPacketListener, IBaseChangesApplied {
  @Shadow
  private ClientWorld world;

  @Inject(method="onUnloadChunk", at=@At("HEAD"))
  private void onUnloadChunk(UnloadChunkS2CPacket packetIn, CallbackInfo ci) {
    WDLHooks.onNHPCHandleChunkUnload((ClientPlayNetworkHandler)(Object)this, this.world, packetIn);
  }

  @Inject(method="onDisconnected", at=@At("HEAD"))
  private void onDisconnected(Text reason, CallbackInfo ci) {
    WDLHooks.onNHPCDisconnect((ClientPlayNetworkHandler)(Object)this, reason);
  }

  @Inject(method="onChatMessage", at=@At("RETURN"))
  private void onChatMessage(ChatMessageS2CPacket p_147251_1_, CallbackInfo ci) {
    WDLHooks.onNHPCHandleChat((ClientPlayNetworkHandler)(Object)this, p_147251_1_);
  }

  @Inject(method="onBlockAction", at=@At("RETURN"))
  private void onBlockAction(BlockActionS2CPacket packetIn, CallbackInfo ci) {
    WDLHooks.onNHPCHandleBlockAction((ClientPlayNetworkHandler)(Object)this, packetIn);
  }

  @Inject(method="onMapUpdate", at=@At("RETURN"))
  private void onMapUpdate(MapUpdateS2CPacket packetIn, CallbackInfo ci) {
    WDLHooks.onNHPCHandleMaps((ClientPlayNetworkHandler)(Object)this, packetIn);
  }

  @Inject(method="onCustomPayload", at=@At("RETURN"))
  private void onCustomPayload(CustomPayloadS2CPacket packetIn, CallbackInfo ci) {
    WDLHooks.onNHPCHandleCustomPayload((ClientPlayNetworkHandler)(Object)this, packetIn);
  }
}

