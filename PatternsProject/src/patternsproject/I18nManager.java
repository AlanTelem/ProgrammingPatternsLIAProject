/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package patternsproject;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author 2478812
 */
public class I18nManager {
    private static I18nManager instance;
    private static Locale currentLocale;
    private static ResourceBundle messages;

    public I18nManager() {
        currentLocale = Locale.getDefault();
        messages = ResourceBundle.getBundle("Messages", currentLocale);
    }
    
    public static synchronized I18nManager getInstance(){
        if (instance == null){
            instance = new I18nManager();
        }
        return instance;
    }
    
    public void setLocale(Locale newLocale){
        this.currentLocale = newLocale;
        this.messages = ResourceBundle.getBundle("Messages", newLocale);
    }

    public Locale getCurrentLocale() {
        return currentLocale;
    }

    public String getMessage(String Key) {
        return messages.getString(Key);
    }
}
