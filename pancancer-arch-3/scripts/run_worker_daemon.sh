#! /bin/bash

LOG_FILE=/var/log/arch3_worker.log
PID_FILE=/var/run/arch3_worker.pid

if [ -f $PID_FILE ]; then
  echo "Existing PID file found at $PID_FILE"
  echo "Maybe kill the Worker daemon (sudo bash kill_worker_daemon.sh) before trying to start it?"
  exit 0
fi
set -e
nohup java -cp pancancer-arch-3-*.jar info.pancancer.arch3.worker.Worker --config workerConfig.json --uuid `uuidgen` --pidFile $PID_FILE  </dev/null > $LOG_FILE 2>&1 &
PID=$!
echo $PID > $PID_FILE
echo "PID of worker daemon is $PID"
