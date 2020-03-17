package edu.ub.ubflix;

import edu.ub.ubflix.controller.Controller;

public class AppUBFLIX {

    public static void main(String[] args) {
        Controller c = Controller.getInstance();
        try {
            c.init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
