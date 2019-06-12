package com.stackroute.service;

import com.stackroute.domain.Track;
import com.stackroute.exception.GlobalException;
import com.stackroute.exception.TrackAlreadyExistsException;
import com.stackroute.exception.TrackNotFoundException;
import com.stackroute.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//Runs this class first
@Primary
@Qualifier
public class TrackServiceImpl implements TrackService{

    //override all methods present in TrackService

    TrackRepository trackRepository;
    @Value("${spring.trackAlreadyExists}")
    String str;
    @Value("${spring.trackNotFound}")
    String str1;


    @Autowired
    public TrackServiceImpl(TrackRepository trackRepository)
    {
        this.trackRepository=trackRepository;
    }


    @Override
    public Track saveTrack(Track track) throws TrackAlreadyExistsException{
        if(trackRepository.existsById(track.getTrackId()))
        {
            throw new TrackAlreadyExistsException(str);
        }
        Track savedUser=trackRepository.save(track);

        if(savedUser==null)
        {
            throw new TrackAlreadyExistsException(str);
        }

        return savedUser;
    }

    @Override
    public List<Track> getAllTracks() throws TrackNotFoundException{
        List<Track> list=trackRepository.findAll();
        if(list.isEmpty()){
            throw new TrackNotFoundException(str);
        }
        return list;
    }

    @Override
    public Track deleteTrack(int trackId) throws TrackNotFoundException {
        if(trackRepository.existsById(trackId))
        {
            trackRepository.deleteById(trackId);
        }
        else
        {
            throw new TrackNotFoundException("str1");
        }
        return null;
    }

    @Override
    public Track updateComments(Track track) throws TrackNotFoundException {
        Track track1=null;
        if(trackRepository.existsById(track.getTrackId()))
        {
            track.setTrackComments(track.getTrackComments()); //trackRepository.save(Track)
            track1=trackRepository.save(track);
        }
        else
        {
            throw new TrackNotFoundException();
        }

        return track1;

    }
}
