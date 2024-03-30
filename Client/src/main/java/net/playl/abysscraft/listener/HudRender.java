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

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.playl.abysscraft.util.world.TickRate;

import java.awt.*;

public class HudRender implements HudRenderCallback {
    @Override
    public void onHudRender(DrawContext drawContext, float tickDelta) {
        float timeSinceLastTick = TickRate.INSTANCE.getTimeSinceLastTick();

        if (timeSinceLastTick >= 1.5f) {
            TextRenderer renderer = MinecraftClient.getInstance().textRenderer;
            MutableText arg = Text.literal(String.format("%.1f", timeSinceLastTick)).styled(style -> {
                if (timeSinceLastTick > 10) return style.withColor(Formatting.DARK_RED);
                else if (timeSinceLastTick > 3) return style.withColor(Formatting.GOLD);
                else return style.withColor(Formatting.YELLOW);
            });
            MutableText text = Text.translatable("abysscraft.inGameHud.lagNotice", arg);
            int textWidth = renderer.getWidth(text);
            int width = (drawContext.getScaledWindowWidth() - textWidth) / 2;
            int height = (drawContext.getScaledWindowHeight()/2)-50;
            drawContext.drawTextWithShadow(renderer, text, width, height, Color.WHITE.getRGB());
        }
    }
}
