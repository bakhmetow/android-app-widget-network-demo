package com.coredroid.netwidget;

import android.app.PendingIntent;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.RemoteViews;

public class InfoService extends Service {

	private static int visibility = View.GONE;
	private static final String TAG = "NetWidgetProvider";

	@Override
	public void onStart(Intent intent, int startId) {
		Log.d(TAG, "onStart InfoService");
		Context context = getApplicationContext();

		AppWidgetManager manager = AppWidgetManager.getInstance(context);

		int[] widgetIdList = intent
				.getIntArrayExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS);

		for (int widgetId : widgetIdList) {
			RemoteViews views = new RemoteViews(context.getPackageName(),
					R.layout.app_widget_main);

			views.setViewVisibility(R.id.lyInfo, visibility);

			if (visibility == View.GONE)
				visibility = View.VISIBLE;
			else
				visibility = View.GONE;

			Intent clickIntent = new Intent(context, NetWidgetProvider.class);
			clickIntent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
			clickIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS,
					widgetIdList);

			PendingIntent pendingIntent = PendingIntent.getBroadcast(context,
					0, clickIntent, PendingIntent.FLAG_UPDATE_CURRENT);
			views.setOnClickPendingIntent(R.id.lyInfo, pendingIntent);
			manager.updateAppWidget(widgetId, views);
		}

		stopSelf();

		super.onStart(intent, startId);
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

}
