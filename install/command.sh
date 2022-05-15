# 修改静态IP
vim /etc/sysconfig/network-scripts/ifcfg-ens33
#BOOTPROTO="static"
#IPADDR=192.168.116.129
#NETMASK=255.255.255.0
#GATEWAY=192.168.116.2
#DNS1=114.114.114.114
#DNS2=8.8.8.8
systemctl restart network

# 安装Docker
yum install -y yum-utils device-mapper-persistent-data lvm2
yum-config-manager --add-repo http://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo
yum install -y docker-ce
systemctl start docker && systemctl enable docker

# 镜像加速
vim /etc/docker/daemon.json
#{
#  "registry-mirrors": ["https://xxx.mirror.aliyuncs.com"]
#}

# 下载docker-compose
wget https://github.com/docker/compose/releases/download/v2.5.0/docker-compose-linux-x86_64 -O /usr/local/bin/docker-compose &&
  chmod +x /usr/local/bin/docker-compose

# 禁止防火墙开机自启
systemctl stop firewalld && systemctl disable firewalld

# 创建组件目录
mkdir -p \
  /data/docker-compose \
  /data/mysql/conf \
  /data/mysql/logs/binlog \
  /data/redis/conf \
  /data/nginx \
  /data/seata-server/config

# 创建服务目录
mkdir -p \
  /data/apps/id-service \
  /data/apps/sentinel-dashboard \
  /data/apps/gateway \
  /data/apps/portal \
  /data/apps/stock \
  /data/apps/order

# 远程拷贝
scp /data/docker-compose/* root@192.168.116.130:/data/docker-compose/
scp /data/apps/* root@192.168.116.130:/data/apps/

# 重新部署服务
docker-compose -f /data/docker-compose/service.yaml up -d --no-deps gateway

# 查看服务日志
docker-compose -f /data/docker-compose/service.yaml logs id-service

# 安装keepalived
# 基础依赖
yum install -y gcc openssl-devel libnl libnl-devel libnfnetlink-devel net-tools
# 下载、解压、改名、删除源文件、进入目录
wget https://www.keepalived.org/software/keepalived-2.2.7.tar.gz --no-check-certificate -P /data &&
  tar -zxf keepalived-2.2.7.tar.gz &&
  mv /data/keepalived-2.2.7 /data/keepalived &&
  rm -rf /data/keepalived-2.2.7.tar.gz &&
  cd /data/keepalived

# 编译安装
./configure && make && make install

# 复制文件到系统
mkdir /etc/keepalived &&
  cp /data/keepalived/keepalived/etc/init.d/keepalived /etc/init.d/ &&
  cp /data/keepalived/keepalived/etc/keepalived/keepalived.conf.sample /etc/keepalived/keepalived.conf &&
  cp /etc/keepalived/keepalived.conf /etc/keepalived/keepalived.conf.backup

# 配置vim /etc/keepalived/keepalived.conf

systemctl start keepalived && systemctl enable keepalived
