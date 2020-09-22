/*
 * Copyright 2011 SecondMarket Labs, LLC.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and limitations under the License.
 */

package org.mongeez;

import com.mongodb.DBObject;
import com.mongodb.QueryBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mongeez.TestDatabaseSetup.db;


public class MongeezCommandsTest  {

    @BeforeEach
    protected void setUpBeforeEachTest() {
        db.dropDatabase();
    }

    protected Mongeez create(String path) {
        return TestDatabaseSetup.createMongeez(path);
    }

    @Test
    @Order(1)
    public void testScriptWithCommand() {
        Mongeez mongeez = create("mongeez_with_command_insert.xml");
        mongeez.process();
        assertEquals(db.getCollection("commands").count(), 1);

        DBObject q = new QueryBuilder().put("code").is("CODE1").get();
        DBObject result = db.getCollection("commands").findOne(q);
        assertEquals(((String) result.get("description")), "example of organization1");
    }

    @Test
    @Order(2)
    public void testScriptWithCommandUpdate() {
        Mongeez mongeez = create("mongeez_with_command_insert.xml");
        mongeez.process();

        mongeez = create("mongeez_with_command_update.xml");
        mongeez.process();
        assertEquals(db.getCollection("commands").count(), 1);

        DBObject q = new QueryBuilder().put("code").is("CODE1").get();
        DBObject result = db.getCollection("commands").findOne(q);
        assertEquals(((String) result.get("description")), "updated description of organization1");
    }
}
