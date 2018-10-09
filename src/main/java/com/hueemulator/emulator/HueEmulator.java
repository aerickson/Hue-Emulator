package com.hueemulator.emulator;

import com.hueemulator.gui.View;

public class HueEmulator {

    public HueEmulator(String fileName, int portNumber) {
        Model model = new Model();

        //  Set Up the View (A JFrame, MenuBar and Console).
        View view = new View(portNumber);

        // Bind the Model and View
        Controller controller = new Controller(model,view,fileName);
        view.getMenuBar().setController(controller);
        view.getGraphicsPanel().setController(controller);

        // Add all the Menu Listeners.
        controller.addMenuListeners();

        // Add all the Property Frame Listeners.
        controller.addPropertiesListeners();

        //  Model is needed here to paint Light Bulbs/ Show bulb information.
        view.getGraphicsPanel().setModel(model);
    }


}


