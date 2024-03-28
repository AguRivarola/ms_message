#!/bin/bash
sudo yum update
sudo yum -y install docker git
service docker start
usermod -a -G docker ec2-user
chkconfig docker on
pip3 install docker-compose