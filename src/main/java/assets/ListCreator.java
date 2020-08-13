package assets;

import entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import services.CategoryService;
import services.ItemService;
import services.ListService;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class ListCreator {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ListService listService;

    @Autowired
    private CategoryService categoryService;

    public TripsList createList(TripsList list, String sex, String Business,
                                       String Hotel, String Family_Friends, String Camping, String Rent,
                                       String Cruise, String Vacation_Home, String Bus, String Plane,
                                       String Car, String Train, String Ship, String Motorcycle, String Bicycle,
                                       String Gym, String Swimming, String Snow_Sports, String Running,
                                       String Bicycling, String Diving, String Hiking, String Tennis,
                                       String Football, String Badminton,String Surfing, String Volleyball, String Basketball,
                                       String Sport_Other, String Beach, String Photograph, String Fancy_Dinner,
                                       String Music_Festival, String Cat, String Dog, String Rodent, String Pet_Other,
                                       String Baby, String Laundry, String Medicine, String Todo,
                                       String Repeat_basics) throws ParseException {
        List<CategoryList> categories=new ArrayList<>();
        Weather weather = list.getWeather();

        categories.add(setClothes(list,sex,Laundry,weather));

        CategoryList hygiene=new CategoryList("Hygiene");
        hygiene.setList(list);
        categoryService.saveCategory(hygiene);
        if(sex.equals("male"))
            includeItems(hygiene,(long)30,1);
        else
            includeItems(hygiene,(long)31,1);
        categories.add(hygiene);

        CategoryList basics=new CategoryList("Basics");
        basics.setList(list);
        categoryService.saveCategory(basics);
        includeItems(basics,(long)39,1);
        categories.add(basics);

        CategoryList accomodation=new CategoryList("Accomodation");
        accomodation.setList(list);
        categoryService.saveCategory(accomodation);
        if(Hotel!=null)
            includeItems(accomodation,(long)24,1);
        if(Family_Friends!=null)
            includeItems(accomodation,(long)25,1);
        if(Camping!=null)
            includeItems(accomodation,(long)26,1);
        if(Rent!=null)
            includeItems(accomodation,(long)27,1);
        if(Cruise!=null)
            includeItems(accomodation,(long)28,1);
        if(Vacation_Home!=null)
            includeItems(accomodation,(long)29,1);
        if(accomodation.getItems()!=null) {
            deleteRepeat(accomodation);
            categories.add(accomodation);
        }
        else
            categoryService.delCategory(accomodation);

        CategoryList transportation=new CategoryList("Transportation");
        transportation.setList(list);
        transportation=categoryService.saveCategory(transportation);
        if(Bus!=null)
            includeItems(transportation,(long)32,1);
        if(Plane!=null)
            includeItems(transportation,(long)33,1);
        if(Car!=null)
            includeItems(transportation,(long)34,1);
        if(Train!=null)
            includeItems(transportation,(long)35,1);
        if(Ship!=null)
            includeItems(transportation,(long)36,1);
        if(Motorcycle!=null)
            includeItems(transportation,(long)37,1);
        if(Bicycle!=null)
            includeItems(transportation,(long)38,1);
        if(transportation.getItems()!=null) {
            deleteRepeat(transportation);
            categories.add(transportation);
        }
        else
            categoryService.delCategory(transportation);

        CategoryList sport=new CategoryList("Sport");
        sport.setList(list);
        categoryService.saveCategory(sport);
        if(Gym!=null)
            includeItems(sport,(long)1,1);
        if(Swimming!=null)
            includeItems(sport,(long)2,1);
        if(Snow_Sports!=null)
            includeItems(sport,(long)3,1);
        if(Running!=null)
            includeItems(sport,(long)4,1);
        if(Bicycling!=null)
            includeItems(sport,(long)5,1);
        if(Diving!=null)
            includeItems(sport,(long)6,1);
        if(Diving!=null)
            includeItems(sport,(long)6,1);
        if(Hiking!=null)
            includeItems(sport,(long)7,1);
        if(Tennis!=null)
            includeItems(sport,(long)8,1);
        if(Football!=null)
            includeItems(sport,(long)9,1);
        if(Badminton!=null)
            includeItems(sport,(long)10,1);
        if(Surfing!=null)
            includeItems(sport,(long)57,1);
        if(Volleyball!=null)
            includeItems(sport,(long)11,1);
        if(Basketball!=null)
            includeItems(sport,(long)12,1);
        if(Sport_Other!=null)
            includeItems(sport,(long)18,1);
        if(sport.getItems()!=null) {
            deleteRepeat(sport);
            categories.add(sport);
        }
        else
            categoryService.delCategory(sport);

        CategoryList tourismActivities=new CategoryList("Tourism Activities");
        tourismActivities.setList(list);
        categoryService.saveCategory(tourismActivities);
        if(Beach!=null)
            includeItems(tourismActivities,(long)19,1);
        if(Photograph!=null)
            includeItems(tourismActivities,(long)20,1);
        if(Fancy_Dinner!=null)
        {
            if (sex.equals("male"))
                includeItems(tourismActivities, (long)21,1);
            else
                includeItems(tourismActivities, (long)58,1);
        }
        if(Music_Festival!=null)
            includeItems(tourismActivities,(long)22,1);
        if(tourismActivities.getItems()!=null) {
            deleteRepeat(tourismActivities);
            categories.add(tourismActivities);
        }
        else
            categoryService.delCategory(tourismActivities);


        CategoryList specialWeather=new CategoryList("Weather");
        specialWeather.setList(list);
        categoryService.saveCategory(specialWeather);
        if(weather.rainy)
            includeItems(specialWeather,(long)56,1);
        if(weather.sunny)
            includeItems(specialWeather,(long)55,1);
        if(specialWeather.getItems()!=null)
            categories.add(specialWeather);
        else
            categoryService.delCategory(specialWeather);

        if(Baby!=null) {
            CategoryList baby=new CategoryList("Baby");
            baby.setList(list);
            categoryService.saveCategory(baby);
            includeItems(baby,(long)23,1);
            categories.add(baby);
        }

        CategoryList pet=new CategoryList("Pet");
        pet.setList(list);
        categoryService.saveCategory(pet);
        if(Cat!=null)
            includeItems(pet,(long)13,1);
        if(Dog!=null)
            includeItems(pet,(long)14,1);
        if(Rodent!=null)
            includeItems(pet,(long)16,1);
        if(Pet_Other!=null)
            includeItems(pet,(long)17,1);
        if(pet.getItems()!=null) {
            categories.add(pet);
        }
        else
            categoryService.delCategory(pet);

        if(Medicine!=null) {
            CategoryList medicine=new CategoryList("Medicine");
            medicine.setList(list);
            categoryService.saveCategory(medicine);
            includeItems(medicine,(long)54,1);
            categories.add(medicine);
        }


        if(Business!=null) {
            CategoryList work=new CategoryList("Work");
            work.setList(list);
            categoryService.saveCategory(work);
            includeItems(work,(long)40,1);
            categories.add(work);
        }


        if(Todo!=null) {
            CategoryList toDo=new CategoryList("To do");
            toDo.setList(list);
            categoryService.saveCategory(toDo);
            includeItems(toDo,(long)41,1);
            categories.add(toDo);
        }


        if(Repeat_basics==null) {
            CategoryList newCategory=new CategoryList("Items");
            newCategory.setList(list);
            categoryService.saveCategory(newCategory);
            List<ItemInList> items=new ArrayList<>();
            for (CategoryList category : categories) {
                for (ItemInList item : category.getItems()) {
                    ItemInList newItem=new ItemInList();
                    newItem.setCategory(newCategory);
                    newItem.setName(item.getName());
                    newItem.setAmount(item.getAmount());
                    itemService.saveItemInList(newItem);
                    items.add(newItem);
                }
                categoryService.delCategory(category);
            }
            newCategory.setItems(new ArrayList<>(items));
            categoryService.saveCategory(newCategory);
            deleteRepeat(newCategory);
            categories.clear();
            categories.add(newCategory);
        }

        list.setCategories(categories);
        return list;
    };

    private void includeItems(CategoryList category,Long subCategoryId,double amontCoef){
        List<ItemInList> items=itemService.findItemsInSubcategory(subCategoryId);
        for (ItemInList item:items) {
            item.setCategory(category);
            item.setAmount((int) (item.getAmount()*amontCoef));
            itemService.saveItemInList(item);
            category.addItem(item);
        }
    }

    private CategoryList setClothes(TripsList list,String sex, String Laundry,Weather weather) throws ParseException {
        String[] listInfo = list.getName().split(" ");
        SimpleDateFormat ft = new SimpleDateFormat("MM/dd/yyyy");
        Calendar dateBegin = Calendar.getInstance();
        Calendar dateEnd = Calendar.getInstance();
        dateBegin.setTime(ft.parse(listInfo[0]));
        dateEnd.setTime(ft.parse(listInfo[4]));
        long milliseconds = dateEnd.getTimeInMillis() - dateBegin.getTimeInMillis();
        int duration = (int) (milliseconds / (24 * 60 * 60 * 1000));

        CategoryList clothes = new CategoryList("Clothes");
        clothes.setList(list);
        clothes=categoryService.saveCategory(clothes);
        double amountCoeff=1;
        if(Laundry==null)
            amountCoeff=duration/5.+1;
        if (sex.equals("male"))
            includeItems(clothes,(long)53,amountCoeff);//male basics
        else
            includeItems(clothes,(long)52,amountCoeff);//female basics

        List<ItemInList> items=new ArrayList<>();
            if (weather.tempGroupOne) {
                if (sex.equals("male"))
                    items = itemService.findItemsInSubcategory((long)43);
                else
                    items = itemService.findItemsInSubcategory((long)42);

                for (ItemInList item : items) {
                    item.setCategory(clothes);
                    if(Laundry==null)
                        if(item.getName().equals("T-shirt")||item.getName().equals("Dress"))
                        item.setAmount((item.getAmount()/3)*duration+1);
                    itemService.saveItemInList(item);
                    clothes.addItem(item);
                }
            }

            if (weather.tempGroupTwo) {
                if (sex.equals("male"))
                    items = itemService.findItemsInSubcategory((long)45);
                 else
                    items = itemService.findItemsInSubcategory((long)44);

                 for (ItemInList item : items) {
                    item.setCategory(clothes);
                    if(Laundry==null&&item.getName().equals("Shirt"))
                            item.setAmount((item.getAmount()*duration)/12+1);
                    itemService.saveItemInList(item);
                    clothes.addItem(item);
                }
            }

            if (weather.tempGroupThree) {
                if (sex.equals("male"))
                    items = itemService.findItemsInSubcategory((long)47);
                else
                    items = itemService.findItemsInSubcategory((long)46);

                for (ItemInList item : items) {
                    item.setCategory(clothes);
                    if(Laundry==null&&(item.getName().equals("Shirt")||item.getName().equals("Pantyhose")))
                        item.setAmount((item.getAmount()*duration)/15+1);
                    itemService.saveItemInList(item);
                    clothes.addItem(item);
                }
            }

            if (weather.tempGroupFour) {
                if (sex.equals("male"))
                    items = itemService.findItemsInSubcategory((long)49);
                else
                    items = itemService.findItemsInSubcategory((long)48);

                for (ItemInList item : items) {
                    item.setCategory(clothes);
                    if(Laundry==null&&(item.getName().equals("Shirt")||item.getName().equals("Pantyhose")))
                        item.setAmount((item.getAmount()*duration)/20+1);
                    itemService.saveItemInList(item);
                    clothes.addItem(item);
                }
            }

            if (weather.tempGroupFive) {
                if (sex.equals("male"))
                    items = itemService.findItemsInSubcategory((long)50);
                else
                    items = itemService.findItemsInSubcategory((long)51);

                for (ItemInList item : items) {
                    item.setCategory(clothes);
                    if(Laundry==null&&(item.getName().equals("Sweater")||item.getName().equals("Pantyhose")||item.getName().equals("Shirt")))
                        item.setAmount((item.getAmount()*duration)/20+1);
                    itemService.saveItemInList(item);
                    clothes.addItem(item);
                }
            }

            deleteRepeat(clothes);

        return clothes;
    }

    private void deleteRepeat(CategoryList category) {
        Comparator<ItemInList> comparator=new Comparator<ItemInList>(){
            @Override
            public int compare(ItemInList i1, ItemInList i2) {
                return i1.getName().compareTo(i2.getName());
            }
        };
        category.getItems().sort(comparator);
        for(int i=0;i<category.getItems().size()-1;++i) {
            int j=i+1;
            ItemInList item1=category.getItems().get(i);
            ItemInList item2=category.getItems().get(j);
            int max=item1.getAmount();
            while(item1.getName().equals(item2.getName())) {
                max=Math.max(max,item2.getAmount());
                itemService.delInList(item2);
                category.getItems().remove(item2);
                if(j==category.getItems().size())
                    break;
                item2=category.getItems().get(j);
            }
            item1.setAmount(max);
        }
        categoryService.saveCategory(category);
    }

    public TripsList joinLists(TripsList first,TripsList second) {
        first.setName(first.getName()+" and "+second.getName());
        List<CategoryList> firstListCategoeries=first.getCategories();
        for(CategoryList category:second.getCategories()) {
            if(firstListCategoeries.contains(category)) {
                int index=firstListCategoeries.indexOf(category);
                CategoryList secondCategory=firstListCategoeries.get(index);
                for(ItemInList item:category.getItems()) {
                    secondCategory.addItem(item);
                }
             deleteRepeat(secondCategory);
             categoryService.saveCategory(secondCategory);
            }
            else {
                category.setList(first);
                categoryService.saveCategory(category);
            }
        }
        listService.saveList(first);
        return first;
    }

}
