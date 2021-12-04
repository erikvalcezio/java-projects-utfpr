@echo off
call mvn clean package
call docker build -t br.edu.utfpr.valcezio/tarefa-ejb .
call docker rm -f tarefa-ejb
call docker run -d -p 9080:9080 -p 9443:9443 --name tarefa-ejb br.edu.utfpr.valcezio/tarefa-ejb