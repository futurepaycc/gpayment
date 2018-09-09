create table fp_order(
    id VARCHAR(32) PRIMARY key comment='uuid',
    amount varchat(16) comment='金额',
    userId varchar(32) comment='用户号',
    status varchar(16) comment='订单状态',
    created varchar(14) comment='创建时间yyyymmddHHMMss'
);
create table fp_account(
    id varchar(32) primary key comment='uuid',
    balance varchar(16) comment='帐户总可用余额',
    balance_lock varchar(16) comment='帐户总锁定余额',
    balance_d0 varchar(16) comment='t0账户可用余额',
    balance_d0_lock varchar(16) comment='t0账户锁定余额',
    balance_d1 varchar(16) comment='d1账户可用余额',
    balance_d1_lock varchar(16) comment='d1账户锁定余额',
    balance_t1 varchar(16) comment='t1账户可用余额',
    balance_t1_lock varchar(16) comment='t1账户锁定余额',
    userId varchar(32) comment='用户号',
    created varchar(14) comment='创建时间yyyymmddHHMMss'
);
create table fp_account_line(
    id varchar(32) primary key comment='uuid',
    in_account_id varchar(32) comment='入款账户',
    out_account_id varchar(32) comment='出款帐户',
    amout varchar(16) comment='转帐金额',
    created varchar(14) comment='创建时间yyyymmddHHMMss'
);