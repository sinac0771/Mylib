package com.halocashkit;

import java.io.IOException;
import java.math.BigInteger;

final class a
{
  private int a;
  private byte[] b;
  private int c;

  public a(int var1, int var2, byte[] var3) {
    this.c = var1;
    this.a = var1 & 31;
    this.b = var3;
  }

  public final int a() {
    return this.a;
  }

  public final b b() throws IOException {
    if((this.c & 32) != 32) {
      throw new IOException("Invalid DER: can\'t parse primitive entity");
    } else {
      return new b(this.b);
    }
  }

  public final BigInteger c() throws IOException {
    if(this.a != 2) {
      throw new IOException("Invalid DER: object is not integer");
    } else {
      return new BigInteger(this.b);
    }
  }
}


/* Location:           C:\Users\THINK\Desktop\tem\doc\halocash_order.jar

 * Qualified Name:     com.halocashkit.order.a

 * JD-Core Version:    0.7.0.1

 */