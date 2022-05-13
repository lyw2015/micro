-- 创建slave权限用户
grant replication slave on *.* to 'slave'@'%' identified by 'slave';

-- 查看主库状态
show master status;

-- 查看从库状态
show slave status;

-- 连接至主库
change master to
 master_host = '192.168.47.112',
 master_user = 'slave',
 master_password = 'slave',
 master_log_file = 'mysql-binlog.000003',
 master_log_pos = 154;

-- 开启同步
start slave;

-- 停止同步
stop slave;

-- 重置同步
reset slave;

