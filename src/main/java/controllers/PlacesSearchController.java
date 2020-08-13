package controllers;
import entities.TripsList;
import entities.User;
import entities.Weather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import services.ListService;
import services.UserService;
import assets.WeatherParser;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class PlacesSearchController {

    private User user;

    @Autowired
    private ListService listService;

    @Autowired
    private UserService userService;

    @GetMapping("/hello")
    public String greeting(Principal principal, Model model) {

        this.user=null;
        if(principal !=null) {
            Object container = new Object();
            User user=null;
            try {
                Field field = principal.getClass().getDeclaredField("userAuthentication");
                field.setAccessible(true);
                Object userAuth = field.get(principal);
                field = userAuth.getClass().getSuperclass().getDeclaredField("details");
                field.setAccessible(true);
                container = field.get(userAuth);
            }
            catch (NoSuchFieldException | IllegalAccessException e) {
                try {
                    Field field = principal.getClass().getDeclaredField("principal");
                    field.setAccessible(true);
                    user = (User)field.get(principal);
                }
                catch (NoSuchFieldException | IllegalAccessException ex) { }
            }
            try {
                Method method= container.getClass().getMethod("get", Object.class);
                String id=method.invoke(container,"id").toString();
                user=(User) userService.loadUserByUsername(id);
            }
            catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException |NullPointerException e) {
                try {
                    Method method= container.getClass().getMethod("get", Object.class);
                    String email= (String) method.invoke(container,"email");
                    user=(User) userService.loadUserByUsername(email);
                }
                catch ( IllegalAccessException | NoSuchMethodException | InvocationTargetException ex) { }
            }
            model.addAttribute("user", user);
            this.user=user;
        }
        return "hello";
    }

    @PostMapping("/hello")
    public String loadWeather(String city1_coordinates,String city2_coordinates, String city3_coordinates,String city4_coordinates,String city5_coordinates,
                              String daterange1, String daterange2,String daterange3,String daterange4,String daterange5,
                              Model model){
        List<TripsList> lists=new ArrayList<>();
        try {
            if (!city1_coordinates.equals("")) {
                lists.add(setWeather(city1_coordinates,daterange1));
            }
            if (!city2_coordinates.equals("")) {
                lists.add(setWeather(city2_coordinates,daterange2));
            }
            if (!city3_coordinates.equals("")) {
                lists.add(setWeather(city3_coordinates,daterange3));
            }
            if (!city4_coordinates.equals("")) {
                lists.add(setWeather(city4_coordinates,daterange4));
            }
            if (!city5_coordinates.equals("")) {
                lists.add(setWeather(city5_coordinates,daterange5));
            }
        }
        catch (Exception e){
            model.addAttribute("user",user);
            model.addAttribute("message","Something went wrong with your destinations or dates choose."+"\n"+"Please,try again.");
            return "hello";
        }
        String redirect="redirect:/details?";
        if(user!=null)
            redirect+="userId="+user.getId()+"&";
        int i=0;
        while(true) {
            TripsList list=lists.get(i);
            list=listService.saveList(list);
            redirect += "listId" + (i+1) + "=" + list.getListId();
            if(++i<lists.size())
                redirect+="&";
            else
                break;
        }
        return redirect;
    }

    private TripsList setWeather(String coordinates,String daterange) throws Exception{
        String[] coord=coordinates.split("/");
        String[] dates = daterange.split("-");

        TripsList newList=new TripsList();
        newList.setName(dates[0]+" - "+dates[1]+" "+coord[2]);

        SimpleDateFormat ft = new SimpleDateFormat("MM/dd/yyyy");
        Calendar dateBegin = Calendar.getInstance();
        Calendar dateEnd = Calendar.getInstance();
        dateBegin.setTime(ft.parse(dates[0]));
        dateEnd.setTime(ft.parse(dates[1]));
        Weather weather=WeatherParser.parseWeather(coord[0],coord[1],dateBegin,dateEnd);
        newList=listService.setWeather(newList,weather);

        return newList;
    }
}
