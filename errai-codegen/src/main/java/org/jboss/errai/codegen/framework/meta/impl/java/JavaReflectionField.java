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

package org.jboss.errai.codegen.framework.meta.impl.java;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import org.jboss.errai.codegen.framework.meta.*;
import org.jboss.errai.codegen.framework.util.GenUtil;

public class JavaReflectionField extends MetaField {
  private Field field;

  JavaReflectionField(Field field) {
    this.field = field;
  }

  @Override
  public String getName() {
    return field.getName();
  }

  @Override
  public MetaClass getType() {
    return MetaClassFactory.get(field.getType(), field.getGenericType());
  }

  private Annotation[] _annotationsCache;

  @Override
  public Annotation[] getAnnotations() {
    if (_annotationsCache != null) {
      return _annotationsCache;
    }
    return _annotationsCache = field.getAnnotations();
  }

  @Override
  public final <A extends Annotation> A getAnnotation(Class<A> annotation) {
    for (Annotation a : getAnnotations()) {
      if (a.annotationType().equals(annotation)) return (A) a;
    }
    return null;
  }

  @Override
  public boolean isAnnotationPresent(Class<? extends Annotation> annotation) {
    return getAnnotation(annotation) != null;
  }

  @Override
  public MetaType getGenericType() {
    return JavaReflectionUtil.fromType(field.getGenericType());
  }

  @Override
  public MetaClass getDeclaringClass() {
    return MetaClassFactory.get(field.getDeclaringClass());
  }

  @Override
  public boolean isAbstract() {
    return (field.getModifiers() & Modifier.ABSTRACT) != 0;
  }

  @Override
  public boolean isPublic() {
    return (field.getModifiers() & Modifier.PUBLIC) != 0;
  }

  @Override
  public boolean isPrivate() {
    return (field.getModifiers() & Modifier.PRIVATE) != 0;
  }

  @Override
  public boolean isProtected() {
    return (field.getModifiers() & Modifier.PROTECTED) != 0;
  }

  @Override
  public boolean isFinal() {
    return (field.getModifiers() & Modifier.FINAL) != 0;
  }

  @Override
  public boolean isStatic() {
    return (field.getModifiers() & Modifier.STATIC) != 0;
  }

  @Override
  public boolean isTransient() {
    return (field.getModifiers() & Modifier.TRANSIENT) != 0;
  }

  @Override
  public boolean isVolatile() {
    return (field.getModifiers() & Modifier.VOLATILE) != 0;
  }

  @Override
  public boolean isSynthetic() {
    return field.isSynthetic();
  }

  @Override
  public boolean isSynchronized() {
    return (field.getModifiers() & Modifier.SYNCHRONIZED) != 0;
  }

  @Override
  public boolean equals(Object o) {
    return o instanceof MetaField && GenUtil.equals(this, (MetaField) o);
  }
}
