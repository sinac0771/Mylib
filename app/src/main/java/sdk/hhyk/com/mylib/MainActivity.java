package sdk.hhyk.com.mylib;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import sdk.hhyk.com.mylib.callback.PayListener;
import sdk.hhyk.com.mylib.control.PayManager;


public class MainActivity extends AppCompatActivity {
    PayManager mPayManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPayManager= PayManager.instance(MainActivity.this);
//        HaloCashPay.init(this, PayConfig.MERCHANT_ID);

        findViewById(R.id.tv_text).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPayManager.payMent( System.currentTimeMillis()+"", "1111", "0.2", "USD", new PayListener() {
                    @Override
                    public void onPaySuccess() {

                    }

                    @Override
                    public void onFailure(String msg) {

                    }
                });
            }
        });
    }
}
