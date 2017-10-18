package sdk.hhyk.com.mylib.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import org.json.JSONObject;

import java.net.URLEncoder;

import sdk.hhyk.com.mylib.PayConfig;
import sdk.hhyk.com.mylib.Util.BitmapCache;
import sdk.hhyk.com.mylib.Util.Utils;


/**
 * 公用显示的view
 *
 * @author smallwei
 */
public class DView extends FrameLayout {
    private Context context;

    /**
     * 显示的宽度
     */
    private int mWidth;
    /**
     * 显示的高度
     */
    private int mHeight;

    /**
     * 关闭的布局
     */
    private FrameLayout mCloseLay;
    /**
     * H5显示的
     */
    private DWebView mDWebView;
//    /**
//     * 加载的布局
//     */
//    private LinearLayout mLoadingLay;
    /**
     * 取消xx
     */
    private ImageView mIvExit;
    DtDialog.CloseInterface closeInterface;

    public DView(Context context,  int width, int height) {
        super(context);
        this.context = context;
        this.mHeight = height;
        this.mWidth = width;
        createLayouts();
    }

    public void setCloseInterface(DtDialog.CloseInterface closeInterface) {
        this.closeInterface = closeInterface;
    }

    public void initData(String transid, String orderID ){


    try {
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("transid",transid);
        jsonObject.put("outtradeno",orderID);
        jsonObject.put("backurl", PayConfig.NOTIFY_URL);
        jsonObject.put("country","US");
        String data= jsonObject.toString();
        String sign="transdata=" + URLEncoder.encode(data,"UTF-8");

        mDWebView.loadUrl(PayConfig.URL_PAYAPI_WEB+"?"+sign);
    } catch (Exception e) {
        e.printStackTrace();
        Log.e("transdata==printSt",e.getMessage());
    }

}


    /**
     * 设置控件的大小
     */
    private void createLayouts() {
        setViewHW();
        setBackgroundResource(android.R.color.transparent);


        mDWebView = new DWebView(context);
        LayoutParams dWebViewLp = new LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        dWebViewLp.gravity = Gravity.CENTER;
        mDWebView.setLayoutParams(dWebViewLp);
        //
        addView(mDWebView);

        // 添加关闭按钮

        mCloseLay = new FrameLayout(context);
        LayoutParams closeLayParams = new LayoutParams(
				/*mWidth*/-1, /*mHeight*/-1);
        mCloseLay.setLayoutParams(closeLayParams);

        mIvExit = new ImageView(context);
        mIvExit.setTag("right");
        Drawable d = BitmapCache.getCloseDb(getContext());
        if (d != null) {
            mIvExit.setImageDrawable(d);
        }
        mIvExit.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

              if (closeInterface != null) {
                  closeInterface.close();
                }
            }
        });

        LayoutParams closeRightBtnLp = new LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
        closeRightBtnLp.rightMargin = Utils.dip2px(getContext(), 5);
            closeRightBtnLp.topMargin = Utils.dip2px(getContext(), 5);
            closeRightBtnLp.gravity = Gravity.RIGHT | Gravity.TOP;
        mIvExit.setLayoutParams(closeRightBtnLp);
        mCloseLay.addView(mIvExit);
        addView(mCloseLay);

        // 默认进来显示加载进度，别的隐藏
        mCloseLay.setVisibility(View.VISIBLE);
        mDWebView.setVisibility(View.VISIBLE);
//        mLoadingLay.setVisibility(View.VISIBLE);

    }

    private void setViewHW() {
        LayoutParams params = new LayoutParams(mWidth, mHeight);
        setLayoutParams(params);
    }











}
