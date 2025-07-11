package com.project.profile_service.service;

import com.project.profile_service.dto.Event;
import com.project.profile_service.feign.EventService;
import com.project.profile_service.model.FavoriteEvent;
import com.project.profile_service.repo.FavoriteEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FavoriteEventService {
    @Autowired
    private FavoriteEventRepository favoriteEventRepository;


    @Autowired
    private EventService eventservice;

    public List<Event> getUserFavorites(Long userId) {
            System.out.println("going to get list of event ids");
            List<Long> listofeventids = favoriteEventRepository.findByUserId(userId);
            System.out.println("got list of event ids");

            for(Long it : listofeventids){
                System.out.println(it);
            }

            List<Event> eventsList = new ArrayList<>();

            for(Long i : listofeventids){
                Event e = eventservice.getById(i).getBody();
                if(e==null) continue;
                eventsList.add(e);
            }

            for(Event events : eventsList){
                System.out.println(events.getId());
                System.out.println(events.getTitle());
            }

            System.out.println("events size is "+eventsList.size());

            return eventsList;
    }


    @Transactional
    public void addFavorite(Long userId, Long eventId) {
        if (favoriteEventRepository.existsByUserIdAndEventId(userId, eventId)) {
            throw new IllegalStateException("Event already in favorites");
        }

        FavoriteEvent favorite = new FavoriteEvent();
        favorite.setUserId(userId);
        favorite.setEventId(eventId);
        eventservice.inclikes(eventId);
        favoriteEventRepository.save(favorite);
    }


    @Transactional
    public void removeFavorite(Long eventId,Long userId) {
        eventservice.declikes(eventId);
        favoriteEventRepository.deleteByUserIdAndEventId(userId, eventId);
    }

    public boolean isLiked(Long eventId,Long userId) {
        boolean liked=false;
        List<Long> listofeventids = favoriteEventRepository.findByUserId(userId);

        if(listofeventids == null){
            return false;
        }

        for(Long eventids : listofeventids){
                if(eventids==eventId){
                    return true;
                }
        }

        return liked;


    }


    public void removeFavoriteForAll(Long eventId) {
       favoriteEventRepository.deleteByEventId(eventId);
    }

}