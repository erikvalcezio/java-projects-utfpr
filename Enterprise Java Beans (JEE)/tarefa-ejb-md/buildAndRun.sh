#!/bin/sh
mvn clean package && docker build -t br.edu.utfpr.valcezio/tarefa-ejb .
docker rm -f tarefa-ejb || true && docker run -d -p 9080:9080 -p 9443:9443 --name tarefa-ejb br.edu.utfpr.valcezio/tarefa-ejb