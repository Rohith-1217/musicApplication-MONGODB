package com.stackroute.service;

import com.stackroute.domain.Track;
import com.stackroute.exception.GlobalException;
import com.stackroute.exception.TrackAlreadyExistsException;
import com.stackroute.exception.TrackNotFoundException;

import java.util.List;

//Create methods in interface  which we implement in TrackServiceImpl
public interface TrackService {

    public Track saveTrack(Track track) throws TrackAlreadyExistsException;

    public List<Track> getAllTracks() throws TrackNotFoundException;

    public Track deleteTrack(int trackId) throws TrackNotFoundException;

    public Track updateComments(Track track) throws GlobalException;
}
