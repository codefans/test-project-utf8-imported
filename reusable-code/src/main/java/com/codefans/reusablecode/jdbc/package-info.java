/**
 * @author: codefans
 * @date: 2018-07-05 14:16
 *
 * 该包主要用于操作DML、DDL、DCL等数据库操作
 *
 * DML（data manipulation language）数据操纵语言
 *      最经常用到的 SELECT、UPDATE、INSERT、DELETE
 * DDL（data definition language）数据定义语言
 *      CREATE、ALTER、DROP
 * DCL（data Control Language）数据控制语言
 *      grant,deny,revoke
 *
 *      用来授予或回收访问数据库的某种特权，并控制数据库操纵事务发生的时间及效果，对数据库实行监视等
 *      1.COMMIT - save work done 提交

        2.SAVEPOINT - identify a point in a transaction to which you can later roll back 保存点

        3.ROLLBACK - restore database to original since the last COMMIT   回滚

        4.SET TRANSACTION - Change transaction options like what rollback segment to use   设置当前事务的特性，它对后面的事务没有影响

 */
package com.codefans.reusablecode.jdbc;