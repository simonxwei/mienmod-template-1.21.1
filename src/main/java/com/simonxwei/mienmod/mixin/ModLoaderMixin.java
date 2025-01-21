package com.simonxwei.mienmod.mixin;

import com.simonxwei.mienmod.MienMod;
import net.minecraft.client.render.model.ModelLoader;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ModelLoader.class)
public abstract class ModLoaderMixin {
    @Shadow protected abstract void loadItemModel(ModelIdentifier id);

    @Inject(
            method = "<init>",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/render/model/ModelLoader;loadItemModel(Lnet/minecraft/client/util/ModelIdentifier;)V",
                    ordinal = 1,
                    shift = At.Shift.AFTER
            )
    )

    public void addPlate(CallbackInfo ci) {
        this.loadItemModel(ModelIdentifier.ofInventoryVariant(Identifier.of(MienMod.MOD_ID, "plate_3d")));
    }
}
