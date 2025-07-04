package com.project.profile_service.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConductedEventReq {
    //title,category,likes,clubname
    private String title;
    private String category;
    private long likes;
    private String clubName;

    public String  getTitle() {
        return title;
    }
    public String  getCategory() {
        return category;
    }
    public long   getLikes() {
        return likes;
    }
    public String  getClubName() {
        return clubName;
    }

    // public ConductedEvent(String title, String category, long likes, String clubname) {
    //     this.title = title;
    //     this.category = category;
    //     this.likes = likes;
    //     this.clubname = clubname;
    // }
}
 