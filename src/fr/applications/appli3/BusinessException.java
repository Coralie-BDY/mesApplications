package fr.applications.appli3;

import java.util.ArrayList;
import java.util.List;

public class BusinessException extends Exception {
	private static final long serialVersionUID = 1L;
	private List<Integer> listError;
	
	public BusinessException() {
		super();
		this.listError=new ArrayList<>();
	}
	
	public void addError(int code) {
		if (!this.listError.contains(code)) {
			this.listError.add(code);
		}
	}
	
	public boolean hasErrors() {
		return this.listError.size()>0;
	}
	
	public List<Integer> getlistError() {
		return this.listError;
	}
}


