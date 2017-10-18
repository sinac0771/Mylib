package sdk.hhyk.com.mylib.callback;


public interface PayCallback {
	
	public void onPaySuccess(String data);
	public void onFailure(String message);

}
