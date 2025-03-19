package com.esli.gestiondestock.interceptor;

import org.hibernate.EmptyInterceptor;
import org.slf4j.MDC;
import org.springframework.util.StringUtils;

public class Interceptor extends EmptyInterceptor {

  @Override
  public String onPrepareStatement(String sql) {
    if (StringUtils.hasLength(sql) && sql.toLowerCase().startsWith("select")) {
      // select utilisateu0_.
      final String entityName = sql.substring(7, sql.indexOf("."));
      if (StringUtils.hasLength(entityName)
          && !entityName.toLowerCase().contains("roles")){
      }
    }
    return super.onPrepareStatement(sql);
  }
}
