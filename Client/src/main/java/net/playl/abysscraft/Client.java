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

package net.playl.abysscraft;

import com.google.gson.Gson;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.playl.abysscraft.listener.HudRenderListen;
import net.playl.abysscraft.listener.WorldRenderListen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Client implements ClientModInitializer {
    public static final String MODID = "{modid}";
    public static final String VERSION = "{version}";
    public static final Logger LOGGER = LoggerFactory.getLogger(MODID);
    public static final MinecraftClient MC = MinecraftClient.getInstance();
    public static final Gson GSON = new Gson();

    public static final MutableText explain = Text.literal(Client.MODID + ": "+ Client.VERSION);
    @Override
    public void onInitializeClient() {
        LOGGER.info("初始化AbyssCraft(深渊工艺)MIA客户端: "+VERSION);
        registerListener();
    }

    private void registerListener() {
        WorldRenderEvents.LAST.register(new WorldRenderListen());
        HudRenderCallback.EVENT.register(new HudRenderListen());
    }

    public static void debugLog(String str) {
        if (FabricLoader.getInstance().isDevelopmentEnvironment()) {
            LOGGER.warn(str);
        }
    }
}
