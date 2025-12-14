/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package patternsproject;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 *
 * @author 2478812
 */
public final class I18nManager {

    private static final I18nManager INSTANCE = new I18nManager();

    private final ObjectProperty<Locale> locale =
            new SimpleObjectProperty<>(Locale.ENGLISH);

    private I18nManager() {}

    public static I18nManager get() {
        return INSTANCE;
    }

    public ObjectProperty<Locale> localeProperty() {
        return locale;
    }

    public Locale getLocale() {
        return locale.get();
    }

    public void setLocale(Locale newLocale) {
        if (newLocale != null) {
            locale.set(newLocale);
        }
    }

    public ResourceBundle getBundle() {
        return ResourceBundle.getBundle("i18n.Messages", getLocale());
    }

    public StringBinding bind(String key, Object... args) {
        return Bindings.createStringBinding(() -> {
            String pattern = getBundle().getString(key);
            return (args == null || args.length == 0)
                    ? pattern
                    : MessageFormat.format(pattern, args);
        }, locale);
    }
}
    
