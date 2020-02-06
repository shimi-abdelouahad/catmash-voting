package com.catmash.voting.api.model;

public class CatApiBean implements Comparable<CatApiBean> {

	private String id;
	private String url;
	private Integer score;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	@Override
	public int compareTo(CatApiBean o) {
		int compareScore = this.score.compareTo(o.getScore());
		return compareScore != 0 ? compareScore * -1 : this.id.compareTo(o.getId());
	}

	@Override
	public int hashCode() {
		return this.id.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !this.getClass().getName().equals(obj.getClass().getName())) {
			return false;
		}
		CatApiBean other = (CatApiBean) obj;
		return this.id.equals(other.getId());
	}

}
