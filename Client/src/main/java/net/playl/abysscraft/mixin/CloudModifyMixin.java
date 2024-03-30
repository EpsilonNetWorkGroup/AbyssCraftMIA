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

import net.minecraft.client.option.CloudRenderMode;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.SimpleOption;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Vec3d;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorldRenderer.class)
public abstract class CloudModifyMixin {
    @Shadow public abstract void renderClouds(MatrixStack matrixStack, Matrix4f matrices, float tickDelta, double cameraX, double cameraY, double cameraZ);

    // 应该安全, 还可以WorldRenderEvents.LAST.register
    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/WorldRenderer;renderClouds(Lnet/minecraft/client/util/math/MatrixStack;Lorg/joml/Matrix4f;FDDD)V"))
    private void onRenderClouds(MatrixStack matrixStack, float tickDelta, long l, boolean bl, Camera camera, GameRenderer gameRenderer, LightmapTextureManager lightmapTextureManager, Matrix4f matrix4f, CallbackInfo ci) {
        Vec3d vec3d = camera.getPos();
        double cameraX = vec3d.getX();
        double cameraY = vec3d.getY();
        double cameraZ = vec3d.getZ();
        // 当渲染云时, 让我们多片在下方X格的位置, 同时改变了XZ, 移动时对比两个不同高度的云有动感
        renderClouds(matrixStack, matrix4f, tickDelta, cameraX*1.0F, cameraY+200F, cameraZ*0.5F);
        renderClouds(matrixStack, matrix4f, tickDelta, cameraX*1.5F, cameraY+300F, cameraZ*1.0F);
        renderClouds(matrixStack, matrix4f, tickDelta, cameraX*2.0F, cameraY+420F, cameraZ*1.5F);
    }

    @Mixin(GameOptions.class)
    public interface CloudOptionAccessor {
        @Accessor
        SimpleOption<CloudRenderMode> getCloudRenderMode();
    }
}
