package com.example.erweim4;

import android.graphics.Bitmap;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import java.util.EnumMap;
import java.util.Map;

import static android.graphics.Color.BLACK;
import static android.graphics.Color.WHITE;

public class GenerateQrCode {

    public Bitmap getBitmap(String info){
            Map<EncodeHintType, Object> hints = null;
            String encoding = getEncoding(info);//获取编码格式
            if (encoding != null) {
                hints = new EnumMap<>(EncodeHintType.class);
                hints.put(EncodeHintType.CHARACTER_SET, encoding);
            }
        BitMatrix result= null;//通过字符串创建二维矩阵
        try {
            result = new MultiFormatWriter().encode(info, BarcodeFormat.QR_CODE,350,350,hints);
        } catch (WriterException e) {
            e.printStackTrace();
        }
        int width = result.getWidth();
            int height = result.getHeight();
            int[] pixels = new int[width * height];

            for (int y = 0; y < height; y++) {
                int offset = y * width;
                for (int x = 0; x < width; x++) {
                    pixels[offset + x] = result.get(x, y) ? BLACK : WHITE;//根据二维矩阵数据创建数组
                }
            }

            Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);//创建位图
            bitmap.setPixels(pixels, 0, width, 0, 0, width, height);//设置位图像素集为数组
            return bitmap;
    }

    private static String getEncoding(CharSequence contents) {
        // Very crude at the moment
        for (int i = 0; i < contents.length(); i++) {
            if (contents.charAt(i) > 0xFF) {
                return "UTF-8";
            }
        }
        return null;
    }
}
