package com.bis.operox.inv.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "webgeocities", catalog = "operox")
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL,region="webgeocities")
public class Webgeocities {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
	private Long id;
	
	@Column(name = "cityName")
	private String cityName;
	
	@Column(name = "countryCode")
	private String countryCode;
	
	@Column(name = "stateCode")
	private String stateCode;
	
	@Column(name = "cityNameAscii")
	private String cityNameAscii;
	
	@Column(name = "latitude")
	private Float latitude;
	
	@Column(name = "longitude")
	private Float longitude;
	
	@Column(name = "timezoneid")
	private String timezoneid;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public String getCityNameAscii() {
		return cityNameAscii;
	}

	public void setCityNameAscii(String cityNameAscii) {
		this.cityNameAscii = cityNameAscii;
	}

	public Float getLatitude() {
		return latitude;
	}

	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}

	public Float getLongitude() {
		return longitude;
	}

	public void setLongitude(Float longitude) {
		this.longitude = longitude;
	}

	public String getTimezoneid() {
		return timezoneid;
	}

	public void setTimezoneid(String timezoneid) {
		this.timezoneid = timezoneid;
	}

}
