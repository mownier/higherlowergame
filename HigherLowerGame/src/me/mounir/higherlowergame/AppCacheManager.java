package me.mounir.higherlowergame;

import java.util.HashMap;
import java.util.Map;

public enum AppCacheManager {
	sharedInstance;
	
	private final Map<String,String> scores = new HashMap<String,String>();

	public Map<String,String> getScores() {
		return scores;
	}
	
}
