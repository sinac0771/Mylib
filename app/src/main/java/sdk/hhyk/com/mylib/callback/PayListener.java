package sdk.hhyk.com.mylib.callback;

public interface PayListener {
	
	public void onPaySuccess();
	
	public void onFailure(String msg);

}
