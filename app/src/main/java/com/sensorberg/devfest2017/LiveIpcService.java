package com.sensorberg.devfest2017;

import com.sensorberg.devfest2017.data.ParcelableData;
import com.sensorberg.easyipc.IpcListener;
import com.sensorberg.easyipc.IpcService;

public class LiveIpcService extends IpcService {

	@Override public void onCreate() {
		super.onCreate();
		addListener(ParcelableData.class, onData);
	}

	@Override public void onDestroy() {
		removeListener(ParcelableData.class, onData);
		super.onDestroy();
	}

	private final IpcListener<ParcelableData> onData = new IpcListener<ParcelableData>() {
		@Override public void onEvent(ParcelableData event) {
			// loop-back data to sender
			dispatchEvent(event);
		}
	};

}
