package com.catmash.voting.data.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "T_CAT")
public class Cat implements Serializable {

	private static final long serialVersionUID = 6305772840315256701L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer key;
	@Column(name = "CAT_REF", nullable = false)
	private String id;
	@Column(name = "CAT_SRC", nullable = false)
	private String url;
	private int score;

	public Integer getKey() {
		return key;
	}

	public void setKey(Integer key) {
		this.key = key;
	}

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

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public int hashCode() {
		return key.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !this.getClass().getName().equals(obj.getClass().getName())) {
			return false;
		}
		Cat other = (Cat) obj;
		return this.key.equals(other.getKey());
	}

	@Override
	public String toString() {

		return "id::" + id + " url:: " + url + " score::" + score;
	}

}
