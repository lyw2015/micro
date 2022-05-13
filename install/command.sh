# 下载docker-compose
wget https://github.com/docker/compose/releases/download/v2.5.0/docker-compose-linux-x86_64 -O /usr/local/bin/docker-compose
# 授予执行权限
chmod +x /usr/local/bin/docker-compose

# 禁止防火墙开机自启
systemctl disable firewalld

# 创建服务目录
mkdir -p \
  /data/apps/id-service \
  /data/apps/sentinel-dashboard \
  /data/apps/gateway \
  /data/apps/portal \
  /data/apps/stock \
  /data/apps/order

# 重新部署服务
docker-compose -f /data/docker-compose/service.yaml up -d --no-deps gateway

# 查看服务日志
docker-compose -f /data/docker-compose/service.yaml logs id-service
