/*
 * This file is part of AbyssCraftMod
 * Copyright (C) 2024  AbyssCraftDev Team
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see https://github.com/EpsilonNetWorkGroup/AbyssCraftMIA.
 */

package net.playl.abysscraft.mixin;

import net.minecraft.client.gui.hud.DebugHud;
import net.playl.abysscraft.Client;
import net.playl.abysscraft.util.AbyssUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(DebugHud.class)
public abstract class DebugHudMixin {
    @Inject(method = "getLeftText", at = @At("TAIL"), cancellable = true)
    private void injectDebugLeft(CallbackInfoReturnable<List<String>> cir) {
        var ret = cir.getReturnValue();
        var camera = Client.MC.player;

        if (camera != null) {
            var blockPos = camera.getBlockPos();
            var x = blockPos.getX();
            var y = blockPos.getY();
            var z = blockPos.getZ();
            int section = AbyssUtil.getSection(x);
            String sectionName = AbyssUtil.getSectionName(section);

            AbyssUtil.Coords abyssCoords = AbyssUtil.toAbyss(x, y);
            x = (int)abyssCoords.x;
            y = (int)abyssCoords.y;

            ret.add("MIA Coords: " + x + " " + y + " " + z + " [" + sectionName + "]");
            cir.setReturnValue(ret);
        }
    }
}
