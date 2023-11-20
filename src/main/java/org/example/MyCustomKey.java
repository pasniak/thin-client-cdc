package org.example;

/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.io.Serializable;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

public class MyCustomKey implements Serializable {
    /** */
    private static final AtomicLong ID_GEN = new AtomicLong();

    public String firstName;
    public String lastName;

    public MyCustomKey() {
        // No-op.
    }

    public MyCustomKey(String firstName, String lastName) {
        // Generate unique ID for this person.

        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }

    @Override
    public boolean equals(Object o) {
        // self check
        if (this == o)
            return true;
        // null check
        if (o == null)
            return false;
        // type check and cast
        if (getClass() != o.getClass())
            return false;
        MyCustomKey person = (MyCustomKey) o;
        // field comparison
        return Objects.equals(firstName, person.firstName)
                && Objects.equals(lastName, person.lastName);
    }


    @Override public String toString() {
        return "MyCustomKey ["+
                ", lastName=" + lastName +
                ", firstName=" + firstName + ']';
    }
}