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

package org.jboss.errai.bus.server;

import java.util.List;

import org.jboss.errai.bus.client.tests.support.TestException;
import org.jboss.errai.bus.client.tests.support.TestRPCServiceRemote;
import org.jboss.errai.bus.server.annotations.Service;

/**
 * @author Mike Brock <cbrock@redhat.com>
 * @author Christian Sadilek <csadilek@redhat.com>
 */
@Service
public class TestRPCService implements TestRPCServiceRemote {
  public boolean isGreaterThan(int a, int b) {
    System.out.println("TestRPCService.isGreaterThan(" + a + ", " + b + ")");
    return a > b;
  }

  @Override
  public void exception() throws TestException {
    throw new TestException();
  }

  @Override
  public List<Long> listOfLong(List<Long> list) {
    return list;
  }

  @Override
  public List<Integer> listOfInteger(List<Integer> list) {
    return list;
  }

  @Override
  public List<Float> listOfFloat(List<Float> list) {
    return list;
  }
}
