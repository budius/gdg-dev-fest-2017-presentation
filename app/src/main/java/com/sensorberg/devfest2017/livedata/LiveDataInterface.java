package com.sensorberg.devfest2017.livedata;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.Transformations;
import android.support.annotation.Nullable;

/**
 * This doesn't do anything
 * Just showing the live data API to copy-paste into the presentation
 */
public class LiveDataInterface {

	public static void api() {

		// abstract base class, "protected" to change data
		LiveData<String> liveData = new LiveData<String>() { };

		// mutable is "public" to change value
		MutableLiveData<String> mutableLiveData = new MutableLiveData<>();
		mutableLiveData.setValue("some value");

		// mediator propagates active/inactive state to added sources
		final MediatorLiveData<String> mediatorLiveData = new MediatorLiveData<>();
		mediatorLiveData.addSource(mutableLiveData, new Observer<String>() {
			@Override public void onChanged(@Nullable String s) {
				if (s != null && s.length() > 2) {
					mediatorLiveData.setValue(s.substring(2));
				}
			}
		});

		// Transformations are just some static methods that wrap a MediatorLiveData
		LiveData<String> map = Transformations.map(mediatorLiveData, new Function<String, String>() {
			@Override public String apply(String input) {
				return "mapped: " + input;
			}
		});
	}
}
