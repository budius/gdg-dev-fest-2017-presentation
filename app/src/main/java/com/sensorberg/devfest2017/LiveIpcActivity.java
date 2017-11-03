package com.sensorberg.devfest2017;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.sensorberg.devfest2017.data.ParcelableData;
import com.sensorberg.easyipc.arch.IpcViewModel;

/**
 * Not adding any view or anything, just to show use of IpcLiveData
 */
public class LiveIpcActivity extends AppCompatActivity {

	@Override protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		IpcViewModel viewModel = ViewModelProviders
				.of(this)
				.get(IpcViewModel.class)
				.init(LiveIpcService.class, BIND_AUTO_CREATE);

		viewModel.getLiveData(ParcelableData.class).observe(this, onBundle);
		viewModel.dispatchEvent(new ParcelableData("value", 42));
	}

	private Observer<ParcelableData> onBundle = new Observer<ParcelableData>() {
		@Override public void onChanged(@Nullable ParcelableData bundle) {

		}
	};
}
