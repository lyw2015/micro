java ^
-Dserver.port=8858 ^
-Dproject.name=sentinel-dashboard ^
-Dsentinel.dashboard.auth.username=sentinel ^
-Dsentinel.dashboard.auth.password=sentinel ^
-Dsentinel.dashboard.nacos.serverAddr=127.0.0.1:8848 ^
-Dsentinel.dashboard.nacos.namespace=18808baf-2238-4f27-be8b-6cfa3680a0a2 ^
-Dsentinel.dashboard.nacos.groupId=SENTINEL_GROUP ^
-jar sentinel-dashboard.jar

rem -Dcsp.sentinel.dashboard.server=localhost:8858 ^