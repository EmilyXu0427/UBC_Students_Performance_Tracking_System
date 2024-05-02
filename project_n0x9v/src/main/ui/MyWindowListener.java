package ui;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/* The listener interface for receiving window events. MyWindowListener class prompts dialogue to ask user if they
 * want to save the data when user exits the application; if the user choose Yes, data will be saved to Json file;
 * otherwise, data would not be saved;
 */
public class MyWindowListener implements WindowListener {


    // EFFECTS: Invoked the first time a window is made visible.
    @Override
    public void windowOpened(WindowEvent e) {

    }

    // EFFECTS: Invoked when the user attempts to close the window from the window's system menu.
    @Override
    public void windowClosing(WindowEvent e) {

    }

    // EFFECTS: Invoked when a window has been closed as the result of calling dispose on the window.
    @Override
    public void windowClosed(WindowEvent e) {

    }

    // EFFECTS: Invoked when a window is changed from a normal to a minimized state.
    @Override
    public void windowIconified(WindowEvent e) {

    }

    // EFFECTS: Invoked when a window is changed from a minimized to a normal state.
    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    // EFFECTS: Invoked when the Window is set to be the active Window.
    @Override
    public void windowActivated(WindowEvent e) {

    }

    // EFFECTS: Invoked when a Window is no longer the active Window.
    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
