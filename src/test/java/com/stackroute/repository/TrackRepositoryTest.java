package com.stackroute.repository;


import com.stackroute.domain.Track;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataMongoTest
public class TrackRepositoryTest {
    @Autowired
    private TrackRepository trackRepository;
    private Track track;

    @Before
    public void setup()
    {
        track=new Track();
        track.setTrackId(1);
        track.setTrackName("aadi");
        track.setTrackComments("good album");
    }

    @After
    public void tearDown(){
        trackRepository.deleteAll();
    }

    @Test
    public void testSaveTrackSuccess()
    {
        trackRepository.save(track);
        Track fetchTrack=trackRepository.findById(track.getTrackId()).get();
        Assert.assertEquals(1,fetchTrack.getTrackId());
    }
    @Test
    public void testSaveTrackFailure(){
        Track testUser = new Track(2,"nagaa","Nagaa Album");
        trackRepository.save(track);
        Track fetchUser = trackRepository.findById(track.getTrackId()).get();
        Assert.assertNotSame(testUser,track);
    }

    @Test
    public void testGetAllTracks() {
        Track t = new Track(3,"nannaku","nannaku album");
        Track t1 = new Track(4,"prematho","prematho album");
        trackRepository.save(t);
        trackRepository.save(t1);

        List<Track> list = trackRepository.findAll();
        Assert.assertEquals(3, list.get(0).getTrackId());
    }

    @Test
    public void testDeleteTrackSuccess(){
        //Track track=new Track(2,"nagaa","Nagaa album");
        trackRepository.delete(track);
        boolean deletedTrack=trackRepository.existsById(1);
        Assert.assertEquals(false,deletedTrack);
    }

    @Test
    public void testDeleteTrackFailure(){
        //Track track=new Track(2,"nagaa","Nagaa album");
        trackRepository.delete(track);
        boolean deletedTrack=trackRepository.existsById(1);
        Assert.assertNotSame(true,deletedTrack);
    }

    @Test
    public void testUpdateTrackSuccess() {
        trackRepository.save(track);
        Track track1=trackRepository.findById(track.getTrackId()).get();
        track1.setTrackComments("saii saiii");
        trackRepository.save(track1);
        List<Track> list=trackRepository.findAll();
        Assert.assertEquals("saii saiii",list.get(0).getTrackComments());
    }

    @Test                                                                                                  //test for failure of update method
    public void testUpdateTrackFailure() {
        // Track testUser = new Track(1, "aadi", "good album");
        trackRepository.save(track);
        trackRepository.findById(track.getTrackId()).get().setTrackComments("saii saiii");
        List<Track> list=trackRepository.findAll();
        Assert.assertNotSame("saii saii",list.get(0).getTrackComments());
    }
}
