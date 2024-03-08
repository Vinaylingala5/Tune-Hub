package com.example.demo.services;

import com.example.demo.entities.Song;
import java.util.*;

public interface SongServices 
{
	public void addSong(Song song);

	public List<Song> fetchAllSongs();
	
	public boolean songExists(String name);

	public void updateSong(Song song);
}
