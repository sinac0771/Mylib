package com.halocashkit;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;

final class b
{
  private InputStream a;

  private b(InputStream var1) {
    this.a = var1;
  }

  public b(byte[] var1) {
    this((InputStream)(new ByteArrayInputStream(var1)));
  }

  public final a a() throws IOException {
    int var1;
    if((var1 = this.a.read()) == -1) {
      throw new IOException("Invalid DER: stream too short, missing tag");
    } else {
      int var3;
      if((var3 = this.a.read()) == -1) {
        throw new IOException("Invalid DER: length missing");
      } else {
        int var10000;
        byte[] var5;
        if((var3 & -128) == 0) {
          var10000 = var3;
        } else {
          int var4 = var3 & 127;
          if(var3 >= 255 || var4 > 4) {
            throw new IOException("Invalid DER: length field too big (" + var3 + ")");
          }

          var5 = new byte[var4];
          if(this.a.read(var5) < var4) {
            throw new IOException("Invalid DER: length too short");
          }

          var10000 = (new BigInteger(1, var5)).intValue();
        }

        int var2 = var10000;
        var5 = new byte[var10000];
        if(this.a.read(var5) < var2) {
          throw new IOException("Invalid DER: stream too short, missing value");
        } else {
          return new a(var1, var2, var5);
        }
      }
    }
  }
}


/* Location:           C:\Users\THINK\Desktop\tem\doc\halocash_order.jar

 * Qualified Name:     com.halocashkit.order.b

 * JD-Core Version:    0.7.0.1

 */