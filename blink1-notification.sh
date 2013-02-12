#!/bin/sh

java -Djava.awt.headless=true -Djava.library.path=lib -cp "lib/*" com.projects.blink1.weather.App $*

