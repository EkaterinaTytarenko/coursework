package controllers;

import assets.ListCreator;
import entities.TripsList;
import entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import services.ListService;
import services.UserService;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class DetailsController {

    @Autowired
    private ListService listService;

    @Autowired
    private ListCreator listCreator;

    @Autowired
    private UserService userService;

    private List<IndexedTripsList> lists;
    private User user;

    class IndexedTripsList{
        private int index;
        private TripsList list;

        public IndexedTripsList(int index, TripsList list) {
            this.index = index;
            this.list = list;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public TripsList getList() {
            return list;
        }

        public void setList(TripsList list) {
            this.list = list;
        }
    }

    @GetMapping("/details")
    public String loadLists(String userId, String listId1, String listId2, String listId3, String listId4, String listId5, Model model){
        user=null;
        if (userId!=null)
            user=(User)userService.loadUserByUserId(Long.parseLong(userId));
        lists =new ArrayList<>();
        lists.add(new IndexedTripsList(0,listService.findListById(Long.parseLong(listId1))));
        if(listId2!=null)
            model.addAttribute("multiple",true);
        if(listId2!=null)
            lists.add(new IndexedTripsList(1,listService.findListById(Long.parseLong(listId2))));
        if(listId3!=null)
            lists.add(new IndexedTripsList(2,listService.findListById(Long.parseLong(listId3))));
        if(listId4!=null)
            lists.add(new IndexedTripsList(3,listService.findListById(Long.parseLong(listId4))));
        if(listId5!=null)
            lists.add(new IndexedTripsList(4,listService.findListById(Long.parseLong(listId5))));
        model.addAttribute("lists",lists);
        return "details";
    }

    @PostMapping("/details")
    public String formList(String sex,String separate,
                           String Business0, String Business1,String Business2,String Business3,String Business4,
                           String Hotel0,String Hotel1,String Hotel2,String Hotel3,String Hotel4,
                           String Family_Friends0,String Family_Friends1,String Family_Friends2,String Family_Friends3,String Family_Friends4,
                           String Camping0,String Camping1,String Camping2,String Camping3,String Camping4,
                           String Rent0,String Rent1,String Rent2,String Rent3,String Rent4,
                           String Cruise0,String Cruise1,String Cruise2,String Cruise3,String Cruise4,
                           String Vacation_Home0,String Vacation_Home1,String Vacation_Home2,String Vacation_Home3,String Vacation_Home4,
                           String Bus0,String Bus1,String Bus2,String Bus3,String Bus4,
                           String Plane0,String Plane1,String Plane2,String Plane3,String Plane4,
                           String Car0,String Car1,String Car2,String Car3,String Car4,
                           String Train0,String Train1,String Train2,String Train3,String Train4,
                           String Ship0,String Ship1,String Ship2,String Ship3,String Ship4,
                           String Motorcycle0,String Motorcycle1,String Motorcycle2,String Motorcycle3,String Motorcycle4,
                           String Bicycle0,String Bicycle1,String Bicycle2,String Bicycle3,String Bicycle4,
                           String Gym0,String Gym1,String Gym2,String Gym3,String Gym4,
                           String Swimming0,String Swimming1,String Swimming2,String Swimming3,String Swimming4,
                           String Snow_Sports0,String Snow_Sports1,String Snow_Sports2,String Snow_Sports3,String Snow_Sports4,
                           String Running0,String Running1,String Running2,String Running3,String Running4,
                           String Bicycling0,String Bicycling1,String Bicycling2,String Bicycling3,String Bicycling4,
                           String Diving0,String Diving1,String Diving2,String Diving3,String Diving4,
                           String Hiking0,String Hiking1,String Hiking2,String Hiking3,String Hiking4,
                           String Tennis0,String Tennis1,String Tennis2,String Tennis3,String Tennis4,
                           String Football0,String Football1,String Football2,String Football3,String Football4,
                           String Badminton0,String Badminton1,String Badminton2,String Badminton3,String Badminton4,
                           String Surfing0,String Surfing1,String Surfing2,String Surfing3,String Surfing4,
                           String Volleyball0,String Volleyball1,String Volleyball2,String Volleyball3,String Volleyball4,
                           String Basketball0,String Basketball1,String Basketball2,String Basketball3,String Basketball4,
                           String Sport_Other0,String Sport_Other1,String Sport_Other2,String Sport_Other3,String Sport_Other4,
                           String Beach0,String Beach1,String Beach2,String Beach3,String Beach4,
                           String Photograph0,String Photograph1,String Photograph2,String Photograph3,String Photograph4,
                           String Fancy_Dinner0,String Fancy_Dinner1,String Fancy_Dinner2,String Fancy_Dinner3,String Fancy_Dinner4,
                           String Music_Festival0,String Music_Festival1,String Music_Festival2,String Music_Festival3,String Music_Festival4,
                           String Cat0,String Cat1,String Cat2,String Cat3,String Cat4,
                           String Dog0,String Dog1,String Dog2,String Dog3,String Dog4,
                           String Rodent0,String Rodent1,String Rodent2,String Rodent3,String Rodent4,
                           String Pet_Other0,String Pet_Other1,String Pet_Other2,String Pet_Other3,String Pet_Other4,
                           String Baby0,String Baby1,String Baby2,String Baby3,String Baby4,
                           String Laundry0,String Laundry1,String Laundry2,String Laundry3,String Laundry4,
                           String Medicine0,String Medicine1,String Medicine2,String Medicine3,String Medicine4,
                           String Todo0,String Todo1,String Todo2,String Todo3,String Todo4,
                           String Repeat_basics0,String Repeat_basics1,String Repeat_basics2,String Repeat_basics3,String Repeat_basics4,
                           Model model) throws ParseException {

        String redirect="redirect:/userLists?";
        List<TripsList> result=new ArrayList<>();
        if(user!=null)
            redirect+="userId="+user.getId()+"&";
        for(int i=0;i<lists.size();++i) {
            TripsList list=lists.get(i).getList();
            switch(i){
                case 0:list=listCreator.createList(list,sex,Business0,
                        Hotel0,Family_Friends0,Camping0,Rent0,Cruise0,Vacation_Home0,Bus0,Plane0,
                        Car0,Train0,Ship0,Motorcycle0,Bicycle0, Gym0,Swimming0,Snow_Sports0,Running0,
                        Bicycling0,Diving0,Hiking0,Tennis0, Football0,Badminton0,Surfing0,Volleyball0,
                        Basketball0,Sport_Other0,Beach0,Photograph0,Fancy_Dinner0,Music_Festival0,
                        Cat0,Dog0,Rodent0,Pet_Other0,Baby0,Laundry0,Medicine0,Todo0,Repeat_basics0);break;
                case 1:list=listCreator.createList(list,sex,Business1,
                        Hotel1,Family_Friends1,Camping1,Rent1,Cruise1,Vacation_Home1,Bus1,Plane1,
                        Car1,Train1,Ship1,Motorcycle1,Bicycle1, Gym1,Swimming1,Snow_Sports1,Running1,
                        Bicycling1,Diving1,Hiking1,Tennis1, Football1,Badminton1,Surfing1,Volleyball1,
                        Basketball1,Sport_Other1,Beach1,Photograph1,Fancy_Dinner1,Music_Festival1,
                        Cat1,Dog1,Rodent1,Pet_Other1,Baby1,Laundry1,Medicine1,Todo1,Repeat_basics1);break;
                case 2:list=listCreator.createList(list,sex,Business2,
                        Hotel2,Family_Friends2,Camping2,Rent2,Cruise2,Vacation_Home2,Bus2,Plane2,
                        Car2,Train2,Ship2,Motorcycle2,Bicycle2, Gym2,Swimming2,Snow_Sports2,Running2,
                        Bicycling2,Diving2,Hiking2,Tennis2, Football2,Badminton2,Surfing2,Volleyball2,
                        Basketball2,Sport_Other2,Beach2,Photograph2,Fancy_Dinner2,Music_Festival2,
                        Cat2,Dog2,Rodent2,Pet_Other2,Baby2,Laundry2,Medicine2,Todo2,Repeat_basics2);break;
                case 3:list=listCreator.createList(list,sex,Business3,
                        Hotel3,Family_Friends3,Camping3,Rent3,Cruise3,Vacation_Home3,Bus3,Plane3,
                        Car3,Train3,Ship3,Motorcycle3,Bicycle3, Gym3,Swimming3,Snow_Sports3,Running3,
                        Bicycling3,Diving3,Hiking3,Tennis3, Football3,Badminton3,Surfing3,Volleyball3,
                        Basketball3,Sport_Other3,Beach3,Photograph3,Fancy_Dinner3,Music_Festival3,
                        Cat3,Dog3,Rodent3,Pet_Other3,Baby3,Laundry3,Medicine3,Todo3,Repeat_basics3);break;
                case 4:list=listCreator.createList(list,sex,Business4,
                        Hotel4,Family_Friends4,Camping4,Rent4,Cruise4,Vacation_Home4,Bus4,Plane4,
                        Car4,Train4,Ship4,Motorcycle4,Bicycle4, Gym4,Swimming4,Snow_Sports4,Running4,
                        Bicycling4,Diving4,Hiking4,Tennis4, Football4,Badminton4,Surfing4,Volleyball4,
                        Basketball4,Sport_Other4,Beach4,Photograph4,Fancy_Dinner4,Music_Festival4,
                        Cat4,Dog4,Rodent4,Pet_Other4,Baby4,Laundry4,Medicine4,Todo4,Repeat_basics4);break;

            };
            if(result.isEmpty()||separate.equals("true"))
                result.add(list);
            else
                result.set(0,listCreator.joinLists(list,result.get(0)));
        }
        for(int i=0;i<result.size();++i) {
            TripsList list=result.get(i);
            if (user != null)
                list.setUser(user);
            list = listService.saveList(list);
            redirect += "listId" + (i + 1) + "=" + list.getListId();
            if (i + 1 < result.size())
                redirect += "&";
        }
        return redirect;
    }
}
