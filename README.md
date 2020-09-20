# Note: This is a fork of https://github.com/mongeez/mongeez
It uses a different maven groupId `com.github.dimaloz.org.mongeez`.

### Functionality added in this fork
+ Added new `runAsCommand` attribute to run scripts using the `db.runCommand()` method.<br> 
+ It allows running scripts on MongoDB versions higher than 4.2.<br>

#### Description of the `runAsCommand` attribute 
If the `runAsCommand` attribute set to `true`, script will be executed by the command `db.runCommand()` and can run on MongoDB versions above 4.2<br>
By default (and for backward compatibility), the `runAsCommand`attribute set to `false`, and the `db.eval()` command used to execute the script (deprecated since MongoDB version 3.0)<br>
##### Examples of scripts with the `runAsCommand` attribute
```xml
<mongoChangeLog>
    <changeSet changeId="ChangeSet-with-command-insert" author="dlozovoy" runAsCommand="true">
        <script>
            {insert: "commands",  documents: [ {"code" : "CODE1", "description": "example of organization1", "version" : 0}]}
        </script>
    </changeSet>
</mongoChangeLog>
```
```xml
<mongoChangeLog>
    <changeSet changeId="ChangeSet-with-command-update" author="dlozovoy" runAsCommand="true">
        <script>
            {update: "commands", updates:[ {q:{"code" : "CODE1"},u:{$set:{"description": "updated description of organization1"}}}] }
        </script>
    </changeSet>
</mongoChangeLog>
```

##### How tests work
To run tests, use the database on the default host and port `127.0.0.1:27017`
 

### How to use it
Maven repo for releases: `https://repo.maven.apache.org/maven2/com/github/dimaloz/org/mongeez` <br>
To use the snapshot:
```xml
<dependency>
    <groupId>com.github.dimaloz.org.mongeez</groupId>
    <artifactId>mongeez</artifactId>
    <version>0.9.8-dimaloz-SNAPSHOT</version>
</dependency>
```

With the snapshot repository:
```xml
<repositories>
    <repository>
        <id>sonatype-nexus-snapshots</id>
        <name>Sonatype Nexus Snapshots</name>
        <url>https://oss.sonatype.org/content/repositories/snapshots</url>
        <releases>
            <enabled>false</enabled>
        </releases>
        <snapshots>
            <enabled>true</enabled>
        </snapshots>
    </repository>
</repositories>
```

### What is mongeez?

mongeez allows you to manage changes of your mongo documents and propagate these changes in sync with your code changes when you perform deployments.

For further information and usage guidelines check out [the wiki](https://github.com/mongeez/mongeez/wiki/How-to-use-mongeez).

###  Join the user group
http://groups.google.com/group/mongeez-users

### Become a contributor
http://groups.google.com/group/mongeez-dev


### Add mongeez to your project
```xml
<dependency>
    <groupId>org.mongeez</groupId>
	<artifactId>mongeez</artifactId>
	<version>0.9.6</version>
</dependency>
```

Maven repo for releases - http://repo1.maven.org/maven2

Internal versions - https://oss.sonatype.org/content/groups/public


### Or download mongeez from
repo1.maven.org - http://repo1.maven.org/maven2/org/mongeez/mongeez

### Travis Continuous Integration Build Status

Hopefully this thing is routinely green. Travis-CI monitors new code to this project and tests it on a variety of JDKs.

[![Build Status](https://travis-ci.org/mongeez/mongeez.png?branch=master)](https://travis-ci.org/mongeez/mongeez)

## License
Licensed under the Apache License, Version 2.0: http://www.apache.org/licenses/LICENSE-2.0
