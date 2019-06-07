package com.stackroute.domain;

import org.springframework.data.annotation.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

/*import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;*/
//Calls Jpa Repository
@Document
//Manages all getter and setter methods
@Data
//create a constructor with No Arguements
@NoArgsConstructor
//create a constructor with All Arguements
@AllArgsConstructor
//@Table(name="track")//Reference for mysql table
public class Track {

    //@Column(name="trackid")
    @Id//Primary Key
    int trackId;

   // @Column(name="trackname")
    String trackName;

   // @Column(name="trackcomments")
    String trackComments;

}
