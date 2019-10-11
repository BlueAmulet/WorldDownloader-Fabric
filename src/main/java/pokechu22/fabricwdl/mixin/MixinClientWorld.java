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

import java.util.function.BiFunction;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.world.ClientChunkManager;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.profiler.Profiler;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkManager;
import net.minecraft.world.chunk.WorldChunk;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.level.LevelInfo;
import net.minecraft.world.level.LevelProperties;
import wdl.WDLHooks;
import wdl.ducks.IBaseChangesApplied;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={ClientWorld.class})
public abstract class MixinClientWorld extends World implements IBaseChangesApplied {
  protected MixinClientWorld(ClientPlayNetworkHandler p_i51056_1_, LevelInfo p_i51056_2_, DimensionType dimType, int p_i51056_4_, Profiler p_i51056_5_, WorldRenderer p_i51056_6_) {
    super(new LevelProperties(p_i51056_2_, "MpServer"), dimType, (p_217422_1_, p_217422_2_) -> new ClientChunkManager((ClientWorld)p_217422_1_, p_i51056_4_), p_i51056_5_, true);
  }

  @Inject(method="tick", at=@At("RETURN"))
  private void onTick(CallbackInfo ci) {
    WDLHooks.onWorldClientTick((ClientWorld)(Object)this);
  }

  @Inject(method="removeEntity", at=@At("HEAD"))
  private void onRemoveEntity(int p_73028_1_, CallbackInfo ci) {
    WDLHooks.onWorldClientRemoveEntityFromWorld((ClientWorld)(Object)this, p_73028_1_);
  }
}

