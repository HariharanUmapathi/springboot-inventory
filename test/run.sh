#!/bin/sh
cd $(dirname $0)
cd ../initial

./mvnw clean compile
ret=$?
if [ $ret -ne 0 ]; then
exit $ret
fi
rm -rf target

exit
