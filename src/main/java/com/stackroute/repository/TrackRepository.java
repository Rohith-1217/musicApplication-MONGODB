package com.stackroute.repository;

import com.stackroute.domain.Track;

import com.stackroute.exception.TrackNotFoundException;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;


//@Repository
public interface TrackRepository extends MongoRepository<Track,Integer> {

    //Finding by Track Name,if track not present it throws an Exception
    //@Query("select t from Track t where t.trackName=:trackName")
     //public Track getByName(@Param("trackName") String trackName) throws TrackNotFoundException;

}
