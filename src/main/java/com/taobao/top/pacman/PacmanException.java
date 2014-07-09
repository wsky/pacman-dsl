package com.taobao.top.pacman;

public class PacmanException extends RuntimeException {
	private static final long serialVersionUID = 3304640387631887577L;
	
	public PacmanException(String message) {
		super(message);
	}
	
	public PacmanException(Throwable e) {
		super(e);
	}
	
	public PacmanException(String message, Throwable e) {
		super(message, e);
	}
}
