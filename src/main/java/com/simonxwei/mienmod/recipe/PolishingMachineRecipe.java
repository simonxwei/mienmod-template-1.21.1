package com.simonxwei.mienmod.recipe;

import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.simonxwei.mienmod.block.custom.PolishingMachine;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.input.SingleStackRecipeInput;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;

import java.util.List;

//泛型是SingleStackRecipeInput，也就是只有一个输入物品的配方
//可以根据自己的需求，选择不同的输入类，有必要时可以创建自己的类
public class PolishingMachineRecipe implements Recipe<SingleStackRecipeInput> {
//    创建了两个变量，一个是输出物品，一个是输入物品
    private final List<Ingredient> recipeItems;
    private final ItemStack output;

//    创建构造方法，传入这两个参数（recipeItems写在前面，不然后面写编解码器的时候又会有问题）
    public PolishingMachineRecipe(List<Ingredient> recipeItems, ItemStack output) {
        this.recipeItems = recipeItems;
        this.output = output;
    }

//    先重写getIngredients方法，这个方法返回一个List<Ingredient>，表示这个配方的输入物品
//    方便后续REI返回这里的getIngredients()
    @Override
    public DefaultedList<Ingredient> getIngredients() {
//        这里将recipeItems中的Ingredient添加到list中，然后返回list
        DefaultedList<Ingredient> list = DefaultedList.ofSize(this.recipeItems.size());
        list.addAll(recipeItems);
        return list;
    }

//    用于判断输入物品是否符合配方
    @Override
    public boolean matches(SingleStackRecipeInput input, World world) {
//        再加一个客户端和服务端的判断，然后判断输入物品是否符合配方
        if (world.isClient()) {
            return false;
        }
//        get里面填的是索引值，不过我们这里只有一个输入物品，所以填0，也可以将get改成getfirst，效果是一样的
//        test方法是判断输入物品是否符合Ingredient的条件
        return recipeItems.get(0).test(input.item());
    }

//    返回配方的输出物品
    @Override
    public ItemStack craft(SingleStackRecipeInput input, RegistryWrapper.WrapperLookup lookup) {
//        直接返回output的拷贝
        return this.output.copy();
    }

//    判断配方是否符合CraftingTable的大小（配方是否符合原版网格大小），这里直接返回true，表示不需要考虑大小
//    确切来讲，这个应该是自定义GUI是不是和原版一样的分辨率，如果不是应该还得写额外的代码
    @Override
    public boolean fits(int width, int height) {
        return true;
    }

//    返回配方的输出物品
//    这个方法和craft方法的区别是，craft方法是真正的合成，而getResult方法是用于配方书等情景的显示
    @Override
    public ItemStack getResult(RegistryWrapper.WrapperLookup registriesLookup) {
        return this.output;
    }

//    用于序列化和反序列化，是编解码器
    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

//    返回这个配方的类型
//    原版的大多数配方都会有这两个东西，而且这两个是各个配方类的嵌套类
    @Override
    public RecipeType<?> getType() {
//        返回下面所创建Type的INSTANCE对象即可
        return Type.INSTANCE;
    }

//    对应getType()，创建Type类，实现RecipeType接口，泛型为PolishingMachineRecipe
    public static class Type implements RecipeType<PolishingMachineRecipe> {
//        创建了一个INSTANCE对象，用于注册；一个ID字符串polishing_machine，是json文件中的type字段
        public static final Type INSTANCE = new Type();
        public static final String ID = "polishing_machine";
    }

//    对应getSerializer()，创建一个Serializer类
    public static class Serializer implements RecipeSerializer<PolishingMachineRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final String ID = "polishing_machine";

//        编解码器，关于ingredients
//        CODEC对象，即编解码器，是用于序列化和反序列化的
//        有报错就按上述所说，对调输入和输出的形参位置
        public static final MapCodec<PolishingMachineRecipe> CODEC = RecordCodecBuilder
                .mapCodec(
//                        fieldOf("ingredients")是读取输入物品的键
                        instance -> instance.group((Ingredient.DISALLOW_EMPTY_CODEC.listOf().fieldOf("ingredients"))
                                        .flatXmap(
                                                ingredients -> {
                                                    Ingredient[] ingredients1 = (Ingredient[]) ingredients.stream().filter(ingredient -> !ingredient.isEmpty()).toArray(Ingredient[]::new);
//                                                    条件判断配方文件输入物品数量是否大于0小于等于9，如果不符合条件就返回错误，可写可不写
                                                    if (ingredients1.length == 0) {
                                                        return DataResult.error(() -> "No ingredients");
                                                    }
                                                    if (ingredients1.length > 9) {
                                                        return DataResult.error(() -> "Too many ingredients");
                                                    }
                                                    return DataResult.success(DefaultedList.copyOf(Ingredient.EMPTY, ingredients1));
                                                }, DataResult::success
                                        ).forGetter(recipe -> recipe.getIngredients()),
//                                fieldOf("output")是读取输出物品的键
                                (ItemStack.VALIDATED_CODEC.fieldOf("output")).forGetter(recipe -> recipe.output)).apply(instance, PolishingMachineRecipe::new)
                );

//        网络发包，即网络传输的编解码器
//        PACKET_CODEC对象，是用于网络传输的编解码器，为了它还要创建两个方法read和write，这两个方法是用于读取和写入数据的
        public static final PacketCodec<RegistryByteBuf, PolishingMachineRecipe> PACKET_CODEC = PacketCodec.ofStatic(Serializer::write, Serializer::read);

//        以上涉及的两个自定义方法write和read
//        read里面用的是decode方法，write里面用的是encode方法，分别对应解码和编码
//        本质是读取json文件，从中获得对应的输入物品和输出物品，只是得按照游戏的底层来写
        private static PolishingMachineRecipe read(RegistryByteBuf registryByteBuf) {
            DefaultedList<Ingredient> inputs = DefaultedList.ofSize(registryByteBuf.readInt(), Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.PACKET_CODEC.decode(registryByteBuf));
            }

            ItemStack output = ItemStack.PACKET_CODEC.decode(registryByteBuf);

            return new PolishingMachineRecipe(inputs, output);
        }

        private static void write(RegistryByteBuf registryByteBuf, PolishingMachineRecipe polishingMachineRecipe) {
            registryByteBuf.writeInt(polishingMachineRecipe.getIngredients().size());

            for (Ingredient ingredient : polishingMachineRecipe.getIngredients()) {
                Ingredient.PACKET_CODEC.encode(registryByteBuf, ingredient);
            }

            ItemStack.PACKET_CODEC.encode(registryByteBuf, polishingMachineRecipe.getResult(null));
        }

//        编解码器
        @Override
        public MapCodec<PolishingMachineRecipe> codec() {
            return CODEC;
        }

//        网络发包
        @Override
        public PacketCodec<RegistryByteBuf, PolishingMachineRecipe> packetCodec() {
            return PACKET_CODEC;
        }
    }
}
