package com.stackroute.controller;

import com.stackroute.domain.Track;
import com.stackroute.exception.GlobalException;
import com.stackroute.exception.TrackAlreadyExistsException;
import com.stackroute.exception.TrackNotFoundException;
import com.stackroute.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping(value="api/v1")
public class TrackController {
    private TrackService trackService;

    @Autowired
    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }

    @PostMapping("track")
    public ResponseEntity<?> saveTrack(@RequestBody Track track) {
        ResponseEntity responseEntity;
        try {
            trackService.saveTrack(track);
            responseEntity = new ResponseEntity("successfully created", HttpStatus.CREATED);
        } catch (TrackAlreadyExistsException ex) {
            responseEntity = new ResponseEntity<String>(ex.getMessage(), HttpStatus.CONFLICT);
            ex.printStackTrace();
        }
        return responseEntity;
    }

    @GetMapping("track")
    public ResponseEntity<?> getAllTracks() throws TrackNotFoundException {
        ResponseEntity responseEntity;
        if(trackService.getAllTracks().isEmpty()){
            throw new TrackNotFoundException("track empty");
        }
        else {
            responseEntity = new ResponseEntity<List<Track>>(trackService.getAllTracks(), HttpStatus.OK);
        }
        return responseEntity;
        //return new ResponseEntity<List<Track>>(trackService.getAllTracks(), HttpStatus.OK);
    }

    @DeleteMapping("track/{trackId}")
    public ResponseEntity<?> deleteTrack(@PathVariable("trackId") int trackId) throws TrackNotFoundException {
        //return new ResponseEntity<String>(trackService.deleteTrack(id),HttpStatus.OK);

            Track track = trackService.deleteTrack(trackId);
            return new ResponseEntity<String>("track deleted", HttpStatus.OK);
    }

    @PutMapping("track/{trackId}")
    public ResponseEntity<?> updateComments(@RequestBody Track track) throws TrackNotFoundException {

        Track track1 = trackService.updateComments(track);
        return new ResponseEntity<String>("track updated", HttpStatus.OK);

    }
}
