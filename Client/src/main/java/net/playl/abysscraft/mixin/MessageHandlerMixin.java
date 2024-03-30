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

import net.minecraft.client.network.message.MessageHandler;
import net.minecraft.text.Text;
import net.playl.abysscraft.TransIndex;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import java.util.Objects;

@Mixin(MessageHandler.class)
public abstract class MessageHandlerMixin {
    @ModifyArg(method = "processChatMessageInternal", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/ChatHud;addMessage(Lnet/minecraft/text/Text;Lnet/minecraft/network/message/MessageSignatureData;Lnet/minecraft/client/gui/hud/MessageIndicator;)V"))
    private Text onProcessChatMessageInternal(Text text) {
        String json = Text.Serialization.toJsonString(text);
        // MIA有时使用组件，有时不使用, 而是拼接好的单个字符串组件, 在没有新的良好解决方案下, 最好使用模糊匹配
        String translationKey = TransIndex.PLAYERMSG.fuzzTranslationKey(json);
        if (translationKey != null) {
            String translationPart = Objects.requireNonNull(TransIndex.PLAYERMSG.translateToString(translationKey));
            json = json.replace(translationKey, translationPart);
            return Text.Serialization.fromJson(json);
        }
        return text;
    }
}
