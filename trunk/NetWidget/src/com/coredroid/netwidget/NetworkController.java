package com.coredroid.netwidget;

import android.content.Context;
import android.net.wifi.WifiManager;


public class NetworkController {
	
	private Context context;

	public NetworkController(Context context) {
		this.context=context;
	}

	public void setWifiConnection(boolean status) {
		WifiManager wifiManager = (WifiManager)context.getSystemService(Context.WIFI_SERVICE);
		wifiManager.setWifiEnabled(status);
	}
	
	public boolean isWifiConnection() {
		WifiManager wifiManager = (WifiManager)context.getSystemService(Context.WIFI_SERVICE);
		int state = wifiManager.getWifiState();
		boolean status=false;
		
		switch(state) {
		case WifiManager.WIFI_STATE_DISABLED:
			status = false;
			break;
		case WifiManager.WIFI_STATE_ENABLING:
			status = false;
			break;
		case WifiManager.WIFI_STATE_ENABLED:
			status = true;
			break;
		case WifiManager.WIFI_STATE_DISABLING:
			status = false;
			break;
		}
		
		return status;
	}
}
