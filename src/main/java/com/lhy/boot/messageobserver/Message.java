package com.lhy.boot.messageobserver;

import java.io.Serializable;

public class Message implements Serializable {

	private static final long serialVersionUID = 1L;

	private final long timestamp;
	private Action action;
	private Object data;

	public Message(Action action, Object data) {
		this.action = action;
		this.data = data;
		this.timestamp = System.currentTimeMillis();
	}

	public Object getData() {
		return data;
	}

	public Action getAction() {
		return action;
	}


	public long getTimestamp() {
		return this.timestamp;
	}

	@Override
	public String toString() {
		return "Message [timestamp=" + timestamp + ", action=" + action + ", data=" + data + "]";
	}

}
