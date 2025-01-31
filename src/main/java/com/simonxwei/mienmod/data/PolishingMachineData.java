package com.simonxwei.mienmod.data;

import com.simonxwei.mienmod.network.BlockPosPayload;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.util.math.BlockPos;

//这个类是一个record类，它的作用是传递BlockPos，实现了BlockPosPayload接口
public record PolishingMachineData(BlockPos pos) implements BlockPosPayload {
//    这个是服务器和客户端之间传输数据的一个数据文件，保存了方块的位置
//    BlockPosPayload是一个接口，来自RebornCore（科技复兴的核心mod）
//    PacketCodec是一个编解码器，它的作用是将数据编码成ByteBuf，然后传输给客户端或服务端
//    这里我们传递的是BlockPos，所以我们用BlockPos.PACKET_CODEC来编解码BlockPos
    public static final PacketCodec<RegistryByteBuf, PolishingMachineData> CODEC =
            PacketCodec.tuple(BlockPos.PACKET_CODEC, PolishingMachineData::pos, PolishingMachineData::new);

}
