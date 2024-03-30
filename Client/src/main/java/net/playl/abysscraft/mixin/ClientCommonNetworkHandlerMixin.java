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

import net.minecraft.client.network.ClientCommonNetworkHandler;
import net.minecraft.network.packet.s2c.common.ResourcePackSendS2CPacket;
import net.playl.abysscraft.Client;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ClientCommonNetworkHandler.class)
public abstract class ClientCommonNetworkHandlerMixin {
    // 修改获取到的资源包下载链接
    @Redirect(method = "onResourcePackSend", at = @At(value = "INVOKE", target = "Lnet/minecraft/network/packet/s2c/common/ResourcePackSendS2CPacket;url()Ljava/lang/String;"))
    private String onResourcePackSend(ResourcePackSendS2CPacket instance) {
        if (Client.enableCdn) {
            String newUrl = instance.url().replaceFirst(Client.MIADOMAIN, Client.CDNDOMAIN);
            Client.LOGGER.info("下载资源包加速后URL: " + newUrl);
            return newUrl;
        }
        return instance.url();
    }
}
