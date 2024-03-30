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

import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Supplier;

public record ComplexRecords() {
    public record LORE() {
        // Supplier方便直接从主分支复制代码
        static Supplier<List<Text>> Cyatoria_Drumstick = () -> {
            List<Text> tooltip = new LinkedList<>();
            tooltip.add(Text.translatable("item.abysscraft.tooltip.drop_from",
                    Text.translatable("entity.abysscraft.cyatoria")
                            .styled(style -> style.withColor(Formatting.AQUA)),
                    Text.translatable("abysscraft.region.edge_of_the_abyss.name")
                            .styled(style -> style.withColor(Formatting.YELLOW)),
                    Text.translatable("abysscraft.region.forest_of_temptation.name")
                            .styled(style -> style.withColor(Formatting.YELLOW))));
            tooltip.add(Text.translatable("item.abysscraft.cyatoria_drumstick.tooltip_1")
                    .styled(style -> style.withColor(Formatting.GRAY)));
            tooltip.add(Text.translatable("item.abysscraft.cyatoria_drumstick.tooltip_2")
                    .styled(style -> style.withColor(Formatting.GREEN)));
            return tooltip;
        };
        static Supplier<List<Text>> Cyatoria_Drumstick_Cooked = () -> Cyatoria_Drumstick.get();
        static Supplier<List<Text>> Cyatoria_Feather = () -> {
            List<Text> tooltip = new LinkedList<>();
            tooltip.add(Text.translatable("item.abysscraft.tooltip.drop_from",
                    Text.translatable("entity.abysscraft.cyatoria")
                            .styled(style -> style.withColor(Formatting.AQUA)),
                    Text.translatable("abysscraft.region.edge_of_the_abyss.name")
                            .styled(style -> style.withColor(Formatting.YELLOW)),
                    Text.translatable("abysscraft.region.forest_of_temptation.name")
                            .styled(style -> style.withColor(Formatting.YELLOW))));

            tooltip.add(Text.translatable("item.abysscraft.cyatoria_feather.tooltip_1",
                            Text.translatable("entity.abysscraft.cyatoria")
                                    .styled(style -> style.withColor(Formatting.AQUA)))
                    .styled(style -> style.withColor(Formatting.GREEN)));
            return tooltip;
        };
        static Supplier<List<Text>> Fuwagi_Foot = () -> {
            List<Text> tooltip = new LinkedList<>();
            tooltip.add(Text.translatable("item.abysscraft.tooltip.drop_from",
                    Text.translatable("entity.abysscraft.fuwaki")
                            .styled(style -> style.withColor(Formatting.GRAY)),
                    Text.translatable("abysscraft.region.edge_of_the_abyss.name")
                            .styled(style -> style.withColor(Formatting.YELLOW)),
                    Text.translatable("abysscraft.region.forest_of_temptation.name")
                            .styled(style -> style.withColor(Formatting.YELLOW))));

            tooltip.add(Text.translatable("item.abysscraft.fuwagi_foot.tooltip_1"));

            tooltip.add(Text.translatable("item.abysscraft.fuwagi_foot.tooltip_2",
                            Text.translatable("item.abysscraft.fuwagi_foot")
                                    .styled(style -> style.withColor(Formatting.YELLOW)))
                    .styled(style -> style.withColor(Formatting.RED)));
            return tooltip;
        };
        static Supplier<List<Text>> Fuwagi_Hide = () -> {
            List<Text> tooltip = new LinkedList<>();
            tooltip.add(Text.translatable("item.abysscraft.tooltip.drop_from",
                    Text.translatable("entity.abysscraft.fuwaki")
                            .styled(style -> style.withColor(Formatting.GRAY)),
                    Text.translatable("abysscraft.region.edge_of_the_abyss.name")
                            .styled(style -> style.withColor(Formatting.YELLOW)),
                    Text.translatable("abysscraft.region.forest_of_temptation.name")
                            .styled(style -> style.withColor(Formatting.YELLOW))));

            tooltip.add(Text.translatable("item.abysscraft.fuwagi_hide.tooltip_1"));

            tooltip.add(Text.translatable("item.abysscraft.fuwagi_hide.tooltip_2")
                    .styled(style -> style.withColor(Formatting.RED)));
            return tooltip;
        };
        static Supplier<List<Text>> Fuwagi_Meat = () -> {
            List<Text> tooltip = new LinkedList<>();
            tooltip.add(Text.translatable("item.abysscraft.tooltip.drop_from",
                    Text.translatable("entity.abysscraft.fuwaki")
                            .styled(style -> style.withColor(Formatting.GRAY)),
                    Text.translatable("abysscraft.region.edge_of_the_abyss.name")
                            .styled(style -> style.withColor(Formatting.YELLOW)),
                    Text.translatable("abysscraft.region.forest_of_temptation.name")
                            .styled(style -> style.withColor(Formatting.YELLOW))));

            tooltip.add(Text.translatable("item.abysscraft.fuwagi_hide.tooltip_1"));

            tooltip.add(Text.translatable("item.abysscraft.fuwagi_meat.tooltip_2")
                    .styled(style -> style.withColor(Formatting.RED)));
            return tooltip;
        };
        static Supplier<List<Text>> Fuwagi_Meat_Cooked = () -> Fuwagi_Meat.get();
        static Supplier<List<Text>> Hammerbeak_Beak = () -> {
            List<Text> tooltip = new LinkedList<>();
            tooltip.add(Text.translatable("item.abysscraft.tooltip.drop_from",
                    Text.translatable("entity.abysscraft.hammerbeak")
                            .styled(style -> style.withColor(Formatting.GOLD)),
                    Text.translatable("abysscraft.region.edge_of_the_abyss.name")
                            .styled(style -> style.withColor(Formatting.YELLOW)),
                    Text.translatable("abysscraft.region.forest_of_temptation.name")
                            .styled(style -> style.withColor(Formatting.YELLOW))));

            tooltip.add(Text.translatable("item.abysscraft.hammerbeak_beak.tooltip_1"));

            tooltip.add(Text.translatable("item.abysscraft.hammerbeak_beak.tooltip_2")
                    .styled(style -> style.withColor(Formatting.BLUE)));

            return tooltip;
        };
        static Supplier<List<Text>> Hammerbeak_Egg = () -> {
            List<Text> tooltip = new LinkedList<>();
            tooltip.add(Text.translatable("item.abysscraft.tooltip.drop_from",
                    Text.translatable("entity.abysscraft.hammerbeak")
                            .styled(style -> style.withColor(Formatting.GOLD)),
                    Text.translatable("abysscraft.region.edge_of_the_abyss.name")
                            .styled(style -> style.withColor(Formatting.YELLOW)),
                    Text.translatable("abysscraft.region.forest_of_temptation.name")
                            .styled(style -> style.withColor(Formatting.YELLOW))));

            tooltip.add(Text.translatable("item.abysscraft.hammerbeak_egg.tooltip_1"));

            tooltip.add(Text.translatable("item.abysscraft.hammerbeak_egg.tooltip_2")
                    .styled(style -> style.withColor(Formatting.BLUE)));

            return tooltip;
        };
        static Supplier<List<Text>> Hammerbeak_Feather = () -> {
            List<Text> tooltip = new LinkedList<>();
            tooltip.add(Text.translatable("item.abysscraft.tooltip.drop_from",
                    Text.translatable("entity.abysscraft.hammerbeak")
                            .styled(style -> style.withColor(Formatting.GOLD)),
                    Text.translatable("abysscraft.region.edge_of_the_abyss.name")
                            .styled(style -> style.withColor(Formatting.YELLOW)),
                    Text.translatable("abysscraft.region.forest_of_temptation.name")
                            .styled(style -> style.withColor(Formatting.YELLOW))));

            tooltip.add(Text.translatable("item.abysscraft.hammerbeak_feather.tooltip_1")
                    .styled(style -> style.withColor(Formatting.BLUE)));

            return tooltip;
        };
        static Supplier<List<Text>> Hammerbeak_Wing = () -> {
            List<Text> tooltip = new LinkedList<>();
            tooltip.add(Text.translatable("item.abysscraft.tooltip.drop_from",
                    Text.translatable("entity.abysscraft.hammerbeak")
                            .styled(style -> style.withColor(Formatting.GOLD)),
                    Text.translatable("abysscraft.region.edge_of_the_abyss.name")
                            .styled(style -> style.withColor(Formatting.YELLOW)),
                    Text.translatable("abysscraft.region.forest_of_temptation.name")
                            .styled(style -> style.withColor(Formatting.YELLOW))));

            tooltip.add(Text.translatable("item.abysscraft.hammerbeak_wing.tooltip_1")
                    .styled(style -> style.withColor(Formatting.BLUE)));

            return tooltip;
        };
        static Supplier<List<Text>> Hammerbeak_Wing_Cooked = () -> {
            List<Text> tooltipList = Hammerbeak_Wing.get();
            tooltipList.remove(tooltipList.size()-1);

            tooltipList.add(Text.translatable("item.abysscraft.hammerbeak_wing_cooked.tooltip_1")
                    .styled(style -> style.withColor(Formatting.BLUE)));
            return tooltipList;
        };
        static Supplier<List<Text>> Okibo_Leather = () -> {
            List<Text> tooltip = new LinkedList<>();
            tooltip.add(Text.translatable("item.abysscraft.okibo_leather.tooltip_1",
                    Text.translatable("abysscraft.region.orth.name")
                            .styled(style -> style.withColor(Formatting.YELLOW))));

            tooltip.add(Text.translatable("item.abysscraft.okibo_leather.tooltip_2")
                    .styled(style -> style.withColor(Formatting.GRAY)));

            return tooltip;
        };
        static Supplier<List<Text>> Okibo_Meat = () -> {
            List<Text> tooltip = new LinkedList<>();
            tooltip.add(Text.translatable("item.abysscraft.okibo_meat.tooltip_1",
                    Text.translatable("abysscraft.region.orth.name")
                            .styled(style -> style.withColor(Formatting.YELLOW))));

            tooltip.add(Text.translatable("item.abysscraft.okibo_meat.tooltip_2")
                    .styled(style -> style.withColor(Formatting.YELLOW)));

            return tooltip;
        };
        static Supplier<List<Text>> Okibo_Meat_Cooked = () -> {
            List<Text> tooltip = new LinkedList<>();
            tooltip.add(Text.translatable("item.abysscraft.okibo_meat_cooked.tooltip_1")
                    .styled(style -> style.withColor(Formatting.YELLOW)));
            tooltip.add(Text.translatable("item.abysscraft.okibo_meat_cooked.tooltip_2",
                    Text.translatable("abysscraft.region.orth.name")
                            .styled(style -> style.withColor(Formatting.GREEN))));
            tooltip.add(Text.translatable("item.abysscraft.okibo_meat_cooked.tooltip_3",
                    Text.translatable("entity.abysscraft.okibo")
                            .styled(style -> style.withColor(Formatting.DARK_AQUA))));

            return tooltip;
        };
        static Supplier<List<Text>> Silkfang_Eye = () -> {
            List<Text> tooltip = new LinkedList<>();
            tooltip.add(Text.translatable("item.abysscraft.tooltip.drop_from",
                    Text.translatable("entity.abysscraft.silkfang")
                            .styled(style -> style.withColor(Formatting.DARK_BLUE)),
                    Text.translatable("abysscraft.region.edge_of_the_abyss.name")
                            .styled(style -> style.withColor(Formatting.YELLOW)),
                    Text.translatable("abysscraft.region.forest_of_temptation.name")
                            .styled(style -> style.withColor(Formatting.YELLOW))));

            tooltip.add(Text.translatable("item.abysscraft.silkfang_eye.tooltip_1")
                    .styled(style -> style.withColor(Formatting.RED)));
            return tooltip;
        };
        static Supplier<List<Text>> Silkfang_Silk = () -> {
            List<Text> tooltip = new LinkedList<>();
            tooltip.add(Text.translatable("item.abysscraft.tooltip.drop_from",
                    Text.translatable("entity.abysscraft.silkfang")
                            .styled(style -> style.withColor(Formatting.DARK_BLUE)),
                    Text.translatable("abysscraft.region.edge_of_the_abyss.name")
                            .styled(style -> style.withColor(Formatting.YELLOW)),
                    Text.translatable("abysscraft.region.forest_of_temptation.name")
                            .styled(style -> style.withColor(Formatting.YELLOW))));

            tooltip.add(Text.translatable("item.abysscraft.silkfang_silk.tooltip_1")
                    .styled(style -> style.withColor(Formatting.RED)));
            return tooltip;
        };
        static Supplier<List<Text>> Silkfang_Silk_Bundle = () -> {
            List<Text> tooltip = new LinkedList<>();
            tooltip.add(Text.translatable("item.abysscraft.tooltip.drop_from",
                    Text.translatable("entity.abysscraft.silkfang")
                            .styled(style -> style.withColor(Formatting.DARK_BLUE)),
                    Text.translatable("abysscraft.region.edge_of_the_abyss.name")
                            .styled(style -> style.withColor(Formatting.YELLOW)),
                    Text.translatable("abysscraft.region.forest_of_temptation.name")
                            .styled(style -> style.withColor(Formatting.YELLOW))));

            tooltip.add(Text.translatable("item.abysscraft.silkfang_silk_bundle.tooltip_1",
                    Text.translatable("entity.abysscraft.silkfang")
                            .styled(style -> style.withColor(Formatting.DARK_BLUE))));

            tooltip.add(Text.translatable("item.abysscraft.silkfang_silk_bundle.tooltip_2")
                    .styled(style -> style.withColor(Formatting.RED)));
            return tooltip;
        };
        static Supplier<List<Text>> Splitjaw_Gunk = () -> {
            List<Text> tooltip = new LinkedList<>();
            tooltip.add(Text.translatable("item.abysscraft.splitjaw_scales.tooltip_1",
                    Text.translatable("item.abysscraft.tooltip.drop_from",
                            Text.translatable("entity.abysscraft.splitjaw")
                                    .styled(style -> style.withColor(Formatting.DARK_PURPLE)),
                            Text.translatable("abysscraft.region.forest_of_temptation.name")
                                    .styled(style -> style.withColor(Formatting.YELLOW)),
                            Text.translatable("abysscraft.region.great_fault.name")
                                    .styled(style -> style.withColor(Formatting.YELLOW)))));


            tooltip.add(Text.translatable("item.abysscraft.splitjaw_gunk.tooltip_1")
                    .styled(style -> style.withColor(Formatting.GREEN)));
            return tooltip;
        };
        static Supplier<List<Text>> Splitjaw_Scales = () -> {
            List<Text> tooltip = new LinkedList<>();
            tooltip.add(Text.translatable("item.abysscraft.tooltip.drop_from",
                    Text.translatable("entity.abysscraft.splitjaw")
                            .styled(style -> style.withColor(Formatting.DARK_PURPLE)),
                    Text.translatable("abysscraft.region.forest_of_temptation.name")
                            .styled(style -> style.withColor(Formatting.YELLOW)),
                    Text.translatable("abysscraft.region.great_fault.name")
                            .styled(style -> style.withColor(Formatting.YELLOW))));

            tooltip.add(Text.translatable("item.abysscraft.splitjaw_scales.tooltip_2")
                    .styled(style -> style.withColor(Formatting.LIGHT_PURPLE)));
            return tooltip;
        };
    }
}
