package net.playl.abysscraft.lodmixins;

import com.seibel.distanthorizons.core.render.renderer.LodRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(LodRenderer.class)
public abstract class LodRendererMixin {
    // region - Fix glIsProgram performance
    // This check called too many times during rendering frame, it just slows everything down and doesn't aclually handle any issues.
    // https://github.com/kawashirov/distant-horizons-core/commit/48cb52f2a375aa97c194b51f41b391560814ea6e

    @Redirect(method = "setupOffset", at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL32;glIsProgram(I)Z"))
    public boolean onCallGlIsProgram(int i) {
        return true;
    }

    // endregion
}
