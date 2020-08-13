function delItem(button){
    var container=button.parentElement;
    var amount=container.getElementsByClassName("amount")[0];
    amount.min="0";
    amount.value="0";
    container.style="display:none";
}

function checkChange(checkBox){
    console.log("checkChange");
    var label=checkBox.parentElement.getElementsByTagName("label")[0];
    var nextSibling=checkBox.nextSibling.nextSibling;
    if(checkBox.checked) {
        nextSibling.checked=true;
        label.style="text-decoration:line-through";
    }
    else
    {
        nextSibling.checked=false;
        label.style="text-decoration:none";
    }
}
function addItem(button)
{
    var categoryId=(button.previousSibling.previousSibling).value;
    var div=document.createElement("div");
    var container=document.getElementById(categoryId);

    var newItemId=categoryId+"newItem"+Math.floor(Math.random() * 100000000);
    var newItemName=document.getElementById("newItemName"+categoryId).value;
    var newItemAmount=document.getElementById("amount"+categoryId).value;
    document.getElementById("newItemName"+categoryId).value="";
    document.getElementById("amount"+categoryId).value="1";

    var items=document.createElement("input");
    items.name="items";
    items.value=newItemId;
    items.style="display:none";
    div.appendChild(items);


    var check=document.createElement("input");
    check.type="checkbox";
    check.onchange=function () {checkChange(this);};
    check.className="float-left";
    div.appendChild(check);


    var names=document.createElement("input");
    names.name="names";
    names.value=newItemName;
    names.style="display:none";
    names.className="float-left w-271";
    div.appendChild(names);

    var checked=document.createElement("input");
    checked.type="checkbox";
    checked.name="checked";
    checked.value=newItemId;
    checked.style="display:none";
    div.appendChild(checked);

    var label=document.createElement("label");
    label.innerHTML=newItemName;
    label.className="float-left";
    div.appendChild(label);

    var amount=document.createElement("input");
    amount.name="amounts";
    amount.className="amount m-r-4";
    amount.min="1";
    amount.value=newItemAmount;
    amount.type="number";
    div.appendChild(amount);

    var edit=document.createElement("input");
    edit.type="button";
    edit.value="Edit";
    edit.className="m-r-4";
    edit.onclick=function () {editName(this);};
    div.appendChild(edit);

    var del=document.createElement("input");
    del.type="button";
    del.value="Delete";
    del.onclick=function () {delItem(this);};
    div.appendChild(del);

    div.className="itemContainer m-b-3";
    container.appendChild(div);
}

document.getElementById ("addCategoryButton").addEventListener ("click", addCategory, false);
function addCategory()
{
    var container=document.getElementById("categories");
    var newCategoryId="newCategory"+Math.floor(Math.random() * 100000000);

    var categoryHeader=document.createElement("div");
    categoryHeader.className="categoryName";

    var categoryName=document.createElement("input");
    categoryName.name="categoryName";
    categoryName.value=document.getElementById("newCategoryName").value;
    categoryName.className="m-r-4";
    categoryName.style="display:none";
    categoryHeader.appendChild(categoryName);

    var categories=document.createElement("input");
    categories.name="categories";
    categories.value=newCategoryId;
    categories.style="display:none";
    categoryHeader.appendChild(categories);

    var label=document.createElement("label");
    label.innerHTML=categoryName.value;
    label.className="m-r-4";
    categoryHeader.appendChild(label);

    var editButton=document.createElement("input");
    editButton.type="button";
    editButton.value="Edit";
    editButton.onclick=function () { editName(this)};
    categoryHeader.appendChild(editButton);

    container.appendChild(categoryHeader);

    var div=document.createElement("div");
    div.id=newCategoryId;
    div.className="m-t-4 m-b-4";
    container.appendChild(div);

    var newItemName=document.createElement("input");
    newItemName.id="newItemName"+newCategoryId;
    newItemName.placeholder="New item`s name";
    newItemName.className="newItemName m-r-4";
    container.appendChild(newItemName);

    var categoryListId=document.createElement("input");
    categoryListId.value=newCategoryId;
    categoryListId.style="display:none";
    container.appendChild(categoryListId);

    var amount=document.createElement("input");
    amount.id="amount"+newCategoryId;
    amount.type="number";
    amount.min="1";
    amount.className="amount m-r-4";
    amount.value="1";
    container.appendChild(amount);

    var addButton=document.createElement("input");
    addButton.type="button";
    addButton.value="Add";
    addButton.onclick=function () {addItem(this);};
    container.appendChild(addButton);

    document.getElementById("newCategoryName").value="";
}
function editName(button)
{
    var container=button.parentElement;
    var label=container.querySelector("label");
    var nameInput=label.previousSibling.previousSibling;
    if(button.value=="Save")
    {
        nameInput.style="display:none";
        label.innerHTML=nameInput.value;
        label.style="display:inline";
        button.value="Edit";
    }
    else
    {
        label.style="display:none";
        nameInput.style="display:inline-block";
        button.value="Save";
    }

}
function pdfOn() {
    document.getElementById("pdf").checked=true;
}

function pdfOff() {
    document.getElementById("pdf").checked=false;
}