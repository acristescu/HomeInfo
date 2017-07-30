package io.zenandroid.homeinfo;

import android.content.Context;

import com.joanzapata.iconify.Iconify;
import com.joanzapata.iconify.fonts.MeteoconsModule;
import com.joanzapata.iconify.fonts.WeathericonsModule;

import io.zenandroid.homeinfo.dagger.Injector;

public class Application extends android.app.Application {

	private static Application instance;

	static {
		//
		// Note: if using vector images, you may want to do uncomment this line
		//
//		AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
	}

	@Override
	protected void attachBaseContext(Context base) {
		super.attachBaseContext(base);
		Injector.init(this);
	}

	@Override
	public void onCreate() {
		super.onCreate();
		instance = this;
		Iconify
				.with(new MeteoconsModule())
				.with(new WeathericonsModule());
	}

	public static Application getInstance() {
		return instance;
	}

}
