FROM centos:latest

RUN ["mkdir", "/var/log/httpd"]
VOLUME /var/log/httpd
