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

import net.minecraft.client.resource.language.I18n;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.List;

public class TransIndex {
    // 翻译索引, 对应MIA中英文到AbyssCraft可翻译键
    public static final Type<MutableText> ITEM_NAME = new Type<>(new HashMap<>());
    public static final Type<List<Text>> ITEM_LORE = new Type<>(new HashMap<>());
    public static final Type<MutableText> BOSS_BAR = new Type<>(new HashMap<>());
    public static final Type<MutableText> TITLE = new Type<>(new HashMap<>());
    public static final Type<MutableText> SUBTITLE = new Type<>(new HashMap<>());
    public static final Type<MutableText> SERVERMSG = new Type<>(new HashMap<>());
    public static final Type<MutableText> PLAYERMSG = new Type<>(new HashMap<>());
    public static class Type<T> {
        private final HashMap<String, T> I;

        private Type(HashMap<String, T> I) {
            this.I = I;
        }

        public boolean hasTranslation(String miaEnglish) {
            return I.containsKey(miaEnglish);
        }
        // 获取模糊翻译键
        public @Nullable String fuzzTranslationKey(String miaEnglish) {
            for (String key : I.keySet()) {
                if (miaEnglish.contains(key)) {
                    return key;
                }
            }
            return null;
        }
        public T translate(String miaEnglish) {
            return I.get(miaEnglish);
        }

        // 仅当泛型为MutableText时可用
        public @Nullable String translateToString(String miaEnglish) {
            if (translate(miaEnglish) instanceof MutableText e) {
                return I18n.translate(e.getString());
            }
            return null;
        }
    }

    static {
        SERVERMSG.I.put("Ore-Generation has been disabled.", Text.translatable("abysscraft.inGameHud.MiningModeOff"));
        SERVERMSG.I.put("Ore-Generation has been enabled.", Text.translatable("abysscraft.inGameHud.MiningModeOn"));
        SERVERMSG.I.put("You found a hidden vein!", Text.translatable("abysscraft.inGameHud.MiningFoundOre"));

        SERVERMSG.I.put("Cyatoria", Text.translatable("entity.abysscraft.cyatoria"));
        SERVERMSG.I.put("Fuwaki", Text.translatable("entity.abysscraft.fuwaki"));
        SERVERMSG.I.put("Hammerbeak", Text.translatable("entity.abysscraft.hammerbeak"));
        SERVERMSG.I.put("Okibo", Text.translatable("entity.abysscraft.okibo"));
        SERVERMSG.I.put("Silkfang", Text.translatable("entity.abysscraft.silkfang"));
        SERVERMSG.I.put("Splitjaw", Text.translatable("entity.abysscraft.splitjaw"));

        SERVERMSG.I.put("Orth", Text.translatable("abysscraft.region.orth.name"));
        SERVERMSG.I.put("Edge of the Abyss", Text.translatable("abysscraft.region.edge_of_the_abyss.name"));
        SERVERMSG.I.put("Forest of Temptation", Text.translatable("abysscraft.region.forest_of_temptation.name"));
        SERVERMSG.I.put("Great Fault", Text.translatable("abysscraft.region.great_fault.name"));
        SERVERMSG.I.put("Goblets of Giants", Text.translatable("abysscraft.region.the_goblets_of_giants.name"));
        SERVERMSG.I.put("Sea of Corpses", Text.translatable("abysscraft.region.sea_of_corpses.name"));

        PLAYERMSG.I.put("Orth", Text.translatable("abysscraft.region.orth.name"));
        PLAYERMSG.I.put("Edge", Text.translatable("abysscraft.region.edge_of_the_abyss.name"));
        PLAYERMSG.I.put("Forest", Text.translatable("abysscraft.region.forest_of_temptation.name"));
        PLAYERMSG.I.put("Fault", Text.translatable("abysscraft.region.great_fault.name"));
        PLAYERMSG.I.put("Goblets", Text.translatable("abysscraft.region.the_goblets_of_giants.name"));
        PLAYERMSG.I.put("Sea", Text.translatable("abysscraft.region.sea_of_corpses.name"));

        TITLE.I.put("Orth", Text.translatable("abysscraft.region.orth.name"));
        SUBTITLE.I.put("City of the Great Pit", Text.translatable("abysscraft.region.orth.description"));

        TITLE.I.put("Edge of the Abyss", Text.translatable("abysscraft.region.edge_of_the_abyss.name"));
        SUBTITLE.I.put("0-1510 blocks", Text.translatable("abysscraft.region.edge_of_the_abyss.description"));

        TITLE.I.put("Forest of Temptation", Text.translatable("abysscraft.region.forest_of_temptation.name"));
        SUBTITLE.I.put("1510-2580 blocks", Text.translatable("abysscraft.region.forest_of_temptation.description"));

        TITLE.I.put("Great Fault", Text.translatable("abysscraft.region.great_fault.name"));
        SUBTITLE.I.put("2580–4020 blocks", Text.translatable("abysscraft.region.great_fault.description"));

        TITLE.I.put("The Goblets of Giants", Text.translatable("abysscraft.region.the_goblets_of_giants.name"));
        SUBTITLE.I.put("4020–5850 blocks", Text.translatable("abysscraft.region.the_goblets_of_giants.description"));

        TITLE.I.put("Sea of Corpses", Text.translatable("abysscraft.region.sea_of_corpses.name"));
        SUBTITLE.I.put("5850–7200 blocks", Text.translatable("abysscraft.region.sea_of_corpses.description"));

        BOSS_BAR.I.put("Stamina", Text.translatable("abysscraft.inGameHud.bossBar.Stamina"));

        ITEM_NAME.I.put("Raw Cyatoria Drumstick", Text.translatable("item.abysscraft.cyatoria_drumstick"));
        ITEM_LORE.I.put("Raw Cyatoria Drumstick", ComplexRecords.LORE.Cyatoria_Drumstick.get());

        ITEM_NAME.I.put("Cooked Cyatoria Drumstick", Text.translatable("item.abysscraft.cyatoria_drumstick_cooked"));
        ITEM_LORE.I.put("Cooked Cyatoria Drumstick", ComplexRecords.LORE.Cyatoria_Drumstick_Cooked.get());
        ITEM_NAME.I.put("Cyatoria Feather", Text.translatable("item.abysscraft.cyatoria_feather"));
        ITEM_LORE.I.put("Cyatoria Feather", ComplexRecords.LORE.Cyatoria_Feather.get());

        ITEM_NAME.I.put("Fuwagi Foot", Text.translatable("item.abysscraft.fuwagi_foot"));
        ITEM_LORE.I.put("Fuwagi Foot", ComplexRecords.LORE.Fuwagi_Foot.get());
        ITEM_NAME.I.put("Fuwagi Hide", Text.translatable("item.abysscraft.fuwagi_hide"));
        ITEM_LORE.I.put("Fuwagi Hide", ComplexRecords.LORE.Fuwagi_Hide.get());

        ITEM_NAME.I.put("Raw Fuwagi Meat", Text.translatable("item.abysscraft.fuwagi_meat"));
        ITEM_LORE.I.put("Raw Fuwagi Meat", ComplexRecords.LORE.Fuwagi_Meat.get());
        ITEM_NAME.I.put("Cooked Fuwagi Meat", Text.translatable("item.abysscraft.fuwagi_meat_cooked"));
        ITEM_LORE.I.put("Cooked Fuwagi Meat", ComplexRecords.LORE.Fuwagi_Meat_Cooked.get());

        ITEM_NAME.I.put("Hammerbeak Beak", Text.translatable("item.abysscraft.hammerbeak_beak"));
        ITEM_LORE.I.put("Hammerbeak Beak", ComplexRecords.LORE.Hammerbeak_Beak.get());
        ITEM_NAME.I.put("Hammerbeak Egg", Text.translatable("item.abysscraft.hammerbeak_egg"));
        ITEM_LORE.I.put("Hammerbeak Egg", ComplexRecords.LORE.Hammerbeak_Egg.get());
        ITEM_NAME.I.put("Hammerbeak Feather", Text.translatable("item.abysscraft.hammerbeak_feather"));
        ITEM_LORE.I.put("Hammerbeak Feather", ComplexRecords.LORE.Hammerbeak_Feather.get());
        ITEM_NAME.I.put("Raw Hammerbeak Wing", Text.translatable("item.abysscraft.hammerbeak_wing"));
        ITEM_LORE.I.put("Raw Hammerbeak Wing", ComplexRecords.LORE.Hammerbeak_Wing.get());
        ITEM_NAME.I.put("Cooked Hammerbeak Wing", Text.translatable("item.abysscraft.hammerbeak_wing_cooked"));
        ITEM_LORE.I.put("Cooked Hammerbeak Wing", ComplexRecords.LORE.Hammerbeak_Wing_Cooked.get());

        ITEM_NAME.I.put("Okibo Leather", Text.translatable("item.abysscraft.okibo_leather"));
        ITEM_LORE.I.put("Okibo Leather", ComplexRecords.LORE.Okibo_Leather.get());
        ITEM_NAME.I.put("Raw Okibo Meat", Text.translatable("item.abysscraft.okibo_meat"));
        ITEM_LORE.I.put("Raw Okibo Meat", ComplexRecords.LORE.Okibo_Meat.get());
        ITEM_NAME.I.put("Cooked Okibo Meat", Text.translatable("item.abysscraft.okibo_meat_cooked"));
        ITEM_LORE.I.put("Cooked Okibo Meat", ComplexRecords.LORE.Okibo_Meat_Cooked.get());

        ITEM_NAME.I.put("Silkfang Eye", Text.translatable("item.abysscraft.silkfang_eye"));
        ITEM_LORE.I.put("Silkfang Eye", ComplexRecords.LORE.Silkfang_Eye.get());
        ITEM_NAME.I.put("Silkfang Silk", Text.translatable("item.abysscraft.silkfang_silk"));
        ITEM_LORE.I.put("Silkfang Silk", ComplexRecords.LORE.Silkfang_Silk.get());
        ITEM_NAME.I.put("Bundle of Silkfang Silk", Text.translatable("item.abysscraft.silkfang_silk_bundle"));
        ITEM_LORE.I.put("Bundle of Silkfang Silk", ComplexRecords.LORE.Silkfang_Silk_Bundle.get());

        ITEM_NAME.I.put("Splitjaw Gunk", Text.translatable("item.abysscraft.splitjaw_gunk"));
        ITEM_LORE.I.put("Splitjaw Gunk", ComplexRecords.LORE.Splitjaw_Gunk.get());
        ITEM_NAME.I.put("Splitjaw Scales", Text.translatable("item.abysscraft.splitjaw_scales"));
        ITEM_LORE.I.put("Splitjaw Scales", ComplexRecords.LORE.Splitjaw_Scales.get());
    }
}
