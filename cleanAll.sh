#!/bin/bash

env mvn clean -Psqlgen -Pws-portafib-v1 -Pws-portafib-callback-server -Pws-portafirmas  -Pws-portafirmas-callback-server -Ppassarelaweb-v1 -Ppassarelaserver-v1
