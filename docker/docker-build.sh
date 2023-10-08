#!/bin/sh

#Setting versions
VERSION='1.0.0'

cd ..
./gradlew clean build -x test

ROOT_PATH=`pwd`
echo $ROOT_PATH

echo 'user-api docker image build...'
cd $ROOT_PATH/user && docker build -t user-api:$VERSION .
echo 'user-api docker image build... DONE.'

echo 'product-api docker image build...'
cd $ROOT_PATH/product && docker build -t product-api:$VERSION .
echo 'product-api docker image build... DONE.'

echo 'nginx docker image build...'
cd $ROOT_PATH/nginx && docker build -t nginx:$VERSION .
echo 'nginx docker image build... DONE.'