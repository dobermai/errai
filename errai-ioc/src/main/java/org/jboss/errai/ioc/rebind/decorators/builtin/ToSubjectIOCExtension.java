/*
 * Copyright 2011 JBoss, a divison Red Hat, Inc
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

package org.jboss.errai.ioc.rebind.decorators.builtin;

import org.jboss.errai.bus.client.api.annotations.ToSubject;
import org.jboss.errai.ioc.client.api.CodeDecorator;
import org.jboss.errai.ioc.rebind.ioc.IOCDecoratorExtension;
import org.jboss.errai.ioc.rebind.ioc.InjectableInstance;
import org.jboss.errai.codegen.framework.Statement;
import org.jboss.errai.codegen.framework.meta.MetaField;
import org.jboss.errai.codegen.framework.util.Stmt;

/**
 * @author Mike Brock .
 */
@CodeDecorator
public class ToSubjectIOCExtension extends IOCDecoratorExtension<ToSubject> {
  public ToSubjectIOCExtension(Class<ToSubject> decoratesWith) {
    super(decoratesWith);
  }

  @Override
  public Statement generateDecorator(InjectableInstance<ToSubject> injectableInstance) {
    final MetaField field = injectableInstance.getField();
    final ToSubject context = field.getAnnotation(ToSubject.class);

    return Stmt.nestedCall(injectableInstance.getValueStatement())
            .invoke("setToSubject", context.value());
  }
}
