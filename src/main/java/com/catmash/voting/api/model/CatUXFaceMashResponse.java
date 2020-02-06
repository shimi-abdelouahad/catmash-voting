package com.catmash.voting.api.model;

public class CatUXFaceMashResponse {

	private CatApiBean first;
	private CatApiBean seconde;

	public CatUXFaceMashResponse() {
	}

	public CatUXFaceMashResponse(CatApiBean first, CatApiBean seconde) {
		this.first = first;
		this.seconde = seconde;
	}

	public CatApiBean getFirst() {
		return first;
	}

	public void setFirst(CatApiBean first) {
		this.first = first;
	}

	public CatApiBean getSeconde() {
		return seconde;
	}

	public void setSeconde(CatApiBean seconde) {
		this.seconde = seconde;
	}

}
