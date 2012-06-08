package com.coredroid.netwidget;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class WifiService extends Service {

	@Override
	public void onStart(Intent intent, int startId) {
		NetworkController wifi = new NetworkController(this);
		wifi.setWifiConnection(!wifi.isWifiConnection());
		super.onStart(intent, startId);
	}

	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}

}
