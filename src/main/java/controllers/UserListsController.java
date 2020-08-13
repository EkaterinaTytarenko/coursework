package controllers;

import entities.TripsList;
import entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import services.ListService;
import services.UserService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserListsController {

    @Autowired
    private UserService userService;

    @Autowired
    private ListService listService;

        @GetMapping("/userLists")
    public String loadLists(String userId, String listId1, String listId2, String listId3, String listId4, String listId5, Model model) {
            List<TripsList> lists=new ArrayList<>();;
            if (userId!=null) {
                User user = (User) userService.loadUserByUserId(Long.parseLong(userId));
                model.addAttribute("user",user);
                for (TripsList list:user.getLists())
                    lists.add(list);
            }
            else {
                lists.add(listService.findListById(Long.parseLong(listId1)));
                if (listId2 != null)
                    lists.add(listService.findListById(Long.parseLong(listId2)));
                if (listId3 != null)
                    lists.add(listService.findListById(Long.parseLong(listId3)));
                if (listId4 != null)
                    lists.add(listService.findListById(Long.parseLong(listId4)));
                if (listId5 != null)
                    lists.add(listService.findListById(Long.parseLong(listId5)));
            }
        if(lists.size()>0)
            model.addAttribute("notEmpty",true);
        model.addAttribute("lists",lists);
        return "userLists";
    }

}
