package com.sensorberg.devfest2017.livedata;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.Transformations;
import android.support.annotation.Nullable;

import com.sensorberg.devfest2017.DataRepo;
import com.sensorberg.devfest2017.Dependencies;
import com.sensorberg.devfest2017.Resource;
import com.sensorberg.devfest2017.data.Account;

import javax.inject.Inject;

import retrofit2.Call;

public abstract class LiveRefreshableRetrofit<T> extends MediatorLiveData<Resource<T>> {

	private final MutableLiveData<Boolean> control;

	public LiveRefreshableRetrofit() {

		// create a control live-data
		control = new MutableLiveData<>();
		control.setValue(false);

		LiveData<Resource<T>> data = Transformations.switchMap(control, new Function<Boolean, LiveData<Resource<T>>>() {
			@Override public LiveData<Resource<T>> apply(Boolean refresh) {
				return new LiveRetrofit<>(getSource(refresh != null && refresh));
			}
		});

		addSource(data, new Observer<Resource<T>>() {
			@Override public void onChanged(@Nullable Resource<T> r) {
				setValue(r);
			}
		});
	}

	protected abstract Call<T> getSource(boolean refresh);

	public void refresh() {
		control.setValue(true);
	}

	public static final LiveRefreshableRetrofit<Account> example = new LiveRefreshableRetrofit<Account>() {

		@Inject DataRepo repo;

		@Override protected Call<Account> getSource(boolean refresh) {

			if (repo == null) {
				// this actually doesn't work with Dagger 2, it needs a proper class
				Dependencies.get().inject(this);
			}

			return repo.getAccount(refresh ? "max-age=0" : null);
		}
	};

}
