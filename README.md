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

## `Assumtions`