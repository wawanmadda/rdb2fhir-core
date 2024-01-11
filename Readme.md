# RDB2FHIR

---

## What is RDB2FHIR?

RDB2FHIR stands for *Relational Database To Fast Healthcare Interoperability Resources* (FHIR). 
RDB2FHIR uses a mapping language, known as [**RDB2OL**](#rdb2ol), to generates FHIR resources from a relational databases.
Currently RDB2FHIR only support FHIR version 4.0.1.
---

## Test

RDB2FHIR was validated against FHIR R4 Examples and US Core Profile Examples. 
The idea was saving the examples to a relational database and generating their RDB2OL. 
The database and the RDB2OL were used as inputs to RDB2FHIR to generate FHIR resources. 
The generated FHIR resources was compared to original Examples. 
The examples passed the test if the comparison is equal.  

To run the test suite for FHIR R4 Examples, type:
```shell
mvn test -Dtest=org.bayisehat.rdb2fhir.core.example.suite.R4MixTestSuite
```
To run the test suite for US Core Examples, type:
```shell
mvn test -Dtest=org.bayisehat.rdb2fhir.core.example.suite.USMixTestSuite
```
These test suites by default use in-memory database (H2). To run the tests using PostgreSQL/MYSQL,
modify the file `BaseExampleTest.java`, `USMixTestSuite.java`, and `R4MixTestSuite.java`. 

You can see the generated RDB2OL in here and the generated database in here.

___

# Usage

You can use RDB2FHIR by either importing it as a library for your project or run the executable jar.
To run RD2FHIR as an executable jar, first you need to [compile the source code](#compiling-rdb2fhir) or download the already compiled [here]().
    
```
Usage: RDB2FHIR [-hV] d=RDBMS [-f=FORMAT] [-P=PASSWORD] -u=URL [-U=USER] <RDB2OL
path> <output path>
A tool to generate FHIR resources from a relational database
RDB2OL path       path to mapping file (RDB2OL)
output path       path to generated FHIR resources
d, --db=RDBMS     Relational Database Management System: POSTGRESQL, MYSQL
-f, --format=FORMAT   FHIR Output Format: JSON, XML, or RDF
-h, --help            Show this help message and exit.
-P, --pass=PASSWORD   Database password
-u, --url=URL         Database URL
-U, --user=USER       Database user
-V, --version         Print version information and exit.
```

For example:
```shell
% java -jar rdb2fhir.jar --db=POSTGRESQL --format=JSON --user=postgres --pass=postgres --url=localhost:5432/postgres /path/to/rdb2ol.json /path/to/output
```

## Compiling RDB2FHIR

You need to compile RDB2FHIR to be able to execute it as an executable jar:

1. Unpack your zip file in whatever directory you want (use one that you can easily get to from your command line).
2. Open a shell/terminal (command prompt) and go to the directory where you put the unpacked files.
3. On the shell prompt type `mvn clean install`. This will compile RDB2FHIR. 
It may take a couple of minutes the first time because maven will download many libraries 
(you need an internet connection for this to work). 
You can also skip the tests by executing `mvn clean install -DskipTests=true`.

The compiled RDB2FHIR can be downloaded [here]().

---

# RDB2OL

---

## What is RDB2OL?

RDB2OL stands for *Relational Databases To Object Language*. 
RDB2OL conveys the mapping information needed by RDB2FHIR to generate FHIR Resources.
RDB2OL is written in JSON.  

RD2BOL consists of 1 or more **Quadruple**. Each *quadruple* consists of 4 properties (hence the name *quadruple* means 4):

- [class](#class)
- [view](#view)
- [identifier](#identifier)
- [mapping](#mapping)

Here is the example of a RDB2OL which consists of 1 quadruple:
```js
[
  {
    "class": "Patient",
    "view": {
      "tableName": "a647870152_0"
    },
    "identifier": [
      {
        "path": "Patient",
        "column": [
          "_subject"
        ]
      }
    ],
    "mapping": [
      {
        "path": "Patient.id",
        "column": [
          "a1"
        ]
      },
      {
        "path": "Patient.name.text",
        "column": [
          "a2"
        ]
      }
    ]
  }
]
```

### Class

The `class` is the resource type or the URL of the `StructureDefinition`. 
`Class` acts as the mapping target.  

### View

The `view` is either a table name or a SQL query, as the mapping source. 

### Identifier

A resource is uniquely identified using a resource type (`class`), a path (mostly using a resource type as the root), 
and 1 or more cells at specified `column`. 
These allow a resource to be referenced from another quadruple to allow mapping multiple tables to the resource.
Also, it enables mapping multiple rows to the same FHIR resources as long as the identifier is the same.

### Mapping

This property consists of one or more JSON objects, each consisting of a `path`, which is the FHIRPath. 
The `column` is a column name in a table. The `function` is a Python script. 
You can get the value from the column in the Python script using function `getValue()` and passing the column name to the function.
The `function` must return a value (`null`/`None` returned if omitted)

---
