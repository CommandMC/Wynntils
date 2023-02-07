/*
 * Copyright © Wynntils 2023.
 * This file is released under AGPLv3. See LICENSE for full license details.
 */
package com.wynntils.models.rewards;

import com.wynntils.core.WynntilsMod;
import com.wynntils.core.components.Model;
import com.wynntils.handlers.item.ItemAnnotation;
import com.wynntils.models.gear.parsing.GearParseResult;
import com.wynntils.models.gear.parsing.GearParser;
import com.wynntils.models.gear.type.GearTier;
import com.wynntils.models.items.items.game.CharmItem;
import com.wynntils.models.items.items.game.TomeItem;
import com.wynntils.models.rewards.type.CharmInfo;
import com.wynntils.models.rewards.type.TomeInfo;
import com.wynntils.models.rewards.type.TomeType;
import java.util.List;
import net.minecraft.world.item.ItemStack;

public class RewardsModel extends Model {
    public RewardsModel() {
        super(List.of());
    }

    public ItemAnnotation fromCharmItemStack(ItemStack itemStack, String name, String displayName, String type) {
        GearTier tier = GearTier.fromFormattedString(name);

        // TODO: replace with API lookup
        CharmInfo charmInfo = new CharmInfo(displayName, tier, type);

        GearParseResult result = GearParser.parseItemStack(itemStack, null);
        if (result.tier() != charmInfo.tier()) {
            WynntilsMod.warn("Tier for " + charmInfo.displayName() + " is reported as " + result.tier());
        }

        return new CharmItem(charmInfo, result.identifications(), result.rerolls());
    }

    public static TomeItem fromTomeItemStack(
            ItemStack itemStack, String name, String displayName, TomeType tomeType, String tier, String variant) {
        GearTier gearTier = GearTier.fromFormattedString(name);

        // TODO: replace with API lookup
        TomeInfo tomeInfo = new TomeInfo(displayName, gearTier, variant, tomeType, tier);

        GearParseResult result = GearParser.parseItemStack(itemStack, null);
        if (result.tier() != tomeInfo.gearTier()) {
            WynntilsMod.warn("Tier for " + tomeInfo.displayName() + " is reported as " + result.tier());
        }

        return new TomeItem(tomeInfo, result.identifications(), result.rerolls());
    }
}