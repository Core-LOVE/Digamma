package katebulka.digamma.mixin.client;

//import tfar.dei.network.client.S2CItemStackPacket;

//@Mixin(
//        value = S2CItemStackPacket.class,
//        remap = false
//)
public abstract class S2CItemStackPacketMixin {
//    @Redirect(
//            method = "<clinit>",
//            at = @At(
//                    value = "INVOKE",
//                    target = "Lnet/minecraft/network/codec/StreamCodec;composite(Lnet/minecraft/network/codec/StreamCodec;Ljava/util/function/Function;Ljava/util/function/Function;)Lnet/minecraft/network/codec/StreamCodec;",
//                    remap = false
//            )
//    )
//    private static StreamCodec<RegistryFriendlyByteBuf, S2CItemStackPacket> replaceCodec(
//            StreamCodec<? super RegistryFriendlyByteBuf, ItemStack> itemCodec,
//            java.util.function.Function<S2CItemStackPacket, ItemStack> getter,
//            java.util.function.Function<ItemStack, S2CItemStackPacket> constructor
//    ) {
//        return new StreamCodec<>() {
//            @Override
//            public void encode(RegistryFriendlyByteBuf buf, S2CItemStackPacket packet) {
//                ItemStack stack = packet.stack();
//                boolean empty = stack.isEmpty();
//                buf.writeBoolean(empty);
//                if (!empty) {
//                    ItemStack.STREAM_CODEC.encode(buf, stack);
//                }
//            }
//
//            @Override
//            public S2CItemStackPacket decode(RegistryFriendlyByteBuf buf) {
//                boolean empty = buf.readBoolean();
//                if (empty) {
//                    return new S2CItemStackPacket(ItemStack.EMPTY);
//                } else {
//                    ItemStack stack = ItemStack.STREAM_CODEC.decode(buf);
//                    return new S2CItemStackPacket(stack);
//                }
//            }
//        };
//    }
}
