package com.sensorberg.devfest2017.livedata;

import android.arch.lifecycle.LiveData;

import com.sensorberg.devfest2017.Resource;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LiveRetrofit<T> extends LiveData<Resource<T>> {

	private final Call<T> call;

	public LiveRetrofit(Call<T> call) {
		this.call = call;
		setValue(new Resource<T>(Resource.Status.NOT_STARTED, null));
	}

	@Override protected void onActive() {
		if (!call.isExecuted()) {
			call.enqueue(callback);
		}
	}

	private final Callback<T> callback = new Callback<T>() {
		@Override public void onResponse(Call<T> call, Response<T> response) {
			if (response.isSuccessful()) {
				setValue(new Resource<T>(Resource.Status.SUCCESS, response.body()));
			} else {
				setValue(new Resource<T>(Resource.Status.FAIL_RESPONSE, null));
			}
		}

		@Override public void onFailure(Call<T> call, Throwable t) {
			setValue(new Resource<T>(Resource.Status.FAIL_IO, null));
		}
	};
}
