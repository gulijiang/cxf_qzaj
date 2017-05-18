package com.qzaj.entity;

/**
 * 重大危险源级别
 * @author gulj
 * @version 20160720
 */
public class DangerLevel {
	public int level; 
	public String levelName;
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public String getLevelName() {
		return levelName;
	}
	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}
	
	public DangerLevel(int level,String levelName){
		this.level = level;
		this.levelName = levelName;
	}
}
