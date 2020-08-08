package com.restpg.infrastructure.interpolator;

import lombok.AllArgsConstructor;

import javax.validation.MessageInterpolator;
import java.util.Locale;

@AllArgsConstructor
public class RpgMessageInterpolator implements MessageInterpolator {

  private final MessageInterpolator messageInterpolator;

  @Override
  public String interpolate(String messageTemplate, Context context) {
    return messageInterpolator.interpolate(messageTemplate, context);
  }

  @Override
  public String interpolate(String messageTemplate, Context context, Locale locale) {
    return messageInterpolator.interpolate(messageTemplate, context, locale);
  }
}
