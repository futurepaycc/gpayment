-- 为简化，所有的varchar(length)都可以设置为255
-- 这里设置长度，为逻辑验证和业务提供一定说明而已
create database cnpay default charset 'UTF8';
use cnpay;
create table fp_order(
    id varchar(36) PRIMARY key comment 'uuid',
    amount varchar(16) comment '金额',
    userId VARCHAR(36) comment '用户号',
    status varchar(16) comment '订单状态',
    created varchar(14) comment '创建时间yyyymmddHHMMss'
) comment='订单表';
create table fp_account(
    id VARCHAR(36) primary key comment 'uuid',
    balance varchar(16) comment '帐户总可用余额',
    balance_lock varchar(16) comment '帐户总锁定余额',
    balance_d0 varchar(16) comment 't0账户可用余额',
    balance_d0_lock varchar(16) comment 't0账户锁定余额',
    balance_d1 varchar(16) comment 'd1账户可用余额',
    balance_d1_lock varchar(16) comment 'd1账户锁定余额',
    balance_t1 varchar(16) comment 't1账户可用余额',
    balance_t1_lock varchar(16) comment 't1账户锁定余额',
    userId VARCHAR(36) comment '用户号',
    created varchar(14) comment '创建时间yyyymmddHHMMss'
) comment='帐户表';
create table fp_account_line(
    id VARCHAR(36) primary key comment 'uuid',
    in_account_id VARCHAR(36) comment '入款账户',
    out_account_id VARCHAR(36) comment '出款帐户',
    amout varchar(16) comment '转帐金额',
    created varchar(14) comment '创建时间yyyymmddHHMMss'
) comment='帐务流水表';
insert into fp_account(id,balance,balance_lock,balance_d0,balance_d0_lock,balance_d1,balance_d1_lock,balance_t1,balance_t1_lock,userId,created)
values(uuid(),"0","0","0","0","0","0","0","0","c6121fa309a630f25a2d89bc5621f3ed","20180910111114");