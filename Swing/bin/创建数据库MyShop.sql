-- 创建myshop数据库
use master 
if exists(select * from sysdatabases where name = 'myshop')

-- 如果已经存在数据库则跳出创建数据库的过程
return
--drop database MyShop
create database MyShop
on
(
  name = 'MyShop_data',
  -- 文件夹需要存在，不然会出错，应先在C盘下建立myshop文件夹
  filename = 'E:\MyShop\MyShop_data.mdf',
  size = 10,
  filegrowth = 20%
)
-- 日志文件相关设置
log on
(
  name = 'MyShop_log',
  -- 文件夹需要存在，不然会出错，应现在C盘下建立MyShop文件夹
  filename = 'E:\MyShop\MyShop_log.ldf',
  size = 3,
  maxsize = 20,
  filegrowth = 20%
)