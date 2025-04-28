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

package net.playl.abysscraft.listener;

import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderContext;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.minecraft.util.math.Vec3d;
import net.playl.abysscraft.Client;

public class WorldRenderListen implements WorldRenderEvents.Last {
    @Override
    public void onLast(WorldRenderContext worldRenderContext) {
        Vec3d vec3d = worldRenderContext.camera().getPos();
        double cameraX = vec3d.getX();
        double cameraY = vec3d.getY();
        double cameraZ = vec3d.getZ();

        var worldRender = worldRenderContext.worldRenderer();
        // 当渲染云时, 让我们多片在下方X格的位置, 同时改变了XZ, 移动时对比两个不同高度的云有动感
        var ms = worldRenderContext.matrixStack();
        var posm = worldRenderContext.positionMatrix();
        var projm = worldRenderContext.projectionMatrix();
        var ticketDelta = worldRenderContext.tickCounter().getTickDelta(false);
        worldRender.renderClouds(
                ms, posm, projm, ticketDelta,
                cameraX * 1.0F, cameraY + 200F, cameraZ * 0.5F);
        worldRender.renderClouds(
                ms, posm, projm, ticketDelta,
                cameraX * 1.5F, cameraY + 300F, cameraZ * 1.0F);
        worldRender.renderClouds(
                ms, posm, projm, ticketDelta,
                cameraX * 2.0F, cameraY + 420F, cameraZ * 1.5F);
    }
}
