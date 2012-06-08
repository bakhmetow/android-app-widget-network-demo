package com.coredroid.netwidget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

public class NetWidgetProvider extends AppWidgetProvider {

	public static String CLICK_WIFI = "com.coredroid.netwidget.WIFI";
	public static String CLICK_DATA = "com.coredroid.netwidget.DATA";
	public static String CLICK_INFO = "com.coredroid.netwidget.INFO";
	private static final String TAG = "NetWidgetProvider";

	@Override
	public void onReceive(Context context, Intent intent) {
		String action = intent.getAction();

		if (CLICK_WIFI.equals(action)) {
			Toast.makeText(context, "wifi option!", Toast.LENGTH_SHORT).show();
			startWifiService(context);
		} else if (CLICK_DATA.equals(action)) {
			Toast.makeText(context, "data option!", Toast.LENGTH_SHORT).show();
		//	startDataService(context);
		}
		else
			if(CLICK_INFO.equals(action)) {
				Toast.makeText(context, "info option!", Toast.LENGTH_SHORT).show();
				startInfoService(context);
			}

		Log.d(TAG, "##### - " + intent.getAction());

		super.onReceive(context, intent);
	}

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		Log.d(TAG, "onUpdate");

		for (int i = 0; i < appWidgetIds.length; ++i) {
			final RemoteViews rv = new RemoteViews(context.getPackageName(),
					R.layout.app_widget_main);

			final Intent onClickWifiIntent = new Intent(context,
					NetWidgetProvider.class);
			onClickWifiIntent.setAction(CLICK_WIFI);
			onClickWifiIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
					appWidgetIds[i]);
			onClickWifiIntent.setData(Uri.parse(onClickWifiIntent
					.toUri(Intent.URI_INTENT_SCHEME)));

			final PendingIntent onClickWifiPendingIntent = PendingIntent
					.getBroadcast(context, 0, onClickWifiIntent,
							PendingIntent.FLAG_UPDATE_CURRENT);
			rv.setOnClickPendingIntent(R.id.btnWifi, onClickWifiPendingIntent);

			final Intent onClickDataIntent = new Intent(context,
					NetWidgetProvider.class);
			onClickDataIntent.setAction(CLICK_DATA);

			final PendingIntent onClickDatahPendingIntent = PendingIntent
					.getBroadcast(context, 0, onClickDataIntent,
							PendingIntent.FLAG_UPDATE_CURRENT);
			rv.setOnClickPendingIntent(R.id.btnData, onClickDatahPendingIntent);
			
			final Intent onClickInfoIntent = new Intent(context,
					NetWidgetProvider.class);
			onClickInfoIntent.setAction(CLICK_INFO);

			final PendingIntent onClickInfoPendingIntent = PendingIntent
					.getBroadcast(context, 0, onClickInfoIntent,
							PendingIntent.FLAG_UPDATE_CURRENT);
			rv.setOnClickPendingIntent(R.id.btnInfo, onClickInfoPendingIntent);

			appWidgetManager.updateAppWidget(appWidgetIds[i], rv);
		}

		super.onUpdate(context, appWidgetManager, appWidgetIds);
	}

	private void startWifiService(Context context) {
		ComponentName thisWidget = new ComponentName(context,
				NetWidgetProvider.class);
		AppWidgetManager appWidgetManager = AppWidgetManager
				.getInstance(context);
		int[] allWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget);

		// Build the intent to call the service
		Intent intent = new Intent(context.getApplicationContext(),
				WifiService.class);
		intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, allWidgetIds);

		// Update the widgets via the service
		context.startService(intent);
	}
	
	private void startDataService(Context context) {
		ComponentName thisWidget = new ComponentName(context,
				NetWidgetProvider.class);
		AppWidgetManager appWidgetManager = AppWidgetManager
				.getInstance(context);
		int[] allWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget);

		// Build the intent to call the service
		Intent intent = new Intent(context.getApplicationContext(),
				DataService.class);
		intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, allWidgetIds);

		// Update the widgets via the service
		context.startService(intent);
	}
	
	private void startInfoService(Context context) {
		ComponentName thisWidget = new ComponentName(context,
				NetWidgetProvider.class);
		AppWidgetManager appWidgetManager = AppWidgetManager
				.getInstance(context);
		int[] allWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget);

		// Build the intent to call the service
		Intent intent = new Intent(context.getApplicationContext(),
				InfoService.class);
		intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, allWidgetIds);

		// Update the widgets via the service
		context.startService(intent);
	}
}
