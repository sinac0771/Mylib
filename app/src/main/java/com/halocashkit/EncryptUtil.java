package com.halocashkit;

import android.util.Base64;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptUtil
{
  public EncryptUtil() {
  }

  public static String Hex2String(byte[] var0) {
    StringBuilder var1 = new StringBuilder(var0.length << 1);
    byte[] var4 = var0;
    int var3 = var0.length;

    for(int var2 = 0; var2 < var3; ++var2) {
      byte var5;
      if(((var5 = var4[var2]) & 255) < 16) {
        var1.append("0");
      }

      var1.append(Integer.toHexString(var5 & 255));
    }

    return var1.toString();
  }

  public static String md5(String var0) {
    byte[] var3;
    try {
      var3 = MessageDigest.getInstance("MD5").digest(var0.getBytes("UTF-8"));
    } catch (NoSuchAlgorithmException var1) {
      throw new RuntimeException("Huh, MD5 should be supported?", var1);
    } catch (UnsupportedEncodingException var2) {
      throw new RuntimeException("Huh, UTF-8 should be supported?", var2);
    }

    return Hex2String(var3);
  }

  public static byte[] decryptBASE64(String var0) {
    return Base64.decode(var0, 2);
  }

  public static String encryptBASE64(byte[] var0) {
    return Base64.encodeToString(var0, 2);
  }
}




/* Location:           C:\Users\THINK\Desktop\tem\doc\halocash_order.jar

 * Qualified Name:     com.halocashkit.order.EncryptUtil

 * JD-Core Version:    0.7.0.1

 */