/*
Mimic functionality by castcrafter/travel_anchors:
https://github.com/castcrafter/travel_anchors/blob/master/src/main/java/de/castcrafter/travelanchors/block/TileTravelAnchor.java

   Copyright 2022 castcrafter

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */

package biz.berger_media.paintedglowstone.block;

import biz.berger_media.paintedglowstone.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.moddingx.libx.base.tile.BlockEntityBase;

import javax.annotation.Nonnull;

public class TilePaintedGlowstone extends BlockEntityBase {
    private BlockState mimic = null;

    public TilePaintedGlowstone(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Override
    public void saveAdditional(@Nonnull CompoundTag nbt) {
        super.saveAdditional(nbt);
        this.writeMimic(nbt);
    }

    @Override
    public void load(@Nonnull CompoundTag nbt) {
        super.load(nbt);
        this.readMimic(nbt);
    }

    @Nonnull
    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag nbt = super.getUpdateTag();
        this.writeMimic(nbt);
        return nbt;
    }

    @Override
    public void handleUpdateTag(CompoundTag nbt) {
        this.readMimic(nbt);
    }

    private void writeMimic(CompoundTag tag) {
        tag.put("mimic", NbtUtils.writeBlockState(this.mimic == null ? ModBlocks.paintedGlowstone.defaultBlockState() : this.mimic));
    }

    private void readMimic(CompoundTag tag) {
        if (tag.contains("mimic")) {
            BlockState state = NbtUtils.readBlockState(tag.getCompound("mimic"));
            if (state == ModBlocks.paintedGlowstone.defaultBlockState()) {
                this.mimic = null;
            } else {
                this.mimic = state;
            }
        }
    }

    /**
     * Returns the currently stored mimic of the block
     */
    public BlockState getMimic() {
        return this.mimic;
    }

    /**
     * Sets the BlockState that should be used as mimic
     */
    public void setMimic(BlockState mimic) {
        this.mimic = mimic;
        this.setChanged();
        this.setDispatchable();
    }
}
