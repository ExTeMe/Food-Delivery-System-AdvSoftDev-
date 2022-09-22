package dao;

import model.*;
import java.sql.*;
import java.util.ArrayList;

public class ResDBManager {

    private Statement st;

    public ResDBManager(Connection conn) throws SQLException {
        st = conn.createStatement();
    }

    public ArrayList<Restaurant> findRestaurants(String name) throws SQLException, Exception {
        String fetch = "SELECT * FROM db.restaurant WHERE Restaurant_Name LIKE '%" + name + "%'";
        // Return list of restaurants matching a certain name pattern
        return getRestaurants(fetch);
    }

    // If name is empty, this method will be called by the controller.
    public ArrayList<Restaurant> seeAllRestaurants() throws SQLException, Exception {
        String fetch = "SELECT * FROM db.restaurant";
        // Return all restaurants and their details
        return getRestaurants(fetch);
    }

    private ArrayList<Restaurant> getRestaurants(String fetch) throws SQLException, Exception {
        ResultSet rs = st.executeQuery(fetch);

        ArrayList<Restaurant> resList = new ArrayList<>();

        while (rs.next()) {
            String resName = rs.getString("Restaurant_Name");

            // Search for the categories below that restaurant
            ArrayList<RCategory> categories = getRestaurantCategories(resName);

            int resID = Integer.parseInt(rs.getString("Restaurant_ID"));
            String imageRef = rs.getString("Image_Reference");
            int streetNo = Integer.parseInt(rs.getString("Street_Number"));
            String streetName = rs.getString("Street_Name");
            int postcode = Integer.parseInt(rs.getString("Postcode"));
            String state = rs.getString("State");
            String suburb = rs.getString("Suburb");
            String country = rs.getString("Country");
            Boolean activated = (Integer.parseInt(rs.getString("Activated")) == 1);
            long abn = Long.parseLong(rs.getString("ABN"));
            String accountName = rs.getString("Account_Name");
            int bsb = Integer.parseInt(rs.getString("BSB"));
            long accountNumber = Long.parseLong(rs.getString("Account_Number"));
            resList.add(new Restaurant(resID, imageRef, resName, categories, streetNo, streetName,
                    postcode, state, suburb, country, activated, abn, accountName, bsb, accountNumber));
        }

        // If it is empty, it is empty, we have the controller deal with that
        return resList;
    }

    private ArrayList<RCategory> getRestaurantCategories(String name) throws SQLException, Exception {
        String fetch = "SELECT RC.RCategory_ID, RCategory_Name " +
                "FROM db.restaurant_rcategory RR INNER JOIN db.restaurant R " +
                "INNER JOIN db.rcategory RC WHERE Restaurant_Name = '" + name + "' AND " +
                "RR.Restaurant_ID = R.Restaurant_ID AND RR.RCategory_ID = RC.RCategory_ID";

        // Not using LIKE %% because this function is only used for searching categories under a specific restaurant

        ResultSet rs = st.executeQuery(fetch);

        ArrayList<RCategory> catList = new ArrayList<>();

        while (rs.next()) {

            int id = Integer.parseInt(rs.getString("RC.RCategory_ID"));
            String catName = rs.getString("RCategory_Name");

            catList.add(new RCategory(id, catName));
        }

        return catList;
    }

    // Force delete to match a specific name so accidental deletes won't occur
    // Assuming there won't be similarly named restaurants
    public void deleteRestaurant(String name) throws SQLException, Exception {
        String fetch = "SELECT Restaurant_ID FROM db.restaurant WHERE Restaurant_Name = '" + name + "'";

        ResultSet rs = st.executeQuery(fetch);

        if (rs.next()) {
            int id = Integer.parseInt(rs.getString("Restaurant_ID"));

            st.executeUpdate("DELETE FROM db.restaurant_rcategory WHERE Restaurant_ID = " + id);
            // Deletes any update request for the restaurant as well
            st.executeUpdate("DELETE FROM db.request WHERE Restaurant_ID = " + id);
            st.executeUpdate("DELETE FROM db.coupon_r WHERE Restaurant_ID = " + id);
            st.executeUpdate("DELETE FROM db.menu_item WHERE Restaurant_ID = " + id);
            st.executeUpdate("DELETE FROM db.staff WHERE Restaurant_ID = " + id);
            st.executeUpdate("DELETE FROM db.restaurant WHERE Restaurant_ID = " + id);
        }
        else {
            throw new Exception("Restaurant Name Not Found! Please Verify That The Name Is Spelt Correctly");
        }

    }

    // Force activation to require specific name, don't want to activate the wrong restaurant
    public void activateRestaurant(String name) throws SQLException, Exception {
        String fetch = "SELECT Restaurant_ID FROM db.restaurant WHERE Restaurant_Name = '" + name + "'";

        ResultSet rs = st.executeQuery(fetch);

        if (rs.next()) {
            if (Integer.parseInt(rs.getString("Activated")) == 1) {
                throw new Exception("Restaurant Already Activated!");
            }
            else {
                st.executeUpdate("UPDATE db.restaurant SET Activated = 1 WHERE Restaurant_Name = '" + name + "'");
            }
        }
        else {
            throw new Exception("Restaurant Name Not Found! Please Verify That The Name Is Spelt Correctly");
        }
    }

    // Force deactivation to require specific name, don't want to deactivate the wrong restaurant
    public void deactivateRestaurant(String name) throws SQLException, Exception {
        String fetch = "SELECT Restaurant_ID FROM db.restaurant WHERE Restaurant_Name = '" + name + "'";

        ResultSet rs = st.executeQuery(fetch);

        if (rs.next()) {
            if (Integer.parseInt(rs.getString("Activated")) == 0) {
                throw new Exception("Restaurant Already Deactivated!");
            }
            else {
                st.executeUpdate("UPDATE db.restaurant SET Activated = 0 WHERE Restaurant_Name = '" + name + "'");
            }
        }
        else {
            throw new Exception("Restaurant Name Not Found! Please Verify That The Name Is Spelt Correctly");
        }
    }

    public ArrayList<RCategory> seeAllRCategories() throws SQLException, Exception {
        String fetch = "SELECT * FROM db.rcategory";

        return getRCategories(fetch);
    }

    public ArrayList<RCategory> findRCategories(String name) throws SQLException, Exception {
        String fetch = "SELECT * FROM db.rcategory WHERE RCategory_Name LIKE '%" + name + "%'";

        return getRCategories(fetch);
    }

    public ArrayList<RCategory> getRCategories(String fetch) throws SQLException, Exception {
        ResultSet rs = st.executeQuery(fetch);

        ArrayList<RCategory> catList = new ArrayList<>();

        while (rs.next()) {
            String rCatName = rs.getString("RCategory_Name");

            ArrayList<Restaurant> restaurants = getCategoryRestaurants(rCatName);

            int id = Integer.parseInt(rs.getString("RCategory_ID"));
            String desc = rs.getString("RCategory_Description");

            catList.add(new RCategory(id, rCatName, desc, restaurants));
        }

        return catList;
    }

    private ArrayList<Restaurant> getCategoryRestaurants(String name) throws SQLException, Exception {
        String fetch = "SELECT R.Restaurant_ID, R.Restaurant_Name " +
                "FROM db.restaurant_rcategory RR INNER JOIN db.restaurant R " +
                "INNER JOIN db.rcategory RC WHERE RCategory_Name = '" + name + "' AND " +
                "RR.Restaurant_ID = R.Restaurant_ID AND RR.RCategory_ID = RC.RCategory_ID";

        ResultSet rs = st.executeQuery(fetch);

        ArrayList<Restaurant> resList = new ArrayList<>();

        while (rs.next()) {
            int id = Integer.parseInt(rs.getString("R.Restaurant_ID"));
            String resName = rs.getString("Restaurant_Name");
            resList.add(new Restaurant(id, resName));
        }

        return resList;
    }

    // Have controller pass a RCategory object so that the parameter is clean
    public void createCategory(RCategory category) throws SQLException, Exception {
        String fetch = "INSERT INTO db.rcategory VALUES (DEFAULT, '" + category.getrCatName() + "', '"
                + category.getrCatDescription() + "'";

        st.executeUpdate(fetch);
    }

    public void updateCategory(RCategory category, String oldCatName) throws SQLException, Exception {
        String fetch = "UPDATE db.rcategory SET RCategory_Name = '" + category.getrCatName() + "', " +
                "RCategory_Description = '" + category.getrCatDescription() + "' WHERE RCategory_Name = '"
                + oldCatName + "'";

        st.executeUpdate(fetch);
    }

    // Delete by exact name to prevent accidental deletion
    public void deleteCategory(String name) throws SQLException, Exception {

        ResultSet rs = st.executeQuery("SELECT RCategory_ID FROM rcategory WHERE RCategory_Name = '" + name + "'");

        if (rs.next()) {
            int id = Integer.parseInt(rs.getString("RCategory_ID"));

            st.executeUpdate("DELETE FROM restaurant_rcategory WHERE RCategory_ID = " + id);
            st.executeUpdate("DELETE FROM rcategory WHERE RCategory_ID = " + id);
        }
        else {
            throw new Exception("Category Name Not Found! Please Verify That The Name Is Spelt Correctly");
        }
    }



}
