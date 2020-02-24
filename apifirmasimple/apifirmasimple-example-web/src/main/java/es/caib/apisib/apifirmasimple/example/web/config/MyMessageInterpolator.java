package es.caib.apisib.apifirmasimple.example.web.config;

import java.util.Locale;

import javax.validation.MessageInterpolator;

/**
 *
 * @author anadal
 *
 */
public class MyMessageInterpolator implements MessageInterpolator {

    public static final ThreadLocal<Locale> languageThread = new ThreadLocal<Locale>();

    private final MessageInterpolator defaultInterpolator;

    public MyMessageInterpolator(MessageInterpolator interpolator) {
        this.defaultInterpolator = interpolator;
    }

    @Override
    public String interpolate(String messageTemplate, Context context) {
        return defaultInterpolator.interpolate(messageTemplate, context);
    }

    @Override
    public String interpolate(String messageTemplate, Context context, Locale locale) {

        languageThread.set(locale);

        return defaultInterpolator.interpolate(messageTemplate, context, locale);

    }
}