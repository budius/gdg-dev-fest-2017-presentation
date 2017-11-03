package com.sensorberg.devfest2017;

public class Resource<T> {

	public final Status status;
	public final T t;

	public Resource(Status status, T t) {
		this.status = status;
		this.t = t;
	}

	public enum Status {
		NOT_STARTED,
		LOADING,
		SUCCESS,
		FAIL_IO,
		FAIL_RESPONSE
	}
}
