package com.osori.lovesj.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import com.osori.lovesj.R;
import com.osori.lovesj.listener.SwipeAdapter;
import com.osori.lovesj.notification.BirthdayNotificationChannel;
import com.osori.lovesj.notification.BirthdayNotificationScheduler;

public class OsoriActivity extends AppCompatActivity {
	private static final int POST_NOTIFICATION_REQUEST_CODE = 3001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_osori);

		BirthdayNotificationChannel.ensureCreated(this);
		requestNotificationPermissionIfNeeded();
		BirthdayNotificationScheduler.scheduleDaily(this);

        ViewPager viewPager = findViewById(R.id.view_page);
        viewPager.setOffscreenPageLimit(1);
        SwipeAdapter swipeAdapter = new SwipeAdapter(getSupportFragmentManager());
        viewPager.setAdapter(swipeAdapter);
        viewPager.setCurrentItem(1);
    }

	private void requestNotificationPermissionIfNeeded() {
		if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
			return;
		}

		if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS)
				== PackageManager.PERMISSION_GRANTED) {
			return;
		}

		ActivityCompat.requestPermissions(
				this,
				new String[]{Manifest.permission.POST_NOTIFICATIONS},
				POST_NOTIFICATION_REQUEST_CODE);
	}
}
