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

package org.jboss.errai.common.client.protocols;

public class SerializationParts {
  public static final String MARSHALLED_TYPES = "__MarshalledTypes";
  public static final String ENCODED_TYPE = "__EncodedType";
  public static final String OBJECT_ID = "__ObjectID";
  public static final String INSTANTIATE_ONLY = "__InstantiateOnly";
  public static final String NUMERIC_VALUE = "__NumVal";
  public static final String VALUE = "Value";

  public static final String EMBEDDED_JSON = "${$JSON$}$::";

  public static final String ENUM_STRING_VALUE = "EnumStringValue";

  private SerializationParts() {
  }
}
