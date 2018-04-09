#!/bin/bash

#aby wykonac skrypt bez podawania hasła:
#sudo visudo
# potem dodajesz wpis: 
#marcin ALL = NOPASSWD: /home/marcin/SpringBootRabbitMQListener/kubernetes/deploy.sh, /sbin/restart
#
#wc -l $(ls)
# echo  'ABC'$(echo "XXX" )

cd /home/marcin/SpringBootRabbitMQListener/docker
docker build -f dockerfile -t springbootrabbitmqlistener . && docker tag springbootrabbitmqlistener marcinkasinski/springbootrabbitmqlistener && docker push marcinkasinski/springbootrabbitmqlistener
echo "End pushing"


kubectl delete pod $( kubectl get pod | grep springbootrabbitmqlistener-deployment  | head -n1 | sed -e 's/\s.*$//' )




echo "End deleting pod"
