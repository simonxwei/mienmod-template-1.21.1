package com.simonxwei.mienmod.item.custom;

import com.mojang.serialization.Codec;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Equipment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

//自制头饰
public class HatItem extends Item implements Equipment {
//    添加type，并为其添加构造函数形参
    protected final Type type;

//    调整type和settings顺序，方便后续注册格式统一
    public HatItem(Type type, Settings settings) {
        super(settings);
        this.type = type;
    }

//    重写use方法
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        return this.equipAndSwap(this, world, user, hand);
    }

    //    然后通过type，调用下面所复制源代码中的getEquipmentSlot
    @Override
    public EquipmentSlot getSlotType() {
        return this.type.getEquipmentSlot();
    }

//    将源代码ArmorItem enum字段复制
    public static enum Type implements StringIdentifiable {
//        将HELMET改为HAT，魔改头盔，其余盔甲部分删除
        HAT(EquipmentSlot.HEAD, 11, "hat");

        public static final Codec<ArmorItem.Type> CODEC = StringIdentifiable.createBasicCodec(ArmorItem.Type::values);
        private final EquipmentSlot equipmentSlot;
        private final String name;
        private final int baseMaxDamage;

        private Type(final EquipmentSlot equipmentSlot, final int baseMaxDamage, final String name) {
            this.equipmentSlot = equipmentSlot;
            this.name = name;
            this.baseMaxDamage = baseMaxDamage;
        }

        public int getMaxDamage(int multiplier) {
            return this.baseMaxDamage * multiplier;
        }

        public EquipmentSlot getEquipmentSlot() {
            return this.equipmentSlot;
        }

        public String getName() {
            return this.name;
        }

//        改成false，即不可锻造
        public boolean isTrimmable() {
            return false;
        }

        public String asString() {
            return this.name;
        }
    }
}
