package biz.berger_media.paintedglowstone.item;

import biz.berger_media.paintedglowstone.PaintedGlowstone;
import biz.berger_media.paintedglowstone.block.BlockPaintedGlowstone;
import biz.berger_media.paintedglowstone.block.TilePaintedGlowstone;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.moddingx.libx.base.ItemBase;
import org.moddingx.libx.mod.ModX;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class ItemPaintingRoll extends ItemBase {
    private static final String STORE_NAME = "storedMimic";

    public ItemPaintingRoll(ModX mod, Properties properties) {
        super(mod, properties);
    }

    @Override
    public void appendHoverText(@Nonnull ItemStack stack, @Nullable Level level, List<Component> tooltip, @Nonnull TooltipFlag flag) {
        final String NO_BLOCK_TRANSLATION = "tooltip.paintedglowstone.no_block_stored";
        final ChatFormatting STORED_BLOCK_FORMATTING = ChatFormatting.GOLD;

        CompoundTag tag = stack.getTag();
        if (tag == null) {
            tooltip.add(Component.translatable(NO_BLOCK_TRANSLATION).withStyle(STORED_BLOCK_FORMATTING));
            return;
        }

        BlockState storedBlockState = NbtUtils.readBlockState(tag.getCompound(STORE_NAME));
        if (!storedBlockState.isAir()) {
            tooltip.add(Component.translatable("tooltip.paintedglowstone.block_stored", Component.translatable(storedBlockState.getBlock().getDescriptionId())).withStyle(STORED_BLOCK_FORMATTING));
        } else {
            tooltip.add(Component.translatable(NO_BLOCK_TRANSLATION).withStyle(STORED_BLOCK_FORMATTING));
        }
    }

    @Override
    public boolean isEnchantable(@Nonnull ItemStack stack) {
        return false;
    }

    @Nonnull
    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        Player player = context.getPlayer();
        InteractionHand hand = context.getHand();
        ItemStack stack = context.getItemInHand();

        if (player == null) {
            PaintedGlowstone.LOGGER.debug("player is null");
            return InteractionResult.PASS;
        }

        CompoundTag nbt = player.getItemInHand(hand).getOrCreateTag();

        // When the player is crouching, we are in copy mode.
        if (player.isCrouching()) {
            BlockState clickedBlockState = level.getBlockState(pos);
            if (clickedBlockState.getBlock() instanceof BlockPaintedGlowstone) {
                // We cannot copy from Painted Glowstone - Notify user
                if (!level.isClientSide) {
                    player.displayClientMessage(Component.translatable("paintedglowstone.message.cant_copy_from_glowstone_block"), true);
                }

                return InteractionResult.PASS;
            }

            BlockHitResult hit = new BlockHitResult(context.getClickLocation(), context.getClickedFace(), pos, context.isInside());
            BlockState mimicState = clickedBlockState.getBlock().getStateForPlacement(new BlockPlaceContext(player, hand, stack, hit));
            if (mimicState != null) {
                nbt.put(STORE_NAME, NbtUtils.writeBlockState(mimicState));
                player.swing(hand);

                if (!level.isClientSide) {
                    player.displayClientMessage(Component.translatable("paintedglowstone.message.copied_block", Component.translatable(clickedBlockState.getBlock().getDescriptionId())), true);
                }
            }

            return InteractionResult.SUCCESS;
        }

        // When a Painted Glowstone block was clicked, apply the block mimic
        if (level.getBlockEntity(pos) instanceof TilePaintedGlowstone glowstoneTile) {
            glowstoneTile.setMimic(NbtUtils.readBlockState(nbt.getCompound(STORE_NAME)));

            player.swing(hand);
            return InteractionResult.sidedSuccess(level.isClientSide);
        }

        return InteractionResult.PASS;
    }
}
