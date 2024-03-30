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

import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableTextContent;
import net.playl.abysscraft.Client;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(targets = "net/minecraft/client/resource/server/ServerResourcePackLoader$3")
public abstract class ServerResourcePackLoaderMixin {

    @Shadow private Text toastTitle;

    @Inject(method = "showToast", at = @At(value = "HEAD"))
    private void onShowToast(CallbackInfo ci) {
        if (Client.enableCdn && !toastTitle.getString().contains("AbyssCraft")) {
            TranslatableTextContent content = (TranslatableTextContent) toastTitle.getContent();
            MutableText modifyTitle = Text.translatable(content.getKey(), content.getArgs()).append(" (AbyssCraft 资源包加速)");
            modifyTitle.getSiblings().addAll(toastTitle.getSiblings());
            modifyTitle.setStyle(toastTitle.getStyle());

            toastTitle = modifyTitle;
        }
    }

    @Inject(method = "onFinish", at = @At(value = "HEAD"))
    private void onFinish(boolean isSuccess, CallbackInfo ci) {
        if (Client.enableCdn && !isSuccess) {
            Client.LOGGER.error("出现资源包下载错误, 本次游戏中的资源包下载加速禁用");
            Client.enableCdn = false;
        }
    }
}
