#!/bin/bash
echo "Current directory is: $('pwd')"


java -jar build/distributions/xreffed-shadow.jar server conf/xreffed.yml
