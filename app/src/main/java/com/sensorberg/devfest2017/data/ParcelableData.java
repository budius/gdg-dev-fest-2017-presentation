package com.sensorberg.devfest2017.data;

import android.os.Parcel;
import android.os.Parcelable;

public class ParcelableData implements Parcelable {

	public final String value1;
	public final int value2;

	public ParcelableData(String value1, int value2) {
		this.value1 = value1;
		this.value2 = value2;
	}

	protected ParcelableData(Parcel in) {
		value1 = in.readString();
		value2 = in.readInt();
	}

	public static final Creator<ParcelableData> CREATOR = new Creator<ParcelableData>() {
		@Override
		public ParcelableData createFromParcel(Parcel in) {
			return new ParcelableData(in);
		}

		@Override
		public ParcelableData[] newArray(int size) {
			return new ParcelableData[size];
		}
	};

	@Override public int describeContents() {
		return 0;
	}

	@Override public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(value1);
		dest.writeInt(value2);
	}
}
