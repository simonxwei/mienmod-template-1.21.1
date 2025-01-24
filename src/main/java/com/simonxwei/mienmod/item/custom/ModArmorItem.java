package com.simonxwei.mienmod.item.custom;

import com.google.common.collect.ImmutableMap;
import com.simonxwei.mienmod.item.ModArmorMaterials;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.World;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

//实现全套盔甲附加的效果
public class ModArmorItem extends ArmorItem {
//    map存放材质和效果
//    将StatusEffectInstance改为List，为盔甲附加多重效果
    private static final Map<ArmorMaterial, List<StatusEffectInstance>> MAP = (
            new ImmutableMap.Builder<ArmorMaterial, List<StatusEffectInstance>>())
                .put(
                        ModArmorMaterials.ICE_ETHER.value(),
//                        new StatusEffectInstance(
//                                StatusEffects.FIRE_RESISTANCE,
//                                1000,
//                                1,
//                                false, false, true)
//                        为盔甲附加多重效果而改为List后，添加Arrays.asList，并添加多个new StatusEffectInstance
                        Arrays.asList(
                                new StatusEffectInstance(
                                        StatusEffects.FIRE_RESISTANCE,
                                        1000,
//                                        0对应1级，以此类推
                                        1,
                                        false, false, true
                                ),
                                new StatusEffectInstance(
                                        StatusEffects.SPEED,
                                        1000,
                                        1,
                                        false, false, true
                                )
                        )
                ).build();

    public ModArmorItem(RegistryEntry<ArmorMaterial> material, Type type, Settings settings) {
        super(material, type, settings);
    }

//    和tick挂钩即实时调用
//    判断盔甲是否被玩家穿戴，否则没有效果
    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
//        自定义条件是否穿戴全套，以及自定义效果
        if (!world.isClient && entity instanceof PlayerEntity player && hasFullSuitOfArmor(player)) {
            evaluateArmorEffects(player);
        }
        super.inventoryTick(stack, world, entity, slot, selected);
    }

//    自定义穿戴效果
//    将StatusEffectInstance改为List，为盔甲附加多重效果
    private void evaluateArmorEffects(PlayerEntity player) {
        for (Map.Entry<ArmorMaterial, List<StatusEffectInstance>> entry : MAP.entrySet()) {
//            遍历Map，获得键和值
            ArmorMaterial material = entry.getKey();
            List<StatusEffectInstance> effects = entry.getValue();

//            如果盔甲符合我们的设定，获得对应的效果
//            if (hasCorrectArmorSet(player, material)) {
//                addStatusEffectForMaterial(player, effect);
//            }
//            修改为for遍历执行
            if (hasCorrectArmorSet(player, material)) {
                for (StatusEffectInstance effect : effects) {
                    addStatusEffectForMaterial(player, effect);
                }
            }
        }
    }

//    获取盔甲效果
    private void addStatusEffectForMaterial(PlayerEntity player, StatusEffectInstance effect) {
//        判断玩家是否拥有该效果
        boolean hasEffect = player.hasStatusEffect(effect.getEffectType());

//        如果没有该效果，则赋予玩家该效果
        if (!hasEffect) {
            player.addStatusEffect(new StatusEffectInstance(effect));
        }
    }

//    判断盔甲是否符合我们的设定（指定）
    private boolean hasCorrectArmorSet(PlayerEntity player, ArmorMaterial material) {
//        排除鞘翅的影响，否则会导致游戏崩溃
        for (ItemStack stack : player.getInventory().armor) {
            if (!(stack.getItem() instanceof ArmorItem)) {
                return false;
            }
        }

//        获取全部位盔甲材料
        ArmorItem helmet = (ArmorItem) player.getInventory().getArmorStack(3).getItem();
        ArmorItem chestplate = (ArmorItem) player.getInventory().getArmorStack(2).getItem();
        ArmorItem leggings = (ArmorItem) player.getInventory().getArmorStack(1).getItem();
        ArmorItem boots = (ArmorItem) player.getInventory().getArmorStack(0).getItem();

//        返回值，鞘翅在这一步将报错
        return helmet.getMaterial().value() == material &&
                chestplate.getMaterial().value() == material &&
                leggings.getMaterial().value() == material &&
                boots.getMaterial().value() == material;
    }

    //    自定义是否穿戴全套条件
    private boolean hasFullSuitOfArmor(PlayerEntity player) {
//        先获取物品堆栈
        ItemStack helmet = player.getInventory().getArmorStack(3);
        ItemStack chestplate = player.getInventory().getArmorStack(2);
        ItemStack leggings = player.getInventory().getArmorStack(1);
        ItemStack boots = player.getInventory().getArmorStack(0);

//        如果拥有全套盔甲，返回值
        return !helmet.isEmpty() && !chestplate.isEmpty() && !leggings.isEmpty() && !boots.isEmpty();
    }
}
