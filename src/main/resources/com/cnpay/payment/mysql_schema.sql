-- 为简化，所有的varchar(length)都可以设置为255
create table fp_order(
    id varchar(36) PRIMARY key comment='uuid',
    amount varchar(16) comment='金额',
    userId VARCHAR(36) comment='用户号',
    status varchar(16) comment='订单状态',
    created varchar(14) comment='创建时间yyyymmddHHMMss'
);
create table fp_account(
    id VARCHAR(36) primary key comment='uuid',
    balance varchar(16) comment='帐户总可用余额',
    balance_lock varchar(16) comment='帐户总锁定余额',
    balance_d0 varchar(16) comment='t0账户可用余额',
    balance_d0_lock varchar(16) comment='t0账户锁定余额',
    balance_d1 varchar(16) comment='d1账户可用余额',
    balance_d1_lock varchar(16) comment='d1账户锁定余额',
    balance_t1 varchar(16) comment='t1账户可用余额',
    balance_t1_lock varchar(16) comment='t1账户锁定余额',
    userId VARCHAR(36) comment='用户号',
    created varchar(14) comment='创建时间yyyymmddHHMMss'
);
create table fp_account_line(
    id VARCHAR(36) primary key comment='uuid',
    in_account_id VARCHAR(36) comment='入款账户',
    out_account_id VARCHAR(36) comment='出款帐户',
    amout varchar(16) comment='转帐金额',
    created varchar(14) comment='创建时间yyyymmddHHMMss'
);