echo off
SET compiledPath=".\target\"
SET executeJar="commandEmulater.jar"

call mvn install -DskipTests=true
