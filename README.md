# StructureDB
Distributed Database for Large Data Structures. Please note this is still under development and the queries list will be updated as they are added.

### Dependencies

* Maven
* Java 8

### Building the database server

* Use the following maven command to build StructureDB:

```
mvn clean package
```

The executable jar file can be found in the `target` directory.


### Running the database server

* Once the jar is built, you can use the following command to run the database server:

```
java -jar structuredb-1.0-SNAPSHOT.jar /path/to/conf
```

### Configuration File

* A sample configuration file for StructureDB looks like this

```
host=127.0.0.1
port=9876
data=/home/somsubhra/.structuredb/data
```


### Accessing the Database

* You can use `telnet` to access the database

```
telnet 127.0.0.1 9876
```


### Queries

* Creating a new app

```
sdb-create-app {"app": "testapp"}
```

* Listing all apps

```
sdb-list-apps
```

### License

StructureDB is MIT-licensed. Please access the LICENSE file for the full license.
