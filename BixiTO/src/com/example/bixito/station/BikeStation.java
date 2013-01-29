package com.example.bixito.station;

public class BikeStation {
	int stationId;
	String stationName;
	String terminalName;
	long lastCommWithServer;
	double latitude;
	double longitude;
	boolean installed; //what is this?
	boolean locked;
	boolean temporary;
	boolean publicStation;
	int nbBikes;
	int nbEmptyDocks;
	long latestUpdateTime;
	
	/**
	 * 
	 * @param stationID
	 * @param stationName
	 * @param terminalName
	 * @param lastCommWithServer
	 * @param latitude
	 * @param longitude
	 * @param installed
	 * @param locked
	 * @param temporary
	 * @param publicStation
	 * @param nbBikes
	 * @param nbEmptyDocks
	 * @param latestUpdateTime
	 */
	public BikeStation(int stationId, String stationName, String terminalName, long lastCommWithServer,
				double latitude, double longitude, boolean installed, boolean locked, boolean temporary,
				boolean publicStation, int nbBikes, int nbEmptyDocks, long latestUpdateTime) {
		setStationId(stationId);
		setStationName(stationName);
		setTerminalName(terminalName);
		setLastCommWithServer(lastCommWithServer);
		setLatitude(latitude);
		setLongitude(longitude);
		setInstalled(installed);
		setLocked(locked);
		setTemporary(temporary);
		setPublicStation(publicStation);
		setNbBikes(nbBikes);
		setNbEmptyDocks(nbEmptyDocks);
		setLatestUpdateTime(latestUpdateTime);
		
	}

	/**
	 * @return the stationId
	 */
	public int getStationId() {
		return stationId;
	}

	/**
	 * @param stationId the stationId to set
	 */
	public void setStationId(int stationId) {
		this.stationId = stationId;
	}

	/**
	 * @return the stationName
	 */
	public String getStationName() {
		return stationName;
	}

	/**
	 * @param stationName the stationName to set
	 */
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	/**
	 * @return the terminalName
	 */
	public String getTerminalName() {
		return terminalName;
	}

	/**
	 * @param terminalName the terminalName to set
	 */
	public void setTerminalName(String terminalName) {
		this.terminalName = terminalName;
	}

	/**
	 * @return the lastCommWithServer
	 */
	public long getLastCommWithServer() {
		return lastCommWithServer;
	}

	/**
	 * @param lastCommWithServer the lastCommWithServer to set
	 */
	public void setLastCommWithServer(long lastCommWithServer) {
		this.lastCommWithServer = lastCommWithServer;
	}

	/**
	 * @return the latitude
	 */
	public double getLatitude() {
		return latitude;
	}

	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	/**
	 * @return the longitude
	 */
	public double getLongitude() {
		return longitude;
	}

	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	/**
	 * @return the installed
	 */
	public boolean isInstalled() {
		return installed;
	}

	/**
	 * @param installed the installed to set
	 */
	public void setInstalled(boolean installed) {
		this.installed = installed;
	}

	/**
	 * @return the locked
	 */
	public boolean isLocked() {
		return locked;
	}

	/**
	 * @param locked the locked to set
	 */
	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	/**
	 * @return the temporary
	 */
	public boolean isTemporary() {
		return temporary;
	}

	/**
	 * @param temporary the temporary to set
	 */
	public void setTemporary(boolean temporary) {
		this.temporary = temporary;
	}

	/**
	 * @return the publicStation
	 */
	public boolean isPublicStation() {
		return publicStation;
	}

	/**
	 * @param publicStation the publicStation to set
	 */
	public void setPublicStation(boolean publicStation) {
		this.publicStation = publicStation;
	}

	/**
	 * @return the nbBikes
	 */
	public int getNbBikes() {
		return nbBikes;
	}

	/**
	 * @param nbBikes the nbBikes to set
	 */
	public void setNbBikes(int nbBikes) {
		this.nbBikes = nbBikes;
	}

	/**
	 * @return the nbEmptyDocks
	 */
	public int getNbEmptyDocks() {
		return nbEmptyDocks;
	}

	/**
	 * @param nbEmptyDocks the nbEmptyDocks to set
	 */
	public void setNbEmptyDocks(int nbEmptyDocks) {
		this.nbEmptyDocks = nbEmptyDocks;
	}

	/**
	 * @return the latestUpdateTime
	 */
	public long getLatestUpdateTime() {
		return latestUpdateTime;
	}

	/**
	 * @param latestUpdateTime the latestUpdateTime to set
	 */
	public void setLatestUpdateTime(long latestUpdateTime) {
		this.latestUpdateTime = latestUpdateTime;
	}
	
}

