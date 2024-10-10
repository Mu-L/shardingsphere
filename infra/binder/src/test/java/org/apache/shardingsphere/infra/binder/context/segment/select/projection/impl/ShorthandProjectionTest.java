/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.shardingsphere.infra.binder.context.segment.select.projection.impl;

import org.apache.shardingsphere.infra.binder.context.segment.select.projection.Projection;
import org.apache.shardingsphere.sql.parser.statement.core.value.identifier.IdentifierValue;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

class ShorthandProjectionTest {
    
    @Test
    void assertGetOwner() {
        assertTrue(new ShorthandProjection(new IdentifierValue("owner"), Collections.emptyList()).getOwner().isPresent());
    }
    
    @Test
    void assertGetColumnNameWithOwner() {
        assertThat(new ShorthandProjection(new IdentifierValue("owner"), Collections.emptyList()).getColumnName(), is("owner.*"));
    }
    
    @Test
    void assertGetColumnNameWithoutOwner() {
        assertThat(new ShorthandProjection(null, Collections.emptyList()).getColumnName(), is("*"));
    }
    
    @Test
    void assertGetColumnProjections() {
        assertThat(new ShorthandProjection(new IdentifierValue("owner"), Arrays.asList(mock(Projection.class), mock(ColumnProjection.class))).getColumnProjections().size(), is(1));
    }
    
    @Test
    void assertGetColumnLabel() {
        assertTrue(new ShorthandProjection(new IdentifierValue("owner"), Collections.emptyList()).getColumnLabel().contains("*"));
    }
    
    @Test
    void assertGetExpressionWithOwner() {
        assertThat(new ShorthandProjection(new IdentifierValue("owner"), Collections.emptyList()).getExpression(), is("owner.*"));
    }
    
    @Test
    void assertGetExpressionWithoutOwner() {
        assertThat(new ShorthandProjection(null, Collections.emptyList()).getExpression(), is("*"));
    }
    
    @Test
    void assertGetAlias() {
        assertFalse(new ShorthandProjection(new IdentifierValue("owner"), Collections.emptyList()).getAlias().isPresent());
    }
}
