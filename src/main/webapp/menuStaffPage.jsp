<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="controller.*" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="model.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.lang.Float" %>

<!DOCTYPE html>
<html style="font-size: 16px;" lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta charset="utf-8">
    <title>Menu Staff Page</title>
    
    <link rel="stylesheet" href="./css/menuTest.css" media="screen">
    <link rel="stylesheet" href="./css/menu.css" media="screen">
    <meta name="theme-color" content="#478ac9">

 <%
    Staff staff = (Staff) session.getAttribute("staff");
      MenuManagement me = staff.GetMenuManagement();
      List<Menu> menus = me.GetAllMenus();
      List<MenuCategory> categories = me.GetAllCategories();
      int categorySize = categories.size();
      if(categorySize == 0) {
        categorySize = 1;
        // For layout
      }
   %>

<script>
  function togglePopup() {
    <% if(categories.size() != 0) {
      out.print("document.getElementById('popup').classList.toggle('active')");
    }
      else {
        out.print("alert('Add Category first to sort')");
      }%>
    }

  
  
  function toggleMenuEditPopup(oldName, price) {
    document.getElementById('EditMenuPopup').classList.toggle('active')
    document.getElementById('oldName').innerText = oldName
    document.getElementById("editMenuName").value = oldName
    document.getElementById("editMenuPrice").value = price
  }
  
  function toggleCatPopup() {
    <% if(categories.size() > 5) {
      out.print("alert('category size limit : 5')");
    }
    else {
      out.print("document.getElementById('CatPopup').classList.toggle('active')");
    } %>
  }

  function toggleCatEditPopup() {
    <% if(categories.size() == 0) {
      out.print("alert('Add at least one category')");
    }
    else {
      if(categories.size() == 1) {
        out.print("document.getElementById('removeButton').disabled = true;");
      }
      out.print("document.getElementById('CatEditPopup').classList.toggle('active');");
      out.print("document.getElementById('remove').style.display='none'");
    } %>
  }

</script>

  </head>

<script>
function addMenu() {
    let name = document.getElementById("newMenuName").value;
    let price = parseFloat(document.getElementById("newMenuPrice").value);
    let category = document.getElementById("addCategory").value;

    if(name.length == 0 || isNaN(price) || price < 1) {
      alert("Please enter valid name and price");
      return;
    }

    window.location.replace('menuAddPage.jsp?name='+name+'&price='+price+'&category=' + category+'&type=add');
    
}

function editMenu() {
    let name = document.getElementById("editMenuName").value;
    let oldName = document.getElementById('oldName').innerText;
    let price = parseFloat(document.getElementById("editMenuPrice").value);
    let category = document.getElementById("editCategory").value;

    if(name.length == 0 || isNaN(price) || price < 1) {
      alert("Please enter valid name and price");
      return;
    }

    window.location.replace("menuAddPage.jsp?name="+name+"&oldName="+oldName+"&price="+price+"&category=" + category+"&type=edit");
}

function addCat() {
  let name = document.getElementById("newCatName").value;

    if(name.length == 0) {
      alert("Please enter valid name");
      return;
    }

    window.location.replace("menuAddPage.jsp?name="+name);
}

function EditCat() {
  if(document.getElementById('removeButton').checked) {
    DeleteCat();
    return;
  }

  let oldName = document.getElementById("EditCategory").value;
  let newName = document.getElementById("newName").value;
  if(newName.length == 0) {
    alert("Please enter valid name");
    return;
  }

  window.location.replace("menuEditPage.jsp?oldName="+oldName + "&newName=" + newName);
}

function DeleteCat() {
 let oldName = document.getElementById("EditCategory").value;

 window.location.replace("menuEditPage.jsp?oldName="+ oldName);
}

function OpenEdit() {
  document.getElementById('remove').style.display='none';
  document.getElementById('edit').style.display='block';
  
}

function OpenRemove() {
  document.getElementById('remove').style.display='block';
  document.getElementById('edit').style.display='none';
}
</script>

  <body class="u-body u-overlap u-xl-mode" data-lang="en">
    <!-- Add menu popup -->
    <div class="popup" id="popup">
      <div class="overlay"> </div>
      <div class="content">
      <div class="close-btn" onclick="togglePopup()">&times;</div>
      <h1> Add menu </h1>
      <p>&nbsp;</p>
      <p style="margin-bottom:120px;"><label for="name">Menu name: </label> <input id="newMenuName" name="newMenuName" type="text" /><br/><br/>
      <label for="price">Price:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </label> <input id="newMenuPrice" name="newMenuPrice" type="number" /><br /><br />
      <label for="category">Choose a category:</label>
      <select name="category" id="addCategory">
      <%
        for(MenuCategory category : categories) {
          out.print("<option value='"+category.GetName()+"'>"+category.GetName() + "</option>");
        }
       %>
      </select>
      </p>
      <a onclick="addMenu()" style= "background-color: red; margin:0 auto;
    display:block; " class="u-align-center u-border-none u-btn u-btn-round u-button-style u-hover-palette-2-light-2 u-palette-2-base u-radius-50 u-btn-1">Add</a>
      </div>
    </div>

    <!-- Edit menu popup -->
    <div class="popup" id="EditMenuPopup">
      <div class="overlay"> </div>
      <div class="content">
      <div class="close-btn" onclick="toggleMenuEditPopup()">&times;</div>
      <h1> Edit menu </h1>
      <p>&nbsp;</p>
      <p id="oldName" style="display:none"></p>
      <p style="margin-bottom:120px;"><label for="name">Menu name: </label> <input id="editMenuName" name="editMenuName" type="text" /><br/><br/>
      <label for="price">Price:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </label> <input id="editMenuPrice" name="editMenuPrice" type="number" /><br /><br />
      <label for="category">Choose a category:</label>
      <select name="category" id="editCategory">
      <%
        for(MenuCategory category : categories) {
          out.print("<option value='"+category.GetName()+"'>"+category.GetName() + "</option>");
        }
       %>
      </select>
      </p>
      <a onclick="editMenu()" style= "background-color: red; margin:0 auto;
    display:block; " class="u-align-center u-border-none u-btn u-btn-round u-button-style u-hover-palette-2-light-2 u-palette-2-base u-radius-50 u-btn-1">Edit</a>
      </div>
    </div>

    <!-- Add Category Popup -->
    <div class="CatPopup" id="CatPopup">
      <div class="overlay"> </div>
      <div style ="height:300px" class="content">
      <div class="close-btn" onclick="toggleCatPopup()">&times;</div>
      <h1> Add Category </h1>
      <p>&nbsp;</p>
      <p><label for="name">Category name: </label> <input id="newCatName" name="newCatName" type="text" /><br/><br/>
      <a onclick="addCat()" style= "background-color: red; margin:0 auto;
    display:block; " class="u-align-center u-border-none u-btn u-btn-round u-button-style u-hover-palette-2-light-2 u-palette-2-base u-radius-50 u-btn-1">Add</a>
      </div>
    </div>

    <!-- Edit Cat Popup -->
    <div class="CatEditPopup" id="CatEditPopup">
      <div class="overlay"> </div>
      <div style ="height:500px" class="content">
      <div class="close-btn" onclick="toggleCatEditPopup()">&times;</div>
      <h1> Edit Category </h1>
      <p>&nbsp;</p>

      <p style="margin-bottom:50px">
      <input type="radio" id="editButton" name="editOrRemove" value="edit" checked onclick="OpenEdit()">
     <label for="edit">Edit</label><br>
     <input type="radio" id="removeButton" name="editOrRemove" value="remove" onclick="OpenRemove()">
     <label for="remove">Remove</label><br>

      <label for="category">Choose a category:</label>
      <select name="category" id="EditCategory">
      <%
        for(MenuCategory category : categories) {
          out.print("<option value='"+category.GetName()+"'>"+category.GetName() + "</option>");
        }
       %>
      </select>

      <div class="edit" id = "edit" style="margin-bottom:100px">
        <label for="newName">New Name:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </label> <input id="newName" name="newName" type="text" /><br /><br />
      </div>

      <div class="remove" id ="remove" style="margin-bottom:80px">
      <p> One category will be automatically created for unassigned items.
          Try again after move all items to other categories to completely remove categories</p>
      </div>
      <a onclick="EditCat()" style= "background-color: red; margin:0 auto;
    display:block; " class="u-align-center u-border-none u-btn u-btn-round u-button-style u-hover-palette-2-light-2 u-palette-2-base u-radius-50 u-btn-1">Edit/Remove</a>
      </div>
    </div>


    <section class="u-clearfix u-section-1" id="sec-664f">
      <div class="u-clearfix u-sheet u-sheet-1">
        <img class="u-expanded-width u-image u-image-round u-radius-25 u-image-1" src="images/testRestoImage.png" alt="test image" data-image-width="960" data-image-height="1280">
        <p class="u-custom-font u-font-montserrat u-text u-text-default u-text-1">Test Delicous Central</p>
         <!-- Buttons -->
        <div class="u-layout-grid u-list u-list-1">
          <div class="u-repeater u-repeater-1">
            <div class="u-container-style u-list-item u-repeater-item">
              <div class="u-container-layout u-similar-container u-container-layout-1">
                <a onclick="togglePopup()" style= "background-color: blue" class="u-align-center u-border-none u-btn u-btn-round u-button-style u-hover-palette-2-light-2 u-palette-2-base u-radius-50 u-btn-1">Add Menu</a>
              </div>
            </div>
            <div class="u-align-left u-container-style u-list-item u-repeater-item">
              <div class="u-container-layout u-similar-container u-container-layout-2">
              </div>
            </div>

            <div class="u-container-style u-list-item u-repeater-item">
              <div class="u-container-layout u-similar-container u-container-layout-3">
                 <a onclick="toggleCatPopup()" style= "background-color: blue" class="u-align-center u-border-none u-btn u-btn-round u-button-style u-hover-palette-2-light-2 u-palette-2-base u-radius-50 u-btn-1">add category</a>
              </div>
            </div>
            <div class="u-container-style u-list-item u-repeater-item">
              <div class="u-container-layout u-similar-container u-container-layout-4">
                <a onclick="toggleCatEditPopup()" class="u-align-center u-border-none u-btn u-btn-round u-button-style u-hover-palette-2-light-2 u-palette-2-base u-radius-50 u-btn-4">edit category<span style="font-size: 0.625rem;"></span>
                </a>
              </div>
            </div>
          </div>
        </div>

         <!-- Category table -->
        <div class="u-table u-table-responsive u-table-1">
          <table class="u-table-entity">
            <colgroup>
              <col width="100%">
            </colgroup>
            <tbody class="u-table-body">
            <% 
            if(categories.size() == 0) {
               out.print("<tr style='height: 46px;'><td class='u-align-left u-border-1 u-border-grey-dark-1 u-table-cell u-table-cell-1'>Empty</td></tr>");
            }
              for(MenuCategory category : categories) {
                 out.print("<tr style='height: 46px;'><td class='u-align-left u-border-1 u-border-grey-dark-1 u-table-cell u-table-cell-1'>" + category.GetName() + "</td></tr>");
            }
            %>
            </tbody>
          </table>
        </div>

         <!-- Menus -->
         <%
          if(menus.size() == 0) {
            out.print("<h3 style='margin: "+ -55 * categorySize+ "px auto 0 285px;' class='u-text u-text-default'>Empty</h3>");
          }
          else {
            int value = -55 * categorySize;
            for(MenuCategory category : categories) {
                out.print("<h3 style='margin: "+ value + "px auto 0 285px;' class='u-text u-text-default'>"+ category.GetName() +"</h3>");
                out.print("<div class='u-list u-list-2'> <div class='u-repeater u-repeater-2'>");
                value = 0;

                for(Menu menu : menus) {
                  //out.print("dd" + menu.GetCategory().GetName() + "ss" + category.GetName());
                  if(menu.GetCategory().GetName().equals(category.GetName())) {
                    out.print("<div class='u-container-style u-list-item u-repeater-item'><div class='u-container-layout u-similar-container u-container-layout-5'><p class='u-custom-font u-font-montserrat u-text u-text-default u-text-3'>");
                    out.print(menu.GetName() + "</p>");
                    out.print("<p>$" + menu.GetPrice() + "</p>");
                    out.print("<p><a onclick='toggleMenuEditPopup(\""+menu.GetName()+"\",\""+menu.GetPrice()+"\")' style='background-color: blue' class='u-align-center u-border-none u-btn u-btn-round u-button-style u-hover-palette-2-light-2 u-palette-2-base u-radius-50 u-btn-1'>Edit Menu</a></p></div></div>");
                  }
                }
                out.print("</div></div>");
            }
          }
        %>

      </div>
    </section>

</body>

</html>