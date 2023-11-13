@echo off
start "VUE APP" /D G:\vue-workspace\drivers-app npm run serve
start "Springboot App" /D  H:\spring-boot-workspace\springboot-inventory mvnw spring-boot:run
@echo on