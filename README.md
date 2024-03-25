# JSON Policy Verifier

> An application that validates JSON policy files for AWS IAM roles

## Requirements

- **Java**: The application requires at least Java version 17 or higher.
- **Maven** or **Docker**

## How to run

Clone this repository:
```bash
git clone https://github.com/IhoreQ/json-policy-verifier.git
```

Enter into the project's root directory:

```bash
cd json-policy-verifier
```

### Command line using Docker

Run the bash script to build the project:

```bash
sh ./build.sh
```

Run the application using:

```bash
java -jar target/json-policy-verifier-1.0-SNAPSHOT.jar <path_to_json_file>
```

### Command line using locally installed Maven

If your default `java` is not from JDK 17, set `JAVA_HOME`:

On MacOS or Linux:

```bash
export JAVA_HOME=<path_to_jdk_home> 
```

On Windows:

1. Launch _Control Panel > System > Advanced system settings > Advanced > Environment variables_
2. To _add_ a new environment variable choose _New_ and enter the variable _Name_ as `JAVA_HOME` and _Value_ as `path_to_jdk_home`.
3. To _change an existing environment variable_ choose _Edit_ and enter the new _Value_ for `JAVA_HOME` variable as `path_to_jdk_home`.

Build the project:

```bash
mvn clean package
```
Run the application using:

```bash
java -jar target/json-policy-verifier-1.0-SNAPSHOT.jar <path_to_json_file>
```
