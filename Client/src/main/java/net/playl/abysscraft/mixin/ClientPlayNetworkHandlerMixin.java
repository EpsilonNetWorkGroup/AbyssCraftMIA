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

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.serialization.JsonOps;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.LoreComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.s2c.play.*;
import net.minecraft.text.*;
import net.playl.abysscraft.Client;
import net.playl.abysscraft.TransIndex;
import net.playl.abysscraft.util.world.TickRate;
import org.spongepowered.asm.mixin.Mixin;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;

@Mixin(ClientPlayNetworkHandler.class)
public abstract class ClientPlayNetworkHandlerMixin {
    @Inject(method = "onGameJoin", at = @At("TAIL"))
    private void onGameJoinTail(GameJoinS2CPacket gameJoinS2CPacket, CallbackInfo ci) {
        TickRate.INSTANCE.onJoinGame();
        MutableText text = Client.explain.copy().styled(style -> {
            HoverEvent e = new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                    Text.of("爱来自AbyssCraft(深渊工艺)\n" + "请加入我们的开发QQ群聊: 894229490"));
            return style.withHoverEvent(e);
        });
        Client.MC.inGameHud.getChatHud().addMessage(text);
    }

    @Inject(method = "onWorldTimeUpdate", at = @At("HEAD"))
    private void onWorldTimeUpdateHead(WorldTimeUpdateS2CPacket worldTimeUpdateS2CPacket, CallbackInfo ci) {
        TickRate.INSTANCE.onReceiveWorldTimeUpdateS2CPacket(worldTimeUpdateS2CPacket);
    }

    @Inject(method = "onInventory", at = @At("HEAD"))
    private void onInventory(InventoryS2CPacket inventoryS2CPacket, CallbackInfo ci) {
        inventoryS2CPacket.getContents().forEach(this::itemStackTranslation);
    }

    @Inject(method = "onScreenHandlerSlotUpdate", at = @At("HEAD"))
    private void onScreenHandlerSlotUpdate(ScreenHandlerSlotUpdateS2CPacket screenHandlerSlotUpdateS2CPacket, CallbackInfo ci) {
        itemStackTranslation(screenHandlerSlotUpdateS2CPacket.getStack());
    }

    @Unique
    private void itemStackTranslation(ItemStack i) {
        if (i.get(DataComponentTypes.CUSTOM_NAME) == null) {
            String customName = i.getName().getString();
            Style originStyle = i.getName().getStyle();
            if (TransIndex.ITEM_NAME.hasTranslation(customName)) {
                i.set(DataComponentTypes.CUSTOM_NAME, TransIndex.ITEM_NAME.translate(customName).setStyle(originStyle));
                // Lore
                if (TransIndex.ITEM_LORE.hasTranslation(customName)) {
                    i.remove(DataComponentTypes.LORE);
                    var newLore = TransIndex.ITEM_LORE.translate(customName);
                    i.set(DataComponentTypes.LORE, new LoreComponent(newLore));
                }
            }
        }
    }

    @Inject(method = "onBossBar", at = @At("HEAD"))
    private void onBossBar(BossBarS2CPacket bossBarS2CPacket, CallbackInfo i) {
        if (bossBarS2CPacket.action instanceof BossBarS2CPacket.AddAction a) {
            String originName = a.name.getString();
            Style originStyle = a.name.getStyle();
            if (TransIndex.BOSS_BAR.hasTranslation(originName)) {
                a.name = TransIndex.BOSS_BAR.translate(originName).setStyle(originStyle);
            }
        } else if (bossBarS2CPacket.action instanceof BossBarS2CPacket.UpdateNameAction a) {
            String originName = a.name.getString();
            Style originStyle = a.name.getStyle();
            if (TransIndex.BOSS_BAR.hasTranslation(originName)) {
                a.name = TransIndex.BOSS_BAR.translate(originName).setStyle(originStyle);
            }
        }
    }

    @Inject(method = "onTitle", at = @At("HEAD"))
    private void onTitle(TitleS2CPacket titleS2CPacket, CallbackInfo ci) {
        String originTitle = titleS2CPacket.text.getString();
        Style originStyle = titleS2CPacket.text.getStyle();
        if (TransIndex.TITLE.hasTranslation(originTitle)) {
            titleS2CPacket.text = TransIndex.TITLE.translate(originTitle).setStyle(originStyle);
        }
    }

    @Inject(method = "onSubtitle", at = @At("HEAD"))
    private void onSubtitle(SubtitleS2CPacket subtitleS2CPacket, CallbackInfo ci) {
        String originSubtitle = subtitleS2CPacket.text.getString();
        Style originStyle = subtitleS2CPacket.text.getStyle();
        if (TransIndex.SUBTITLE.hasTranslation(originSubtitle)) {
            subtitleS2CPacket.text = TransIndex.SUBTITLE.translate(originSubtitle).setStyle(originStyle);
        }
    }

    // FIXME: ikd why not work. TextCodecs.CODEC.decode fail
//    @Inject(method = "onGameMessage", at = @At("HEAD"))
//    private void onGameMessage(GameMessageS2CPacket gameMessageS2CPacket, CallbackInfo ci) {
//        if (!gameMessageS2CPacket.overlay()) {
//            String json = Client.GSON.toJson(TextCodecs.CODEC.encodeStart(JsonOps.INSTANCE, gameMessageS2CPacket.content));
//            // MIA有时使用组件，有时不使用, 而是拼接好的单个字符串组件, 在没有新的良好解决方案下, 最好使用模糊匹配
//            String translationKey;
//            while ((translationKey = TransIndex.SERVERMSG.fuzzTranslationKey(json)) != null) {
//                String translationPart = Objects.requireNonNull(TransIndex.SERVERMSG.translateToString(translationKey));
//                json = json.replace(translationKey, translationPart);
//            }
//            gameMessageS2CPacket.content = TextCodecs.CODEC
//                    .decode(JsonOps.INSTANCE, Client.GSON.fromJson(json, JsonElement.class))
//                    .getPartialOrThrow().getFirst();
//
//        }
//    }
}
