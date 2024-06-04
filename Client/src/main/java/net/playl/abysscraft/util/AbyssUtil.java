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

package net.playl.abysscraft.util;

/**
 * This tool allows to get procedural coordinates from mia distorted worlds
 * @see <a href="https://github.com/semyon422/voxy/blob/c0ce82919df769a25bea011f3ccd8535a2688855/src/main/java/me/cortex/voxy/client/core/util/AbyssUtil.java">
 *     origin code</a>
 */
public class AbyssUtil {
    private static final int ABYSS_WY = -256;
    private static final int ABYSS_WH = 512;
    private static final int ABYSS_DX = 16384;
    private static final int ABYSS_DY = 480;
    private static final int ABYSS_OVERLAP = 32;
    private static final int ABYSS_SECTIONS = 15;

    private static final String[] SECTION_NAMES = {
            "L0/L1S1",
            "L1S2",
            "L1S3",
            "L1S4/L2S1",
            "L2S2",
            "L2S3/L3S1",
            "L3S2",
            "L3S3",
            "L3S4/L4S1",
            "L4S2",
            "L4S3",
            "L4S4",
            "L4S5/L5S1",
            "L5S2",
            "L5S3",
    };

    // accept world x
    public static int getSection(double x) {
        return (int)(x / ABYSS_DX + 0.5);
    }

    public static String getSectionName(int s) {
        if (s < 0 || s > SECTION_NAMES.length - 1) return "";
        return SECTION_NAMES[s];
    }

    public static class Coords {
        public double x, y;
        public Coords(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    public static Coords toAbyss(double x, double y) {
        int section = getSection(x);
        double _x = ABYSS_DX * ((x / ABYSS_DX + 0.5) % 1 - 0.5);
        double _y = y - section * ABYSS_DY;
        return new Coords(_x, _y);
    }

    private static boolean belongsToAbyssSection(int s, int y) {
        if (s == 0 && y > 0) return true;
        int min_y = ABYSS_WY - s * ABYSS_DY;
        int max_y = ABYSS_WY + ABYSS_WH - s * ABYSS_DY;
        return y >= min_y && y < max_y;
    }

    public static int getAbyssSection(int y) {
        for (int s = 0; s < ABYSS_SECTIONS; s++) {
            if (belongsToAbyssSection(s, y)) return s;
        }
        return 0;
    }

    public static Coords toWorld(double x, double y) {
        int section = getAbyssSection((int)y);
        double _x = section * ABYSS_DX + x;
        double _y = y - section * ABYSS_DY;
        return new Coords(_x, _y);
    }
}
