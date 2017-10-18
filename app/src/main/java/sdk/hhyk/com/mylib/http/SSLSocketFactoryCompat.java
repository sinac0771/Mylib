package sdk.hhyk.com.mylib.http;

import android.os.Build.VERSION;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import javax.net.SocketFactory;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;

public class SSLSocketFactoryCompat
  extends SSLSocketFactory
{
  private SSLSocketFactory defaultFactory;
  static String[] protocols = null;
  static String[] cipherSuites = null;

  public SSLSocketFactoryCompat(X509TrustManager var1) {
    try {
      SSLContext var2;
      (var2 = SSLContext.getInstance("TLS")).init((KeyManager[])null, var1 != null?new X509TrustManager[]{var1}:null, (SecureRandom)null);
      this.defaultFactory = var2.getSocketFactory();
    } catch (GeneralSecurityException var3) {
      throw new AssertionError();
    }
  }

  public SSLSocketFactoryCompat() {
    try {
      SSLContext var1;
      (var1 = SSLContext.getInstance("TLS")).init((KeyManager[])null, new X509TrustManager[0], (SecureRandom)null);
      this.defaultFactory = var1.getSocketFactory();
    } catch (GeneralSecurityException var2) {
      throw new AssertionError();
    }
  }

  private void upgradeTLS(SSLSocket var1) {
    if(protocols != null) {
      var1.setEnabledProtocols(protocols);
    }

    if(VERSION.SDK_INT < 21 && cipherSuites != null) {
      var1.setEnabledCipherSuites(cipherSuites);
    }

  }

  public String[] getDefaultCipherSuites() {
    return cipherSuites;
  }

  public String[] getSupportedCipherSuites() {
    return cipherSuites;
  }

  public Socket createSocket(Socket var1, String var2, int var3, boolean var4) throws IOException {
    if((var1 = this.defaultFactory.createSocket(var1, var2, var3, var4)) instanceof SSLSocket) {
      this.upgradeTLS((SSLSocket)var1);
    }

    return var1;
  }

  public Socket createSocket(String var1, int var2) throws IOException, UnknownHostException {
    Socket var3;
    if((var3 = this.defaultFactory.createSocket(var1, var2)) instanceof SSLSocket) {
      this.upgradeTLS((SSLSocket)var3);
    }

    return var3;
  }

  public Socket createSocket(String var1, int var2, InetAddress var3, int var4) throws IOException, UnknownHostException {
    Socket var5;
    if((var5 = this.defaultFactory.createSocket(var1, var2, var3, var4)) instanceof SSLSocket) {
      this.upgradeTLS((SSLSocket)var5);
    }

    return var5;
  }

  public Socket createSocket(InetAddress var1, int var2) throws IOException {
    Socket var3;
    if((var3 = this.defaultFactory.createSocket(var1, var2)) instanceof SSLSocket) {
      this.upgradeTLS((SSLSocket)var3);
    }

    return var3;
  }

  public Socket createSocket(InetAddress var1, int var2, InetAddress var3, int var4) throws IOException {
    Socket var5;
    if((var5 = this.defaultFactory.createSocket(var1, var2, var3, var4)) instanceof SSLSocket) {
      this.upgradeTLS((SSLSocket)var5);
    }

    return var5;
  }

  static {
    try {
      SSLSocket var0;
      if((var0 = (SSLSocket)SSLSocketFactory.getDefault().createSocket()) != null) {
        LinkedList var1 = new LinkedList();
        String[] var2;
        int var3 = (var2 = var0.getSupportedProtocols()).length;

        for(int var4 = 0; var4 < var3; ++var4) {
          String var5;
          if(!(var5 = var2[var4]).toUpperCase().contains("SSL")) {
            var1.add(var5);
          }
        }

        protocols = (String[])var1.toArray(new String[var1.size()]);
        if(VERSION.SDK_INT < 21) {
          List var7 = Arrays.asList(new String[]{"TLS_RSA_WITH_AES_256_GCM_SHA384", "TLS_RSA_WITH_AES_128_GCM_SHA256", "TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA256", "TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256", "TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384", "TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256", "TLS_ECHDE_RSA_WITH_AES_128_GCM_SHA256", "TLS_RSA_WITH_3DES_EDE_CBC_SHA", "TLS_RSA_WITH_AES_128_CBC_SHA", "TLS_RSA_WITH_AES_256_CBC_SHA", "TLS_ECDHE_ECDSA_WITH_3DES_EDE_CBC_SHA", "TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA", "TLS_ECDHE_RSA_WITH_3DES_EDE_CBC_SHA", "TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA"});
          List var8 = Arrays.asList(var0.getSupportedCipherSuites());
          HashSet var9;
          (var9 = new HashSet(var7)).retainAll(var8);
          var9.addAll(new HashSet(Arrays.asList(var0.getEnabledCipherSuites())));
          cipherSuites = (String[])var9.toArray(new String[var9.size()]);
        }
      }

    } catch (IOException var6) {
      throw new RuntimeException(var6);
    }
  }
}


/* Location:           C:\Users\THINK\Desktop\tem\doc\halocash_plugin.jar

 * Qualified Name:     com.halocash.volley.SSLSocketFactoryCompat

 * JD-Core Version:    0.7.0.1

 */