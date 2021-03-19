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

import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;


public abstract class TestDatabaseSetup {
    private static final String dbName = "test_mongeez";
    private static final int port = 27019;
    private static final Mongo mongo;
    public static final DB db;

    static  {
        MongodStarter starter = MongodStarter.getDefaultInstance();
        String host = "localhost";

        IMongodConfig mongodConfig;
        try {
            mongodConfig = new MongodConfigBuilder()
                    .version(Version.Main.PRODUCTION)
                    .net(new Net(host, port, Network.localhostIsIPv6()))
                    .build();
            starter.prepare(mongodConfig).start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        mongo = new MongoClient(host, port);
        db = mongo.getDB(dbName);
    }

    public static Mongeez createMongeez(String path) {
        Mongeez mongeez = new Mongeez();
        mongeez.setFile(new ClassPathResource(path));
        mongeez.setMongo(mongo);
        mongeez.setDbName(dbName);
        return mongeez;
    }
}
