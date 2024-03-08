package com.example.demo.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.demo.entities.Playlist;
import com.example.demo.entities.Song;
import com.example.demo.services.PlaylistServices;
import com.example.demo.services.SongServices;

@Controller
public class PlaylistController 
{
	@Autowired
	SongServices songService;
	
	@GetMapping("/createPlaylist")
	public String createPlaylist(Model model) 
	{
		List<Song> songList = songService.fetchAllSongs();
		model.addAttribute("songs",songList);
		
		return "createPlaylist";
	}
	
	@Autowired
	PlaylistServices playlistService;
	
	@GetMapping("/addPlaylist")
	public String addPlaylist(@ModelAttribute Playlist playlist) 
	{
		 playlistService.addPlaylist(playlist);
		 
		 //updating song table
		 List<Song> songList = playlist.getSongs();
		 for(Song s : songList)
		 {
			 s.getPlaylists().add(playlist);
			 //updating song object in db
			 songService.updateSong(s);
		 }
		return "adminHome";
	}
	
	@GetMapping("/viewPlaylists")
	public String viewPlaylists(Model model)
	{
		List<Playlist> allPlaylists = playlistService.fetchAllPlaylists();
		model.addAttribute("allPlaylits",allPlaylists);
		return "displayPlaylists";
	}
	

}
