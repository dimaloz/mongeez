# How to run MongoDb locally

## Mac OS

### Brew 
##### Installing the latest version of the database
+ For Install, run command: `brew install mongodb-community`
+ You can check configs in `/usr/local/etc/mongod.conf` 
+ For start database service, run command: `brew services start mongodb-community`
+ For stop database service, run command: `brew services stop mongodb-community`
+ For Uninstall, run command: `brew uninstall mongodb-community`

##### Installing a specific version of the database
+ For Install, run command: `brew install mongodb-community@3.6`
+ You can check configs in `/usr/local/etc/mongod.conf` 
+ For start database service, run command: `brew services start mongodb-community@3.6`
+ For stop database service, run command: `brew services stop mongodb-community@3.6`
+ For Uninstall, run command: `brew uninstall mongodb-community@3.6`

### Docker
+ run mongo container:<br>
`docker run -d -p 27017-27019:27017-27019 --name mongo mongo:3.6`
`docker stop mongo`
`docker start mongo`
`docker run -d -p 27017-27019:27017-27019 --name mongo42 mongo:4.2`