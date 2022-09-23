package controller.rms;

import controller.Validator;
import dao.ResDBManager;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.RCategory;
import model.Restaurant;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "controller/rms/ModifyRestaurantServlet", value = "/ModifyRestaurantServlet")
public class ModifyRestaurantServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        Validator validator = new Validator();
        ResDBManager manager = (ResDBManager) session.getAttribute("ResDBManager");
        clear(session); // Used to clear error messages.

        String resID = request.getParameter("id");
        String image = request.getParameter("image");
        // No validation for resName for creative names though unlikely
        String resName = request.getParameter("resName");
        String strNum = request.getParameter("strNum");
        String strName = request.getParameter("strName");
        String postcode = request.getParameter("postcode");
        String state = request.getParameter("state");
        String suburb = request.getParameter("suburb");
        String country = request.getParameter("country");
        // Won't validate activation because jsp will only have a on/off button to collect 1 or 0 only
        String activation = request.getParameter("activate");
        String abn = request.getParameter("abn");
        String acctName = request.getParameter("acctName");
        String bsb = request.getParameter("bsb");
        String acctNum = request.getParameter("acctNum");
        boolean hasError = false;

        if (!validator.validateName(strName)) {
            session.setAttribute("rStrName_Error", "Street Name must start with a capital letter");
            hasError = true;
        }

        if (!validator.validatePostCode(postcode)) {
            session.setAttribute("rPostCode_Error", "Postcode must be 4 digits long");
            hasError = true;
        }

        if (!validator.validateName(state)) {
            session.setAttribute("rState_Error", "State must start with a capital letter");
            hasError = true;
        }

        if (!validator.validateName(suburb)) {
            session.setAttribute("rSuburb_Error", "Suburb must start with a capital letter");
            hasError = true;
        }

        if (!validator.validateName(country)) {
            session.setAttribute("rCountry_Error", "Country must start with a capital letter");
            hasError = true;
        }

        if (!validator.validateABN(abn)) {
            session.setAttribute("rABN_Error", "ABN must be 11 digits long");
            hasError = true;
        }

        if (!validator.validateAcctNum(acctNum)) {
            session.setAttribute("rAcctNum_Error", "Account Number must be 6 digits long");
            hasError = true;
        }

        if (!validator.validateName(acctName)) {
            session.setAttribute("rAcctName_Error", "Account Name must start with a capital letter");
            hasError = true;
        }

        if (!validator.validateBSB(bsb)) {
            session.setAttribute("rBSB_Error", "BSB must be 6 digits long");
            hasError = true;
        }

        if (!validator.validateStrNum(strNum)) {
            session.setAttribute("rStrNum_Error", "Street Number must be digits only or empty");
            hasError = true;
        }

        if (hasError) {
            request.getRequestDispatcher("modifyRes.jsp").include(request, response);
        }

        // If ID is empty means we want to create a new record
        // ID is a hidden field which will have an ID if we are modifying an existing record
        if (resID.equals("")) {
            try {
                manager.createRestaurant(new Restaurant(image, resName, Integer.parseInt(strNum), strName,
                        Integer.parseInt(postcode), state, suburb, country,
                        (Integer.parseInt(activation) == 1), Long.parseLong(abn),
                        acctName, Integer.parseInt(bsb), Integer.parseInt(acctNum)));
                session.setAttribute("rModifySuccess", "Successfully Added Restaurant!");
                request.getRequestDispatcher("modifyRes.jsp").include(request, response);
            } catch (Exception e) {
                Logger.getLogger(ModifyCategoryServlet.class.getName()).log(Level.SEVERE, null, e);
                request.getRequestDispatcher("modifyRes.jsp").include(request, response);
            }
        }
        else {
            try {
                manager.updateRestaurant(new Restaurant(Integer.parseInt(resID), image,
                        resName, Integer.parseInt(strNum), strName,
                        Integer.parseInt(postcode), state, suburb, country,
                        (Integer.parseInt(activation) == 1), Long.parseLong(abn),
                        acctName, Integer.parseInt(bsb), Integer.parseInt(acctNum)));
                session.setAttribute("rModifySuccess", "Successfully Edited Restaurant!");
                request.getRequestDispatcher("find-res?id=" + resID).include(request, response);
            } catch (Exception e) {
                Logger.getLogger(ModifyCategoryServlet.class.getName()).log(Level.SEVERE, null, e);
                request.getRequestDispatcher("modifyRes.jsp").include(request, response);
            }
        }

    }

    private void clear(HttpSession session) {
        session.setAttribute("rStrName_Error", "");
        session.setAttribute("rModifySuccess", "");
        session.setAttribute("rPostCode_Error", "");
        session.setAttribute("rState_Error", "");
        session.setAttribute("rSuburb_Error", "");
        session.setAttribute("rCountry_Error", "");
        session.setAttribute("rABN_Error", "");
        session.setAttribute("rAcctNum_Error", "");
        session.setAttribute("rAcctName_Error", "");
        session.setAttribute("rBSB_Error", "");
        session.setAttribute("rStrNum_Error", "");
    }
}
