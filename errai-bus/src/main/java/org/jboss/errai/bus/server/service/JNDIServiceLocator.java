/*
 * Copyright 2010 JBoss, a divison Red Hat, Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.errai.bus.server.service;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;

/**
 * @author: Heiko Braun <hbraun@redhat.com>
 * @date: Jul 19, 2010
 */
@Deprecated
public class JNDIServiceLocator implements ServiceLocator<HttpSession> {
  public static final String DEFAULT_JNDI_NAME = "java:/Errai";

  private String jndiName = null;

  public JNDIServiceLocator() {
  }

  public JNDIServiceLocator(String jndiName) {
    this.jndiName = jndiName;
  }

  public ErraiService<HttpSession> locateService() {
    return lookupService(jndiName != null ? jndiName : DEFAULT_JNDI_NAME);
  }

  @SuppressWarnings({"unchecked"})
  private ErraiService<HttpSession> lookupService(String jndiName) {
    InitialContext ctx = null;
    ErraiService<HttpSession> errai = null;

    try {
      ctx = new InitialContext();
      errai = (ErraiService<HttpSession>) ctx.lookup(jndiName);
    }
    catch (NamingException e) {
      if (ctx != null) {
        try {
          // fallback in development mode
          errai = (ErraiService<HttpSession>) ctx.lookup("java:comp/env/Errai");
        }
        catch (NamingException e1) {
        }
      }

      if (null == errai)
        throw new RuntimeException("Failed to lookup Errai service instance from JNDI", e);
    }

    return errai;
  }
}
