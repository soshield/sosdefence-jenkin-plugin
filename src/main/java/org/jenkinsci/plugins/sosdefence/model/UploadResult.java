package org.jenkinsci.plugins.sosdefence.model;

import lombok.Value;

@Value
public class UploadResult {

	private final boolean success;
	private final String token;

	public UploadResult(boolean success) {
		this(success, null);
	}

	public UploadResult(boolean success, String token) {
		this.success = success;
		this.token = token;
	}

}
