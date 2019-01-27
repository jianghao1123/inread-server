#!/bin/sh

## chang here
SERVICE_DIR=/root/inread
SERVICE_NAME=inread-boot-1.0-SNAPSHOT
SPRING_PROFILES_ACTIVE=prod
SERVER_PORT=8091

## jvm
JVM_OPTS= -Dname=$SERVICE_NAME -Djava.awt.headless=true -Xms512M -Xmx512M -XX:PermSize=256M -XX:MaxPermSize=512M -XX:+HeapDumpOnOutOfMemoryError -XX:+PrintGCDateStamps -Xloggc:$GC_LOG_PATH -XX:+PrintGCDetails -XX:++UseConcMarkSweepGC

case "$1" in
	start)
		procedure=`ps -ef | grep -w "${SERVICE_NAME}" |grep -w "java"| grep -v "grep" | awk '{print $2}'`
		if [ "${procedure}" = "" ];
		then
			echo "start ..."
			if [ "$2" != "" ];
			then
				SPRING_PROFILES_ACTIVE=$2
			fi
			echo "spring.profiles.active=${SPRING_PROFILES_ACTIVE}"
			exec nohup java $JVM_OPTS -jar ${SERVICE_DIR}/${SERVICE_NAME}\.jar --server.port=$SERVER_PORT --spring.profiles.active=${SPRING_PROFILES_ACTIVE} >/dev/null 2>&1 &
			echo "start success"
		else
			echo "${SERVICE_NAME} is start"
		fi
		;;

	stop)
		procedure=`ps -ef | grep -w "${SERVICE_NAME}" |grep -w "java"| grep -v "grep" | awk '{print $2}'`
		if [ "${procedure}" = "" ];
		then
			echo "${SERVICE_NAME} is stop"
		else
			kill -9 ${procedure}
			sleep 1
			argprocedure=`ps -ef | grep -w "${SERVICE_NAME}" |grep -w "java"| grep -v "grep" | awk '{print $2}'`
			if [ "${argprocedure}" = "" ];
			then
				echo "${SERVICE_NAME} stop success"
			else
				kill -9 ${argprocedure}
				echo "${SERVICE_NAME} stop error"
			fi
		fi
		;;

	restart)
		$0 stop
		sleep 1
		$0 start $2
		;;

	*)
		echo "usage: $0 [start|stop|restart] [dev|test|prod]"
		;;
esac