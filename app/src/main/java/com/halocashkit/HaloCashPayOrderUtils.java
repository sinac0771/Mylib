package com.halocashkit;

import android.text.TextUtils;
import android.util.Log;


import java.net.URLEncoder;
import org.json.JSONObject;

public class HaloCashPayOrderUtils
{
  public static final String TAG = HaloCashPayOrderUtils.class.getSimpleName();
  private String a;
  private String b;
  private String c;
  private String d;
  private String e;
  private String f;
  private String g;
  private String h;

  public HaloCashPayOrderUtils() {
  }

  public void setMerchantid(String var1) {
    this.a = var1;
  }

  public void setOuttradeno(String var1) {
    this.b = var1;
  }

  public void setSubject(String var1) {
    this.c = var1;
  }

  public void setAmount(String var1) {
    this.d = var1;
  }

  public void setCurrency(String var1) {
    this.e = var1;
  }

  public void setNotifyurl(String var1) {
    this.f = var1;
  }

  public void setCustomerid(String var1) {
    this.g = var1;
  }

  public void setCountry(String var1) {
    this.h = var1;
  }

  public String getTransdata(String var1) {
    String var2 = "";
    JSONObject var3 = new JSONObject();

    try {
      if(TextUtils.isEmpty(this.a)) {
        Log.d(TAG, "merchantid is null");
        return "merchantid is null";
      }

      var3.put("merchantid", this.a);
      if(TextUtils.isEmpty(this.b)) {
        Log.d(TAG, "outtradeno is null ");
        return "outtradeno is null ";
      }

      var3.put("outtradeno", this.b);
      if(TextUtils.isEmpty(this.c)) {
        Log.d(TAG, "subject is null ");
        return "subject is null";
      }

      var3.put("subject", this.c);
      if(TextUtils.isEmpty(this.d)) {
        Log.d(TAG, "amount is null ");
        return "amount is null";
      }

      var3.put("amount", this.d);
      if(TextUtils.isEmpty(this.e)) {
        Log.d(TAG, "currency is null ");
        return "currency is null";
      }

      var3.put("currency", this.e);
      if(TextUtils.isEmpty(this.f)) {
        Log.d(TAG, "notifyurl is null ");
        return "notifyurl is null";
      }

      var3.put("notifyurl", this.f);
      if(TextUtils.isEmpty(this.g)) {
        Log.d(TAG, "customerid is null ");
        return "customerid is null";
      }

      var3.put("customerid", this.g);
      if(!TextUtils.isEmpty(this.h)) {
        var3.put("country", this.h);
      }

      var2 = var3.toString();
    } catch (Exception var5) {
      var5.printStackTrace();
    }

    var3 = null;

    try {
      String var6 = RSAHelper.signForPKCS1(var2, var1);
      return "transdata=" + URLEncoder.encode(var2, "utf-8") + "&sign=" + URLEncoder.encode(var6, "utf-8") + "&signtype=RSA";
    } catch (Exception var4) {
      var4.printStackTrace();
      Log.d(TAG, "数据加密失败。。。。：" + var4.toString());
      return "";
    }
  }
}


/* Location:           C:\Users\THINK\Desktop\tem\doc\halocash_order.jar

 * Qualified Name:     com.halocashkit.order.HaloCashPayOrderUtils

 * JD-Core Version:    0.7.0.1

 */