# Cron Expression Parser

## `Task Description`
This is a command line application which parses a cron string and expands each field to show the times at which it will run. 

This should only consider the standard cron format with five time fields (minute, hour, day of month, month, and day of week) plus a command, and it does not need to handle the special time strings such as `@yearly`.

The input will be on a single line.

## `Running the application`
### `Prerequisits`
```text
- java 11
```
### `How to run it`
```shell
./gradlew run --args="*/15 0 1,15 * 1-5 /usr/bin/find"
```

## `Running the tests`
```shell
./gradlew test
```
Location of the test report locally `./build/reports/tests/test/index.html`.

## `Assumtions, notes`

- **Assumption 1**: The valid ranges for each field:
```text
minute        0-59
hour          0-23
day of month  1-31
month         1-12
day of week   1-7
```
- **Assumption 2**: A valid command starts with a `./` or `/`
  
- **Note 1**: The following special characters have been implemented:
```text
* , - ?
```
- **Note 2**: No cross-validation of the correctness of `day of month` and `day of week` regarding the `?` character.
- **Note 3**: No validation of the correctness of the whole expression 
    - eg: month: 2, day of month: 31
- **Note 4**: The `month` and `day of week` fields can only take number representations of month or days.
