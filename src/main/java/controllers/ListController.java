package controllers;

import assets.PdfCreator;
import entities.CategoryList;
import entities.ItemInList;
import entities.TripsList;
import entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import services.CategoryService;
import services.ItemService;
import services.ListService;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Controller
public class ListController {

    @Autowired
    private ListService listService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private CategoryService categoryService;

    private TripsList list;

    private User user;

    @GetMapping("/list")
    public String loadList(String id,Model model) {
        list=listService.findListById(Long.parseLong(id));
        user=list.getUser();
        model.addAttribute("list",list);
        model.addAttribute("user",user);
        return "list";
    }

    @PostMapping("/list")
    public String editList(String pdf,String categories,String categoryName,String items,String names,String amounts,String checked,HttpServletRequest request, HttpServletResponse response,Model model) throws IOException {

        if(items!=null) {
            String[] itemsArray = items.split(",");
            List<String> checkedList = new ArrayList<>();
            if (checked != null)
                checkedList = new ArrayList<>(Arrays.asList(checked.split(",")));

            String[] categoryIds=categories.split(",");
            String[] categoryNames=categoryName.split(",");
            for(int i=0;i<categoryIds.length;++i) {
                CategoryList category;
                try {
                   category = categoryService.getById(Long.parseLong(categoryIds[i]));
                }
                catch(Exception e) {
                    category=new CategoryList();
                    category.setList(list);
                    categoryService.saveCategory(category);
                    Long categoryId=category.getCategoryListId();
                    for(int j=0;j<itemsArray.length;++j) {
                        if(itemsArray[j].contains(categoryIds[i])) {
                            String[]itemId=itemsArray[j].split("newItem");
                            if(checkedList.contains(itemsArray[j])) {
                                checkedList.remove(itemsArray[j]);
                                checkedList.add(categoryId+"newItem"+itemId[1]);
                            }
                            itemsArray[j]=categoryId+"newItem"+itemId[1];
                        }
                    }
                }
                category.setCategoryListName(categoryNames[i]);
            }

            String[] amountsArray = amounts.split(",");
            String[] itemNames=names.split(",");

            for (int i = 0; i < itemsArray.length; ++i) {
                ItemInList item;
                try {
                    item = itemService.findItemById(Long.parseLong(itemsArray[i]));
                }
                catch(Exception e) {
                    item = new ItemInList();
                    Long categoryId=Long.parseLong(itemsArray[i].split("newItem")[0]);
                    CategoryList category=categoryService.getById(categoryId);
                    item.setCategory(category);
                    category.addItem(item);
                    categoryService.saveCategory(category);
                }

                int amount = Integer.decode(amountsArray[i]);
                String itemName=itemNames[i];
                if (amount == 0)
                    try {
                        itemService.delInList(item);
                    }
                    catch(Exception e){}
                else {
                    item.setAmount(amount);
                    item.setName(itemName);
                    if (checkedList.contains(itemsArray[i]))
                        item.setChecked(true);
                    else
                        item.setChecked(false);
                    itemService.saveItemInList(item);
                }
            }
        }
        list=listService.saveList(list);
        for (CategoryList category: list.getCategories()) {
            if(category.getItems()==null||category.getItems().isEmpty())
                categoryService.delCategory(category);
        }

        if(pdf==null)
            return "redirect:/userLists?userId="+ user.getId();
        return downloadPdf(request,response,model);
    }

    @PostMapping("/delList")
    public String delList() {
        listService.delList(list);
        return "redirect:/userLists?userId="+ user.getId();
    }


    public String downloadPdf(HttpServletRequest request, HttpServletResponse response,Model model) throws IOException {

        String EXTERNAL_FILE_PATH = "C:/applicationFiles/";
        String fileName=EXTERNAL_FILE_PATH + "list"+list.getListId()+".pdf";

        PdfCreator pdfCreator=new PdfCreator(list,fileName);
        pdfCreator.createListPdf();

        File file = new File(fileName);
        if (file.exists()) {
            String mimeType = "application/pdf";
            response.setContentType(mimeType);
            response.setHeader("Content-Disposition", String.format("attachment; filename=\"" + file.getName() + "\""));
            response.setContentLength((int) file.length());
            InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
            FileCopyUtils.copy(inputStream, response.getOutputStream());
        }
        file.delete();
        model.addAttribute("list",list);
        model.addAttribute("user",user);
        return "list";
    }
}
