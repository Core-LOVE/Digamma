package katebulka.digamma.mixin.client;

//import me.saharnooby.qoi.QOIImage;
//import me.saharnooby.qoi.QOIUtil;
//import org.spongepowered.asm.mixin.injection.At;
//import org.spongepowered.asm.mixin.injection.Inject;
//import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.nio.ByteBuffer;

import com.mojang.blaze3d.platform.NativeImage;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(NativeImage.class)
public abstract class NativeImageMixin {
//    @Inject(method = "read(Ljava/io/InputStream;)Lnet/minecraft/client/texture/NativeImage;",
//            at = @At("HEAD"), cancellable = true)
//    private static void onReadInputStream(InputStream inputStream, CallbackInfoReturnable<NativeImage> cir) throws IOException {
//        if (!inputStream.markSupported()) {
//            return;
//        }
//        inputStream.mark(4);
//
//        try {
//            byte[] magicBytes = new byte[4];
//            int bytesRead = inputStream.read(magicBytes);
//            if (bytesRead >= 4 && "qoif".equals(new String(magicBytes))) {
//                inputStream.reset();
//                QOIImage qoiImage = QOIUtil.readImage(inputStream);
//
//                int imgWidth = qoiImage.getWidth();
//                int imgHeight = qoiImage.getHeight();
//                byte[] pixelData = qoiImage.getPixelData();
//                int channels = qoiImage.getChannels();
//
//                if (channels != 4) {
//
//                }
//
//                NativeImage nativeImage = new NativeImage(imgWidth, imgHeight, true);
//
//                for (int y = 0; y < imgHeight; y++) {
//                    for (int x = 0; x < imgWidth; x++) {
//                        int pixelOffset = (y * imgWidth + x) * 4;
//
//                        int r = pixelData[pixelOffset] & 0xFF;
//                        int g = pixelData[pixelOffset + 1] & 0xFF;
//                        int b = pixelData[pixelOffset + 2] & 0xFF;
//                        int a = pixelData[pixelOffset + 3] & 0xFF;
//                        int abgr = (a << 24) | (b << 16) | (g << 8) | r;
//                        nativeImage.setPixelRGBA(x, y, abgr);
//                    }
//                }
//
//                cir.setReturnValue(nativeImage);
//            }
//        } finally {
//            inputStream.reset();
//        }
//    }
}
