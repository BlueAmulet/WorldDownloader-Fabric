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

import java.io.PrintStream;
import net.minecraft.util.crash.CrashReport;
import net.minecraft.util.crash.CrashReportSection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import wdl.WDLHooks;
import wdl.ducks.IBaseChangesApplied;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={CrashReport.class})
public abstract class MixinCrashReport implements IBaseChangesApplied {
  @Inject(method="fillSystemDetails", at=@At("RETURN"))
  private void onCrashReportPopulateEnvironment(CallbackInfo ci) {
    try {
      WDLHooks.onCrashReportPopulateEnvironment((CrashReport)(Object)this);
    } catch (Throwable t) {
      try {
        final Logger LOGGER = LogManager.getLogger();
        LOGGER.fatal("World Downloader: Failed to add crash info", t);
        ((CrashReport)(Object)this).getSystemDetailsSection().add("World Downloader - Fatal error in crash handler (see log)", t);
      }
      catch (Throwable t2) {
        System.err.println("WDL: Double failure adding info to crash report!");
        t.printStackTrace();
        t2.printStackTrace();
      }
    }
  }
}

