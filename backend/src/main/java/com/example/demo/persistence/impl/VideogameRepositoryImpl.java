package com.example.demo.persistence.impl;

import com.example.demo.domain.Videogame;
import com.example.demo.persistence.repositories.VideogameRepository;
import org.springframework.stereotype.Repository;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class VideogameRepositoryImpl implements VideogameRepository {
    List<Videogame> videogames = new ArrayList<>();
    DateFormat df = new SimpleDateFormat("MM/dd/yyyy");

    public VideogameRepositoryImpl() {
        /*String str1 = "2005-01-02";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime dateTime1 = LocalDateTime.parse(str1, formatter);

        String str2 = "2023-08-09 00:00";
        LocalDateTime dateTime2 = LocalDateTime.parse(str2, formatter);*/
        videogames.add(Videogame.builder()
                        .id(1)
                        .name("name1")
                        .price(10)
                        .description("Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur?")
                        //.releaseDate(dateTime1)
                        .featured(true)
                        .image("image1")
                .build());
        videogames.add(Videogame.builder()
                .id(2)
                .name("name2")
                .price(10)
                .description("Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur?")
                //.releaseDate(dateTime2)
                .featured(true)
                .image("image2")
                .build());
        videogames.add(Videogame.builder()
                .id(3)
                .name("name3")
                .price(10)
                .description("Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur?")
                //.releaseDate(dateTime2)
                .featured(true)
                .image("image3")
                .build());
        videogames.add(Videogame.builder()
                .id(4)
                .name("name4")
                .price(10)
                .description("Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur?")
                //.releaseDate(dateTime2)
                .featured(true)
                .image("image4")
                .build());
    }
    @Override
    public List<Videogame> GetVideogames() {
        return videogames;
    }

    @Override
    public List<Videogame> GetFeaturedVideogames() {
        List<Videogame> featuredVideogames = new ArrayList<>();
        for(Videogame videogame : videogames) {
            if(videogame.getFeatured()) {
                featuredVideogames.add(videogame);
            }
        }
        return featuredVideogames;
    }

    @Override
    public List<Videogame> GetUpcomingVideogames() {
        List<Videogame> upcomingVideogames = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();
        for(Videogame videogame : videogames) {
            //if(videogame.getReleaseDate().isAfter(now)) {
                upcomingVideogames.add(videogame);
            //}
        }
        return upcomingVideogames;
    }

    @Override
    public Videogame GetVideogame(int index) {
        Videogame returnVideogame = Videogame.builder().build();
        for(Videogame v : videogames) {
            if(v.getId() == index) {
                returnVideogame = v;
            }
        }
        return returnVideogame;
    }

    @Override
    public Videogame AddVideogame(Videogame addition) {
        videogames.add(addition);
        Videogame returnVideogame = Videogame.builder().build();
        for(Videogame v : videogames) {
            if(v.getId() == addition.getId()) {
                returnVideogame = v;
            }
        }
        return returnVideogame;
    }

    @Override
    public Videogame UpdateVideogame(Videogame addition) {
        Videogame returnVideogame = Videogame.builder().build();
        for(Videogame v : videogames) {
            if(v.getId() == addition.getId()) {
                returnVideogame = v;
            }
        }
        int index = videogames.indexOf(returnVideogame);
        videogames.remove(index);
        videogames.add(index, addition);
        return returnVideogame;
    }

    @Override
    public Videogame DeleteVideogame(int index) {
        Videogame returnVideogame = Videogame.builder().build();
        for(Videogame v : videogames) {
            if(v.getId() == index) {
                returnVideogame = v;
            }
        }
        videogames.remove(returnVideogame);
        return returnVideogame;
    }
}
