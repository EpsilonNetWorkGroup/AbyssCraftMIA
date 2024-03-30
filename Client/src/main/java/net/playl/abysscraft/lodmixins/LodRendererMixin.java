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

package net.playl.abysscraft.lodmixins;

import com.seibel.distanthorizons.core.render.renderer.LodRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = LodRenderer.class, remap = false)
public abstract class LodRendererMixin {
    // region - Fix glIsProgram performance
    // This check called too many times during rendering frame, it just slows everything down and doesn't aclually handle any issues.
    // https://github.com/kawashirov/distant-horizons-core/commit/48cb52f2a375aa97c194b51f41b391560814ea6e

    @Redirect(method = "setupOffset", at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL32;glIsProgram(I)Z"))
    public boolean onCallGlIsProgram(int i) {
        return true;
    }

    // endregion
}
