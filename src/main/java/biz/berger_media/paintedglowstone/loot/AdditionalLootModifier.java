/*
AdditionalLootModifier by SaphieNyako/Feywild:
https://github.com/SaphieNyako/Feywild/blob/dev/1.19/src/main/java/com/feywild/feywild/loot/AdditionLootModifier.java

MIT License

Copyright (c) 2021 SaphieNyako

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */
package biz.berger_media.paintedglowstone.loot;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;

import javax.annotation.Nonnull;

public class AdditionalLootModifier extends LootModifier {
    public static final Codec<AdditionalLootModifier> CODEC = RecordCodecBuilder.create(i -> i.group(
            ResourceLocation.CODEC.fieldOf("loot_table").forGetter(lm -> lm.table),
            LOOT_CONDITIONS_CODEC.fieldOf("conditions").forGetter(lm -> lm.conditions)
    ).apply(i, AdditionalLootModifier::new));

    private final ResourceLocation table;

    public AdditionalLootModifier(ResourceLocation table, LootItemCondition... conditions) {
        super(conditions);

        this.table = table;
    }

    @Nonnull
    @Override
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> loot, LootContext context) {
        LootTable table = context.getLootTable(this.table);
        ObjectArrayList<ItemStack> stacks = table.getRandomItems(LootContextHelper.copyWith(context, this.table));
        loot.addAll(stacks);

        return loot;
    }

    public Codec<? extends IGlobalLootModifier> codec() {
        return CODEC;
    }
}
