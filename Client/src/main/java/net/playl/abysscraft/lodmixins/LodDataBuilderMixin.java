package net.playl.abysscraft.lodmixins;

import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalIntRef;
import com.seibel.distanthorizons.core.dataObjects.transformers.LodDataBuilder;
import com.seibel.distanthorizons.core.wrapperInterfaces.block.IBlockStateWrapper;
import com.seibel.distanthorizons.core.wrapperInterfaces.chunk.IChunkWrapper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(LodDataBuilder.class)
public abstract class LodDataBuilderMixin {
    // region - Fix for Mine in Abyss.
    // It seems, getLightBlockingHeightMapValue returns invalid values on MiA, so just scan full-height.
    // Can't use y = lastY, as it causes assertion error later in encoder, so topmost block will not be saved properly.
    // https://github.com/kawashirov/distant-horizons-core/commit/a5fdd224283938ca53542a24503875b94b121a7b
    @Redirect(method = "createChunkData", at = @At(value = "INVOKE", target = "Lcom/seibel/distanthorizons/core/wrapperInterfaces/block/IBlockStateWrapper;isAir()Z"))
    private static boolean onCallIsAir(IBlockStateWrapper instance,
                                       @Local(ordinal = 4) LocalIntRef y, @Local(ordinal = 2) int lastY) {
        y.set(lastY - 1);
        return true;
    }
    // endregion

    // region - Workaround for NPE in ChunkLightStorage.get()
    // https://github.com/kawashirov/distant-horizons-core/commit/4ca91f9c0535adeedf08cf32e36a5fd7bfc3d3f2
    @ModifyVariable(method = "createChunkData", at = @At("STORE"), ordinal = 0)
    private static byte onStoreLight(byte light, IChunkWrapper chunkWrapper,
                                     @Local(ordinal = 0) int x, @Local(ordinal = 1) int z, @Local(ordinal = 2) int lastY) {
        return kawaChunkLightStorageNPEWorkaround(chunkWrapper, x, lastY+1, z);
    }

    @ModifyVariable(method = "createChunkData", at = @At("STORE"), ordinal = 1)
    private static byte onStoreNewLight(byte light, IChunkWrapper chunkWrapper,
                                        @Local(ordinal = 0) int x, @Local(ordinal = 1) int z, @Local(ordinal = 5) int y) {
        return kawaChunkLightStorageNPEWorkaround(chunkWrapper, x, y+1, z);
    }
    // endregion

    @Unique
    private static byte kawaChunkLightStorageNPEWorkaround(IChunkWrapper chunkWrapper, int x, int y, int z) {
        // Workaround for NullPointerException in ChunkLightStorage.get(...).
        // If ChunkLightStorage is crashed, assume average level of 5 for sky and 0 for block light.
        byte blockLight = 0, skyLight = 5;
        try {
            blockLight = (byte) chunkWrapper.getBlockLight(x, y, z);
        } catch (RuntimeException ignored) {}
        try {
            skyLight = (byte) chunkWrapper.getSkyLight(x, y, z);
        } catch (RuntimeException ignored) {}
        return (byte)((blockLight << 4) + skyLight);
    }
}
