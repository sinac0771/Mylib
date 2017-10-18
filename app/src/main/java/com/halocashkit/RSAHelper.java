package com.halocashkit;


import android.util.Log;

import java.io.IOException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

public class RSAHelper
{
  public static String signForPKCS1(String json, String priKey) throws IOException {
    byte[] pKey = EncryptUtil.decryptBASE64(priKey);
    PKCS1EncodedKeySpec keySpec = new PKCS1EncodedKeySpec(pKey);
    PrivateKey privateKey = null;
    try {
      privateKey = KeyFactory.getInstance("RSA").generatePrivate(keySpec.getKeySpec());
      Signature localObject= Signature.getInstance("MD5withRSA");
      localObject .initSign(privateKey);
      localObject.update(json.getBytes("UTF-8"));
      return EncryptUtil.encryptBASE64(localObject.sign());
    } catch (Exception e) {
      e.printStackTrace();
      Log.e(RSAHelper.class.getSimpleName(),e.getMessage());
    }
    return null;
  }
  public static boolean verify(String var0, String var1, String var2) {
    byte[] var4 = EncryptUtil.decryptBASE64(var1);
    X509EncodedKeySpec var5 = new X509EncodedKeySpec(var4);
    PublicKey var6 = null;
    try {
      var6 = KeyFactory.getInstance("RSA").generatePublic(var5);
      Signature var3 = Signature.getInstance("MD5withRSA");
      var3.initVerify(var6);
      var3.update(var0.getBytes("UTF-8"));
      return var3.verify(EncryptUtil.decryptBASE64(var2));
    } catch (Exception e) {
      e.printStackTrace();
      Log.e(RSAHelper.class.getSimpleName(),e.getMessage());
    }
  return false;
  }
}



/* Location:           C:\Users\THINK\Desktop\tem\doc\halocash_order.jar

 * Qualified Name:     com.halocashkit.order.RSAHelper

 * JD-Core Version:    0.7.0.1

 */