# Java-selenium-cucumber


This project is an E2E automated functional testing for **Estratégia Educacional** website using Selenium and Cucumber.

## Pre-requisites:

- NodeJS
- Java 8 JDK
- Maven
- Selenium 4 _(the latest version)_
- Chrome browser
- Firefox browser
- Docker


## Installation:

It is required to setup **ChromeDriver** and **GeckoDriver** on your system on `src/test/resources/drivers` according to the browser version. Follows below:

- **Chrome**: To run the tests locally with _Chrome_, install ChromeDriver from [here](http://chromedriver.chromium.org/)


- **Firefox**: To run the tests locally with _Firefox_, install GeckoDriver from [here](https://github.com/mozilla/geckodriver/releases)


## Maven Profile ##

Maven profiles can be used to create customized build configurations, so in this project has been used profiles to set the environment and base URL, such as:

- For `env=prod` (production):

```console
$ url=https://www.estrategiaconcursos.com.br
```


## Running tests ##

- To install all dependencies - It will run all tests in **chrome** browser

```console
$ mvn clean install
```

- Run all tests using **chrome** browser

```console
$  mvn clean test -Denv=prod -Dbrowser=chrome
```

- Run all tests using **firefox** browser

```console
$ mvn clean test -Denv=prod -Dbrowser=firefox
```


## Running tests using docker


- Build docker-compose

```console
$ docker-compose -f docker-compose.yml up -d
```

- Run all tests using **remote** browser

```console
$ mvn clean test -Denv=prod -Dbrowser=remote
```

- Launch the selenium-grid server

```console
$ http://localhost:4444
```

- Stops containers

```console
$ docker-compose down
```
