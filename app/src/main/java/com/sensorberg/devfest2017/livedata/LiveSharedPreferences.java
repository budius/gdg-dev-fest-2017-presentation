package com.sensorberg.devfest2017.livedata;

import android.arch.lifecycle.MutableLiveData;
import android.content.SharedPreferences;
import android.os.Looper;
import android.support.annotation.NonNull;

public abstract class LiveSharedPreferences<T> extends MutableLiveData<T> {

	public static MutableLiveData<String> getString(SharedPreferences preferences, String alias) {
		return new TypeString(alias, preferences);
	}

	public static MutableLiveData<Boolean> getBoolean(SharedPreferences preferences, String alias) {
		return new TypeBoolean(alias, preferences);
	}

	final String alias;
	final SharedPreferences preferences;

	private LiveSharedPreferences(String alias, SharedPreferences preferences) {
		this.alias = alias;
		this.preferences = preferences;
	}

	@Override protected void onActive() {
		super.onActive();
		load();
		preferences.registerOnSharedPreferenceChangeListener(onChanged);
	}

	@Override protected void onInactive() {
		preferences.unregisterOnSharedPreferenceChangeListener(onChanged);
		super.onInactive();
	}

	private final SharedPreferences.OnSharedPreferenceChangeListener onChanged =
			new SharedPreferences.OnSharedPreferenceChangeListener() {
				@Override
				public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
					if (alias.equals(key)) {
						load();
					}
				}
			};

	@Override public void setValue(T value) {
		super.setValue(value);
		updatePrefs(value);
	}

	@Override public void postValue(T value) {
		super.postValue(value);
		updatePrefs(value);
	}

	private void updatePrefs(T value) {
		if (value == null) {
			preferences.edit().remove(alias).apply();
		} else {
			save(value);
		}
	}

	abstract void load();

	abstract void save(@NonNull T value);

	private static class TypeBoolean extends LiveSharedPreferences<Boolean> {

		private TypeBoolean(String alias, SharedPreferences preferences) {
			super(alias, preferences);
		}

		@Override void load() {
			LiveSharedPreferences.safeSetValue(this, preferences.getBoolean(alias, false));
		}

		@Override void save(@NonNull Boolean value) {
			preferences.edit().putBoolean(alias, value).apply();
		}
	}

	private static class TypeString extends LiveSharedPreferences<String> {

		private TypeString(String alias, SharedPreferences preferences) {
			super(alias, preferences);
		}

		@Override protected void load() {
			LiveSharedPreferences.safeSetValue(this, preferences.getString(alias, null));
		}

		@Override void save(@NonNull String value) {
			preferences.edit().putString(alias, value).apply();
		}

	}

	private static <T> void safeSetValue(MutableLiveData<T> data, T value) {
		if (Looper.getMainLooper().equals(Looper.myLooper())) {
			data.setValue(value);
		} else {
			data.postValue(value);
		}
	}
}
