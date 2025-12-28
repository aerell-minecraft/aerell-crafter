package dev.fernaerell.aerellcrafter.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CrafterBlock;
import net.minecraft.block.entity.CrafterBlockEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.block.WireOrientation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.minecraft.block.CrafterBlock.*;

@Mixin(CrafterBlock.class)
abstract class CrafterBlockMixin {

    @Unique
    private boolean aerellcrafter$isTemplatedOrEmpty(World world, BlockPos pos) {
        return world.getBlockEntity(pos) instanceof CrafterBlockEntity crafterBlockEntity && (crafterBlockEntity.isEmpty() || crafterBlockEntity.getHeldStacks().stream().anyMatch(itemStack -> itemStack.getCount() == 1));
    }

    @Inject(method = "neighborUpdate", at = @At("HEAD"), cancellable = true)
    private void neighborUpdateInject(BlockState state, World world, BlockPos pos, Block sourceBlock, WireOrientation wireOrientation, boolean notify, CallbackInfo ci) {
        if(this.aerellcrafter$isTemplatedOrEmpty(world, pos) && !world.getBlockState(pos).get(TRIGGERED)) ci.cancel();
    }

    @Inject(method = "craft", at = @At("HEAD"), cancellable = true)
    private void craftInject(BlockState state, ServerWorld world, BlockPos pos, CallbackInfo ci) {
        if(this.aerellcrafter$isTemplatedOrEmpty(world, pos)) ci.cancel();
    }
}