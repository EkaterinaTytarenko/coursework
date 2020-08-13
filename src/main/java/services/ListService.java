package services;

import entities.TripsList;
import entities.Weather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repos.ListRepo;
import repos.WeatherRepo;

@Service
public class ListService {

    @Autowired
    private ListRepo listRepo;

    @Autowired
    private WeatherRepo weatherRepo;

    public TripsList saveList(TripsList list) {
        TripsList savedList = listRepo.saveAndFlush(list);

        return savedList;

    }

    public TripsList setWeather(TripsList list, Weather weather) {
        Weather savedWeather=weatherRepo.saveAndFlush(weather);
        list.setWeather(savedWeather);
        return list;
    }

    public TripsList findListById(Long id){
        return listRepo.findByListId(id);
    }

    public void delList(TripsList list)
    {
        listRepo.delete(list);
    }


}
