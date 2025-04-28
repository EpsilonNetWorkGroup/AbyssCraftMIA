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

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.CloudRenderMode;
import net.minecraft.client.option.SimpleOption;
import net.playl.abysscraft.Client;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.URL;

@Mixin(MinecraftClient.class)
public abstract class ClientInitMixin {
    // 注入到游戏初始化完成
    @Inject(method = "onInitFinished", at = @At("HEAD"))
    private void onInitFinished(CallbackInfoReturnable<Runnable> cir) {
        // 强制开启云渲染, 高品质
        SimpleOption<CloudRenderMode> option = ((CloudModifyMixin.CloudOptionAccessor) Client.MC.options).getCloudRenderMode();
        option.setValue(CloudRenderMode.FANCY);
        Client.debugLog("设定游戏选项, 高品质云");
    }
}
