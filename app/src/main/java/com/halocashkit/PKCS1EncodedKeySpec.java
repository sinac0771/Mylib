package com.halocashkit;


import java.io.IOException;
import java.math.BigInteger;
import java.security.spec.RSAPrivateCrtKeySpec;

public class PKCS1EncodedKeySpec
{
  private RSAPrivateCrtKeySpec a;

  public PKCS1EncodedKeySpec(byte[] var1) throws IOException {
    a var2;
    if((var2 = (new b(var1)).a()).a() != 16) {
      throw new IOException("Invalid DER: not a sequence");
    } else {
      b var10;
      (var10 = var2.b()).a();
      BigInteger var3 = var10.a().c();
      BigInteger var4 = var10.a().c();
      BigInteger var5 = var10.a().c();
      BigInteger var6 = var10.a().c();
      BigInteger var7 = var10.a().c();
      BigInteger var8 = var10.a().c();
      BigInteger var9 = var10.a().c();
      BigInteger var11 = var10.a().c();
      this.a = new RSAPrivateCrtKeySpec(var3, var4, var5, var6, var7, var8, var9, var11);
    }
  }

  public RSAPrivateCrtKeySpec getKeySpec() {
    return this.a;
  }
}



/* Location:           C:\Users\THINK\Desktop\tem\doc\halocash_order.jar

 * Qualified Name:     com.halocashkit.order.PKCS1EncodedKeySpec

 * JD-Core Version:    0.7.0.1

 */